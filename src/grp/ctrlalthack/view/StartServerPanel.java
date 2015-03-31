/**
 * Main Home Panel
 *  
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.view;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

//public class StartServerPanel extends JPanel implements ViewConstants {
public class StartServerPanel extends CardPanel implements ViewConstants {
	
	private JTextField txt_server_password;	
	private JTextField txt_server_port;
	private JComboBox<Integer> cmb_num_players;
	private JTextField txt_host_player;
	private JButton btn_start_server;
	private JButton btn_cancel_start_server;
	
	/**
	 * @wbp.parser.constructor 
	 */
	public StartServerPanel() {
		this(new JPanel());
	}
	
	/**
	 * Create the panel.
	 */
	public StartServerPanel(JPanel cards) {
		//super();
		super(cards);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblStartServer = new JLabel("Start Server");
		GridBagConstraints gbc_lblStartServer = new GridBagConstraints();
		gbc_lblStartServer.gridwidth = 2;
		gbc_lblStartServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartServer.gridx = 2;
		gbc_lblStartServer.gridy = 1;
		add(lblStartServer, gbc_lblStartServer);
		
		JLabel lblServerName = new JLabel("Server Name:");
		GridBagConstraints gbc_lblServerName = new GridBagConstraints();
		gbc_lblServerName.anchor = GridBagConstraints.WEST;
		gbc_lblServerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerName.gridx = 2;
		gbc_lblServerName.gridy = 3;
		add(lblServerName, gbc_lblServerName);
		
		JTextField txt_server_name = new JTextField("");
		GridBagConstraints gbc_txt_server_name = new GridBagConstraints();
		gbc_txt_server_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_name.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_name.gridx = 3;
		gbc_txt_server_name.gridy = 3;
		add(txt_server_name, gbc_txt_server_name);
		
		JLabel lblServer = new JLabel("Server Password:");
		GridBagConstraints gbc_lblServer = new GridBagConstraints();
		gbc_lblServer.anchor = GridBagConstraints.WEST;
		gbc_lblServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblServer.gridx = 2;
		gbc_lblServer.gridy = 4;
		add(lblServer, gbc_lblServer);
		
		txt_server_password = new JTextField("");
		GridBagConstraints gbc_txt_server_password = new GridBagConstraints();
		gbc_txt_server_password.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_password.gridx = 3;
		gbc_txt_server_password.gridy = 4;
		add(txt_server_password, gbc_txt_server_password);
		
		JLabel lblPort = new JLabel("Port:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.WEST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 5;
		add(lblPort, gbc_lblPort);
		
		txt_server_port = new JTextField("");
		GridBagConstraints gbc_txt_server_port = new GridBagConstraints();
		gbc_txt_server_port.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_port.gridx = 3;
		gbc_txt_server_port.gridy = 5;
		add(txt_server_port, gbc_txt_server_port);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of Players:");
		GridBagConstraints gbc_lblNumberOfPlayers = new GridBagConstraints();
		gbc_lblNumberOfPlayers.anchor = GridBagConstraints.WEST;
		gbc_lblNumberOfPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfPlayers.gridx = 2;
		gbc_lblNumberOfPlayers.gridy = 6;
		add(lblNumberOfPlayers, gbc_lblNumberOfPlayers);
		
		cmb_num_players = new JComboBox<Integer>();
		cmb_num_players.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {3, 4, 5, 6}));
		GridBagConstraints gbc_cmb_num_players = new GridBagConstraints();
		gbc_cmb_num_players.insets = new Insets(0, 0, 5, 5);
		gbc_cmb_num_players.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmb_num_players.gridx = 3;
		gbc_cmb_num_players.gridy = 6;
		add(cmb_num_players, gbc_cmb_num_players);
		
		JLabel lblHostPlayerName = new JLabel("Host Player Name:");
		GridBagConstraints gbc_lblHostPlayerName = new GridBagConstraints();
		gbc_lblHostPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblHostPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblHostPlayerName.gridx = 2;
		gbc_lblHostPlayerName.gridy = 7;
		add(lblHostPlayerName, gbc_lblHostPlayerName);
		
		txt_host_player = new JTextField("");
		GridBagConstraints gbc_txt_host_player = new GridBagConstraints();
		gbc_txt_host_player.insets = new Insets(0, 0, 5, 5);
		gbc_txt_host_player.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_host_player.gridx = 3;
		gbc_txt_host_player.gridy = 7;
		add(txt_host_player, gbc_txt_host_player);
		
		btn_start_server = new JButton("Start Server");
		GridBagConstraints gbc_btn_start_server = new GridBagConstraints();
		gbc_btn_start_server.anchor = GridBagConstraints.WEST;
		gbc_btn_start_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_start_server.gridx = 2;
		gbc_btn_start_server.gridy = 9;
		add(btn_start_server, gbc_btn_start_server);
		
		btn_cancel_start_server = new JButton("Cancel");
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
		
	}

}
