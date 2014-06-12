package com.jflusin.castlevanilla.backend.models;

import com.jflusin.castlevanilla.backend.controllers.VampireController;

public class VampirePlayer extends PlayerModel {
	
	public VampirePlayer(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public VampireController getController() {
		return (VampireController)super.getController();
	}
	
}
