/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.data;

import grp.ctrlalthack.model.GameConstants;
import grp.ctrlalthack.model.HackerCard;

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
	
	/**
	 * Constructor
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
