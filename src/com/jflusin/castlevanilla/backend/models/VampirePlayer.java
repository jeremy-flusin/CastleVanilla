package com.jflusin.castlevanilla.backend.models;

import com.jflusin.castlevanilla.backend.controllers.VampireController;

public class VampirePlayer extends AbstractModel {
	
	@Override
	public VampireController getController() {
		return (VampireController)super.getController();
	}
	
}
