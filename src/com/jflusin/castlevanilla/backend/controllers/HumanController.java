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
	public HumanEntity getEntity() {
		return (HumanEntity)super.getEntity();
	}

	@Override
	public HumanPlayer getModel() {
		return (HumanPlayer)super.getModel();
	}

	@Override
	public void handleInput() {
//		float x = getEntity().getBody().getPosition().x;
//		float y = getEntity().getBody().getPosition().y;
//		
//		if(InputHandler.isDown(InputHandler.UP)){
//			getEntity().getBody().setTransform(x, y + 0.04f, 0);
//			getEntity().setDirection(HumanEntity.LOOKS_UP);
//		}
//		if(InputHandler.isDown(InputHandler.DOWN)){
//			getEntity().getBody().setTransform(x, y - 0.04f, 0);
//			getEntity();
//			getEntity().setDirection(HumanEntity.LOOKS_DOWN);
//		}
//		if(InputHandler.isDown(InputHandler.LEFT)){
//			getEntity().getBody().setTransform(x - 0.04f, y, 0);
//			getEntity().setDirection(HumanEntity.LOOKS_LEFT);
//		}
//		if(InputHandler.isDown(InputHandler.RIGHT)){
//			getEntity().getBody().setTransform(x + 0.04f, y, 0);
//			getEntity().setDirection(HumanEntity.LOOKS_RIGHT);
//		}
	}
}
