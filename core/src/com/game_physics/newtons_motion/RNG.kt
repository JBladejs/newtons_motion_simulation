package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import kotlin.random.Random

object RNG {
    fun nextX() : Float = Random.nextFloat() * Gdx.graphics.width
    fun nextY() : Float = Random.nextFloat() * Gdx.graphics.height
}