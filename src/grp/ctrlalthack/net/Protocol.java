/**
 * Superclass for Command and Response objects
 * Defines the standard object for communicating between the client and server
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import java.io.Serializable;
import java.util.HashMap;


public abstract class Protocol implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6921245798473839523L;
	
	private String keyword; //the name of the command
	private HashMap<String,Object> params; //the command parameters
		
	/**
	 * Constructor
	 */
	public Protocol(String keyword, HashMap<String,Object> params) {
		this.setKeyword(keyword);
		this.setParams(params);
	}
		
	/**
	 * @return name of the command
	 */
	public String getKeyword() {
		return this.keyword;
	}
		
	/**	 
	 * @return command parameters map
	 */
	public HashMap<String,Object> getParams() {
		return this.params;
	}
	
	/**	
	 * @param param to return 
	 * @return command parameters map
	 */
	public Object getParam(String param) {		
		return this.getParam(param, null);
	}
	
	/**	
	 * @param param to return 
	 * @param param to fallback to
	 * @return command parameters map
	 */
	public Object getParam(String param, Object fallback) {		
		return (this.getParams() == null) ? fallback : this.getParams().get(param);
	}
			
	/**
	 * Sets the keyword name	 
	 */
	private void setKeyword(String keyword) {
		keyword = (keyword == null) ? "" : keyword.toUpperCase();
		if ( this.isValidKeyword(keyword) ) {
			this.keyword = keyword;
		} else {
			throw new IllegalArgumentException("Invalid Keyword");
		}
	}
	
	
	/**	 
	 * Sets the command parameters map
	 */
	private void setParams(HashMap<String,Object> params) {
		if ( this.isValidParams(this.getKeyword(), params) ) {
			this.params = params;
		} else {
			throw new IllegalArgumentException("Invalid Parameters");
		}
	}
				
	/**
	 * toString method
	 */
	public String toString() {
		String out = "";
		out = this.getKeyword() + " ";
		out += (this.getParams() == null) ? "" : this.getParams().toString();
		return out;							
	}
	
	/**
	 * Checks if is a valid keyword
	 */
	public abstract boolean isValidKeyword(String keyword);
	
	/**
	 * Checks if a given set of parameters are valid for a command string
	 * @param command string
	 * @param params hasmap
	 */
	public abstract boolean isValidParams(String command, HashMap<String,Object> params);
		
}
