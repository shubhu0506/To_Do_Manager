package model;

public class Client extends User{
	 public final String type="client";
	 
	 public Client(String name,String password) {super(name, password);}
	 
	 public String getType() {return type;}
}
