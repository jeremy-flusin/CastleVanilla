package com.jflusin.castlevanilla.backend.views.scenes;

import static com.jflusin.castlevanilla.backend.utils.B2DVars.PPM;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.castlevanilla.backend.handlers.contact.ContactHandler;
import com.jflusin.castlevanilla.backend.main.Game;
import com.jflusin.castlevanilla.backend.rules.TurnManager;
import com.jflusin.castlevanilla.backend.utils.ContentManager;
import com.jflusin.castlevanilla.backend.utils.SceneManager;
import com.jflusin.castlevanilla.backend.utils.map.Door;
import com.jflusin.castlevanilla.backend.utils.map.Frame;
import com.jflusin.castlevanilla.backend.utils.map.GameMap;
import com.jflusin.castlevanilla.backend.utils.map.Room;
import com.jflusin.castlevanilla.backend.utils.map.Spawn;

public abstract class AbstractScene {
	
	protected static int TOTAL_ROOMS = 8;
	
	protected Box2DDebugRenderer b2dr;
	protected OrthographicCamera b2dcam;

	protected ContactHandler contactHandler;
	
	protected SceneManager sm;
	protected Game game;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected ContentManager cm;
	
	protected ArrayList<Frame> spawns;
	protected ArrayList<Frame> doors;
	protected ArrayList<Room> rooms;
	
	protected World world;
	private GameMap map;
	
	// Map
	protected TiledMap tileMap;
	protected float tileSize;
	protected OrthogonalTiledMapRenderer mapRenderer;
	
	protected TurnManager turnManager;
	
	public AbstractScene(SceneManager sm) {
		this.sm = sm;
		game = sm.getGame();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		cm = new ContentManager();
		
		// Debug initializations
		b2dr = new Box2DDebugRenderer();
		b2dcam = new OrthographicCamera();
		b2dcam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);

		// Initializations
		contactHandler = new ContactHandler();
		world = new World(new Vector2(0, 0f), true);
		world.setContactListener(contactHandler);
		turnManager = new TurnManager();
		loadContent();
	}

	public abstract void loadContent();
	public abstract void handleInput();
	public abstract void render();
	public abstract void dispose();
	
	public void update(float dt){
		world.step(dt, 6, 2);
		turnManager.step(dt);
	}

	protected void createMap() {
		
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
						Door doorObject = new Door(door.getX(), door.getY());
						room.addDoor(doorObject);
					}
				}
			}
			//Adding spawns
			for(Frame spawn: spawns){
				for(Frame groundFrame: groundFrames){
					if (spawn.isSameCoodinatesAs(groundFrame)){
						Spawn spawnObject = new Spawn(spawn.getX(), spawn.getY());
						room.addSpawn(spawnObject);
					}
				}
			}
			rooms.add(room);
		}
		map = new GameMap(rooms);
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
	
	public ContentManager getContent(){
		return cm;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public ArrayList<Frame> getSpawns() {
		return spawns;
	}
	
	public ArrayList<Frame> getDoors() {
		return doors;
	}
	
	public GameMap getMap() {
		return map;
	}
}
