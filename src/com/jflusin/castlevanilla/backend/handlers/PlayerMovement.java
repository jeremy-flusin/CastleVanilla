package com.jflusin.castlevanilla.backend.handlers;

import java.util.ArrayList;

import com.jflusin.castlevanilla.backend.utils.map.Frame;

public class PlayerMovement {

	private ArrayList<Frame> path;
	private int step = 0;
	
	public PlayerMovement(ArrayList<Frame> path) {
		this.path = path;
	}
	
	public void setPath(ArrayList<Frame> path) {
		this.path = path;
	}
	
	public ArrayList<Frame> getPath() {
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
		return step >= path.size();
	}
}
