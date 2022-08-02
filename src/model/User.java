package model;

import java.util.*;
public class User {
	private final String name;
	private final String password;
	private final ArrayList<Task> tasks;
	
	public User(String name,String password)
	{
		this.name=name;
		this.password=password;
		tasks=new ArrayList<>();
	}

	public ArrayList<Task> getTasks(){return tasks;}
	
	public String getName() {return name;}
	
	public String getPassword() {
		return password;
	}
}
