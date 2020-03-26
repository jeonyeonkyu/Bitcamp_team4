
import java.util.HashMap;

public class Product {

	public Product() {
	}

	private int pPrice;

	private String pName;

	private HashMap<String, Food> pFood;

	private HashMap<String, Drink> pDrink;

	private HashMap<String, ChargedTime> pTime;

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

	public HashMap<String, Food> getpFood() {
		return pFood;
	}

	public void setpFood(HashMap<String, Food> pFood) {
		this.pFood = pFood;
	}

	public HashMap<String, Drink> getpDrink() {
		return pDrink;
	}

	public void setpDrink(HashMap<String, Drink> pDrink) {
		this.pDrink = pDrink;
	}

	public HashMap<String, ChargedTime> getpTime() {
		return pTime;
	}

	public void setpTime(HashMap<String, ChargedTime> pTime) {
		this.pTime = pTime;
	}

	@Override
	public String toString() {
		return "Product [pPrice=" + pPrice + ", pName=" + pName + ", pFood=" + pFood + ", pDrink=" + pDrink + ", pTime="
				+ pTime + "]";
	}

}