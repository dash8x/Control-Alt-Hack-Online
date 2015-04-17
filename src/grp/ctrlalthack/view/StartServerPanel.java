/**
 * Displays the Start Server Screen
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.net.ClientService;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StartServerPanel extends CardPanel implements ViewConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -455218529248508555L;
	
	private JTextField txt_server_password;	
	private JTextField txt_server_port;
	private JComboBox<Integer> cmb_num_players;
	private JTextField txt_host_player;
	private JButton btn_start_server;
	private JButton btn_cancel_start_server;
	private JTextField txt_server_name;	
		
	/**
	 * Create the panel.
	 */
	public StartServerPanel(CardParent cards) {
		//super();
		super(cards);
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblStartServer = new JLabel("Start Server");
		lblStartServer.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblStartServer = new GridBagConstraints();
		gbc_lblStartServer.gridwidth = 2;
		gbc_lblStartServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartServer.gridx = 2;
		gbc_lblStartServer.gridy = 1;
		add(lblStartServer, gbc_lblStartServer);
		
		JLabel lblServerName = new JLabel("Server Name:");
		lblServerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblServerName = new GridBagConstraints();
		gbc_lblServerName.anchor = GridBagConstraints.WEST;
		gbc_lblServerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerName.gridx = 2;
		gbc_lblServerName.gridy = 3;
		add(lblServerName, gbc_lblServerName);
		
		txt_server_name = new JTextField("");
		txt_server_name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_server_name = new GridBagConstraints();
		gbc_txt_server_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_name.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_name.gridx = 3;
		gbc_txt_server_name.gridy = 3;
		add(txt_server_name, gbc_txt_server_name);
		
		JLabel lblServer = new JLabel("Server Password:");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblServer = new GridBagConstraints();
		gbc_lblServer.anchor = GridBagConstraints.WEST;
		gbc_lblServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblServer.gridx = 2;
		gbc_lblServer.gridy = 4;
		add(lblServer, gbc_lblServer);
		
		txt_server_password = new JTextField("");
		txt_server_password.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_server_password = new GridBagConstraints();
		gbc_txt_server_password.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_password.gridx = 3;
		gbc_txt_server_password.gridy = 4;
		add(txt_server_password, gbc_txt_server_password);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.WEST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 5;
		add(lblPort, gbc_lblPort);
		
		txt_server_port = new JTextField(Integer.toString(ClientService.DEFAULT_SERVER_PORT));
		txt_server_port.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_server_port = new GridBagConstraints();
		gbc_txt_server_port.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_port.gridx = 3;
		gbc_txt_server_port.gridy = 5;
		add(txt_server_port, gbc_txt_server_port);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players:");
		lblNumberOfPlayers.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNumberOfPlayers = new GridBagConstraints();
		gbc_lblNumberOfPlayers.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfPlayers.gridx = 2;
		gbc_lblNumberOfPlayers.gridy = 6;
		add(lblNumberOfPlayers, gbc_lblNumberOfPlayers);
		
		cmb_num_players = new JComboBox<Integer>();
		cmb_num_players.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmb_num_players.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {3, 4, 5, 6}));
		GridBagConstraints gbc_cmb_num_players = new GridBagConstraints();
		gbc_cmb_num_players.insets = new Insets(0, 0, 5, 5);
		gbc_cmb_num_players.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_num_players.gridx = 3;
		gbc_cmb_num_players.gridy = 6;
		add(cmb_num_players, gbc_cmb_num_players);
		
		JLabel lblHostPlayerName = new JLabel("Host Player Name:");
		lblHostPlayerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblHostPlayerName = new GridBagConstraints();
		gbc_lblHostPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblHostPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblHostPlayerName.gridx = 2;
		gbc_lblHostPlayerName.gridy = 7;
		add(lblHostPlayerName, gbc_lblHostPlayerName);
		
		txt_host_player = new JTextField("Player");
		txt_host_player.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_host_player = new GridBagConstraints();
		gbc_txt_host_player.insets = new Insets(0, 0, 5, 5);
		gbc_txt_host_player.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_host_player.gridx = 3;
		gbc_txt_host_player.gridy = 7;
		add(txt_host_player, gbc_txt_host_player);
		
		btn_start_server = new JButton("Start Server");
		btn_start_server.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_start_server = new GridBagConstraints();
		gbc_btn_start_server.anchor = GridBagConstraints.WEST;
		gbc_btn_start_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_start_server.gridx = 2;
		gbc_btn_start_server.gridy = 9;
		add(btn_start_server, gbc_btn_start_server);
		
		btn_cancel_start_server = new JButton("Cancel");
		btn_cancel_start_server.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_cancel_start_server = new GridBagConstraints();
		gbc_btn_cancel_start_server.anchor = GridBagConstraints.WEST;
		gbc_btn_cancel_start_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_cancel_start_server.gridx = 3;
		gbc_btn_cancel_start_server.gridy = 9;
		add(btn_cancel_start_server, gbc_btn_cancel_start_server);
		
		btn_cancel_start_server.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigateTo(HOME_PANEL);
			}
		});
		
		btn_start_server.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startServer();
			}
		});
		
	}
	
	/**
	 * Handler for Start Server button
	 */
	private void startServer() {
		//get all the values
		String server_name = this.txt_server_name.getText();
		String server_pass = this.txt_server_password.getText();
		int max_players = (int) this.cmb_num_players.getSelectedItem();	
		String host_player = this.txt_host_player.getText();
		
		int server_port;		
		try {
			server_port = getTextInt(this.txt_server_port);
		} catch (IllegalArgumentException e) {
			showError("Invalid Port Number!");
			return;
		}
		
		try {
			//start the server
			getParent().runServer(server_port, max_players, server_pass, server_name);			
			
			//start the client for the host player
			getParent().runClient("localhost", server_port, server_pass, host_player);						
		} catch (Exception e) {			
			stopClient();
			stopServer();
			showError(e.getMessage());
		}
	}
	
}
