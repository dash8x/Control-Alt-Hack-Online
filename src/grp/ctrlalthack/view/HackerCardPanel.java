/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class HackerCardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HackerCardPanel() {
		setBackground(new Color(102, 204, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 0, 5, 0, 5, 0};
		gridBagLayout.rowHeights = new int[]{5, 0, 0, 50, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblFranzi = new JLabel("Franzi");
		lblFranzi.setBackground(new Color(0, 102, 255));
		lblFranzi.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblFranzi.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblFranzi = new GridBagConstraints();
		gbc_lblFranzi.gridwidth = 3;
		gbc_lblFranzi.insets = new Insets(0, 0, 5, 5);
		gbc_lblFranzi.anchor = GridBagConstraints.WEST;
		gbc_lblFranzi.gridx = 1;
		gbc_lblFranzi.gridy = 1;
		add(lblFranzi, gbc_lblFranzi);
		
		SkillFragment panel = new SkillFragment();		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		

	}
	
	/**
	 * Skill panel
	 */
	private class SkillFragment extends JPanel {
		
		public SkillFragment() {
			
			this.setBackground(Color.WHITE);
			
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{1, 0, 1, 0};
			gbl_panel.rowHeights = new int[]{1, 0, 0, 1, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
			this.setLayout(gbl_panel);
			
			JLabel label = new JLabel("12");
			label.setFont(new Font("Tahoma", Font.BOLD, 16));
			label.setForeground(new Color(51, 102, 255));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 1;
			this.add(label, gbc_label);
			
			JLabel lblHardwareHacking = new JLabel("Hardware Hacking");
			lblHardwareHacking.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_lblHardwareHacking = new GridBagConstraints();
			gbc_lblHardwareHacking.insets = new Insets(0, 0, 5, 5);
			gbc_lblHardwareHacking.gridx = 1;
			gbc_lblHardwareHacking.gridy = 2;
			this.add(lblHardwareHacking, gbc_lblHardwareHacking);
			
		}
	}

}
