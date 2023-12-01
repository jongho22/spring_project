package ch05_sjh_01.contact;

public class ContactSet {
	
	private String name;
	private String phoneNumber;
	
	public ContactSet(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	
}
