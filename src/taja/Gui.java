package taja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JPanel implements ActionListener, KeyListener {
	public static JLabel[] arrJlabel = new JLabel[9]; /*
														 * JLabel �迭 ����, 9���� ������
														 * �����Ƿ� 9���� �����Ѵ�.
														 * RainŬ�������� ����ؾ� �ϹǷ�
														 * ����(static) �ʵ�
														 */
	public static JLabel[] lifeMark = new JLabel[3]; /*
														 * �Ǵٸ� JLabel �迭, life��
														 * 3���̹Ƿ� 3�� ����
														 */
	public static int speed = 1000; // ���ڰ� �������� �ӵ��� �������� ������ ������ �����Ѵ�. �ʱⰪ�� 1000
	private JLabel numLabel, nameLabel, resultName, resultNumber, resultAc, resultTime;
	private JButton startButton, lowButton, highButton, midButton, quitButton; // JButton
																				// ����
	private JTextField inputText, inputName, inputNum; // JTextField ����
	private Random myRandom = new Random(); // �����Լ� ����
	totalPlayTime total_play_time = new totalPlayTime(); // �ð� Ÿ�̸� ������ Ŭ���� ����
	Rain data_rain = new Rain(); // ���ڰ� ���������ϴ� ������ Ŭ���� ����
	WordData word_create = new WordData(); // ������ �����ϴ� Ŭ���� ����
	private float tryCount = 0; // �õ� Ƚ�� ����
	private float correctCount = 0; // ���� Ƚ�� ����
	private int correctPercent; // ���߷� ����
	public static ImageIcon icon, buttonIcon, buttonOnclick, lifeIcon, lowIcon, lowIconClick, midIcon, midIconClick,
			highIcon, highIconClick; // ImageIcon ����
	private String studentName, studentNumber; // �й��� , �л��̸� ���� String���� ����

	public Gui() {
		setSize(800, 596); // �г� ����� 800x600���� �����Ѵ�
		setLayout(null); // ��ġ�� ���밪 ��ġ�� �����ϱ� ������, ���̾ƿ��� null�� �����Ѵ�

		icon = new ImageIcon("img/background.jpg");
		buttonIcon = new ImageIcon("img/button.png");
		buttonOnclick = new ImageIcon("img/buttonclick.png");
		lifeIcon = new ImageIcon("img/life3.png");
		lowIcon = new ImageIcon("img/low.png");
		lowIconClick = new ImageIcon("img/lowclick.png");
		midIcon = new ImageIcon("img/mid.png");
		midIconClick = new ImageIcon("img/midclick.png");
		highIcon = new ImageIcon("img/high.png");
		highIconClick = new ImageIcon("img/highclick.png");

		startButton = new JButton(buttonIcon);
		startButton.setRolloverIcon(buttonOnclick); // startButton�� ���콺��
													// �����ٳ��������� ������
		startButton.setOpaque(false); // startButton�� ���̰� �Ѵ�
		startButton.setBounds(560, 360, 100, 43); // strartButton�� ��ǥ��,���� ����
		add(startButton); // Gui JPanel�� startButton�� �߰��Ѵ�.
		startButton.addActionListener(this); // startButton�� ActionListener �߰�
		startButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.setContentAreaFilled(false); // startButton�� �������� �����.

		lowButton = new JButton(lowIcon);
		lowButton.setOpaque(false);
		lowButton.setBounds(555, 310, 40, 43);
		add(lowButton);
		lowButton.addActionListener(this);
		lowButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lowButton.setBorderPainted(false);
		lowButton.setFocusPainted(false);
		lowButton.setContentAreaFilled(false); // ���̵� �� ��ư�� �߰��ϰ� �������� �����

		midButton = new JButton(midIcon);
		midButton.setOpaque(false);
		midButton.setBounds(590, 310, 40, 43);
		add(midButton);
		midButton.addActionListener(this);
		midButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		midButton.setBorderPainted(false);
		midButton.setFocusPainted(false);
		midButton.setContentAreaFilled(false); // ���̵� �� ��ư�� �߰��ϰ� �������� �����

		highButton = new JButton(highIcon);
		highButton.setOpaque(false);
		highButton.setBounds(625, 310, 40, 43);
		add(highButton);
		highButton.addActionListener(this);
		highButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		highButton.setBorderPainted(false);
		highButton.setFocusPainted(false);
		highButton.setContentAreaFilled(false); // ���̵� �� ��ư�� �߰��ϰ� �������� �����

		inputNum = new JTextField(10);
		inputNum.setOpaque(false);
		inputNum.setBounds(360, 295, 180, 35);
		add(inputNum);
		inputNum.setBorder(javax.swing.BorderFactory
				.createEmptyBorder());/* �й� �Է� �ؽ�Ʈ�ʵ� �߰��ϰ� �������� ����� */

		inputName = new JTextField(10);
		inputName.setOpaque(false);
		inputName.setBounds(360, 370, 180, 35);
		add(inputName);
		inputName.setBorder(javax.swing.BorderFactory
				.createEmptyBorder()); /* �̸� �Է� �ؽ�Ʈ�ʵ� �߰��ϰ� �������� ����� */
		total_play_time.playTime.setBounds(390, 0, 200, 50);
		total_play_time.playTime.setFont(new Font("Dialog", Font.BOLD, 30));
		add(total_play_time.playTime);
		total_play_time.playTime.setVisible(false); // Ÿ�̸��߰��ϰ�, �Ⱥ��̰� �Ѵ�
		word_create.create();// word_createŬ�������� �ܾ �����ϴ� create�޼ҵ带 �����Ѵ�

	}

	@Override
	public void actionPerformed(ActionEvent e) { // ��ư�̺�Ʈ ����
		if (e.getSource() == startButton) { // startButton�� Ŭ���ϰ� �Ǹ�
			studentName = inputName.getText(); // studentName�� inputName�ؽ�Ʈ�ʵ忡
												// �Է��� ���� �ȴ�.
			studentNumber = inputNum.getText(); // studentNumber�� inputNum�ؽ�Ʈ�ʵ忡
												// �Է��� ���� �ȴ�.
			nameLabel = new JLabel(studentName);
			numLabel = new JLabel(studentNumber);
			firstStart(); // firstStart �޼ҵ� ����

		}

		else if (e.getSource() == lowButton) { // lowButton ��ư�� Ŭ���ϰ� �Ǹ�
			lowButton.setIcon(lowIconClick);
			midButton.setIcon(midIcon);
			highButton.setIcon(highIcon);
			speed = 1200; // lowButton�������� lowIconClick���������� �ٲٰ�, speed�� 1200����
							// �����Ѵ�.
		}

		else if (e.getSource() == midButton) {// midButton ��ư�� Ŭ���ϰ� �Ǹ�
			midButton.setIcon(midIconClick);
			lowButton.setIcon(lowIcon);
			highButton.setIcon(highIcon);
			speed = 700; // lowButton���� �ٲٰ�, speed�� 700���� �����Ѵ�.

		}

		else if (e.getSource() == highButton) {// highButton ��ư�� Ŭ���ϰ� �Ǹ�
			highButton.setIcon(highIconClick);
			lowButton.setIcon(lowIcon);
			midButton.setIcon(midIcon);
			speed = 200;// highButton���� �ٲٰ�, speed�� 200���� �����Ѵ�.

		} else if (e.getSource() == quitButton) { // quit��ư�� ������
			System.exit(0); // ���α׷��� �����Ѵ�

		}
	}

	@Override
	public void keyPressed(KeyEvent e) { // Ű�̺�Ʈ ����
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) { // ���Ͱ� ������
			tryCount++; // �õ� ȸ�� 1 ����
			removeAnswer(); // ����ó�� �޼ҵ� ����
			//endAnswer(); // ��� ������ ���� �޼ҵ� ����
			try {
				endAnswer2();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void removeAnswer() { // ����ó�� �޼ҵ�
		for (int i = 0; i < 9; i++) {
			if (inputText.getText().equals(
					word_create.arr.get(i))) { /* �Էµ� ���� ��̸���Ʈ�� ��� ���� ��ġ�ϸ� */
				arrJlabel[i].setVisible(false);
				correctCount++;
			} // �� JLabel�� �Ⱥ��̰� �ϰ�, correctCount(���� ��)�� 1 ������Ų��
		}
		inputText.setText("");
		/*
		 * JLabel Visible False ��, TextLabel�� ��ĭ���� �ξ �ٷ� �Է��Ҽ� �ְ� �Ѵ�.
		 */

	}
//--------------------------------------------------------new GUI
	private void endAnswer2() throws Exception
	{

		// ������ ������ ���� �޼ҵ�(������ ��� ������ ��)
		if (arrJlabel[0].isVisible() == false && arrJlabel[1].isVisible() == false && arrJlabel[2].isVisible() == false
				&& arrJlabel[3].isVisible() == false && arrJlabel[4].isVisible() == false
				&& arrJlabel[5].isVisible() == false && arrJlabel[6].isVisible() == false
				&& arrJlabel[7].isVisible() == false && arrJlabel[8].isVisible() == false) { // ���
			/* JLabel�� ������ ������(��,�Ϸ�Ǹ�) */

			for (int i = 0; i < arrJlabel.length; i++) {
				arrJlabel[i].setVisible(true);
				arrJlabel[i].setFont(new Font("����", Font.BOLD, 12));
				arrJlabel[i].setForeground(Color.BLUE);
				;
				arrJlabel[i].setLocation(i * 90, 0);
			} // ������ ���Դ� �ܾ���� ���ӻ�ܿ� ǥ���� �ش�

			data_rain.stop(); // �꼺�� ������ ����
			total_play_time.stop(); // �ð� Ÿ�̸� ������ ����.
			correctPercent = Math.round((correctCount / tryCount)
					* 100); /*
			 * ���߷��� �����. ����ȸ��/��Ƚ�� * 100�ؼ� �Ҽ��� ����
			 */
			icon = new ImageIcon("img/background3.jpg"); // ����� background3����
			// �ٲ۴�

			resultNumber = new JLabel(studentNumber);
			resultNumber.setBounds(360, 156, 200, 100);
			resultNumber.setFont(new Font("����", Font.BOLD, 22));
			resultNumber.setForeground(Color.WHITE);
			add(resultNumber); // ���â�� �й����̺��� �����ؼ� �߰��Ѵ�

			resultName = new JLabel(studentName);
			resultName.setBounds(360, 206, 200, 100);
			resultName.setFont(new Font("����", Font.BOLD, 22));
			resultName.setForeground(Color.WHITE);
			add(resultName);// ���â�� �̸����̺��� �����ؼ� �߰��Ѵ�

			resultTime = new JLabel((Integer.toString(total_play_time.gamePlayTime) + "��"));
			/*
			 * ���ӽð��� String���� �ٲپ JLabel�� �����
			 */
			resultTime.setBounds(360, 256, 200, 100);
			resultTime.setFont(new Font("����", Font.BOLD, 22));
			resultTime.setForeground(Color.WHITE);
			add(resultTime); // ���â�� �ð� ���̺��� �����ؼ� �߰��Ѵ�

			resultAc = new JLabel(Integer.toString(correctPercent) + "%");
			/*
			 * ���߷��� String���� �ٲپ JLabel�� �����.
			 */
			resultAc.setBounds(360, 306, 200, 100);
			resultAc.setFont(new Font("����", Font.BOLD, 22));
			resultAc.setForeground(Color.WHITE);
			add(resultAc); // ���â�� ���߷� ���̺��� �����ؼ� �߰��Ѵ�

			quitButton = new JButton();
			quitButton.setOpaque(false);
			quitButton.setBounds(360, 400, 80, 30);
			add(quitButton);
			quitButton.addActionListener(this);
			quitButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			quitButton.setBorderPainted(false);
			quitButton.setFocusPainted(false);
			quitButton.setContentAreaFilled(false); // �����ϱ� ��ư�� ���� �������� ��������
			// ActionListener �߰��Ѵ�
			//--------------------------------------------------------------------DB start
			Connection con1 = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con1 = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
			System.out.println("Connected");
			String insert = "insert into score(id,name,time) values(?,?,?)";
			String id = studentNumber;
			String name = studentName;
			String time = Integer.toString(total_play_time.gamePlayTime);
			PreparedStatement inps = con1.prepareStatement(insert);
			inps.setString(1,id);
			inps.setString(2,name);
			inps.setString(3,time);
			int rs = inps.executeUpdate();
			System.out.println("I Inserted "+rs+"!!");

			String sql = "select name,time from score order by time asc";
			PreparedStatement ps = con1.prepareStatement(sql);
			ResultSet rss = ps.executeQuery();

			String str="";

			if(rss.next())
			{
				do{
					String showName = rss.getString(1);
					String showTime = rss.getString(2);
				}
				while(rss.next());
			}





			//------------------------------------------------------------------DB end
			for (int j = 0; j < lifeMark.length; j++) {
				lifeMark[j].setVisible(false);
			} // ������ ������ �ʰ� �Ѵ�
		}
	}

//--------------------------------------------------------------new GUI

	private void endAnswer() { // ������ ������ ���� �޼ҵ�(������ ��� ������ ��)
		if (arrJlabel[0].isVisible() == false && arrJlabel[1].isVisible() == false && arrJlabel[2].isVisible() == false
				&& arrJlabel[3].isVisible() == false && arrJlabel[4].isVisible() == false
				&& arrJlabel[5].isVisible() == false && arrJlabel[6].isVisible() == false
				&& arrJlabel[7].isVisible() == false && arrJlabel[8].isVisible() == false) { // ���
			/* JLabel�� ������ ������(��,�Ϸ�Ǹ�) */

			for (int i = 0; i < arrJlabel.length; i++) {
				arrJlabel[i].setVisible(true);
				arrJlabel[i].setFont(new Font("����", Font.BOLD, 12));
				arrJlabel[i].setForeground(Color.BLUE);
				;
				arrJlabel[i].setLocation(i * 90, 0);
			} // ������ ���Դ� �ܾ���� ���ӻ�ܿ� ǥ���� �ش�

			data_rain.stop(); // �꼺�� ������ ����
			total_play_time.stop(); // �ð� Ÿ�̸� ������ ����.
			correctPercent = Math.round((correctCount / tryCount)
					* 100); /*
							 * ���߷��� �����. ����ȸ��/��Ƚ�� * 100�ؼ� �Ҽ��� ����
							 */
			icon = new ImageIcon("img/background3.jpg"); // ����� background3����
															// �ٲ۴�

			resultNumber = new JLabel(studentNumber);
			resultNumber.setBounds(360, 156, 200, 100);
			resultNumber.setFont(new Font("����", Font.BOLD, 22));
			resultNumber.setForeground(Color.WHITE);
			add(resultNumber); // ���â�� �й����̺��� �����ؼ� �߰��Ѵ�

			resultName = new JLabel(studentName);
			resultName.setBounds(360, 206, 200, 100);
			resultName.setFont(new Font("����", Font.BOLD, 22));
			resultName.setForeground(Color.WHITE);
			add(resultName);// ���â�� �̸����̺��� �����ؼ� �߰��Ѵ�

			resultTime = new JLabel((Integer.toString(total_play_time.gamePlayTime) + "��"));
			/*
			 * ���ӽð��� String���� �ٲپ JLabel�� �����
			 */
			resultTime.setBounds(360, 256, 200, 100);
			resultTime.setFont(new Font("����", Font.BOLD, 22));
			resultTime.setForeground(Color.WHITE);
			add(resultTime); // ���â�� �ð� ���̺��� �����ؼ� �߰��Ѵ�

			resultAc = new JLabel(Integer.toString(correctPercent) + "%");
			/*
			 * ���߷��� String���� �ٲپ JLabel�� �����.
			 */
			resultAc.setBounds(360, 306, 200, 100);
			resultAc.setFont(new Font("����", Font.BOLD, 22));
			resultAc.setForeground(Color.WHITE);
			add(resultAc); // ���â�� ���߷� ���̺��� �����ؼ� �߰��Ѵ�

			quitButton = new JButton();
			quitButton.setOpaque(false);
			quitButton.setBounds(360, 400, 80, 30);
			add(quitButton);
			quitButton.addActionListener(this);
			quitButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			quitButton.setBorderPainted(false);
			quitButton.setFocusPainted(false);
			quitButton.setContentAreaFilled(false); // �����ϱ� ��ư�� ���� �������� ��������
													// ActionListener �߰��Ѵ�
			for (int j = 0; j < lifeMark.length; j++) {
				lifeMark[j].setVisible(false);
			} // ������ ������ �ʰ� �Ѵ�
		}
	}

	private void firstStart() { // �����ϱ� ��ư�� ������ ���� �޼ҵ�(���ӽ���)
		inputText = new JTextField(2);
		inputText.addKeyListener(this); // �ؽ�Ʈ�ʵ忡 Ű �̺�Ʈ �߰�(����)
		inputText.setBounds(360, 493, 150, 58);
		inputText.setOpaque(false);
		inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		add(inputText); // ���� �Է��ϴ� �ؽ�Ʈ�ʵ带 �������� ���� �߰��Ѵ�

		for (int i = 0; i < lifeMark.length; i++) {
			lifeMark[i] = new JLabel(lifeIcon);
			lifeMark[i].setOpaque(false);
			lifeMark[i].setBounds(0 + (i * 80), 460, 80, 80);
			add(lifeMark[i]);
			lifeMark[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
			lifeMark[i].setVisible(true); // ������ �����ִ� JLabel�� ���� �߰��Ѵ�
		}

		add(numLabel); // �й� JLabel�� �߰��Ѵ�
		add(nameLabel); // �л��̸� JLabel�� �߰��Ѵ�
		numLabel.setBounds(105, 4, 100, 60);
		nameLabel.setBounds(110, 34, 100, 60);

		startButton.setVisible(false); // ���� ��ư �Ⱥ��̰�
		inputName.setVisible(false);
		inputNum.setVisible(false);
		inputText.setVisible(true); // �� �Է�â ���̰� ��
		lowButton.setVisible(false);
		midButton.setVisible(false);
		highButton.setVisible(false);
		icon = new ImageIcon("img/background2.jpg"); // ��� �̹����� �ι�° �̹����� �ٲ�
		
		word_create.shuffle();// word_createŬ�������� �ܾ������ ���� shuffle�޼ҵ带 �����Ѵ�.
		for (int i = 0; i < arrJlabel.length; i++) {
			arrJlabel[i] = new JLabel(
					word_create.arr.get(i)); /*
												 * JLabel�� ���ʴ�� �������� ���Ե� ��̸���Ʈ
												 * Ű������ �ϴ� �ؽ����� ������ �̸����� ����
												 */

			arrJlabel[i].setBounds(0, 0, 150, 20); // arrJLabel�� ���� ����
			arrJlabel[i].setFont(new Font("����", Font.BOLD, 15)); // arrJLabel��
																	// ��Ʈ ����
			arrJlabel[i].setLocation(i * 85, myRandom.nextInt(250) + 10); // arrJLabel��
																			// ��ġ��
																			// �����Ѵ�.
			add(arrJlabel[i]); // arrJLabel�� �гο� �߰��Ѵ�

		}

		for (int j = 0; j < lifeMark.length; j++) {
			lifeMark[j].setVisible(true); // ������Ȳ�� �����ش�
		}
		total_play_time.playTime.setVisible(true); // �ð�Ÿ�̸Ӹ� ���̰� ��
		total_play_time.start(); // �ð� Ÿ�̸� ������ ����
		data_rain.start(); // �꼺�� ������ ����

	}

	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null); // 0,0��ǥ���� �̹����� �Ѹ�
		setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
		super.paintComponent(g);
	}

}
