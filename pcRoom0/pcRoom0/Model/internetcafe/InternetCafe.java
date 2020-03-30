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
* 클래스명 : InternetCafe // 피시방 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 오주형
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
		admin = new Administrator("사조", "참치"); // 관리자 아이디 비밀번호 자동 생성
		sc = new Scanner(System.in);
		this.memberList = new HashMap<String, Member>();
		this.seat = new Member[MAX_ROW_INDEX][MAX_COL_INDEX];

		File filename = new File("UserList.txt");

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		if (filename.exists()) { // 저장되어 있던 유저목록 불러오기
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

	public void firstMain() { // 피시방 처음 화면
		while (true) {
			System.out.println("■-------------------***----------------------■");
			System.out.println("■----------------*****_*****-----------------■");
			System.out.println("■-----------=******************=-------------■");
			System.out.println("■--------==*사조피시방에 오신걸 환영합니다*==--------■");
			System.out.println("■-----*********-**************-*********-----■");
			System.out.println("■----------*********-**-********-------------■");
			System.out.println("■-------------*******-*******----------------■");
			System.out.println("■-----------------*-***-*--------------------■");
			System.out.println();
			System.out.println("■--------------아무 키나 입력하시오---------------■");

			String input = sc.nextLine();
			if (input.equals("admin")) {
				adminPageSelect();
			}
			memberSignSelect();

		}
	}

	public String memberSignPage() { // 회원 회원가입 선택 화면
		showSeat();
		System.out.println("■-----------이용하실 서비스를 선택하세요------------■");
		System.out.println("1. 회원으로 이용");
		System.out.println("2. 회원 가입");
		System.out.println("0. 나가기");
		System.out.println("■-------------------------------------------■");
		String menu = sc.nextLine();
		return menu;
	}

	public void memberSignSelect() { // 1 로그인 2 회원가입 선택
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
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("메뉴는 0 ~ 2번까지 입니다.");
				break;
			}
		}

	}

	public void loginSelect() { // 로그인 메뉴 선택
		Member member = login();
		if (member == null) {
			return;
		}
		if (member.isLog() == true) {
			// 회원이 로그인이 되어있지 않으면 로그값을 true로 바꿔줌
			while (true) {
				try {
					System.out.println("소지금액을 입력해주세요.");
					int money = Integer.parseInt(sc.nextLine());
					if (money < 1000) {
						if (money <= 0) {
							System.out.println("잘못 입력하셨습니다.");
							System.out.println("정확한 돈을 입력해주세요.");
						}
						System.out.println("해당금액으로는 서비스 이용이 어렵습니다 다시 이용해주세요");
						continue;

					}
					if (member.getMoney() <= 0) {
						System.out.println("돈을 충전하시고 이용해주세요");
						continue;
					}

					member.setMoney(member.getMoney() + money);
					break;
				} catch (Exception e) {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("숫자를 입력해주세요.");
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
			System.out.println("■--------------로그인을 시작합니다---------------■");
			System.out.println("ID를 입력하세요. (0번 입력시 나가기)");
			id = sc.nextLine().trim();
			if(id.equals("0")) {
				return null;
			}
			System.out.println("PassWord를 입력하세요. (0번 입력시 나가기)");
			pwd = sc.nextLine().trim();
			if(pwd.equals("0")) {
				return null;
			}
			if(!memberList.containsKey(id)) {
				System.out.println("ID가 틀립니다.");
				System.out.println("재입력 하세요.");
	
			}else {
				if(memberList.get(id).getPwd().equals(pwd)) {
					
					
					System.out.println("로그인 되었습니다.");
					System.out.println("■-------------------------------------------■");
					System.out.println();
//					while (true) {
//						try {
//							System.out.println("소지하신 금액을 입력해주세요.");
//							int money = Integer.parseInt(sc.nextLine());
//							if (money < 0) {
//								System.out.println("잘못 입력하셨습니다.");
//								System.out.println("정확한 돈을 입력해주세요.");
//								continue;
//							}
//							
//							
//							memberList.get(id).setMoney(memberList.get(id).getMoney() + money);
//							break;
//						} catch (Exception e) {
//							System.out.println("잘못 입력하셨습니다.");
//							System.out.println("숫자를 입력해주세요.");
//							System.out.println();
//						}
//					}
					break;
					
				}else {
					System.out.println("PassWord가 틀립니다.");
					System.out.println("재입력 하세요.");
				}
			}
			
		}return memberList.get(id);
		}

	public void logout(Member member) {

		int playTime = 0;
		while (true) {
			try {
				if(member.totalplaytime == 0) break;
				System.out.println("■-------------로그아웃을 진행합니다--------------■");
				System.out.println("몇시간 이용하셨습니까?");
				playTime = Integer.parseInt(sc.nextLine());
				if (playTime < 0 || member.getTotalplaytime() < playTime) {
					System.out.println("시간을 잘못 입력하셨습니다.");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("숫자를 입력해주세요.");
				System.out.println();
			}
		}
		int totalplaytime = member.getTotalplaytime() - playTime;
		member.setTotalplaytime(totalplaytime);

		System.out.println("회원님의 남은 시간 : " + totalplaytime);
		System.out.println("이용해주셔서 감사합니다.");
		System.out.println("■-------------------------------------------■");
		System.out.println();

		member.logCheck();

		int[] intSeatArr = member.getSeatArr();

		if (seat[intSeatArr[0]][intSeatArr[1]] != null) {

			seat[intSeatArr[0]][intSeatArr[1]] = null;
			System.out.println("로그아웃 되었습니다.");
			File filename = new File("UserList.txt"); // 회원을 파일로 저장
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

	public void selectSeat(String id) { // 선택만 하는걸로 .. 번호값 리턴?
		int row = 0;
		int col = 0;

		do {
			try {
				System.out.println("■---------------좌석을 선택해주세요--------------■");
				System.out.println("■-----------------Ex : 1-3 -----------------■");
				System.out.println("■-------------------------------------------■");
				System.out.print("좌석 선택 :  ");
				String inputSeat = sc.nextLine();
				String seatArr[] = inputSeat.trim().replace(" ", "").split("-");

				row = Integer.parseInt(seatArr[0]) - 1; // 인덱스입력이니까
				col = Integer.parseInt(seatArr[1]) - 1; // 인덱스입력이니까

				if (row > MAX_ROW_INDEX || col > MAX_COL_INDEX) {
					throw new Exception("좌석을 잘못 선택하셨습니다"); // 배열을 벗어난 자리
				}
				if (this.seat[row][col] == null) {
					// seat[row][col] = -> ,기준으로 나눠서 int로 바꾸고 row랑 col에 넣을수있게!!
					System.out.println("좌석선택 완료");

					System.out.println("선택하신 좌석은 " + " [" + (row + 1) + "]" + " [" + (col + 1) + "] 입니다");
					seat[row][col] = memberList.get(id);
					int[] intSeatArr = new int[] { row, col };
					memberList.get(id).setSeatArr(intSeatArr); // 회원 자리 할당

					while (true) {
						try {
							System.out.println("소지하신 금액을 입력해주세요.");
							int money = Integer.parseInt(sc.nextLine());
							if (money < 0) {
								System.out.println("잘못 입력하셨습니다.");
								System.out.println("정확한 돈을 입력해주세요.");
								continue;
							}

							memberList.get(id).setMoney(memberList.get(id).getMoney() + money);
							break;
						} catch (Exception e) {
							System.out.println("잘못 입력하셨습니다.");
							System.out.println("숫자를 입력해주세요.");
							System.out.println();
						}
					}
					break;
				} else if (memberList.get(id).getSeatArr()[0] == row && memberList.get(id).getSeatArr()[1] == col) {

				} else {
					throw new Exception("이용중인 좌석입니다"); // 이미 예약된 자리
				}
			} catch (Exception e) {
				System.out.println("좌석을 선택할 수 없습니다. 다시 선택해주세요");
				System.out.println("(1,1)부터 (4,5)까지의 좌석만 존재합니다.");
			}
		} while (true);

	}

	public void showSeat() {
		System.out.println("   --1 ---2 ---3 ---4 ---5 --");
		for (int i = 0; i < this.seat.length; i++) {
			System.out.printf("%d| ", i + 1);
			for (int j = 0; j < this.seat[i].length; j++) {
				System.out.printf("[%s]", this.seat[i][j] == null ? " □ " : " ■ ");
			}
			System.out.println();
		}

	}

	public String adminPage() { // 관리자 페이지 시작
		System.out.println("****************************************");
		System.out.println("관리자 페이지 입니다.");
		System.out.println("1. 관리자 로그인");
		System.out.println("0. 나가기");
		System.out.println("****************************************");
		String menu = sc.nextLine();
		return menu;
	}

	public void adminPageSelect() { // 관리자 페이지 선택
		Loop_1: while (true) {
			String menunum = adminPage();
			switch (menunum) {
			case "1":
				System.out.println("아이디를 입력해주세요.");
				String adminid = sc.nextLine();
				if (!adminid.equals(admin.getId())) {
					System.out.println("잘못 입력하셨습니다.");
					break;
				}
				System.out.println("비밀번호를 입력해주세요.");
				String adminpwd = sc.nextLine();
				if (!adminpwd.equals(admin.getPwd())) {
					System.out.println("잘못 입력하셨습니다.");
					break;
				}
				selectAdminMenu();
				break;
			case "0":
				break Loop_1;
			default:
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("메뉴는 0 ~ 1번까지 입니다.");

			}
		}
	}

	public String displayAdminMenu() { // 관리자 메뉴 화면
		System.out.println("**************관리자 메뉴********************");
		System.out.println("1. 물품 추가");
		System.out.println("2. 물품 삭제");
		System.out.println("3. 시간 요금 변경");
		System.out.println("4. 매장 매출 확인");
		System.out.println("5. 회원 리스트 확인");
		System.out.println("6. 프로그램 종료");
		System.out.println("0. 나가기");
		System.out.println("*******************************************");
		String menu = sc.nextLine();
		return menu;
	}

	public void selectAdminMenu() { // 관리자 메뉴 선택

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
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("메뉴는 0~6번까지 입니다.");
			}
		}
	}

	public String memberMenu() { // 회원 메뉴 화면

		System.out.println("**************************************");
		System.out.println("1. 이용시간 추가");
		System.out.println("2. 음식 주문");
		System.out.println("3. 시간 및 주문 조회");
		System.out.println("4. 로그아웃"); // 로그아웃하고 처음 화면으로 돌아가자
		System.out.println("0. 처음으로 돌아가기"); // 수정하자
		System.out.println("**************************************");
		String menu = sc.nextLine();
		return menu;
	}

	public void selectMemberMenu(Member member) { // 회원 메뉴 선택

		loop_1: while (true) {
			System.out.println();
			System.out.printf("안녕하세요 [%s]님\n", member.getId());
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
				System.out.println("안녕히 가세요.");
				break loop_1;
			default:
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("메뉴는 0~4번까지 입니다.");
			}
		}
	}

	public Member signUp() { // 회원 가입

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
				System.out.println("입력하신 정보가 맞습니까? (Y/N)");
				String yesno = sc.nextLine();
				if (yesno.equalsIgnoreCase("Y")) {
					break loop_1;
				} else if (yesno.equalsIgnoreCase("N")) {
					continue loop_1;
				} else {
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("Y와 N중에 입력해주세요.");
				}
			}
		}

		Member member = new Member(id, pwd, name, phonenumber); // 아이디 비번 이름 폰번호를 입력받아서 회원을 만들자
		memberList.put(member.getId(), member);
		System.out.println("회원가입이 되었습니다.");
		System.out.printf("[%s]님의 id : [%s]\n", name, id);
		System.out.printf("[%s]님의 pwd : [%s]\n", name, pwd);
		System.out.printf("[%s]님의 휴대폰 번호 : [%s]\n", name, phonenumber);

		File filename = new File("UserList.txt"); // 회원을 파일로 저장
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