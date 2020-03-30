package internetcafe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

import internetcafe.user.Administrator;
import internetcafe.user.Member;
import internetcafe.user.UserInfo;

/*
* Ŭ������ : InternetCafe // �ǽù� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class InternetCafe {

	private static final int MAX_ROW_INDEX = 4;
	private static final int MAX_COL_INDEX = 5;
	private HashMap<String, Member> memberList;

	private Member[][] seat;
	private Scanner sc;
	private Administrator admin;

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public InternetCafe() {
		admin = new Administrator("����", "��ġ"); // ������ ���̵� ��й�ȣ �ڵ� ����
		sc = new Scanner(System.in);
		this.memberList = new HashMap<String, Member>();
		this.seat = new Member[MAX_ROW_INDEX][MAX_COL_INDEX];

		File filename = new File("UserList.txt");

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		if (filename.exists()) { // ����Ǿ� �ִ� ������� �ҷ�����
			try {
				fis = new FileInputStream(filename);
				bis = new BufferedInputStream(fis);
				ois = new ObjectInputStream(bis);

				memberList = (HashMap) ois.readObject();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
					bis.close();
					fis.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}

		File foodfile = new File("FoodList.txt");
		if (foodfile.exists()) {
			try {
				fis = new FileInputStream(foodfile);
				bis = new BufferedInputStream(fis);
				ois = new ObjectInputStream(bis);

				admin.setFoodlist((HashMap) ois.readObject());

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					ois.close();
					bis.close();
					fis.close();

				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}

			}
		}

	}

	public void firstMain() { // �ǽù� ó�� ȭ��
		while (true) {
			System.out.println("��-------------------***----------------------��");
			System.out.println("��----------------*****_*****-----------------��");
			System.out.println("��-----------=******************=-------------��");
			System.out.println("��--------==*�����ǽù濡 ���Ű� ȯ���մϴ�*==--------��");
			System.out.println("��-----*********-**************-*********-----��");
			System.out.println("��----------*********-**-********-------------��");
			System.out.println("��-------------*******-*******----------------��");
			System.out.println("��-----------------*-***-*--------------------��");
			System.out.println();
			System.out.println("��--------------�ƹ� Ű�� �Է��Ͻÿ�---------------��");

			String input = sc.nextLine();
			if (input.equals("admin")) {
				adminPageSelect();
			}
			memberSignSelect();

		}
	}

	public String memberSignPage() { // ȸ�� ȸ������ ���� ȭ��
		showSeat();
		System.out.println("��-----------�̿��Ͻ� ���񽺸� �����ϼ���------------��");
		System.out.println("1. ȸ������ �̿�");
		System.out.println("2. ȸ�� ����");
		System.out.println("0. ������");
		System.out.println("��-------------------------------------------��");
		String menu = sc.nextLine();
		return menu;
	}

	public void memberSignSelect() { // 1 �α��� 2 ȸ������ ����
		while (true) {
			String menu = memberSignPage();
			switch (menu) {
			case "1":
				loginSelect();
				break;
			case "2":
				signUp();
				break;
			case "0":
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				System.out.println("�޴��� 0 ~ 2������ �Դϴ�.");
				break;
			}
		}

	}

	public void loginSelect() { // �α��� �޴� ����
		Member member = login();
		if (member == null) {
			return;
		}
		if (member.isLog() == true) {
			// ȸ���� �α����� �Ǿ����� ������ �αװ��� true�� �ٲ���
			while (true) {
				try {
					System.out.println("�����ݾ��� �Է����ּ���.");
					int money = Integer.parseInt(sc.nextLine());
					if (money < 1000) {
						if (money <= 0) {
							System.out.println("�߸� �Է��ϼ̽��ϴ�.");
							System.out.println("��Ȯ�� ���� �Է����ּ���.");
						}
						System.out.println("�ش�ݾ����δ� ���� �̿��� ��ƽ��ϴ� �ٽ� �̿����ּ���");
						continue;

					}
					if (member.getMoney() <= 0) {
						System.out.println("���� �����Ͻð� �̿����ּ���");
						continue;
					}

					member.setMoney(member.getMoney() + money);
					break;
				} catch (Exception e) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					System.out.println("���ڸ� �Է����ּ���.");
					System.out.println();
				}
			}
			selectMemberMenu(member);
		} else {
			selectSeat(member.getId());
			selectMemberMenu(member);
			member.logCheck();
		}

	}

	public Member login() {
		String id = "";
		String pwd = "";
		while(true) {
			System.out.println("��--------------�α����� �����մϴ�---------------��");
			System.out.println("ID�� �Է��ϼ���. (0�� �Է½� ������)");
			id = sc.nextLine().trim();
			if(id.equals("0")) {
				return null;
			}
			System.out.println("PassWord�� �Է��ϼ���. (0�� �Է½� ������)");
			pwd = sc.nextLine().trim();
			if(pwd.equals("0")) {
				return null;
			}
			if(!memberList.containsKey(id)) {
				System.out.println("ID�� Ʋ���ϴ�.");
				System.out.println("���Է� �ϼ���.");
	
			}else {
				if(memberList.get(id).getPwd().equals(pwd)) {
					
					
					System.out.println("�α��� �Ǿ����ϴ�.");
					System.out.println("��-------------------------------------------��");
					System.out.println();
//					while (true) {
//						try {
//							System.out.println("�����Ͻ� �ݾ��� �Է����ּ���.");
//							int money = Integer.parseInt(sc.nextLine());
//							if (money < 0) {
//								System.out.println("�߸� �Է��ϼ̽��ϴ�.");
//								System.out.println("��Ȯ�� ���� �Է����ּ���.");
//								continue;
//							}
//							
//							
//							memberList.get(id).setMoney(memberList.get(id).getMoney() + money);
//							break;
//						} catch (Exception e) {
//							System.out.println("�߸� �Է��ϼ̽��ϴ�.");
//							System.out.println("���ڸ� �Է����ּ���.");
//							System.out.println();
//						}
//					}
					break;
					
				}else {
					System.out.println("PassWord�� Ʋ���ϴ�.");
					System.out.println("���Է� �ϼ���.");
				}
			}
			
		}return memberList.get(id);
		}

	public void logout(Member member) {

		int playTime = 0;
		while (true) {
			try {
				if(member.totalplaytime == 0) break;
				System.out.println("��-------------�α׾ƿ��� �����մϴ�--------------��");
				System.out.println("��ð� �̿��ϼ̽��ϱ�?");
				playTime = Integer.parseInt(sc.nextLine());
				if (playTime < 0 || member.getTotalplaytime() < playTime) {
					System.out.println("�ð��� �߸� �Է��ϼ̽��ϴ�.");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				System.out.println("���ڸ� �Է����ּ���.");
				System.out.println();
			}
		}
		int totalplaytime = member.getTotalplaytime() - playTime;
		member.setTotalplaytime(totalplaytime);

		System.out.println("ȸ������ ���� �ð� : " + totalplaytime);
		System.out.println("�̿����ּż� �����մϴ�.");
		System.out.println("��-------------------------------------------��");
		System.out.println();

		member.logCheck();

		int[] intSeatArr = member.getSeatArr();

		if (seat[intSeatArr[0]][intSeatArr[1]] != null) {

			seat[intSeatArr[0]][intSeatArr[1]] = null;
			System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
			File filename = new File("UserList.txt"); // ȸ���� ���Ϸ� ����
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(filename);
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(bos);

				oos.writeObject(memberList);

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				try {
					oos.close();
					bos.close();
					fos.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	}

	public void selectSeat(String id) { // ���ø� �ϴ°ɷ� .. ��ȣ�� ����?
		int row = 0;
		int col = 0;

		do {
			try {
				System.out.println("��---------------�¼��� �������ּ���--------------��");
				System.out.println("��-----------------Ex : 1-3 -----------------��");
				System.out.println("��-------------------------------------------��");
				System.out.print("�¼� ���� :  ");
				String inputSeat = sc.nextLine();
				String seatArr[] = inputSeat.trim().replace(" ", "").split("-");

				row = Integer.parseInt(seatArr[0]) - 1; // �ε����Է��̴ϱ�
				col = Integer.parseInt(seatArr[1]) - 1; // �ε����Է��̴ϱ�

				if (row > MAX_ROW_INDEX || col > MAX_COL_INDEX) {
					throw new Exception("�¼��� �߸� �����ϼ̽��ϴ�"); // �迭�� ��� �ڸ�
				}
				if (this.seat[row][col] == null) {
					// seat[row][col] = -> ,�������� ������ int�� �ٲٰ� row�� col�� �������ְ�!!
					System.out.println("�¼����� �Ϸ�");

					System.out.println("�����Ͻ� �¼��� " + " [" + (row + 1) + "]" + " [" + (col + 1) + "] �Դϴ�");
					seat[row][col] = memberList.get(id);
					int[] intSeatArr = new int[] { row, col };
					memberList.get(id).setSeatArr(intSeatArr); // ȸ�� �ڸ� �Ҵ�

					while (true) {
						try {
							System.out.println("�����Ͻ� �ݾ��� �Է����ּ���.");
							int money = Integer.parseInt(sc.nextLine());
							if (money < 0) {
								System.out.println("�߸� �Է��ϼ̽��ϴ�.");
								System.out.println("��Ȯ�� ���� �Է����ּ���.");
								continue;
							}

							memberList.get(id).setMoney(memberList.get(id).getMoney() + money);
							break;
						} catch (Exception e) {
							System.out.println("�߸� �Է��ϼ̽��ϴ�.");
							System.out.println("���ڸ� �Է����ּ���.");
							System.out.println();
						}
					}
					break;
				} else if (memberList.get(id).getSeatArr()[0] == row && memberList.get(id).getSeatArr()[1] == col) {

				} else {
					throw new Exception("�̿����� �¼��Դϴ�"); // �̹� ����� �ڸ�
				}
			} catch (Exception e) {
				System.out.println("�¼��� ������ �� �����ϴ�. �ٽ� �������ּ���");
				System.out.println("(1,1)���� (4,5)������ �¼��� �����մϴ�.");
			}
		} while (true);

	}

	public void showSeat() {
		System.out.println("   --1 ---2 ---3 ---4 ---5 --");
		for (int i = 0; i < this.seat.length; i++) {
			System.out.printf("%d| ", i + 1);
			for (int j = 0; j < this.seat[i].length; j++) {
				System.out.printf("[%s]", this.seat[i][j] == null ? " �� " : " �� ");
			}
			System.out.println();
		}

	}

	public String adminPage() { // ������ ������ ����
		System.out.println("****************************************");
		System.out.println("������ ������ �Դϴ�.");
		System.out.println("1. ������ �α���");
		System.out.println("0. ������");
		System.out.println("****************************************");
		String menu = sc.nextLine();
		return menu;
	}

	public void adminPageSelect() { // ������ ������ ����
		Loop_1: while (true) {
			String menunum = adminPage();
			switch (menunum) {
			case "1":
				System.out.println("���̵� �Է����ּ���.");
				String adminid = sc.nextLine();
				if (!adminid.equals(admin.getId())) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					break;
				}
				System.out.println("��й�ȣ�� �Է����ּ���.");
				String adminpwd = sc.nextLine();
				if (!adminpwd.equals(admin.getPwd())) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					break;
				}
				selectAdminMenu();
				break;
			case "0":
				break Loop_1;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				System.out.println("�޴��� 0 ~ 1������ �Դϴ�.");

			}
		}
	}

	public String displayAdminMenu() { // ������ �޴� ȭ��
		System.out.println("**************������ �޴�********************");
		System.out.println("1. ��ǰ �߰�");
		System.out.println("2. ��ǰ ����");
		System.out.println("3. �ð� ��� ����");
		System.out.println("4. ���� ���� Ȯ��");
		System.out.println("5. ȸ�� ����Ʈ Ȯ��");
		System.out.println("6. ���α׷� ����");
		System.out.println("0. ������");
		System.out.println("*******************************************");
		String menu = sc.nextLine();
		return menu;
	}

	public void selectAdminMenu() { // ������ �޴� ����

		while (true) {
			String menunum = displayAdminMenu();
			switch (menunum) {
			case "1":
				admin.addProduct(sc);

				break;
			case "2":
				admin.delProduct(sc);
				break;
			case "3":
				admin.selectChangeTime(sc);
				break;
			case "4":
				admin.showTotalMoney(this);
				break;
			case "5":
				admin.showMemberList();
				break;
			case "6":

				System.exit(0);
			case "0":

				firstMain();
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				System.out.println("�޴��� 0~6������ �Դϴ�.");
			}
		}
	}

	public String memberMenu() { // ȸ�� �޴� ȭ��

		System.out.println("**************************************");
		System.out.println("1. �̿�ð� �߰�");
		System.out.println("2. ���� �ֹ�");
		System.out.println("3. �ð� �� �ֹ� ��ȸ");
		System.out.println("4. �α׾ƿ�"); // �α׾ƿ��ϰ� ó�� ȭ������ ���ư���
		System.out.println("0. ó������ ���ư���"); // ��������
		System.out.println("**************************************");
		String menu = sc.nextLine();
		return menu;
	}

	public void selectMemberMenu(Member member) { // ȸ�� �޴� ����

		loop_1: while (true) {
			System.out.println();
			System.out.printf("�ȳ��ϼ��� [%s]��\n", member.getId());
			System.out.println();
			String menunum = memberMenu();
			switch (menunum) {
			case "1":
				member.selectPlayTime(this, sc);
				break;
			case "2":
				member.buyfood(this, sc);
				break;
			case "3":
				member.showTimeFood();
				break;
			case "4":
				logout(member);
				break loop_1;
			case "0":
				if(member.totalplaytime == 0) {
					logout(member);
				}
				System.out.println("�ȳ��� ������.");
				break loop_1;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				System.out.println("�޴��� 0~4������ �Դϴ�.");
			}
		}
	}

	public Member signUp() { // ȸ�� ����

		String id = "";
		String pwd = "";
		String name = "";
		String phonenumber = "";

		loop_1: while (true) {
			id = UserInfo.id(this);
			System.out.println();
			pwd = UserInfo.pwd();
			System.out.println();
			name = UserInfo.name();
			System.out.println();
			phonenumber = UserInfo.phone();
			System.out.println();

			while (true) {
				System.out.println("�Է��Ͻ� ������ �½��ϱ�? (Y/N)");
				String yesno = sc.nextLine();
				if (yesno.equalsIgnoreCase("Y")) {
					break loop_1;
				} else if (yesno.equalsIgnoreCase("N")) {
					continue loop_1;
				} else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					System.out.println("Y�� N�߿� �Է����ּ���.");
				}
			}
		}

		Member member = new Member(id, pwd, name, phonenumber); // ���̵� ��� �̸� ����ȣ�� �Է¹޾Ƽ� ȸ���� ������
		memberList.put(member.getId(), member);
		System.out.println("ȸ�������� �Ǿ����ϴ�.");
		System.out.printf("[%s]���� id : [%s]\n", name, id);
		System.out.printf("[%s]���� pwd : [%s]\n", name, pwd);
		System.out.printf("[%s]���� �޴��� ��ȣ : [%s]\n", name, phonenumber);

		File filename = new File("UserList.txt"); // ȸ���� ���Ϸ� ����
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filename);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(memberList);

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return member;

	}

	public HashMap<String, Member> getmemberList() {
		return memberList;
	}

	public void setmemberList(HashMap<String, Member> memberList) {
		this.memberList = memberList;
	}

	public Member[][] getSeat() {
		return seat;
	}

	public void setSeat(Member[][] seat) {
		this.seat = seat;
	}

	public static int getMaxRowIndex() {
		return MAX_ROW_INDEX;
	}

	public static int getMaxColIndex() {
		return MAX_COL_INDEX;
	}

}