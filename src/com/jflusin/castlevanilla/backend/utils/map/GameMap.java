package com.jflusin.castlevanilla.backend.utils.map;

import java.util.ArrayList;

import com.jflusin.castlevanilla.backend.utils.B2DVars;

public class GameMap {

	protected ArrayList<Room> rooms;
	
	public GameMap(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public Door getDoor(Room from, Room to){
		for(Door destDoor: to.getDoors()){
			for(Door localDoor: from.getDoors()){
				if(destDoor.getX() == localDoor.getX() &&
						destDoor.getY() == localDoor.getY()){
					return localDoor;
				}
			}
		}
		return null;
	}
	
	public Frame getFrameAtCoor(float x, float y){
		for (Room room : rooms) {
			for (Frame frame : room.getGroundFrames()) {
				float frameX = frame.getX();
				float frameY = frame.getY();
				if(frameX - B2DVars.PRECISION <= x  && x < frameX + B2DVars.FRAMEWIDTH + B2DVars.PRECISION
						&& frameY - B2DVars.PRECISION <= y  && y < frameY + B2DVars.FRAMEWIDTH + B2DVars.PRECISION){
					return frame;
				}
			}
		}
		return null;
	}
	
	public Room getRoomAtCoor(float x, float y){
		Frame currentFrame = getFrameAtCoor(x, y);
		if(currentFrame != null){
			float currentX = currentFrame.getX();
			float currentY = currentFrame.getY();
			for (Room room : rooms) {
				for (Frame frame : room.getGroundFrames()) {
					float frameX = frame.getX();
					float frameY = frame.getY();
					if(currentX >= frameX - B2DVars.PRECISION &&
							currentX <= frameX + B2DVars.PRECISION &&
							currentY >= frameY - B2DVars.PRECISION &&
							currentY <= frameY + B2DVars.PRECISION){
						return room;
					}
				}
			}
		}
		return null;
	}
}
