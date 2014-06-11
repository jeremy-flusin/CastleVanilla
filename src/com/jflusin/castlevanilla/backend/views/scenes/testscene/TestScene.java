package com.jflusin.castlevanilla.backend.views.scenes.testscene;

import static com.jflusin.castlevanilla.backend.utils.B2DVars.PPM;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.castlevanilla.backend.factory.HumanFactory;
import com.jflusin.castlevanilla.backend.handlers.contact.ContactHandler;
import com.jflusin.castlevanilla.backend.handlers.inputs.InputHandler;
import com.jflusin.castlevanilla.backend.main.Game;
import com.jflusin.castlevanilla.backend.utils.SceneManager;
import com.jflusin.castlevanilla.backend.utils.map.Frame;
import com.jflusin.castlevanilla.backend.utils.map.Room;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;
import com.jflusin.castlevanilla.backend.views.entities.VampireEntity;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public class TestScene extends AbstractScene {

	// Constants
	private static final int TOTAL_ROOMS = 8;
	
	// Debug renderer & camera
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dcam;

	// Contact handler
	private ContactHandler contactHandler;

	// Scene objects
	World world;
	HumanEntity human;
	VampireEntity vampire;

	// Map
	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer mapRenderer;
	ArrayList<Frame> spawns;
	ArrayList<Frame> doors;
	ArrayList<Room> rooms;
	
	public TestScene(SceneManager sm) {
		super(sm);

		// Debug initializations
		b2dr = new Box2DDebugRenderer();
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);

		// Initializations
		contactHandler = new ContactHandler();
		world = new World(new Vector2(0, 0f), true);
		world.setContactListener(contactHandler);

		createMap();

		doors = createFramesFromTilesInLayer("Doors", true);
		spawns = createFramesFromTilesInLayer("Spawns", true);
		rooms = new ArrayList<Room>();
		
		for(int i = 0; i < TOTAL_ROOMS; i++){
			ArrayList<Frame> groundFrames = createFramesFromTilesInLayer("Room" + i, true);
			Room room = new Room("Room" + i, groundFrames);
			//Adding doors
			for(Frame door: doors){
				for(Frame groundFrame: groundFrames){
					if (door.isAdjacentWith(groundFrame)){
						room.addDoor(door);
					}
				}
			}
			//Adding spawns
			for(Frame spawn: spawns){
				for(Frame groundFrame: groundFrames){
					if (spawn.isSameCoodinatesAs(groundFrame)){
						room.addSpawn(spawn);
					}
				}
			}
			rooms.add(room);
		}

		createPlayers();
	}

	private ArrayList<Frame> createFramesFromTilesInLayer(String layerName,
			boolean walkable) {
		ArrayList<Frame> frames = new ArrayList<Frame>();
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(
				layerName);
		if(layer != null){
			tileSize = layer.getTileWidth();
	
			for (int row = 0; row < layer.getHeight(); row++) {
				for (int col = 0; col < layer.getWidth(); col++) {
	
					Cell cell = layer.getCell(col, row);
	
					if (cell != null && cell.getTile() != null) {
						Frame frame = new Frame((col + 0.5f) * tileSize / PPM,
								(row + 0.5f) * tileSize / PPM, tileSize, tileSize,
								walkable);
						frames.add(frame);
					}
				}
			}
			return frames;
		}else{
			throw new RuntimeException("Layer: " + layerName + " could not be found in map !");
		}
	}

	private void createMap() {
		tileMap = new TmxMapLoader().load("res/maps/level.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(tileMap);
	}

	private void createPlayers() {
		
		Random r = new Random();
		Frame spawn = spawns.get(r.nextInt(spawns.size()));
		
		human = HumanFactory.getInstance(this).getEntity();
		human.createInto(world, spawn);
	}

	@Override
	public void loadContent() {
		cm.loadTexture("res/images/human.png", "human");
	}

	@Override
	public void handleInput() {
		human.getController().handleInput();
	}

	@Override
	public void update(float dt) {
		world.step(dt, 6, 2);
		handleInput();
		human.update(dt);
		
		if(InputHandler.isClicked(0)){
			System.out.println("Mouse left-clicked at: (" + InputHandler.mouseX + ";" + InputHandler.mouseY +")");
		}
		
		if(InputHandler.isClicked(1)){
			System.out.println("Mouse right-clicked at: (" + InputHandler.mouseX + ";" + InputHandler.mouseY +")");
		}
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mapRenderer.setView(cam);
		mapRenderer.render();
		sb.setProjectionMatrix(cam.combined);
		human.render(sb);
		b2dr.render(world, b2dcam.combined);
	}

	@Override
	public void dispose() {

	}
}
