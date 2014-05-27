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
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import entities.Crystal;
import entities.HUD;
import entities.Player;

public class Play extends GameState {

	private World world;
	private Player player;
	private HUD hud;
	
	private Array<Crystal> crystals = new Array<Crystal>();
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera box2dCam;
	private MyContactListener contactListener;
	
	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer mapRenderer;
	
	
	public Play(GameStateManager gsm) {
		super(gsm);
		
		world = new World(new Vector2(0, -9.81f), true);
		contactListener = new MyContactListener();
		world.setContactListener(contactListener);
		b2dr = new Box2DDebugRenderer();
		
		createMap();
		createPlayer();
		createCrystals();
		createHud();
		
		//set up box2d cam
		box2dCam = new OrthographicCamera();
		box2dCam.setToOrtho(false, BlockBunnyGame.V_WIDTH / PPM, BlockBunnyGame.V_HEIGHT / PPM);
		
	}

	@Override
	public void handleInput() {
		//player jump
		if(InputHandler.isDown(InputHandler.JUMP)){
			if(contactListener.isPlayerOnGround()){
				player.getBody().applyForceToCenter(0, 150, true);
			}
		}
		if(InputHandler.isPressed(InputHandler.SWITCH_COLOR)){
			switchColor();
		}
	}

	private void switchColor() {

		short newMaskBit;
		short newMaskBitWithCrystal = 0;
		short oldMaskBit = player.getBody().getFixtureList().first().getFilterData().maskBits;
		
		//removing crystal bit
		oldMaskBit &= ~B2DVars.BIT_CRYSTAL;
		
		switch (oldMaskBit) {
			case B2DVars.BIT_RED:
				newMaskBit = B2DVars.BIT_BLUE;
				break;
			case B2DVars.BIT_BLUE:
				newMaskBit = B2DVars.BIT_GREEN;
				break;
			case B2DVars.BIT_GREEN:
				newMaskBit = B2DVars.BIT_RED;
				break;
			default:
				throw new RuntimeException("WTF is happening ??");
		}
		
		//putting the crystal bit
		newMaskBitWithCrystal = (short) (newMaskBit | B2DVars.BIT_CRYSTAL);
		
		Filter filterData = new Filter();

		//player
		filterData.categoryBits = B2DVars.BIT_PLAYER;
		filterData.maskBits = newMaskBitWithCrystal;
		player.getBody().getFixtureList().first().setFilterData(filterData);
		
		//foot
		filterData.maskBits = newMaskBit;
		player.getBody().getFixtureList().get(1).setFilterData(filterData);
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		handleInput();
		player.update(dt);		
		for (Crystal crystal: crystals) {
			crystal.update(dt);
		}
		
		Array<Crystal> crystalsToRemove = contactListener.getToRemove();
		
		for (Crystal crystal : crystalsToRemove) {
			crystals.removeValue(crystal, true);
			world.destroyBody(crystal.getBody());
			player.collectCrystal();
		}
		crystalsToRemove.clear();
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mapRenderer.setView(cam);
		mapRenderer.render();
		
		cam.position.set(player.getPosition().x * PPM + BlockBunnyGame.V_WIDTH / 4,
				BlockBunnyGame.V_HEIGHT / 2,
				0);
		cam.update();
		
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);
		for (Crystal crystal: crystals) {
			crystal.render(sb);
		}
		
		sb.setProjectionMatrix(hudCam.combined);
		hud.render(sb);
		
//		b2dr.render(world, box2dCam.combined);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	private void createMap(){
		
		tileMap = new TmxMapLoader().load("res/maps/level1.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(tileMap);

		createTiledLayer("Red", B2DVars.BIT_RED);
		createTiledLayer("Blue", B2DVars.BIT_BLUE);
		createTiledLayer("Green", B2DVars.BIT_GREEN);
		
	}
	
	private void createTiledLayer(String name, short category) {
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(name);
		
		tileSize = layer.getTileWidth();
		
		for (int row = 0; row < layer.getHeight(); row++){
			for (int col = 0; col < layer.getWidth(); col++){
				
				Cell cell = layer.getCell(col, row);
				
				if(cell != null && cell.getTile() != null){
					bdef.type = BodyType.StaticBody;
					bdef.position.set(
							(col + 0.5f) * tileSize / PPM,
							(row + 0.5f) * tileSize / PPM
					);
					
					ChainShape cshape = new ChainShape();
					Vector2[] v = new Vector2[3];
					v[0] = new Vector2(
							-tileSize / 2 / PPM, -tileSize / 2 / PPM);
					v[1] = new Vector2(
							-tileSize / 2 / PPM, tileSize / 2 / PPM);
					v[2] = new Vector2(
							tileSize / 2 / PPM, tileSize / 2 / PPM);
					
					cshape.createChain(v);
					fdef.friction = 0;
					fdef.shape = cshape;
					fdef.isSensor = false;
					fdef.filter.categoryBits = category;
					fdef.filter.maskBits = -1;
					
					world.createBody(bdef).createFixture(fdef);
				}
			}
		}
	}

	private void createPlayer() {
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		Fixture fixture = null;
		Body body = null;
		
		//Creating player
		bdef.position.set(80 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(0.8f, 0);
		body = world.createBody(bdef);
		
		shape.setAsBox(13 / PPM, 13 / PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_RED | B2DVars.BIT_CRYSTAL;
		fixture = body.createFixture(fdef);
		fixture.setUserData("Player");
		
		// create foot sensor
		shape.setAsBox(13 / PPM, 2 / PPM, new Vector2(0, -13 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_RED;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("Player's foot");
		player = new Player(body);
		
		body.setUserData(player);
		
	}
	
	private void createCrystals() {
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		MapLayer layer = (MapLayer) tileMap.getLayers().get("Crystals");
		MapObjects objects = layer.getObjects();
		
		for (MapObject mapObject : objects) {
			bdef.type = BodyType.StaticBody;
			bdef.position.set(
					(float) mapObject.getProperties().get("x") / PPM,
					(float) mapObject.getProperties().get("y") / PPM
			);
			
			CircleShape cshape = new CircleShape();
			cshape.setPosition(new Vector2(8/PPM, 8/PPM));
			cshape.setRadius(8/PPM);
			
			fdef.filter.categoryBits = B2DVars.BIT_CRYSTAL;
			fdef.filter.maskBits = B2DVars.BIT_PLAYER;
			fdef.isSensor = true;
			fdef.shape = cshape;
			
			Body body = world.createBody(bdef);
			Fixture fixture = body.createFixture(fdef);
			
			Crystal c = new Crystal(body);
			crystals.add(c);
			
			fixture.setUserData(c);
			body.setUserData(c);
		}
	}

	private void createHud() {
		hud = new HUD(player);
	}

}
