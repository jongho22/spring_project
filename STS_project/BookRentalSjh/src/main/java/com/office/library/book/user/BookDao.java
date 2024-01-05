package com.office.library.book.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;

@Component
public class BookDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<BookVo> selectBooksBySearch(BookVo bookVo){
		System.out.println("[BookDao] selectBooksBySearch()");
		
		String sql = "SELECT * FROM tbl_book "
				   + "WHERE b_name LIKE ? "
				   + "ORDER BY b_no DESC";
		
		List<BookVo> bookVos = null;
		
		try {
			
			RowMapper<BookVo> rowMapper = BeanPropertyRowMapper.newInstance(BookVo.class);
			bookVos = jdbcTemplate.query(sql, rowMapper, "%" + bookVo.getB_name() + "%");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookVos.size() > 0 ? bookVos : null;
	}
	
	public BookVo selectBook(int b_no) {
		System.out.println("[BookDao] selectBook()");
		
		String sql = "SELECT * FROM tbl_book "
				   + "WHERE b_no = ? ";
		
		List<BookVo> bookVos = null;
		
		try {
			
			RowMapper<BookVo> rowMapper = BeanPropertyRowMapper.newInstance(BookVo.class);
			bookVos = jdbcTemplate.query(sql, rowMapper, b_no);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookVos.size() > 0 ? bookVos.get(0) : null;
	}
	
	public int insertRentalBook(int b_no, int u_m_no) {
		System.out.println("[BookDao] insertRentalBook()");
		
		String sql = "INSERT INTO tbl_rental_book(b_no, u_m_no, rb_start_date, rb_reg_date, rb_mod_date) "
				   + "VALUES(?, ?, NOW(), NOW(), NOW())";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, b_no, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void updateRentalBookAble(int b_no) {
		System.out.println("[BookDao] updateRentalBookAble()");
		
		String sql = "UPDATE tbl_book "
				   + "SET b_rantal_able = 0 "
				   + "WHERE b_no = ?";
		
		try {
			jdbcTemplate.update(sql,b_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<RentalBookVo> selectRentalBooks(int u_m_no) {
		System.out.println("[BookDao] selectRentalBooks()");
		
		String sql =  "SELECT * FROM tbl_rental_book rb "
				+ "JOIN tbl_book b "
				+ "ON rb.b_no = b.b_no "
				+ "JOIN tbl_user_member um "
				+ "ON rb.u_m_no = um.u_m_no "
				+ "WHERE rb.u_m_no = ? AND rb.rb_end_date = '1000-01-01'";
		
		List<RentalBookVo> rentalBookVos = new ArrayList<RentalBookVo>();
		
		try {
			
			RowMapper<RentalBookVo> rowMapper = BeanPropertyRowMapper.newInstance(RentalBookVo.class);
			rentalBookVos = jdbcTemplate.query(sql, rowMapper, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rentalBookVos;
		
	}
	
	public List<RentalBookVo> selectRentalBookHistory(int u_m_no) {
		System.out.println("[BookDao] selectRentalBooks()");
		
		String sql =  "SELECT * FROM tbl_rental_book rb "
					+ "JOIN tbl_book b "
					+ "ON rb.b_no = b.b_no "
					+ "JOIN tbl_user_member um "
					+ "ON rb.u_m_no = um.u_m_no "
					+ "WHERE rb.u_m_no = ? "
					+ "ORDER BY rb.rb_reg_date DESC";
		
		List<RentalBookVo> rentalBookVos = new ArrayList<RentalBookVo>();
		
		try {
			
			RowMapper<RentalBookVo> rowMapper = BeanPropertyRowMapper.newInstance(RentalBookVo.class);
			rentalBookVos = jdbcTemplate.query(sql, rowMapper, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rentalBookVos;	
	}
	
	public int insertHopeBook(HopeBookVo hopeBookVo) {
		System.out.println("[BookDao] insertHopeBook()");
		
		String sql =  "INSERT INTO tbl_hope_book(u_m_no, hb_name, hb_author, hb_publisher, "
					+ "hb_publish_year, hb_reg_date, hb_mod_date, hb_result_last_date) "
					+ "VALUES(?, ?, ?, ?, ?, NOW(), NOW(), NOW())";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, 
											hopeBookVo.getU_m_no(), 
											hopeBookVo.getHb_name(), 
											hopeBookVo.getHb_author(), 
											hopeBookVo.getHb_publisher(), 
											hopeBookVo.getHb_publish_year());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}	
	
	public List<HopeBookVo> selectRequestHopeBooks(int u_m_no) {
		System.out.println("[BookDao] insertHopeBook()");
		
		String sql = "SELECT * FROM tbl_hope_book WHERE u_m_no = ?";
		
		List<HopeBookVo> hopeBookVos = null;
		
		try {
			
			RowMapper<HopeBookVo> rowMapper = BeanPropertyRowMapper.newInstance(HopeBookVo.class);
			hopeBookVos = jdbcTemplate.query(sql, rowMapper, u_m_no);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hopeBookVos;
	}

}
