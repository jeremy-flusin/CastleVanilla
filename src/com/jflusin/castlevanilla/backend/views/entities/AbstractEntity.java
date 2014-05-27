package com.jflusin.castlevanilla.backend.views.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.castlevanilla.backend.controllers.AbstractController;

public abstract class AbstractEntity {
	
	private AbstractController controller;
	
	public void setController(AbstractController controller){
		this.controller = controller;
	}
	
	public AbstractController getController() {
		return controller;
	}
	
	public abstract void create(World world);
}
