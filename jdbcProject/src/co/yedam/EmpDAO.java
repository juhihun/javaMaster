package co.yedam;

import java.sql.*;
import java.util.*;

public class EmpDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	// DB 접속 후 connection
	private void getconn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "jsp", "jsp");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	} // end of getConn().

	// 사원 목록 기능
	List<Employee> empList() {
		getconn();
		List<Employee> list = new ArrayList<>();
		String sql = "select * from emp order by emp_no";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			//조회 결과.
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpNo(rs.getInt("emp_no"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhone(rs.getString("emp_phone"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 사원 등록 기능
	boolean insertEmp(Employee emp) {
		getconn();
		String sql = "insert into emp (emp_no, emp_name, emp_phone, email, salary,hire_date) "
				+ "values (emp_seq.nextval, ?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getEmpName());
			psmt.setString(2, emp.getPhone());
			psmt.setString(3, emp.getEmail());
			psmt.setInt(4, emp.getSalary());
			psmt.setString(5, emp.getHireDate());

			int r = psmt.executeUpdate(); // 처리된 건수 반환.
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 예외발성하거나 처리된 건수가 0일 경우.
	}

	// 수정기능.
	boolean updateEmp(Employee emp) {
		getconn();
		String sql = "update emp";
		sql += "   set salary = ?";
		sql += "   , email = ?";
		sql += "   where emp_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getSalary());
			psmt.setString(2, emp.getEmail());
			psmt.setInt(3, emp.getEmpNo());

			int r = psmt.executeUpdate(); //
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}//

	boolean deleteEmp(int eno) {
		getconn();
		String sql = "delete emp where emp_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, eno);

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
