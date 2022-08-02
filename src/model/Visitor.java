package model;

public class Visitor extends User {
 public final String type="visitor";
 
 public Visitor(String name,String password) {super(name, password);}
 
 public String getType() {return type;}
}
