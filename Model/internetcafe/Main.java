package internetcafe;
import java.util.Scanner;
/*
* 클래스명 : Main  //메인
* 
* 버전 정보 v.1.0
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
*/


public class Main {

	public static void main(String[] args) {
		
		InternetCafe internetCafe = new InternetCafe();
		internetCafe.showSeat();
		Scanner sc = new Scanner(System.in);
		
		internetCafe.signUp(sc);
		System.out.println(internetCafe.getmemberList());
		
	}

}
