package ch05_sjh_01.contact.service;

import org.springframework.beans.factory.annotation.Autowired;

import ch05_sjh_01.contact.ContactSet;
import ch05_sjh_01.contact.dao.ContactDao;

public class ContactSearchService {
	
//	@Autowired
//	private ContactDao contactDao;
	
//	public ContactSearchService() {
//		System.out.println("디폴트 생성자");
//	}
	
//	@Autowired
//	public ContactSearchService(ContactDao contactDao) {
//		System.out.println("확인 : " + contactDao);
//		this.contactDao = contactDao;
//	}
	private ContactDao contactDao;
	
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
	
	@Autowired
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
}
