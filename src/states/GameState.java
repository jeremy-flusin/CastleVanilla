package states;

import handlers.GameStateManager;
import main.BlockBunnyGame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {

	protected GameStateManager gsm;
	protected BlockBunnyGame game;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.getGame();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHudCamera();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
	
}
