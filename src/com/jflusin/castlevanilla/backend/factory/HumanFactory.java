package com.jflusin.castlevanilla.backend.factory;

import com.jflusin.castlevanilla.backend.controllers.HumanController;
import com.jflusin.castlevanilla.backend.models.HumanPlayer;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public abstract class HumanFactory {

	public static HumanController getInstance(AbstractScene scene){
		HumanEntity entity = new HumanEntity(scene);
		HumanPlayer model = new HumanPlayer();
		return new HumanController(entity, model);
	}
	
}
