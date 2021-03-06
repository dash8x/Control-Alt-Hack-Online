/**
 * Sends requests from the Client GUI to the server and receives back responses
 * The Client GUI interacts with this class to communicate with the server
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.Player;
import grp.ctrlalthack.model.Trade;
import grp.ctrlalthack.model.mission.MissionCard;
import grp.ctrlalthack.net.exception.ClientException;
import grp.ctrlalthack.net.exception.ErrorResponseException;
import grp.ctrlalthack.net.exception.FailResponseException;
import grp.ctrlalthack.net.exception.InvalidResponseException;
import grp.ctrlalthack.net.exception.NoResponseException;
import grp.ctrlalthack.view.CardParent;

public class ClientService implements NetworkConstants, ProtocolConstants {
	
	public static final int DEFAULT_SERVER_PORT = 1234;
	
	private String server_ip; //server ip
	private int server_port; //port number of the server
	private Socket client; //socket for connection
	private String player_name; //name of the player
	private String server_password; //server password
	private CardParent client_ui;
	//object streams
	private ObjectOutputStream output;
	private ObjectInputStream input;		
	private boolean running; 	//flag to keep class running	
	private ReentrantLock sendResponseCommandLock; //lock for concurrent access to send/receive data from server
		
	//constant for DATA UPDATED Command
	public static final Command DATA_UPDATED_CHECKER = new Command(CMD_CHECK_UPDATED, null);
	
	
	/**
	 * Constructor
	 */
	public ClientService(CardParent client_ui, String server_ip, int server_port, String server_password, String player_name) {
		this.client_ui = client_ui;
		this.server_ip = server_ip;
		this.setPort(server_port);
		this.setPlayerName(player_name);
		this.server_password = server_password;
		this.sendResponseCommandLock = new ReentrantLock();		
	}	
	
	/**
	 * @return the player_name
	 */
	public String getPlayerName() {
		return this.player_name;
	}
	
	/**
	 * set the player_name
	 */
	private void setPlayerName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("Player name cannot be empty");
		} else {
			this.player_name = name;
		}
	}	

	/**
	 * Sets the server port
	 */
	private void setPort(int port) {
		if ( port < 1 || port > 65535 ) { //invalid port number
    		throw new IllegalArgumentException("Invalid Port Number");
    	} 
		this.server_port = port;
	}
	
	/**
	 * Runs the client
	 */
	public void runClient() {		
		//run if only if not running
		if ( !this.isRunning() ) { 
			//connect to the server
			boolean connected = false;
			try {
				this.client = new Socket(this.server_ip, this.server_port);
				this.input = new ObjectInputStream(this.client.getInputStream());
				this.output = new ObjectOutputStream(this.client.getOutputStream());
				this.output.flush();
				connected = true;
			} catch (IOException e) { //abort if connection cannot be initialized				
				this.closeConnection();
				throw new ClientException("Error connecting to server");							
			}
			
			//run the client
			if ( connected ) {
				try {
					// initialize flag
					this.running = this.wasAccepted();
					// initialize update listener, checks for new updates in the data
					Thread bacgkround_thread = new Thread(new UpdateListener());
					bacgkround_thread.start();
					//move to the lobby
					this.client_ui.openServerLobby();
					this.client_ui.updatePlayers();
					// wait until GUI is closed
					while (this.isRunning()) {
		
					}
				} finally {
					if (this.isRunning()) { // close connections if they were not closed
						this.stop();
					}
				}
			}
		}		
	}
	
	/**
	 * Runnable to poll the server for update signals in the background
	 */
	private class UpdateListener implements Runnable {
		@Override
		public void run() {			
			while(isRunning()) {
				try {
					String updated = wasUpdated();
					if (!updated.equals("")) {
						handleUpdate(updated);
					}					
					Thread.sleep(500); //run every 500ms
				} catch (InterruptedException e) {				
				} catch (NoResponseException e) {				
				} catch (Exception e) {
					client_ui.endGame(e.getMessage());
				}
			}
		}	
	}
	
	/**
	 * Handle update	  
	 */
	private void handleUpdate(String updated) {
		switch (updated) {
			case FLAG_MESSAGE:
				client_ui.showMessage();
				break;
			case FLAG_NEW_ROUND:
				client_ui.newRound();
				break;
			case FLAG_ATTENDANCE:
				client_ui.setToAttendance();
				break;
			case FLAG_MEETING:
				client_ui.setToMeeting();
				break;
			case FLAG_NEW_TURN:
				client_ui.newTurn();
				break;
			case FLAG_NEW_OFFER:
				client_ui.newOffer();
				break;
			case FLAG_PLAYERS:
				client_ui.updatePlayers();
				break;
			case FLAG_GAME_STATS:
				client_ui.updateGameStats();
				break;
			case FLAG_CHOOSE_CHARACTERS:
				client_ui.openChooseCharacter();
				break;
		}			
	}	
	
	/**
	 * Closes the connection to the server
	 */
	private void closeConnection() {
		try {			
			//close the connections
			this.input.close();			
			this.output.close();
			this.client.close();			
		} catch (NullPointerException | SocketException e) { //abort on any error
			return;
		} catch (IOException e) {			
			throw new ClientException("Error closing connection with server");			
		} finally {
			this.running = false;
		}
	}
			
	/**
	 * Checks if server is running
	 */
	public boolean isRunning() {
		return this.running;
	}
	
	/**
	 * Sets the running flag to false and close the connections
	 */
	public void stop() {
		if ( this.isRunning() ) {
			//notify the server that the connection is being closed
			this.sendCommand(new Command(CMD_TERMINATE,null));
			this.closeConnection();
			this.running = false;
		}
	}	
	
	/**
	 * Check if the connection was accepted
	 */
	public boolean wasAccepted() {
		//build the params map
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("password", this.server_password);
		params.put("player_name", this.getPlayerName());
		Command command = new Command(CMD_INITIATE, params);
		//send the command to the server
		Response reply = this.sendResponseCommand(command);
		//display error if operation wasn't successful
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {
			throw new InvalidResponseException("Invalid response received from server for " + command.getKeyword());
		}
		return true;
	}
	
	/**
	 * Check if data was updated since last request
	 */
	public String wasUpdated() {
		//send commad to server and get back response
		Response reply = this.sendResponseCommand(DATA_UPDATED_CHECKER);		
				
		//initialize output value
		String was_updated = "";
				
		//get the result from the response
		if ( reply.getKeyword().equals(RESP_CHECK_UPDATED) ) {
			was_updated = (String) reply.getParam("updated");
		} else {
			throw new InvalidResponseException("Invalid response received from server for DATA UPDATED");
		}
				
		return was_updated;
	}
		
	/**
	 * Sends a command to the server and get a response back
	 * @param request
	 */
	private Response sendResponseCommand( Command request ) {
		this.sendResponseCommandLock.lock();
		Response reply = null;
		try {
			this.sendCommand(request);		
			reply = this.getResponse();
			if (reply == null) {
				throw new NoResponseException();
			} else if ( reply.getKeyword().equals(RESP_ERROR) ) {
				String msg = (String)reply.getParam("message");
				throw new ErrorResponseException(msg);
			} else if ( reply.getKeyword().equals(RESP_FAIL) ) {
				String msg = (String)reply.getParam("message");
				throw new FailResponseException(msg);
			}
		} finally {
			this.sendResponseCommandLock.unlock();
		}
		return reply;
	}		
	
	/**
	 * Sends a command to the server
	 * @param request
	 */
	private void sendCommand( Command request ) {
		try {
			this.output.writeObject(request); 
	        this.output.flush();
	        this.output.reset();
		} catch ( SocketException e ) { //give up if can't talk to server
			if (this.isRunning()) {
				this.closeConnection();
				throw new ClientException("Connection to server lost.\nThis client will now close");								
			}
		} catch ( IOException e ) {			
			throw new ClientException("Error sending request to server\n" + request.toString() + "\n" + e.getMessage());					
	    }
	}
	
	/**
	 * Gets a response from the server
	 */
	private Response getResponse() {			
		Response reply = null;
		try {
			reply = ( Response ) this.input.readObject();
			if ( reply.getKeyword().equals(RESP_TERMINATE) ) {
				throw new ClientException("The server has closed the connection.\nThis client will now close");											
			} 
		} catch ( SocketException e ) { //give up if can't talk to server
			if (this.isRunning()) {
				throw new ClientException("Connection to server lost.\nThis client will now close");								
			}
		} catch ( IOException e ) {
        	throw new ClientException("Error reading response from server\n" + e.getMessage());        
        } catch ( ClassCastException | ClassNotFoundException e ) {
        	throw new ClientException("Unknown response received from server");        	
        } catch ( ClientException e ) {
        	this.closeConnection();
        	throw e;
        }
		return reply;
	}
	
	/**
	 * Displays an error message	
	 */
	/*public void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Network Error!", JOptionPane.ERROR_MESSAGE);
	}*/
	
	/**
	 * set ready to start
	 */	
	public void readyToStart() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_READY_TO_START, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_READY_TO_START);
		}						
	}
	
	/**
	 * send start game command
	 */	
	public void startGame() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_START_GAME, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_READY_TO_START);
		}						
	}
	
	/**
	 * send attend command
	 */	
	public void attend() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_ATTEND, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_ATTEND);
		}						
	}
	
	/**
	 * get character choices
	 */	
	public HackerCard[] getCharacterChoices() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_CHARACTER_CHOICES, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_CHARACTER_CHOICES) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_GET_CHARACTER_CHOICES);
		}	
		
		return (HackerCard[]) reply.getParam("characters");
	}
	
	/**
	 * gets current mission card
	 */	
	public MissionCard getCurrMission() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_CURR_MISSION, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_MISSION) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_GET_CURR_MISSION);
		}	
		
		return (MissionCard) reply.getParam("mission");
	}
	
	/**
	 * gets current player
	 */	
	public Player getCurrPlayer() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_CURR_PLAYER, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_PLAYER) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_GET_CURR_PLAYER);
		}	
		
		return (Player) reply.getParam("player");
	}
	
	/**
	 * gets my player
	 */	
	public Player getMyPlayer() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_MY_PLAYER, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_PLAYER) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_GET_MY_PLAYER);
		}	
		
		return (Player) reply.getParam("player");
	}
	
	/**
	 * check if my turn
	 */	
	public boolean isMyTurn() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_CHECK_TURN, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_CHECK_TURN) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_CHECK_TURN);
		}	
		
		return (Boolean) reply.getParam("your_turn");
	}
	
	/**
	 * check if my turn
	 */	
	public boolean rollTask() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_ROLL_TASK, null));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_ROLL_TASK) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_ROLL_TASK);
		}	
		
		return (Boolean) reply.getParam("result");
	}
	
	/**
	 * choose character
	 */	
	public void chooseCharacter(int i) {
		//build params
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("character", i);
		
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_SELECT_CHARACTER, params));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_SELECT_CHARACTER);
		}	
		
	}
	
	/**
	 * trade
	 */	
	public void trade(Trade t) {
		//build params
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("trade", t);
		
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_TRADE, params));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_TRADE);
		}	
		
	}
	
	/**
	 * respond to offer
	 */	
	public void respondOffer(boolean accept) {
		//build params
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("accept", accept);
		
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_RESPOND_OFFER, params));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_RESPOND_OFFER);
		}	
		
	}
	
	/**
	 * choose character
	 */	
	public void buyBagOfTricks(int i) {
		//build params
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("card", i);
		
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_BUY_BAG_OF_TRICKS, params));						
				
		//get the result from the response
		if ( !reply.getKeyword().equals(RESP_SUCCESS) ) {						
			throw new InvalidResponseException("Invalid response received from server for " + CMD_BUY_BAG_OF_TRICKS);
		}	
		
	}
	
	/**
	 * Gets a message from the server
	 */
	public Message getMessage() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_MESSAGE, null));		
				
		//initialize output array
		Message message = null;
				
		//get the result from the response
		if ( reply.getKeyword().equals(RESP_MESSAGE) ) {
			message = (Message) reply.getParam("message");
		} else {			
			throw new InvalidResponseException("Invalid response received from server for GET MESSAGE");
		}
				
		return message;
	}
	
	/**
	 * Gets an incoming offer from the server
	 */
	public Trade getIncomingOffer() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_INCOMING_OFFER, null));		
				
		//initialize output array
		Trade trade = null;
				
		//get the result from the response
		if ( reply.getKeyword().equals(RESP_TRADE) ) {
			trade = (Trade) reply.getParam("trade");
		} else {			
			throw new InvalidResponseException("Invalid response received from server for CMD_GET_INCOMING_OFFER");
		}
				
		return trade;
	}
	
	/**
	 * Gets game stats
	 */
	public GameStats getGameStats() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_GAME_STATS, null));							
				
		//get the result from the response
		if ( reply.getKeyword().equals(RESP_GAME_STATS) ) {
			return (GameStats) reply.getParam("stats");
		} else {			
			throw new InvalidResponseException("Invalid response received from server for GET MESSAGE");
		}

	}
	
	/**
	 * Returns the players array list
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Player> getPlayers() {
		//send the command to the server
		Response reply = this.sendResponseCommand(new Command(CMD_GET_PLAYERS, null));		
				
		//initialize output array
		ArrayList<Player> results = new ArrayList<Player>();
				
		//get the result from the response
		if ( reply.getKeyword().equals(RESP_PLAYERS) ) {
			results = (ArrayList<Player>) reply.getParam("players");
		} else {			
			throw new InvalidResponseException("Invalid response received from server for GET PLAYERS");
		}
				
		return results;
	}
}
