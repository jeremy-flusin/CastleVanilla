package handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

import entities.Crystal;

public class MyContactListener implements ContactListener {

	private int numFootContact;
	private Array<Crystal> toRemove = new Array<>();

	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa.getUserData() != null
				&& fa.getUserData().equals("Player's foot")) {
			numFootContact++;
		}

		if (fb.getUserData() != null
				&& fb.getUserData().equals("Player's foot")) {
			numFootContact++;
		}

		if (fa.getUserData() != null && fa.getUserData() instanceof Crystal) {
			toRemove.add((Crystal) fa.getUserData());
		}

		if (fb.getUserData() != null && fb.getUserData() instanceof Crystal) {
			toRemove.add((Crystal) fb.getUserData());
		}

	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa.getUserData() != null
				&& fa.getUserData().equals("Player's foot")) {
			numFootContact--;
		}

		if (fb.getUserData() != null
				&& fb.getUserData().equals("Player's foot")) {
			numFootContact--;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

	public boolean isPlayerOnGround() {
		return numFootContact > 0;
	}

	public Array<Crystal> getToRemove() {
		return toRemove;
	}
	
}
