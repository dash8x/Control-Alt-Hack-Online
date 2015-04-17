/**
 * Displays Join Server Panel
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
import javax.swing.SwingConstants;

public class JoinServerPanel extends CardPanel implements ViewConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7961184287151143688L;
	
	private JTextField txt_server_password;	
	private JTextField txt_server_port;
	private JTextField txt_player_name;
	private JButton btn_join_server;
	private JButton btn_cancel_join_server;
	private JTextField txt_server_ip;
	
	/**
	 * Create the panel.
	 */
	public JoinServerPanel(CardParent cards) {		
		super(cards);
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 0, 0, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 30, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lbl_panel_title = new JLabel("Join Server");
		lbl_panel_title.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblStartServer = new GridBagConstraints();
		gbc_lblStartServer.gridwidth = 2;
		gbc_lblStartServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartServer.gridx = 2;
		gbc_lblStartServer.gridy = 1;
		add(lbl_panel_title, gbc_lblStartServer);
		
		JLabel lblServerName = new JLabel("Server IP:");
		lblServerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblServerName = new GridBagConstraints();
		gbc_lblServerName.anchor = GridBagConstraints.WEST;
		gbc_lblServerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerName.gridx = 2;
		gbc_lblServerName.gridy = 3;
		add(lblServerName, gbc_lblServerName);
		
		txt_server_ip = new JTextField("");
		txt_server_ip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_server_ip = new GridBagConstraints();
		gbc_txt_server_ip.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_ip.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_ip.gridx = 3;
		gbc_txt_server_ip.gridy = 3;
		add(txt_server_ip, gbc_txt_server_ip);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.anchor = GridBagConstraints.WEST;
		gbc_lblPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblPort.gridx = 2;
		gbc_lblPort.gridy = 4;
		add(lblPort, gbc_lblPort);
		
		txt_server_port = new JTextField(Integer.toString(ClientService.DEFAULT_SERVER_PORT));
		txt_server_port.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_server_port = new GridBagConstraints();
		gbc_txt_server_port.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_port.gridx = 3;
		gbc_txt_server_port.gridy = 4;
		add(txt_server_port, gbc_txt_server_port);
		
		JLabel lblServer = new JLabel("Server Password:");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblServer = new GridBagConstraints();
		gbc_lblServer.anchor = GridBagConstraints.WEST;
		gbc_lblServer.insets = new Insets(0, 0, 5, 5);
		gbc_lblServer.gridx = 2;
		gbc_lblServer.gridy = 5;
		add(lblServer, gbc_lblServer);
		
		txt_server_password = new JTextField("");
		txt_server_password.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_server_password = new GridBagConstraints();
		gbc_txt_server_password.insets = new Insets(0, 0, 5, 5);
		gbc_txt_server_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_server_password.gridx = 3;
		gbc_txt_server_password.gridy = 5;
		add(txt_server_password, gbc_txt_server_password);
		
		JLabel lblHostPlayerName = new JLabel("Player Name:");
		lblHostPlayerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblHostPlayerName = new GridBagConstraints();
		gbc_lblHostPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblHostPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblHostPlayerName.gridx = 2;
		gbc_lblHostPlayerName.gridy = 6;
		add(lblHostPlayerName, gbc_lblHostPlayerName);
		
		txt_player_name = new JTextField("Player");
		txt_player_name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_txt_player_name = new GridBagConstraints();
		gbc_txt_player_name.insets = new Insets(0, 0, 5, 5);
		gbc_txt_player_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_player_name.gridx = 3;
		gbc_txt_player_name.gridy = 6;
		add(txt_player_name, gbc_txt_player_name);
		
		btn_join_server = new JButton("Join Server");
		btn_join_server.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_join_server.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btn_join_server = new GridBagConstraints();
		gbc_btn_join_server.anchor = GridBagConstraints.WEST;
		gbc_btn_join_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_join_server.gridx = 2;
		gbc_btn_join_server.gridy = 8;
		add(btn_join_server, gbc_btn_join_server);
		
		btn_cancel_join_server = new JButton("Cancel");
		btn_cancel_join_server.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_cancel_start_server = new GridBagConstraints();
		gbc_btn_cancel_start_server.anchor = GridBagConstraints.WEST;
		gbc_btn_cancel_start_server.insets = new Insets(0, 0, 5, 5);
		gbc_btn_cancel_start_server.gridx = 3;
		gbc_btn_cancel_start_server.gridy = 8;
		add(btn_cancel_join_server, gbc_btn_cancel_start_server);
		
		btn_cancel_join_server.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigateTo(HOME_PANEL);
			}
		});
		
		btn_join_server.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doJoinServer();
			}
		});
		
	}

	/**
	 * 
	 */
	private void doJoinServer() {
		//get all the values
		String server_host = this.txt_server_ip.getText();
		String server_pass = this.txt_server_password.getText();		
		String player_name = this.txt_player_name.getText();

		int server_port;
		try {
			server_port = getTextInt(this.txt_server_port);
		} catch (IllegalArgumentException e) {
			showError("Invalid Port Number!");
			return;
		}
				
		try {			
			//create a client for the player
			getParent().runClient(server_host, server_port, server_pass, player_name);			
		} catch (Exception e) {			
			showError(e.getMessage());
		}
	}

}
