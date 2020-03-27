package internetcafe.user;

import java.util.*;

/*
* 클래스명 : User // 유저에 대한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
*/
public abstract class User {

	public User() {
	}

	private String id;

	private String pwd;

	private boolean log;

	public void logCheck() {
	}

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

	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", log=" + log + "]";
	}

}