package internetcafe.product;

import java.util.HashMap;
import java.util.Scanner;

/*
* Ŭ������ : UserInfo // ���������� �Է��� �� ����ǥ���Ļ���� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 26
* 
* �۾��� : ������
* id,pwd,name �Լ��� isId,isPwd,isName ���� �ٲٰ� ��Ʈ�� ���� �����ϵ��� ����
*/
public class UserInfo {
	// static ����
	static String data = "";
	static String regExp = "";
	static Scanner sc = new Scanner(System.in);

	// -------------------------1--------------------------

	public static String isId(Scanner sc, HashMap<String, Member> memberList) {
		String regExpId = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
		while (true) {
			System.out.println("[1] ID �Է�  ");
			String id = sc.nextLine();
			boolean b1 = id.matches(regExpId);
			if (b1) {
				if (memberList.containsKey(id)) {
					System.out.println("�ߺ��� ID �Դϴ�.");
					continue;
				}
				System.out.println("�ùٸ� Id�����Դϴ�.");
				return id;
			} else {
				System.out.println("ID�� ������ 6~12�� ���̿� ���� ��,�ҹ���,���ڰ� �� �� ������ ù������ ��� ������ �� �ϳ��� �Է��ϼž��մϴ�.");
			}
		}

	}

	// -------------------------2--------------------------

	public static String isPwd() {
		regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
		while (true) {
			System.out.println("[2] ��й�ȣ �Է�:");
			data = sc.nextLine();
			boolean b2 = data.matches(regExp);
			if (b2) {
				System.out.println("�ùٸ� ��й�ȣ �����Դϴ�.");
				return data;
			} else {
				System.out.println("��й�ȣ ������ 6~12�� ���̿� ����빮��, Ư�����ڵ� ���� �� �ֽ��ϴ�.");
			}
		}
	}

	// -------------------------3--------------------------

	public static String isName() {
		regExp = "^[��-����-�R]*$";
		while (true) {
			System.out.println("[3] �̸� �Է� : ");
			data = sc.nextLine();
			boolean b3 = data.matches(regExp);
			if (b3) {
				System.out.println("�ùٸ� �̸������Դϴ�.");
				return data;
			} else {
				System.out.println("�̸� ���� �ѱ۸� �Է� �����մϴ�.");
			}
		}
	}
	// -------------------------4--------------------------

	public static void isPhone() {
		System.out.println("[4] �޴��ȣ �Է�: ");
		// �� ���ڸ� 010/ ��� 4�ڸ�/ ������4�ڸ�
		// 010Ȯ������ \d(����,����)
		// {} ��ȣ���� ���ڸ�ŭ ���� ���ڿ� �ݺ�

		String regExp = "(010)-?\\d{4}-?\\d{4}";
		String data = sc.nextLine();

		boolean b4 = data.matches(regExp); // true, false ��ȿ�� �˻�

		if (b4 == true) {
			System.out.println("�ùٸ� �����Դϴ�.");
		} else {
			System.out.println("��Ŀ� ���� �ٽ� �Է����ּ���. ����) 010-xxxx-xxxx�Դϴ�. ");

		}
	}

	// -------------------------5--------------------------
	public static void isEmail() {
		// @ �� ���� ��ҹ��� ���ڸ�
		// @ �� ���� ��ҹ���
		// . �ڿ� ���� ��ҹ���
		// {} ���๮�ڰ� ��Ÿ�� Ƚ�� �Ǵ� ����..?
		System.out.println("[5] �̸��� �Է�");

		String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		String data = sc.nextLine();

		boolean b5 = data.matches(regExp); // true�� false ��ȿ�� �˻�

		if (b5 == true) {
			System.out.println("�ùٸ� �������� �Է��ϼ̽��ϴ�.");
		} else {
			System.out.println("example1006@bit.com�� �������� �Է����ּ���.");
		}

	}

}