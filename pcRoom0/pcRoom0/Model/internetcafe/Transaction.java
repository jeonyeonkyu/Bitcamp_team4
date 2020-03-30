package internetcafe;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import internetcafe.product.Food;
/*
* 클래스명 : Transaction // 거래내역에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
*/

public class Transaction implements Serializable{
	private String transactiodate;
	private String transactiontime;
	private int money;
	
	
	public String getTransactiodate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");		
		return sdf.format(cal.getTime());
	}
	public void setTransactiodate(String transactiodate) {
		this.transactiodate = transactiodate;
	}
	public String getTransactiontime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH시 mm분 ss초");
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
		return "거래내역\t결제 날짜: [" + transactiodate + "] 결제 시간: [" + transactiontime + "] 결제 금액: ["
				+ money+"]";
	}
	
	

}
