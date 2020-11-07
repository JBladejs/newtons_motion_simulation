package com.game_physics.newtons_motion

import com.badlogic.gdx.Gdx
import kotlin.random.Random

object RNG {
    fun nextX(offset: Float = 0f): Float = offset + Random.nextFloat() * (Gdx.graphics.width - (offset * 2f))
    fun nextY(offset: Float = 0f): Float = offset + Random.nextFloat() * (Gdx.graphics.height - (offset * 2f))
}