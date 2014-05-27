package handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter {

	@Override
	public boolean keyDown(int k){
		if(k == Keys.D){
			InputHandler.setKey(InputHandler.JUMP, true);
		}
		if(k == Keys.F){
			InputHandler.setKey(InputHandler.SWITCH_COLOR, true);
		}
		return true;
	}

	@Override
	public boolean keyUp(int k){
		if(k == Keys.D){
			InputHandler.setKey(InputHandler.JUMP, false);
		}
		if(k == Keys.F){
			InputHandler.setKey(InputHandler.SWITCH_COLOR, false);
		}
		return true;
	}
}
