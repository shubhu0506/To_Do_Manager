package dao;

import java.util.ArrayList;

import model.Task;

public interface TaskDao {
	ArrayList<Task> tasks=new ArrayList<>();
	
	void taskInitializer();
	
	void add(int taskId, String taskTitle);
	
	void delete(int taskId);
	
	void update(int taskId);
	
	Task search(int taskId);
	
	void assign(int taskId, String userName);
	
	void listTasks();
	
}
