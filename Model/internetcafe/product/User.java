package internetcafe.product;

/*
* Ŭ������ : User // ������ ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 26
* 
* �۾��� : ������
*/
public abstract class User {

	public User() {
	}

	private String id;

	private String pwd;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + "]";
	}

}