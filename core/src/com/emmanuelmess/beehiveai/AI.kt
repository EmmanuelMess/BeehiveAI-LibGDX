package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actors.FriendPawn

object AI {
    private var isDone = false

    fun act() {
        if(isDone) return

        val actor = FriendPawn().also {
            it.x = Game.Size.WIDTH / 2
            it.y = Game.Size.HEIGHT / 2
        }

        Game.pawnFriendGroup.addActor(actor)

        actor.gotToWithinPlaceBlock(Game.Size.WIDTH - 200, Game.Size.HEIGHT - 300)

        val foeAgents = Game.pawnFoeGroup.children

        if (foeAgents.isEmpty) return

        //actor.shootingTarget = foeAgents.get(0) as AgentActor

        isDone = true
    }
}