package miniproject.phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//리스트: 1 SELECT *
//등록: 2 INSERT
//삭제: 3 DELETE
//검색: 4 SELECT * LIKE
//종료 5
public class PhoneBookDAOImpl implements PhoneBookDAO {
	// 공통 접속 메서드
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,
					"C##bituser",
					"bituser");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		}
		
		return conn;
	}
	
	@Override
	public List<PhoneBookVO> getList(){
		Connection conn = null;
		Statement stmt = null;
		// SELECT
		ResultSet rs = null;
		// 결과 객체
		List<PhoneBookVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			// 쿼리문 
			String sql = "SELECT id, name, hp, tel FROM PHONE_BOOK ORDER BY id ASC";
			
			// 쿼리 실행
			rs = stmt.executeQuery(sql);
			
			// ResultSet -> Java 객체로 반환
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				// DTO 객체
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				// DTO 객체 -> List에 추가
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				
			}
		}
		return list;
	}
	
	@Override
	public List<PhoneBookVO> search(String keyword){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PhoneBookVO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM PHONE_BOOK WHERE name LIKE ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			
			// 쿼리 수행
			rs = pstmt.executeQuery();
			
			// 변환 작업
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");
				
				// VO 객체
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				
			}
		}
		return list;
	}
	
	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			// 실행계획
			String sql = "INSERT INTO PHONE_BOOK VALUES(SEQ_PHONE_BOOK_PK.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			// 쿼리 수행
			insertedCount = pstmt.executeUpdate();	
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				
			}
		}
		return 1 == insertedCount;
	}
	
	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM PHONE_BOOK WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			deletedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {
				
			}
		}
		return 1 == deletedCount;
	}

	
		
}
