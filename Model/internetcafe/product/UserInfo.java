package internetcafe.product;
 
import java.util.Scanner;
/*
* 클래스명 : UserInfo // 유저데이터 입력할 때 정규표현식사용을 위한 클래스
* 
* 버전 정보 v.1.0
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
    
    public static String isId(String data) {
        System.out.println("[1] ID 입력  ");
        String regExp = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
        data = sc.nextLine();
        boolean b1 = data.matches(regExp);
        if(b1 == true) {
            System.out.println("올바른 형식입니다.");
        }
        else {
            System.out.println("ID의 형식은 6~12자 사이에 영어 대,소문자,숫자가 올 수 있으며 첫문자의 경우 영문자 중 하나를 입력하셔야합니다.");
        }
		return data;
    }
 
    // -------------------------2--------------------------
    
    public static void isPwd() {
        System.out.println("[2] 비밀번호 입력:");
        String regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
        String data = sc.nextLine();
        
        boolean b2 = data.matches(regExp);
        
        if(b2==true) {
            System.out.println("올바른 형식입니다. 짝짝짝!!");
        }else {
            System.out.println("6~12자 사이에 영어대문자, 특수문자도 들어올 수 있습니다.");
        }
    }
    
    
    // -------------------------3--------------------------
    
    public static void isName() {
        System.out.println("[3] 이름 입력 : ");
        String regExp = "^[ㄱ-ㅎ가-힣]*$";
        String data = sc.nextLine();
        boolean b3 = data.matches(regExp);
        if(b3 == true) {
            System.out.println("올바른 형식입니다.");
        }
        else {
            System.out.println("올바른 이름형식이 아닙니다, 이름은 한글 형식만 입력 가능합니다.");
        }
    }
    // -------------------------4--------------------------
    
    public static void isPhone() {
        System.out.println("[4] 휴대번호 입력: ");
        //폰 앞자리 010/ 가운데 4자리/ 마지막4자리
        //010확정짓고 \d(숫자,정수)
        //{} 괄호안의 숫자만큼 앞의 문자열 반복
        
        String regExp = "(010)-?\\d{4}-?\\d{4}";
        String data = sc.nextLine();
        
        boolean b4 = data.matches(regExp);  //true, false 유효성 검사
        
        if(b4 == true) {
            System.out.println("올바른 형식입니다.");
        }else {
            System.out.println("양식에 맞춰 다시 입력해주세요. 예시) 010-xxxx-xxxx입니다. ");
            
        }
    }
    
    // -------------------------5--------------------------
    public static void isEmail() {
        //@ 앞 영어 대소문자 숫자만
        //@ 뒤 영어 대소문자
        //. 뒤에 영어 대소문자 
        //{} 선행문자가 나타는 횟수 또는 범위..?
        System.out.println("[5] 이메일 입력");
        
        String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        String data = sc.nextLine();
        
        boolean b5 = data.matches(regExp); //true와 false 유효성 검사
        
        if (b5 == true) {
            System.out.println("올바른 형식으로 입력하셨습니다.");
        } else {
            System.out.println("example1006@bit.com의 형식으로 입력해주세요.");
        }
        
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("******************************************************");
            System.out.println("[1] 아이디       [2] 비밀번호     [3] 이름            [4] 핸드폰번호    [5] 이메일       [0] 종료");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
            case 1 :
                isId(sc.nextLine());
                break;
            case 2 :
                isPwd();
                break;
            case 3 :
                isName();
                break;
            case 4 :
                isPhone();
                break;
            case 5 :
                isEmail();
                break;
            case 0 :
                System.exit(0);
                break;
            default :
                System.out.println("잘못된 번호 입력");
                break;
            }
            
            
            
        }
    }    
    
}