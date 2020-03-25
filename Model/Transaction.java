
import java.util.*;

public class Transaction {

    public Transaction() {
    }

    private String transactionDate;

    private String transactionTime;

    private int totalmoney;

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public int getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}

	@Override
	public String toString() {
		return "Transaction [transactionDate=" + transactionDate + ", transactionTime=" + transactionTime
				+ ", totalmoney=" + totalmoney + "]";
	}


}