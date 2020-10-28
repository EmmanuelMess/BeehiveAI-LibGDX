package com.emmanuelmess.beehiveai.actions

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction
import com.badlogic.gdx.utils.Align.center
import com.emmanuelmess.beehiveai.Game
import com.emmanuelmess.beehiveai.actors.BulletActor
import com.emmanuelmess.beehiveai.actors.PawnActor

class ShootingAtAction(
        val shootingTarget: PawnActor,
        val bulletVelocity: Float,
        val hitRange: Int,
        val cooldownTime: Float
) : Action() {
    private var lastShot = 0f

    override fun act(delta: Float): Boolean {
        lastShot += delta

        if (lastShot < cooldownTime) {
            return false
        }

        val bullet = Game.shotsPool.obtain()

        Game.bulletGroup.addActor(bullet.also {
            val start = Vector2(actor.x, actor.y)
            val end = Vector2(shootingTarget.x, shootingTarget.y)

            it.shooter = actor as PawnActor
            it.setPosition(start.x, start.y, center)
            it.addAction(ShotBulletAction(start, end, bulletVelocity, hitRange))
        })

        lastShot = 0f

        return false
    }
}

val ShotBulletAction = { start: Vector2, end: Vector2, bulletVelocity: Float, hitRange: Int ->
    SequenceAction(
            MoveToAction().also { action ->
                action.x = end.x
                action.y = end.y
                action.duration = start.dst(end) / bulletVelocity
            },
            Actions.removeActor()
    )
}