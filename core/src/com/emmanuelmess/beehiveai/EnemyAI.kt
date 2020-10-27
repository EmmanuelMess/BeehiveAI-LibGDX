package com.emmanuelmess.beehiveai

import com.emmanuelmess.beehiveai.actors.FoeAgent

object EnemyAI {
    private var isDone = false

    fun act() {
        if(isDone) return

        Game.actorFoeGroup.addActor(FoeAgent().apply {
            x = 100f
            y = 100f
        })
        isDone = true
    }
}