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

import internetcafe.product.Administrator;
import internetcafe.product.Member;
import internetcafe.product.Nonmember;
import internetcafe.product.UserInfo;
import internetcafe.user.Product;

/*
* 클래스명 : InternetCafe // 피시방 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 26
* 
* 작업자 : 전연규
* 회원가입 기능에 정규표현식으로 확인하기 추가
* 셀렉트 좌석에 좌석 선택 후 로그인 하기 기능 추가
* 비회원 이 없어서 전역변수인  seat를 Member[][] seat로 변경
*  // 휴대폰 번호 추가해야됨
*/
public class InternetCafe {

	private static final int MAX_ROW_INDEX = 4;
	private static final int MAX_COL_INDEX = 5;
	private HashMap<String, Member> memberList;
	private List<Nonmember> nonmemberList;
	private List<Product> foodlist;
	private Member[][] seat;

	public InternetCafe() {
		this.memberList = new HashMap<String, Member>();
		this.seat = new Member[MAX_ROW_INDEX][MAX_COL_INDEX];
		this.nonmemberList = new ArrayList<Nonmember>();
		this.foodlist = new ArrayList<Product>();
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
				member.setMoney(Integer.parseInt(array[3]));
				member.setTotalplaytime(Integer.parseInt(array[4]));
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
			System.out.println("아이디를입력하세요 , 0을 입력 시 종료");
			String id = sc.next();
			if (id.equals("0")) {
				showMain(sc); // 메인메뉴로 돌아가야함
				return null;
			}
			System.out.println("비밀번호입력하세요, 0을 입력 시 종료");
			String pwd = sc.next();
			if (pwd.equals("0")) {
				showMain(sc);// 메인메뉴로 돌아가야함
				return null;
			}
			Member loginUser = memberList.get(id);
			if (loginUser != null && loginUser.getId().equals(id) && loginUser.getPwd().equals(pwd)) {
				System.out.println("로그인 되었습니다");

				if (loginUser.getMoney() < 500) { // 유저데이터에 있는 금액이 500원 보다 적다면
					System.out.println("현재 가지고 계신 금액은 얼마입니까?");
					int money = Integer.parseInt(sc.next());
					loginUser.setMoney(money); // 돈 넣어주기
					System.out.println("현재 가지고 있는 돈은 " + money + "원 입니다");
				}
				if (loginUser.getTotalplaytime() == 0) {
					System.out.println("시간을 충전하고 이용해주세요");
					// 시간충전 메소드 넣어야함
				}
				return loginUser;
			} else {
				System.out.println("잘못 입력 되었습니다 , 다시 입력해주세요");
				continue;
			}
		}

	}

	public void logout(int row, int col) {
		if (seat[row][col] != null) {
			seat[row][col] = null;
		} 
		loadMemberList();
		Member member = new Member();
		  // 원본파일경로
		  String fileName = "C:\\internetCafe\\customer.txt";

		  // file 객체 생성
		  File inputFile = new File(fileName);
		  File outputFile = new File(fileName+".temp");

		  FileInputStream fileInputStream = null;
		  BufferedReader bufferedReader = null;
		  FileOutputStream fileOutputStream = null;
		  BufferedWriter bufferedWriter = null;

		  boolean result = false;

		  try {
		   // FileInputStream,FileOutputStream, BufferdReader, BufferedWriter
		   // 생성
		   fileInputStream = new FileInputStream(inputFile);
		   fileOutputStream = new FileOutputStream(outputFile);

		   bufferedReader = new BufferedReader(new InputStreamReader(
		     fileInputStream));
		   bufferedWriter = new BufferedWriter(new OutputStreamWriter(
		     fileOutputStream));

		   // 원본 파일에서 읽어 들이는 한라인
		   String line;
		   // 패턴에 일치하는 문자로 대체하고 난 후의 string
		   String repLine;

		   // 바꾸고자 하는 string과 바꿀 string 정의
		   String originalString = member.getId() + "," + member.getPwd() + "," + member.getName() + "," + member.getMoney()
			+ "," + member.getTotalplaytime();
		   String replaceString =  member.getId() + "," + member.getPwd() + "," + member.getName() + "," + member.getMoney()
			+ "," + member.getTotalplaytime();

		   // 원본 파일에서 한라인씩 읽는다.
		   while ((line = bufferedReader.readLine()) != null) {
		    // 일치하는 패턴에서는 바꿀 문자로 변환
		    repLine = line.replaceAll(originalString, replaceString);

		    // 새로운 파일에 쓴다.
		    bufferedWriter.write(repLine, 0, repLine.length());
		    bufferedWriter.newLine();
		   }
		   // 정상적으로 수행되었음을 알리는 flag
		   result = true;
		  } catch (IOException ex) {
		   ex.printStackTrace();
		  } finally {
		   // 리소스 해제. 개별적으로 해제한다.
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

		   // 정상적으로 수행되었을 경우 원본 파일을 지우고 새로운 파일명을 원본파일명으로 rename한다.
		   if (result) {
		    inputFile.delete();
		    outputFile.renameTo(new File(fileName));
		   }
		  }
		
		System.out.println("로그아웃 되셨습니다");
	}

	public void showMain(Scanner sc) {
		System.out.println("=====어서오세요 4조피시방=====");
		System.out.println("이용하려면 아무키나 입력하세요: ");
		String touch = sc.nextLine();

		if (touch.equals("admin")) {
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

	/*
	 * private void selectNonMember(Scanner sc) { while (true) {
	 * System.out.println("1. 카드발급"); System.out.println("2. 회원가입");
	 * System.out.println("3. 종료");
	 * 
	 * int key = sc.nextInt(); sc.nextLine();
	 * 
	 * switch (key) { case 1: int cardnum = ((int) (Math.random() * 20) + 1); break;
	 * 
	 * case 2: signUp(sc); break;
	 * 
	 * case 3: System.out.println("이용해주셔서 감사합니다"); showMain(sc);
	 * 
	 * default: System.out.println("잘못된 메뉴입니다. 다시 입력해주세요. "); break; }
	 * 
	 * }
	 * 
	 * }
	 */
	public void selectCustomer(Scanner sc) {
		while (true) {
			System.out.println("1. 회원로그인");
//			System.out.println("2. 비회원");
			System.out.println("3. 종료");

			int s_c_key = sc.nextInt();
			switch (s_c_key) {
			case 1:
				System.out.println("로그인을 해주세요");
				login(sc);
				break;

			case 2:
//				selectNonMember(sc);
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
					throw new Exception("좌석을 잘못 선택하셨습니다"); // 배열을 벗어난 자리
				}
				if (this.seat[row][col] == null) {
					// seat[row][col] = -> ,기준으로 나눠서 int로 바꾸고 row랑 col에 넣을수있게!!
					System.out.println("좌석선택 완료");
					this.seat[row][col] = (login(sc));// 로그인하기
					System.out.println("선택하신 좌석은 " + " [" + (row + 1) + "]" + " [" + (col + 1) + "] 입니다");

					// 1.충전한 시간 조회 및 추가
					// 2.음식 주문
					// 3.주문한 음식 조회
					// 4.로그아웃
					// 5.화면 나가기

					// 4번 로그아웃 아래
					System.out.println("로그아웃 하시겠습니까? ,1 입력 ");
					if(Integer.parseInt(sc.next()) == 1 ){
					logout(row, col);
					}
					// 로그아웃 위
					return;
				} else {
					throw new Exception("이용중인 좌석입니다"); // 이미 예약된 자리
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
		System.out.println("회원가입 메뉴입니다");
		Member member = new Member();
		UserInfo userInfo = new UserInfo();

		member.setId(userInfo.isId(sc, memberList));
		member.setPwd(userInfo.isPwd());
		member.setName(userInfo.isName());
		member.setMoney(0);
		member.setTotalplaytime(0);
		memberList.put(member.getId(), member);
		System.out.println("등록 되었습니다");
		System.out.println("시간충전하시고 이용해주세요");

		BufferedWriter bw = null;
		PrintWriter writer = null;
		try {
			String path = "C:\\internetCafe\\customer.txt";
			File f = new File(path);
			if (!f.exists()) {
				// 파일이 존재하지 않을경우 파일만들기
				f.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(path, true));
			writer = new PrintWriter(bw, true);
			String writeMember = member.getId() + "," + member.getPwd() + "," + member.getName() + "," + member.getMoney()
					+ "," + member.getTotalplaytime();
			// 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
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

	public List<Product> getFoodlist() {
		return foodlist;
	}

	public void setFoodlist(List<Product> foodlist) {
		this.foodlist = foodlist;
	}

	public Member[][] getSeat() {
		return seat;
	}

	public void setSeat(Member[][] seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "InternetCafe [memberList=" + memberList + ", nonmemberList=" + nonmemberList + ", foodlist=" + foodlist
				+ ", seat=" + Arrays.toString(seat) + "]";
	}

}