package com.jflusin.castlevanilla.backend.utils.map;

import java.util.ArrayList;

public class Room {

	private String name;
	private ArrayList<Frame> groundFrames;
	private ArrayList<Door> doors;
	private ArrayList<Spawn> spawns;

	public Room(String name, ArrayList<Frame> groundFrames) {
		this.name = name;
		this.groundFrames = groundFrames;
		doors = new ArrayList<Door>();
		spawns = new ArrayList<Spawn>();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Frame> getGroundFrames() {
		return groundFrames;
	}
	
	public ArrayList<Door> getDoors() {
		return doors;
	}
	
	public ArrayList<Spawn> getSpawns() {
		return spawns;
	}
	
	public void addDoor(Door door){
		doors.add(door);
	}
	
	public void addSpawn(Spawn spawn){
		spawns.add(spawn);
	}
}
