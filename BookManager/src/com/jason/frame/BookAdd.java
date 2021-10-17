package com.jason.frame;//com.jason.frame.BookAdd.java
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
public class BookAdd extends Frame implements ActionListener{
	Toolkit tool= getToolkit();
	String url="src/bookbk.png";
	Image img=tool.getImage(url);
		public void paint(Graphics g){
		g.drawImage(img,0,0,this);
	}

	public void clearAndSetBookId(){
		for(int j=0;j<booktxt.length;j++){
			booktxt[j].setText("");
		}
		String str=getInsertOrderedList();
		booktxt[0].setEditable(false);
		booktxt[0].setText(str);

	}

	String[] lbname={"ͼ����","ͼ������","ͼ��ҳ��","ͼ������","��     ��","�� �� ��","����ʱ��","����","�������","��������"};
	Label[] booklb=new Label[10];
	TextField[] booktxt=new TextField[10];
	Button savebtn=new Button("Save");
	Button closebtn=new Button("Close");
	Choice booktype=new Choice();
	public BookAdd(){
		setTitle("�������");
		setLayout(null);
		setSize(500,250);
		setResizable(false);
		//this.setOpaque(false);
		this.setForeground(Color.BLACK);
		int lx=50,ly=50;
		booktype.add("�������");
		booktype.add("ͼ�����");
		booktype.add("����");
		booktype.add("�Ƽ�");
		booktype.add("��ѧ");
		booktype.add("��ʷ");
		booktype.add("�ٿ�");
		booktype.add("Ӣ��");
		booktype.add("�����");
		booktype.add("Internet");
		booktype.add("��ѧ");
		String str=getInsertOrderedList();
		for(int i=0;i<booklb.length;i++){
			if(lx>240){
				lx=50;
				ly=ly+30;
			}
			booklb[i]=new Label(lbname[i]);
			booklb[i].setBounds(lx,ly,50,20);
			booktxt[i]=new TextField();
			booktxt[i].setBounds(lx+60,ly,100,20);
			lx=lx+190;
			add(booklb[i]);add(booktxt[i]);
			}
		booktxt[0].setEditable(false);
		booktxt[0].setText(str);

		booktxt[9].setVisible(false);
		booktype.setBounds(300,170,100,20);
		add(booktype);
		savebtn.setBounds(150,210,80,25);
		closebtn.setBounds(280,210,80,25);
		add(savebtn);add(closebtn);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				DbOp.close();
				dispose();	
			}
		});
		savebtn.addActionListener(this);
		closebtn.addActionListener(this);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static String getInsertOrderedList(){
		String returnstring="";
		String sql="select * from book";
		
		try{
		int count=0;
		ResultSet rs=DbOp.executeQuery(sql);
		while(rs.next()){
			
			count++;
		}
		String[] allid=new String[count];
		int[] intofid=new int[count];
	
		int i=0;
		ResultSet rs1=DbOp.executeQuery(sql);
		while(rs1.next()){
			allid[i]=rs1.getString("id");
			String mystr=allid[i].substring(1);
			intofid[i]=Integer.parseInt(mystr);
			i++;
		}
		int temp=-1;
		for(int j=0;j<intofid.length;j++){
			if(intofid[j]>temp){
				temp=intofid[j];		
			}
		}
		returnstring=String.valueOf(temp+1);
		int len=returnstring.length();
		for(int f=0;f<5-len;f++){
			returnstring="0"+returnstring;
			
		}
		returnstring="A"+returnstring;
		DbOp.close();
		}catch(SQLException ee){

		}
		
		
		return returnstring;
	}

	public  void actionPerformed(ActionEvent e){
		Object ob=e.getSource();
		if(ob==savebtn){
			savebtnActionPerformed();
			clearAndSetBookId();
		}
		if(ob==closebtn){
				DbOp.close();
				dispose();				
			
		}
	}
	public  void savebtnActionPerformed(){
		String id=booktxt[0].getText();
		String typestr=booktype.getSelectedItem().toString();
		String[] inputstring=new String[9];
		boolean emptyequals=false;
		for(int i=0;i<inputstring.length;i++){
			inputstring[i]=booktxt[i].getText();
			if(inputstring[i].equals("")){
				JOptionPane.showMessageDialog(null,"�������д����");
				return;
			}
		}
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"ͼ���Ų���Ϊ��");
			return;
		}
		if(IfBookIdExit(id)){
			JOptionPane.showMessageDialog(null,"ͼ�����Ѵ���");
			return;
		}
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			sdf.parse(inputstring[6]);
			float price=Float.parseFloat(inputstring[7]);
			int stock= Integer.parseInt(inputstring[8]);
			int page=Integer.parseInt(inputstring[2]);
			String sql="insert into book(id,bookname,booktype,author,translator,publisher,publish_time,price,stock,page)";
			sql=sql+"values('"+id+"','"+inputstring[1]+"','"+typestr+"','"+inputstring[3]+"','"+inputstring[4]+"','";
			sql=sql+inputstring[5]+"','"+inputstring[6]+"',"+price+","+stock+","+page+")";
			int i=DbOp.executeUpdate(sql);
			if(i==1){
				JOptionPane.showMessageDialog(null,"ͼ����ӳɹ���");
				clearAllText();
			}
		}catch(ParseException e2){
			JOptionPane.showMessageDialog(null,"����ʱ���ʽ������ȷΪ����-�£�");
		}catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(null,"����������۸�ҳ������ӦΪ����");
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
		new BookAdd();
	}
}