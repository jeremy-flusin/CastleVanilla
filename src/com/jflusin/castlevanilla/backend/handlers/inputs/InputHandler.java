package com.jflusin.castlevanilla.backend.handlers.inputs;

public class InputHandler {

	public static boolean[] keys;
	public static boolean[] prevkeys;
	
	public static final int NUM_KEYS = 10;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	static {
		keys = new boolean[NUM_KEYS];
		prevkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevkeys[i] = keys[i];
		}
	}

	public static boolean isDown(int i) {
		return keys[i];
	}
	
	public static boolean isPressed(int i) {
		return keys[i] && !prevkeys[i];
	}
	
	public static void setKey(int k, boolean value) {
		keys[k] = value;
	}
}
