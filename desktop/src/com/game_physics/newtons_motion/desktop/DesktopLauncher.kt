package com.game_physics.newtons_motion.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.game_physics.newtons_motion.NewtonGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        with(config) {
            title = "Newton's Law of Motion"
            width = 640
            height = 480
            resizable = false
            LwjglApplication(NewtonGame(), this)
        }
    }
}