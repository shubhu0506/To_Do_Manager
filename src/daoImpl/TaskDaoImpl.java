package daoImpl;

import dao.UserDao;

import java.util.*;

import customExceptionHandler.TaskNotFoundException;
import customExceptionHandler.UserNotFoundException;
import dao.TaskDao;
import model.Task;
import model.User;

public class TaskDaoImpl implements TaskDao {
    UserDao userDao=new UserDaoImpl();
    Scanner in=new Scanner(System.in);
    HashSet<Integer> taskIdChecker=new HashSet<>();
    //Main
	@Override
	public void taskInitializer() {
		String[] taskName= {"eat","drink","study","sleep","code"};
		for(int i=0; i<taskName.length;i++)
		{
			taskIdChecker.add(i);
			tasks.add(new Task(i, taskName[i]));
		}
		
	}

	@Override
	public void add(int taskId, String taskTitle) {
		if(taskIdChecker.contains(taskId)) {
			System.out.println("Given id already exist, please enter another task id.");
			taskId=in.nextInt();
		}
		Task task=new Task(taskId, taskTitle);
		tasks.add(task);
		System.out.println("Task added!");
	}

	@Override
	public void delete(int taskId) {
		Task task=search(taskId);
		try {
			if(task==null)
				throw new TaskNotFoundException("Task not found!");
			tasks.remove(task);
			System.out.println("Task Deleted!");
		}catch(TaskNotFoundException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void update(int taskId) {
		Task task=search(taskId);
		try {
			if(task==null)
				throw new TaskNotFoundException("Task not found");
			in.nextLine();
			System.out.println("Please enter the new title: ");
			String taskTitle=in.nextLine();
			task.setTaskTitle(taskTitle);
			System.out.println("Task Updated!");
		}catch(TaskNotFoundException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void assign(int taskId, String userId) {
		Task task=search(taskId);
		User user=null;
		try {
			if(task==null)
				throw new TaskNotFoundException("Task not found!");
			user=userDao.userFinder(userId);
			try {
				if(user==null)
					throw new UserNotFoundException("User not found!");
				user.getTasks().add(task);
				System.out.println(task.getTaskTitle()+ " assigned to " + user.getName() + "!");
			}catch(UserNotFoundException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}catch(TaskNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	@Override
	public void listTasks() {
		for(Task task:tasks)
		{
			System.out.println("task id : " + task.getTaskId() + " :: task title: " + task.getTaskTitle());
		}
	}
	
	@Override
	public Task search(int taskId) {
		for(Task task: tasks)
			if(task.getTaskId()== taskId)
				return task;
		return null;
	}

}
