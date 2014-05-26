package entities;

import main.BlockBunnyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Crystal extends B2DSprite{

	public Crystal(Body body) {
		super(body);
		Texture t = BlockBunnyGame.res.getTexture("crystal");
		TextureRegion[] frames = TextureRegion.split(t, 16, 16)[0];
		
		animation.setFrames(frames, 1/12f);
	}

}
