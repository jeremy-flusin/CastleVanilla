package com.jflusin.castlevanilla.backend.controllers;

import com.jflusin.castlevanilla.backend.models.AbstractModel;
import com.jflusin.castlevanilla.backend.models.VampirePlayer;
import com.jflusin.castlevanilla.backend.views.entities.AbstractEntity;
import com.jflusin.castlevanilla.backend.views.entities.VampireEntity;

public class VampireController extends AbstractController {

	public VampireController(AbstractEntity entity, AbstractModel model) {
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

	@Override
	public void handleInput() {
		
	}
	
}
