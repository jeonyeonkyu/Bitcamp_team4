package internetcafe.product;

import java.io.Serializable;
import java.util.*;

/*
* Ŭ������ : Food // ���� ���Ÿ� ���� Ŭ����
* 
* ���� ���� v.1.1
* 
* ������ ������Ʈ ��¥ : 2020 - 03 - 25
* 
* �۾��� : ������
*/
public class Food extends Product implements Serializable {

	public Food(String pname, int pprice) {
		this.setpName(pname);
		this.setpPrice(pprice);

	}

}