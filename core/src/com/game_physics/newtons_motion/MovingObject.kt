package com.game_physics.newtons_motion

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import javax.swing.DesktopManager

val grawitacja = 0.03f
val tarcie = 0.2f   //strata prędkości od odbicia się od ściany (procentowa 1 - 100%    0.5 - 50%)
val opor = 0.01f    //opor powietrza zmniejszajacy stale predkosc pilki (procentowa)

class MovingObject(private val radius: Float, private val color: Color, private var x: Float = 0.0f, private var y: Float = 0.0f, private var vx: Float = 3.0f, private var vy: Float = 3.0f) {
    fun move(dt: Float) {

        x += vx*dt
        y += vy*dt

        //opór powietrza
        vx *= (1-opor)
        vy *= (1-opor)
        //grawitacja
        vy -= grawitacja*dt

        //TODO wyciągnąć aktualne wartości ekranu żeby nie wpisywać ograniczeń na sztywno
        if(x < 0 || x > 640){
            vx *= (-1+tarcie)
        }
        if(y < 0 || y > 480){
            vy *= (-1+tarcie)
            vx *= (1-tarcie)
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