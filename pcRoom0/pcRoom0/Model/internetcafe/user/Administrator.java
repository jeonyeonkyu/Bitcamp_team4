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
	private HashMap<String,Food> foodlist;
	private ChargedTime[] timelist = {new ChargedTime(1, 1000), new ChargedTime(3,2700),new ChargedTime(5,4000),new ChargedTime(10,7500)};
	


	public HashMap<String, Food> getFoodlist() {
		return foodlist;
	}

	public void setFoodlist(HashMap<String, Food> foodlist) {
		this.foodlist = foodlist;
	}

	public ChargedTime[] getTimelist() {
		return timelist;
	}

	public void setTimelist(ChargedTime[] timelist) {
		this.timelist = timelist;
	}

	public Administrator(String id, String pwd) {
		foodlist = new HashMap<String, Food>();
		this.setId(id);
		this.setPwd(pwd);		
	}

	public void addProduct(Scanner sc) { // ���� �̸�, ���� �Է¹ް� food ���� foodlist�� �߰� �׸��� ����
		System.out.println("�߰��� ���� �̸��� �Է��ϼ���.");
		String name = sc.nextLine();
		int price = 0;
		while (true) {
			try {
				System.out.println("������ ������ �Է��ϼ���.");
				price = Integer.parseInt(sc.nextLine());
				if (price < 0) {
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				System.out.println("���ڸ� �Է����ּ���.");
				System.out.println();
			}
		}	
		Food food = new Food(name, price);		
		this.foodlist.put(name, food);		
		saveFoodlist();
		showFoodList();
	}   
	
	
	public boolean showFoodList() {
		File foodfile = new File("FoodList.txt");
		if(foodfile.exists() && foodlist.size() > 0) {
		try {
			FileInputStream fis = new FileInputStream(foodfile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			foodlist = (HashMap)ois.readObject();
			Set<String> keyset = foodlist.keySet(); 
			System.out.println("******���� ����Ʈ*******");
			for(String key : keyset) {
				String pname = foodlist.get(key).getpName();
				int pprice = foodlist.get(key).getpPrice();
				System.out.printf("���� �̸�: [%s]\t ���� ����: [%d]\n",pname,pprice);
			}
			ois.close();
			bis.close();
			fis.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}return true;
	}else {
		System.out.println("���� ���� ����� �����ϴ�.");
		return false;
	}
		
	}
		
		

	public void delProduct(Scanner sc) { //���� �̸��� ��ġ�� �� ���� �׸��� ����
		if(showFoodList()) {
		System.out.println();
		while(true) {
		System.out.println("������ ���� �̸��� �Է��ϼ���.");
		
		
		String name = sc.nextLine();
		if(!foodlist.containsKey(name)) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} else {
			foodlist.remove(name);
			System.out.println("�����߽��ϴ�.");
			saveFoodlist();
			break;
		}			
		}
		
		}
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
	
	public String changeTimeDisplay(Scanner sc) {
	
		System.out.println("���� �ð��� ���");
		for(ChargedTime ch : timelist) {
			System.out.printf("%d�ð��� ��� : [%d]\n",ch.getPlaytime(), ch.getpPrice());
		}
		System.out.println();
		System.out.println("1. 1�ð� ��� ����");
		System.out.println("2. 3�ð� ��� ����");
		System.out.println("3. 5�ð� ��� ����");
		System.out.println("4. 10�ð� ��� ����");
		System.out.println("0. ������");
			
		String menu = sc.nextLine();
		
		return menu;		
	}
	public void selectChangeTime(Scanner sc) {
		
		int price = 0;
		loop_1 : while(true) {
			String menunum = changeTimeDisplay(sc);
		switch (menunum) {
		case "1" :
			System.out.println("���Ͻô� ����� �Է����ּ���.");
			price = Integer.parseInt(sc.nextLine());
			timelist[0].setpPrice(price);
			break ;
		case "2" :
			System.out.println("���Ͻô� ����� �Է����ּ���.");
			price = Integer.parseInt(sc.nextLine());
			timelist[1].setpPrice(price);
			break;
		case "3" :
			System.out.println("���Ͻô� ����� �Է����ּ���.");
			price = Integer.parseInt(sc.nextLine());
			timelist[2].setpPrice(price);
			break;
		case "4" :
			System.out.println("���Ͻô� ����� �Է����ּ���.");
			price = Integer.parseInt(sc.nextLine());
			timelist[3].setpPrice(price);
			break;
		case "0" :
			break loop_1;
		default : 
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			System.out.println("�޴��� 0~4������ �Դϴ�.");
		}
		}
	}

	public void showTotalMoney(InternetCafe ic) {
		int alltotalmoney = 0; //�� ����
		HashMap<String, Member> memberlist = ic.getmemberList(); //ȸ������Ʈ ȣ��
		Set<String> key = memberlist.keySet(); //��� id(key)�� set�� ����
		for(String userId : key) { 
			Member member = memberlist.get(userId); //id�� ���� value�� Member ��ü�� �̷��� ����
			List<Transaction> memtranlist = member.getTransactions(); //�� ����� transaction list�� ����
			for(Transaction tran : memtranlist) {
				alltotalmoney += tran.getMoney(); //transaction list�� �ִ� transaction���� ������ ���� �� ���� ������ �հ��ϸ� ����
				System.out.println(tran.toString());
			}
		}
		/*
		 * List<Nonmember> nonmemlist = ic.getNonmemberList(); //��ȸ������Ʈ ȣ�� for(Nonmember
		 * nonmem : nonmemlist) { List<Transaction> nonmemtranlist =
		 * nonmem.getTransactions(); // ��ȸ���� transaction list�� ���� for(Transaction tran :
		 * nonmemtranlist) { alltotalmoney += tran.getMoney(); //transaction list�� �ִ�
		 * transaction���� ������ ���� �� ���� ������ �հ��ϸ� ���� } }
		 */
		//���� �������� ���̰� ���� -> ���� �߰�
		System.out.println();
		System.out.println("�� ���� : " + alltotalmoney);
	}

	public void showMemberList() { //��������Ʈ �����ֱ�
		//IO�� �ذ�
		File filename = new File("UserList.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		
		if(filename.exists()) {
		try {
			
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			System.out.println("******��������Ʈ*******");
			System.out.println();
			HashMap<String, Member> memberlist = (HashMap)ois.readObject();
			Set<String> keyset = memberlist.keySet();
			for(String key : keyset) {
				String id = memberlist.get(key).getId();
				String pwd = memberlist.get(key).getPwd();
				String name = memberlist.get(key).getName();
				String phonenumber = memberlist.get(key).getPhonenumber();
				System.out.printf("ID: [%s]\t PWD: [%s]\t �̸�: [%s]\t �޴��� ��ȣ: [%s]\n",id,pwd,name,phonenumber);
			}		
		} catch (Exception e) {
			
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			e.printStackTrace();
		}finally {
			try {
				ois.close();
				bis.close();
				fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
				
				
		}
		
	
	}else {
		System.out.println("ȸ�� ����� �����ϴ�.");
	}
	

}
}