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
* 클래스명 : Customer // 손님에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
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
		System.out.printf("회원님의 좌석 정보 : [%d], [%d]\n",intSeatArr[0]+1,intSeatArr[1]+1);
		System.out.println();
		System.out.println("회원님의 남은 이용 시간 : " + this.totalplaytime);
		System.out.println();
		if (!totalOrderList.isEmpty()) {
			for (Food food : totalOrderList) {
				System.out.println("회원님의 음식 주문 내역: " + food);
			}
		} else {
			System.out.println("회원님의 음식 주문 내역이 없습니다.");
		}
		System.out.println("회원님의 잔액 : " +this.money);
		
		

	}

	
	
	public boolean addCartFood(InternetCafe ic,Scanner sc) {
		HashMap<String, Food> foodlist = ic.getAdmin().getFoodlist();
		if (ic.getAdmin().showFoodList()) {
			loop_1: while (true) {

				System.out.println();
				System.out.println("원하시는 음식의 이름을 입력해주세요.");
				System.out.println("만약 결제를 원하시면  *를 입력해주세요");
				String foodname = sc.nextLine();
				

				if (foodname.equals("*")) { // *누르면 계산으로 넘어감
					break loop_1;
				}
				if (foodlist.containsKey(foodname)) {// 입력받은 음식 이름이 foodlist에 있는지 확인
					int number = 0;
					for (int i = 0; i < cart.size(); i++) {
						Food food = cart.get(i);
						if (food.getpName().equals(foodname)) {
							while (true) {
								try {
									System.out.println("몇 개를 구매하시겠습니까? (1개 이상 선택해주세요.)");
									number = Integer.parseInt(sc.nextLine());
									if (number <= 0) {
										continue;
									}
									break;
								} catch (Exception e) {
									System.out.println("잘못 입력하셨습니다.");
									System.out.println("숫자를 입력해주세요.");
									System.out.println();
								}
							}

							cart.remove(i);
							System.out.printf("[%s]님의 카트에 [%s], [%d]개가 추가되었습니다.\n", this.getId(), foodname, number);
							number += food.getNumber();
							food.setNumber(number);
							cart.add(food);

							System.out.println();
							System.out.println("회원님의 주문 목록");
							System.out.println();
							// 시간은 어떻게?
							for (Food cartin : cart) {
								System.out.println(cartin.toString());

							}
							System.out.println();
							continue loop_1;
						}
					}

					while (true) {
						try {
							System.out.println("몇 개를 구매하시겠습니까? (1개 이상 선택해주세요.)");
							number = Integer.parseInt(sc.nextLine());
							if (number <= 0) {
								continue;
							}
							break;
						} catch (Exception e) {
							System.out.println("잘못 입력하셨습니다.");
							System.out.println("숫자를 입력해주세요.");
							System.out.println();
						}
					}
					foodlist.get(foodname).setNumber(number);

					cart.add(foodlist.get(foodname));

					System.out.printf("[%s]님의 카트에 [%s], [%d]개가 추가되었습니다.\n", this.getId(), foodname, number);
					System.out.println();

					System.out.println("회원님의 주문 목록");
					System.out.println();
					// 시간은 어떻게?
					for (Food cartin : cart) {
						System.out.println(cartin.toString());

					}
					System.out.println();
				} else {
					System.out.println("음식 이름을 잘못 입력하셨습니다.");
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
			System.out.println("정말로 결제하시겠습니까? (Y/N)");
			String yesno = sc.nextLine();
			if (yesno.equalsIgnoreCase("n")) {
				System.out.println("취소되었습니다.");
				break;
			} else if (yesno.equalsIgnoreCase("y")) {
				if (this.money < ic.getAdmin().getTimelist()[0].getpPrice()) {
					System.out.println("회원님의 잔액이 부족합니다.");

					break;
				} else {
					this.money -= ic.getAdmin().getTimelist()[0].getpPrice();
					System.out.println("당신의 현재 잔액 : " + this.money);
					transaction.setMoney(ic.getAdmin().getTimelist()[0].getpPrice());
					transaction.setTransactiodate(transaction.getTransactiodate());
					transaction.setTransactiontime(transaction.getTransactiontime());
					transactions.add(transaction);
					break;
				}
			} else {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("Y와 N중에 입력해주세요.");
			}
		}

	}

	public String displayPlayTime(InternetCafe ic,Scanner sc) {

		System.out.println("원하는 이용시간을 선택하세요.");
		System.out.println("1. 1시간: " + ic.getAdmin().getTimelist()[0].getpPrice());
		System.out.println("2. 3시간: " + ic.getAdmin().getTimelist()[1].getpPrice());
		System.out.println("3. 5시간: " + ic.getAdmin().getTimelist()[2].getpPrice());
		System.out.println("4. 10시간: " + ic.getAdmin().getTimelist()[3].getpPrice());
		System.out.println("0. 나가기");
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
					System.out.println("정말로 결제하시겠습니까? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("취소되었습니다.");
						
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[0].getpPrice()) {
							System.out.println("회원님의 잔액이 부족합니다.");
							System.out.println("당신의 현재 잔액 : " + this.money);
							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[0].getpPrice();
							System.out.println("당신의 현재 잔액 : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[0].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[0].getPlaytime();
							System.out.printf("[%s]님 이용시간 [%d]시간이 추가되었습니다.\n", this.getId(),
									ic.getAdmin().getTimelist()[0].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("잘못 입력하셨습니다.");
						System.out.println("Y와 N중에 입력해주세요.");
					}
				}

			case "2":
				while (true) {
					System.out.println("정말로 결제하시겠습니까? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("취소되었습니다.");
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[1].getpPrice()) {
							System.out.println("회원님의 잔액이 부족합니다.");
							System.out.println("당신의 현재 잔액 : " + this.money);

							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[1].getpPrice();
							System.out.println("당신의 현재 잔액 : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[1].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[1].getPlaytime();
							System.out.printf("[%s]님 이용시간 [%d]시간이 추가되었습니다.\n", this.getId(),
									ic.getAdmin().getTimelist()[1].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("잘못 입력하셨습니다.");
						System.out.println("Y와 N중에 입력해주세요.");
					}
				}

			case "3":
				while (true) {
					System.out.println("정말로 결제하시겠습니까? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("취소되었습니다.");
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[2].getpPrice()) {
							System.out.println("회원님의 잔액이 부족합니다.");
							System.out.println("당신의 현재 잔액 : " + this.money);

							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[2].getpPrice();
							System.out.println("당신의 현재 잔액 : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[2].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[2].getPlaytime();
							System.out.printf("[%s]님 이용시간 [%d]시간이 추가되었습니다.\n", this.getId(),
									ic.getAdmin().getTimelist()[2].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("잘못 입력하셨습니다.");
						System.out.println("Y와 N중에 입력해주세요.");
					}
				}

			case "4":
				while (true) {
					System.out.println("정말로 결제하시겠습니까? (Y/N)");
					String yesno = sc.nextLine();
					if (yesno.equalsIgnoreCase("n")) {
						System.out.println("취소되었습니다.");
						break loop_2;
					} else if (yesno.equalsIgnoreCase("y")) {
						if (this.money < ic.getAdmin().getTimelist()[3].getpPrice()) {
							System.out.println("회원님의 잔액이 부족합니다.");
							System.out.println("당신의 현재 잔액 : " + this.money);

							break loop_2;
						} else {
							this.money -= ic.getAdmin().getTimelist()[3].getpPrice();
							System.out.println("당신의 현재 잔액 : " + this.money);
							transaction.setMoney(ic.getAdmin().getTimelist()[3].getpPrice());
							transaction.setTransactiodate(transaction.getTransactiodate());
							transaction.setTransactiontime(transaction.getTransactiontime());
							transactions.add(transaction);
							this.totalplaytime += ic.getAdmin().getTimelist()[3].getPlaytime();
							System.out.printf("[%s]님 이용시간 [%d]시간이 추가되었습니다.\n", this.getId(),
									ic.getAdmin().getTimelist()[3].getPlaytime());
							break loop_2;
						}
					} else {
						System.out.println("잘못 입력하셨습니다.");
						System.out.println("Y와 N중에 입력해주세요.");
					}
				}

			case "0":
				break loop_1;

			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
				break loop_1;
			}
		}

	}

	public void buyfood(InternetCafe ic,Scanner sc) { // 계좌랑 어떻게??
		if (addCartFood(ic,sc)) {
			int totalprice = 0;
			Transaction transaction = new Transaction();
			for (Food cartin : cart) {

				totalprice += cartin.getpPrice() * cartin.getNumber();
			}

			System.out.println("총 금액 : " + totalprice);
			while (true) {
				System.out.println("정말로 결제하시겠습니까? (Y/N)");
				String select = sc.nextLine();
				if (select.equalsIgnoreCase("Y")) {
					if (totalprice > this.money) {
						System.out.println("고객님의 잔액이 부족합니다.");
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
					System.out.println("결제가 취소되었습니다.");
					cart.clear();
					break;
				} else {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("Y와 N중에 입력해주세요.");
				}
			}
			System.out.println("당신의 현재 잔액 : " + this.money);
			
	}

}
}