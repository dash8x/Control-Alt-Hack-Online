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

public class Server {
	/*
	private int apr_port; 	//port to run server on	
	private ServerSocket server; //serversocket
	private ExecutorService client_threads; //thread pool to manage client threads
	private boolean running; //flag to run the server
	private Connection db; //the database connection	
	private ArrayList<ServerService> clients; //keeps track of all the clients		
	private ReentrantLock lockSynchronizer; //allows concurrent access to the lock methods
	private ReentrantLock logSynchronizer; //allows concurrent writes to the log
	private ReentrantLock updatedSynchronizer; //allows concurrent access to updated flag setter
	
	*//**
	 * Constructor
	 *//*
	public Server() {				
		this.client_threads = Executors.newCachedThreadPool();
		this.clients = new ArrayList<ServerService>();		
		this.lockSynchronizer = new ReentrantLock();
		this.logSynchronizer = new ReentrantLock();
		this.updatedSynchronizer = new ReentrantLock();
	}
	
	*//**
	 * Runs the server
	 *//*
	public void runServer() {
		try {			
			this.openPort(); //open the server port
			this.initGUI(); //initialize the GUI
			this.running = true; //initialize flag
			while(this.isRunning()) {
				try {
					//new connection received
					ServerService new_client = new ServerService(this, this.student_controller, this.server.accept());
					this.addClient(new_client); //add to list of clients
					this.client_threads.execute(new_client); //run thread for new client
				} catch (SocketException e) { //something happened to the socket
					if (this.isRunning()) { //check if server was closed
						throw new IOException();
					}
				}
			}			
		} catch (IOException e) {
			this.showServerError("Server error\n" + e.getMessage());			
		} finally {
			this.stop();
		}
	}
	
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
	 * Adds a client from the list of clients
	 *//*
	public void addClient(ServerService client) {
		this.clients.add(client);
	}
	
	*//**
	 * Removes a client from the list of clients
	 *//*
	public void removeClient(ServerService client) {
		this.clients.remove(client);
	}
	
	*//**
	 * Displays a message in the server GUI log
	 *//*
	public void displayLog(String message, String msg_type) {
		this.logSynchronizer.lock();
		try {
			this.server_gui.displayLog(message, msg_type);
		} finally {
			this.logSynchronizer.unlock();
		}
	}
	
	*//**
	 * Displays a message from a client in the server GUI log
	 *//*
	public void displayClientLog(String message, String msg_type, ServerService client) {
		this.logSynchronizer.lock();
		try {
			this.server_gui.displayClientLog(client.getIP(), message, msg_type);
		} finally {
			this.logSynchronizer.unlock();
		}
	}
	
	*//**
	 * Displays a command message in the server GUI log
	 *//*
	public void showCommandLog(String message, ServerService client) {
		this.displayClientLog(message, "COMMAND", client);
	}
	
	*//**
	 * Displays a response message in the server GUI log
	 *//*
	public void showResponseLog(String message, ServerService client) {
		this.displayClientLog(message, "RESPONSE", client);
	}
	
	*//**
	 * Displays an error message in the server GUI log
	 *//*
	public void showErrorLog(String message, ServerService client) {
		this.displayClientLog("ERROR " + message, "ERROR", client);
	}
	
	*//**
	 * Displays a server error
	 *//*
	public void showServerError(String message) {
		JOptionPane.showMessageDialog(null, message, "Server Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	*//**
	 * Displays a success message in the server GUI log
	 *//*
	public void showSuccessLog(String message, ServerService client) {
		this.displayClientLog(message, "SUCCESS", client);
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

	*//**
	 * Opens the Server Port
	 *//*
	private void openPort() {
		this.initPort(); //get the port number from the user
		try { //open the chosen port
			this.server = new ServerSocket(this.apr_port);
		} catch (IOException e) { //abort if cannot open the port
			this.showServerError("Cannot open port " + this.apr_port);
			System.exit(0);
		}
	}
	
	*//**
	 * Checks if server is running
	 *//*
	public boolean isRunning() {
		return this.running;
	}
	
	*//**
	 * Stops the server
	 *//*
	public void stop() {	
		this.running = false; //close the flag
		//send the terminate signal to all clients
		this.broadcast(new Response("TERMINATE", null));
		try { //close the server port
			this.server.close();
		} catch (IOException e) { //show error if cannot close the port
			this.showServerError("Error closing the server");			
		} finally { //close db connection
			DBConnection.closeDB(this.db);
		}
	}
	
	*//**
	 * Broadcasts response to all clients
	 *//*
	public void broadcast(Response reply) {
		for (ServerService client : this.clients) {
			client.sendResponse(reply);
		}
	}
	
	*//**
	 * Sets the updated flag of all clients
	 *//*
	public void setAllUpdated(boolean updated) {
		this.updatedSynchronizer.lock();
		try {
			for (ServerService client : this.clients) {
				client.setUpdated(updated);
			}
		} finally {
			this.updatedSynchronizer.unlock();
		}
	}
	
	*//**
	 * Initialize the server socket
	 *//*
	private void initPort() {
		//get the server IP and port number from the user		
		JTextField txt_port = new JTextField("8888");
		Object[] fields = {		    
		    "Port Number:", txt_port
		};
		int option = JOptionPane.showConfirmDialog(null, fields, "Server Details", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {		    
		    Integer port = -1;
		    try {
		    	port = Integer.parseInt(txt_port.getText());
		    	if ( port < 1 || port > 65535 ) { //invalid port number
		    		throw new NumberFormatException("Invalid Port Number");
		    	} else {
		    		this.apr_port = port;
		    	}
		    } catch (NumberFormatException e) {
		    	this.showServerError("Invalid Port Number");
		    	this.initPort();
		    }
		} else {
			System.exit(0);
		}
	}

*/}
