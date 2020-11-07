package com.game_physics.newtons_motion

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import javax.swing.DesktopManager

class TargetZone(var color: Color, var x: Float = 0.0f, var y: Float = 0.0f, var width: Float = 5.0f, var height: Float = 5.0f) {
    private val rectangle = Rectangle(x, y, width, height)

    fun contains(x: Float, y: Float) : Boolean = rectangle.contains(x, y)

    fun render(renderer: ShapeRenderer) {
        renderer.setColor(color)
        with(renderer) {
            set(ShapeRenderer.ShapeType.Line)
            rect(x,y,width,height)
        }
    }
}