package com.jflusin.castlevanilla.backend.views.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.castlevanilla.backend.controllers.AbstractController;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public abstract class AbstractEntity {
	
	private AbstractController controller;
	private AbstractScene scene;
	
	public AbstractEntity(AbstractScene scene) {
		this.scene = scene;
	}
	
	public void setController(AbstractController controller){
		this.controller = controller;
	}
	
	public AbstractScene getScene(){
		return scene;
	}
	
	public AbstractController getController() {
		return controller;
	}

	public abstract void createInto(World world);
	
	public abstract void update(float dt);

	public abstract void render(SpriteBatch sb);


}
