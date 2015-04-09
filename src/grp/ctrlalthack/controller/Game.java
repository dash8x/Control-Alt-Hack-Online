/**
 * Manages the logic of the game
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.controller;

import grp.ctrlalthack.data.DataIO;
import grp.ctrlalthack.model.EntropyCard;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.Player;
import grp.ctrlalthack.model.mission.MissionCard;

import java.util.ArrayList;

public class Game {
		
	public static final int MAX_PLAYERS = 6; //absolute max number of players allowed
	public static final int MIN_PLAYERS = 3; //absolute min number of players allowed
	
	private ArrayList<Player> players; //the players in the game
	private ArrayList<EntropyCard> entropy_deck; //unplayed entropy cards
	private ArrayList<EntropyCard> entropy_discard; //discarded entropy cards
	private ArrayList<MissionCard> missions_deck; //unplayed missions
	private ArrayList<MissionCard> missions_played; //played missions
	private ArrayList<HackerCard> hacker_cards; //available hacker cards	
	private int round; //round number of the game
	private int phase; //phase of the game
	
	//status values
	private boolean has_started;
	
	/**
	 * Constructor
	 */
	public Game() {
		this.round = 1;
		this.players = new ArrayList<Player>();
		this.entropy_deck = new ArrayList<EntropyCard>();
		this.entropy_discard = new ArrayList<EntropyCard>();
		this.missions_deck = new ArrayList<MissionCard>();
		this.missions_played = new ArrayList<MissionCard>();
		this.hacker_cards = DataIO.readHackerCards();
	}
	
	/**
	 * Returns the current round number
	 */
	public int getRound() {
		return this.round;
	}
	
	/**
	 * Returns the current phase number
	 */
	public int getPhase() {
		return this.phase;
	}
	
	/**
	 * Returns the total hacker creds of all players
	 */
	public int getTotalHackerCreds() {
		int total = 0;
		for ( Player p : getPlayers() ) {
			total += p.getHackerCreds();
		}
		return total;
	}
	
	/**
	 * Returns the total cash of all players
	 */
	public int getTotalCash() {
		int total = 0;
		for ( Player p : getPlayers() ) {
			total += p.getCash();
		}
		return total;
	}
	
	/**
	 * Sets the phase number
	 */
	public void setPhase(int phase) {
		if ( phase < 1 || phase > 7 ) { 
			throw new IllegalArgumentException("Invalid phase number");
		} else {
			this.phase = phase;
		}
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
	
	/**
	 * Method to get a random object from an ArrayList
	 */
	public static Object getRandomElement(ArrayList<Object> list) {
		Object elem = null;
		if ( list != null ) {
			elem = list.get((int)(Math.random() * list.size()));
		}
		return elem;
	}
	
	//status values
	
	/**
	 * checks if game has started
	 */
	public boolean hasStarted() {
		return this.has_started;
	}
	
}
