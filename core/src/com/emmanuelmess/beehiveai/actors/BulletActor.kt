package com.emmanuelmess.beehiveai.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture

class BulletActor(): BitmapActor(getTexture()) {
    companion object {
        val BULLET_WIDTH = 100
        val BULLET_HEIGHT = 100

        fun getTexture(): Texture {
            Pixmap(BULLET_WIDTH, BULLET_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BLACK)
                it.fillCircle(BULLET_WIDTH/2, BULLET_HEIGHT/2, BULLET_WIDTH/2)
                return Texture(it)
            }
        }
    }
}