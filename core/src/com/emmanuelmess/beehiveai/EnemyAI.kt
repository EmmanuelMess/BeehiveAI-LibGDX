package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actors.FoePawn
import com.emmanuelmess.beehiveai.actors.PawnActor

object EnemyAI {
    private var isDone = false

    fun act() {
        if(isDone) return

        Game.pawnFoeGroup.addActor(FoePawn().apply {
            x = 100f
            y = 100f
            fireOnTo(Game.pawnFriendGroup.getChild(0) as PawnActor)
        })
        isDone = true
    }
}