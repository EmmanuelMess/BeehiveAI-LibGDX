package com.emmanuelmess.beehiveai

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Align.bottomLeft
import com.badlogic.gdx.utils.Align.center
import com.emmanuelmess.beehiveai.actors.BoxActor
import com.emmanuelmess.beehiveai.actors.BulletActor
import com.emmanuelmess.beehiveai.actors.PawnActor


class ColisionChecker {
    val bullets = mutableSetOf<BulletActor>()
    val boxes = mutableSetOf<BoxActor>()
    val pawns = mutableSetOf<PawnActor>()

    fun doChecks() {
        for (bullet in bullets) {
            val bulletCollision = Circle(bullet.getX(center), bullet.getY(center), bullet.width/2f)
            for(pawn in pawns) {
                val pawnCollision = Circle(pawn.getX(center), pawn.getY(center), pawn.width/2f)
                if(Intersector.overlaps(bulletCollision, pawnCollision)) {
                    pawn.onShot(bullet)
                    bullet.onHit(pawn)
                }
            }

            for(box in boxes) {
                val boxCollision = Rectangle(box.getX(bottomLeft), box.getY(bottomLeft), box.width, box.height)
                if(Intersector.overlaps(bulletCollision, boxCollision)) {
                    bullet.onHit(box)
                }
            }
        }
    }
}