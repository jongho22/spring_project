package com.office.library.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalBookVo {
	
	int rb_no;
	String rb_start_date;
	String rb_end_date;
	String rb_reg_date;
	String rb_mod_date;
	
	int b_no;
	String b_thumbnail;
	String b_name;
	String b_author;	
	String b_publisher;
	String b_publish_year;
	String b_isbn;
	String b_call_number;
	int b_rantal_able;
	String b_reg_date;
	String b_mod_date;
	
	int u_m_no;
	String u_m_id;
	String u_m_pw;
	String u_m_name;
	String u_m_gender;
	String u_m_mail;
	String u_m_phone;
	String u_m_reg_date;
	String u_m_mod_date;
	
}
