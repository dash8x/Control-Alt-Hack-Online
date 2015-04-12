/**
 * Defines the Player class
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

import grp.ctrlalthack.model.entropy.EntropyCard;
import grp.ctrlalthack.model.entropy.SkillModifier;
import grp.ctrlalthack.model.mission.MissionCard;
import grp.ctrlalthack.model.mission.MissionTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6580840923874920613L;
	
	private String name; //name of the player
	private String ip; //ip of the player client
	private int player_id; //unique number of the player
	private int hacker_creds; //number of hacker creds
	private int cash; //number of player money
	private HackerCard character; //character card of the player
	private ArrayList<EntropyCard> entropy_cards; //entropy cards on hand	
	
	//entropy cards in play
	private ArrayList<EntropyCard> entropy_in_play = new ArrayList<EntropyCard>();
	
	//skill modifier
	private HashMap<String,Integer> bot_skill_modifiers = new HashMap<String,Integer>(); //skill modifiers from BoT cards
	
	//status values
	private boolean attending = false; 
	private boolean is_host = false;
	private boolean has_played = false;
	private boolean ready_to_start; //indicates if is ready to start game
	private boolean character_chosen = false;
	private boolean is_my_turn; //checks if is player's turn
	
	//hacker card choices
	private HackerCard[] character_choices;
	
	//mission to play
	private MissionCard mission;
	private int curr_task; //current task to play
	
	/**
	 * Constructor
	 */
	public Player(String ip, String name) {
		this.ip = ip;
		this.player_id = 0;
		this.name = name;
		this.entropy_cards = new ArrayList<EntropyCard>();
		this.cash = 0;
		this.hacker_creds = 0;
		
		//status values
		this.setReadyToStart(false);
	}	
	
	/**
	 * Check if played in the current phase
	 */
	public boolean hasPlayed() {
		return this.has_played;
	}
	
	/**
	 * Set played to true
	 */
	public void setPlayed() {
		this.has_played = true;
	}
	
	/**
	 * Check if is attending
	 */
	public void setAttending(boolean val) {
		this.attending = val;
	}
	
	/**
	 * Check if is attending
	 */
	public boolean isAttending() {
		return this.attending;
	}
	
	/**
	 * Reset for phase
	 */
	public void resetPhase() {
		this.has_played = false;
	}
	
	/**
	 * Reset for round
	 */
	public void resetRound() {
		resetPhase();
		this.curr_task = -1;
		this.setMission(null);
		this.setAttending(false);
	}
	
	/**
	 * Sets the mission
	 */
	public void setMission(MissionCard mission) {
		this.mission = mission;		
	}

	/**
	 * Gets the mission
	 */
	public MissionCard getMission() {
		return this.mission;		
	}
	
	/**
	 * Roll for a task
	 */
	public boolean playTask(int num) {
		boolean success = false;
		if (this.hasPlayed()) {
			throw new PlayerException(this.getPlayerName() + " already played");
		} else if ( this.isMyTurn() ) {
			this.curr_task++;
			MissionTask task = this.getMission().getTasks().get(this.curr_task);
			//try if has infinite skill
			if ( this.getSkill(task.getSkill()) == GameConstants.INFINITE_SKILL ) {
				success = true;
			} else {
				//try main task
				int number_to_beat = this.getSkill(task.getSkill()) - task.getSkillModifier();
				if ( num <= number_to_beat) {
					success = true;
				} else if ( task.hasAltSkill() ) {
					//try alt task
					if ( this.getSkill(task.getAltSkill()) == GameConstants.INFINITE_SKILL ) {
						success = true;
					} else {						
						number_to_beat = this.getSkill(task.getAltSkill()) - task.getAltSkillModifier();
						if ( num <= number_to_beat) {
							success = true;
						}
					}
				}
			}
			//if failed end turn
			
			//check if last task
		} else {
			throw new PlayerException("Not " + this.getPlayerName() + "'s turn");
		}
		return success;
	}
	
	/**
	 * Sets the character choices
	 */
	public void setCharacterChoices(HackerCard[] cards) {
		if ( cards == null || cards.length > 3 ) {
			throw new IllegalArgumentException("Invalid number of character choices.");
		}
		this.character_choices = cards;
	}
	
	/**
	 * returns the character choices
	 */
	public HackerCard[] getCharacterChoices() {		
		return this.character_choices;
	}
	
	
	/**
	 * @return the name
	 */
	public String getPlayerName() {
		return name;
	}
	
	/**
	 * @return the player ip
	 */
	public String getPlayerIP() {
		return ip;
	}
	
	/**
	 * Sets the host flag
	 */
	public void setHost(boolean is_host) {
		this.is_host = is_host;
	}
	
	/**
	 * Checks if is host player
	 */
	public boolean isHost() {
		return this.is_host;
	}
	
	/**
	 * @return sets the player_id
	 */
	public int setPlayerID(int id) {
		return this.player_id = id;
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
	 * @return the entropy_cards in play
	 */
	public ArrayList<EntropyCard> getEntropyCardsInPlay() {
		return entropy_in_play;
	}
	
	/**
	 * Adds an entropy card
	 */
	public void addEntropyCard(EntropyCard card) {
		if ( card == null ) {
			throw new IllegalArgumentException("Entropy card cannot be null");
		}
		this.entropy_cards.add(card);
	}
	
	/**
	 * Empty the entropy cards 
	 */
	public ArrayList<EntropyCard> emptyEntropyCards() {
		ArrayList<EntropyCard> purge = new ArrayList<EntropyCard>();
		purge.addAll(this.getEntropyCards());
		purge.addAll(this.getEntropyCardsInPlay());
		//reset the entropy cards
		this.entropy_cards = new ArrayList<EntropyCard>();
		this.entropy_in_play = new ArrayList<EntropyCard>();
		this.bot_skill_modifiers = new HashMap<String, Integer>();
		
		return purge;
	}
	
	/**
	 * Empty the cash
	 */
	public int emptyCash() {
		int curr_cash = this.getCash();
		this.cash = 0;
		return curr_cash;
	}
	
	/**
	 * remove cash
	 */
	public void removeCash(int amount) {
		this.cash = Math.max(0, this.getCash() - amount);
	}
	
	/**
	 * Remove the mission
	 */
	public MissionCard removeMission() {
		MissionCard mission = this.getMission();
		this.setMission(null);
		return mission;
	}
	
	/**
	 * Empty the hacker creds
	 */
	public int emptyHackerCreds() {
		int curr_creds = this.getHackerCreds();
		this.hacker_creds = 0;
		return curr_creds;
	}
	
	/**
	 * remove creds
	 */
	public void removeHackerCreds(int amount) {
		this.hacker_creds = Math.max(0, this.getHackerCreds() - amount);
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
	
	/**
	 * Get skill modifier
	 */
	public HashMap<String, Integer> getBoTSkillModifiers() {
		return bot_skill_modifiers;
	}
	
	/**
	 * get single skill modifier
	 */
	public int getBoTSkillModifier(String skill) {		
		Integer skill_val = this.bot_skill_modifiers.get(skill);
		return (  skill_val == null ) ? 0 : skill_val;
	}
	
	/**
	 * Adds a skill
	 */
	private void addSkillModifier(SkillModifier skill_mod) {
		if ( skill_mod != null ) {
			//get current modify value
			Integer curr = this.bot_skill_modifiers.get(skill_mod.getSkill());
			//add modify value to current value
			if ( curr != null ) {
				curr += skill_mod.getSkillModifier();
			} else {
				curr = skill_mod.getSkillModifier();
			}
		}
	}
	
	
	/**
	 * @return the value for a specific skill
	 */
	public int getSkill(String skill) {		
		//first get unmodified value
		int orig = this.getCharacter().getSkill(skill);
		//bot skill modifier
		int bot_mod = this.getBoTSkillModifier(skill);
		//return modified val
		return orig + bot_mod;
	}
	
	/**
	 * choose a character
	 */
	public void chooseCharacter(int i) {
		if ( this.characterChosen() ) {
			throw new PlayerException("Character already chosen for " + this.getPlayerName());
		} else if ( this.getCharacterChoices() == null ) {
			throw new PlayerException("Character choosing over");
		} else {
			try {
				this.character = this.getCharacterChoices()[i];
				this.character_choices = null;
				this.character_chosen = true;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new PlayerException("Invalid character choice");
			}
		}
	}
	
	//status values
	/**
	 * @return the ready_to_start
	 */
	public boolean isReadyToStart() {
		return ready_to_start;
	}
	
	/**
	 * get character choosen
	 */
	public boolean characterChosen() {
		return this.character_chosen && this.getCharacter() != null;
	}
	
	/**
	 * @param ready_to_start the ready_to_start to set
	 */
	public void setReadyToStart(boolean ready_to_start) {
		this.ready_to_start = ready_to_start;
	}

	/**
	 * @return the is_my_turn
	 */
	public boolean isMyTurn() {
		return is_my_turn;
	}

	/**
	 * @param is_my_turn the is_my_turn to set
	 */
	public void setMyTurn(boolean is_my_turn) {
		this.is_my_turn = is_my_turn;
	}
	
}
