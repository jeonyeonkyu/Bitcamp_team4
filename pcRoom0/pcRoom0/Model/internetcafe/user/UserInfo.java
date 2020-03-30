package internetcafe.user;
 
import java.util.Scanner;

import internetcafe.InternetCafe;
/*
* Ŭ������ : UserInfo // ���������� �Է��� �� ����ǥ���Ļ���� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class UserInfo {
    //static ����
    static String data = "";
    static String regExp;
    static Scanner sc = new Scanner(System.in);
    
    // -------------------------1--------------------------
    
    public static String id(InternetCafe ic) {
		
		regExp = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
		while (true) {
			System.out.println("[1] ID �Է�  ");
			data = sc.nextLine();
			boolean b1 = data.matches(regExp);
			if (b1) {
				if (ic.getmemberList().containsKey(data)) {
					System.out.println("�ߺ��� ID �Դϴ�.");
					continue;
				}
				
				break;
			} else {
				System.out.println("ID�� ������ 6~12�� ���̿� ���� ��,�ҹ���,���ڰ� �� �� ������ ù������ ��� ������ �� �ϳ��� �Է��ϼž��մϴ�.");
			}
		}
		return data;
    }

    // -------------------------2--------------------------
    
    public static String pwd() {
		String regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
		while (true) {
			System.out.println("[2] ��й�ȣ �Է�:");
			String pwd = sc.nextLine();
			boolean b21 = pwd.matches(regExp);
			if (b21) {
				while (true) {
					System.out.println("�ùٸ� ��й�ȣ �����Դϴ�.");
					System.out.println("��й�ȣ Ȯ�� >> ��й�ȣ�� �ѹ� �� �Է����ּ���");
					String pwd2 = sc.nextLine();
					boolean b22 = pwd2.matches(regExp);
					if (b22) {
						if (pwd.equals(pwd2)) {
							System.out.println("��й�ȣ�� ��ġ�մϴ�");
							return pwd;
						} else {
							System.out.println("�غ�й�ȣ�� ��ġ���� �ʽ��ϴ�. ��й�ȣ�� ó������ �ٽ� �Է����ּ���");
							break;
						}
					}
				}
			} else {
				System.out.println("��й�ȣ ������ 6~12�� ���̿� ����빮��, Ư�����ڵ� ���� �� �ֽ��ϴ�.");
			}
		}
	}

    
    
    // -------------------------3--------------------------
    
    public static String name() {
    	while(true) {
        System.out.println("[3] �̸� �Է� : ");
        regExp = "^[��-����-�R]*$";
        data = sc.nextLine();
        boolean b3 = data.matches(regExp);
        if(b3 == true) {
           break;
        }
        else {
            System.out.println("�̸��� �ѱ۷� �Է��ϼž� �մϴ�.");
        }
    	}
    	return data;
    }
    // -------------------------4--------------------------
    
    public static String phone() {
    	while(true) {
        System.out.println("[4] �޴��ȣ �Է�: ");
        //�� ���ڸ� 010/ ��� 4�ڸ�/ ������4�ڸ�
        //010Ȯ������ \d(����,����)
        //{} ��ȣ���� ���ڸ�ŭ ���� ���ڿ� �ݺ�
        
        regExp = "(010)-?\\d{4}-?\\d{4}";
        data = sc.nextLine();
        
        boolean b4 = data.matches(regExp);  //true, false ��ȿ�� �˻�
        
        if(b4 == true) {
           break;
        }else {
            System.out.println("�޴��� ��ȣ�� 010-xxxx-xxxx�� �������� �Է��ϼž� �մϴ�. ");
            
        }
    	}
    	return data;
    }
    
    // -------------------------5--------------------------
   
 
            
           
        
    
    
}