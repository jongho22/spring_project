package com.office.library.admin.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminMemberDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean isAdminMember(String a_m_id) {
		System.out.println("[AdminMemberDao] isAdminMember()");
		
		String sql = "SELECT COUNT(*) FROM tbl_admin_member " + "WHERE a_m_id = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, a_m_id);
		
		if (result > 0)
			return true;
		else
			return false;
	}
	
	public int insertAdminAccount(AdminMemberVo adminMemberVo) {
		System.out.println("[AmdinMemberDao] insertAdminAccount()");
		
		List<String> args = new ArrayList<String>();
		
		String sql = "INSERT INTO tbl_admin_member(";
			if(adminMemberVo.getA_m_id().equals("super admin")) {
				sql += "a_m_approval, ";
				args.add("1");
			}
			
			sql += "a_m_id, ";
			args.add(adminMemberVo.getA_m_id());
			
			sql += "a_m_pw, ";
			args.add(passwordEncoder.encode(adminMemberVo.getA_m_pw()));
			
			sql += "a_m_name, ";
			args.add(adminMemberVo.getA_m_name());
			
			sql += "a_m_gender, ";
			args.add(adminMemberVo.getA_m_gender());
			
			sql += "a_m_part, ";
			args.add(adminMemberVo.getA_m_part());
			
			sql += "a_m_position, ";
			args.add(adminMemberVo.getA_m_position());
			
			sql += "a_m_mail, ";
			args.add(adminMemberVo.getA_m_mail());
			
			sql += "a_m_phone, ";
			args.add(adminMemberVo.getA_m_phone());
			
			sql += "a_m_reg_date, a_m_mod_date) ";
			
			if (adminMemberVo.getA_m_id().equals("super admin"))
				sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			else 
				sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, args.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
			
	}
	
	public AdminMemberVo selectAdmin(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberDao] selectAdmin()");
		
		String sql = "SELECT * FROM tbl_admin_member " + "WHERE a_m_id = ? AND a_m_approval > 0";
	
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		
		try {
			RowMapper<AdminMemberVo> rowMapper = BeanPropertyRowMapper.newInstance(AdminMemberVo.class);
			adminMemberVos = jdbcTemplate.query(sql, rowMapper, adminMemberVo.getA_m_id());
			
			if (!passwordEncoder.matches(adminMemberVo.getA_m_pw(), adminMemberVos.get(0).getA_m_pw()))
				adminMemberVos.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;
		
	}
	
	public List<AdminMemberVo> selectAdmins() {
		System.out.println("[AdminMemberDao] selectAdmins()");
		
		String sql = "SELECT * FROM tbl_admin_member";
		
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		
		try {
			RowMapper<AdminMemberVo> rowMapper = BeanPropertyRowMapper.newInstance(AdminMemberVo.class);
			adminMemberVos = jdbcTemplate.query(sql, rowMapper);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return adminMemberVos;
	}
	
	public int updateAdminAccount(int a_m_no) {
		System.out.println("[AdminMemberDao] updateAdminAccount(int a_m_no)");
		
		String sql = "UPDATE tbl_admin_member SET "
				+ "a_m_approval = 1 "
				+ "WHERE a_m_no = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql,a_m_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateAdminAccount(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberDao] updateAdminAccount(AdminMemberVo adminMemberVo)");
		
		String sql = "UPDATE tbl_admin_member SET " + "a_m_name = ?, "
					+ "a_m_gender = ?, " + "a_m_part = ?, "
					+ "a_m_position = ?, " + "a_m_mail = ?, "
					+ "a_m_phone = ?, " + "a_m_mod_date = NOW() "
					+ "WHERE a_m_no = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, adminMemberVo.getA_m_name(),
					adminMemberVo.getA_m_gender(), adminMemberVo.getA_m_part(),
					adminMemberVo.getA_m_position(), adminMemberVo.getA_m_mail(),
					adminMemberVo.getA_m_phone(), adminMemberVo.getA_m_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	public AdminMemberVo selectAdmin(int a_m_no) {
		System.out.println("[AdminMemberDao] selectAdmin()");
		
		String sql = "SELECT * FROM tbl_admin_member "
					+ "WHERE a_m_no = ?";
		
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		
		try {
			
			RowMapper<AdminMemberVo> rowMapper = BeanPropertyRowMapper.newInstance(AdminMemberVo.class);
			adminMemberVos = jdbcTemplate.query(sql, rowMapper, a_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;	
	}
	
	public AdminMemberVo selectAdmin(String a_m_id, String a_m_name, String a_m_mail) {
		System.out.println("[AdminMemberDao] selectAdmin(String a_m_id, String a_m_name, String a_m_mail)");
		
		String sql = "SELECT * FROM tbl_admin_member "
				+ "WHERE a_m_id = ? AND a_m_name = ? AND a_m_mail = ?";
		
		List<AdminMemberVo> adminMemberVos = new ArrayList<AdminMemberVo>();
		
		try {
			
			RowMapper<AdminMemberVo> rowMapper = BeanPropertyRowMapper.newInstance(AdminMemberVo.class);
			adminMemberVos = jdbcTemplate.query(sql, rowMapper, a_m_id, a_m_name, a_m_mail);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;	
	}
	
	public int updatePassword(String a_m_id, String newPassword) {
		System.out.println("[AdminMemberDao] updatePassword()");
		
		String sql = "UPDATE tbl_admin_member SET "
				+ "a_m_pw = ?, a_m_mod_date = NOW() "
				+ "WHERE a_m_id = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, passwordEncoder.encode(newPassword), a_m_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
