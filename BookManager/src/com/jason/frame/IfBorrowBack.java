package com.jason.frame;//com.jason.frame.IfBorrowBack.java
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class IfBorrowBack{
	public static boolean findBook(String bookid,String readerid){
		String sql;
		sql="select * from borrow where book_id='"+bookid+"' and reader_id='"+readerid+"' and if_back='��'";
		ResultSet rs=DbOp.executeQuery(sql);
		try{
			if(rs.next())
				return true;
			else
				return false;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"���ݿ��ѯʧ��");
		}
		return true;
	}
}