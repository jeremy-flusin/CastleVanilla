package handlers;

import java.util.Stack;

import main.BlockBunnyGame;
import states.GameState;
import states.Play;

public class GameStateManager {

	private BlockBunnyGame game;
	
	private Stack<GameState> gameStates;
	
	public static final int PLAY = 912837;
	
	public BlockBunnyGame getGame() {
		return game;
	}
	
	public GameStateManager(BlockBunnyGame game) {
		this.game = game;
		gameStates = new Stack<>();
		pushState(PLAY);
	}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	
	public void render(){
		gameStates.peek().render();
	}
	
	private GameState getState(int state){
		if (state == PLAY){
			return new Play(this);
		}
		return null;
	}
	
	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void pushState(int state){
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState g = gameStates.pop();
		g.dispose();
	}
}
