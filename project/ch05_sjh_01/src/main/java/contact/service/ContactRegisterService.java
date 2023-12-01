package contact.service;

import ch05_sjh_01.contact.ContactSet;
import contact.dao.ContactDao;

public class ContactRegisterService {
	
	
	private ContactDao contactDao;
	
	public ContactRegisterService(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	public void register(ContactSet contactSet) {
		
		String name = contactSet.getName();
		
		if (verify(name)) {
			contactDao.insert(contactSet);
		} else {
			System.out.println("이미 가입되어 있습니다.");
		}
	}
	
	public boolean verify(String name) {
		ContactSet contactSet = contactDao.select(name);
		return contactSet == null ? true : false ;
	}
	
	public void setWordDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
}
