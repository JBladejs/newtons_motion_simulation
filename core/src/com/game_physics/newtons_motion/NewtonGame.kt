package com.game_physics.newtons_motion

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class NewtonGame : Game() {
    lateinit var renderer: ShapeRenderer

    override fun create() {
        renderer = ShapeRenderer()
        renderer.setAutoShapeType(true)
        screen = GameScreen(this)
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        screen.dispose()
        renderer.dispose()
    }
}