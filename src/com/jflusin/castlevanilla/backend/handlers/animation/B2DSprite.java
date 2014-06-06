package com.jflusin.castlevanilla.backend.handlers.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.jflusin.castlevanilla.backend.utils.B2DVars;

/**
 * Animation attached to a Box2D body
 */
public class B2DSprite {

	protected Body body;
	protected AnimationHandler animation;
	protected float width;
	protected float height;
	protected float delay;
	
	public B2DSprite(Body body) {
		this.body = body;
		animation = new AnimationHandler();
	}
	
	public void setAnimation(TextureRegion[] frames, float delay){
		animation.setFrames(frames, delay);
		height = frames[0].getRegionHeight();
		width = frames[0].getRegionWidth();
		this.delay = delay;
	}
	
	public void updateFrames(TextureRegion[] frames){
		animation.setFrames(frames, delay);
	}
	
	public void update(float dt, boolean paused){
		animation.update(dt, paused);
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		sb.draw(
				animation.getFrame(),
				body.getPosition().x * B2DVars.PPM - width / 2,
				body.getPosition().y * B2DVars.PPM - height / 2
				);

		sb.end();
	}
	
	public Body getBody() { 
		return body; 
	}
	
	public Vector2 getPosition(){
		return body.getPosition();
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
}
