package internetcafe.product;
 
import java.util.Scanner;
/*
* Ŭ������ : UserInfo // ���������� �Է��� �� ����ǥ���Ļ���� ���� Ŭ����
* 
* ���� ���� v.1.0
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
    
    public static String isId(String data) {
        System.out.println("[1] ID �Է�  ");
        String regExp = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
        data = sc.nextLine();
        boolean b1 = data.matches(regExp);
        if(b1 == true) {
            System.out.println("�ùٸ� �����Դϴ�.");
        }
        else {
            System.out.println("ID�� ������ 6~12�� ���̿� ���� ��,�ҹ���,���ڰ� �� �� ������ ù������ ��� ������ �� �ϳ��� �Է��ϼž��մϴ�.");
        }
		return data;
    }
 
    // -------------------------2--------------------------
    
    public static void isPwd() {
        System.out.println("[2] ��й�ȣ �Է�:");
        String regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
        String data = sc.nextLine();
        
        boolean b2 = data.matches(regExp);
        
        if(b2==true) {
            System.out.println("�ùٸ� �����Դϴ�. ¦¦¦!!");
        }else {
            System.out.println("6~12�� ���̿� ����빮��, Ư�����ڵ� ���� �� �ֽ��ϴ�.");
        }
    }
    
    
    // -------------------------3--------------------------
    
    public static void isName() {
        System.out.println("[3] �̸� �Է� : ");
        String regExp = "^[��-����-�R]*$";
        String data = sc.nextLine();
        boolean b3 = data.matches(regExp);
        if(b3 == true) {
            System.out.println("�ùٸ� �����Դϴ�.");
        }
        else {
            System.out.println("�ùٸ� �̸������� �ƴմϴ�, �̸��� �ѱ� ���ĸ� �Է� �����մϴ�.");
        }
    }
    // -------------------------4--------------------------
    
    public static void isPhone() {
        System.out.println("[4] �޴��ȣ �Է�: ");
        //�� ���ڸ� 010/ ��� 4�ڸ�/ ������4�ڸ�
        //010Ȯ������ \d(����,����)
        //{} ��ȣ���� ���ڸ�ŭ ���� ���ڿ� �ݺ�
        
        String regExp = "(010)-?\\d{4}-?\\d{4}";
        String data = sc.nextLine();
        
        boolean b4 = data.matches(regExp);  //true, false ��ȿ�� �˻�
        
        if(b4 == true) {
            System.out.println("�ùٸ� �����Դϴ�.");
        }else {
            System.out.println("��Ŀ� ���� �ٽ� �Է����ּ���. ����) 010-xxxx-xxxx�Դϴ�. ");
            
        }
    }
    
    // -------------------------5--------------------------
    public static void isEmail() {
        //@ �� ���� ��ҹ��� ���ڸ�
        //@ �� ���� ��ҹ���
        //. �ڿ� ���� ��ҹ��� 
        //{} ���๮�ڰ� ��Ÿ�� Ƚ�� �Ǵ� ����..?
        System.out.println("[5] �̸��� �Է�");
        
        String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        String data = sc.nextLine();
        
        boolean b5 = data.matches(regExp); //true�� false ��ȿ�� �˻�
        
        if (b5 == true) {
            System.out.println("�ùٸ� �������� �Է��ϼ̽��ϴ�.");
        } else {
            System.out.println("example1006@bit.com�� �������� �Է����ּ���.");
        }
        
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("******************************************************");
            System.out.println("[1] ���̵�       [2] ��й�ȣ     [3] �̸�            [4] �ڵ�����ȣ    [5] �̸���       [0] ����");
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
                System.out.println("�߸��� ��ȣ �Է�");
                break;
            }
            
            
            
        }
    }    
    
}