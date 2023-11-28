package ch04_sjh_01.ems.member.service;

import java.util.Map;

import ch04_sjh_01.ems.member.Student;
import ch04_sjh_01.ems.member.dao.StudentDao;

public class StudentAllSelectService {
	
	private StudentDao studentDao;
	
	public StudentAllSelectService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public Map<String, Student> allSelect() {
		return studentDao.getStudentDB();
	}

}
