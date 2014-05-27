package com.jflusin.castlevanilla.backend.views.scenes.testscene;

import static com.jflusin.castlevanilla.backend.utils.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.castlevanilla.backend.handlers.contact.ContactHandler;
import com.jflusin.castlevanilla.backend.main.Game;
import com.jflusin.castlevanilla.backend.utils.ContentManager;
import com.jflusin.castlevanilla.backend.utils.SceneManager;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public class TestScene extends AbstractScene {

	//Debug renderer & camera
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dcam;
	
	//Contact handler
	private ContactHandler contactHandler;
	
	//Scene objects
	World world;
	HumanEntity player;
	
	public TestScene(SceneManager sm) {
		super(sm);
		
		//Debug initializations
		b2dr = new Box2DDebugRenderer();
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
		
		//Initializations
		contactHandler = new ContactHandler();
		world = new World(new Vector2(0, 0f), true);
		world.setContactListener(contactHandler);
		
		createPlayer();
	}

	private void createPlayer() {
		
		player = new HumanEntity();
		player.create(world);

	}
	
	@Override
	public void loadContent() {
		cm.loadTexture("res/images/human.png", "human");
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		b2dr.render(world, b2dcam.combined);
	}

	@Override
	public void dispose() {

	}
}
