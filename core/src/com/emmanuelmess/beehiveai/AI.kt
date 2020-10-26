package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actions.AgentPutBlock

object AI {
    fun act() {
        BeehiveAI.actorGroup.getChild(0).addAction(AgentPutBlock(50, 50))
    }
}