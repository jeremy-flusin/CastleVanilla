package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class BlockBunny {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = BlockBunnyGame.TITLE;
		cfg.width = BlockBunnyGame.V_WIDTH * BlockBunnyGame.SCALE;
		cfg.height = BlockBunnyGame.V_HEIGHT * BlockBunnyGame.SCALE;
		
		new LwjglApplication(new BlockBunnyGame(), cfg);
	      
	}
	
}
