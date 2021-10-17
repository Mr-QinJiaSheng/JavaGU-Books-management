package com.jason.frame;//com.jason.frame.BookDelete.java
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class BookDelete extends Frame{
	Toolkit tool= getToolkit();
	String url="src/bookbk.png";
	Image img=tool.getImage(url);
		public void paint(Graphics g){
		g.drawImage(img,0,0,this);
	}

	String[] lbname={"ͼ����","ͼ������","ͼ��ҳ��","ͼ������","��     ��","�� �� ��","����ʱ��","����","�������","��������"};
	Label idlb=new Label("ͼ����");
	TextField idtxt=new TextField();
	Label[] booklb=new Label[10];
	TextField[] booktxt=new TextField[10];
	Button deletebtn=new Button("Delete");
	Button closebtn=new Button("Close");
	Button querybtn=new Button("��ѯ");
	public BookDelete(){
		setTitle("ɾ��ͼ��");
		setLayout(null);
		setSize(500,280);setResizable(false);
		int lx=50,ly=80;
		idlb.setBounds(100,40,50,20);
		idtxt.setBounds(160,40,100,20);
		querybtn.setBounds(280,40,80,20);
		add(idlb);add(idtxt);add(querybtn);
		for(int i=0;i<booklb.length;i++){
			if(lx>240){
				lx=50;
				ly=ly+30;
			}
			booklb[i]=new Label(lbname[i]);
			booktxt[i]=new TextField();
			booklb[i].setBounds(lx,ly,50,20);
			booktxt[i].setBounds(lx+60,ly,100,20);
			lx=lx+190;
			add(booklb[i]);add(booktxt[i]);
			booktxt[i].setEditable(false);
		}
		deletebtn.setBounds(150,240,80,25);
		closebtn.setBounds(280,240,80,25);
		add(deletebtn);add(closebtn);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				DbOp.close();
				dispose();	
			}
		});
		querybtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				querybtnActionPerformed();
			}
		});
		deletebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				deletebtnActionPerformed();
			}
		});
		closebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DbOp.close();
				dispose();				
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void deletebtnActionPerformed(){
		String id=idtxt.getText();	
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"ͼ���Ų���Ϊ��");
			return;
		}
		String sql="delete from book where id='"+id+"'";
		int a=DbOp.executeUpdate(sql);
		if(a==1){
			JOptionPane.showMessageDialog(null,"ͼ��ɾ���ɹ�");
			clearAllText();
		}else{
			JOptionPane.showMessageDialog(null,"ͼ��ɾ��ʧ��");
		}
	}
	public  void querybtnActionPerformed(){
		String id=idtxt.getText();
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"ͼ���Ų���Ϊ��");
			return;
		}
		if(IfBookIdExit(id)){
			Book book =BookSelect.SelectBookByID(id);
			String[] infor=book.getBookinformation();
			if(book!=null){
				for(int i=0;i<infor.length;i++){
					booktxt[i].setText(infor[i]);
				}
			}		
		}else{	
			JOptionPane.showMessageDialog(null,"û�и�ͼ��");
		}
	}
	public boolean IfBookIdExit(String id){
		boolean right=false;
		String sql="select*from book where id='"+id+"'";
		ResultSet rs=DbOp.executeQuery(sql);
		try{
			while(rs.next()){
			
				right = true;
			}
				//right = false;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"�޷�������ȡ����");
		}
		return right;
	}
	public void clearAllText(){
		for(int i=0;i<booktxt.length;i++){
			booktxt[i].setText("");
		}
	}

	public static void main(String[] args){
		new BookDelete();
	}
}