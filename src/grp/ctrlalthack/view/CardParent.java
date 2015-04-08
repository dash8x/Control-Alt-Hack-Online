/**
 * Abstract class for card panels
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */


package grp.ctrlalthack.view;

import grp.ctrlalthack.model.Player;
import grp.ctrlalthack.net.ClientService;
import grp.ctrlalthack.net.Server;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class CardParent extends JPanel implements ViewConstants {
	
	private byte ui_mode;
	
	private Server server;
	private ClientService client;	
	private Thread server_thread;
	private Thread client_thread;
	
	//panels
	private CardPanel home_panel;
	private StartServerPanel start_server_panel;
	private JoinServerPanel join_server_panel;
	private ServerLobbyPanel server_lobby_panel;
		
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
		
		//set to client mode by default
		this.setMode(CLIENT_MODE);
	}	
	
	/**
	 * Sets the GUI mode
	 */
	private void setMode(byte mode) {
		this.ui_mode = mode;
	}

	/**
	 * Checks if is in server mode 
	 */
	public boolean inServerMode() {
		return this.ui_mode == SERVER_MODE;
	}
	
	/**
	 * Checks if is in client mode 
	 */
	public boolean inClientMode() {
		return this.ui_mode == CLIENT_MODE;
	}
	
	
	/**
	 * Navigate to a given panel
	 */
	public void navigateTo(String panel_name) {
		CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, panel_name);
	}	
	
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
	private void setServer(Server set_server) {
		stopServer();
		server = set_server;
	}

	/**
	 * @param client the client to set
	 */
	private void setClient(ClientService set_client) {
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
		//set back to client mode
		setMode(CLIENT_MODE);
	}
	
	/**
	 * runs the client
	 */
	public void runClient(String server_ip, int server_port, String server_password, String player_name) {
		setClient(new ClientService(this, server_ip, server_port, server_password, player_name));
		if ( getClient() != null ) {
			client_thread = new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
						getClient().runClient();
					} catch (Exception e) {									
						showError(e.getMessage());
						navigateTo(HOME_PANEL);
					}
				}
			});	
			client_thread.start();
		}		
	}
	
	/**
	 * runs the server
	 */
	public void runServer(int server_port, int max_players, String password, String server_name) {
		setServer(new Server(server_port, max_players, password, server_name));
		if ( getServer() != null ) {
			server_thread = new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
						getServer().runServer();
					} catch (Exception e) {						
						showError(e.getMessage());
						navigateTo(HOME_PANEL);
					}
				}
			});	
			server_thread.start();
		}
		setMode(SERVER_MODE); //set to server mode
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
		if ( this.server_lobby_panel != null ) {
			try {
				ArrayList<Player> players = this.getClient().getPlayers();
				this.server_lobby_panel.updatePlayers(players);
			} catch ( Exception e ) {
				showError(e.getMessage());
			}
		}
	}
	
	/**
	 * Read to start
	 */
	public void readyToStart() {
		try {
			this.getClient().readyToStart();			
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}	
	
	/**
	 * Starts the game
	 */
	public void startGame() {
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
