package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actors.FoePawn

object EnemyAI {
    private var isDone = false

    fun act() {
        if(isDone) return

        Game.pawnFoeGroup.addActor(FoePawn().apply {
            x = 100f
            y = 100f
        })
        isDone = true
    }
}