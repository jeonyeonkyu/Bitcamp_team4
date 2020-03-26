package internetcafe.product;

import java.util.*;

/*
* Ŭ������ : Member // (ȸ�������� �մ� ) = ����� ���� Ŭ����
* 
* ���� ���� v.1.0
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class Member extends Customer {

	public Member() {
		
	}
	public Member(String id, String pwd, String name) {
		super.setId(id);
		super.setPwd(pwd);
		this.setName(name);

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
		return "Member [name=" + name + "]";
	}

}