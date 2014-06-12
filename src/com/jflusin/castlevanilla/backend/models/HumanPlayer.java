package com.jflusin.castlevanilla.backend.models;

import com.jflusin.castlevanilla.backend.controllers.HumanController;

public class HumanPlayer extends PlayerModel {

	public HumanPlayer(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public HumanController getController() {
		return (HumanController)super.getController();
	}
	
}
