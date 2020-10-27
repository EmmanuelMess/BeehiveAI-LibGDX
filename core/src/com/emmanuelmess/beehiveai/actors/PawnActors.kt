package com.emmanuelmess.beehiveai.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.emmanuelmess.beehiveai.Game
import com.emmanuelmess.beehiveai.actions.CreateBlockAt

const val AGENT_WIDTH = 40
const val AGENT_HEIGHT = 40
const val AGENT_MARGIN = 7

open class PawnActor(texture: Texture): BitmapActor(texture) {
    companion object {
        const val AGENT_VELOCITY = 50f

        private val BULLET_VELOCITY = 300f
        private val HIT_RANGE = 5

        private val COOLDOWN_TIME = 1f
    }

    var shootingTarget: PawnActor? = null

    private var lastShot = 0f

    override fun act(delta: Float) {
        super.act(delta)

        lastShot += delta

        if(lastShot < COOLDOWN_TIME) {
            return
        }

        shootingTarget?.let { shootingTarget: PawnActor ->
            val bullet = Game.shotsPool.obtain()

            Game.bulletGroup.addActor(bullet.also {
                val start = Vector2(x, y)
                val end = Vector2(shootingTarget.x, shootingTarget.y)

                it.setPosition(start.x, start.y)
                it.addAction(SequenceAction(
                        MoveToAction().also { action ->
                            action.x = end.x
                            action.y = end.y
                            action.duration = start.dst(end) / BULLET_VELOCITY
                        },
                        RunnableAction().also { action ->
                            action.runnable = Runnable {
                                val pos = Vector2()
                                val hit = Game.pawnFoeGroup.children.filter { found ->
                                    pos.set(found.x, found.y).dst(it.x, it.y) < HIT_RANGE
                                }.firstOrNull()

                                Game.pawnFoeGroup.removeActor(hit)
                                this.shootingTarget = null

                                Game.bulletGroup.removeActor(it)
                                Game.shotsPool.free(it)
                            }
                        }
                ))
            })
        }

        lastShot = 0f
    }

    fun gotToWithinPlaceBlock(x: Float, y: Float) {
        val start = Vector2(this.x, this.y)
        val pseudoEnd = Vector2(x, y)
        val dist = pseudoEnd.sub(start)
        val realEnd = dist.cpy().nor().scl(dist.len() - AGENT_WIDTH /4)

        actions.clear()

        shootingTarget = null

        addAction(SequenceAction(
                MoveToAction().also {
                    it.x = start.x + realEnd.x
                    it.y = start.y + realEnd.y
                    it.duration = realEnd.len() / AGENT_VELOCITY
                },
                CreateBlockAt(x.toInt(), y.toInt())
        ))
    }
}

class FriendPawn: PawnActor(Game.friendAgentTexture) {
    companion object {
        fun getTexture(): Texture {
            Pixmap(AGENT_WIDTH, AGENT_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BLACK)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2)
                it.setColor(Color.GREEN)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2 - AGENT_MARGIN)
                return Texture(it)
            }
        }
    }
}

class FoePawn: PawnActor(Game.foeAgentTexture) {
    companion object {
        fun getTexture(): Texture {
            Pixmap(AGENT_WIDTH, AGENT_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BLACK)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2)
                it.setColor(Color.RED)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2 - AGENT_MARGIN)
                return Texture(it)
            }
        }
    }
}