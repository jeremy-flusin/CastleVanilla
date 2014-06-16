package com.jflusin.castlevanilla.backend.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jflusin.castlevanilla.backend.handlers.inputs.InputHandler;
import com.jflusin.castlevanilla.backend.handlers.inputs.InputProcessor;
import com.jflusin.castlevanilla.backend.utils.SceneManager;

public class Game implements ApplicationListener {

	public static final boolean IS_DEBUG = true;
	public static final String TITLE = "CastleVanilla";
	public static final String VERSION = "p-o-c";
	public static final int V_WIDTH = 1440;
	public static final int V_HEIGHT = 896;
	public static final int SCALE = 1;
	public static final float STEP = 1/60f;
	
	private float accum;
	
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	
	private SceneManager sm;
	
	@Override
	public void create() {
		
		//Main camera
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		//Initializations
		sb = new SpriteBatch();
		sm = new SceneManager(this);
		sm.setState(SceneManager.TEST_SCENE);
		Gdx.input.setInputProcessor(new InputProcessor());
		
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		while (accum >= STEP){
			accum -= STEP;
			sm.update(STEP);
			sm.render();
			InputHandler.update();
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
	
	public SpriteBatch getSpriteBatch() {
		return sb;
	}
	
	public OrthographicCamera getCamera() {
		return cam;
	}
}