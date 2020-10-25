package com.emmanuelmess.beehiveai.actor

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture


class AgentActor(texture: Texture): BitmapActor(texture) {
    companion object {
        val AGENT_WIDTH = 40
        val AGENT_HEIGHT = 40

        fun getTexture(): Texture {
            Pixmap(AGENT_WIDTH, AGENT_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BLACK)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2)
                return Texture(it)
            }
        }
    }


}