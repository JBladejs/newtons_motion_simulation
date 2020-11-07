package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20

class GameScreen(private val game: NewtonGame) : Screen {
    private var dt = 2f   //Prędkość gry
    private val playerObject = MovingObject(10.0f, Color(255, 0, 0), Color(0, 255, 0), 50f, 50f)
    private val target = TargetZone(Color(255, 0, 0), RNG.nextX(25f), RNG.nextY(25f), 50f, 50f)
    private val rotationSpeed = 2.5f

    private fun update() {
        playerObject.move(dt)

        //wykrywanie kolizji pilki z polem docelowym
        if (target.contains(playerObject.x - playerObject.radius, playerObject.y + playerObject.radius) &&
                target.contains(playerObject.x + playerObject.radius, playerObject.y - playerObject.radius)) {
            target.color = Color(0, 255, 0)
            playerObject.stop()
        }

        if (Gdx.input.isKeyPressed(W)) playerObject.increaseSpeed()
        if (Gdx.input.isKeyPressed(S)) playerObject.decreaseSpeed()
        if (Gdx.input.isKeyPressed(A)) playerObject.rotation -= rotationSpeed
        if (Gdx.input.isKeyPressed(D)) playerObject.rotation += rotationSpeed
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(game.renderer) {
            begin()
            playerObject.render(this)
            target.render(this)
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