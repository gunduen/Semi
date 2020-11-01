package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService 
{
	private JDBCTemplate factory;
	
	public NoticeService() 
	{
		factory = JDBCTemplate.getConnection();
	}
	public PageData selectList(int currentPage)
	{
		ArrayList<Notice> noticeList = null;
		// finally에서 conn을 닫아야 하고
		// 메소드 내에서 전역변수로 쓸 수 있도록 Connection conn을 선언
		Connection conn = null;
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		try 
		{
			conn = factory.createConnection();
			// setPageList() 메소드는 10개의 게시물을 저장
			pd.setPageList(new NoticeDAO().selectList(conn, currentPage, recordCountPerPage));
			// setPageNavi() 메소드는 a링크 10개를 저장
			pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			// noticeList = new NoticeDAO().selectList(conn);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public Notice selectNotice(int notice_No)
	{
		Notice notice = null;
		Connection conn = null;
		try
		{
			conn = factory.createConnection();
			notice = new NoticeDAO().selectNotice(conn, notice_No);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(conn);
		}
		return notice;
	}
	
	public int insertNotice(String notice_Subject, String notice_Contents, String customer_Id)
	{	
		Connection conn = null;
		int result = 0;
		try
		{	// 서비스에서 DB 연결 생성
			conn = factory.createConnection();
			// 생성된 연결을 DAO로 넘겨줌
			result = new NoticeDAO().insertNotice(conn, notice_Subject, notice_Contents, customer_Id);
			// 서비스 클래스에서 하는 역할로 commit/rollback
			if ( result > 0 )
			{
				JDBCTemplate.commit(conn);
			}
			else
			{
				JDBCTemplate.rollback(conn);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public int updateNotice(String notice_Subject, String notice_Contents, int notice_No)
	{
		int result = 0;
		Connection conn = null;
		try
		{
			conn = factory.createConnection();
			result = new NoticeDAO().updateNotice(conn, notice_Subject, notice_Contents, notice_No);
			if( result > 0 )
			{
				JDBCTemplate.commit(conn);
			}
			else
			{
				JDBCTemplate.rollback(conn);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public int deleteNotice(int notice_No)
	{
		int result = 0;
		Connection conn = null;
		try
		{
			conn = factory.createConnection();
			result = new NoticeDAO().deleteNotice(conn, notice_No);
			if( result > 0 )
			{
				JDBCTemplate.commit(conn);
			}
			else
			{
				JDBCTemplate.rollback(conn);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(conn);
		}
		return result;
	}
}

