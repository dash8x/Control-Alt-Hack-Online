/**
 * Abstract class for card panels
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */


package grp.ctrlalthack.view;

import grp.ctrlalthack.net.ClientService;
import grp.ctrlalthack.net.Server;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public abstract class CardPanel extends JPanel {
	
	private JPanel cards;
	private Server server;
	private ClientService client;	
	private Thread server_thread;
	private Thread client_thread;

	/**
	 * Constructor
	 */
	public CardPanel(JPanel cards) {
		super();
		this.cards = cards;
	}
	
	/**
	 * Get cards
	 */
	public JPanel getParent() {
		return this.cards;
	}
	
	/**
	 * Get cards layout
	 */
	public CardLayout getParentLayout() {
		return (CardLayout) this.cards.getLayout();
	}
	
	/**
	 * Navigate to a given panel
	 */
	public void navigateTo(String panel_name) {
		CardLayout cl = this.getParentLayout();
        cl.show(this.getParent(), panel_name);
	}
	
	/**
	 * Returns current instance
	 */
	public CardPanel getInstance() {
		return this;
	}
	
	/**
	 * @return the server
	 */
	public Server getServer() {
		return this.server;
	}

	/**
	 * @return the client
	 */
	public ClientService getClient() {
		return this.client;
	}
	
	/**
	 * @param server the server to set
	 */
	public void setServer(Server server) {
		//this.stopServer();
		this.server = server;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ClientService client) {
		this.stopClient();
		this.client = client;
	}
	
	/**
	 * Stops the client
	 */
	public void stopClient() {
		if ( this.getClient() != null ) {
			this.getClient().stop();
		}
	}
	
	/**
	 * Stops the server
	 */
	public void stopServer() {
		if ( this.getServer() != null ) {
			this.getServer().stop();
		}
	}
	
	/**
	 * runs the client
	 */
	public void runClient() {
		if ( this.getClient() != null ) {
			this.client_thread = new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
						getClient().runClient();
					} catch (Exception e) {						
						showError(e.getMessage());
					}
				}
			});	
			this.client_thread.start();
		}
	}
	
	/**
	 * runs the server
	 */
	public void runServer() {
		if ( this.getServer() != null ) {
			this.server_thread = new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
						getServer().runServer();
					} catch (Exception e) {
						showError(e.getMessage());
					}
				}
			});	
			this.server_thread.start();
		}
	}
	
	/**
	 * Method to get int from field
	 */
	public static int getTextInt(JTextComponent field) {
		String string_val = field.getText();		
		try {
			return Integer.parseInt(string_val);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Shows error
	 */
	public void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error!", JOptionPane.ERROR_MESSAGE);
	}
}
