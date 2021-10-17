package com.jason.frame;//com.jason.frame.DbOp.java;
//package PublicModule;
import java.sql.*;
import javax.swing.JOptionPane;
public class DbOp{

	/**
	 * mysql数据库信息, 想换成其他数据源的,直接在这里修改
	 */
	private static String driver="com.mysql.cj.jdbc.Driver";
	private static String url="jdbc:mysql://127.0.0.1:3306/book?characterEncoding=utf-8&serverTimezone=GMT";
	private static String username="root";
	private static String password="";

	private static Connection con=null;
	private DbOp(){
		try{
			if(con==null){
				Class.forName(driver);
				con = DriverManager.getConnection(
						url,
						username,
						password);
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"数据库未能打开！");
			System.out.println(e.getMessage());
		}
	}
	public static ResultSet executeQuery(String sql){
		ResultSet rs=null;
		try{
			if(con==null){
				new DbOp();
			}
			rs=con.createStatement().executeQuery(sql);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"数据查询失败！");
			rs=null;
		}
		return rs;
	}
	public static int executeUpdate(String sql){
			int a=0;
		try{
			if(con==null){
				new DbOp();
			}
			a= con.createStatement().executeUpdate(sql);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"数据库更新失败！");			
			a= -1;
		}
		return a;
	}
	public static void close(){
		try{
			if(con!=null){
				
				con.close();
				con=null;
				//JOptionPane.showMessageDialog(null,"数据库已关闭！");
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"数据库未打开！");
		}
	}
}