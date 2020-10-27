package com.emmanuelmess.beehiveai.actions

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction
import com.emmanuelmess.beehiveai.actors.AGENT_WIDTH

class MoveToWidthinAction(val x: Float, val y: Float, val velocity: Float): MoveByAction() {

    override fun setActor(actor: Actor?) {
        if(actor != null) {
            val start = Vector2(actor.x, actor.y)
            val pseudoEnd = Vector2(x, y)
            val dist = pseudoEnd.sub(start)
            val realEnd = dist.cpy().nor().scl(dist.len() - AGENT_WIDTH / 4)

            amountX = realEnd.x
            amountY = realEnd.y
            duration = dist.len() / velocity
        }
        super.setActor(actor)
    }
}