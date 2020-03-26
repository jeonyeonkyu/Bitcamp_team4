package internetcafe.product;

/*
* Ŭ������ : Member // (ȸ�������� �մ� ) = ����� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 26
* 
* �۾��� : ������
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