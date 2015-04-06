/**
 * Manages the logic of the game
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.controller;

import grp.ctrlalthack.model.Player;

import java.util.ArrayList;

public class Game {
		
	public static final int MAX_PLAYERS = 6; //absolute max number of players allowed
	public static final int MIN_PLAYERS = 3; //absolute min number of players allowed
	
	private ArrayList<Player> players; //the players in the game
	
	//status values
	private boolean has_started;
	
	/**
	 * Constructor
	 */
	public Game() {
		this.players = new ArrayList<Player>();
	}
	
	/**
	 * Returns the list of players
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	/**
	 * Removes a player from the game
	 */
	public void removePlayer(Player new_player) {
		this.players.remove(new_player);
	}
	
	/**
	 * Adds a player to the game
	 */
	public void addPlayer(Player new_player) {
		//check if game has started
		if (this.hasStarted()) {
			throw new IllegalArgumentException("Game has already started");
		} else {
			//check if game is full
			if (this.getNumPlayers() == MAX_PLAYERS) {
				throw new IllegalArgumentException("Game is full");
			} else {		
				//set the id of the player
				new_player.setPlayerID(this.getNumPlayers() + 1);
				this.players.add(new_player);				
			}
		}
	}
	
	/**
	 * Returns the current number of players
	 */
	public int getNumPlayers() {		
		int num = 0;
		if ( this.getPlayers() != null ) {
			num = this.getPlayers().size();
		}
		return num;
	}
	
	//status values
	
	/**
	 * checks if game has started
	 */
	public boolean hasStarted() {
		return this.has_started;
	}
	
}
