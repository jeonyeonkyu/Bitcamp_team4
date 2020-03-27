package internetcafe.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import internetcafe.Transaction;
import internetcafe.product.ChargedTime;
import internetcafe.product.Product;

/*
* 클래스명 : Customer // 손님에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 26
* 
* 작업자 : 전연규
*/
public class Customer extends User {
	Scanner sc;

	Customer() {

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
		System.out.println("몇 개를 구매하시겠습니까?");
		int number = Integer.parseInt(sc.nextLine()); // string값 입력받았을 시 예외 추가, 0 입력할 시 다시 입력해달라고 할까나?
		for (int i = 0; i < number; i++) {
			cart.add(p);
		}
		System.out.printf("[%s]님의 카트에 [%s], [%d]개가 추가되었습니다.\n", this.getId(), p.toString(), number);
	}

	public void buyTime(ChargedTime ct) {
		this.totalplaytime += ct.getPlaytime();
		cart.add(ct);
		System.out.printf("[%s]님 이용시간 [%d]시간이 추가되었습니다.\n", this.getId(), ct.getPlaytime());

	}

	public void show() {
	}

	public void summary() { // 계좌랑 어떻게??
		int totalprice = 0;
		Transaction transaction = new Transaction();
		System.out.println("고객님이 추가하신 물품 목록");
		System.out.println(cart.toString()); // 시간은 어떻게?
		for (Product cartin : cart) {
			totalprice += cartin.getpPrice();
		}
		transaction.setMoney(totalprice);
		transaction.setTransactiodate(transaction.getTransactiodate());
		transaction.setTransactiontime(transaction.getTransactiontime());
		transactions.add(transaction);
		System.out.println("총 금액 : " + totalprice);
		System.out.println("정말로 결제하시겠습니까?");
		System.out.println("1. 예");
		System.out.println("2. 아니오");
		int select = Integer.parseInt(sc.nextLine());
		switch (select) {
		case 1:
			if (totalprice > this.money) {
				System.out.println("고객님의 잔액이 부족합니다.");
				return;
			}
			this.money -= totalprice;
			break;
		case 2:
			return;
		default:
			break;
		}
		System.out.println("당신의 현재 잔액 : " + this.money);
		cart.clear();

	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getTotalplaytime() {
		return totalplaytime;
	}

	public void setTotalplaytime(int totalplaytime) {
		this.totalplaytime = totalplaytime;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void setCart(List<Product> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [sc=" + sc + ", money=" + money + ", totalplaytime=" + totalplaytime + ", cart=" + cart
				+ ", transactions=" + transactions + "]";
	}

}