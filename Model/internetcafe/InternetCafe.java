package internetcafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import internetcafe.product.Administrator;
import internetcafe.product.Customer;
import internetcafe.product.Member;
import internetcafe.product.Nonmember;
import internetcafe.product.UserInfo;
import internetcafe.user.Product;

/*
* Ŭ������ : InternetCafe // �ǽù� Ŭ����
* 
* ���� ���� v.1.0
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class InternetCafe {

	private static final int MAX_ROW_INDEX = 4;
	private static final int MAX_COL_INDEX = 5;
	private HashMap<String, Member> memberList;
	private List<Nonmember> nonmemberList;
	private List<Product> foodlist;
	private Customer[][] seat;

	public InternetCafe() {
		this.memberList = new HashMap<String, Member>();
		this.seat = new Customer[MAX_ROW_INDEX][MAX_COL_INDEX];
		this.nonmemberList = new ArrayList<Nonmember>();
		this.foodlist = new ArrayList<Product>();
	}

	public Member login(Scanner sc) {
		System.out.println("���̵��Է��ϼ���");
		String id = sc.next();
		System.out.println("��й�ȣ�Է��ϼ���");
		String pwd = sc.next();
		Member loginUser = memberList.get(id);
		if (loginUser != null && loginUser.getId().equals(id) && loginUser.getPwd().equals(pwd)) {
			System.out.println("�α��� �Ǿ����ϴ�");
			return loginUser;
		} else {
			System.out.println("�߸� �Է� �Ǿ����ϴ� , �ٽ� �Է����ּ���");
		}

		return null;
	}

	public void showMain(Scanner sc) {
		System.out.println("=====������� 4���ǽù�=====");
		System.out.println("�̿��Ϸ��� �ƹ�Ű�� �Է��ϼ���: ");
		String touch = sc.nextLine();
		
		if (touch.equals("admin") == true) {
			adminMenu(sc);	
		}
		showSeat();
	}

	public void showSeat() {
		for (int i = 0; i < this.seat.length; i++) {
			for (int j = 0; j < this.seat[i].length; j++) {
				System.out.printf("[%s]", this.seat[i][j] == null ? "�̿밡��" : "!�̿���!");
			}
			System.out.println();
		}

	}

	private void selectNonMember(Scanner sc) {
		while (true) {
			System.out.println("1. ī��߱�");
			System.out.println("2. ȸ������");
			System.out.println("3. ����");

			int key = sc.nextInt();
			sc.nextLine();

			switch (key) {
			case 1:
				int cardnum = ((int) (Math.random() * 20) + 1);
				break;

			case 2:
				signUp(sc);
				break;

			case 3:
				System.out.println("�̿����ּż� �����մϴ�");
				showMain(sc);

			default:
				System.out.println("�߸��� �޴��Դϴ�. �ٽ� �Է����ּ���. ");
				break;
			}

		}

	}

	public void selectCustomer(Scanner sc) {
		while (true) {
			System.out.println("1. ȸ��");
			System.out.println("2. ��ȸ��");
			System.out.println("3. ����");

			int s_c_key = sc.nextInt();
			switch (s_c_key) {
			case 1:
				System.out.println("�α����� ���ּ���");
				signUp(sc);
				break;

			case 2:
				selectNonMember(sc);
				break;

			case 3:
				System.out.println("�̿����ּż� �����մϴ�");
				showMain(sc);

			default:
				System.out.println("�߸��� �޴��Դϴ�. �ٽ� �Է����ּ���. ");
				break;
			}

		}

	}

	public void selectSeat(Scanner sc) {

		int row = 0;
		int col = 0;

		do {

			try {

				System.out.println("=====�¼��� �������ּ���=====");
				System.out.print("ex) 1,3: ");
				String inputSeat = sc.nextLine();
				String[] seatArr = inputSeat.trim().replace(" ", "").split(",");

				row = Integer.parseInt(seatArr[0]) - 1; // �ε����Է��̴ϱ�
				col = Integer.parseInt(seatArr[1]) - 1; // �ε����Է��̴ϱ�

				if (row > MAX_ROW_INDEX || col > MAX_COL_INDEX) {
					throw new Exception(); // �迭�� ��� �ڸ�
				}

				if (seat[row][col] == null) {
					// seat[row][col] = -> ,�������� ������ int�� �ٲٰ� row�� col�� �������ְ�!!
					System.out.println("�¼����� �Ϸ�");
					selectCustomer(sc);

				} else {
					throw new Exception(); // �̹� ����� �ڸ�
				}

			} catch (Exception e) {
				System.out.println("�¼��� ������ �� �����ϴ�. �ٽ� �������ּ���");
				System.out.println("(1,1)���� (5,4)������ �¼��� �����մϴ�.");
			}

		} while (true);

	}
	
	private void adminMenu(Scanner sc) {
		System.out.println("������ �������Դϴ�.");
		System.out.println("��й�ȣ�� �Է����ּ���>> ");
		String admin_pw = sc.nextLine();

		// ����� ������ �����ڸ޴� ����
		if (admin_pw.equals("admin") == true) {
			Administrator admin = new Administrator();
			System.out.println("1. �޴��߰�    2.�޴�����    3.����   4.�̿��ڸ��");
			int admin_select = sc.nextInt();
			sc.nextLine();

			switch (admin_select) {

			case 1:
				admin.addProduct();
				break;

			case 2:
				admin.delProduct();
				break;

			case 3:
				admin.showTotalMoney();
				break;

			case 4:
				admin.showMemberList();
				break;

			case 5:
				System.out.println("�̿����ּż� �����մϴ�");
				showMain(sc);

			default:
				System.out.println("�߸��� �޴��Դϴ�. �ٽ� �Է����ּ���. ");
				break;

			}

		}

	}

	public void signUp(Scanner sc) { // ȸ������
		Member member = new Member();
		UserInfo userInfo = new UserInfo();
		System.out.println("���̵� �Է��ϼ���");
		member.setId(sc.next());
		System.out.println("��й�ȣ�� �Է��ϼ���");
		member.setPwd(sc.next());
		System.out.println("�̸��� �Է��ϼ���");
		member.setName(sc.next());
		
		memberList.put(member.getId(), member);
	}

	public HashMap<String, Member> getmemberList() {
		return memberList;
	}

	public void setmemberList(HashMap<String, Member> memberList) {
		this.memberList = memberList;
	}

	public List<Nonmember> getNonmemberList() {
		return nonmemberList;
	}

	public void setNonmemberList(List<Nonmember> nonmemberList) {
		this.nonmemberList = nonmemberList;
	}

	public List<Product> getFoodlist() {
		return foodlist;
	}

	public void setFoodlist(List<Product> foodlist) {
		this.foodlist = foodlist;
	}

	public Customer[][] getSeat() {
		return seat;
	}

	public void setSeat(Customer[][] seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "InternetCafe [memberList=" + memberList + ", nonmemberList=" + nonmemberList + ", foodlist=" + foodlist
				+ ", seat=" + Arrays.toString(seat) + "]";
	}

}