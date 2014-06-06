package com.jflusin.castlevanilla.backend.utils.map;


public class Frame {

	private float x = 0;
	private float y = 0;
	private boolean walkable = true;
	private float width = 0;
	private float height = 0;

	public Frame(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Frame(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Frame(float x, float y, boolean walkable) {
		this.x = x;
		this.y = y;
		this.walkable = walkable;
	}
	
	public Frame(float x, float y, float width, float height, boolean walkable){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.walkable = walkable;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
}
