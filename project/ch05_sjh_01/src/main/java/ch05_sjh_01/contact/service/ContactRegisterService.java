package ch05_sjh_01.contact.service;

import org.springframework.beans.factory.annotation.Autowired;

import ch05_sjh_01.contact.ContactSet;
import ch05_sjh_01.contact.dao.ContactDao;

public class ContactRegisterService {
	
//	@Autowired
//	private ContactDao contactDao;
	
//	public ContactRegisterService() {
//		System.out.println("디폴트 생성자");
//	}
	
//	@Autowired
//	public ContactRegisterService(ContactDao contactDao) {
//		System.out.println("확인 : " + contactDao);
//		this.contactDao = contactDao;
//	}
	
	private ContactDao contactDao;
	
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
	
	@Autowired
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
}
