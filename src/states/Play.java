package states;

import main.BlockBunnyGame;
import handlers.GameStateManager;

import static handlers.B2DVars.PPM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Play extends GameState {

	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera box2dCam;
	
	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		b2dr = new Box2DDebugRenderer();
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(160 / PPM, 120 / PPM);
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50 / PPM, 5 / PPM);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		
		bdef.position.set(160 / PPM ,200 / PPM);
		bdef.type = BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		shape.setAsBox(5 / PPM, 5 / PPM);
		fdef.shape = shape;
		fdef.restitution = 0.5f;
		body.createFixture(fdef);
		
		//set up box2d cam
		box2dCam = new OrthographicCamera();
		box2dCam.setToOrtho(false, BlockBunnyGame.V_WIDTH / PPM, BlockBunnyGame.V_HEIGHT / PPM);
	}
	
	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
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
