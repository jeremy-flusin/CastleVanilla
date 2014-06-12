package com.jflusin.castlevanilla.backend.factory;

import com.jflusin.castlevanilla.backend.controllers.VampireController;
import com.jflusin.castlevanilla.backend.models.VampirePlayer;
import com.jflusin.castlevanilla.backend.views.entities.VampireEntity;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public abstract class VampireFactory {

	public static VampireController getInstance(AbstractScene scene, String nickname){
		VampireEntity entity = new VampireEntity(scene);
		VampirePlayer model = new VampirePlayer(nickname);
		return new VampireController(entity, model);
	}
	
}
