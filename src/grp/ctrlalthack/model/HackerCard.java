/**
 * Defines Hacker Character Cards
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model;

import java.util.HashMap;

public class HackerCard {
	
	private String name; //name of the character
	private String desc; //character description
	private int kitchen_sink; //kitchen sink skill level
	private HashMap<String,Integer> skills; //skill set
	private int start_cash; //number of money player gets at start of each round, default 2K
	private boolean can_reroll; //free reroll once per turn
	private boolean draw_double; //can draw 2 mission cards
	private int fail_penalty; //number of hacker cred lost if mission failed
	
	/**
	 * Constructor
	 */
	public HackerCard(String name, String desc, HashMap<String,Integer> skills, int kitchen_sink) {
		this.name = name;
		this.desc = desc;	
		this.setKitchenSink(kitchen_sink);
		this.setSkills(skills);
		//defaults
		this.setStartCash(2000);
		this.setReroll(false);
		this.setDrawDouble(false);
		this.setFailPenalty(0);
	}		
	
	/**
	 * @param kitchen_sink the kitchen_sink to set
	 */
	private void setKitchenSink(int kitchen_sink) {
		if ( kitchen_sink < 0 ) {
			throw new IllegalArgumentException("Skill value has to be positive");
		}
		this.kitchen_sink = kitchen_sink;
	}

	/**
	 * @param skills the skills to set
	 */
	private void setSkills(HashMap<String, Integer> skills) {
		if ( skills == null || skills.isEmpty() ) {
			throw new IllegalArgumentException("Skill set cannot be empty");
		}
		this.skills = skills;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return the kitchen_sink
	 */
	public int getKitchenSink() {
		return kitchen_sink;
	}

	/**
	 * @return the skills
	 */
	public HashMap<String, Integer> getSkills() {
		return skills;
	}
	
	/**
	 * @return the value for a specific skill
	 */
	public int getSkill(String skill) {		
		Integer skill_val = this.getSkills().get(skill);
		if (  skill_val == null ) {
			//return kitchen sink if skill not specified
			return this.getKitchenSink();
		} else {
			return skill_val;
		}
	}
	
	/**
	 * @return the start_cash
	 */
	public int getStartCash() {
		return this.start_cash;
	}

	/**
	 * @return the draw_double
	 */
	public boolean canDrawDouble() {
		return this.draw_double;
	}
	
	/**
	 * @return the can_reroll
	 */
	public boolean canReroll() {
		return this.can_reroll;
	}

	/**
	 * @return the fail_penalty
	 */
	public int getFailPenalty() {
		return this.fail_penalty;
	}

	/**
	 * @param cash_bonus the start_cash to set
	 */
	public void setStartCash(int start_cash) {
		if ( start_cash < 0 ) {
			throw new IllegalArgumentException("Start cash cannot be negative");
		}
		this.start_cash = start_cash;
	}

	/**
	 * @param draw_double the draw_double to set
	 */
	public void setDrawDouble(boolean draw_double) {
		this.draw_double = draw_double;
	}

	/**
	 * @param can_reroll the can_reroll to set
	 */
	public void setReroll(boolean can_reroll) {
		this.can_reroll = can_reroll;
	}
	
	/**
	 * @param fail_penalty the fail_penalty to set
	 */
	public void setFailPenalty(int fail_penalty) {
		this.fail_penalty = fail_penalty;
	}
		

}
