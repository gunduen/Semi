package message.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import message.model.dao.MessageDAO;
import message.model.vo.Message;
import message.model.vo.PageData;

public class MessageService {
	
	private JDBCTemplate factory;
	
	public MessageService() {
		factory = JDBCTemplate.getConnection();
	}
	
	//1. messageList
	public PageData messageList(int currentPage) {
		Connection conn = null;
		ArrayList<Message> mList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new MessageDAO().messageList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new MessageDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	//1. messageSenderList()
	public PageData messageSenderList(int currentPage, String sender) {
		Connection conn = null;
		ArrayList<Message> mList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new MessageDAO().messageSenderList(conn, currentPage, recordCountPerPage, sender));
			pd.setPageNavi(new MessageDAO().getPageNaviSender(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	//1. messageReceiverList()
		public PageData messageReceiverList(int currentPage, String receiver) {
			Connection conn = null;
			ArrayList<Message> mList = null;
			int recordCountPerPage = 10;
			int naviCountPerPage = 10;
			PageData pd = new PageData();
			try {
				conn = factory.createConnection();
				pd.setPageList(new MessageDAO().messageReceiverList(conn, currentPage, recordCountPerPage, receiver));
				pd.setPageNavi(new MessageDAO().getPageNaviReceiver(conn, currentPage, recordCountPerPage, naviCountPerPage));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(conn);
			}
			return pd;
		}
	
	//2. selectMessage()
	public Message selectMessage(int messageNo) {
		Message message = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			message = new MessageDAO().selectMessage(conn, messageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return message;
	}
	
	//3. insertMessage()
	public int insertMessage(Message message, String userId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessage(conn, message, userId);
			JDBCTemplate.close(conn);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	//4. deleteMessage()
	public int deleteMessage(int messageNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().deleteMessage(conn, messageNo);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

}
