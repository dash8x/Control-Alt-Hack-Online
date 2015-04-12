/**
 * Abstract class for card panels
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */


package grp.ctrlalthack.view;

import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.Message;
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
	private ChooseCharacterPanel choose_character_panel;
	private GamePanel game_panel;

	private boolean game_open = false;
	
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
	 * Check if game is open
	 */
	public boolean isGameOpen() {
		return this.game_open && this.game_panel != null;
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
		try {			
			ArrayList<Player> players = this.getClient().getPlayers();					
			if ( this.isGameOpen() ) {
				this.game_panel.updatePlayers(players);
			} else if ( this.server_lobby_panel != null ) {
				this.server_lobby_panel.updatePlayers(players);	
			}
		} catch ( Exception e ) {
			showError(e.getMessage());
		}
	}
	
	/**
	 * Updates the players list
	 */
	public void updateGameStats() {
		if ( this.isGameOpen() ) {						
			try {
				GameStats stats = this.getClient().getGameStats();
				this.game_panel.updateGameStats(stats);				
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
		this.readyToStart();
		try {
			this.getClient().startGame();			
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
	
	/**
	 * Choose character
	 */
	public void chooseCharacter(int i) {		
		try {
			this.getClient().chooseCharacter(i);	
			openGamePanel();
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
	
	/**
	 * Shows a message from the server
	 */
	public void showMessage() {
		try {
			Message message = this.getClient().getMessage();		
			if ( message != null && !message.getMessage().equals("") ) {
				if ( isGameOpen() ) {
					this.game_panel.displayLog(message.getMessage(), message.getContext());
					//update main panel
					switch (message.getContext()) {
						case Message.CONTEXT_ROUND:
						case Message.CONTEXT_PHASE:
							this.game_panel.updateStatus(message.getMessage());
							break;
						
						case Message.CONTEXT_ROLL:
							this.game_panel.updateRollMessage(message.getMessage());
							break;
					}
				}
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
	
	
	/**
	 * Creates the server lobby
	 */
	public void openServerLobby() {
		server_lobby_panel = new ServerLobbyPanel(this);
		this.add(server_lobby_panel, SERVER_LOBBY_PANEL);
		navigateTo(SERVER_LOBBY_PANEL);	
	}
	
	/**
	 * Creates the choose character
	 */
	public void openChooseCharacter() {
		try {
			HackerCard[] cards = this.getClient().getCharacterChoices();		
			choose_character_panel = new ChooseCharacterPanel(this, cards);
			this.add(choose_character_panel, CHOOSE_CHARACTER_PANEL);
			navigateTo(CHOOSE_CHARACTER_PANEL);	
		} catch (Exception e) {
			showError(e.getMessage());
		}		
	}
	
	/**
	 * Creates the game panel
	 */
	public void openGamePanel() {
		game_panel = new GamePanel(this);
		this.add(game_panel, GAME_PANEL);
		game_panel.updateStatus("Waiting for all players to choose characters.");
		game_panel.updateRollMessage("");
		navigateTo(GAME_PANEL);	
		this.game_open  = true;
	}
}
