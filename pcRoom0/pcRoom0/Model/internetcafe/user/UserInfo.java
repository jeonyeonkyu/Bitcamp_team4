package internetcafe.user;
 
import java.util.Scanner;

import internetcafe.InternetCafe;
/*
* Å¬·¡½º¸í : UserInfo // À¯Àúµ¥ÀÌÅÍ ÀÔ·ÂÇÒ ¶§ Á¤±ÔÇ¥Çö½Ä»ç¿ëÀ» À§ÇÑ Å¬·¡½º
* 
* ¹öÀü Á¤º¸ v.1.1
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
    
    public static String id(InternetCafe ic) {
		
		regExp = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
		while (true) {
			System.out.println("[1] ID ÀÔ·Â  ");
			data = sc.nextLine();
			boolean b1 = data.matches(regExp);
			if (b1) {
				if (ic.getmemberList().containsKey(data)) {
					System.out.println("Áßº¹µÈ ID ÀÔ´Ï´Ù.");
					continue;
				}
				
				break;
			} else {
				System.out.println("IDÀÇ Çü½ÄÀº 6~12ÀÚ »çÀÌ¿¡ ¿µ¾î ´ë,¼Ò¹®ÀÚ,¼ıÀÚ°¡ ¿Ã ¼ö ÀÖÀ¸¸ç Ã¹¹®ÀÚÀÇ °æ¿ì ¿µ¹®ÀÚ Áß ÇÏ³ª¸¦ ÀÔ·ÂÇÏ¼Å¾ßÇÕ´Ï´Ù.");
			}
		}
		return data;
    }

    // -------------------------2--------------------------
    
    public static String pwd() {
		String regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
		while (true) {
			System.out.println("[2] ºñ¹Ğ¹øÈ£ ÀÔ·Â:");
			String pwd = sc.nextLine();
			boolean b21 = pwd.matches(regExp);
			if (b21) {
				while (true) {
					System.out.println("¿Ã¹Ù¸¥ ºñ¹Ğ¹øÈ£ Çü½ÄÀÔ´Ï´Ù.");
					System.out.println("ºñ¹Ğ¹øÈ£ È®ÀÎ >> ºñ¹Ğ¹øÈ£¸¦ ÇÑ¹ø ´õ ÀÔ·ÂÇØÁÖ¼¼¿ä");
					String pwd2 = sc.nextLine();
					boolean b22 = pwd2.matches(regExp);
					if (b22) {
						if (pwd.equals(pwd2)) {
							System.out.println("ºñ¹Ğ¹øÈ£°¡ ÀÏÄ¡ÇÕ´Ï´Ù");
							return pwd;
						} else {
							System.out.println("¡Øºñ¹Ğ¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù. ºñ¹Ğ¹øÈ£¸¦ Ã³À½ºÎÅÍ ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä");
							break;
						}
					}
				}
			} else {
				System.out.println("ºñ¹Ğ¹øÈ£ Çü½ÄÀº 6~12ÀÚ »çÀÌ¿¡ ¿µ¾î´ë¹®ÀÚ, Æ¯¼ö¹®ÀÚµµ µé¾î¿Ã ¼ö ÀÖ½À´Ï´Ù.");
			}
		}
	}

    
    
    // -------------------------3--------------------------
    
    public static String name() {
    	while(true) {
        System.out.println("[3] ÀÌ¸§ ÀÔ·Â : ");
        regExp = "^[¤¡-¤¾°¡-ÆR]*$";
        data = sc.nextLine();
        boolean b3 = data.matches(regExp);
        if(b3 == true) {
           break;
        }
        else {
            System.out.println("ÀÌ¸§Àº ÇÑ±Û·Î ÀÔ·ÂÇÏ¼Å¾ß ÇÕ´Ï´Ù.");
        }
    	}
    	return data;
    }
    // -------------------------4--------------------------
    
    public static String phone() {
    	while(true) {
        System.out.println("[4] ÈŞ´ë¹øÈ£ ÀÔ·Â: ");
        //Æù ¾ÕÀÚ¸® 010/ °¡¿îµ¥ 4ÀÚ¸®/ ¸¶Áö¸·4ÀÚ¸®
        //010È®Á¤Áş°í \d(¼ıÀÚ,Á¤¼ö)
        //{} °ıÈ£¾ÈÀÇ ¼ıÀÚ¸¸Å­ ¾ÕÀÇ ¹®ÀÚ¿­ ¹İº¹
        
        regExp = "(010)-?\\d{4}-?\\d{4}";
        data = sc.nextLine();
        
        boolean b4 = data.matches(regExp);  //true, false À¯È¿¼º °Ë»ç
        
        if(b4 == true) {
           break;
        }else {
            System.out.println("ÈŞ´ëÆù ¹øÈ£´Â 010-xxxx-xxxxÀÇ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼Å¾ß ÇÕ´Ï´Ù. ");
            
        }
    	}
    	return data;
    }
    
    // -------------------------5--------------------------
   
 
            
           
        
    
    
}