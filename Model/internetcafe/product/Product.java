package internetcafe.product;

import java.io.Serializable;
import java.util.HashMap;

/*
* Ŭ������ : Product // ��ǰ��Ͽ� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
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