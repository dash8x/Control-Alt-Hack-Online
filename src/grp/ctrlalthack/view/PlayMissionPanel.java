/**
 * Display the play mission panel
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.model.mission.MissionCard;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class PlayMissionPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8097766342719091892L;

	//modes
	private boolean mission_mode = false;
		
	private MissionCard card;
	private MissionCardPanel mission_panel;
	private JTextArea lbl_roll;

	private JLabel lbl_status;
	private JLabel lbl_title;	
	
	/**
	 * Create the panel.
	 */
	public PlayMissionPanel(MissionCard card) {				
		this.card = card;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(204, 204, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 5, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lbl_title = new JLabel("New Round");
		lbl_title.setBackground(new Color(0, 204, 102));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setOpaque(true);
		lbl_title.setForeground(Color.WHITE);
		lbl_title.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_title.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_title = new GridBagConstraints();
		gbc_lbl_title.fill = GridBagConstraints.BOTH;
		gbc_lbl_title.gridwidth = 2;
		gbc_lbl_title.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_title.gridx = 0;
		gbc_lbl_title.gridy = 0;
		add(lbl_title, gbc_lbl_title);
		
		mission_panel = new MissionCardPanel(card);
		//JPanel mission_panel = new JPanel();
		GridBagConstraints gbc_mission_panel = new GridBagConstraints();
		gbc_mission_panel.gridheight = 3;
		gbc_mission_panel.insets = new Insets(0, 0, 0, 5);
		gbc_mission_panel.fill = GridBagConstraints.BOTH;
		gbc_mission_panel.gridx = 0;
		gbc_mission_panel.gridy = 1;
		add(mission_panel, gbc_mission_panel);
		
		lbl_status = new JLabel("Arushad playing");
		lbl_status.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_status.setForeground(new Color(0, 0, 0));
		lbl_status.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_status.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_status = new GridBagConstraints();
		gbc_lbl_status.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_status.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_status.gridx = 1;
		gbc_lbl_status.gridy = 1;
		add(lbl_status, gbc_lbl_status);
		
		lbl_roll = new JTextArea("You rolled 8");
		lbl_roll.setEditable(false);
		lbl_roll.setWrapStyleWord(true);
		lbl_roll.setLineWrap(true);
		lbl_roll.setOpaque(false);
		lbl_roll.setForeground(Color.BLACK);
		lbl_roll.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_roll.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_roll = new GridBagConstraints();
		gbc_lbl_roll.fill = GridBagConstraints.BOTH;
		gbc_lbl_roll.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_roll.gridx = 1;
		gbc_lbl_roll.gridy = 2;
		add(lbl_roll, gbc_lbl_roll);
		
		//fill in the values
		setMission(card);
	}
	
	/**
	 * Populate the fields
	 */
	public void setMission(MissionCard card) {
		this.card = card;
		populateFields();
	}		
	
	/**
	 * Check which mode
	 */
	public boolean inMissionMode() {
		return this.mission_mode && this.card != null;
	}
	
	/**
	 * sets new round
	 */
	public void setNewRound() {
		this.lbl_title.setText("New Round");
		this.lbl_title.setBackground(Color.BLUE);
	}
	
	/**
	 * Set mission mode
	 * @param my_turn 
	 */
	public void setMissionMode(boolean my_turn) {
		this.mission_mode = true;
		this.mission_panel.setVisible(true);
		if ( my_turn ) {
			this.lbl_title.setText("Your turn to play");
			this.lbl_title.setBackground(new Color(0, 204, 102));
		}
		populateFields();
	}
	
	/**
	 * unset mission mode
	 */
	public void unsetMissionMode() {
		this.mission_mode = false;
		this.mission_panel.setVisible(false);
		this.lbl_title.setText("Your turn has ended");
		this.lbl_title.setBackground(Color.RED);
	}
	
	/**
	 * Display title message
	 */
	public void displayStatusMessage(String msg) {
		this.lbl_status.setText(msg);
	}
	
	/**
	 * Display title message
	 */
	public void displayRollMessage(String msg) {
		this.lbl_roll.setText(msg);
	}
	
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.card != null ) {		
			this.mission_panel.setCard(card);			
		} else {
			this.mission_panel.setCard(null);
		}
	}
	
	

}
