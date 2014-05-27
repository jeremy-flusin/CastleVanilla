package com.jflusin.castlevanilla.backend.models;

import com.jflusin.castlevanilla.backend.controllers.AbstractController;
import com.jflusin.castlevanilla.backend.controllers.HumanController;

public class HumanPlayer extends AbstractModel {
	
	@Override
	public AbstractController getController() {
		return (HumanController)super.getController();
	}
	
}
