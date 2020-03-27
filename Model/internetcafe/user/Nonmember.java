package internetcafe.user;

/*
* 클래스명 : Nonmember // 비회원에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 26
* 
* 작업자 : 전연규
*/
public class Nonmember extends Customer {

	public Nonmember() {
	}

	private String cardnum;

	public void cardNumber() {
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

}