package com.jflusin.castlevanilla.backend.controllers;

import com.jflusin.castlevanilla.backend.handlers.inputs.InputHandler;
import com.jflusin.castlevanilla.backend.models.AbstractModel;
import com.jflusin.castlevanilla.backend.models.HumanPlayer;
import com.jflusin.castlevanilla.backend.utils.map.Frame;
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
		float x = getEntity().getBody().getPosition().x;
		float y = getEntity().getBody().getPosition().y;
		
		if(InputHandler.isDown(InputHandler.UP)){
			getEntity().getBody().setTransform(x, y + 0.04f, 0);
			getEntity().setDirection(HumanEntity.LOOKS_UP);
		}
		if(InputHandler.isDown(InputHandler.DOWN)){
			getEntity().getBody().setTransform(x, y - 0.04f, 0);
			getEntity();
			getEntity().setDirection(HumanEntity.LOOKS_DOWN);
		}
		if(InputHandler.isDown(InputHandler.LEFT)){
			getEntity().getBody().setTransform(x - 0.04f, y, 0);
			getEntity().setDirection(HumanEntity.LOOKS_LEFT);
		}
		if(InputHandler.isDown(InputHandler.RIGHT)){
			getEntity().getBody().setTransform(x + 0.04f, y, 0);
			getEntity().setDirection(HumanEntity.LOOKS_RIGHT);
		}
	}

	/**
	 * Returns true if the movement is done.
	 * @param towards
	 * @return
	 */
	public boolean moveTowards(Frame towards) {
		
		boolean arrived = true;
		if(towards != null){
			float x = getEntity().getBody().getPosition().x;
			float y = getEntity().getBody().getPosition().y;
			float destX = towards.getX();
			float destY = towards.getY();
			float xTransform = 0f;
			float yTransform = 0f;
			if(destX > x + 0.01f){
				xTransform = 0.04f;
				getEntity().setDirection(HumanEntity.LOOKS_RIGHT);
				arrived = false;
			} else if (destX < x - 0.01f){
				xTransform = -0.04f;
				getEntity().setDirection(HumanEntity.LOOKS_LEFT);
				arrived = false;
			} else if (destY > y + 0.01f){
				yTransform = 0.04f;
				getEntity().setDirection(HumanEntity.LOOKS_UP);
				arrived = false;
			} else if (destY < y - 0.01f){
				yTransform = -0.04f;
				getEntity().setDirection(HumanEntity.LOOKS_LEFT);
				arrived = false;
			}
			getEntity().getBody().setTransform(x + xTransform, y + yTransform, 0);
		}
		return arrived;
	}
}
