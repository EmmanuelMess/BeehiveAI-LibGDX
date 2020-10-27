package com.emmanuelmess.beehiveai.actions

import com.badlogic.gdx.scenes.scene2d.Action
import com.emmanuelmess.beehiveai.Game
import com.emmanuelmess.beehiveai.actors.BlockActor

class CreateBlockAtAction(val x: Int, val y: Int): Action() {
    override fun act(delta: Float): Boolean {
        Game.blockGroup.addActor(BlockActor().also {
            it.setPosition(x.toFloat(), y.toFloat())
        })
        return true
    }
}