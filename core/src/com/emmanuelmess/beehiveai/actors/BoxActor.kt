package com.emmanuelmess.beehiveai.actors

import com.badlogic.gdx.graphics.Texture
import com.emmanuelmess.beehiveai.Game

class BoxActor(
        texture: Texture
): BitmapActor(texture) {
    init {
        Game.colisionChecker.boxes.add(this)
    }
}