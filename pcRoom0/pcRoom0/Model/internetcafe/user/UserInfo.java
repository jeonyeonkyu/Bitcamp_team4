package internetcafe.user;
 
import java.util.Scanner;

import internetcafe.InternetCafe;
/*
* 클래스명 : UserInfo // 유저데이터 입력할 때 정규표현식사용을 위한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
*/
public class UserInfo {
    //static 선언
    static String data = "";
    static String regExp;
    static Scanner sc = new Scanner(System.in);
    
    // -------------------------1--------------------------
    
    public static String id(InternetCafe ic) {
		
		regExp = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
		while (true) {
			System.out.println("[1] ID 입력  ");
			data = sc.nextLine();
			boolean b1 = data.matches(regExp);
			if (b1) {
				if (ic.getmemberList().containsKey(data)) {
					System.out.println("중복된 ID 입니다.");
					continue;
				}
				
				break;
			} else {
				System.out.println("ID의 형식은 6~12자 사이에 영어 대,소문자,숫자가 올 수 있으며 첫문자의 경우 영문자 중 하나를 입력하셔야합니다.");
			}
		}
		return data;
    }

    // -------------------------2--------------------------
    
    public static String pwd() {
		String regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
		while (true) {
			System.out.println("[2] 비밀번호 입력:");
			String pwd = sc.nextLine();
			boolean b21 = pwd.matches(regExp);
			if (b21) {
				while (true) {
					System.out.println("올바른 비밀번호 형식입니다.");
					System.out.println("비밀번호 확인 >> 비밀번호를 한번 더 입력해주세요");
					String pwd2 = sc.nextLine();
					boolean b22 = pwd2.matches(regExp);
					if (b22) {
						if (pwd.equals(pwd2)) {
							System.out.println("비밀번호가 일치합니다");
							return pwd;
						} else {
							System.out.println("※비밀번호가 일치하지 않습니다. 비밀번호를 처음부터 다시 입력해주세요");
							break;
						}
					}
				}
			} else {
				System.out.println("비밀번호 형식은 6~12자 사이에 영어대문자, 특수문자도 들어올 수 있습니다.");
			}
		}
	}

    
    
    // -------------------------3--------------------------
    
    public static String name() {
    	while(true) {
        System.out.println("[3] 이름 입력 : ");
        regExp = "^[ㄱ-ㅎ가-힣]*$";
        data = sc.nextLine();
        boolean b3 = data.matches(regExp);
        if(b3 == true) {
           break;
        }
        else {
            System.out.println("이름은 한글로 입력하셔야 합니다.");
        }
    	}
    	return data;
    }
    // -------------------------4--------------------------
    
    public static String phone() {
    	while(true) {
        System.out.println("[4] 휴대번호 입력: ");
        //폰 앞자리 010/ 가운데 4자리/ 마지막4자리
        //010확정짓고 \d(숫자,정수)
        //{} 괄호안의 숫자만큼 앞의 문자열 반복
        
        regExp = "(010)-?\\d{4}-?\\d{4}";
        data = sc.nextLine();
        
        boolean b4 = data.matches(regExp);  //true, false 유효성 검사
        
        if(b4 == true) {
           break;
        }else {
            System.out.println("휴대폰 번호는 010-xxxx-xxxx의 형식으로 입력하셔야 합니다. ");
            
        }
    	}
    	return data;
    }
    
    // -------------------------5--------------------------
   
 
            
           
        
    
    
}