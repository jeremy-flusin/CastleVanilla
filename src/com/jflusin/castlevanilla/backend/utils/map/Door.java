package com.jflusin.castlevanilla.backend.utils.map;

public class Door extends Frame {

	private boolean open = true;
	
	public Door(float x, float y) {
		super(x, y);
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	@Override
	public boolean isWalkable() {
		return open;
	}
	
}
