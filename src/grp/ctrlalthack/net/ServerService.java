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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ServerService implements Runnable {
		
	private Socket connection; 	//socket for connection
    private ObjectOutputStream output; //output stream
    private ObjectInputStream input; //input stream        
    private String ip; //client IP
    private Server server; //the server that opened this thread
    
    private boolean data_updated; //flag to check if data has updated since last request
	
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
	public void setUpdated(boolean updated) {
		this.data_updated = updated;
		if (updated) {
			this.server.showSuccessLog("DATA UPDATED Signal received", this);
		} else {
			this.server.showSuccessLog("RESPONSE DATA UPDATED Client notified", this);
		}
	}
	
	/**
	 * Returns the updated flag
	 */
	public boolean wasUpdated() {
		return this.data_updated;
	}
	
	
	/**
	 * Returns the IP of the client
	 */
	public String getIP() {
		return this.ip;
	}
	
	/**
	 * Runs the server service
	 */
    @Override
	public void run() {
		try {
			Command request = null;
			do {
				try {
					request = ( Command ) this.input.readObject();
					this.handleRequest(request); //perform the command
				} catch (ClassCastException | ClassNotFoundException e) { //unkown object received				
					this.server.showErrorLog("Unknown command object recieved from client", this); //display in the log
					this.sendError("Unsupported object"); //send error response
				}
			} while (!request.getKeyword().equals("TERMINATE"));
			this.server.showSuccessLog("TERMINATE Connection terminated by client", this);
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
     * Sends an error response to the client
     * @param message the error message
     */
	private void sendError(String message) {
		HashMap<String,Object> params = new HashMap<String,Object>(); 
		params.put("error", message);
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
		params.put("updated", this.wasUpdated());
		this.sendResponse(new Response("DATA UPDATED", params));
		//update the flag
		if ( this.wasUpdated() ) {
			this.setUpdated(false);
		}
	}
	
    /**
     * Handles commands
     * @param request the Command
     */
    private void handleRequest(Command request) {    	
    	String keyword = request.getKeyword();
    	if ( !keyword.equals("DATA UPDATED") ) { //don't log data updated requests
    		this.server.showCommandLog(request.toString(), this);
    	}
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
