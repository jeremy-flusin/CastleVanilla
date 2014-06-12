package com.jflusin.castlevanilla.backend.models;

public class PlayerModel extends AbstractModel {

	public static final int TOPPED_STAMINA = 7;
	protected int maxStamina = 3;
	protected int stamina;
	protected String nickname;
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public int getStamina() {
		return stamina;
	}
	
	public int getMaxStamina() {
		return maxStamina;
	}
	
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}
	
}
