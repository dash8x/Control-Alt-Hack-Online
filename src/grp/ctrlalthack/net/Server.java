/**
 * The main Server
 * - Initializes a ServerSocket
 * - Keeps listening to incoming connections
 * - Assigns threads to new connections
 * - Defines methods to broadcast to all clients
 * - Logs Commands, Responses and errors into the server GUI
 * - Implements locks for concurrent access from multiple client threads
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.net;

import grp.ctrlalthack.controller.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements NetworkConstants {
	
	private int server_port; 	//port to run server on	
	private ServerSocket server_socket; //serversocket
	private String password; //server password
	private String server_name; //server name
	private int max_players; //maximum number of players allowed
	private ExecutorService client_threads; //thread pool to manage client threads
	private ArrayList<ServerService> clients; //keeps track of all the clients
	private Game game; //the game object
	private boolean running; //flag to run the server
	
	private ReentrantLock logSynchronizer; //allows concurrent writes to the log
	private ReentrantLock updatedSynchronizer; //allows concurrent access to updated flag setter
	
	/**
	 * Constructor
	 */
	public Server(int server_port) {
		this(server_port, 6);
	}
	
	/**
	 * Constructor
	 */
	public Server(int server_port, int max_players) {
		this(server_port, max_players, ""); //blank password
	}
	
	/**
	 * Constructor
	 */
	public Server(int server_port, int max_players, String password) {
		this(server_port, 6, password, "");
	}	
	
	/**
	 * Constructor
	 */
	public Server(int server_port, int max_players, String password, String server_name) {
		this.setPort(server_port);
		this.setMaxPlayers(max_players);
		this.password = password;
		this.server_name = server_name;
		this.client_threads = Executors.newCachedThreadPool();
		this.clients = new ArrayList<ServerService>();
		this.game = new Game();
		this.logSynchronizer = new ReentrantLock();
		this.updatedSynchronizer = new ReentrantLock();
		/*this.lockSynchronizer = new ReentrantLock();		
		*/
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
	 * Sets the maximum number of players
	 */
	private void setMaxPlayers(int num_players) {
		if ( num_players < Game.MIN_PLAYERS || num_players > Game.MAX_PLAYERS ) { //max players should be between 3-6
    		throw new IllegalArgumentException("Maximum number of players should be between 3 and 6");
    	} 
		this.max_players = num_players;
	}
	
	/**
	 * Returns the max number of players allowed
	 */
	public int getMaxPlayers() {		
		return this.max_players;
	}
	
	/**
	 * Returns the current number of clients
	 */
	public int getNumClients() {		
		int num = 0;
		if ( this.clients != null ) {
			num = this.clients.size();
		}
		return num;
	}
	
	/**
	 * Verify password
	 */
	public boolean verifyPassord(String pass) {				
		return this.password.equals(pass);
	}	
	
	/**
	 * Checks if server is running
	 */
	public boolean isRunning() {
		return this.running;
	}
	
	/**
	 * Opens the Server Port
	 */
	private void openPort() {		
		try { //open the chosen port
			this.server_socket = new ServerSocket(this.server_port);
		} catch (IOException e) { //abort if cannot open the port
			this.showServerError("Cannot open port " + this.server_port);			
		}
	}
	
	/**
	 * Runs the server
	 */
	public void runServer() {
		try {			
			this.openPort(); //open the server port			
			this.running = true; //initialize flag
			this.displayLog("Waiting for connections...", GENERAL_MSG);
			while(this.isRunning()) {
				try {
					//new connection received
					ServerService new_client = new ServerService(this, this.server_socket.accept());
					this.addClient(new_client); //add to list of clients
					this.client_threads.execute(new_client); //run thread for new client
				} catch (SocketException e) { //something happened to the socket
					if (this.isRunning()) { //check if server was closed
						throw new IOException();
					}
				}
			}			
		} catch (IOException e) {			
			this.showServerError(e.getMessage());			
		} finally {
			this.stop();
		}
	}
	
	/**
	 * Stops the server
	 */
	public void stop() {
		if ( this.isRunning() ) {
			//send the terminate signal to all clients
			this.broadcast(new Response("TERMINATE", null));
			try { //close the server port
				this.server_socket.close();
			} catch (IOException e) { //show error if cannot close the port
				this.showServerError("Error closing the server");			
			}
			this.running = false; //close the flag
		}
	}
	
	/**
	 * Broadcasts response to all clients
	 */
	public void broadcast(Response reply) {
		if ( this.clients != null ) {
			for (ServerService client : this.clients) {
				client.sendResponse(reply);
			}
		}
	}	
	
	/**
	 * Adds a client from the list of clients
	 */
	public void addClient(ServerService client) {
		this.clients.add(client);
	}
	
	/**
	 * Removes a client from the list of clients
	 */
	public void removeClient(ServerService client) {
		this.game.removePlayer(client.getPlayer());
		this.clients.remove(client);
		this.setAllUpdated(FLAG_PLAYERS); //notify others the players list was updated
	}	
	
	/**
	 * Sets the updated flag of all clients
	 */
	public void setAllUpdated(String updated) {
		this.updatedSynchronizer.lock();
		try {
			for (ServerService client : this.clients) {
				client.setUpdated(updated);
			}
		} finally {
			this.updatedSynchronizer.unlock();
		}
	}	
	
	/**
	 * Returns the game object
	 */
	public Game getGame() {
		return this.game;
	}
	
	//logging
	
	/**
	 * Displays a server error
	 */
	public void showServerError(String message) {
		this.displayLog("Server Error! " + message, ERROR_MSG);
	}
	
	/**
	 * Displays a command message in the server GUI log
	 */
	public void showCommandLog(String message, ServerService client) {
		this.displayClientLog(message, "COMMAND", client);
	}
	
	/**
	 * Displays a response message in the server GUI log
	 */
	public void showResponseLog(String message, ServerService client) {
		this.displayClientLog(message, "RESPONSE", client);
	}
	
	/**
	 * Displays an error message in the server GUI log
	 */
	public void showErrorLog(String message, ServerService client) {
		this.displayClientLog("ERROR " + message, "ERROR", client);
	}		
	
	/**
	 * Displays a success message in the server GUI log
	 */
	public void showSuccessLog(String message, ServerService client) {
		this.displayClientLog(message, "SUCCESS", client);
	}
	
	/**
	 * Displays a message in the server GUI log
	 */
	public void displayLog(String message, String msg_type) {
		this.logSynchronizer.lock();
		try {
			System.out.println(msg_type + ": " + message);
		} finally {
			this.logSynchronizer.unlock();
		}
	}
	
	/**
	 * Displays a message from a client in the server GUI log
	 */
	public void displayClientLog(String message, String msg_type, ServerService client) {
		this.logSynchronizer.lock();
		try {
			this.displayLog(client.getIP() + ": " + message, msg_type);
		} finally {
			this.logSynchronizer.unlock();
		}
	}
	
	/*						
	private ReentrantLock lockSynchronizer; //allows concurrent access to the lock methods		
		
	*//**
	 * Locks a student record to a given client
	 *//*
	public void lock(Integer record, ServerService client) {
		this.lockSynchronizer.lock();
		try {
			//first check if already locked by someone else		
			checkLocked(record, client);
			//check if student exist
			this.student_controller.getStudent(record);
			this.student_record_locks.put(record, client);
		} finally {
			this.lockSynchronizer.unlock();
		}
	}
	
	*//**
	 * Raises an exception if student record is locked
	 *//*
	public void checkLocked(Integer record, ServerService client) {
		if ( this.isLocked(record, client) ) {
			throw new LockedException("Specified student is being edited by another user.");
		}
	}
	
	*//**
	 * Checks if a student record is locked
	 *//*
	public boolean isLocked(Integer record, ServerService client) {
		//first check if already locked by someone else
		ServerService owner = this.student_record_locks.get(record);
		return ( owner != null && !owner.equals(client) );
	}
	
	*//**
	 * Unlocks a given student record
	 *//*
	public void unlock(Integer record) {
		this.lockSynchronizer.lock();
		try {
			if ( this.student_record_locks.remove(record) == null ) { //if not present in locks, check if student exists
				this.student_controller.getStudent(record);
			}
		} finally {
			this.lockSynchronizer.unlock();
		}
	}
	
	*//**
	 * Unlocks all locks owned by a client
	 *//*
	public void unlockAll(ServerService client) {
		this.lockSynchronizer.lock();
		try {
			//iterate over all the locks
			Iterator<Map.Entry<Integer,ServerService>> it = this.student_record_locks.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<Integer,ServerService> pairs = it.next();
		        if(pairs.getValue().equals(client)) { //remove if client is the owner
		        	it.remove(); //using an iterator to remove avoids a ConcurrentModificationException
		        }
		    }
		} finally {
			this.lockSynchronizer.unlock();
		}
	}		
	
	*//**
	 * Method to add client to list of clients in the server GUI
	 * @param ip of the client
	 *//*
	public void guiAddClient(String ip) {
		this.server_gui.addClient(ip);
	}

	*//**
	 * Method to remove a client from list of clients in the server GUI
	 * @param ip of the client
	 *//*
	public void guiRemoveClient(String ip) {
		this.server_gui.removeClient(ip);
	}
	
	*//**
	 * Initializes the GUI and displays initial info
	 *//*
	private void initGUI() {
		this.server_gui = new ServerView(); //initialize the GUI						
		//stop when GUI closed
		this.server_gui.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {		        
		    	stop();
		    }
		});
		this.server_gui.setPort(this.apr_port); //display the port number
		this.server_gui.displayLog("Waiting for connections...");
	}						
	

*/}
