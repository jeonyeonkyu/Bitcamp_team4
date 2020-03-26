package internetcafe.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import internetcafe.Transaction;
import internetcafe.user.ChargedTime;
import internetcafe.user.Product;

/*
* Ŭ������ : Customer // �մԿ� ���� Ŭ����
* 
* ���� ���� v.1.0
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class Customer extends User {
	Scanner sc;

	Customer(){
		
	}
	Customer(int money) {
		this.money = money;
		sc = new Scanner(System.in);
		cart = new ArrayList<Product>();
		transactions = new ArrayList<Transaction>();

	}
	
	public List<Product> getCart() {
		return cart;
	}
	private int money;
	private int totalplaytime;
	private List<Product> cart;

	private List<Transaction> transactions;


	public void buyFood(Product p) {
		System.out.println("�� ���� �����Ͻðڽ��ϱ�?");
		int number = Integer.parseInt(sc.nextLine()); // string�� �Է¹޾��� �� ���� �߰�, 0 �Է��� �� �ٽ� �Է��ش޶�� �ұ?
		for (int i = 0; i < number; i++) {
			cart.add(p);
		}
		System.out.printf("[%s]���� īƮ�� [%s], [%d]���� �߰��Ǿ����ϴ�.\n", this.getId(), p.toString(), number);
	}

	public void buyTime(ChargedTime ct) {
		this.totalplaytime += ct.getPlaytime();
		cart.add(ct);
		System.out.printf("[%s]�� �̿�ð� [%d]�ð��� �߰��Ǿ����ϴ�.\n", this.getId(), ct.getPlaytime());

	}

	public void show() {
	}
	public void summary() {		 //���¶� ���??		 
		int totalprice = 0;
		Transaction transaction = new Transaction();
		System.out.println("������ �߰��Ͻ� ��ǰ ���");
		System.out.println(cart.toString()); //�ð��� ���?
		for(Product cartin : cart) {
			totalprice += cartin.getpPrice();		
		}
		transaction.setMoney(totalprice);
		transaction.setTransactiodate(transaction.getTransactiodate());
		transaction.setTransactiontime(transaction.getTransactiontime());
		transactions.add(transaction);
		System.out.println("�� �ݾ� : " + totalprice);
		System.out.println("������ �����Ͻðڽ��ϱ�?");
		System.out.println("1. ��");
		System.out.println("2. �ƴϿ�");
		int select = Integer.parseInt(sc.nextLine());
		switch (select) {
			case 1 : 
				if(totalprice > this.money) {
					System.out.println("������ �ܾ��� �����մϴ�.");
					return;
				}
				this.money -= totalprice;
				break;
			case 2 :
				return;
			default :
				break;		
		}
		System.out.println("����� ���� �ܾ� : " + this.money);
		cart.clear();
		
	}
}