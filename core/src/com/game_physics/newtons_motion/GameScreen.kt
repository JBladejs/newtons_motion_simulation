package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.MathUtils.*
import kotlin.system.exitProcess


class GameScreen(private val game: NewtonGame) : Screen {
    private var dt = 2f
    private val playerObject = MovingObject(15.0f, Color(255, 0, 0), Color(0, 255, 0), 50f, 50f)
    private var followingObject : MovingObject? = followingObjectInit()
    private var target : TargetZone? = targetInit()

    private fun followingObjectInit(): MovingObject = MovingObject(10.0f, Color(0, 0, 255), Color(0, 255, 0), RNG.nextX(5f), RNG.nextY(5f), constantSpeed = 1f)
    private fun targetInit(): TargetZone = TargetZone(Color(255, 0, 0), RNG.nextX(25f), RNG.nextY(25f), 50f, 50f)

    private fun update() {
        playerObject.move(dt)
        followingObject?.move(dt)

        if (target != null && target!!.contains(playerObject.x, playerObject.y)) {
            target!!.color = Color(0, 255, 0)
            playerObject.stop()
        }

        if (followingObject != null) followingObject!!.rotation = 180f * atan2(playerObject.x - followingObject!!.x, playerObject.y - followingObject!!.y) / PI

        if (Gdx.input.isKeyPressed(W)) playerObject.increaseSpeed()
        if (Gdx.input.isKeyPressed(S)) playerObject.decreaseSpeed()
        if (Gdx.input.isKeyPressed(A)) playerObject.rotateLeft()
        if (Gdx.input.isKeyPressed(D)) playerObject.rotateRight()

        if (Gdx.input.isKeyJustPressed(NUMPAD_0)) playerObject.gravityOn = !playerObject.gravityOn
        if (Gdx.input.isKeyJustPressed(NUMPAD_1)) target = if (target != null) null else targetInit()
        if (Gdx.input.isKeyJustPressed(NUMPAD_2)) playerObject.resistanceOn = !playerObject.resistanceOn
        if (Gdx.input.isKeyJustPressed(NUMPAD_3)) followingObject = if (followingObject != null) null else followingObjectInit()
        if (Gdx.input.isKeyJustPressed(NUMPAD_4)) playerObject.asymetrical = !playerObject.asymetrical

        if (Gdx.input.isKeyJustPressed(ESCAPE))
            exitProcess(1)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(game.renderer) {
            begin()
            target?.render(this)
            followingObject?.render(this)
            playerObject.render(this)
            end()
        }
        update()
    }

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {}

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun show() {}
}