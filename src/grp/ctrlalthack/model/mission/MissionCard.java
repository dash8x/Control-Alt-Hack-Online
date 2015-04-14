/**
 * Class for Mission cards
 *   
 * @author Arushad Ahmed
 * @arthor_uri http://arushad.org  
 */

package grp.ctrlalthack.model.mission;

import java.io.Serializable;
import java.util.ArrayList;

public class MissionCard implements Serializable {	
	
	private String title;
	private String desc;
	private boolean is_newb = false;
	private ArrayList<MissionTask> tasks = new ArrayList<MissionTask>();
	private MissionSuccess success;
	private MissionFailure failure;
	
	/**
	 * Constructor
	 */
	public MissionCard(String title, String desc, ArrayList<MissionTask> tasks, MissionSuccess success, MissionFailure failure, boolean is_newb) {
		this.title = title;
		this.desc = desc;
		this.setTasks(tasks);
		this.setSuccess(success);
		this.setFailure(failure);
		this.is_newb = is_newb;
	}

	/**
	 * @return the is_newb
	 */
	public boolean isNewb() {
		return is_newb;
	}

	/**
	 * @return the tasks
	 */
	public ArrayList<MissionTask> getTasks() {
		return tasks;
	}

	/**
	 * @return the success
	 */
	public MissionSuccess getSuccess() {
		return success;
	}

	/**
	 * @return the failure
	 */
	public MissionFailure getFailure() {
		return failure;
	}	
	
	/**
	 * returns the mission title
	 */
	public String getTitle() {		
		return title;
	}
	
	/**
	 * returns the mission desc
	 */
	public String getDesc() {		
		return desc;
	}
	
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(ArrayList<MissionTask> tasks) {
		if ( tasks == null || tasks.size() > 3 ) {
			throw new IllegalArgumentException("Tasks cannot be null or more than 3 tasks per mission");
		}
		this.tasks = tasks;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(MissionSuccess success) {
		if ( success == null ) {
			throw new IllegalArgumentException("Mission success outcome cannot be null");
		}
		this.success = success;
	}

	/**
	 * @param failure the failure to set
	 */
	public void setFailure(MissionFailure failure) {
		if ( failure == null ) {
			throw new IllegalArgumentException("Mission failure outcome cannot be null");
		}
		this.failure = failure;
	}		
		
}
