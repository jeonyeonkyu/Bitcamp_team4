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
public class Product implements Serializable{

	public Product() {
		
	}

	private int pPrice;

	private String pName;

	

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

	

	

}