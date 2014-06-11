package com.jflusin.castlevanilla.backend.handlers.inputs;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class InputProcessor extends InputAdapter {

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == 0){
			InputHandler.setClick(0, screenX, screenY, true);
		}
		if(button == 1){
			InputHandler.setClick(1, screenX, screenY, true);
		}
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == 0){
			InputHandler.setClick(0, screenX, screenY, false);
		}
		if(button == 1){
			InputHandler.setClick(1, screenX, screenY, false);
		}
		return true;
	}
	
	@Override
	public boolean keyDown(int k){
		if(k == Keys.UP){
			InputHandler.setKey(InputHandler.UP, true);
		}
		if(k == Keys.DOWN){
			InputHandler.setKey(InputHandler.DOWN, true);
		}
		if(k == Keys.LEFT){
			InputHandler.setKey(InputHandler.LEFT, true);
		}
		if(k == Keys.RIGHT){
			InputHandler.setKey(InputHandler.RIGHT, true);
		}
		return true;
	}

	@Override
	public boolean keyUp(int k){
		if(k == Keys.UP){
			InputHandler.setKey(InputHandler.UP, false);
		}
		if(k == Keys.DOWN){
			InputHandler.setKey(InputHandler.DOWN, false);
		}
		if(k == Keys.LEFT){
			InputHandler.setKey(InputHandler.LEFT, false);
		}
		if(k == Keys.RIGHT){
			InputHandler.setKey(InputHandler.RIGHT, false);
		}
		return true;
	}
}
