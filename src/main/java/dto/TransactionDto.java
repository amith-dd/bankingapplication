package dto;

public class TransactionDto {
	
	private int transactionId;
	private TransactionType transactionType;
	private double transactionAmount;
	private String transactionStatus;
	private int accountNumber;
	private int transactionAccountNumber;
	private String transactionDate;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getTransactionAccountNumber() {
		return transactionAccountNumber;
	}
	public void setTransactionAccountNumber(int transactionAccountNumber) {
		this.transactionAccountNumber = transactionAccountNumber;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public TransactionDto(int transactionId, TransactionType transactionType, double transactionAmount,
			String transactionStatus, int accountNumber, int transactionAccountNumber, String transactionDate) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionStatus = transactionStatus;
		this.accountNumber = accountNumber;
		this.transactionAccountNumber = transactionAccountNumber;
		this.transactionDate = transactionDate;
	}
	
	
	
	
}
