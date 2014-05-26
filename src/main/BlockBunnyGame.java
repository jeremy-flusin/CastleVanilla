package main;

import handlers.GameStateManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockBunnyGame implements ApplicationListener {

	public static final String TITLE = "BlockBunny";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int SCALE = 2;
	public static final float STEP = 1/60f;
	
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;

	private GameStateManager gsm;
	private float accum;
	
	public SpriteBatch getSpriteBatch(){
		return sb;
	}

	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public OrthographicCamera getHudCamera(){
		return hudCam;
	}
	
	@Override
	public void create() {
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		accum += Gdx.graphics.getDeltaTime();
		while (accum >= STEP){
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
		}
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}