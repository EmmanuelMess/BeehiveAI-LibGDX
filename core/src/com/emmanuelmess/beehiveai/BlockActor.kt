package com.emmanuelmess.beehiveai

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.emmanuelmess.beehiveai.actor.BitmapActor

class BlockActor(texture: Texture): BitmapActor(texture) {
    companion object {
        val BLOCK_WIDTH = 40
        val BLOCK_HEIGHT = 40

        fun getTexture(): Texture {
            Pixmap(BLOCK_HEIGHT, BLOCK_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BROWN)
                it.fillRectangle(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT)
                return Texture(it)
            }
        }
    }


}