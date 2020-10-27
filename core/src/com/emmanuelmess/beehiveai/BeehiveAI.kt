package com.emmanuelmess.beehiveai

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Pools
import com.badlogic.gdx.utils.viewport.FillViewport
import com.emmanuelmess.beehiveai.actors.BlockActor
import com.emmanuelmess.beehiveai.actors.BulletActor
import com.emmanuelmess.beehiveai.actors.FoeAgent
import com.emmanuelmess.beehiveai.actors.FriendAgent


object BeehiveAI : ApplicationAdapter() {
    object Size {
        const val WIDTH = 800f
        const val HEIGHT = 600f

        const val C = 10f
    }

    private lateinit var viewport: FillViewport
    private lateinit var camera: OrthographicCamera

    lateinit var stage: Stage

    lateinit var blockGroup: Group
    lateinit var actorGroup: Group
    lateinit var actorFoeGroup: Group
    lateinit var actorFriendGroup: Group
    lateinit var bulletGroup: Group

    var shotsPool = Pools.get(BulletActor::class.java)

    lateinit var blockTexture: Texture
    lateinit var friendAgentTexture: Texture
    lateinit var foeAgentTexture: Texture

    override fun create() {
        Gdx.graphics.isContinuousRendering = true
        Gdx.graphics.requestRendering()

        camera = OrthographicCamera().apply {
            setToOrtho(true, Size.WIDTH, Size.HEIGHT)
        }
        viewport = FillViewport(Size.WIDTH, Size.HEIGHT, camera)

        blockTexture = BlockActor.getTexture()
        friendAgentTexture = FriendAgent.getTexture()
        foeAgentTexture = FoeAgent.getTexture()

        blockGroup = Group().apply {
            addActor(BlockActor())
        }

        actorGroup = Group()
        bulletGroup = Group()

        actorFriendGroup = Group().apply {
            addActor(FriendAgent().also {
                it.x = 10f
                it.y = 15f
            })
        }
        actorFoeGroup = Group()

        stage = Stage(viewport).also {
            it.addActor(blockGroup)
            actorGroup.addActor(actorFoeGroup)
            actorGroup.addActor(actorFriendGroup)
            it.addActor(actorGroup)
            it.addActor(bulletGroup)
        }

    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT
                or if (Gdx.graphics.bufferFormat.coverageSampling) GL20.GL_COVERAGE_BUFFER_BIT_NV else 0)

        stage.draw()
        stage.act()

        AI.act()
        EnemyAI.act()
    }

    override fun dispose() {
        blockTexture.dispose()
    }
}