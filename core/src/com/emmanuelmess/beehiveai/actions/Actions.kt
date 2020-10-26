package com.emmanuelmess.beehiveai.actions

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.emmanuelmess.beehiveai.BeehiveAI
import com.emmanuelmess.beehiveai.actors.BlockActor

class CreateBlockAt(x: Int, y: Int): Action() {
    override fun act(delta: Float): Boolean {
        BeehiveAI.blockGroup.addActor(BlockActor())
        return true
    }
}

val AgentPutBlock = { x: Int, y: Int ->
    SequenceAction(
            MoveToAction().also {
                it.x = x.toFloat()
                it.y = y.toFloat()
            },
            CreateBlockAt(x, y)
    )
}