package internetcafe.product;
 
import java.util.Scanner;
/*
* Å¬·¡½º¸í : UserInfo // À¯Àúµ¥ÀÌÅÍ ÀÔ·ÂÇÒ ¶§ Á¤±ÔÇ¥Çö½Ä»ç¿ëÀ» À§ÇÑ Å¬·¡½º
* 
* ¹öÀü Á¤º¸ v.1.0
* 
* ¸¶Áö¸· ¾÷µ¥ÀÌÆ® ³¯Â¥ : 2020 - 03 - 25
* 
* ÀÛ¾÷ÀÚ : ¾öÁöÈñ
*/
public class UserInfo {
    //static ¼±¾ğ
    static String data = "";
    static String regExp;
    static Scanner sc = new Scanner(System.in);
    
    // -------------------------1--------------------------
    
    public static String isId(String data) {
        System.out.println("[1] ID ÀÔ·Â  ");
        String regExp = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
        data = sc.nextLine();
        boolean b1 = data.matches(regExp);
        if(b1 == true) {
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀÔ´Ï´Ù.");
        }
        else {
            System.out.println("IDÀÇ Çü½ÄÀº 6~12ÀÚ »çÀÌ¿¡ ¿µ¾î ´ë,¼Ò¹®ÀÚ,¼ıÀÚ°¡ ¿Ã ¼ö ÀÖÀ¸¸ç Ã¹¹®ÀÚÀÇ °æ¿ì ¿µ¹®ÀÚ Áß ÇÏ³ª¸¦ ÀÔ·ÂÇÏ¼Å¾ßÇÕ´Ï´Ù.");
        }
		return data;
    }
 
    // -------------------------2--------------------------
    
    public static void isPwd() {
        System.out.println("[2] ºñ¹Ğ¹øÈ£ ÀÔ·Â:");
        String regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
        String data = sc.nextLine();
        
        boolean b2 = data.matches(regExp);
        
        if(b2==true) {
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀÔ´Ï´Ù. Â¦Â¦Â¦!!");
        }else {
            System.out.println("6~12ÀÚ »çÀÌ¿¡ ¿µ¾î´ë¹®ÀÚ, Æ¯¼ö¹®ÀÚµµ µé¾î¿Ã ¼ö ÀÖ½À´Ï´Ù.");
        }
    }
    
    
    // -------------------------3--------------------------
    
    public static void isName() {
        System.out.println("[3] ÀÌ¸§ ÀÔ·Â : ");
        String regExp = "^[¤¡-¤¾°¡-ÆR]*$";
        String data = sc.nextLine();
        boolean b3 = data.matches(regExp);
        if(b3 == true) {
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀÔ´Ï´Ù.");
        }
        else {
            System.out.println("¿Ã¹Ù¸¥ ÀÌ¸§Çü½ÄÀÌ ¾Æ´Õ´Ï´Ù, ÀÌ¸§Àº ÇÑ±Û Çü½Ä¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
        }
    }
    // -------------------------4--------------------------
    
    public static void isPhone() {
        System.out.println("[4] ÈŞ´ë¹øÈ£ ÀÔ·Â: ");
        //Æù ¾ÕÀÚ¸® 010/ °¡¿îµ¥ 4ÀÚ¸®/ ¸¶Áö¸·4ÀÚ¸®
        //010È®Á¤Áş°í \d(¼ıÀÚ,Á¤¼ö)
        //{} °ıÈ£¾ÈÀÇ ¼ıÀÚ¸¸Å­ ¾ÕÀÇ ¹®ÀÚ¿­ ¹İº¹
        
        String regExp = "(010)-?\\d{4}-?\\d{4}";
        String data = sc.nextLine();
        
        boolean b4 = data.matches(regExp);  //true, false À¯È¿¼º °Ë»ç
        
        if(b4 == true) {
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀÔ´Ï´Ù.");
        }else {
            System.out.println("¾ç½Ä¿¡ ¸ÂÃç ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä. ¿¹½Ã) 010-xxxx-xxxxÀÔ´Ï´Ù. ");
            
        }
    }
    
    // -------------------------5--------------------------
    public static void isEmail() {
        //@ ¾Õ ¿µ¾î ´ë¼Ò¹®ÀÚ ¼ıÀÚ¸¸
        //@ µÚ ¿µ¾î ´ë¼Ò¹®ÀÚ
        //. µÚ¿¡ ¿µ¾î ´ë¼Ò¹®ÀÚ 
        //{} ¼±Çà¹®ÀÚ°¡ ³ªÅ¸´Â È½¼ö ¶Ç´Â ¹üÀ§..?
        System.out.println("[5] ÀÌ¸ŞÀÏ ÀÔ·Â");
        
        String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        String data = sc.nextLine();
        
        boolean b5 = data.matches(regExp); //true¿Í false À¯È¿¼º °Ë»ç
        
        if (b5 == true) {
            System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
        } else {
            System.out.println("example1006@bit.comÀÇ Çü½ÄÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
        }
        
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("******************************************************");
            System.out.println("[1] ¾ÆÀÌµğ       [2] ºñ¹Ğ¹øÈ£     [3] ÀÌ¸§            [4] ÇÚµåÆù¹øÈ£    [5] ÀÌ¸ŞÀÏ       [0] Á¾·á");
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
                System.out.println("Àß¸øµÈ ¹øÈ£ ÀÔ·Â");
                break;
            }
            
            
            
        }
    }    
    
}