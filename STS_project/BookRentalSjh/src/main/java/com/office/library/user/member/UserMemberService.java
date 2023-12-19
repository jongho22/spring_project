package com.office.library.user.member;

import java.security.SecureRandom;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class UserMemberService {
	
	final static public int USER_ACCOUNT_ALREADY_EXIST = 0;
	final static public int USER_ACCOUNT_CREATE_SUCCESS = 1;
	final static public int USER_ACCOUNT_CREATE_FAIL = -1;
	
	@Autowired
	UserMemberDao userMemberDao;
	
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	public int createAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberDao] createAccountConfirm()");
		
		boolean isMember = userMemberDao.isUserMember(userMemberVo.getU_m_id());
		
		if (!isMember) {
			int result = userMemberDao.insertUserAccount(userMemberVo);
			
			if (result > 0) {
				return USER_ACCOUNT_CREATE_SUCCESS;
			} else {
				return USER_ACCOUNT_CREATE_FAIL;
			}
		} else {
			return USER_ACCOUNT_ALREADY_EXIST;
		}
	}
	
	public UserMemberVo loginConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] loginConfirm()");
		
		UserMemberVo loginedUserMemberVo = userMemberDao.selectUser(userMemberVo);
		
		if (loginedUserMemberVo != null) System.out.println("[UserMembeervice] USER MEMBER LOGIN SUCCESS!!");
		else System.out.println("[UserMembeervice] USER MEMBER LOGIN FAIL!!");
		
		return loginedUserMemberVo;
	}
	
	public int modifyAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] modifyAccountConfirm()");
		
		return userMemberDao.updateUserAccount(userMemberVo);
	}
	
	public UserMemberVo getLoginedUserMemberVo(int u_m_no) {
		System.out.println("[UserMemberService] getLoginedUserMember()");
		
		return userMemberDao.selectUser(u_m_no);
	}
	
	public int findPasswordConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserMemberService] findPasswordConfirm()");
		
		UserMemberVo selectedUserMemberVo = userMemberDao.selectUser(
											userMemberVo.getU_m_id(),
											userMemberVo.getU_m_name(),
											userMemberVo.getU_m_mail());
		
		int result = 0;
		
		if (selectedUserMemberVo != null) {
			String newPassword = createNewPassword();
			result = userMemberDao.updatePassword(userMemberVo.getU_m_id(), newPassword);
			
			if (result > 0)
				sendNewPasswordByMail(userMemberVo.getU_m_mail(), newPassword);
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
			
				if (index % 2 == 0) 
					stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
				else
					stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
			
		}
		System.out.println("[AdminMemberService] NEW PASSWORD: " + stringBuffer.toString());
			
		return stringBuffer.toString();
	}	
	
	private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
		System.out.println("[AdminMemberService] sendNewPasswordByMail()");
		
		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo("sjh0804_22@naver.com");	
				mimeMessageHelper.setSubject("[한국 도서관] 새 비밀번호 안내입니다.");
				mimeMessageHelper.setText("새 비밀번호 : " + newPassword, true);
			}
			
		};
		javaMailSenderImpl.send(mimeMessagePreparator);
		
	}
}
