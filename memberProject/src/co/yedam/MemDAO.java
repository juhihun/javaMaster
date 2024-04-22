package co.yedam;

import java.sql.*;
import java.util.*;


public class MemDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	private void getconn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	//회원 목록 기능
	List<Member> memList(){
		getconn();
		List<Member> list = new ArrayList<>();
		String sql = "select * from mem order by mem_no";
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setMemNo(rs.getInt("mem_no"));
				mem.setName(rs.getString("mem_name"));
				mem.setPhone(rs.getString("mem_phone"));
				mem.setBirthday(rs.getString("birhday"));
				mem.setGender(rs.getString("gender"));
				
				list.add(mem);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	//회원등록
	
	boolean insertMem(Member mem) {
		getconn();
		String sql = "insert into mem (mem_no, mem_name, mem_phone,birhday, gender)"
				+ "values (mem_seq.nextval,?,?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getName());
			psmt.setString(2, mem.getPhone());
			psmt.setString(3, mem.getBirthday());
			psmt.setString(4, mem.getGender());
			
			int r = psmt.executeUpdate();
			if(r> 0) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}
	
	boolean updateMem(Member mem) {
		getconn();
		String sql = "update mem";
		sql += " set mem_phone = ? ";
		sql += " where mem_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mem.getPhone());
			psmt.setInt(2, mem.getMemNo());
			
			int r = psmt.executeUpdate();
			if(r>0) { 
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			return false;	
	}
	
	boolean deleteMem(int mem) {
		getconn();
		String sql = "delete mem where mem_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, mem);
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
