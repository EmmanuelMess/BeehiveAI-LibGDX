package com.emmanuelmess.beehiveai

import com.badlogic.gdx.utils.Align.center
import com.badlogic.gdx.utils.Align.topLeft
import com.emmanuelmess.beehiveai.actors.FoePawn
import com.emmanuelmess.beehiveai.actors.PawnActor

object EnemyAI {
    private var isDone = false

    fun act() {
        if(isDone) return

        Game.pawnFoeGroup.addActor(FoePawn().apply {
            setX(100f, center)
            setY(100f, center)
            fireOnTo(Game.pawnFriendGroup.getChild(0) as PawnActor)
        })
        isDone = true
    }
}