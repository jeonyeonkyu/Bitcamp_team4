package internetcafe.product;



import java.util.*;
/*
* Ŭ������ : ChargedTime // �ð������� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class ChargedTime extends Product {

    public ChargedTime(int playtime, int money) {
    	this.playtime = playtime;
    	this.setpPrice(money);
    }
    private int playtime;
    
	public int getPlaytime() {
		return playtime;
	}
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}


}