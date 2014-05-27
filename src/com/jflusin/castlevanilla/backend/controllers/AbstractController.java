package com.jflusin.castlevanilla.backend.controllers;

import com.jflusin.castlevanilla.backend.models.AbstractModel;
import com.jflusin.castlevanilla.backend.views.entities.AbstractEntity;

public abstract class AbstractController {

	private AbstractEntity entity;
	private AbstractModel model;
	
	public AbstractController(AbstractEntity entity, AbstractModel model) {
		this.entity = entity;
		this.model = model;
		entity.setController(this);
		model.setController(this);
	}
	
	public AbstractEntity getEntity() {
		return entity;
	}
	
	public AbstractModel getModel() {
		return model;
	}
}
