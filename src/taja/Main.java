package taja;

import javax.swing.JFrame;
import java.net.*;
import java.sql.*;

public class Main extends JFrame {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
//-----------------------------------------------------------------------DB
	/*	Connection con1 = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
		System.out.println("Connected");
		String insert = "insert into score(id,name,time) values(?,?,?)";
		String id = new String("201515048");
		String name = new String("JangHeeSoo");
		String time = Integer.toString(10);
		PreparedStatement inps = con1.prepareStatement(insert);
		inps.setString(1,id);
		inps.setString(2,name);
		inps.setString(3,time);
		int rs = inps.executeUpdate();
		System.out.println("I Inserted "+rs+"!!");

		String sql = "select name,time from score order by time desc";
		PreparedStatement ps = con1.prepareStatement(sql);
		ResultSet rss = ps.executeQuery();



		while(rss.next())
		{

		}

		con1.close();

		/*String sql = "select name,time from score order by time ASC";
		PreparedStatement ps = con1.prepareStatement(sql);*/
		//studentName,(Integer.toString(total_play_time.gamePlayTime),

//-----------------------------------------------------------------------DB
		JFrame myFrame = new JFrame("Java Typing Practice"); // JFrame ��ü�� �����Ѵ�
		myFrame.add(new Gui()); // JFrame�� Gui JPanel�� �߰��Ѵ�
		myFrame.setSize(800, 596); // JFrame ������ ����
		myFrame.setLocationRelativeTo(null); // JFrame�� ������� �߾ӿ� ���� �Ѵ�
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}
