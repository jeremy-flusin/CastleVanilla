package entities;

import main.BlockBunnyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends B2DSprite {

	private int numCrystals;
	private int totalCrystals;
	
	public Player(Body body) {
		super(body);
		
		Texture t = BlockBunnyGame.res.getTexture("bunny");
		TextureRegion[] sprites = TextureRegion.split(t, 32, 32)[0];

		setAnimation(sprites, 1/12f);
	}
	
	public void collectCrystal() {
		numCrystals++;
	}
	
	public int getNumCrystals(){
		return numCrystals;
	}

	public void setTotalCrystals(int crystals){
		totalCrystals = crystals;
	}
	
	public int getTotalCrystals(){
		return totalCrystals;
	}
	
}
