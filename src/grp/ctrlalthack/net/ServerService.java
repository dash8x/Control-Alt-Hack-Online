/**
 * Runnable to run client threads
 * - Keeps listening for incoming requests from the client
 * - Perform requested operation using the controller
 * - Responds back to the client with the result of the requested operation
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import grp.ctrlalthack.controller.Game;
import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.Player;
import grp.ctrlalthack.model.Trade;
import grp.ctrlalthack.model.entropy.EntropyCard;
import grp.ctrlalthack.model.mission.MissionCard;
import grp.ctrlalthack.model.mission.MissionTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class ServerService implements Runnable, NetworkConstants, ProtocolConstants {
		
	private Socket connection; 	//socket for connection
    private ObjectOutputStream output; //output stream
    private ObjectInputStream input; //input stream        
    private String ip; //client IP
    private Server server; //the server that opened this thread
    private Player player; //the player object for the client
    private int client_id; //id of the client
    
    private LinkedList<String> data_updated = new LinkedList<String>(); //flag to check if data has updated since last request
    private LinkedList<Message> messages = new LinkedList<Message>(); //messages for the client
	
    private ReentrantLock addFlagSynchronizer = new ReentrantLock();
    private ReentrantLock addMessageSynchronizer = new ReentrantLock();
    
    /**
     * Constructor
     */
    public ServerService(Server server, Socket connection) {
    	this.server = server;    	
    	this.connection = connection;        	
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
	        if ( reply.getKeyword().equals(RESP_FAIL) ) {
	        	this.server.showErrorLog( reply.toString(), this );
	        } else if ( !reply.getKeyword().equals(RESP_CHECK_UPDATED) ) { //don't log data updated responses
	        	this.server.showResponseLog( reply.toString(), this );
	        }
		} catch ( IOException ioException ) {
			this.server.showErrorLog( "Cannot send response to client " + reply.toString(), this );	         
	    } 
	}		
    
	/**
	 * Adds a message
	 */
	public void addMessage(String message) {
		if ( message != null && !message.equals("") ) {
			this.addMessage(new Message(message));
		}
	}
	
	/**
	 * Adds a message
	 */
	public void addMessage(Message message) {
		this.addMessageSynchronizer.lock();
		try {			
			if ( message != null ) {
				this.messages.add(message);		
				this.server.showSuccessLog("MESSAGE Signal received for " + message , this);
			}
		} finally {
			this.addMessageSynchronizer.unlock();
		}
	}
	
	/**
	 * Returns the head of messages queu
	 */
	public Message getMessage() {
		if (this.messages.isEmpty()) {
			return new Message("");
		} else {
			return this.messages.remove();
		}
	}
	
	/**
	 * Sets the updated flag
	 */
	public void setUpdated(String updated) {
		this.addFlagSynchronizer.lock();
		try {
			if ( updated != null && !updated.equals("") ) {
				this.data_updated.add(updated);		
				this.server.showSuccessLog(CMD_CHECK_UPDATED + " Signal received for " + updated , this);
			}
		} finally {
			this.addFlagSynchronizer.unlock();
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
				} while (!request.getKeyword().equals(CMD_TERMINATE));
				this.server.showSuccessLog(CMD_TERMINATE + " Connection terminated by client", this);
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
		if ( request.getKeyword().equals(CMD_INITIATE) ) {
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
						this.server.setAllUpdated(FLAG_PLAYERS); //notify others the players list was updated
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
	 * Wrapper method
	 */
	private void setAllUpdated(String msg) {
		this.server.setAllUpdated(msg);
	}
	
	/**
	 * Wrapper method
	 */
	private void broadcastMessage(Message msg) {
		this.server.broadcastMessage(msg);
	}
	
	/**
	 * Wrapper method
	 */
	private void broadcastMessage(String msg) {
		this.server.broadcastMessage(msg);
	}
	
	/**
	 * reads a request from the client
	 */
	private Command getCommand() throws IOException {
		Command request = null;
		try {
			request = ( Command ) this.input.readObject();	
			if ( !request.getKeyword().equals(CMD_CHECK_UPDATED) ) { //don't log data updated requests
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
		this.sendResponse(new Response(RESP_ERROR, params));
	}
    
	/**
     * Sends a SUCCESS response to the client
     * @param message the success message
     */
	private void sendSuccess(String message) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("operation", message);
		this.sendResponse(new Response(RESP_SUCCESS, params));
	}
	
	/**
     * Sends a FAIL response to the client
     * @param message the success message
     */
	private void sendFail(String op, String message) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("operation", op);
		params.put("message", "" + message);
		this.sendResponse(new Response(RESP_FAIL, params));
	}
		
	/**
     * Sends a DATA UPDATED response to the client
     * Notify the client whether data was changed since last request
     */
	private void sendDataUpdated() {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("updated", this.getUpdated());
		this.sendResponse(new Response(RESP_CHECK_UPDATED, params));		
	}
	
	/**
     * Sends a MESSAGE to the client
     * Notify the client whether data was changed since last request
     */
	private void sendMessage() {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("message", this.getMessage());
		this.sendResponse(new Response(RESP_MESSAGE, params));		
	}
	
	/**
     * Sends a PLAYERS response to the client
     * @param courses
     */
	private void sendPlayers(ArrayList<Player> players) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("players", players);
		this.sendResponse(new Response(RESP_PLAYERS, params));
	}
	
	/**
     * Sends a CHARACTER_CHOICES response to the client
     * @param courses
     */
	private void sendCharacterChoices(HackerCard[] cards) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("characters", cards);
		this.sendResponse(new Response(RESP_CHARACTER_CHOICES, params));
	}
	
	/**
     * Sends a GAME_STATS response to the client
     * @param courses
     */
	private void sendGameStats(GameStats stats) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("stats", stats);
		this.sendResponse(new Response(RESP_GAME_STATS, params));
	}
	
	/**
     * Sends a MISSION response to the client
     * @param courses
     */
	private void sendMission(MissionCard card) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("mission", card);
		this.sendResponse(new Response(RESP_MISSION, params));
	}
	
	/**
     * Sends a PLAYER response to the client
     * @param courses
     */
	private void sendPlayer(Player player) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("player", player);
		this.sendResponse(new Response(RESP_PLAYER, params));
	}
	
	/**
     * Sends a CHECK_TURN response to the client
     * @param courses
     */
	private void sendCheckTurn(boolean turn) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("your_turn", turn);
		this.sendResponse(new Response(RESP_CHECK_TURN, params));
	}
	
	/**
     * Sends a ROLL_TASK response
     * @param courses
     */
	private void sendRollTask(boolean success) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("result", success);
		this.sendResponse(new Response(RESP_ROLL_TASK, params));
	}
	
	/**
     * Sends a TRADE response
     * @param courses
     */
	private void sendTrade(Trade trade) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("trade", trade);
		this.sendResponse(new Response(RESP_TRADE, params));
	}
	
	
    /**
     * Handles commands
     * @param request the Command
     */
    private void handleRequest(Command request) {    	
    	String keyword = request.getKeyword();    	
    	HashMap<String, Object> params = request.getParams();
    	switch(keyword) {
	    	case CMD_READY_TO_START:
	    		readyToStart(keyword, params);
				break;
	    	case CMD_START_GAME:
	    		handleStartGame(keyword, params);
				break;
	    	case CMD_GET_CHARACTER_CHOICES:
	    		getCharacterChoices(keyword, params);
				break;
	    	case CMD_SELECT_CHARACTER:
	    		chooseCharacter(keyword, params);
	    		break;
	    	case CMD_BUY_BAG_OF_TRICKS:
	    		buyBagOfTricks(keyword, params);
	    		break;
	    	case CMD_TRADE:
	    		trade(keyword, params);
	    		break;
	    	case CMD_GET_INCOMING_OFFER:
	    		getIncomingOffer(keyword, params);
				break;
	    	case CMD_RESPOND_OFFER:
	    		respondToOffer(keyword, params);
	    		break;
	    	case CMD_GET_PLAYERS:
	    		getPlayers(keyword, params);
				break;
	    	case CMD_ATTEND:
	    		attend(keyword, params);
				break;
	    	case CMD_GET_CURR_MISSION:
	    		getCurrMission(keyword, params);
				break;
	    	case CMD_GET_CURR_PLAYER:
	    		getCurrPlayer(keyword, params);
				break;
	    	case CMD_GET_MY_PLAYER:
	    		getMyPlayer(keyword, params);
				break;
	    	case CMD_CHECK_TURN:
	    		checkTurn(keyword, params);
				break;
	    	case CMD_ROLL_TASK:
	    		rollTask(keyword, params);
				break;
	    	case CMD_GET_GAME_STATS:
	    		getGameStats(keyword, params);
				break;
	    	case CMD_GET_MESSAGE:
				this.sendMessage();
				break;
			case CMD_CHECK_UPDATED:
				this.sendDataUpdated();
				break;
			case CMD_TERMINATE:
				break;
			default:
				this.sendError("Unknown command");
    	}    	
    }
    
    /**
     * Check if it's the player's turn
     */
    private void rollTask(String keyword, HashMap<String, Object> params) {
    	if ( !this.server.getGame().inPlaying() ) {
    		this.sendFail(keyword, "No mission is being played right now");
    		return;    		
    	}
    	boolean success = false;
    	try {
			MissionTask task = this.getPlayer().getTask();
			int dice = Game.rollDice();
			success = this.getPlayer().playTask(dice);
			String roll_text = this.getPlayer().getPlayerName() + " rolled " + dice + " on the " + task.getTitle() + " roll.";
			if ( success ) {
				this.broadcastMessage(new Message(roll_text + " " + this.getPlayer().getPlayerName() + " succeeded the task.", Message.CONTEXT_ROLL));								
			} else {				
				this.broadcastMessage(new Message(roll_text + " " + this.getPlayer().getPlayerName() + " failed the task.", Message.CONTEXT_ROLL));
				//check for fee rerolls
				if ( this.getPlayer().hasFreeReroll() || this.getPlayer().hasFreeReroll(task.getSkill()) || (task.hasAltSkill() && this.getPlayer().hasFreeReroll(task.getAltSkill())) ) {
					this.broadcastMessage(new Message(this.getPlayer().getPlayerName() + " rerolling.", Message.CONTEXT_ROLL));
				} else {
					EntropyCard auto_success_card = null;
					auto_success_card = this.getPlayer().getBoTAutoSuccess(task.getSkill());
					if ( auto_success_card == null && task.hasAltSkill() ) {
						auto_success_card = this.getPlayer().getBoTAutoSuccess(task.getAltSkill());
					}					
					if ( auto_success_card != null ) {
						this.server.getGame().addEntropyCard(auto_success_card);
						this.setAllUpdated(FLAG_PLAYERS);
						this.broadcastMessage(new Message(this.getPlayer().getPlayerName() + " used autosuccess card.", Message.CONTEXT_ROLL));						
						success = true;
					} else {	
						this.getPlayer().setSucceeded(false);
						this.player.applyFailure();
						//remove entropy cards
						for ( int i = 0; i < this.getPlayer().getMission().getFailure().getEntropyCards(); i++ ) {
							this.server.getGame().addEntropyCard(this.getPlayer().removeRandomEntropyCard());
						}
						this.setAllUpdated(FLAG_PLAYERS);
						this.setAllUpdated(FLAG_GAME_STATS);
						this.broadcastMessage(new Message(this.getPlayer().getPlayerName() + " failed the mission.", Message.CONTEXT_ROLL));
						//end the turn
						if ( this.server.getGame().nextPlayer() ) {
							//inform next player	
							this.setAllUpdated(FLAG_NEW_TURN);
						} else {
							this.newRound();
						}
					}
				}
			}
			if ( success ) {
				this.getPlayer().nextTask();				
				//check if was last task
				if ( this.getPlayer().isLastTask() ) {
					this.getPlayer().setSucceeded(true);
					this.player.applySuccess();
					//add entropy cards
					for ( int i = 0; i < this.getPlayer().getMission().getSuccess().getEntropyCards(); i++ ) {
						this.getPlayer().addEntropyCard(this.server.getGame().getEntropyCard());
					}
					this.setAllUpdated(FLAG_PLAYERS);
					this.setAllUpdated(FLAG_GAME_STATS);
					this.broadcastMessage(new Message(this.getPlayer().getPlayerName() + " succeeded the mission.", Message.CONTEXT_ROLL));
					//end the turn
					if ( this.server.getGame().nextPlayer() ) {
						//inform next player	
						this.setAllUpdated(FLAG_NEW_TURN);						
					} else {
						this.newRound();
					}
				}
			}
			
		} catch (Exception e) {			
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendRollTask(success);
	}

    /**
     * New round
     */
    private void newRound() {
    	this.server.newRound();
    }
    
	/**
     * Sends the current mission card
     */
    private void getCurrMission(String keyword, HashMap<String, Object> params) {
    	MissionCard card = null;
		try {	
			card = this.server.getGame().getCurrentPlayer().getMission();									
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendMission(card);
	}

    /**
     * Sends the current mission card
     */
    private void getCurrPlayer(String keyword, HashMap<String, Object> params) {
    	Player player = null;
		try {	
			player = this.server.getGame().getCurrentPlayer();									
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendPlayer(player);
	}
    
    /**
     * Sends the current mission card
     */
    private void getMyPlayer(String keyword, HashMap<String, Object> params) {
    	Player player = null;
		try {	
			player = this.getPlayer();									
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendPlayer(player);
	}
    
	/**
     * Check turn
     */
    private void checkTurn(String keyword, HashMap<String, Object> params) {
    	boolean turn = false;
		try {	
			turn = this.getPlayer().equals(this.server.getGame().getCurrentPlayer());				
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendCheckTurn(turn);
	}
    
    /**
     * Get game stats
     */
    private void getGameStats(String keyword, HashMap<String, Object> params) {
    	GameStats stats = null;
		try {	
			stats = this.server.getGame().getGameStats();									
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendGameStats(stats);
	}

	/**
     * Choose a character
     */
    private void chooseCharacter(String keyword, HashMap<String, Object> params) {
    	if ( this.server.getGame().inChooseCharacter() ) {
			try {
				int id = (Integer) params.get("character");
				this.getPlayer().chooseCharacter(id);
				this.sendSuccess("Character chosen");
				this.setAllUpdated(FLAG_GAME_STATS);
				this.setAllUpdated(FLAG_PLAYERS);
				String msg = this.getPlayer().getPlayerName() + " chose " + this.getPlayer().getCharacter().getName();
				this.broadcastMessage(new Message(msg, Message.CONTEXT_CHARACTER_CHOOSE));
				//check if all players finished choosing
				if ( this.server.getGame().allCharactersChosen() ) {
					this.newRound();
				}
			} catch ( Exception e ) {				
				this.sendFail(keyword, e.getMessage());
				return;
			}
		} else {
			this.sendFail(keyword, "Game not in character choosing stage.");
		}
	}
    
    /**
     * attend
     */
    private void attend(String keyword, HashMap<String, Object> params) {
    	if ( this.server.getGame().inAttendance() ) {
			try {
				this.getPlayer().setAttending(true);
				this.getPlayer().setFreeReroll(false);
				this.sendSuccess("You are attending");				
				this.setAllUpdated(FLAG_PLAYERS);
				this.broadcastMessage(this.getPlayer().getPlayerName() + " chose to attend");
			} catch ( Exception e ) {				
				this.sendFail(keyword, e.getMessage());
				return;
			}
		} else {
			this.sendFail(keyword, "Game not in attendance stage.");
		}
	}
    
    /**
     * trade
     */
    private void trade(String keyword, HashMap<String, Object> params) {
    	Trade t = (Trade) params.get("trade");
    	Player other_player = this.server.getGame().getPlayer(t.getPlayerID());
    	if ( !this.server.getGame().inMeeting() ) { 
    		this.sendFail(keyword, "Game not in meeting stage.");
    	} else if ( !this.getPlayer().isAttending() ) {
    		this.sendFail(keyword, "You need to be attending the Staff Video Conference to make trades.");			
    	} else if ( this.getPlayer().getPlayerID() == t.getPlayerID() ) {
    		this.sendFail(keyword, "You cannot trade with yourself.");			
    	} else if ( other_player == null ) {
    		this.sendFail(keyword, "Cannot find specified player.");			
    	} else if ( !other_player.isAttending() ) {
    		this.sendFail(keyword, "You can only trade with attending players.");			
    	} else if ( this.getPlayer().hasPendingTrades() ) {
    		this.sendFail(keyword, "Cannot make offer. You have pending trades that need to be addressed first.");			
    	} else if ( this.getPlayer().getCash() < t.getCash() ) {
    		this.sendFail(keyword, "Cannot make offer. You do not have enough cash.");			
    	} else {
    		try {				
				other_player.setIncomingTrade(new Trade(this.getPlayer().getPlayerID(), t.getCash(), this.getPlayer().getPlayerName()));
				this.getPlayer().setOutgoingTrade(t);				
				this.sendSuccess("Offer made.");
				this.setAllUpdated(FLAG_NEW_OFFER);
				this.broadcastMessage(this.getPlayer().getPlayerName() + " made an offer of $" + t.getCash() + " to " + other_player.getPlayerName());
			} catch ( Exception e ) {				
				this.sendFail(keyword, e.getMessage());
				return;
			}		
		}
	}
    
    /**
     * respond to offer
     */
    private void respondToOffer(String keyword, HashMap<String, Object> params) {    	    	
    	if ( !this.server.getGame().inMeeting() ) { 
    		this.sendFail(keyword, "Game not in meeting stage.");
    	} else if ( !this.getPlayer().isAttending() ) {
    		this.sendFail(keyword, "You need to be attending the Staff Video Conference to make trades.");			
    	} else if ( this.getPlayer().getIncomingTrade() == null ) {
    		this.sendFail(keyword, "You don't have any incoming offers.");			
    	} else {
    		try {	
    			Trade t = this.getPlayer().getIncomingTrade();
    			boolean accept = (Boolean) params.get("accept");
    			Player other_player = this.server.getGame().getPlayer(t.getPlayerID());
    			this.getPlayer().setIncomingTrade(null);
    			other_player.setOutgoingTrade(null);
    			if ( accept ) {
    				other_player.removeCash(t.getCash());
    				this.getPlayer().addCash(t.getCash());
    				MissionCard other_mission = other_player.getMission();
    				other_player.setMission(this.getPlayer().getMission());
    				this.getPlayer().setMission(other_mission);
    				this.sendSuccess("Offer accepted.");
    				this.broadcastMessage(this.getPlayer().getPlayerName() + " accepted " + other_player.getPlayerName() + "'s offer of $" + t.getCash() );
    			} else {
    				this.sendSuccess("Offer rejected.");
    				this.broadcastMessage(this.getPlayer().getPlayerName() + " rejected " + other_player.getPlayerName() + "'s offer of $" + t.getCash() );
    			}				
				this.setAllUpdated(FLAG_PLAYERS);				
			} catch ( Exception e ) {				
				this.sendFail(keyword, e.getMessage());
				return;
			}		
		}
	}
    
    /**
     * returns incoming offer
     */
    private void getIncomingOffer(String keyword, HashMap<String, Object> params) {    	    	    	
    	Trade t = null;
    	if ( !this.server.getGame().inMeeting() ) {     		
    		this.sendFail(keyword, "Game not in meeting stage.");
    		return;
    	} else {
    		try {	
    			t = this.getPlayer().getIncomingTrade();				
			} catch ( Exception e ) {				
				this.sendFail(keyword, e.getMessage());
				return;
			}		
		}
    	this.sendTrade(t);
	}
    
    /**
     * Choose a character
     */
    private void buyBagOfTricks(String keyword, HashMap<String, Object> params) {
    	if ( this.server.getGame().hasStarted() ) {
			try {
				int id = (Integer) params.get("card");
				this.getPlayer().buyBagOfTricks(id);
				this.sendSuccess("Bag of Tricks card bought");			
				this.setAllUpdated(FLAG_PLAYERS);
				this.setAllUpdated(FLAG_GAME_STATS);
			} catch ( Exception e ) {
				this.sendFail(keyword, e.getMessage());
				return;
			}
		} else {
			this.sendFail(keyword, "Game has not started.");
		}
	}

	/**
     * Character choices
     */
    private void getCharacterChoices(String keyword, HashMap<String, Object> params) {
		if ( this.server.getGame().inChooseCharacter() ) {
			try {
				this.sendCharacterChoices(this.getPlayer().getCharacterChoices());
			} catch ( Exception e ) {
				this.sendFail(keyword, e.getMessage());
				return;
			}
		} else {
			this.sendFail(keyword, "Game not in character choosing stage.");
		}
	}

	/**
     * start game
     */
    private void handleStartGame(String keyword, HashMap<String, Object> params) {
		if ( !this.getPlayer().isHost() ) {
			this.sendFail(keyword, "Only the host can start the game.");
		} else {
			try {
				this.server.getGame().startGame();
				this.sendSuccess("Starting the game now");
				setAllUpdated(FLAG_CHOOSE_CHARACTERS);
			} catch ( Exception e ) {
				this.sendFail(keyword, e.getMessage());
				return;
			}
		}
	}

	/**
	 * Process READY_TO_START
	 */
	private void readyToStart(String keyword, HashMap<String, Object> params) {		
		try {	
			this.getPlayer().setReadyToStart(true);									
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendSuccess("Status set to ready");
		setAllUpdated(FLAG_PLAYERS);
	}
	
    /**
	 * Process GET PLAYERS
	 */
	private void getPlayers(String keyword, HashMap<String, Object> params) {
		ArrayList<Player> players = null;
		try {	
			players = this.server.getGame().getPlayers();									
		} catch (Exception e) {
			this.sendFail(keyword, e.getMessage());
			return;
		}
		this.sendPlayers(players);
	}


}
