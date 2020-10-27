package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actors.AgentActor
import com.emmanuelmess.beehiveai.actors.FoeAgent

object AI {
    private var isDone = false

    fun act() {
        val foeAgents = BeehiveAI.actorFoeGroup.children

        if(foeAgents.isEmpty) return

        (BeehiveAI.actorFriendGroup.getChild(0) as AgentActor).shootingTarget = foeAgents.get(0) as AgentActor

        isDone = true
    }
}