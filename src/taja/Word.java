package taja;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JLabel;

abstract public class Word { // ������ ���� �߻� Ŭ����
	public ArrayList<String> arr = new ArrayList<>(); // ��̸���Ʈ arr ����

	abstract void create(); // create �߻� �޼ҵ�

	abstract void shuffle();

}

class WordData extends Word { // ���������� ���� Ŭ����, �߻�ũ���� Word�� ��ӹ޴´�

	@Override
	public void create() { // ������ �����ϴ� create�޼ҵ� �߻�޼ҵ� create�� �������̵��Ѵ�
		// TODO Auto-generated method stub
		try {
			Scanner inputStream = new Scanner(
					new File("word.txt")); /*
											 * inputStream ��ü ����, �����̸���
											 * word.txt�̴� , word.txt������ ����
											 */
			while (inputStream.hasNextLine()) // word.txt�� ���̻� �ܾ ���� �� ���� ����
				this.arr.add(inputStream.nextLine()); // ��̸���Ʈ arr�� �� �ܾ���� �߰���
		} catch (FileNotFoundException e) { // ������ ã�� �� ���� ��
			System.out.println("File Not Found"); // File Not Found�� ���
		}

	}

	@Override
	void shuffle() {
		// TODO Auto-generated method stub
		Collections.shuffle(
				this.arr); /*
							 * ��̸���Ʈ arr�� ������ �������� ���´�(���� ������ �Ź� �ٸ��� �ؾ��ϱ� ����)
							 */
	}
}
