package ch04_sjh_01.ems.member.service;

import ch04_sjh_01.ems.member.Student;
import ch04_sjh_01.ems.member.dao.StudentDao;

public class StudentDeleteService {
	
	private StudentDao studentDao;
	
	public StudentDeleteService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public void delete(String sNum) {
		if(verify(sNum)) {
			studentDao.delete(sNum);
		} else {
			System.out.println("존재하지 않는 학생 정보입니다.");
		}
	}
	
	public boolean verify(String sNum) {
		Student student = studentDao.select(sNum);
		return student != null ? true : false;
	}
	
}
