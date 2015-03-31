/**
 * Defines the Player class
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

import java.util.ArrayList;

public class Player {
	
	private String name; //name of the player
	private int player_id; //unique number of the player
	private int hacker_creds; //number of hacker creds
	private int cash; //number of player money
	private HackerCard character; //character card of the player
	private ArrayList<EntropyCard> entropy_cards; //entropy cards on hand
	
	/**
	 * Constructor
	 */
	public Player(int player_id, String name) {
		this.player_id = player_id;
		this.name = name;
		this.entropy_cards = new ArrayList<EntropyCard>();
		this.cash = 0;
		this.hacker_creds = 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the player_id
	 */
	public int getPlayerID() {
		return player_id;
	}

	/**
	 * @return the hacker_creds
	 */
	public int getHackerCreds() {
		return hacker_creds;
	}

	/**
	 * @return the cash
	 */
	public int getCash() {
		return cash;
	}

	/**
	 * @return the character
	 */
	public HackerCard getCharacter() {
		return character;
	}

	/**
	 * @return the entropy_cards
	 */
	public ArrayList<EntropyCard> getEntropyCards() {
		return entropy_cards;
	}		
	
	/**
	 * Adds an entropy card
	 */
	public void addEntropyCard(EntropyCard card) {
		if ( card == null ) {
			throw new NullPointerException("Entropy card cannot be null");
		}
		this.entropy_cards.add(card);
	}
	
	/**
	 * Adds cash
	 */
	public void addCash(int cash) {
		this.cash += cash;
	}
	
	/**
	 * Adds hacker_creds
	 */
	public void addHackerCreds(int hacker_creds) {
		this.hacker_creds += hacker_creds;
	}
	
	/**
	 * Sets character card
	 */
	public void setCharacter(HackerCard hacker) {
		this.character = hacker;
	}
	
}
