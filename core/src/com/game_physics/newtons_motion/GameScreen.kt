package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.MathUtils.*
import kotlin.system.exitProcess


class GameScreen(private val game: NewtonGame) : Screen {
    private var dt = 2f   //Prędkość gry
    private val playerObject = MovingObject(15.0f, Color(255, 0, 0), Color(0, 255, 0), 50f, 50f)
    private val followingObject = MovingObject(10.0f, Color(0, 0, 255), Color(0, 255, 0), 150f, 150f)
    private val target = TargetZone(Color(255, 0, 0), RNG.nextX(25f), RNG.nextY(25f), 50f, 50f)
    private val rotationSpeed = 2.5f


    //Boolean do wyłączania obiektów w grze
    private var playerObjectHide: Boolean = false
    private var followingObjectHide: Boolean = false
    private var targetHide: Boolean = false


    init {
        followingObject.constantSpeed = 1f
    }

    private fun update() {

        playerObject.stopped = playerObjectHide
        followingObject.stopped = followingObjectHide
        playerObject.move(dt)
        followingObject.move(dt)

        //wykrywanie kolizji środka pilki z polem docelowym
        if (target.contains(playerObject.x, playerObject.y) && !targetHide) {
            target.color = Color(0, 255, 0)
            playerObject.stop()
        }
        followingObject.rotation = 180f * atan2(playerObject.x - followingObject.x, playerObject.y - followingObject.y) / PI
        //println(followingObject.rotation)

        if (Gdx.input.isKeyPressed(W)) playerObject.increaseSpeed()
        if (Gdx.input.isKeyPressed(S)) playerObject.decreaseSpeed()
        if (Gdx.input.isKeyPressed(A)) playerObject.rotation -= rotationSpeed
        if (Gdx.input.isKeyPressed(D)) playerObject.rotation += rotationSpeed

        if (Gdx.input.isKeyJustPressed(NUMPAD_1))
        {
            playerObjectHide = !playerObjectHide
            playerObject.stopped =playerObjectHide
            playerObject.airRes = !playerObject.airRes
            //playerObject.stop()
        }

        if (Gdx.input.isKeyJustPressed(NUMPAD_2))
        {
            followingObjectHide = !followingObjectHide
            followingObject.stop()
        }

        if (Gdx.input.isKeyJustPressed(NUMPAD_3))
        {
            targetHide = !targetHide
        }


        if (Gdx.input.isKeyJustPressed(ESCAPE))
            exitProcess(1)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(game.renderer) {
            begin()
            if(!targetHide) target.render(this)
            if(!followingObjectHide)followingObject.render(this)
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