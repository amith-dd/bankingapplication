package dto;

public class UserDto {
	
	private int userId;
	private String userName;
	private int userAge;
	private String userEmail;
	private long userContact;
	private String userPassword;
	private String userRole;
	 /* 0 will be the account number if 
	account is not created for that user*/
	private int userAccountNumber; 
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public long getUserContact() {
		return userContact;
	}
	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public int getUserAccountNumber() {
		return userAccountNumber;
	}
	public void setUserAccountNumber(int userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}
	public UserDto(int userId, String userName, int userAge, String userEmail, long userContact, String userPassword,
			String userRole, int userAccountNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
		this.userEmail = userEmail;
		this.userContact = userContact;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userAccountNumber = userAccountNumber;
	}
	
	
}
