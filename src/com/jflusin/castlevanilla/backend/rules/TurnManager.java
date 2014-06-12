package com.jflusin.castlevanilla.backend.rules;

import java.util.ArrayList;
import java.util.Map;

import com.jflusin.castlevanilla.backend.controllers.PlayerController;

public class TurnManager {

	private Map<Team, ArrayList<PlayerController>> players;
	private Team currentTeam;
	private int currentPlayer;
	private boolean gameEnded;
	
	public TurnManager(Map<Team, ArrayList<PlayerController>> players) {
		this.players = players;
		this.currentTeam = Team.HUMANS;
		this.currentPlayer = 0;
		this.gameEnded = false;
	}
	
	public TurnManager() {
		this.currentTeam = Team.HUMANS;
		this.currentPlayer = 0;
		this.gameEnded = false;
	}
	
	public void nextTurn(){
		if(!gameEnded){
			currentPlayer++;
			if(currentPlayer >= players.get(currentTeam).size()){
				currentPlayer = 0;
				changeTeam();
			}
		}
		System.out.println("Player turn: " + getCurrentPlayer().getModel().getNickname());
	}
	
	private void changeTeam() {
		if(currentTeam == Team.HUMANS){
			currentTeam = Team.VAMPIRES;
		}else if(currentTeam == Team.VAMPIRES){
			currentTeam = Team.HUMANS;
		}
		System.out.println("Team changed: " + currentTeam.name());
	}
	
	public void step(float dt) {
		if(!gameEnded){
			if(getCurrentPlayer().getModel().getStamina() <= 0){
				nextTurn();
				getCurrentPlayer().initTurn();
			}
		}
	}

	public Team getCurrentTeam(){
		return currentTeam;
	}
	
	public PlayerController getCurrentPlayer(){
		return players.get(currentTeam).get(currentPlayer);
	}
	
	public void setPlayers(Map<Team, ArrayList<PlayerController>> players) {
		this.players = players;
	}
}
