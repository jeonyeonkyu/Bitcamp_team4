package internetcafe.user;

import java.util.HashMap;
import java.util.Scanner;

/*
* 클래스명 : UserInfo // 유저데이터 입력할 때 정규표현식사용을 위한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 26
* 
* 작업자 : 전연규
* id,pwd,name 함수를 isId,isPwd,isName 으로 바꾸고 스트링 값을 리턴하도록 변경
*/
public class UserInfo {
	// static 선언
	static String data = "";
	static String regExp = "";
	static Scanner sc = new Scanner(System.in);

	// -------------------------1--------------------------

	public static String isId(Scanner sc, HashMap<String, Member> memberList) {
		String regExpId = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
		while (true) {
			System.out.println("[1] ID 입력  ");
			String id = sc.nextLine();
			boolean b1 = id.matches(regExpId);
			if (b1) {
				if (memberList.containsKey(id)) {
					System.out.println("중복된 ID 입니다.");
					continue;
				}
				System.out.println("올바른 Id형식입니다.");
				return id;
			} else {
				System.out.println("ID의 형식은 6~12자 사이에 영어 대,소문자,숫자가 올 수 있으며 첫문자의 경우 영문자 중 하나를 입력하셔야합니다.");
			}
		}

	}

	// -------------------------2--------------------------

	public static String isPwd() {
		regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
		while (true) {
			System.out.println("[2] 비밀번호 입력:");
			data = sc.nextLine();
			boolean b2 = data.matches(regExp);
			if (b2) {
				System.out.println("올바른 비밀번호 형식입니다.");
				return data;
			} else {
				System.out.println("비밀번호 형식은 6~12자 사이에 영어대문자, 특수문자도 들어올 수 있습니다.");
			}
		}
	}

	// -------------------------3--------------------------

	public static String isName() {
		regExp = "^[ㄱ-ㅎ가-힣]*$";
		while (true) {
			System.out.println("[3] 이름 입력 : ");
			data = sc.nextLine();
			boolean b3 = data.matches(regExp);
			if (b3) {
				System.out.println("올바른 이름형식입니다.");
				return data;
			} else {
				System.out.println("이름 형식 한글만 입력 가능합니다.");
			}
		}
	}
	// -------------------------4--------------------------

	public static void isPhone() {
		System.out.println("[4] 휴대번호 입력: ");
		// 폰 앞자리 010/ 가운데 4자리/ 마지막4자리
		// 010확정짓고 \d(숫자,정수)
		// {} 괄호안의 숫자만큼 앞의 문자열 반복

		String regExp = "(010)-?\\d{4}-?\\d{4}";
		String data = sc.nextLine();

		boolean b4 = data.matches(regExp); // true, false 유효성 검사

		if (b4 == true) {
			System.out.println("올바른 형식입니다.");
		} else {
			System.out.println("양식에 맞춰 다시 입력해주세요. 예시) 010-xxxx-xxxx입니다. ");

		}
	}

	// -------------------------5--------------------------
	public static void isEmail() {
		// @ 앞 영어 대소문자 숫자만
		// @ 뒤 영어 대소문자
		// . 뒤에 영어 대소문자
		// {} 선행문자가 나타는 횟수 또는 범위..?
		System.out.println("[5] 이메일 입력");

		String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		String data = sc.nextLine();

		boolean b5 = data.matches(regExp); // true와 false 유효성 검사

		if (b5 == true) {
			System.out.println("올바른 형식으로 입력하셨습니다.");
		} else {
			System.out.println("example1006@bit.com의 형식으로 입력해주세요.");
		}

	}

}