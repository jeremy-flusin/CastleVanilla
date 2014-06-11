package com.jflusin.castlevanilla.backend.handlers.inputs;

import com.jflusin.castlevanilla.backend.main.Game;
import com.jflusin.castlevanilla.backend.utils.B2DVars;

public class InputHandler {

	public static boolean[] clicks;
	public static boolean[] prevClicks;
	public static float mouseX;
	public static float mouseY;
	
	public static boolean[] keys;
	public static boolean[] prevkeys;
	
	public static final int NUM_KEYS = 10;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	static {
		clicks = new boolean[2];
		prevClicks = new boolean[2];
		keys = new boolean[NUM_KEYS];
		prevkeys = new boolean[NUM_KEYS];
	}
	
	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevkeys[i] = keys[i];
		}
		for (int i = 0; i < 2; i++) {
			prevClicks[i] = clicks[i];
		}
	}

	public static boolean isClicked(int i){
		return clicks[i] && !prevClicks[i];
	}
	
	public static void setClick(int button, int x, int y, boolean value){
		clicks[button] = value;
		if(value){
			mouseX = x / B2DVars.PPM;
			mouseY = ( Game.V_HEIGHT - y ) / B2DVars.PPM;
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
