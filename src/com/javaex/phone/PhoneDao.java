package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자
	// 메소드g/s
	// 메소드일반

	// db접속
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			System.out.println("[접속성공]");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 5. 자원정리
	private void close() {
		
	    try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }

	}

	// phone 저장
	public int personInsert(PersonVo personvo) {
		
		int count = 0;
		
		getConnection();
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			//INSERT INTO PERSON VALUES (seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111');
		    String query ="";
		    query += " INSERT INTO PERSON ";
		    query += " VALUES (seq_person_id.nextval, ?, ?, ?)";
		    
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, personvo.getName());
		    pstmt.setString(2, personvo.getHp());
		    pstmt.setString(3, personvo.getCompany());
		    
		    count = pstmt.executeUpdate();
			
			// 4.결과처리
		    System.out.println("[Dao]" + count + "건이 저장되었습니다.");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		   
		close();
		return count;
	}
	
	// phone 삭제
	public int personDelete(int personId) {
		
		int count = 0;
		getConnection();
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			//DELETE FROM PERSON WHERE person_id = 5;
			String query = "";
			query += " DELETE FROM PERSON ";
			query += " WHERE person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);
			
			count = pstmt.executeUpdate();
			
		    // 4.결과처리
			 System.out.println("[Dao]" + count + "건이 삭제되었습니다.");
		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		close();
		return count;
	}
	
	// phone 수정
	public int personUpdate(PersonVo personVo) {
		
		int count = 0;
		getConnection();

		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			//UPDATE PERSON SET name = '유정재', hp = '010-9999-9999', company = '02-9999-9999' WHERE person_id = 4;
			String query = "";
			query += " UPDATE PERSON ";
			query += " SET 	  name = ?, ";
			query += "  	  hp = ?,";
			query += " 		  company = ? ";
			query += " WHERE person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());
			
			count = pstmt.executeUpdate();
			
		    // 4.결과처리
			 System.out.println("[Dao]" + count + "건이 수정되었습니다.");
		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	// phone 리스트
	public List<PersonVo> getPersonList() {
		
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		
		getConnection();
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT person_id, ";
			query += " 		  name, ";
			query += " 		  hp, ";
			query += " 		  company ";
			query += " FROM person ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getNString("hp");
				String company = rs.getNString("company");
				
				PersonVo pvo = new PersonVo(personId, name, hp, company);
				
				phoneList.add(pvo);
				
			}

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		close();
		return phoneList;
	}
	
	
	// phone 검색
	
	

}
