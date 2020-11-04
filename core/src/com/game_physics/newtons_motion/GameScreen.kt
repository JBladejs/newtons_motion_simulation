package com.game_physics.newtons_motion

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20

class GameScreen(private val game: NewtonGame) : Screen {
    private var dt = 2f   //Prędkość gry
    private val movingObject = MovingObject(10.0f, Color(255, 0, 0), 50f, 50f)

    private fun update() {
        movingObject.move(dt)

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        movingObject.render(game.renderer)
        update()
    }

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {}

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun show() {}
}