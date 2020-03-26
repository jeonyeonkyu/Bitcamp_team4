package internetcafe.product;

import java.util.*;

/*
* 클래스명 : Member // (회원가입한 손님 ) = 멤버에 대한 클래스
* 
* 버전 정보 v.1.0
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
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