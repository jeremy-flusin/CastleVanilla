package com.jflusin.castlevanilla.backend.handlers;

import java.util.ArrayList;

import com.jflusin.castlevanilla.backend.utils.map.Frame;
import com.jflusin.castlevanilla.backend.utils.map.astar.Node;

public class PlayerMovement {

	private boolean isMoving = false;
	private ArrayList<Node> path;
	private int step = 0;
	
	public PlayerMovement(boolean isMoving, ArrayList<Node> path) {
		this.isMoving = isMoving;
		this.path = path;
	}
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public boolean isMoving() {
		return isMoving;
	}

	public void setPath(ArrayList<Node> path) {
		this.path = path;
	}
	
	public ArrayList<Node> getPath() {
		return path;
	}
	
	public void step(){
		step++;
	}

	public Frame getCurrentDest() {
		if(step < path.size()){
			return path.get(path.size() - 1 - step);
		}else{
			return null;
		}
	}

	public boolean isArrived() {
		return step >= path.size() - 1;
	}
}
