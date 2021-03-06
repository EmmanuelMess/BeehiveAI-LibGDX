package com.emmanuelmess.beehiveai.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.emmanuelmess.beehiveai.Game
import com.emmanuelmess.beehiveai.actions.CreateBlockAtAction
import com.emmanuelmess.beehiveai.actions.MoveToWidthinAction
import com.emmanuelmess.beehiveai.actions.ShootingAtAction

const val AGENT_WIDTH = 40
const val AGENT_HEIGHT = 40
const val AGENT_MARGIN = 7

open class PawnActor(texture: Texture): BitmapActor(texture) {
    companion object {
        const val AGENT_VELOCITY = 50f

        private val COOLDOWN_TIME = 1f
    }

    init {
        Game.colisionChecker.pawns.add(this)
    }

    fun onShot(bullet: BulletActor) {
        if(bullet.shooter === this) {
            return
        }

        remove()
    }

    fun shotSomething(actor: Actor) {
        if(actor !is PawnActor) {
            return
        }

        for (action in actions) {
            if(action is ShootingAtAction) {
                removeAction(action)
            }
        }
    }

    fun fireOnTo(actor: PawnActor) {
        addAction(ShootingAtAction(actor, COOLDOWN_TIME))
    }

    fun gotToWithinPlaceBlock(x: Float, y: Float) {
        addAction(SequenceAction(
                MoveToWidthinAction(x, y, AGENT_VELOCITY),
                CreateBlockAtAction(x.toInt(), y.toInt())
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