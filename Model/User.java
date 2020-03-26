
import java.util.*;

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