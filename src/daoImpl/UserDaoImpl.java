package daoImpl;

import dao.UserDao;
import model.Task;
import model.User;

public class UserDaoImpl implements UserDao {
	public User tmpUser;
	
	@Override
	public User isValidUser(String name, String password) {
		for(User user:users) {
			if(user.getName().equalsIgnoreCase(name) && user.getPassword().equalsIgnoreCase(password))
				return user;
		}
		return null;
	}

	@Override
	public void listAssignedTasks(String name) {
		User user =userFinder(name);
		if(user.getTasks().size()==0)
			System.out.println("No tasks are assigned to " + user.getName());
		for(Task task:user.getTasks())
			System.out.println("task id: " + task.getTaskId() + " :: task title :" + task.getTaskTitle());
		
	}

	@Override
	public User userFinder(String username) {
		for(User user: users)
			if(user.getName().equalsIgnoreCase(username))
				return user;
		return null;
	}

}
