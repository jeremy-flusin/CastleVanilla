package com.jflusin.castlevanilla.backend.views.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jflusin.castlevanilla.backend.handlers.PlayerMovement;
import com.jflusin.castlevanilla.backend.handlers.animation.B2DSprite;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public abstract class PlayerEntity extends AbstractEntity {
	
	public PlayerEntity(AbstractScene scene) {
		super(scene);
		orientedSprites = new ArrayList<>();
	}

	public static final int LOOKS_DOWN = 0;
	public static final int LOOKS_LEFT = 1;
	public static final int LOOKS_RIGHT = 2;
	public static final int LOOKS_UP = 3; 
	
	protected ArrayList<TextureRegion[]> orientedSprites;
	protected PlayerMovement movement;
	protected boolean moving;

	protected B2DSprite sprite;
	
	public void setDirection(int direction){
		switch (direction) {
		case LOOKS_DOWN:
			sprite.updateFrames(orientedSprites.get(LOOKS_DOWN));
			break;
		case LOOKS_LEFT:
			sprite.updateFrames(orientedSprites.get(LOOKS_LEFT));
			break;
		case LOOKS_RIGHT:
			sprite.updateFrames(orientedSprites.get(LOOKS_RIGHT));
			break;
		case LOOKS_UP:
			sprite.updateFrames(orientedSprites.get(LOOKS_UP));
			break;
		default:
			break;
		}
	}
}
