package entities;

import handlers.B2DVars;
import main.BlockBunnyGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class HUD {
	
	private Player player;
	private TextureRegion[] blocks;
	
	public HUD(Player player) {

		this.player = player;
		
		Texture t = BlockBunnyGame.res.getTexture("hud");
		
		blocks = new TextureRegion[3];
		
		for (int i = 0; i < blocks.length; i++){
			blocks[i] = new TextureRegion(t, 32 + i * 16, 0, 16, 16);
		}
	}
	
	public void render(SpriteBatch sb){
		
		short playerMask = player.getBody().getFixtureList().get(1).getFilterData().maskBits;
		sb.begin();
			switch (playerMask) {
			case B2DVars.BIT_RED:
				sb.draw(blocks[0], 40, 200);
				break;
			case B2DVars.BIT_GREEN:
				sb.draw(blocks[1], 40, 200);
				break;
			case B2DVars.BIT_BLUE:
				sb.draw(blocks[2], 40, 200);
				break;
			default:
				throw new RuntimeException("WTF is Happening ???");
			}
		sb.end();
	}
}
