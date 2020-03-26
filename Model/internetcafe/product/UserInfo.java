package internetcafe.product;

import java.util.HashMap;
import java.util.Scanner;

/*
* Å¬·¡½º¸í : UserInfo // À¯Àúµ¥ÀÌÅÍ ÀÔ·ÂÇÒ ¶§ Á¤±ÔÇ¥Çö½Ä»ç¿ëÀ» À§ÇÑ Å¬·¡½º
* 
* ¹öÀü Á¤º¸ v.1.1
* 
* ¸¶Áö¸· ¾÷µ¥ÀÌÆ® ³¯Â¥ : 2020 - 03 - 26
* 
* ÀÛ¾÷ÀÚ : Àü¿¬±Ô
* id,pwd,name ÇÔ¼ö¸¦ isId,isPwd,isName À¸·Î ¹Ù²Ù°í ½ºÆ®¸µ °ªÀ» ¸®ÅÏÇÏµµ·Ï º¯°æ
*/
public class UserInfo {
	// static ¼±¾ğ
	static String data = "";
	static String regExp = "";
	static Scanner sc = new Scanner(System.in);

	// -------------------------1--------------------------

	public static String isId(Scanner sc, HashMap<String, Member> memberList) {
		String regExpId = "^[a-zA-Z]{1}[a-zA-Z0-9]{5,11}$";
		while (true) {
			System.out.println("[1] ID ÀÔ·Â  ");
			String id = sc.nextLine();
			boolean b1 = id.matches(regExpId);
			if (b1) {
				if (memberList.containsKey(id)) {
					System.out.println("Áßº¹µÈ ID ÀÔ´Ï´Ù.");
					continue;
				}
				System.out.println("¿Ã¹Ù¸¥ IdÇü½ÄÀÔ´Ï´Ù.");
				return id;
			} else {
				System.out.println("IDÀÇ Çü½ÄÀº 6~12ÀÚ »çÀÌ¿¡ ¿µ¾î ´ë,¼Ò¹®ÀÚ,¼ıÀÚ°¡ ¿Ã ¼ö ÀÖÀ¸¸ç Ã¹¹®ÀÚÀÇ °æ¿ì ¿µ¹®ÀÚ Áß ÇÏ³ª¸¦ ÀÔ·ÂÇÏ¼Å¾ßÇÕ´Ï´Ù.");
			}
		}

	}

	// -------------------------2--------------------------

	public static String isPwd() {
		regExp = "^[A-Z0-9_@./#&+-?~]*.{6,12}$";
		while (true) {
			System.out.println("[2] ºñ¹Ğ¹øÈ£ ÀÔ·Â:");
			data = sc.nextLine();
			boolean b2 = data.matches(regExp);
			if (b2) {
				System.out.println("¿Ã¹Ù¸¥ ºñ¹Ğ¹øÈ£ Çü½ÄÀÔ´Ï´Ù.");
				return data;
			} else {
				System.out.println("ºñ¹Ğ¹øÈ£ Çü½ÄÀº 6~12ÀÚ »çÀÌ¿¡ ¿µ¾î´ë¹®ÀÚ, Æ¯¼ö¹®ÀÚµµ µé¾î¿Ã ¼ö ÀÖ½À´Ï´Ù.");
			}
		}
	}

	// -------------------------3--------------------------

	public static String isName() {
		regExp = "^[¤¡-¤¾°¡-ÆR]*$";
		while (true) {
			System.out.println("[3] ÀÌ¸§ ÀÔ·Â : ");
			data = sc.nextLine();
			boolean b3 = data.matches(regExp);
			if (b3) {
				System.out.println("¿Ã¹Ù¸¥ ÀÌ¸§Çü½ÄÀÔ´Ï´Ù.");
				return data;
			} else {
				System.out.println("ÀÌ¸§ Çü½Ä ÇÑ±Û¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
			}
		}
	}
	// -------------------------4--------------------------

	public static void isPhone() {
		System.out.println("[4] ÈŞ´ë¹øÈ£ ÀÔ·Â: ");
		// Æù ¾ÕÀÚ¸® 010/ °¡¿îµ¥ 4ÀÚ¸®/ ¸¶Áö¸·4ÀÚ¸®
		// 010È®Á¤Áş°í \d(¼ıÀÚ,Á¤¼ö)
		// {} °ıÈ£¾ÈÀÇ ¼ıÀÚ¸¸Å­ ¾ÕÀÇ ¹®ÀÚ¿­ ¹İº¹

		String regExp = "(010)-?\\d{4}-?\\d{4}";
		String data = sc.nextLine();

		boolean b4 = data.matches(regExp); // true, false À¯È¿¼º °Ë»ç

		if (b4 == true) {
			System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀÔ´Ï´Ù.");
		} else {
			System.out.println("¾ç½Ä¿¡ ¸ÂÃç ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä. ¿¹½Ã) 010-xxxx-xxxxÀÔ´Ï´Ù. ");

		}
	}

	// -------------------------5--------------------------
	public static void isEmail() {
		// @ ¾Õ ¿µ¾î ´ë¼Ò¹®ÀÚ ¼ıÀÚ¸¸
		// @ µÚ ¿µ¾î ´ë¼Ò¹®ÀÚ
		// . µÚ¿¡ ¿µ¾î ´ë¼Ò¹®ÀÚ
		// {} ¼±Çà¹®ÀÚ°¡ ³ªÅ¸´Â È½¼ö ¶Ç´Â ¹üÀ§..?
		System.out.println("[5] ÀÌ¸ŞÀÏ ÀÔ·Â");

		String regExp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		String data = sc.nextLine();

		boolean b5 = data.matches(regExp); // true¿Í false À¯È¿¼º °Ë»ç

		if (b5 == true) {
			System.out.println("¿Ã¹Ù¸¥ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
		} else {
			System.out.println("example1006@bit.comÀÇ Çü½ÄÀ¸·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}

	}

}