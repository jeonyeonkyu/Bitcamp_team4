
import java.util.*;

public class Internet_Cafe {

	public Internet_Cafe() {
	}
	private static final int MAX_ROW_INDEX = 4;
	private static final int MAX_COL_INDEX = 5;
	private HashMap<String, Member> memberlist;
	private List<Nonmember> nonmemberlist;
	private List<Product> foodlist;
	private Customer[][] seat;
	
	public void addMember() {
	}

	public void addNonmember() {
	}

	public void showMain() {
	}

	public void showSeat() {

	}

	public void selectCustomer() {
	}

	public void selectSeat() {
	}

	public void selectMember() {
	}

	public void selectNonMember() {
	}

	public void adminMenu() {
	}

	public HashMap<String, Member> getMemberlist() {
		return memberlist;
	}

	public void setMemberlist(HashMap<String, Member> memberlist) {
		this.memberlist = memberlist;
	}

	public List<Nonmember> getNonmemberlist() {
		return nonmemberlist;
	}

	public void setNonmemberlist(List<Nonmember> nonmemberlist) {
		this.nonmemberlist = nonmemberlist;
	}

	public List<Product> getFoodlist() {
		return foodlist;
	}

	public void setFoodlist(List<Product> foodlist) {
		this.foodlist = foodlist;
	}

	public Customer[][] getSeat() {
		return seat;
	}

	public void setSeat(Customer[][] seat) {
		this.seat = seat;
	}

	public static int getMaxRowIndex() {
		return MAX_ROW_INDEX;
	}

	public static int getMaxColIndex() {
		return MAX_COL_INDEX;
	}

	@Override
	public String toString() {
		return "Internet_Cafe [memberlist=" + memberlist + ", nonmemberlist=" + nonmemberlist + ", foodlist=" + foodlist
				+ ", seat=" + Arrays.toString(seat) + "]";
	}

}