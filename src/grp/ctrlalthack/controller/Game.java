/**
 * Manages the logic of the game
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.controller;

import grp.ctrlalthack.data.DataIO;
import grp.ctrlalthack.model.GameConstants;
import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.Player;
import grp.ctrlalthack.model.entropy.EntropyCard;
import grp.ctrlalthack.model.mission.MissionCard;

import java.util.ArrayList;

import javax.xml.crypto.Data;

public class Game implements GameConstants {
		
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
	private Player current_player; //the current player 
	
	//autonumber generator
	private int last_id = 0;
	
	//status values
	private int game_status;
	
	/**
	 * Constructor
	 */
	public Game() {
		this.round = 1;
		this.players = new ArrayList<Player>();
		this.entropy_deck = DataIO.readEntropyCards();
		this.entropy_discard = new ArrayList<EntropyCard>();
		this.missions_deck = DataIO.readMissionCards();
		this.missions_played = new ArrayList<MissionCard>();
		this.hacker_cards = DataIO.readHackerCards();
		this.setGameStatus(STATUS_WAITING_TO_START);
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
	 * Returns the current player
	 */
	public Player getCurrentPlayer() {
		return this.current_player;
	}
	
	/**
	 * return game stats
	 */
	public GameStats getGameStats() {
		String player = "";
		if ( getCurrentPlayer() != null ) {
			player = getCurrentPlayer().getPlayerName();
		}
		return new GameStats(getTotalHackerCreds(), getTotalCash(), getRound(), getPhase(), player);
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
			throw new GameException("Game has already started");
		} else {
			//check if game is full
			if (this.getNumPlayers() == MAX_PLAYERS) {
				throw new GameException("Game is full");
			} else {		
				//if first player, set as host
				if ( last_id == 0 ) {
					new_player.setHost(true);
				}				
				//set the id of the player			
				new_player.setPlayerID(this.last_id + 1);
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
	public static Object getRandomElement(ArrayList list) {
		Object elem = null;
		if ( list != null || list.size() != 0 ) {
			elem = list.get((int)(Math.random() * list.size()));
		} else {
			throw new IndexOutOfBoundsException("List is empty, cannot get random choice.");
		}
		return elem;
	}
	
	/**
	 * pop a hacker card
	 */
	private HackerCard getHackerCard() {
		return (HackerCard) getRandomElement(this.hacker_cards);
	}
	
	/**
	 * pop an entropy card
	 */
	private EntropyCard getEntropyCard() {
		return (EntropyCard) getRandomElement(this.entropy_deck);
	}
	
	/**
	 * Starts the game
	 */
	public void startGame() {
		//check if game has started
		if (this.hasStarted()) {
			throw new GameException("Game has already started");
		} else if (this.getNumPlayers() < MIN_PLAYERS) {
			throw new GameException("Not enough players.");
		} else {
			//check if all players are ready to start
			for ( Player p : this.getPlayers() ) {
				if ( !p.isReadyToStart() ) {
					throw new GameException(p.getPlayerName() + " is not ready.");					
				}
			}
			//start the game
			this.setGameStatus(STATUS_CHOOSE_CHARACTER);
			//assign character card choices to the players
			this.distributeCharacters();
		}
	}
	
	/**
	 * Distributes character cards
	 */
	private void distributeCharacters() {
		if ( inChooseCharacter() ) {
			//choose number of cards to give
			int num_cards = (this.getNumPlayers() == MAX_PLAYERS) ? 2 : 3;			
			for ( Player p : this.getPlayers() ) {
				//reset entropy cards
				p.emptyEntropyCards();
				//give cash and creds
				p.emptyCash();
				p.emptyHackerCreds();
				p.addHackerCreds(START_CREDS);
				//distribute the cards
				HackerCard[] cards = new HackerCard[num_cards];
				for ( int i = 0; i < cards.length; i++) {
					cards[i] = getHackerCard();					
				}
				p.setCharacterChoices(cards);
				//give entropy cards
				for ( int i = 0; i < 3; i++ ) {
					p.addEntropyCard(this.getEntropyCard());
				}
			}
		} else {
			throw new GameException("Not in choose character stage");
		}
		
	}

	//status values
	/**
	 * Sets the game status
	 */
	public void setGameStatus(int status) {
		this.game_status = status;
	}
	
	/**
	 * Returns the game status
	 */
	public int getGameStatus() {
		return this.game_status;
	}
	
	/**
	 * checks if game has started
	 */
	public boolean hasStarted() {
		return this.getGameStatus() != STATUS_WAITING_TO_START;
	}
	
	/**
	 * checks if game is int choose character stage
	 */
	public boolean inChooseCharacter() {
		return this.getGameStatus() == STATUS_CHOOSE_CHARACTER;
	}
}
