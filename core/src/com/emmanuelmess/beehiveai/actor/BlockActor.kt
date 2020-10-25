package com.emmanuelmess.beehiveai.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture

class BlockActor(texture: Texture): BitmapActor(texture) {
    companion object {
        val BLOCK_WIDTH = 40
        val BLOCK_HEIGHT = 40

        fun getTexture(): Texture {
            Pixmap(BLOCK_HEIGHT, BLOCK_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BROWN)
                it.fillRectangle(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT)

                it.setColor(Color.BLACK)
                it.drawLine(0, 0, BLOCK_WIDTH, BLOCK_HEIGHT)
                it.drawLine(BLOCK_WIDTH -1, 0, 0, BLOCK_HEIGHT -1)
                return Texture(it)
            }
        }
    }


}