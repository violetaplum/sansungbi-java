package taja;

import javax.swing.JOptionPane;

public class Rain extends Thread { // �꼺�� �޼ҵ� �����带 ��ӹ޴´�.

	public int life = 3; // �ʱ� ������ �� = 3

	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				for (int j = 0; j < Gui.arrJlabel.length; j++) {
					int x = Gui.arrJlabel[j].getX(); // Gui�� arrJLabel�� x��ǥ����
														// x������ �����Ѵ�
					int y = Gui.arrJlabel[j].getY();// Gui�� arrJLabel�� y��ǥ����
													// y������ �����Ѵ�
					y += 10; // ��ǥ�� 10�� ������Ų��
					Gui.arrJlabel[j].setLocation(x, y); // arrJLabel�� ��ġ�� �ٽ�
														// �����Ѵ�
					if (Gui.arrJlabel[j].isVisible() && Gui.arrJlabel[j].getY() > 400) {
						Gui.arrJlabel[j].setVisible(false); // ���� arrJlabe�� Y��ǥ��
															// 400�̻��� �Ǹ�(�ٴٿ�
															// �����ϸ�)
						life -= 1; // �������� 1���ҽ�Ų��
						switch (life) { // �������� switch case��
						case 2: // life�� 2�� ��
							Gui.lifeMark[2].setVisible(false); // lifeMark ���̺�
																// �ϳ��� �Ⱥ��̰� �Ѵ�
							break;
						case 1:
							Gui.lifeMark[1].setVisible(false); // lifeMark ���̺�
																// �ϳ��� �Ⱥ��̰� �Ѵ�
							break;
						case 0:
							Gui.lifeMark[0].setVisible(false); // lifeMark ���̺�
																// �ϳ��� �Ⱥ��̰� �Ѵ�
							JOptionPane.showMessageDialog(Gui.lifeMark[0], "Game Over", "Game Over",
									JOptionPane.INFORMATION_MESSAGE); /*
																		 * ��������
																		 * 0��
																		 * �Ǿ����Ƿ�
																		 * Game
																		 * Over���
																		 * �޼�����
																		 * ����Ѵ�
																		 */
							System.exit(0); // ���α׷��� �����Ų��
							break;

						}
					}

				}
				Thread.sleep(Gui.speed); // Gui�� speed������ŭ �������� �ӵ��� �����Ѵ�
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
