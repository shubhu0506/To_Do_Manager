package main;

import static dao.UserDao.users;
import dao.TaskDao;
import dao.UserDao;
import daoImpl.TaskDaoImpl;
import daoImpl.UserDaoImpl;
import model.Task;
import model.User;

import java.util.*;

import customExceptionHandler.InvalidUserException;

public class MainDemo {
	private static final UserDao userDao=new UserDaoImpl();
	private static final TaskDao taskDao=new TaskDaoImpl();
	static Scanner in =new Scanner(System.in);
	
	public static void main(String[] args)
	{
		MainDemo main=new MainDemo();
		taskDao.taskInitializer();
		do {
			System.out.println("Hello");
			System.out.println("Press 1 if you are a client.");
			System.out.println("Press 2 if you are a user.");
			int option =in.nextInt();
			in.nextLine();
			if(option ==1)
				main.client();
			else if(option==2)
				main.visitor();
			else 
				System.out.println("Incorrect option .Try again!");
			System.out.println("Do you want to continue with login menu ? Press 0 to exit/Any other key(1-9) to continue");
		}while(in.nextInt()!=0);
	}
	User loginMenu()
	{
		System.out.println("1. Login");
		System.out.println("2. Register");
		int choice =in.nextInt();
		in.nextLine();
		String name, password;
		User user =null;
		switch(choice) {
		case 1:
			System.out.println("Please enter your name: ");
			name=in.nextLine();
			System.out.println("Please enter your passowrd: ");
			password=in.nextLine();
			user=userDao.isValidUser(name, password);
			break;
		case 2:
			System.out.println("Please enter your name: ");
			name=in.nextLine();
			System.out.println("Please enter your passowrd: ");
			password=in.nextLine();
			user=new User(name,password);
			users.add(user);
			break;
		default:
			System.out.println("Wrong Choice");
			System.exit(0);
		}
		return user;
	}
	void client() {
		do {
			User user=loginMenu();
			try {
				if(user==null)
					throw new InvalidUserException("Unauthorized user");
				do {
					System.out.println("1. Add a task");
					System.out.println("2. Update a task");
					System.out.println("3. Search a task");
					System.out.println("4. Delete a task");
					System.out.println("5. Assign a task");
					System.out.println("6. List all task");
					System.out.println("7. List all assigned tasks");
					System.out.println("Enter your choice: ");
					int choice =in.nextInt();
					switch(choice) {
					case 1:
						System.out.println("Enter task id: ");
						int taskId=in.nextInt();
						in.nextLine();
						System.out.println("Enter task title: ");
						String taskTitle =in.nextLine();
						taskDao.add(taskId, taskTitle);
						break;
					case 2:
						System.out.println("Enter task id: ");
						taskId=in.nextInt();
						in.nextLine();
						taskDao.update(taskId);
						break;
					case 3:
						System.out.println("Enter the task id: ");
						taskId=in.nextInt();
						in.nextLine();
						Task task=taskDao.search(taskId);
						if(task!=null)
							System.out.println("Task "+ task.getTaskTitle()+ " with task id "+ taskId + " found!");
						else 
							System.out.println("Task with task id  "+ taskId + " not found ");
						break;
					case 4:
						System.out.println("Enter task id: ");
						taskId=in.nextInt();
						in.nextLine();
						taskDao.delete(taskId);
						break;
					case 5:
						System.out.println("Enter task id: ");
						taskId =in.nextInt();
						in.nextLine();
						System.out.println("Enter username: ");
						String username=in.nextLine();
						taskDao.assign(taskId, username);
						break;
					case 6:
						taskDao.listTasks();
						break;
					case 7:
						userDao.listAssignedTasks(user.getName());
						break;
					default:
						System.out.println("Wrong choice!");
					}
					System.out.println("Do you want to continue ? Press 0 to exit/Any other key(1-9) to continue");
				}while(in.nextInt()!=0);
				
			}catch(InvalidUserException e) {
				System.out.println(e);
			}
			System.out.println("Do you want to continue with client menu ? Press 0 to exit/Any other key(1-9) to continue");
		}while(in.nextInt()!=0);
	}
	void visitor() {
		do {
			User user =loginMenu();
			try {
				if(user ==null)
					throw new InvalidUserException("Unauthorized user");
				System.out.println("Do you want to list all assigned tasks?(yes/no)");
				String ans=in.nextLine();
				if(ans.equalsIgnoreCase("yes"))
					userDao.listAssignedTasks(user.getName());
			}catch(InvalidUserException e) {
				System.out.println(e);
			}
			System.out.println("Do you want to continue with visitor menu ? Press 0 to exit/Any other key(1-9) to continue");
		}while(in.nextInt()!=0);
	}
}
