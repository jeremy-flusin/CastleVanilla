package com.jflusin.castlevanilla.backend.views.entities;

import static com.jflusin.castlevanilla.backend.utils.B2DVars.PPM;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.jflusin.castlevanilla.backend.controllers.AbstractController;
import com.jflusin.castlevanilla.backend.controllers.HumanController;
import com.jflusin.castlevanilla.backend.handlers.animation.B2DSprite;

public class HumanEntity extends AbstractEntity {
	
	B2DSprite sprite;
	
	@Override
	public void create(World world) {
		BodyDef bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(100/PPM, 100/PPM);
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(50/PPM, 50/PPM);
		fdef.shape = shape;
		Body body = world.createBody(bdef);
		body.createFixture(fdef);
		
//		Textures...
//		sprite = new B2DSprite(body);
//		sprite.setAnimation(frames, delay);
	}

	@Override
	public AbstractController getController() {
		return (HumanController)super.getController();
	}
	
}
