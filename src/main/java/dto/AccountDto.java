package dto;

public class AccountDto {
	
	private int accountId;
	private double accountBalance;
	private AccountType accountType;
	private AccountStatus accountStatus;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public AccountDto(int accountId, double accountBalance, AccountType accountType, AccountStatus accountStatus) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
	}
	
	
	
	
	
}
