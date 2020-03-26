
import java.util.*;

public class Member extends Customer {

    public Member() {
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