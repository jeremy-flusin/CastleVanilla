package com.jflusin.castlevanilla.backend.views.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jflusin.castlevanilla.backend.main.Game;
import com.jflusin.castlevanilla.backend.utils.ContentManager;
import com.jflusin.castlevanilla.backend.utils.SceneManager;

public abstract class AbstractScene {
	
	protected SceneManager sm;
	protected Game game;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected ContentManager cm;
	
	public AbstractScene(SceneManager sm) {
		this.sm = sm;
		game = sm.getGame();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		cm = new ContentManager();
		loadContent();
	}

	public abstract void loadContent();
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	
	public ContentManager getContent(){
		return cm;
	}
}
