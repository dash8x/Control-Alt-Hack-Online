/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.data.DataIO;
import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.mission.MissionCard;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class PlayMissionPanel extends JPanel {
	
	private GameStats stats;	
	private MissionCard card;
	private MissionCardPanel mission_panel;
	private JButton btn_roll;
	private JLabel lbl_roll;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<MissionCard> cards = DataIO.readMissionCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new PlayMissionPanel(cards.get(4)));					
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the panel.
	 */
	public PlayMissionPanel(MissionCard card) {				
		this.card = card;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(204, 204, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 5, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		mission_panel = new MissionCardPanel(card);
		GridBagConstraints gbc_mission_panel = new GridBagConstraints();
		gbc_mission_panel.gridheight = 3;
		gbc_mission_panel.insets = new Insets(0, 0, 0, 5);
		gbc_mission_panel.fill = GridBagConstraints.BOTH;
		gbc_mission_panel.gridx = 0;
		gbc_mission_panel.gridy = 0;
		add(mission_panel, gbc_mission_panel);
		
		JLabel lbl_status = new JLabel("Arushad playing");
		lbl_status.setForeground(new Color(0, 0, 0));
		lbl_status.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_status.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_status = new GridBagConstraints();
		gbc_lbl_status.anchor = GridBagConstraints.NORTH;
		gbc_lbl_status.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_status.gridx = 1;
		gbc_lbl_status.gridy = 0;
		add(lbl_status, gbc_lbl_status);
		
		lbl_roll = new JLabel("You rolled 8");
		lbl_roll.setForeground(Color.BLACK);
		lbl_roll.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_roll.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_roll = new GridBagConstraints();
		gbc_lbl_roll.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_roll.gridx = 1;
		gbc_lbl_roll.gridy = 1;
		add(lbl_roll, gbc_lbl_roll);
		
		btn_roll = new JButton("ROLL");
		btn_roll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btn_roll = new GridBagConstraints();
		gbc_btn_roll.gridx = 1;
		gbc_btn_roll.gridy = 2;
		add(btn_roll, gbc_btn_roll);
		
		//fill in the values
		setStats(stats);
	}
	
	/**
	 * Populate the fields
	 */
	public void setStats(GameStats stats) {
		this.stats = stats;
		populateFields();
	}
	
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.stats != null ) {									
			
		}
	}


}
