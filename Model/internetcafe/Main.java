package internetcafe;
import java.util.Scanner;
/*
* Ŭ������ : Main  //����
* 
* ���� ���� v.1.0
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
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
