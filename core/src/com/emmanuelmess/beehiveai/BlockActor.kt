package com.emmanuelmess.beehiveai

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.emmanuelmess.beehiveai.actor.BitmapActor

class BlockActor(texture: Texture): BitmapActor(texture) {
    companion object {
        val BLOCK_WIDTH = 100
        val BLOCK_HEIGHT = 100

        fun getTexture(): Texture {
            Pixmap(BLOCK_HEIGHT, BLOCK_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.GREEN)
                it.fillCircle(BLOCK_WIDTH / 2, BLOCK_HEIGHT / 2, BLOCK_WIDTH / 5)
                return Texture(it)
            }
        }
    }


}