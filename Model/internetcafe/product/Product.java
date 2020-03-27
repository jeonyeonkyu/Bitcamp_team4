package internetcafe.product;

import java.io.Serializable;
import java.util.HashMap;

/*
* 클래스명 : Product // 상품목록에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
*/
public class Product implements Serializable {

	public Product() {

	}

	private int pPrice;

	private String pName;

	private HashMap<String, Food> FoodList;

	private HashMap<String, ChargedTime> TimeList;

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public HashMap<String, Food> getFoodList() {
		return FoodList;
	}

	public void setFoodList(HashMap<String, Food> foodList) {
		FoodList = foodList;
	}

	public HashMap<String, ChargedTime> getpTime() {
		return TimeList;
	}

	public void setpTime(HashMap<String, ChargedTime> pTime) {
		this.TimeList = pTime;
	}

	@Override
	public String toString() {
		return "Product [pPrice=" + pPrice + ", pName=" + pName + ", FoodList=" + FoodList + ", TimeList=" + TimeList
				+ "]";
	}

}