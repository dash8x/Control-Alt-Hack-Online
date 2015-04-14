/**
 * Contains static methods to read data from resources
 * 
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org 
 */

package grp.ctrlalthack.view;

import grp.ctrlalthack.data.DataIO;
import grp.ctrlalthack.model.GameConstants;
import grp.ctrlalthack.model.HackerCard;
import grp.ctrlalthack.model.mission.MissionCard;
import grp.ctrlalthack.model.mission.MissionTask;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class MissionCardPanel extends JPanel {
	
	private JTextArea lbl_mission_desc;	
	private JLabel lbl_mission_title;
	private MissionCard card;
	
	//list to store variables
	private ArrayList<TaskPanel> task_panels;		
	private TaskPanel task_panel_1;
	private TaskPanel task_panel_2;
	private TaskPanel task_panel_3;
	private JLabel lbl_success;
	private JLabel lbl_failure;
	private JLabel lbl_success_title;
	private JLabel lbl_failure_title;
	private JTextArea lbl_success_desc;
	private JTextArea lbl_failure_desc;
	private JLabel lbl_name;

	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<MissionCard> cards = DataIO.readMissionCards();
					JFrame window = new JFrame();
					window.getContentPane().add(new MissionCardPanel(cards.get(1)));
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/**
	 * Create the panel.
	 */
	public MissionCardPanel(MissionCard card) {
		
		this.card = card;
		this.task_panels = new ArrayList<TaskPanel>();		
		
		setBorder(new LineBorder(new Color(0, 0, 153), 10));
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		Border lbl_perk_padding = BorderFactory.createEmptyBorder(10,10,10,10);
		setPreferredSize(new Dimension(350, 400));
		
		lbl_name = new JLabel("MISSION");
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		lbl_name.setOpaque(true);
		lbl_name.setBackground(new Color(51, 102, 255));
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_name.setForeground(Color.WHITE);
		GridBagConstraints gbc_lbl_name = new GridBagConstraints();
		gbc_lbl_name.gridwidth = 2;
		gbc_lbl_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_name.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_name.gridx = 0;
		gbc_lbl_name.gridy = 0;
		add(lbl_name, gbc_lbl_name);				
		
		lbl_mission_title = new JLabel("Mission Title");
		lbl_mission_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_mission_title.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lbl_mission_title = new GridBagConstraints();
		gbc_lbl_mission_title.gridwidth = 2;
		gbc_lbl_mission_title.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_mission_title.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_mission_title.gridx = 0;
		gbc_lbl_mission_title.gridy = 2;
		add(lbl_mission_title, gbc_lbl_mission_title);
		
		lbl_mission_desc = new JTextArea("Mission desc");
		lbl_mission_desc.setWrapStyleWord(true);
		lbl_mission_desc.setLineWrap(true);
		lbl_mission_desc.setEditable(false);
		lbl_mission_desc.setOpaque(false);
		lbl_mission_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_mission_desc.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
		GridBagConstraints gbc_lbl_mission_desc = new GridBagConstraints();
		gbc_lbl_mission_desc.gridwidth = 2;
		gbc_lbl_mission_desc.fill = GridBagConstraints.BOTH;
		gbc_lbl_mission_desc.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_mission_desc.gridx = 0;
		gbc_lbl_mission_desc.gridy = 3;
		add(lbl_mission_desc, gbc_lbl_mission_desc);
		
		task_panel_1 = new TaskPanel();
		GridBagConstraints gbc_task_panel_1 = new GridBagConstraints();
		gbc_task_panel_1.gridwidth = 2;
		gbc_task_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_task_panel_1.fill = GridBagConstraints.BOTH;
		gbc_task_panel_1.gridx = 0;
		gbc_task_panel_1.gridy = 5;
		add(task_panel_1, gbc_task_panel_1);
		
		task_panel_2 = new TaskPanel();
		GridBagConstraints gbc_task_panel_2 = new GridBagConstraints();
		gbc_task_panel_2.gridwidth = 2;
		gbc_task_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_task_panel_2.fill = GridBagConstraints.BOTH;
		gbc_task_panel_2.gridx = 0;
		gbc_task_panel_2.gridy = 6;
		add(task_panel_2, gbc_task_panel_2);
		
		task_panel_3 = new TaskPanel();
		GridBagConstraints gbc_task_panel_3 = new GridBagConstraints();
		gbc_task_panel_3.gridwidth = 2;
		gbc_task_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_task_panel_3.fill = GridBagConstraints.BOTH;
		gbc_task_panel_3.gridx = 0;
		gbc_task_panel_3.gridy = 7;
		add(task_panel_3, gbc_task_panel_3);
		
		lbl_success = new JLabel("Success:");
		lbl_success.setForeground(new Color(0, 204, 204));
		lbl_success.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_success.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		GridBagConstraints gbc_lbl_success = new GridBagConstraints();
		gbc_lbl_success.anchor = GridBagConstraints.WEST;
		gbc_lbl_success.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_success.gridx = 0;
		gbc_lbl_success.gridy = 9;
		add(lbl_success, gbc_lbl_success);
		
		lbl_success_title = new JLabel("Success title");
		lbl_success_title.setForeground(new Color(0, 0, 102));
		lbl_success_title.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_success_title.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		GridBagConstraints gbc_lbl_success_title = new GridBagConstraints();
		gbc_lbl_success_title.anchor = GridBagConstraints.WEST;
		gbc_lbl_success_title.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_success_title.gridx = 1;
		gbc_lbl_success_title.gridy = 9;
		add(lbl_success_title, gbc_lbl_success_title);
		
		lbl_success_desc = new JTextArea("Success desc");
		lbl_success_desc.setWrapStyleWord(true);
		lbl_success_desc.setOpaque(false);
		lbl_success_desc.setLineWrap(true);
		lbl_success_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_success_desc.setEditable(false);
		lbl_success_desc.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 5, 10)));
		GridBagConstraints gbc_lbl_success_desc = new GridBagConstraints();
		gbc_lbl_success_desc.gridwidth = 2;
		gbc_lbl_success_desc.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_success_desc.fill = GridBagConstraints.BOTH;
		gbc_lbl_success_desc.gridx = 0;
		gbc_lbl_success_desc.gridy = 10;
		add(lbl_success_desc, gbc_lbl_success_desc);
		
		lbl_failure = new JLabel("Failure:");
		lbl_failure.setForeground(new Color(0, 204, 204));
		lbl_failure.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_failure.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		GridBagConstraints gbc_lbl_failure = new GridBagConstraints();
		gbc_lbl_failure.anchor = GridBagConstraints.WEST;
		gbc_lbl_failure.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_failure.gridx = 0;
		gbc_lbl_failure.gridy = 11;
		add(lbl_failure, gbc_lbl_failure);
		
		lbl_failure_title = new JLabel("Failure title");
		lbl_failure_title.setForeground(new Color(0, 0, 102));
		lbl_failure_title.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_failure_title.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 0, 10)));
		GridBagConstraints gbc_lbl_failure_title = new GridBagConstraints();
		gbc_lbl_failure_title.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_failure_title.anchor = GridBagConstraints.WEST;
		gbc_lbl_failure_title.gridx = 1;
		gbc_lbl_failure_title.gridy = 11;
		add(lbl_failure_title, gbc_lbl_failure_title);
		
		lbl_failure_desc = new JTextArea("Failure desc");
		lbl_failure_desc.setWrapStyleWord(true);
		lbl_failure_desc.setOpaque(false);
		lbl_failure_desc.setLineWrap(true);
		lbl_failure_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_failure_desc.setEditable(false);
		lbl_failure_desc.setBorder(new CompoundBorder(null, new EmptyBorder(5, 10, 10, 10)));
		GridBagConstraints gbc_lbl_failure_desc = new GridBagConstraints();
		gbc_lbl_failure_desc.gridwidth = 2;
		gbc_lbl_failure_desc.fill = GridBagConstraints.BOTH;
		gbc_lbl_failure_desc.gridx = 0;
		gbc_lbl_failure_desc.gridy = 12;
		add(lbl_failure_desc, gbc_lbl_failure_desc);
		
		
		
		//add task panels to list
		task_panels.add(task_panel_1);
		task_panels.add(task_panel_2);
		task_panels.add(task_panel_3);
		
		//fill in the values
		populateFields();
	}
	
	/**
	 * Set card
	 */
	public void setCard(MissionCard card) {
		this.card = card;
		populateFields();
	}
	
	/**
	 * Populate the fields
	 */
	private void populateFields() {
		if ( this.card != null ) {
			
			if ( this.card.isNewb() ) {
				lbl_name.setText("MISSION: NEWB JOB");
			}
			
			lbl_mission_title.setText(card.getTitle());			
			lbl_mission_desc.setText(card.getDesc());									
			
			//success			
			lbl_success_title.setText(card.getSuccess().getTitle());
			lbl_success_desc.setText(card.getSuccess().getDesc());
	
			//failure
			lbl_failure_title.setText(card.getFailure().getTitle());
			lbl_failure_desc.setText(card.getFailure().getDesc());
			
			//add tasks
			ArrayList<MissionTask> mission_tasks = card.getTasks();
		    for ( int i = 0; i < task_panels.size(); i++ ) {
				TaskPanel tp = task_panels.get(i);
				try {
					tp.setTask(mission_tasks.get(i));
				} catch (IndexOutOfBoundsException e) {					
				}
		    }
		    this.setVisible(true);
		} else {
			this.setVisible(false);
		}
	}

	/**
	 * Skill panel
	 */
	private class TaskPanel extends JPanel {
		
		private JLabel lbl_task_title;
		private JTextArea lbl_task_desc;
		private MissionTask task;
		
		/**
		 * Constructor
		 */
		public TaskPanel() {
			this(null);
		}

		/**
		 * Constructor
		 */
		public TaskPanel(MissionTask task) {
			
			this.setTask(task);
			
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			this.setLayout(gbl_panel);
			
			lbl_task_title = new JLabel("Task Title");
			lbl_task_title.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbl_task_title.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
			GridBagConstraints gbc_lblTaskTitle = new GridBagConstraints();
			gbc_lblTaskTitle.insets = new Insets(0, 0, 5, 0);
			gbc_lblTaskTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblTaskTitle.gridx = 0;
			gbc_lblTaskTitle.gridy = 0;
			this.add(lbl_task_title, gbc_lblTaskTitle);
			
			lbl_task_desc = new JTextArea("Task desc");
			lbl_task_desc.setWrapStyleWord(true);
			lbl_task_desc.setOpaque(false);
			lbl_task_desc.setLineWrap(true);
			lbl_task_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbl_task_desc.setEditable(false);
			lbl_task_desc.setBorder(new CompoundBorder(null, new EmptyBorder(10, 10, 10, 10)));
			GridBagConstraints gbc_lbl_task_desc = new GridBagConstraints();
			gbc_lbl_task_desc.fill = GridBagConstraints.BOTH;
			gbc_lbl_task_desc.gridx = 0;
			gbc_lbl_task_desc.gridy = 1;
			this.add(lbl_task_desc, gbc_lbl_task_desc);
			
		}

		/**
		 * Sets the task
		 */
		public void setTask(MissionTask task) {			
			this.task = task;
			this.populateFields();
		}

		/**
		 * Populates the fields
		 */
		private void populateFields() {
			if ( this.task != null ) {
				this.lbl_task_title.setText(this.task.getTitle());
				this.lbl_task_desc.setText(this.task.getDesc());
				this.setVisible(true);
			} else {
				this.setVisible(false);
			}
		}
	}

}
