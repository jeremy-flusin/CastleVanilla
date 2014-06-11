package com.jflusin.castlevanilla.backend.utils.map;

import java.util.ArrayList;

public class Room {

	private String name;
	private ArrayList<Frame> groundFrames;
	private ArrayList<Frame> doors;
	private ArrayList<Frame> spawns;

	public Room(String name, ArrayList<Frame> groundFrames) {
		this.name = name;
		this.groundFrames = groundFrames;
		doors = new ArrayList<Frame>();
		spawns = new ArrayList<Frame>();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Frame> getGroundFrames() {
		return groundFrames;
	}
	
	public ArrayList<Frame> getDoors() {
		return doors;
	}
	
	public ArrayList<Frame> getSpawns() {
		return spawns;
	}
	
	public void addDoor(Frame door){
		doors.add(door);
	}
	
	public void addSpawn(Frame spawn){
		spawns.add(spawn);
	}
	
	public Frame getDoorToRoom(Room destination){
		for(Frame destDoor: destination.getDoors()){
			for(Frame localDoor: getDoors()){
				if(destDoor.getX() == localDoor.getX() &&
						destDoor.getY() == localDoor.getY()){
					return localDoor;
				}
			}
		}
		return null;
	}
}
