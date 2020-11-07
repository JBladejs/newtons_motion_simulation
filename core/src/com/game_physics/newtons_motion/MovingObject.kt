package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class MovingObject(private val radius: Float, private val color: Color, x: Float = 0.0f, y: Float = 0.0f, var vx: Float = 0.0f, var vy: Float = 0.0f) {
    var x = x
    private set
    var y = y
    private set

    companion object {
        const val gravity = 0.03f
        const val flexibility = 0.2f   //strata prędkości od odbicia się od ściany (procentowa 1 - 100%    0.5 - 50%)
        const val resistance = 0.01f
    }

    fun move(dt: Float) {
        println(vy)
        x += vx * dt
        y += vy * dt - 0.5f * gravity * dt * dt

        //opór powietrza
        vx *= (1-resistance)
        vy *= (1-resistance)
        //grawitacja
        vy -= gravity * dt

        if (x - radius < 0 || x + radius > Gdx.graphics.width){
            x = if (x - radius < 0) radius
            else Gdx.graphics.width - radius
            vx *= (-1+flexibility)
        }
        if (y - radius < 0 || y + radius > Gdx.graphics.height){
            y = if (y - radius < 0) radius
            else Gdx.graphics.height - radius
            vy *= (-1+flexibility)
            vx *= (1-flexibility)
        }
    }

    fun render(renderer: ShapeRenderer) {
        renderer.setColor(color)
        with(renderer) {
            begin()
            set(ShapeRenderer.ShapeType.Filled)
            circle(x, y, radius)
            end()
        }
    }
}