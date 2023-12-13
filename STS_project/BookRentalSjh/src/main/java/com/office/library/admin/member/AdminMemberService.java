package com.office.library.admin.member;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {
	
	final static public int ADMIN_ACCOUNT_ALREADY_EXIST = 0;
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1;
	final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;
	
	@Autowired
	AdminMemberDao adminMemberDao;
	
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	public int createAccoountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] createAccountConfirm()");
		
		boolean isMember = adminMemberDao.isAdminMember(adminMemberVo.getA_m_id());
		
		if (!isMember) {
			int result = adminMemberDao.insertAdminAccount(adminMemberVo);
			
			if (result > 0)
				return ADMIN_ACCOUNT_CREATE_SUCCESS;
			else
				return ADMIN_ACCOUNT_CREATE_FAIL;
		} else {
			return ADMIN_ACCOUNT_ALREADY_EXIST;
		}
	}
	
	public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] loginConfirm()");
		
		AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo);
		
		if (loginedAdminMemberVo != null)
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN SUCCESS!!");
		else
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN FAIL!!");
		
		return loginedAdminMemberVo;
	}
	
	public List<AdminMemberVo> listupAdmin() {
		System.out.println("[AdminMemberService] listupAdmin()");
		
		return adminMemberDao.selectAdmins();
	}
	
	public void setAdminApproval(int a_m_no) {
		System.out.println("[AdminMemberService] setAdminApproval()");
		
		int result = adminMemberDao.updateAdminAccount(a_m_no);
	}
	
	public int modifyAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] modifyAccountConfirm()");
		
		return adminMemberDao.updateAdminAccount(adminMemberVo);
	}
	
	public AdminMemberVo getLoginedAdminMemberVo(int a_m_no) {
		System.out.println("[AdminMemberService] getLoginedAdminMemberVo()");
		
		AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(a_m_no);
		
		if (loginedAdminMemberVo != null)
			System.out.println("[AdminMemberService] ADMIN MEMBER UPDATE SUCCESS!!");
		else
			System.out.println("[AdminMemberService] ADMIN MEMBER UPDATE FAIL!!");
		
		return loginedAdminMemberVo;
	}
	
	public int findPasswordConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] indPasswordConfirm()");
		
		AdminMemberVo selectedAdminMember = adminMemberDao.selectAdmin(adminMemberVo.getA_m_id(),
				adminMemberVo.getA_m_name(), adminMemberVo.getA_m_mail());
		
		int result = 0;
		
		if (selectedAdminMember != null) {
			String newPassword = createNewPassword();
			result = adminMemberDao.updatePassword(adminMemberVo.getA_m_id(),newPassword);
			
			if (result > 0) sendNewPasswordByMail(adminMemberVo.getA_m_mail(), newPassword);
		}
		return result;
	}
	
	private String createNewPassword() {
		System.out.println("[AdminMemberService] createNewPassword()");
		
		char[] chars = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
				'u', 'v', 'w', 'x', 'y', 'z'
				};
		
		StringBuffer stringBuffer = new StringBuffer();
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(new Date().getTime());
		
		int index = 0;
		int length = chars.length;
		for (int i = 0; i < 8; i++) {
			index = secureRandom.nextInt(length);
			
			if (index % 2 == 0) stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
			else stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
		}
		
		System.out.println("[AdminMemberService] NEW PASSWORD : " + stringBuffer.toString());
		
		return stringBuffer.toString();
	}
	
	private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
		System.out.println("[AdminMemberService] sendNewPasswordByMail()");
		
		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				mimeMessageHelper.setTo("sjh0804_22@naver.com");
				mimeMessageHelper.setSubject("[한국도서관] 새비밀번호 안내입니다.");
				mimeMessageHelper.setText("새비밀번호 : " + newPassword, true);
			}
		};
		
		javaMailSenderImpl.send(mimeMessagePreparator);
	}
}
