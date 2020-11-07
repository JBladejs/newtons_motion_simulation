package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20

class GameScreen(private val game: NewtonGame) : Screen {
    private var dt = 2f   //Prędkość gry
    private val movingObject = MovingObject(10.0f, Color(255, 0, 0), 50f, 50f)
    private val target = TargetZone(Color(255, 0, 0), RNG.nextX(50f), RNG.nextY(50f), 50f, 50f)
    private val speed = 0.1f

    private fun update() {
        movingObject.move(dt)

        //wykrywanie kolizji pilki z polem docelowym
        if (target.contains(movingObject.x - movingObject.radius, movingObject.y + movingObject.radius) &&
                target.contains(movingObject.x + movingObject.radius, movingObject.y - movingObject.radius)) {
            target.color = Color(0, 255, 0)
            movingObject.stop()
        }

        if (Gdx.input.isKeyPressed(W)) movingObject.vy += speed
        if (Gdx.input.isKeyPressed(S)) movingObject.vy -= speed
        if (Gdx.input.isKeyPressed(A)) movingObject.vx -= speed
        if (Gdx.input.isKeyPressed(D)) movingObject.vx += speed
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(game.renderer) {
            begin()
            movingObject.render(this)
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