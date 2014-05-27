package com.jflusin.castlevanilla.backend.models;

import com.jflusin.castlevanilla.backend.controllers.AbstractController;

public abstract class AbstractModel {

	private AbstractController controller;
	
	public void setController(AbstractController controller){
		this.controller = controller;
	}
	
	public AbstractController getController() {
		return controller;
	}
	
}
