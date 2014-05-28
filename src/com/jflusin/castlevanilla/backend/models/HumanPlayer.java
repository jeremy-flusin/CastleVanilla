package com.jflusin.castlevanilla.backend.models;

import com.jflusin.castlevanilla.backend.controllers.HumanController;

public class HumanPlayer extends AbstractModel {
	
	@Override
	public HumanController getController() {
		return (HumanController)super.getController();
	}
	
}
