package com.flow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.flow.db.DBManager;
import com.flow.vo.FreeboardVO;
import com.flow.vo.SchVO;

public class BoardDAO {
	// 생성자 private
	private BoardDAO() {

	}
	// instance도 private
	private static BoardDAO instance = new BoardDAO();
	// setter는 정의 x, getter만 만듬
	public static BoardDAO getInstance() {
		return instance;
	}
	// 글 목록
	public List<FreeboardVO> selectAllBoard() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FreeboardVO> list = new ArrayList<FreeboardVO>();
		try {
			conn = DBManager.getConnection();
			String sql = "select * from flow_freeboard order by freeboard_no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FreeboardVO fvo = new FreeboardVO();
				fvo.setFreeboard_no(rs.getInt("freeboard_no"));
				fvo.setFreeboard_title(rs.getString("freeboard_title"));
				fvo.setFreeboard_content(rs.getString("freeboard_content"));
				fvo.setFile_name(rs.getString("File_name"));
				list.add(fvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}	
	//글 상세보기
	public FreeboardVO selectBoardRead(String freeboard_no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeboardVO fvo = null;
		String sql = "select * from Flow_Freeboard WHERE freeboard_no = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(freeboard_no));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fvo = new FreeboardVO();
				fvo.setFreeboard_no(rs.getInt("freeboard_no"));
				fvo.setFreeboard_title(rs.getString("freeboard_title"));
				fvo.setFreeboard_content(rs.getString("freeboard_content"));
				fvo.setFile_name(rs.getString("file_name"));
				fvo.setPath(rs.getString("path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return fvo;
	}	
	// 글수정하기
	public FreeboardVO updateBoard(FreeboardVO fvo) {
		String sql = "UPDATE Flow_Freeboard SET freeboard_title = ?, freeboard_content = ? WHERE freeboard_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;		
			try {
				conn = DBManager.getConnection();				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fvo.getFreeboard_title());
				pstmt.setString(2, fvo.getFreeboard_content());
				pstmt.setInt(3, fvo.getFreeboard_no());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
			return fvo;
	}	
	//글 삭제하기
	public void deleteBoard(int freeboard_no) {
		String sql = "DELETE FROM Flow_Freeboard WHERE freeboard_no = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;		
			try {
				conn = DBManager.getConnection();				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, freeboard_no);				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
	}	
	//글 검색하기
	public ArrayList<FreeboardVO> getSearchedList(String searchWord){
		
		String sql = "select * from Flow_Freeboard where freeboard_title like ? or freeboard_content like ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<FreeboardVO> list = new ArrayList<FreeboardVO>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%'+searchWord+'%');
			pstmt.setString(2, '%'+searchWord+'%');	
			rs = pstmt.executeQuery();						
			while (rs.next()) {
				FreeboardVO fvo = new FreeboardVO();
				fvo.setFreeboard_no(rs.getInt(1));
				fvo.setFreeboard_title(rs.getString(2));
				fvo.setFreeboard_content(rs.getString(3));
				list.add(fvo);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;	
	}	
	//페이징
	public ArrayList<FreeboardVO> PagingList(int currentPage, String searchWord, String searchType) {
		
		String sql = "select * from "; 
			   sql +="(select rownum as rnum,A.freeboard_no, A.freeboard_title, A.freeboard_content, A.freeboard_writedate ";
			   sql +="from ( "; 
			   sql +="select freeboard_no, freeboard_title, freeboard_content, freeboard_writedate "; 
			   sql +="from flow_freeboard "; 
			   if("freeboard_title".equals(searchType) && !"".equals(searchWord)) {		 
				   sql +="where freeboard_title like '%"+searchWord+"%' ";
			   }
			   if("freeboard_content".equals(searchType) && !"".equals(searchWord)) {
				   sql +="where freeboard_content like '%"+searchWord+"%' ";
			   }	 
               sql +="order by freeboard_no DESC) A) "; 
		       sql +="where rnum between ? and ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<FreeboardVO> list = new ArrayList<FreeboardVO>();
		try {
			int startPage = (currentPage-1)*10+1;
			int endPage = currentPage * 10;
			int index=1;
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(index++, startPage);
			pstmt.setInt(index++, endPage);
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				FreeboardVO fvo = new FreeboardVO();
				fvo.setFreeboard_no(rs.getInt(2));
				fvo.setFreeboard_title(rs.getString(3));
				fvo.setFreeboard_content(rs.getString(4));
				fvo.setFreeboard_writedate(rs.getDate(5));
				list.add(fvo);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return list;
	}	
	//페이징 총 글 갯수
	public int getAllCount(int currentPage, String searchWord, String searchType) {
		String sql ="select count(*) as count "; 
		       sql +="from flow_freeboard "; 
		       if("freeboard_title".equals(searchType) && !"".equals(searchWord)) {		 
		    	   sql +="where freeboard_title like '%"+searchWord+"%' ";
		       }
		       if("freeboard_content".equals(searchType) && !"".equals(searchWord)) {
		    	   sql +="where freeboard_content like '%"+searchWord+"%' ";
		   }
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");			
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return count;
	}
	//통계 pie 그래프 첨부파일 게시글 수
	public HashMap<String, Object> fileNumber() {
		String sql = "select totalCount, fileCount, totalCount - fileCount as fileRemainder\r\n" + 
					 "from (\r\n" + 
					 "select count(file_name) as fileCount, count(*) as totalCount\r\n" + 
					 "from flow_freeboard\r\n" +
					 "where freeboard_writedate >= to_char(add_months(sysdate,-1), 'yyyy-mm-dd')\r\n"+
					 ")";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				totalCount = rs.getInt("totalCount");   ----1
//				fileCount = rs.getInt("fileCount");
//				fileRemainder = rs.getInt("fileRemainder");				
				map.put("totalCount", rs.getInt("totalCount"));        //---2
				map.put("fileCount", rs.getInt("fileCount"));
				map.put("fileRemainder", rs.getInt("fileRemainder"));				
//				map.put("totalCount", totalCount);    //---3
//				map.put("fileCount", fileCount);
//				map.put("fileRemainder", fileRemainder);
				//방식은 두가지 사용 가능 1,3번 같이 사용하거나 2번만 사용하거나 둘다 맞는 방식임
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return map;
	}
	//통계 Line 그래프 
	public HashMap<String, Object> weekChart() {
		String sql = "SELECT week, count(week) as count\r\n" + 
					 "FROM\r\n" + 
					 "(\r\n" + 
					 "SELECT \r\n" + 
					 "case when freeboard_writedate >= SYSDATE-7 then 'oneWeek'\r\n" + 
					 "when freeboard_writedate between SYSDATE-14 and SYSDATE-7 then 'twoWeek'\r\n" + 
					 "when freeboard_writedate between SYSDATE-21 and SYSDATE-14 then 'threeWeek'\r\n" + 
					 "when freeboard_writedate between SYSDATE-28 and SYSDATE-21 then 'fourWeek'\r\n" + 
					 "when freeboard_writedate between SYSDATE-35 and SYSDATE-28 then 'fiveWeek'\r\n" + 
					 "when freeboard_writedate between SYSDATE-42 and SYSDATE-35 then 'sixWeek'\r\n" + 
					 "else '0' END as week\r\n" + 
					 "from Flow_Freeboard\r\n" + 
					 ")\r\n" + 
					 "GROUP BY week";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				map.put(rs.getString("week"), rs.getInt("count"));
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return map;
	}
	//스케줄러 list
	public List<SchVO> selectAllSch() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SchVO> list = new ArrayList<SchVO>();
		try {
			conn = DBManager.getConnection();
			String sql = "select * from Flow_Sch order by index_no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SchVO svo = new SchVO();
				svo.setIndex_no(rs.getInt("index_no"));
				svo.setType1(rs.getString("type1"));
				svo.setType1_Time(rs.getString("type1_Time"));
				svo.setType2(rs.getString("type2"));
				svo.setType2_Time(rs.getString("type2_Time"));
				list.add(svo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
}