package com.jflusin.castlevanilla.backend.controllers;

import com.jflusin.castlevanilla.backend.models.HumanPlayer;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;

public class HumanController extends PlayerController {

	public HumanController(HumanEntity entity, HumanPlayer model) {
		super(entity, model);
	}
	
	@Override
	public HumanEntity getEntity() {
		return (HumanEntity)super.getEntity();
	}

	@Override
	public HumanPlayer getModel() {
		return (HumanPlayer)super.getModel();
	}
}
