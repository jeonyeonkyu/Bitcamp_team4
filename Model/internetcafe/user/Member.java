package internetcafe.product;

/*
* 클래스명 : Member // (회원가입한 손님 ) = 멤버에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 26
* 
* 작업자 : 전연규
*/
public class Member extends Customer {

	public Member() {

	}
	public Member(String id, String pwd, String name,int money, int totalplaytime) {
		this.setId(id);
		this.setPwd(pwd);
		this.setName(name);
		this.setMoney(money);
		this.setTotalplaytime(totalplaytime);
	}
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", money=" + getMoney() + ", totalPlaytime=" + getTotalplaytime() + ", id="
				+ getId() + ", pwd=" + getPwd() + "]";
	}

}