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
import com.emmanuelmess.beehiveai.actors.FoePawn
import com.emmanuelmess.beehiveai.actors.FriendPawn


object Game : ApplicationAdapter() {
    object Size {
        const val WIDTH = 800f
        const val HEIGHT = 600f
    }

    val colisionChecker = ColisionChecker()

    private lateinit var viewport: FillViewport
    private lateinit var camera: OrthographicCamera

    lateinit var stage: Stage

    lateinit var blockGroup: Group
    lateinit var pawnGroup: Group
    lateinit var pawnFoeGroup: Group
    lateinit var pawnFriendGroup: Group
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
        friendAgentTexture = FriendPawn.getTexture()
        foeAgentTexture = FoePawn.getTexture()

        blockGroup = Group()
        pawnGroup = Group()
        bulletGroup = Group()

        pawnFriendGroup = Group()
        pawnFoeGroup = Group()

        stage = Stage(viewport).also {
            it.addActor(blockGroup)
            pawnGroup.addActor(pawnFoeGroup)
            pawnGroup.addActor(pawnFriendGroup)
            it.addActor(pawnGroup)
            it.addActor(bulletGroup)
        }

    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update((width), (height), true)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT
                or if (Gdx.graphics.bufferFormat.coverageSampling) GL20.GL_COVERAGE_BUFFER_BIT_NV else 0)

        stage.draw()
        stage.act()

        AI.act()
        EnemyAI.act()

        colisionChecker.doChecks()
    }

    override fun dispose() {
        blockTexture.dispose()
    }
}