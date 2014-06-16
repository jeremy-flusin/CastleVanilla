package com.jflusin.castlevanilla.backend.views.entities;

import static com.jflusin.castlevanilla.backend.utils.B2DVars.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jflusin.castlevanilla.backend.controllers.HumanController;
import com.jflusin.castlevanilla.backend.handlers.animation.B2DSprite;
import com.jflusin.castlevanilla.backend.utils.map.Frame;
import com.jflusin.castlevanilla.backend.views.scenes.AbstractScene;

public class HumanEntity extends PlayerEntity {

	public HumanEntity(AbstractScene scene) {
		super(scene);
	}
	
	@Override
	public void createInto(World world, Frame spawn) {
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(spawn.getX(), spawn.getY());
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(spawn.getWidth()/ 2 /PPM, spawn.getHeight()/ 2 /PPM);
		fdef.shape = shape;
		fdef.friction = 0;
		fdef.isSensor = true;
		body = world.createBody(bdef);
		body.createFixture(fdef);
		createSprite(body);
	}

	private void createSprite(Body body) {
		Texture t = getScene().getContent().getTexture("human");
		for(int i = 0; i < 4; i++){
			TextureRegion[] human = TextureRegion.split(t, 32, 32)[i];
			TextureRegion[] frames = new TextureRegion[6];
			for(int j = 0; j < 6; j++){
				frames[j] = human[j];
			}
			orientedSprites.add(frames);
		}
		sprite = new B2DSprite(body);
		sprite.setAnimation(orientedSprites.get(LOOKS_DOWN), 1/12f);
	}

	@Override
	public HumanController getController() {
		return (HumanController)super.getController();
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		sprite.update(dt, !moving);
	}

	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb);
	}
	
	@Override
	public Body getBody() {
		return body;
	}
}
