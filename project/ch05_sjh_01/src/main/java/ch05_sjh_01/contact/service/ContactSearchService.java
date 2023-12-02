package ch05_sjh_01.contact.service;

import ch05_sjh_01.contact.ContactSet;
import ch05_sjh_01.contact.dao.ContactDao;

public class ContactSearchService {
	
	private ContactDao contactDao;
	
	public ContactSearchService(ContactDao contactDao) {
		System.out.println("확인 : " + contactDao);
		this.contactDao = contactDao;
	}
	
	public ContactSet searchContact(String name) {
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
