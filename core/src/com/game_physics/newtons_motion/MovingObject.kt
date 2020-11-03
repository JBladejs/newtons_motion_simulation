package com.game_physics.newtons_motion

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class MovingObject(private val radius: Float, private val color: Color, private var x: Float = 0.0f, private var y: Float = 0.0f, private var vx: Float = 1.0f, private var vy: Float = 1.0f) {
    fun move(dt: Float) {
        x += vx*dt
        y += vy*dt
    }

    fun render(renderer: ShapeRenderer) {
        renderer.setColor(color)
    }
}