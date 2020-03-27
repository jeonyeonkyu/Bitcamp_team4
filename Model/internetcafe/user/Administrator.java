package internetcafe.user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import internetcafe.InternetCafe;
import internetcafe.Transaction;
import internetcafe.product.ChargedTime;
import internetcafe.product.Food;
import internetcafe.product.Product;

/*
* Ŭ������ : Administrator //�����ڿ� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class Administrator extends User {
	Scanner sc;
	private HashMap<String, Product> foodlist;
	private ChargedTime[] timelist = { new ChargedTime(1), new ChargedTime(3), new ChargedTime(5),
			new ChargedTime(10) };

	public Administrator(String id, String pwd) {
		foodlist = new HashMap<String, Product>();
		sc = new Scanner(System.in);
		this.setId(id);
		this.setPwd(pwd);
	}

	public void addProduct() { // ���� �̸�, ���� �Է¹ް� food ���� foodlist�� �߰� �׸��� ����
		System.out.println("�߰��� ���� �̸��� �Է��ϼ���.");
		String name = sc.nextLine();
		System.out.println("������ ������ �Է��ϼ���.");
		int price = Integer.parseInt(sc.nextLine());
		Food food = new Food(name, price);
		this.foodlist.put(name, food);

		File foodfile = new File("FoodList.txt");
		try {
			FileOutputStream fos = new FileOutputStream(foodfile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			oos.writeObject(foodlist);
			oos.close();
			bos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("������ ���еǾ����ϴ�.");

		}
		System.out.println("����Ǿ����ϴ�.");

	}

	public void showFoodList() {
		File foodfile = new File("FoodList.txt");
		try {
			FileInputStream fis = new FileInputStream(foodfile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);

			foodlist = (HashMap) ois.readObject();
			Set<String> keyset = foodlist.keySet();
			System.out.println("******���� ����Ʈ*******");
			for (String key : keyset) {
				String pname = foodlist.get(key).getpName();
				int pprice = foodlist.get(key).getpPrice();
				System.out.printf("���� �̸�: [%s]\t ���� ����: [%d]\n", pname, pprice);
			}
			ois.close();
			bis.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void delProduct() { // ���� �̸��� ��ġ�� �� ���� �׸��� ����

		System.out.println("������ ���� �̸��� �Է��ϼ���.");
		String name = sc.nextLine();
		if (!foodlist.containsKey(name)) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} else {
			foodlist.remove(name);
			System.out.println("�����߽��ϴ�.");
		}
		saveFoodlist();
	}

	public void saveFoodlist() { // txt���Ͽ� foodlist ����
		File foodfile = new File("FoodList.txt");
		try {
			FileOutputStream fos = new FileOutputStream(foodfile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			oos.writeObject(foodlist);
			oos.close();
			bos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("������ ���еǾ����ϴ�.");
			System.out.println(e.getMessage());

		}
		System.out.println("����Ǿ����ϴ�.");
	}

	public void changeTime() {

		System.out.println("���� �ð��� ���");
		for (ChargedTime ch : timelist) {
			System.out.printf("%d�ð��� ��� : [%d]\n", ch.getPlaytime(), ch.getpPrice());
		}
		System.out.println("1. 1�ð� ���");
		System.out.println("2. 3�ð� ���");
		System.out.println("3. 5�ð� ���");
		System.out.println("4. 10�ð� ���");
		System.out.println("0. ������");

	}

	public void showTotalMoney(InternetCafe ic) {
		int alltotalmoney = 0; // �� ����
		HashMap<String, Member> memberlist = ic.getmemberList(); // ȸ������Ʈ ȣ��
		Set<String> key = memberlist.keySet(); // ��� id(key)�� set�� ����
		for (String userId : key) {
			Member member = memberlist.get(userId); // id�� ���� value�� Member ��ü�� �̷��� ����
			List<Transaction> memtranlist = member.getTransactions(); // �� ����� transaction list�� ����
			for (Transaction tran : memtranlist) {
				alltotalmoney += tran.getMoney(); // transaction list�� �ִ� transaction���� ������ ���� �� ���� ������ �հ��ϸ� ����
			}
		}
		/*
		 * List<Nonmember> nonmemlist = ic.getNonmemberList(); //��ȸ������Ʈ ȣ�� for(Nonmember
		 * nonmem : nonmemlist) { List<Transaction> nonmemtranlist =
		 * nonmem.getTransactions(); // ��ȸ���� transaction list�� ���� for(Transaction tran :
		 * nonmemtranlist) { alltotalmoney += tran.getMoney(); //transaction list�� �ִ�
		 * transaction���� ������ ���� �� ���� ������ �հ��ϸ� ���� } }
		 */
		// ���� �������� ���̰� ���� -> ���� �߰�
		System.out.println("�� ���� : " + alltotalmoney);
	}

	public void showMemberList() { // ��������Ʈ �����ֱ�
		// IO�� �ذ�
		File filename = new File("UserList.txt");
		try {
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			System.out.println("******��������Ʈ*******");
			System.out.println();
			HashMap<String, Member> memberlist = (HashMap) ois.readObject();
			Set<String> keyset = memberlist.keySet();
			for (String key : keyset) {
				String id = memberlist.get(key).getId();
				String pwd = memberlist.get(key).getPwd();
				String name = memberlist.get(key).getName();

				System.out.printf("ID: [%s]\t PWD: [%s]\t �̸�: [%s]\n", id, pwd, name);
			}
			ois.close();
			bis.close();
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}