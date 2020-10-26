package com.emmanuelmess.beehiveai

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Container
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.emmanuelmess.beehiveai.actors.FriendAgent
import com.emmanuelmess.beehiveai.actors.BlockActor
import com.emmanuelmess.beehiveai.actors.FoeAgent

object BeehiveAI : ApplicationAdapter() {
    object Size {
        const val WIDTH = 800f
        const val HEIGHT = 600f

        const val C = 10f
    }

    private lateinit var viewport: FillViewport
    private lateinit var camera: OrthographicCamera

    private lateinit var textViewport: FitViewport

    lateinit var stage: Stage

    lateinit var blockGroup: Group
    lateinit var actorGroup: Group

    lateinit var blockTexture: Texture
    lateinit var friendAgentTexture: Texture
    lateinit var foeAgentTexture: Texture

    override fun create() {
        Gdx.graphics.isContinuousRendering = false
        Gdx.graphics.requestRendering()

        camera = OrthographicCamera().apply {
            setToOrtho(true, Size.WIDTH, Size.HEIGHT)
        }
        viewport = FillViewport(Size.WIDTH, Size.HEIGHT, camera)

        textViewport = FitViewport(Size.WIDTH, Size.HEIGHT)

        blockTexture = BlockActor.getTexture()
        friendAgentTexture = FriendAgent.getTexture()
        foeAgentTexture = FoeAgent.getTexture()

        blockGroup = Group().apply {
            addActor(BlockActor())
        }

        actorGroup = Group().apply {
            addActor(FriendAgent().also {
                it.x = 10f
                it.y = 15f
            })
        }

        stage = Stage(textViewport).also {
            it.addActor(Container(blockGroup).apply {
                setFillParent(true)
            })
            it.addActor(Container(actorGroup).apply {
                setFillParent(true)
            })
        }

        AI.act()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT
                or if(Gdx.graphics.bufferFormat.coverageSampling) GL20.GL_COVERAGE_BUFFER_BIT_NV else 0)

        stage.draw()
        stage.act()
    }

    override fun dispose() {
        blockTexture.dispose()
    }
}