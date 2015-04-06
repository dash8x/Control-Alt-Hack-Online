/**
 * Runnable to run client threads
 * - Keeps listening for incoming requests from the client
 * - Perform requested operation using the controller
 * - Responds back to the client with the result of the requested operation
 * - Addded the ability to edit the current total credits and GPA of student
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import grp.ctrlalthack.model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class ServerService implements Runnable {
		
	private Socket connection; 	//socket for connection
    private ObjectOutputStream output; //output stream
    private ObjectInputStream input; //input stream        
    private String ip; //client IP
    private Server server; //the server that opened this thread
    private Player player; //the player object for the client
    private int client_id; //id of the client
    
    private LinkedList<String> data_updated; //flag to check if data has updated since last request
	
    /**
     * Constructor
     */
    public ServerService(Server server, Socket connection) {
    	this.server = server;    	
    	this.connection = connection;    
    	this.data_updated = new LinkedList<String>();
    	//get IP as a String a remove leading /
    	this.ip = this.connection.getRemoteSocketAddress().toString().replaceFirst("\\/", "");
    	//get streams
    	try {
    		this.output = new ObjectOutputStream(this.connection.getOutputStream());
    		this.output.flush(); 
    		this.input = new ObjectInputStream(this.connection.getInputStream());    		
    		this.server.showSuccessLog( "New client connection", this );    		    		
        } catch ( IOException ioException ) {
        	this.server.showErrorLog( "Cannot obtain client streams", this );
        } 
    }
    
    /**
	 * Sends a response to the client
	 * @param reply
	 */
	public void sendResponse( Response reply ) {
		try {			
			this.output.writeObject(reply); 
	        this.output.flush();
	        this.output.reset();
	        if ( reply.getKeyword().equals("FAIL") ) {
	        	this.server.showErrorLog( reply.toString(), this );
	        } else if ( !reply.getKeyword().equals("DATA UPDATED") ) { //don't log data updated responses
	        	this.server.showResponseLog( reply.toString(), this );
	        }
		} catch ( IOException ioException ) {
			this.server.showErrorLog( "Cannot send response to client " + reply.toString(), this );	         
	    } 
	}		
    
	/**
	 * Sets the updated flag
	 */
	public void setUpdated(String updated) {
		if ( updated != null && !updated.equals("") ) {
			this.data_updated.add(updated);		
			this.server.showSuccessLog("DATA UPDATED Signal received for " + updated , this);
		}
	}
	
	/**
	 * Returns the head of update queu
	 */
	public String getUpdated() {
		if (this.data_updated.isEmpty()) {
			return "";
		} else {
			return this.data_updated.remove();
		}
	}
	
	/**
	 * Returns the updated flag
	 */
	public boolean wasUpdated() {
		return !this.data_updated.isEmpty();
	}
	
	/**
	 * Returns the IP of the client
	 */
	public String getIP() {
		return this.ip;
	}
	
	/**
	 * Returns the id of the client
	 */
	public int getID() {
		return this.client_id;
	}
	
	/**
	 * Returns the player of the client
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Runs the server service
	 */
    @Override
	public void run() {
		try {
			Command request = getCommand(); //read the initial command
			if ( this.acceptPlayer(request) ) {
				do {
					request = getCommand();
					this.handleRequest(request); //perform the command
				} while (!request.getKeyword().equals("TERMINATE"));
				this.server.showSuccessLog("TERMINATE Connection terminated by client", this);
			} else {
				this.server.showErrorLog("Client connection rejected", this);
			}
		} catch (IOException e) {
			this.server.showErrorLog(e.getMessage(), this);
		} finally {
			try {
				this.input.close();
				this.output.close();
				this.connection.close();				
			} catch (IOException e) {
				this.server.showErrorLog("Cannot close connection", this);
			} finally {
				this.server.removeClient(this);
			}			
		}
	}

    /**
     * Validates the server password and verifies server is not full
     */
	private boolean acceptPlayer(Command request) {
		boolean accepted = false;
		//check for correct command
		if ( request.getKeyword().equals("INITIATE") ) {
			//first check if server is full
			if ( this.server.getNumClients() > this.server.getMaxPlayers() ) {
				//server full
				this.sendFail(request.getKeyword(), "Server is full.");
			} else {
				//validate password
				String pass = request.getParam("password", "").toString();
				if ( this.server.verifyPassord(pass) ) {
					//create player instance
					try {
						String player_name = request.getParam("player_name", "").toString();
						this.player = new Player(this.getIP(), player_name);
						this.server.getGame().addPlayer(this.getPlayer()); //add player to the game
						this.sendSuccess(request.getKeyword());					
						accepted = true;
					} catch (Exception e) {
						this.sendFail(request.getKeyword(), e.getMessage());
					}
				} else {
					this.sendFail(request.getKeyword(), "Invalid server password");
				}
			}			
		} else { //invalid request			
			this.sendFail(request.getKeyword(), "Client not initiated.");
		}
		return accepted;
	}

	/**
	 * reads a request from the client
	 */
	private Command getCommand() throws IOException {
		Command request = null;
		try {
			request = ( Command ) this.input.readObject();	
			if ( !request.getKeyword().equals("DATA UPDATED") ) { //don't log data updated requests
	    		this.server.showCommandLog(request.toString(), this);
	    	}
		} catch (ClassCastException | ClassNotFoundException e) { //unkown object received				
			this.server.showErrorLog("Unknown command object recieved from client", this); //display in the log
			this.sendError("Unsupported object"); //send error response
		}
		return request;
	}

    /**
     * Sends an error response to the client
     * @param message the error message
     */
	private void sendError(String message) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("message", message);
		this.sendResponse(new Response("ERROR", params));
	}
    
	/**
     * Sends a SUCCESS response to the client
     * @param message the success message
     */
	private void sendSuccess(String message) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("operation", message);
		this.sendResponse(new Response("SUCCESS", params));
	}
	
	/**
     * Sends a FAIL response to the client
     * @param message the success message
     */
	private void sendFail(String op, String message) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("operation", op);
		params.put("message", message);
		this.sendResponse(new Response("FAIL", params));
	}
		
	/**
     * Sends a DATA UPDATED response to the client
     * Notify the client whether data was changed since last request
     */
	private void sendDataUpdated() {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("updated", this.getUpdated());
		this.sendResponse(new Response("DATA UPDATED", params));
		//update the flag
		if ( this.wasUpdated() ) {
			this.setUpdated("");
		}
	}
	
    /**
     * Handles commands
     * @param request the Command
     */
    private void handleRequest(Command request) {    	
    	String keyword = request.getKeyword();    	
    	HashMap<String, Object> params = request.getParams();
    	switch(keyword) {
			case "DATA UPDATED":
				this.sendDataUpdated();
				break;
			case "TERMINATE":
				break;
			default:
				this.sendError("Unknown command");
    	}    	
    }

	/**
	 * Process GET STUDENT
	 */
	/*private void getStudent(String keyword, HashMap<String, Object> params) {
		Student stud = null;
		try {					
			stud = this.student_controller.getStudent((Integer)params.get("student_id"));					
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendStudent(stud);
	}*/

}
