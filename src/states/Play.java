package states;

import static handlers.B2DVars.PPM;
import handlers.B2DVars;
import handlers.GameStateManager;
import handlers.InputHandler;
import handlers.MyContactListener;
import main.BlockBunnyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Play extends GameState {

	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera box2dCam;
	private Body playerBody;
	private MyContactListener contactListener;
	
	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		contactListener = new MyContactListener();
		world.setContactListener(contactListener);
		b2dr = new Box2DDebugRenderer();
		
		//Creating plateform
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / PPM, 120 / PPM);
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / PPM, 5 / PPM);
		
		Fixture fixture = null;
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_GROUND;
		fdef.filter.maskBits = B2DVars.BIT_PLAYER; 
		fixture = body.createFixture(fdef);
		fixture.setUserData("Plateform");
		
		//Creating player
		bdef.position.set(160 / PPM ,200 / PPM);
		bdef.type = BodyType.DynamicBody;
		playerBody = world.createBody(bdef);
		
		shape.setAsBox(5 / PPM, 5 / PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_GROUND;
		fixture = playerBody.createFixture(fdef);
		fixture.setUserData("Player");
		
		//create ball
//		bdef.position.set(153 / PPM, 220 / PPM);
//		body = world.createBody(bdef);
//		CircleShape cshape = new CircleShape();
//		cshape.setRadius(5 / PPM);
//		fdef.shape = cshape;		
//		fdef.filter.categoryBits = B2DVars.BIT_BALL;
//		fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_BOX;
//		fixture = body.createFixture(fdef);
//		fixture.setUserData("Ball");
		
		// create foot sensor
		shape.setAsBox(2 / PPM, 2 / PPM, new Vector2(0, -5 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_GROUND;
		fdef.isSensor = true;
		playerBody.createFixture(fdef).setUserData("Player's foot");
		
		//set up box2d cam
		box2dCam = new OrthographicCamera();
		box2dCam.setToOrtho(false, BlockBunnyGame.V_WIDTH / PPM, BlockBunnyGame.V_HEIGHT / PPM);
	}
	
	@Override
	public void handleInput() {
//		if (InputHandler.isPressed(InputHandler.BUTTON1)){
//			System.out.println("Button 1 is Down");
//		}
//		if (InputHandler.isPressed(InputHandler.BUTTON2)){
//			System.out.println("Button 2 is Down");
//		}
		
		//player jump
		if(InputHandler.isDown(InputHandler.BUTTON1)){
			if(contactListener.isPlayerOnGround()){
				playerBody.applyForceToCenter(0, 100, true);
			}
		}
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		handleInput();
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		b2dr.render(world, box2dCam.combined);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
