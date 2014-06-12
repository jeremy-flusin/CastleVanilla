package com.jflusin.castlevanilla.backend.utils.map;

import java.util.ArrayList;

public abstract class GameMap {

	protected ArrayList<Room> rooms;
	
	public GameMap(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	
	public Frame getDoor(Room from, Room to){
		for(Frame destDoor: to.getDoors()){
			for(Frame localDoor: from.getDoors()){
				if(destDoor.getX() == localDoor.getX() &&
						destDoor.getY() == localDoor.getY()){
					return localDoor;
				}
			}
		}
		return null;
	}
	
}
