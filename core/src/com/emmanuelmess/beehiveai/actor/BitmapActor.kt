package com.emmanuelmess.beehiveai.actor

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image

open class BitmapActor(
        texture: Texture
): Image(texture) {

    object Size {
        val SQUARE_WIDTH = 10f
    }

    init {
        width = Size.SQUARE_WIDTH.toFloat()
        height = Size.SQUARE_WIDTH.toFloat()
    }
}