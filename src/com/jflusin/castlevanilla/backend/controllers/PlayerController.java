package com.jflusin.castlevanilla.backend.controllers;

import java.util.ArrayList;

import com.jflusin.castlevanilla.backend.handlers.PlayerMovement;
import com.jflusin.castlevanilla.backend.handlers.inputs.InputHandler;
import com.jflusin.castlevanilla.backend.models.PlayerModel;
import com.jflusin.castlevanilla.backend.rules.PlayerTurnActions;
import com.jflusin.castlevanilla.backend.utils.B2DVars;
import com.jflusin.castlevanilla.backend.utils.map.Door;
import com.jflusin.castlevanilla.backend.utils.map.Frame;
import com.jflusin.castlevanilla.backend.utils.map.Room;
import com.jflusin.castlevanilla.backend.utils.map.astar.PathFinder;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;
import com.jflusin.castlevanilla.backend.views.entities.PlayerEntity;

public abstract class PlayerController extends AbstractController{
	
	protected float velocity = 0.1f * B2DVars.FRAMEWIDTH;
	protected PlayerMovement movement;
	
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
	
	public void setMovement(PlayerMovement movement) {
		this.movement = movement;
	}
	
	public PlayerMovement getMovement() {
		return movement;
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
			doesAction(PlayerTurnActions.MOVE);
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
	
	@Override
	public void handleInput() {
		clickToMove();
	}

	private void clickToMove() {
		if(InputHandler.isClicked(0)){
			float x = InputHandler.mouseX;
			float y = InputHandler.mouseY;
			Frame clickedFrame = getEntity().getScene().getMap().getFrameAtCoor(x, y);
			Room clickedRoom = getEntity().getScene().getMap().getRoomAtCoor(x, y);
			Room currentRoom = getEntity().getScene().getMap().getRoomAtCoor(
					getEntity().getBody().getPosition().x,
					getEntity().getBody().getPosition().y);
			Frame currentFrame = getEntity().getCurrentFrame();
			if(clickedFrame != null && clickedRoom != null && currentRoom != null && 
					currentFrame != null){
				Door doorBetween = getEntity().getScene().getMap().getDoor(currentRoom, clickedRoom);
				if(doorBetween != null){
					ArrayList<Frame> path = new ArrayList<Frame>();
					//From current point to Door
					PathFinder pf = new PathFinder(currentRoom.getGroundFrames(), 
							currentFrame, doorBetween);
					path.addAll(pf.findPath());
					//From Door to dest point
					pf = new PathFinder(clickedRoom.getGroundFrames(), 
							doorBetween, clickedFrame);					
					path.addAll(pf.findPath());
					PlayerMovement movement = new PlayerMovement(path);
					setMovement(movement);
					getEntity().setMovement(movement);
				}
			}
		}
	}
}
