package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

const val gravity = 0.03f
const val flexibility = 0.2f   //strata prędkości od odbicia się od ściany (procentowa 1 - 100%    0.5 - 50%)
const val resistance = 0.01f    //opor powietrza zmniejszajacy stale predkosc pilki (procentowa)

class MovingObject(private val radius: Float, private val color: Color, private var x: Float = 0.0f, private var y: Float = 0.0f, var vx: Float = 0.0f, var vy: Float = 0.0f) {
    fun move(dt: Float) {
        println(vy)
        x += vx*dt
        y += vy*dt

        //opór powietrza
        vx *= (1-resistance)
        vy *= (1-resistance)
        //grawitacja
        vy -= gravity*dt

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

    fun getX(): Float{
        return x
    }
    fun getY(): Float{
        return y
    }
}