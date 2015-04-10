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


public class ListEntropyCardsPanel extends JPanel {
	
	private GameStats stats;	
	private ArrayList<EntropyCard> cards;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<EntropyCard> cards = DataIO.readEntropyCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new ListEntropyCardsPanel(cards));					
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
	public ListEntropyCardsPanel(ArrayList<EntropyCard> cards) {				
		this.cards = cards;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(204, 204, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		JPanel body = new JPanel();
		body.setOpaque(false);
		scrollPane.setViewportView(body);	
		int rows = cards.size() / 2;
		body.setLayout(new GridLayout(rows, 2, 5, 5));
		
		for (int i = 0; i < cards.size(); i++) {				
			ActionableEntropyCardPanel panel = new ActionableEntropyCardPanel(i, cards.get(i));
	        body.add(panel);
	    }
		
	}
		
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.stats != null ) {									
			
		}
	}


}
