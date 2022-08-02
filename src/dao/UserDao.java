package dao;

import java.util.*;

import model.User;

public interface UserDao {
	ArrayList<User> users=new ArrayList<>();
	
	User isValidUser(String name, String password);
	
	void listAssignedTasks(String name);
	
	User userFinder(String username);
}
