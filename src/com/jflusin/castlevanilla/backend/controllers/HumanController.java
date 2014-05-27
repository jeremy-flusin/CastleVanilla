package com.jflusin.castlevanilla.backend.controllers;

import com.jflusin.castlevanilla.backend.models.AbstractModel;
import com.jflusin.castlevanilla.backend.models.HumanPlayer;
import com.jflusin.castlevanilla.backend.views.entities.AbstractEntity;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;

public class HumanController extends AbstractController {

	public HumanController(AbstractEntity entity, AbstractModel model) {
		super(entity, model);
	}
	
	@Override
	public AbstractEntity getEntity() {
		return (HumanEntity)super.getEntity();
	}

	@Override
	public AbstractModel getModel() {
		return (HumanPlayer)super.getModel();
	}
	
}
