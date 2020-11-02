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
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try 
		{
			conn = factory.createConnection();
			pd.setPageList(new NoticeDAO().selectList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			pd.setTotalCount(new NoticeDAO().totalCount(conn));
			pd.setRecordCountPerPage(recordCountPerPage);
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
	
	public int plusHits(int notice_No)
	{
		int result = 0;
		Connection conn = null;
		try
		{
			conn = factory.createConnection();
			result = new NoticeDAO().plusHits(conn, notice_No);
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
	
	public PageData noticeSearchList(String search, int currentPage)
	{
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try
		{
			conn = factory.createConnection();
			pd.setPageList(new NoticeDAO().noticeSearchList(conn, search, currentPage, recordCountPerPage));
			pd.setPageNavi(new NoticeDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));
			pd.setTotalCount(new NoticeDAO().searchTotalCount(conn, search));
			pd.setRecordCountPerPage(recordCountPerPage);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public int insertNotice(String notice_Subject, String notice_Contents, String customer_Id)
	{	
		Connection conn = null;
		int result = 0;
		try
		{	
			conn = factory.createConnection();
			result = new NoticeDAO().insertNotice(conn, notice_Subject, notice_Contents, customer_Id);
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

