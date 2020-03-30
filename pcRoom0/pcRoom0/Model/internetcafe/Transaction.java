package internetcafe;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import internetcafe.product.Food;
/*
* Ŭ������ : Transaction // �ŷ������� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/

public class Transaction implements Serializable{
	private String transactiodate;
	private String transactiontime;
	private int money;
	
	
	public String getTransactiodate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd��");		
		return sdf.format(cal.getTime());
	}
	public void setTransactiodate(String transactiodate) {
		this.transactiodate = transactiodate;
	}
	public String getTransactiontime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH�� mm�� ss��");
		return sdf.format(cal.getTime());
	}
	public void setTransactiontime(String transactiontime) {
		this.transactiontime = transactiontime;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "�ŷ�����\t���� ��¥: [" + transactiodate + "] ���� �ð�: [" + transactiontime + "] ���� �ݾ�: ["
				+ money+"]";
	}
	
	

}
