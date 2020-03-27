package internetcafe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/*
* 클래스명 : Transaction // 거래내역에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 26
* 
* 작업자 : 전연규
*/

public class Transaction {
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

}
