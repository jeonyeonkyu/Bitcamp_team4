package internetcafe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import internetcafe.product.Product;
import internetcafe.user.Administrator;
import internetcafe.user.Customer;
import internetcafe.user.Member;
import internetcafe.user.Nonmember;
import internetcafe.user.UserInfo;

/*
* Ŭ������ : InternetCafe // �ǽù� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 26
* 
* �۾��� : ������
* ȸ������ ��ɿ� ����ǥ�������� Ȯ���ϱ� �߰�
* ����Ʈ �¼��� �¼� ���� �� �α��� �ϱ� ��� �߰�
* ��ȸ�� �� ��� ����������  seat�� Customer[][] seat�� ����
*  // �޴��� ��ȣ �߰��ؾߵ�
*/
public class InternetCafe {

	private static final int MAX_ROW_INDEX = 4;
	private static final int MAX_COL_INDEX = 5;
	private HashMap<String, Member> memberList;
	private List<Nonmember> nonmemberList;
	private HashMap<String, Product> foodList;
	private Customer[][] seat;
	private Administrator admin;

	public InternetCafe() {
		this.memberList = new HashMap<String, Member>();
		this.seat = new Member[MAX_ROW_INDEX][MAX_COL_INDEX];
		this.nonmemberList = new ArrayList<Nonmember>();
		this.foodList = new HashMap<String, Product>();
		loadMemberList();

	}

	public void loadMemberList() {
		try {

			String path = "C:\\internetCafe\\customer.txt";
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "euc-kr"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] array = line.split(",");

				Member member = new Member();
				member.setId(array[0]);
				member.setPwd(array[1]);
				member.setName(array[2]);
				member.setTotalplaytime(Integer.parseInt(array[3]));
				getmemberList().put(array[0], member);
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public Member login(Scanner sc) {
		while (true) {
			System.out.println("���̵��Է��ϼ��� , 0�� �Է� �� ����");
			String id = sc.next();
			if (id.equals("0")) {
				showMain(sc); // ���θ޴��� ���ư�����
				return null;
			}
			System.out.println("��й�ȣ�Է��ϼ���, 0�� �Է� �� ����");
			String pwd = sc.next();
			if (pwd.equals("0")) {
				showMain(sc);// ���θ޴��� ���ư�����
				return null;
			}
			Member loginUser = memberList.get(id);
			if (loginUser != null && loginUser.getId().equals(id) && loginUser.getPwd().equals(pwd)) {
				System.out.println("�α��� �Ǿ����ϴ�");

				if (loginUser.getMoney() < 500) { // ���������Ϳ� �ִ� �ݾ��� 500�� ���� ���ٸ�
					System.out.println("���� ������ ��� �ݾ��� ���Դϱ�?");
					int money = Integer.parseInt(sc.next());
					loginUser.setMoney(money); // �� �־��ֱ�
					System.out.println("���� ������ �ִ� ���� " + money + "�� �Դϴ�");
				}
				if (loginUser.getTotalplaytime() == 0) {
					System.out.println("�ð��� �����ϰ� �̿����ּ���");
					// �ð����� �޼ҵ� �־����
				}
				return loginUser;
			} else {
				System.out.println("�߸� �Է� �Ǿ����ϴ� , �ٽ� �Է����ּ���");
				continue;
			}
		}

	}

	public void logout(int row, int col, Scanner sc) {
		if (seat[row][col] != null) {
			seat[row][col] = null;
		}
		Member member = new Member();
		// �������ϰ��
		String fileName = "C:\\internetCafe\\customer.txt";

		// file ��ü ����
		File inputFile = new File(fileName);
		File outputFile = new File(fileName + ".temp");

		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;

		boolean result = false;

		try {
			// FileInputStream,FileOutputStream, BufferdReader, BufferedWriter
			// ����
			fileInputStream = new FileInputStream(inputFile);
			fileOutputStream = new FileOutputStream(outputFile);

			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

			// ���� ���Ͽ��� �о� ���̴� �Ѷ���
			String line;
			// ���Ͽ� ��ġ�ϴ� ���ڷ� ��ü�ϰ� �� ���� string
			String repLine;

			// �ٲٰ��� �ϴ� string�� �ٲ� string ����
			String originalString = "";
			while (true) {
				if (memberList.get(member).equals(login(sc))) {
					originalString = memberList.get(member.getId()) + "," + memberList.get(member.getPwd()) + ","
							+ memberList.get(member.getName()) +   ","
							+ memberList.get(member.getTotalplaytime());
					break;
				}
			}
			String replaceString = member.getId() + "," + member.getPwd() + "," + member.getName() + ","
					 + member.getTotalplaytime();

			// ���� ���Ͽ��� �Ѷ��ξ� �д´�.
			while ((line = bufferedReader.readLine()) != null) {
				// ��ġ�ϴ� ���Ͽ����� �ٲ� ���ڷ� ��ȯ
				repLine = line.replaceAll(originalString, replaceString);

				// ���ο� ���Ͽ� ����.
				bufferedWriter.write(repLine, 0, repLine.length());
				bufferedWriter.newLine();
			}
			// ���������� ����Ǿ����� �˸��� flag
			result = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// ���ҽ� ����. ���������� �����Ѵ�.
			try {
				bufferedReader.close();
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
			try {
				bufferedWriter.close();
			} catch (IOException ex2) {
				ex2.printStackTrace();
			}

			// ���������� ����Ǿ��� ��� ���� ������ ����� ���ο� ���ϸ��� �������ϸ����� rename�Ѵ�.
			if (result) {
				inputFile.delete();
				outputFile.renameTo(new File(fileName));
			}
		}

		System.out.println("�α׾ƿ� �Ǽ̽��ϴ�");
	}

	public void setfoodList(HashMap<String, Product> foodList) {
		this.foodList = foodList;
	}

	public void showSeat() {
		for (int i = 0; i < this.seat.length; i++) {
			for (int j = 0; j < this.seat[i].length; j++) {
				System.out.printf("[%s]", this.seat[i][j] == null ? "�̿밡��" : "!�̿���!");
			}
			System.out.println();
		}

	}

	public void showMain(Scanner sc) {

		System.out.println("�ƹ� Ű�� �Է��Ͻÿ�.");
		String input = sc.nextLine();
		if (input.equals("admin")) {
			adminPageSelect(sc);
		}
		selectCustomerMenu(sc);
	}

	public int adminPage(Scanner sc) {
		int menu = 0;
		do {
			try {
				System.out.println("������ ������ �Դϴ�.");
				System.out.println("1. ������ �α���");
				System.out.println("2. ������");
				menu = Integer.parseInt(sc.nextLine());
				if (menu == 1 || menu == 2) {
					break;
				} else {
					throw new Exception("�޴��� �߸� �Է��ϼ̽��ϴ�.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (true);

		return menu;
	}

	public void adminPageSelect(Scanner sc) {
		while (true) {
			int menunum = adminPage(sc);
			Loop_1: switch (menunum) {
			case 1:
				System.out.println("���̵� �Է����ּ���.");
				String adminid = sc.nextLine();
				if (!adminid.equals(admin.getId())) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					break Loop_1;
				}
				System.out.println("��й�ȣ�� �Է����ּ���.");
				String adminpwd = sc.nextLine();
				if (!adminpwd.equals(admin.getPwd())) {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					break Loop_1;
				}
				selectAdminMenu(sc);
				break;
			case 2:
				showMain(sc);
				break;
			}
		}
	}

	public int displayAdminMenu(Scanner sc) {
		int menu = 0;
		do {
			try {
				System.out.println("*****������ �޴�******");
				System.out.println("1. ��ǰ �߰�");
				System.out.println("2. ��ǰ ����");
				System.out.println("3. ���� ���� Ȯ��");
				System.out.println("4. ȸ�� ����Ʈ Ȯ��");
				System.out.println("5. ó������ ���ư���");
				System.out.println("*******************");
				menu = Integer.parseInt(sc.nextLine());
				if (menu >= 1 && menu <= 5) {
					break;
				} else {
					throw new Exception("�޴��� �߸� �Է��ϼ̽��ϴ�.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("�޴��� 1~5������ �Դϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
			}
		} while (true);
		return menu;
	}

	public void selectAdminMenu(Scanner sc) {
		int menunum = displayAdminMenu(sc);
		switch (menunum) {
		case 1:
			admin.addProduct();

			break;
		case 2:
			admin.delProduct();
			break;
		case 3:
			admin.showTotalMoney(this);
			break;
		case 4:
			admin.showMemberList();
			break;
		case 5:
			showMain(sc);
			break;
		}
	}

	public int customerMenu(Scanner sc) {
		int menu = 0;
		do {
			try {
				System.out.println("*******************");
				System.out.println("1. �̿�ð� �߰� �� ��ȸ");
				System.out.println("2. ���� �ֹ�");
				System.out.println("3. ����ϱ�");
				System.out.println("4. �α׾ƿ�");
				System.out.println("5. ������");
				System.out.println("*******************");
				menu = Integer.parseInt(sc.nextLine());
				if (menu >= 1 && menu <= 4) {
					break;
				} else {
					throw new Exception("�޴��� �߸� �Է��ϼ̽��ϴ�.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("�޴��� 1~5������ �Դϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
			}
		} while (true);
		return menu;
	}

	public void selectCustomerMenu(Scanner sc) {
		int menunum = customerMenu(sc);
		switch (menunum) {
		case 1:

			break;
		case 2:
			admin.showFoodList();

			break;
		case 3:

			break;
		case 4:
			break;
		case 5:
			System.out.println("�ȳ��� ������.");
			System.exit(0);

		}
	}

	/*
	 * private void selectNonMember(Scanner sc) { while (true) {
	 * System.out.println("1. ī��߱�"); System.out.println("2. ȸ������");
	 * System.out.println("3. ����");
	 * 
	 * int key = sc.nextInt(); sc.nextLine();
	 * 
	 * switch (key) { case 1: int cardnum = ((int) (Math.random() * 20) + 1); break;
	 * 
	 * case 2: signUp(sc); break;
	 * 
	 * case 3: System.out.println("�̿����ּż� �����մϴ�"); showMain(sc);
	 * 
	 * default: System.out.println("�߸��� �޴��Դϴ�. �ٽ� �Է����ּ���. "); break; }
	 * 
	 * }
	 * 
	 * }
	 */
	public void selectCustomer(Scanner sc) {
		while (true) {
			System.out.println("1. ȸ���α���");
//			System.out.println("2. ��ȸ��");
			System.out.println("3. ����");

			int s_c_key = sc.nextInt();
			switch (s_c_key) {
			case 1:
				System.out.println("�α����� ���ּ���");
				login(sc);
				break;

			case 2:
//				selectNonMember(sc);
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
					throw new Exception("�¼��� �߸� �����ϼ̽��ϴ�"); // �迭�� ��� �ڸ�
				}
				if (this.seat[row][col] == null) {
					// seat[row][col] = -> ,�������� ������ int�� �ٲٰ� row�� col�� �������ְ�!!
					System.out.println("�¼����� �Ϸ�");
					this.seat[row][col] = (login(sc));// �α����ϱ�
					System.out.println("�����Ͻ� �¼��� " + " [" + (row + 1) + "]" + " [" + (col + 1) + "] �Դϴ�");

					// 1.������ �ð� ��ȸ �� �߰�
					// 2.���� �ֹ�
					// 3.�ֹ��� ���� ��ȸ
					// 4.�α׾ƿ�
					// 5.ȭ�� ������

					// 4�� �α׾ƿ� �Ʒ�
					System.out.println("�α׾ƿ� �Ͻðڽ��ϱ�? ,1 �Է� ");
					if (Integer.parseInt(sc.next()) == 1) {
						logout(row, col, sc);
					}
					// �α׾ƿ� ��
					return;
				} else {
					throw new Exception("�̿����� �¼��Դϴ�"); // �̹� ����� �ڸ�
				}

			} catch (Exception e) {
				System.out.println("�¼��� ������ �� �����ϴ�. �ٽ� �������ּ���");
				System.out.println("(1,1)���� (5,4)������ �¼��� �����մϴ�.");
			}

		} while (true);

	}

	public void signUp(Scanner sc) { // ȸ������
		System.out.println("ȸ������ �޴��Դϴ�");
		Member member = new Member();
		UserInfo userInfo = new UserInfo();

		member.setId(userInfo.isId(sc, memberList));
		member.setPwd(userInfo.isPwd());
		member.setName(userInfo.isName());
		member.setMoney(0);
		member.setTotalplaytime(0);
		memberList.put(member.getId(), member);
		System.out.println("��� �Ǿ����ϴ�");
		System.out.println("�ð������Ͻð� �̿����ּ���");

		BufferedWriter bw = null;
		PrintWriter writer = null;
		try {
			String path = "C:\\internetCafe\\customer.txt";
			File f = new File(path);
			if (!f.exists()) {
				// ������ �������� ������� ���ϸ����
				f.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(path, true));
			writer = new PrintWriter(bw, true);
			String writeMember = member.getId() + "," + member.getPwd() + "," + member.getName() + ","
					 + "," + member.getTotalplaytime();
			// ���� ������ ���뿡 �̾ ������ true��, ���� ������ ���ְ� ���� ������ false�� �����Ѵ�.
			writer.write(writeMember + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

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

	public Customer[][] getSeat() {
		return seat;
	}

	public void setSeat(Customer[][] seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "InternetCafe [memberList=" + memberList + ", nonmemberList=" + nonmemberList + ", foodList=" + foodList
				+ ", seat=" + Arrays.toString(seat) + "]";
	}

}