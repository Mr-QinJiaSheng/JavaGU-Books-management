package com.jason.frame;// com.jason.frame.BookQueryImprove.java


import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookQueryImprove extends Frame implements ActionListener {
	private static final long serialVersionUID = 5477686699015910381L;
	TextField seriesName;
	TextArea bookName;
	Button button;

	BookQueryImprove() { // ���췽��
		super("ͼ���ѯ");
		setBounds(150, 150, 300, 300);
		seriesName = new TextField(16);
		bookName = new TextArea(5, 10);
		button = new Button("ȷ��");
		Panel p1 = new Panel(), p2 = new Panel();
		p1.add(new Label("�������������"));
		p1.add(seriesName);
		p2.add(button);
		add(p1, "North");
		add(p2, "South");
		add(bookName, "Center");
		button.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setLocationRelativeTo(null); // ʹ��������Ļ�Ͼ��з���
		setVisible(true); // ��ʾ����
	}

	public void actionPerformed(ActionEvent e) {
		// �����ǰ��������Ϊ��ť
		if (e.getSource() == button) {
			try {
				bookName.setText(null); // ����ı���
				ListStudent();
			} catch (SQLException ee) {
			}
		}
	}

	private void ListStudent() throws SQLException {
		String bn1, bn2, sqlcmd;
		// ��ȡ����Ĵ������
		bn1 = seriesName.getText();
		// ����������Ϊ�գ���ֱ�ӷ���
		if (bn1.length()==0){
			return;
		}
		try {
			// ����JDBC-ODBC��������
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
		}
		// �������ݿ�����
		Connection con = DriverManager.getConnection("jdbc:odbc:javaodbc");
		// ����Statement����
		Statement st = con.createStatement();
		// ����ȫ����¼���õ������ResultSet����
		// ����SQL��䣬like��ʾģ��ƥ�䣬%��ʾ�����ַ���
		// ��������� like '����Ĵ�������ַ���%'����ʾ���Ҵ������Ϊ
		// ������Ĵ�������ַ�����ͷ��ȫ������
		sqlcmd = "select * from ��Ŀ��¼ where ������� like '";
		sqlcmd = sqlcmd + bn1 + "%'";
		// ִ��SQL���
		ResultSet rs = st.executeQuery(sqlcmd);
		boolean boo = false;
		while (rs.next()) {
			bn2 = rs.getString("����"); // ��ȡ����
			bookName.append(bn2 + "\n");
			boo = true; // ��ϵ�д��鲻Ϊ��
		}
		con.close();
		if (boo == false) {
			bookName.append("��ϵ�д��鲻���ڣ�");
		}
	}

	public static void main(String[] args) {
		new BookQueryImprove();
	}
}
