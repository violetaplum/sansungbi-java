package taja;

import javax.swing.JLabel;

public class totalPlayTime extends Thread { // ���ӽð��� �����ϴ� Ŭ����, ������Ŭ������ ��ӹ���
	public int gamePlayTime; // ������ ���ӽð� ����
	public JLabel playTime = new JLabel("0"); // �ʱⰪ�� 0���� �Ѵ�

	public void run() {
		for (gamePlayTime = 0;; gamePlayTime++) {
			try {
				Thread.sleep(1000); // 1�ʿ� �ѹ��� gamePlayTime ������ ������Ų��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			playTime.setText(gamePlayTime + ""); // playTime���̺��� gamePlayTime������
													// �����Ѵ�
		}
	}
}
