package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.*

class MovingObject(private val radius: Float, private val color1: Color, private val color2: Color, x: Float = 0.0f, y: Float = 0.0f, private var vx: Float = 0.0f, private var vy: Float = 0.0f) {
    var x = x
        private set
    var y = y
        private set
    var rotation = 0.0f
        set(value) {
            var angle = value
            while (angle > 360f) angle -= 360f
            while (angle < 0f) angle += 360f
            field = angle
        }
    var constantSpeed: Float? = null
    var stopped = false
    var resistanceOn = true
    var gravityOn = true

    companion object {
        const val gravity = 0.03f
        const val flexibility = 0.2f   //strata prędkości od odbicia się od ściany (procentowa 1 - 100%    0.5 - 50%)
        const val resistance = 0.01f
        const val speed = 0.1f
    }

    fun move(dt: Float) {
        if (constantSpeed != null) {
            vx = sin(rotation * PI / 180f) * constantSpeed!!
            vy = cos(rotation * PI / 180f) * constantSpeed!!
        }

        x += vx * dt
        y += vy * dt //- 0.5f * gravity * dt * dt

        if (!stopped && constantSpeed == null) {
            if (resistanceOn) {
                vx *= (1 - resistance)
                vy *= (1 - resistance)
            }
            if (gravityOn) vy -= gravity * dt
        }

        if (x - radius < 0 || x + radius > Gdx.graphics.width) {
            x = if (x - radius < 0) radius
            else Gdx.graphics.width - radius
            vx *= (-1 + flexibility)
        }
        if (y - radius < 0 || y + radius > Gdx.graphics.height) {
            y = if (y - radius < 0) radius
            else Gdx.graphics.height - radius
            vy *= (-1 + flexibility)
            vx *= (1 - flexibility)
        }
    }

    fun increaseSpeed() {
        if (stopped) return
        vx += sin(rotation * PI / 180f) * speed
        vy += cos(rotation * PI / 180f) * speed
    }

    fun decreaseSpeed() {
        if (stopped) return
        vx -= sin(rotation * PI / 180f) * speed
        vy -= cos(rotation * PI / 180f) * speed
    }

    fun stop() {
        vx = 0f
        vy = 0f
        stopped = true
    }

    fun render(renderer: ShapeRenderer) {
        with(renderer) {
            set(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(color1)
            translate(x, y, 0f)
            rotate(0f, 0f, -1f, rotation)
            circle(0f, 0f, radius)
            setColor(color2)
            triangle(-radius, 0f, radius, 0f, 0f, radius)
            rotate(0f, 0f, 1f, rotation)
            translate(-x, -y, 0f)
        }
    }
}