package com.jflusin.castlevanilla.backend.controllers;

import com.jflusin.castlevanilla.backend.models.VampirePlayer;
import com.jflusin.castlevanilla.backend.views.entities.VampireEntity;

public class VampireController extends PlayerController {

	public VampireController(VampireEntity entity, VampirePlayer model) {
		super(entity, model);
	}
	
	@Override
	public VampireEntity getEntity() {
		return (VampireEntity)super.getEntity();
	}

	@Override
	public VampirePlayer getModel() {
		return (VampirePlayer)super.getModel();
	}
}
