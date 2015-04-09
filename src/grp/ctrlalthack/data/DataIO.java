/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.data;

import grp.ctrlalthack.model.GameConstants;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.entropy.BoTAutoSuccessCard;
import grp.ctrlalthack.model.entropy.BoTFreeRerollCard;
import grp.ctrlalthack.model.entropy.BoTSkillModCard;
import grp.ctrlalthack.model.entropy.SkillModifier;
import grp.ctrlalthack.model.mission.MissionCard;
import grp.ctrlalthack.model.mission.MissionFailure;
import grp.ctrlalthack.model.mission.MissionSuccess;
import grp.ctrlalthack.model.mission.MissionTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DataIO implements GameConstants {
	
	//constants
	private static final String RESOURCES = "/resources";
	private static final String CARDS = "/cards";
	private static final String ENTROPY_CARDS = CARDS + "/entropy_cards";
	
	/**
	 * reads hacker cards
	 */
	public static ArrayList<HackerCard> readHackerCards() {
		ArrayList<HackerCard> cards = new ArrayList<HackerCard>(); 
		JSONObject json_obj = null;
		try {
			String contents = readFile(CARDS + "/character_cards.json");
			JSONArray json_array = new JSONArray(contents);			
			for ( int i = 0; i < json_array.length(); i++ ) {
				json_obj = (JSONObject) json_array.get(i);
				
				//fields
				String name = json_obj.getString("name");
				int kitchen_sink = json_obj.getInt("kitchen_sink");
				String desc = json_obj.getString("desc");
				
				//get the skill set
				HashMap<String, Integer> skills = new HashMap<String, Integer>();
				JSONArray skills_json = json_obj.getJSONArray("skills");
				for ( int x = 0; x < skills_json.length(); x++ ) {
					JSONObject skill_obj = (JSONObject) skills_json.get(x);
					String skill = skill_obj.getString("skill");
					int val = skill_obj.optInt("value", INFINITE_SKILL);
					skills.put(skill, val);
				}
				
				//create the card object
				HackerCard card = new HackerCard(name, desc, skills, kitchen_sink);
				cards.add(card);
			}
		} catch (JSONException e) {		
			System.out.println(json_obj);
			e.printStackTrace();
		}	
		return cards;
	}
	
	/**
	 * reads BoT skill modifier cards
	 */
	public static ArrayList<BoTSkillModCard> readBoTSkillModCards() {
		ArrayList<BoTSkillModCard> cards = new ArrayList<BoTSkillModCard>(); 
		JSONObject json_obj = null;
		try {
			String contents = readFile(ENTROPY_CARDS + "/bag_of_tricks_skill_mod.json");
			JSONArray json_array = new JSONArray(contents);			
			for ( int i = 0; i < json_array.length(); i++ ) {
				json_obj = (JSONObject) json_array.get(i);
				
				//fields
				String title = json_obj.getString("card_title");
				String desc = json_obj.getString("card_desc");
				int cost = json_obj.getInt("cost");				
				
				//get the skill modifiers
				ArrayList<SkillModifier> skills_mods = new ArrayList<SkillModifier>();
				JSONArray skills_json = json_obj.getJSONArray("skills");
				for ( int x = 0; x < skills_json.length(); x++ ) {
					JSONObject skill_obj = (JSONObject) skills_json.get(x);
					String skill = skill_obj.getString("skill");
					int val = skill_obj.getInt("mod");
					
					SkillModifier skill_mod = new SkillModifier(skill, val);					
					skills_mods.add(skill_mod);
				}
				
				//create the card object
				BoTSkillModCard card = new BoTSkillModCard(title, desc, cost, skills_mods);
				cards.add(card);
			}
		} catch (JSONException e) {		
			System.out.println(json_obj);
			e.printStackTrace();
		}	
		return cards;
	}
	
	/**
	 * reads BoT skill modifier cards
	 */
	public static ArrayList<BoTFreeRerollCard> readBoTFreeRerollCards() {
		ArrayList<BoTFreeRerollCard> cards = new ArrayList<BoTFreeRerollCard>(); 
		JSONObject json_obj = null;
		try {
			String contents = readFile(ENTROPY_CARDS + "/bag_of_tricks_reroll.json");
			JSONArray json_array = new JSONArray(contents);			
			for ( int i = 0; i < json_array.length(); i++ ) {
				json_obj = (JSONObject) json_array.get(i);
				
				//fields
				String title = json_obj.getString("card_title");
				String desc = json_obj.getString("card_desc");
				int cost = json_obj.getInt("cost");				
				
				//get the skills
				ArrayList<String> skills = new ArrayList<String>();
				JSONArray skills_json = json_obj.getJSONArray("skills");
				for ( int x = 0; x < skills_json.length(); x++ ) {
					JSONObject skill_obj = (JSONObject) skills_json.get(x);
					String skill = skill_obj.getString("skill");													
					skills.add(skill);
				}
				
				//create the card object
				BoTFreeRerollCard card = new BoTFreeRerollCard(title, desc, cost, skills);
				cards.add(card);
			}
		} catch (JSONException e) {		
			System.out.println(json_obj);
			e.printStackTrace();
		}	
		return cards;
	}
	
	/**
	 * reads BoT skill modifier cards
	 */
	public static ArrayList<BoTAutoSuccessCard> readBoTAutoSuccessCards() {
		ArrayList<BoTAutoSuccessCard> cards = new ArrayList<BoTAutoSuccessCard>(); 
		JSONObject json_obj = null;
		try {
			String contents = readFile(ENTROPY_CARDS + "/bag_of_tricks_auto_success.json");
			JSONArray json_array = new JSONArray(contents);			
			for ( int i = 0; i < json_array.length(); i++ ) {
				json_obj = (JSONObject) json_array.get(i);
				
				//fields
				String title = json_obj.getString("card_title");
				String desc = json_obj.getString("card_desc");
				int cost = json_obj.getInt("cost");				
				String skill = json_obj.getString("skill");
				
				//create the card object
				BoTAutoSuccessCard card = new BoTAutoSuccessCard(title, desc, cost, skill);
				cards.add(card);
			}
		} catch (JSONException e) {		
			System.out.println(json_obj);
			e.printStackTrace();
		}	
		return cards;
	}
	
	/**
	 * reads mission cards
	 */
	public static ArrayList<MissionCard> readMissionCards() {
		ArrayList<MissionCard> cards = new ArrayList<MissionCard>(); 
		JSONObject json_obj = null;
		try {
			String contents = readFile(CARDS + "/mission_cards.json");
			JSONArray json_array = new JSONArray(contents);			
			for ( int i = 0; i < json_array.length(); i++ ) {
				json_obj = (JSONObject) json_array.get(i);
				
				//fields
				boolean newb = json_obj.optBoolean("newb_mission", false);
				String title = json_obj.getString("mission_title");				
				String desc = json_obj.getString("mission_desc");
				
				//get the tasks
				ArrayList<MissionTask> tasks = new ArrayList<MissionTask>();
				JSONArray tasks_json = json_obj.getJSONArray("tasks");
				for ( int x = 0; x < tasks_json.length(); x++ ) {
					JSONObject task_obj = (JSONObject) tasks_json.get(x);
					String t_desc = task_obj.getString("desc");
					String skill = task_obj.getString("skill");					
					int skill_mod = task_obj.optInt("mod", 0);

					//alt skill
					JSONObject alt_obj = task_obj.optJSONObject("alt");
					String alt_skill = null;
					int alt_mod = 0;
					if (alt_obj != null) {
						alt_skill = alt_obj.optString("skill", null);
						alt_mod = alt_obj.optInt("mod", 0);
					}
					
					//creat task
					MissionTask task = new MissionTask(t_desc, skill, skill_mod, alt_skill, alt_mod);
					tasks.add(task);
				}
				
				//get success
				JSONObject success_obj = json_obj.getJSONObject("success");
				boolean success_all = success_obj.optBoolean("all", false);
				int success_cred = success_obj.optInt("hacker_cred", 0);
				int success_cash = success_obj.optInt("cash", 0);
				int success_entropy = success_obj.optInt("entropy_card", 0);
				String success_desc = success_obj.optString("desc", "");
				
				//create success object
				MissionSuccess success = new MissionSuccess(success_desc, success_cred, success_cash, success_entropy, success_all);
				
				//get failure
				JSONObject failure_obj = json_obj.getJSONObject("failure");
				boolean failure_all = failure_obj.optBoolean("all", false);
				int failure_cred = failure_obj.optInt("hacker_cred", 0);
				int failure_cash = failure_obj.optInt("cash", 0);
				int failure_entropy = failure_obj.optInt("entropy_card", 0);
				String failure_desc = failure_obj.optString("desc", "");
				
				//create failure object
				MissionFailure failure = new MissionFailure(failure_desc, failure_cred, failure_cash, failure_entropy, failure_all);
				
				//create the card object
				MissionCard card = new MissionCard(title, desc, tasks, success, failure, newb);
				cards.add(card);
			}
		} catch (JSONException e) {		
			System.out.println(json_obj);
			e.printStackTrace();
		}	
		return cards;
	}
	
	/**
	 * Gets a string from file contents
	 */
	public static String readFile(String file_name) {						
		String contents = "";
		Scanner file_scanner = null;
		try {	
			InputStream stream = DataIO.class.getResourceAsStream(file_name);
			file_scanner = new Scanner(stream);
			while ( file_scanner.hasNextLine() ) {
				contents += file_scanner.nextLine() + "\n";
			}			
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {	
			if ( file_scanner != null )
				file_scanner.close();
		}
		return contents;
	}

}
