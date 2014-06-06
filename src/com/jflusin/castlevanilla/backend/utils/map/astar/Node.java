package com.jflusin.castlevanilla.backend.utils.map.astar;

import com.jflusin.castlevanilla.backend.utils.map.Frame;

public class Node extends Frame {

	private int g;
	private int h;
	Node parent;
	
	public Node(float x, float y, boolean walkable) {
		super(x, y, walkable);
	}

	public void setG(int g) {
		this.g = g;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public int getG() {
		return g;
	}
	
	public int getH() {
		return h;
	}
	
	public int getF() {
		return g + h;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getParent() {
		return parent;
	}
}
