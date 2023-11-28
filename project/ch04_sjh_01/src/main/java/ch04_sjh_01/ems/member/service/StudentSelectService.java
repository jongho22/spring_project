package ch04_sjh_01.ems.member.service;

import ch04_sjh_01.ems.member.dao.StudentDao;
import ch04_sjh_01.ems.member.Student;

public class StudentSelectService {
	
	private StudentDao studentDao;
	
	public StudentSelectService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public Student select(String sNum) {
		if (verify(sNum)) {
			return studentDao.select(sNum);
		} else {
			System.out.println("존재하지 않는 학생 정보입니다.");
		}
		return null;
	}
	
	public boolean verify(String sNum) {
		Student student = studentDao.select(sNum);
		return student != null ? true : false;
	}
	
}
