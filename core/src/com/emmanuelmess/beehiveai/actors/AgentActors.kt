package com.emmanuelmess.beehiveai.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.emmanuelmess.beehiveai.BeehiveAI
import java.lang.Math.pow
import kotlin.math.sqrt

const val AGENT_WIDTH = 40
const val AGENT_HEIGHT = 40
const val AGENT_MARGIN = 7

open class AgentActor(texture: Texture): BitmapActor(texture) {
    var shootingTarget: AgentActor? = null

    private val BULLET_VELOCITY = 300f
    private val HIT_RANGE = 5

    private val COOLDOWN_TIME = 1f
    private var lastShot = 0f

    override fun act(delta: Float) {
        super.act(delta)

        lastShot += delta

        if(lastShot < COOLDOWN_TIME) {
            return
        }

        shootingTarget?.let { shootingTarget: AgentActor ->
            val bullet = BeehiveAI.shotsPool.obtain()

            BeehiveAI.bulletGroup.addActor(bullet.also {
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
                                val hit = BeehiveAI.actorFoeGroup.children.filter { found ->
                                    pos.set(found.x, found.y).dst(it.x, it.y) < HIT_RANGE
                                }.first()

                                BeehiveAI.actorFoeGroup.removeActor(hit)
                                this.shootingTarget = null

                                BeehiveAI.bulletGroup.removeActor(it)
                                BeehiveAI.shotsPool.free(it)
                            }
                        }
                ))
            })
        }

        lastShot = 0f
    }
}

class FriendAgent: AgentActor(BeehiveAI.friendAgentTexture) {
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

class FoeAgent: AgentActor(BeehiveAI.foeAgentTexture) {
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