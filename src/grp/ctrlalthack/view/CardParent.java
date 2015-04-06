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

public class CardParent extends JPanel implements ViewConstants {
		
	private Server server;
	private ClientService client;	
	private Thread server_thread;
	private Thread client_thread;
	
	//panels
	private CardPanel home_panel;
	private CardPanel start_server_panel;
	private CardPanel join_server_panel;
	private CardPanel server_lobby_panel;
		
	/**
	 * Constructor
	 */
	public CardParent() {
		super();
		
		this.setLayout(new CardLayout(0, 0));
		
		home_panel = new HomePanel(this);
		this.add(home_panel, HOME_PANEL);				
		
		start_server_panel = new StartServerPanel(this);
		this.add(start_server_panel, START_SERVER_PANEL);
		
		join_server_panel = new JoinServerPanel(this);
		this.add(join_server_panel, JOIN_SERVER_PANEL);
	}	
	
	/**
	 * Navigate to a given panel
	 */
	public void navigateTo(String panel_name) {
		CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, panel_name);
	}
	
	/**
	 * Gets a panel
	 */
//	public CardPanel getPanel(String panel_name) {
//		CardLayout cl = (CardLayout) this.getLayout();
//		this.getCom
//	}
	
	/**
	 * Returns current instance
	 */
	public CardParent getInstance() {
		return this;
	}
	
	/**
	 * @return the server
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * @return the client
	 */
	public ClientService getClient() {
		return client;
	}
	
	/**
	 * @param server the server to set
	 */
	public void setServer(Server set_server) {
		stopServer();
		server = set_server;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ClientService set_client) {
		stopClient();
		client = set_client;
	}
	
	/**
	 * Stops the client
	 */
	public void stopClient() {
		if ( getClient() != null ) {
			getClient().stop();
		}
	}
	
	/**
	 * Stops the server
	 */
	public void stopServer() {
		if ( getServer() != null ) {
			getServer().stop();
		}
	}
	
	/**
	 * runs the client
	 */
	public void runClient() {
		if ( getClient() != null ) {
			client_thread = new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
						getClient().runClient();
					} catch (Exception e) {						
						showError(e.getMessage());
					}
				}
			});	
			client_thread.start();
		}
	}
	
	/**
	 * runs the server
	 */
	public void runServer() {
		if ( getServer() != null ) {
			server_thread = new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
						getServer().runServer();
					} catch (Exception e) {
						showError(e.getMessage());
					}
				}
			});	
			server_thread.start();
		}
	}	
	
	/**
	 * Shows error
	 */
	public void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Updates the players list
	 */
	public void updatePlayers() {
		
		//this.getLayout()
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Creates the server lobby
	 */
	public void openServerLobby() {
		server_lobby_panel = new ServerLobbyPanel(this);
		this.add(server_lobby_panel, SERVER_LOBBY_PANEL);
		navigateTo(SERVER_LOBBY_PANEL);	
	}
}
