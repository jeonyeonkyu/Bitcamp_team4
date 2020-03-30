package internetcafe.product;



import java.util.*;
/*
* 클래스명 : ChargedTime // 시간충전을 위한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
*/
public class ChargedTime extends Product {

    public ChargedTime(int playtime, int money) {
    	this.playtime = playtime;
    	this.setpPrice(money);
    }
    private int playtime;
    
	public int getPlaytime() {
		return playtime;
	}
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}


}