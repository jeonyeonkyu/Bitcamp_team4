package internetcafe.user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import internetcafe.InternetCafe;
import internetcafe.Transaction;
import internetcafe.product.ChargedTime;
import internetcafe.product.Food;
/*
* 클래스명 : Administrator //관리자에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 오주형
*/
public class Administrator extends User {
	private HashMap<String,Food> foodlist;
	private ChargedTime[] timelist = {new ChargedTime(1, 1000), new ChargedTime(3,2700),new ChargedTime(5,4000),new ChargedTime(10,7500)};
	


	public HashMap<String, Food> getFoodlist() {
		return foodlist;
	}

	public void setFoodlist(HashMap<String, Food> foodlist) {
		this.foodlist = foodlist;
	}

	public ChargedTime[] getTimelist() {
		return timelist;
	}

	public void setTimelist(ChargedTime[] timelist) {
		this.timelist = timelist;
	}

	public Administrator(String id, String pwd) {
		foodlist = new HashMap<String, Food>();
		this.setId(id);
		this.setPwd(pwd);		
	}

	public void addProduct(Scanner sc) { // 음식 이름, 가격 입력받고 food 만들어서 foodlist에 추가 그리고 저장
		System.out.println("추가할 음식 이름을 입력하세요.");
		String name = sc.nextLine();
		int price = 0;
		while (true) {
			try {
				System.out.println("음식의 가격을 입력하세요.");
				price = Integer.parseInt(sc.nextLine());
				if (price < 0) {
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("숫자를 입력해주세요.");
				System.out.println();
			}
		}	
		Food food = new Food(name, price);		
		this.foodlist.put(name, food);		
		saveFoodlist();
		showFoodList();
	}   
	
	
	public boolean showFoodList() {
		File foodfile = new File("FoodList.txt");
		if(foodfile.exists() && foodlist.size() > 0) {
		try {
			FileInputStream fis = new FileInputStream(foodfile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			
			foodlist = (HashMap)ois.readObject();
			Set<String> keyset = foodlist.keySet(); 
			System.out.println("******음식 리스트*******");
			for(String key : keyset) {
				String pname = foodlist.get(key).getpName();
				int pprice = foodlist.get(key).getpPrice();
				System.out.printf("음식 이름: [%s]\t 음식 가격: [%d]\n",pname,pprice);
			}
			ois.close();
			bis.close();
			fis.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}return true;
	}else {
		System.out.println("현재 음식 목록이 없습니다.");
		return false;
	}
		
	}
		
		

	public void delProduct(Scanner sc) { //음식 이름이 일치할 시 삭제 그리고 저장
		if(showFoodList()) {
		System.out.println();
		while(true) {
		System.out.println("삭제할 음식 이름을 입력하세요.");
		
		
		String name = sc.nextLine();
		if(!foodlist.containsKey(name)) {
			System.out.println("음식이 존재하지 않습니다.");
		} else {
			foodlist.remove(name);
			System.out.println("삭제했습니다.");
			saveFoodlist();
			break;
		}			
		}
		
		}
	}
	public void saveFoodlist() { // txt파일에 foodlist 저장
		
		File foodfile = new File("FoodList.txt");
		try {
			FileOutputStream fos = new FileOutputStream(foodfile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			oos.writeObject(foodlist);
			oos.close();
			bos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("저장이 실패되었습니다.");
			System.out.println(e.getMessage());
			
		}
		System.out.println("저장되었습니다.");
	}
	
	public String changeTimeDisplay(Scanner sc) {
	
		System.out.println("현재 시간당 요금");
		for(ChargedTime ch : timelist) {
			System.out.printf("%d시간당 요금 : [%d]\n",ch.getPlaytime(), ch.getpPrice());
		}
		System.out.println();
		System.out.println("1. 1시간 요금 변경");
		System.out.println("2. 3시간 요금 변경");
		System.out.println("3. 5시간 요금 변경");
		System.out.println("4. 10시간 요금 변경");
		System.out.println("0. 나가기");
			
		String menu = sc.nextLine();
		
		return menu;		
	}
	public void selectChangeTime(Scanner sc) {
		
		int price = 0;
		loop_1 : while(true) {
			String menunum = changeTimeDisplay(sc);
		switch (menunum) {
		case "1" :
			System.out.println("원하시는 요금을 입력해주세요.");
			price = Integer.parseInt(sc.nextLine());
			timelist[0].setpPrice(price);
			break ;
		case "2" :
			System.out.println("원하시는 요금을 입력해주세요.");
			price = Integer.parseInt(sc.nextLine());
			timelist[1].setpPrice(price);
			break;
		case "3" :
			System.out.println("원하시는 요금을 입력해주세요.");
			price = Integer.parseInt(sc.nextLine());
			timelist[2].setpPrice(price);
			break;
		case "4" :
			System.out.println("원하시는 요금을 입력해주세요.");
			price = Integer.parseInt(sc.nextLine());
			timelist[3].setpPrice(price);
			break;
		case "0" :
			break loop_1;
		default : 
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("메뉴는 0~4번까지 입니다.");
		}
		}
	}

	public void showTotalMoney(InternetCafe ic) {
		int alltotalmoney = 0; //총 매출
		HashMap<String, Member> memberlist = ic.getmemberList(); //회원리스트 호출
		Set<String> key = memberlist.keySet(); //멤버 id(key)를 set에 저장
		for(String userId : key) { 
			Member member = memberlist.get(userId); //id에 대한 value가 Member 객체니 이렇게 저장
			List<Transaction> memtranlist = member.getTransactions(); //그 멤버의 transaction list를 저장
			for(Transaction tran : memtranlist) {
				alltotalmoney += tran.getMoney(); //transaction list에 있는 transaction들의 각각의 돈을 총 매출 변수에 합계하며 저장
				System.out.println(tran.toString());
			}
		}
		/*
		 * List<Nonmember> nonmemlist = ic.getNonmemberList(); //비회원리스트 호출 for(Nonmember
		 * nonmem : nonmemlist) { List<Transaction> nonmemtranlist =
		 * nonmem.getTransactions(); // 비회원의 transaction list를 저장 for(Transaction tran :
		 * nonmemtranlist) { alltotalmoney += tran.getMoney(); //transaction list에 있는
		 * transaction들의 각각의 돈을 총 매출 변수에 합계하며 저장 } }
		 */
		//매출 내역들을 보이게 하자 -> 따로 추가
		System.out.println();
		System.out.println("총 매출 : " + alltotalmoney);
	}

	public void showMemberList() { //유저리스트 보여주기
		//IO로 해결
		File filename = new File("UserList.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		
		if(filename.exists()) {
		try {
			
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			System.out.println("******유저리스트*******");
			System.out.println();
			HashMap<String, Member> memberlist = (HashMap)ois.readObject();
			Set<String> keyset = memberlist.keySet();
			for(String key : keyset) {
				String id = memberlist.get(key).getId();
				String pwd = memberlist.get(key).getPwd();
				String name = memberlist.get(key).getName();
				String phonenumber = memberlist.get(key).getPhonenumber();
				System.out.printf("ID: [%s]\t PWD: [%s]\t 이름: [%s]\t 휴대폰 번호: [%s]\n",id,pwd,name,phonenumber);
			}		
		} catch (Exception e) {
			
			System.out.println("잘못 입력하셨습니다.");
			e.printStackTrace();
		}finally {
			try {
				ois.close();
				bis.close();
				fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
				
				
		}
		
	
	}else {
		System.out.println("회원 목록이 없습니다.");
	}
	

}
}