package model;

public class Task {
	private int taskId;
	private String taskTitle;

	
	public Task(int taskId, String taskTitle) {
		this.taskId = taskId;
		this.taskTitle = taskTitle;
	}
	
	public int getTaskId() {return taskId;}

	public void setTaskId(int taskId) {this.taskId = taskId;}
	
	public String getTaskTitle() {return taskTitle;}
	
	public void setTaskTitle(String taskTitle) {this.taskTitle = taskTitle;}
	
 
}
