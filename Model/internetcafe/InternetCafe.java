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
* 클래스명 : InternetCafe // 피시방 클래스
* 
* 버전 정보 v.1.0
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
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
		System.out.println("아이디를입력하세요");
		String id = sc.next();
		System.out.println("비밀번호입력하세요");
		String pwd = sc.next();
		Member loginUser = memberList.get(id);
		if (loginUser != null && loginUser.getId().equals(id) && loginUser.getPwd().equals(pwd)) {
			System.out.println("로그인 되었습니다");
			return loginUser;
		} else {
			System.out.println("잘못 입력 되었습니다 , 다시 입력해주세요");
		}

		return null;
	}

	public void showMain(Scanner sc) {
		System.out.println("=====어서오세요 4조피시방=====");
		System.out.println("이용하려면 아무키나 입력하세요: ");
		String touch = sc.nextLine();
		
		if (touch.equals("admin") == true) {
			adminMenu(sc);	
		}
		showSeat();
	}

	public void showSeat() {
		for (int i = 0; i < this.seat.length; i++) {
			for (int j = 0; j < this.seat[i].length; j++) {
				System.out.printf("[%s]", this.seat[i][j] == null ? "이용가능" : "!이용중!");
			}
			System.out.println();
		}

	}

	private void selectNonMember(Scanner sc) {
		while (true) {
			System.out.println("1. 카드발급");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");

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
				System.out.println("이용해주셔서 감사합니다");
				showMain(sc);

			default:
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요. ");
				break;
			}

		}

	}

	public void selectCustomer(Scanner sc) {
		while (true) {
			System.out.println("1. 회원");
			System.out.println("2. 비회원");
			System.out.println("3. 종료");

			int s_c_key = sc.nextInt();
			switch (s_c_key) {
			case 1:
				System.out.println("로그인을 해주세요");
				signUp(sc);
				break;

			case 2:
				selectNonMember(sc);
				break;

			case 3:
				System.out.println("이용해주셔서 감사합니다");
				showMain(sc);

			default:
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요. ");
				break;
			}

		}

	}

	public void selectSeat(Scanner sc) {

		int row = 0;
		int col = 0;

		do {

			try {

				System.out.println("=====좌석을 선택해주세요=====");
				System.out.print("ex) 1,3: ");
				String inputSeat = sc.nextLine();
				String[] seatArr = inputSeat.trim().replace(" ", "").split(",");

				row = Integer.parseInt(seatArr[0]) - 1; // 인덱스입력이니까
				col = Integer.parseInt(seatArr[1]) - 1; // 인덱스입력이니까

				if (row > MAX_ROW_INDEX || col > MAX_COL_INDEX) {
					throw new Exception(); // 배열을 벗어난 자리
				}

				if (seat[row][col] == null) {
					// seat[row][col] = -> ,기준으로 나눠서 int로 바꾸고 row랑 col에 넣을수있게!!
					System.out.println("좌석선택 완료");
					selectCustomer(sc);

				} else {
					throw new Exception(); // 이미 예약된 자리
				}

			} catch (Exception e) {
				System.out.println("좌석을 선택할 수 없습니다. 다시 선택해주세요");
				System.out.println("(1,1)부터 (5,4)까지의 좌석만 존재합니다.");
			}

		} while (true);

	}
	
	private void adminMenu(Scanner sc) {
		System.out.println("관리자 페이지입니다.");
		System.out.println("비밀번호를 입력해주세요>> ");
		String admin_pw = sc.nextLine();

		// 비번이 맞으면 관리자메뉴 오픈
		if (admin_pw.equals("admin") == true) {
			Administrator admin = new Administrator();
			System.out.println("1. 메뉴추가    2.메뉴삭제    3.정산   4.이용자명단");
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
				System.out.println("이용해주셔서 감사합니다");
				showMain(sc);

			default:
				System.out.println("잘못된 메뉴입니다. 다시 입력해주세요. ");
				break;

			}

		}

	}

	public void signUp(Scanner sc) { // 회원가입
		Member member = new Member();
		UserInfo userInfo = new UserInfo();
		System.out.println("아이디를 입력하세요");
		member.setId(sc.next());
		System.out.println("비밀번호를 입력하세요");
		member.setPwd(sc.next());
		System.out.println("이름을 입력하세요");
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