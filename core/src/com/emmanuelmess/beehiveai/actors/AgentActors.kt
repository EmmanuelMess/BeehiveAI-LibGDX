package com.emmanuelmess.beehiveai.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.emmanuelmess.beehiveai.BeehiveAI

const val AGENT_WIDTH = 40
const val AGENT_HEIGHT = 40
const val AGENT_MARGIN = 7

class FriendAgent: BitmapActor(BeehiveAI.friendAgentTexture) {
    companion object {
        fun getTexture(): Texture {
            Pixmap(AGENT_WIDTH, AGENT_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BLACK)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2)
                it.setColor(Color.GREEN)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2 - AGENT_MARGIN)
                return Texture(it)
            }
        }
    }
}

class FoeAgent: BitmapActor(BeehiveAI.foeAgentTexture) {
    companion object {
        fun getTexture(): Texture {
            Pixmap(AGENT_WIDTH, AGENT_HEIGHT, Pixmap.Format.RGBA8888).also {
                it.setColor(Color.BLACK)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2)
                it.setColor(Color.RED)
                it.fillCircle(AGENT_WIDTH /2, AGENT_HEIGHT /2, AGENT_WIDTH /2 - AGENT_MARGIN)
                return Texture(it)
            }
        }
    }
}