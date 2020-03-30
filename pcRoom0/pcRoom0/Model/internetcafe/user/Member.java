package internetcafe.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import internetcafe.InternetCafe;
import internetcafe.Transaction;
import internetcafe.product.Food;

/*
* Ŭ������ : Customer // �մԿ� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class Member extends User implements Serializable {

	private String name;
	private String phonenumber;
	private int money;
	public int totalplaytime;
	private List<Food> cart;
	private List<Transaction> transactions;
	private List<Food> totalOrderList;
	private int[] intSeatArr;
	public String getPhonenumber() {
		return phonenumber;
	}

	public int[] getSeatArr() {
		return intSeatArr;
	}

	public int getTotalplaytime() {
		return totalplaytime;
	}

	public void setTotalplaytime(int totalplaytime) {
		this.totalplaytime = totalplaytime;
	}

	public void setSeatArr(int[] intSeatArr) {
		this.intSeatArr = intSeatArr;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Member(String id, String pwd, String name, String phonenumber) {
		cart = new ArrayList<Food>();
		transactions = new ArrayList<Transaction>();
		this.setId(id);
		this.setPwd(pwd);
		this.name = name;
		this.money = 0;
		this.phonenumber = phonenumber;
		this.totalplaytime = 0;
		totalOrderList = new ArrayList<Food>();
		this.intSeatArr = new int[2];
		this.setLog(false);

	}

	public List<Food> getCart() {
		return cart;
	}

	public void showTimeFood() {
		System.out.printf("ȸ������ �¼� ���� : [%d], [%d]\n",intSeatArr[0]+1,intSeatArr[1]+1);
		System.out.println();
		System.out.println("ȸ������ ���� �̿� �ð� : " + this.totalplaytime);
		System.out.println();
		if (!totalOrderList.isEmpty()) {
			for (Food food : totalOrderList) {
				System.out.println("ȸ������ ���� �ֹ� ����: " + food);
			}
		} else {
			System.out.println("ȸ������ ���� �ֹ� ������ �����ϴ�.");
		}
		System.out.println("ȸ������ �ܾ� : " +this.money);
		
		

	}

	
	
	public boolean addCartFood(InternetCafe ic,Scanner sc) {
		HashMap<String, Food> foodlist = ic.getAdmin().getFoodlist();
		if (ic.getAdmin().showFoodList()) {
			loop_1: while (true) {

				System.out.println();
				System.out.println("���Ͻô� ������ �̸��� �Է����ּ���.");
				System.out.println("���� ������ ���Ͻø�  *�� �Է����ּ���");
				String foodname = sc.nextLine();
				

				if (foodname.equals("*")) { // *������ ������� �Ѿ
					break loop_1;
				}
				if (foodlist.containsKey(foodname)) {// �Է¹��� ���� �̸��� foodlist�� �ִ��� Ȯ��
					int number = 0;
					for (int i = 0; i < cart.size(); i++) {
						Food food = cart.get(i);
						if (food.getpName().equals(foodname)) {
							while (true) {
								try {
									System.out.println("�� ���� �����Ͻðڽ��ϱ�? (1�� �̻� �������ּ���.)");
									number = Integer.parseInt(sc.nextLine());
									if (number <= 0) {
										continue;
									}
									break;
								} catch (Exception e) {
									System.out.println("�߸� �Է��ϼ̽��ϴ�.");
									System.out.println("���ڸ� �Է����ּ���.");
									System.out.println();
								}
							}

							cart.remove(i);
							System.out.printf("[%s]���� īƮ�� [%s], [%d]���� �߰��Ǿ����ϴ�.\n", this.getId(), foodname, number);
							number += food.getNumber();
							food.setNumber(number);
							cart.add(food);

							System.out.println();
							System.out.println("ȸ������ �ֹ� ���");
							System.out.println();
							// �ð��� ���?
							for (Food cartin : cart) {
								System.out.println(cartin.toString());

							}
							System.out.println();
							continue loop_1;
						}
					}

					while (true) {
						try {
							System.out.println("�� ���� �����Ͻðڽ��ϱ�? (1�� �̻� �������ּ���.)");
							number = Integer.parseInt(sc.nextLine());
							if (number <= 0) {
								continue;
							}
							break;
						} catch (Exception e) {
							System.out.println("�߸� �Է��ϼ̽��ϴ�.");
							System.out.println("���ڸ� �Է����ּ���.");
							System.out.println();
						}
					}
					foodlist.get(foodname).setNumber(number);

					cart.add(foodlist.get(foodname));

					System.out.printf("[%s]���� īƮ�� [%s], [%d]���� �߰��Ǿ����ϴ�.\n", this.getId(), foodname, number);
					System.out.println();

					System.out.println("ȸ������ �ֹ� ���");
					System.out.println();
					// �ð��� ���?
					for (Food cartin : cart) {
						System.out.println(cartin.toString());

					}
					System.out.println();
				} else {
					System.out.println("���� �̸��� �߸� �Է��ϼ̽��ϴ�.");
					System.out.println();
				}
			}
			return true;
		}

		return false;

	}

	public void buyTime(InternetCafe ic,Scanner sc) {

		Transaction transaction = new Transaction();
		while (true) {
			System.out.println("������ �����Ͻðڽ��ϱ�? (Y/N)");
			String yesno = sc.nextLine();
			if (yesno.equalsIgnoreCase("n")) {
				System.out.println("��ҵǾ����ϴ�.");
				break;
			} else if (yesno.equalsIgnoreCase("y")) {
				if (this.money < ic.getAdmin().getTimelist()[0].getpPrice()) {
					System.out.println("ȸ������ �ܾ��� �����մϴ�.");

					break;
				} else {
					this.money -= ic.getAdmin().getTimelist()[0].getpPrice();
					System.out.println("����� ���� �ܾ� : " + this.money);
					transaction.setMoney(ic.getAdmin().getTimelist()[0].getpPrice());
					transaction.setTransactiodate(transaction.getTransactiodate());
					transaction.setTransactiontime(transaction.getTransactiontime());
					transactions.add(transaction);
					break;
				}
			} else {
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				System.out.println("Y�� N�߿� �Է����ּ���.");
			}
		}

	}

	public String displayPlayTime(InternetCafe ic,Scanner sc) {

		System.out.println("���ϴ� �̿�ð��� �����ϼ���.");
		System.out.println("1. 1�ð�: " + ic.getAdmin().getTimelist()[0].getpPrice());
		System.out.println("2. 3�ð�: " + ic.getAdmin().getTimelist()[1].getpPrice());
		System.out.println("3. 5�ð�: " + ic.getAdmin().getTimelist()[2].getpPrice());
		System.out.println("4. 10�ð�: " + ic.getAdmin().getTimelist()[3].getpPrice());
		System.out.println("0. ������");
		String menunum = sc.nextLine();

		return menunum;
	}

	public void selectPlayTime(InternetCafe ic,Scanner sc) {

		loop_1: while (true) {
			Transaction transaction = new Transaction();
			String menunum = displayPlayTime(ic,sc);
			System.out.println();

			loop_2: switch (menunum) {
			case "1":
				while (true) {
					System.out.println("������ �����Ͻðڽ��ϱ�? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("��ҵǾ����ϴ�.");
						
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[0].getpPrice()) {
							System.out.println("ȸ������ �ܾ��� �����մϴ�.");
							System.out.println("����� ���� �ܾ� : " + this.money);
							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[0].getpPrice();
							System.out.println("����� ���� �ܾ� : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[0].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[0].getPlaytime();
							System.out.printf("[%s]�� �̿�ð� [%d]�ð��� �߰��Ǿ����ϴ�.\n", this.getId(),
									ic.getAdmin().getTimelist()[0].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						System.out.println("Y�� N�߿� �Է����ּ���.");
					}
				}

			case "2":
				while (true) {
					System.out.println("������ �����Ͻðڽ��ϱ�? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("��ҵǾ����ϴ�.");
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[1].getpPrice()) {
							System.out.println("ȸ������ �ܾ��� �����մϴ�.");
							System.out.println("����� ���� �ܾ� : " + this.money);

							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[1].getpPrice();
							System.out.println("����� ���� �ܾ� : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[1].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[1].getPlaytime();
							System.out.printf("[%s]�� �̿�ð� [%d]�ð��� �߰��Ǿ����ϴ�.\n", this.getId(),
									ic.getAdmin().getTimelist()[1].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						System.out.println("Y�� N�߿� �Է����ּ���.");
					}
				}

			case "3":
				while (true) {
					System.out.println("������ �����Ͻðڽ��ϱ�? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("��ҵǾ����ϴ�.");
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[2].getpPrice()) {
							System.out.println("ȸ������ �ܾ��� �����մϴ�.");
							System.out.println("����� ���� �ܾ� : " + this.money);

							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[2].getpPrice();
							System.out.println("����� ���� �ܾ� : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[2].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[2].getPlaytime();
							System.out.printf("[%s]�� �̿�ð� [%d]�ð��� �߰��Ǿ����ϴ�.\n", this.getId(),
									ic.getAdmin().getTimelist()[2].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						System.out.println("Y�� N�߿� �Է����ּ���.");
					}
				}

			case "4":
				while (true) {
					System.out.println("������ �����Ͻðڽ��ϱ�? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("��ҵǾ����ϴ�.");
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[3].getpPrice()) {
							System.out.println("ȸ������ �ܾ��� �����մϴ�.");
							System.out.println("����� ���� �ܾ� : " + this.money);

							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[3].getpPrice();
							System.out.println("����� ���� �ܾ� : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[3].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[3].getPlaytime();
							System.out.printf("[%s]�� �̿�ð� [%d]�ð��� �߰��Ǿ����ϴ�.\n", this.getId(),
									ic.getAdmin().getTimelist()[3].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
						System.out.println("Y�� N�߿� �Է����ּ���.");
					}
				}

			case "0":
				break loop_1;

			default:
				System.out.println("�޴��� �߸� �Է��ϼ̽��ϴ�.");
				break loop_1;
			}
		}

	}

	public void buyfood(InternetCafe ic,Scanner sc) { // ���¶� ���??
		if (addCartFood(ic,sc)) {
			int totalprice = 0;
			Transaction transaction = new Transaction();
			for (Food cartin : cart) {

				totalprice += cartin.getpPrice() * cartin.getNumber();
			}

			System.out.println("�� �ݾ� : " + totalprice);
			while (true) {
				System.out.println("������ �����Ͻðڽ��ϱ�? (Y/N)");
				String select = sc.nextLine();
				if (select.equalsIgnoreCase("Y")) {
					if (totalprice > this.money) {
						System.out.println("������ �ܾ��� �����մϴ�.");
						cart.clear();
						break;
					}
					this.money -= totalprice;
					transaction.setMoney(totalprice);
					transaction.setTransactiodate(transaction.getTransactiodate());
					transaction.setTransactiontime(transaction.getTransactiontime());
					transactions.add(transaction);
					for (Food food : cart) {
						totalOrderList.add(food);
					}
					cart.clear();
					break;
				} else if (select.equalsIgnoreCase("N")) {
					System.out.println("������ ��ҵǾ����ϴ�.");
					cart.clear();
					break;
				} else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					System.out.println("Y�� N�߿� �Է����ּ���.");
				}
			}
			System.out.println("����� ���� �ܾ� : " + this.money);
			
	}

}
}