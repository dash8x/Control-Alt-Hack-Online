/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.data.DataIO;
import grp.ctrlalthack.model.GameStats;
import grp.ctrlalthack.model.entropy.EntropyCard;
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
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;


public class ActionableEntropyCardPanel extends JPanel {
	
	private EntropyCard card;	
	private int id;
	private EntropyCardPanel card_panel;
	private JButton btn_action;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<EntropyCard> cards = DataIO.readEntropyCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new ActionableEntropyCardPanel(0, cards.get(0)));					
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
	public ActionableEntropyCardPanel(int id, EntropyCard card) {				
		this.id = id;
		this.card = card;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		card_panel = EntropyCardPanel.generateEntropyCardPanel(this.card);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(card_panel, gbc_panel);
		
		btn_action = new JButton("USE");
		btn_action.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btn_action = new GridBagConstraints();
		gbc_btn_action.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_action.gridx = 0;
		gbc_btn_action.gridy = 1;
		add(btn_action, gbc_btn_action);						
	}
			


}
