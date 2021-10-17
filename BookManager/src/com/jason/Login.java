package com.jason;//com.jason.Login.java
//import PublicModule.com.jason.frame.DbOp;
import com.jason.frame.DbOp;
import com.jason.frame.GlobalVar;
import com.jason.frame.ShowMain;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
public class Login extends Frame{
	Toolkit tool= getToolkit();
	String url="src/login.png";
	Image img=tool.getImage(url);
		public void paint(Graphics g){
		g.drawImage(img,0,0,this);
	}

	TextField text_user,text_pass;
	public Login(){
		this.setTitle("欢迎登陆");
		this.setLayout(null);
		//setOpaque(true);
		this.setSize(260,170);setResizable(false);
		Label userlb=new Label("用户名:");
		Label passlb=new Label("密  码:");
		Button sure=new Button("Login");
		Button cancel=new Button("Cancel");
		text_user=new TextField();
		text_pass=new TextField();
		text_pass.setEchoChar('●');
		userlb.setBounds(30,53,70,20);
		passlb.setBounds(30,83,70,20);
		text_user.setBounds(110,50,120,20);
		text_pass.setBounds(110,80,120,20);
		sure.setBounds(42,120,80,25);
		cancel.setBounds(135,120,80,25);
		this.add(text_user);this.add(text_pass);this.add(sure);this.add(cancel);
		sure.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sureActionListener(e);
				
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DbOp.close();
				dispose();

			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				DbOp.close();
				dispose();
			}
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public void sureActionListener(ActionEvent le){
		String user=text_user.getText();
		String pass=text_pass.getText();
		String is_admin="";
		if(user.equals("")||pass.equals("")){
			JOptionPane.showMessageDialog(null,"密码不能为空，请输入密码");
			return;
		}
		try{
			String sql="select * from user where username="+"'"+user+"'"+"and password="+"'"+pass+"'";
			ResultSet rs=DbOp.executeQuery(sql);
				if(rs.next()){
					is_admin=rs.getString("is_admin");
				}else{
					JOptionPane.showMessageDialog(null,"Wrong that is UserNmae or Password ");
					return;
				}
			GlobalVar.login_user=user;
			ShowMain show=new ShowMain();
			show.setRights(is_admin);
			JOptionPane.showMessageDialog(null,"登录成功!!!");
			dispose();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"the wrong from information");
		}
	}
	public static void main(String[] args){
		new Login();
	}
}