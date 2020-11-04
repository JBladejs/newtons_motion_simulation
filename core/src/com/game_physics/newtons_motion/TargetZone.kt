package com.game_physics.newtons_motion

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import javax.swing.DesktopManager

class TargetZone( private var color: Color, var x: Float = 0.0f, var y: Float = 0.0f, var width: Float = 5.0f, var height: Float = 5.0f) {

    fun changeColor(color: Color){
        this.color = color
    }

    fun render(renderer: ShapeRenderer) {
        renderer.setColor(color)
        with(renderer) {
            begin()
            set(ShapeRenderer.ShapeType.Line)
            rect(x,y,width,height)
            end()
        }
    }
}