package ch05_sjh_01.contact.dao;

import java.util.HashMap;
import java.util.Map;

import ch05_sjh_01.contact.ContactSet;

public class ContactDao {
	
	private Map<String, ContactSet> contactDB = new HashMap<String, ContactSet>();
	
	public void insert(ContactSet contactSet) {
		contactDB.put(contactSet.getName(), contactSet);
	}
	
	public ContactSet select(String name) {
		return contactDB.get(name);
	}
	
	public Map<String, ContactSet> getContactDB() {
		return contactDB;
	}

}
