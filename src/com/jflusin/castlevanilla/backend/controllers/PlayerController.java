package com.jflusin.castlevanilla.backend.controllers;

import com.jflusin.castlevanilla.backend.models.PlayerModel;
import com.jflusin.castlevanilla.backend.utils.B2DVars;
import com.jflusin.castlevanilla.backend.utils.map.Frame;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;
import com.jflusin.castlevanilla.backend.views.entities.PlayerEntity;

public abstract class PlayerController extends AbstractController{
	
	protected float velocity = 0.1f * B2DVars.FRAMEWIDTH;
	
	public PlayerController(PlayerEntity entity, PlayerModel model) {
		super(entity, model);
		getModel().setStamina(getModel().getMaxStamina());
	}

	public void initTurn(){
		getModel().setStamina(getModel().getMaxStamina());
	}
	
	public void doesAction(int cost){
		getModel().setStamina(getModel().getStamina() - cost);
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
				xTransform = velocity;
				getEntity().setDirection(HumanEntity.LOOKS_RIGHT);
				arrived = false;
			} else if (destX < x - 0.01f){
				xTransform = -velocity;
				getEntity().setDirection(HumanEntity.LOOKS_LEFT);
				arrived = false;
			}	
			if (destY > y + 0.01f){
				yTransform = velocity;
				getEntity().setDirection(HumanEntity.LOOKS_UP);
				arrived = false;
			} else if (destY < y - 0.01f){
				yTransform = -velocity;
				getEntity().setDirection(HumanEntity.LOOKS_DOWN);
				arrived = false;
			}
			getEntity().getBody().setTransform(x + xTransform, y + yTransform, 0);
		}
		return arrived;
	}
	
	@Override
	public PlayerEntity getEntity() {
		return (PlayerEntity)super.getEntity();
	}
	
	@Override
	public PlayerModel getModel() {
		return (PlayerModel)super.getModel();
	}
}
