package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actors.AgentActor
import com.emmanuelmess.beehiveai.actors.FriendAgent

object AI {
    private var isDone = false

    fun act() {
        Game.actorFriendGroup.addActor(FriendAgent().also {
            it.x = Game.Size.WIDTH / 2
            it.y = Game.Size.HEIGHT / 2
        })

        val foeAgents = Game.actorFoeGroup.children

        if (foeAgents.isEmpty) return

        (Game.actorFriendGroup.getChild(0) as AgentActor).shootingTarget = foeAgents.get(0) as AgentActor

        isDone = true
    }
}