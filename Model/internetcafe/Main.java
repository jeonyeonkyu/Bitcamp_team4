package internetcafe;

import java.util.Scanner;

/*
* Ŭ������ : Main // ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 26
* 
* �۾��� : ������
*
*/
public class Main {

	public static void main(String[] args) {

		InternetCafe internetCafe = new InternetCafe();

		System.out.println(internetCafe.getmemberList());
		internetCafe.showSeat();
		Scanner sc = new Scanner(System.in);
		internetCafe.signUp(sc);
		internetCafe.selectSeat(sc);
		internetCafe.showSeat();

	}

}
