package com.jflusin.castlevanilla.backend.views.scenes.testscene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.jflusin.castlevanilla.backend.controllers.HumanController;
import com.jflusin.castlevanilla.backend.controllers.PlayerController;
import com.jflusin.castlevanilla.backend.controllers.VampireController;
import com.jflusin.castlevanilla.backend.factory.HumanFactory;
import com.jflusin.castlevanilla.backend.factory.VampireFactory;
import com.jflusin.castlevanilla.backend.rules.Team;
import com.jflusin.castlevanilla.backend.utils.SceneManager;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public class TestScene extends AbstractScene {
	
	private static final int NUMBER_OF_PLAYERS_IN_EACH_TEAM = 1;
	private Map<Team, ArrayList<PlayerController>> players;
	
	public TestScene(SceneManager sm) {
		super(sm);
		createTiledMap();
		createMap();
		createPlayers();
	}

	private void createTiledMap() {
		tileMap = new TmxMapLoader().load("res/maps/level.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(tileMap);
	}

	private void createPlayers() {

		ArrayList<PlayerController> humans = new ArrayList<PlayerController>();
		ArrayList<PlayerController> vampires = new ArrayList<PlayerController>();
		
		for(int i = 0; i < NUMBER_OF_PLAYERS_IN_EACH_TEAM; i++){
			HumanController human = HumanFactory.getInstance(this, "Human"+(i+1));
			human.getEntity().createInto(world, spawns.get(0 + i));
			VampireController vampire = VampireFactory.getInstance(this, "Vampire"+(i+1));
			vampire.getEntity().createInto(world, spawns.get(2 + i));
			humans.add(human);
			vampires.add(vampire);
		}
		
		players = new HashMap<Team, ArrayList<PlayerController>>();
		players.put(Team.HUMANS, humans);
		players.put(Team.VAMPIRES, vampires);
		turnManager.setPlayers(players);
	}

	@Override
	public void loadContent() {
		cm.loadTexture("res/images/human.png", "human");
	}

	@Override
	public void handleInput() {
		turnManager.getCurrentPlayer().handleInput();
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		handleInput();
		ArrayList<PlayerController> humans = players.get(Team.HUMANS);
		ArrayList<PlayerController> vampires = players.get(Team.HUMANS);
		for (PlayerController playerController : humans) {
			playerController.getEntity().update(dt);
		}
		for (PlayerController playerController : vampires) {
			playerController.getEntity().update(dt);
		}
	}

	@Override
	public void render() {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mapRenderer.setView(cam);
		mapRenderer.render();
		sb.setProjectionMatrix(cam.combined);
		b2dr.render(world, b2dcam.combined);
		for (PlayerController player: players.get(Team.HUMANS)) {
			player.getEntity().render(sb);
		}
		for (PlayerController player: players.get(Team.VAMPIRES)) {
			player.getEntity().render(sb);
		}
	}

	@Override
	public void dispose() {
		
	}
}
