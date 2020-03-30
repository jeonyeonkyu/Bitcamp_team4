package internetcafe.product;



import java.io.Serializable;
import java.util.*;
/*
* 클래스명 : Food // 음식 구매를 위한 클래스
* 
* 버전 정보 v.1.1
* 
* 마지막 업데이트 날짜 : 2020 - 03 - 25
* 
* 작업자 : 엄지희
*/
public class Food extends Product implements Serializable{
	private int number;

    public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "음식이름 : " +getpName() + "\t 수량 : " + number ;
	}

	public Food(String pname, int pprice) {
    	this.setpName(pname);
    	this.setpPrice(pprice);
    	
    }

}