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
import com.jflusin.castlevanilla.backend.handlers.PlayerMovement;
import com.jflusin.castlevanilla.backend.handlers.contact.ContactHandler;
import com.jflusin.castlevanilla.backend.main.Game;
import com.jflusin.castlevanilla.backend.utils.SceneManager;
import com.jflusin.castlevanilla.backend.utils.map.Frame;
import com.jflusin.castlevanilla.backend.utils.map.astar.Node;
import com.jflusin.castlevanilla.backend.utils.map.astar.PathFinder;
import com.jflusin.castlevanilla.backend.views.entities.HumanEntity;
import com.jflusin.castlevanilla.backend.views.entities.VampireEntity;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public class TestScene extends AbstractScene {

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
	private ArrayList<Frame> mapFrames;
	ArrayList<Frame> spawns;

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
		mapFrames = new ArrayList<Frame>();

		createMap();

		mapFrames.addAll(createFramesFromTilesInLayer("Room 1", true));
		mapFrames.addAll(createFramesFromTilesInLayer("Doors", true));
		mapFrames.addAll(createFramesFromTilesInLayer("Walls", false));
		mapFrames.addAll(createFramesFromTilesInLayer("Room 2", true));
		mapFrames.addAll(createFramesFromTilesInLayer("Room 3", true));
		mapFrames.addAll(createFramesFromTilesInLayer("Room 4", true));
		mapFrames.addAll(createFramesFromTilesInLayer("Room 5", true));
		ArrayList<Frame> dest = createFramesFromTilesInLayer("Dest", true);
		spawns = createFramesFromTilesInLayer("Spawns", true);
		createPlayers();
		
		Frame playerFrame = new Frame(human.getBody().getPosition().x,
				human.getBody().getPosition().y);
			
		PathFinder pf = new PathFinder(mapFrames, playerFrame,
				dest.get(0));
		ArrayList<Node> path = pf.findPath();
		PlayerMovement movement = new PlayerMovement(true, path);
		human.setMovement(movement);
	}

	private ArrayList<Frame> createFramesFromTilesInLayer(String layerName,
			boolean walkable) {
		ArrayList<Frame> frames = new ArrayList<Frame>();
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get(
				layerName);
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
