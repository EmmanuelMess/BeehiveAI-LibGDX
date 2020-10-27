package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actors.AgentActor
import com.emmanuelmess.beehiveai.actors.FriendAgent

object AI {
    private var isDone = false

    fun act() {
        if(isDone) return

        val actor = FriendAgent().also {
            it.x = Game.Size.WIDTH / 2
            it.y = Game.Size.HEIGHT / 2
        }

        Game.actorFriendGroup.addActor(actor)

        actor.gotToWithinPlaceBlock(Game.Size.WIDTH - 200, Game.Size.HEIGHT - 300)

        val foeAgents = Game.actorFoeGroup.children

        if (foeAgents.isEmpty) return

        //actor.shootingTarget = foeAgents.get(0) as AgentActor

        isDone = true
    }
}