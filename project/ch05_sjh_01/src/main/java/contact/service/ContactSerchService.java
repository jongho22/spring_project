package contact.service;

import ch05_sjh_01.contact.ContactSet;
import contact.dao.ContactDao;

public class ContactSerchService {
	
	private ContactDao contactDao;
	
	public ContactSerchService(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	public ContactSet serchContact(String name) {
		if (verify(name)) {
			return contactDao.select(name);
		} else {
			System.out.println("없는 이름 입니다.");
		}
		
		return null;
	}
	
	public boolean verify(String name) {
		ContactSet contactSet = contactDao.select(name);
		return contactSet != null ? true : false ;
	}
	
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
}
