package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.AccountDto;
import dto.AccountStatus;
import dto.AccountType;
import dto.TransactionDto;
import dto.TransactionType;
import dto.UserDto;

public class Dao {

	Connection con;

	// get connection
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql:///bankingdata", "root", "root");
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// get count of managers
	public int getNumOfManagers() {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select count(*) from user where userrole = 'manager'");
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	// get count of users
		public int getNumOfUsers() {
			Connection connection = getConnection();
			try {
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select count(*) from user");
				if (rs.next()) {
					return rs.getInt(1)+1;
				} else {
					return 0;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

	// save user (manager/customer)
	public int saveUser(UserDto user) {
		try {

			Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
			pst.setInt(1, user.getUserId());
			pst.setString(2, user.getUserName());
			pst.setInt(3, user.getUserAge());
			pst.setString(4, user.getUserEmail());
			pst.setLong(5, user.getUserContact());
			pst.setString(6, user.getUserPassword());
			pst.setString(7, user.getUserRole());
			pst.setInt(8, user.getUserAccountNumber());

			if (user.getUserRole().equals("manager")) {
				int managercount = getNumOfManagers();
				if (managercount == 0) {
					return pst.executeUpdate(); // manager does not exist, new manager will be saved
				} else {
					return 0; // manager already exist
				}
			} else {
				return pst.executeUpdate(); // user saved
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getNextUserId() {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select count(*) from user");
			if(rs.next()) {
				return rs.getInt(1)+1;
			}else {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	// login user (manager/customer)
	public UserDto loginUser(String email, String password) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select * from user where useremail=? and userpassword=? ");
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new UserDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// create account
	public int createAccount(AccountDto account) {
		Connection con = getConnection();
		int accounttype = 0;
		if (account.getAccountType() == AccountType.SAVING) {
			accounttype = 1;
		}
		try {
			PreparedStatement pst = con.prepareStatement("insert into account values(?,?,?,?)");
			pst.setInt(1, account.getAccountId());
			pst.setDouble(2, account.getAccountBalance());
			pst.setInt(3, accounttype);
			pst.setInt(4, 1);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
	// generate accountids
	public int getAccountid() {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select count(*) from account");
			if(rs.next()) {
				return rs.getInt(1)+1;
			}else {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// get account by account id/account num
	public AccountDto finndAccountByAccountId(int accountid) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select * from account where accountid=?");
			pst.setInt(1, accountid);
			ResultSet rs = pst.executeQuery();
			AccountType actype = null;
			AccountStatus status = null;
			if (rs.next()) {
				if (rs.getInt(3) == 0) {
					actype = AccountType.CURRENT;
				}
				if (rs.getInt(3) == 1) {
					actype = AccountType.SAVING;
				}
				if (rs.getInt(4) == 0) {
					status = AccountStatus.ACTIVE;
				}
				if (rs.getInt(4) == 1) {
					status = AccountStatus.BLOCKED;
				}
				return new AccountDto(rs.getInt(1), rs.getDouble(2), actype, status);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// update account
	public int updateAccount(AccountDto account) {
		Connection con = getConnection();
		int accounttype = 0;
		int accountstatus = 0;
		if (account.getAccountType() == AccountType.SAVING) {
			accounttype = 1;
		}
		if (account.getAccountStatus() == AccountStatus.BLOCKED) {
			accountstatus = 1;
		}
		try {
			PreparedStatement pst = con.prepareStatement(
					"update account set accountbalance=?, accounttype=?,accountstatus=? where accountid=?");
			pst.setInt(4, account.getAccountId());
			pst.setDouble(1, 500);
			pst.setInt(2, accounttype);
			pst.setInt(3, accountstatus);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	// find user by account number
	public UserDto findUserByAccountId(int accountid) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select * from user where accountid=?");
			pst.setInt(1, accountid);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new UserDto(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),
						rs.getString(6), rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// show all blocked accounts
	public List<AccountDto> getAllBloackedAcounts() {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select * from account where accountstatus=1");
			ResultSet rs = pst.executeQuery();
			AccountType actype = null;
			AccountStatus status = null;
			List<AccountDto> blockedaccounts = new ArrayList<AccountDto>();
			while (rs.next()) {
				if (rs.getInt(3) == 0) {
					actype = AccountType.CURRENT;
				}
				if (rs.getInt(3) == 1) {
					actype = AccountType.SAVING;
				}
				if (rs.getInt(4) == 0) {
					status = AccountStatus.ACTIVE;
				}
				if (rs.getInt(4) == 1) {
					status = AccountStatus.BLOCKED;
				}
				blockedaccounts.add(new AccountDto(rs.getInt(1), rs.getDouble(2), actype, status));
			}
			return blockedaccounts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// approve account creation request
	public int aproveAccountCreation(int accountid) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update account set accountstatus = 0 where accountid = ?");
			pst.setInt(1, accountid);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	// create/save transaction
	public int createTransaction(TransactionDto transaction) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?)");
			pst.setInt(1, transaction.getTransactionId());
			int transactiontype = transaction.getTransactionType() == TransactionType.CREDIT ? 0 : 1;

			pst.setInt(2, transactiontype);
			pst.setDouble(3, transaction.getTransactionAmount());
			pst.setString(4, transaction.getTransactionStatus());
			pst.setInt(5, transaction.getAccountNumber());
			pst.setInt(6, transaction.getTransactionAccountNumber());
			pst.setString(7, transaction.getTransactionDate());
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// get transacrtion id
	public int getTransactionCount() {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rSet = statement.executeQuery("select count(*) from transaction");
			if (rSet.next()) {
				return rSet.getInt(1) + 1;
			} else {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// add money to account (return 2 --> sucessfull transaction, fail otherwise)
	public int addmoneyToAccount(int accountid, double amount) {
		AccountDto account = finndAccountByAccountId(accountid);
		int updatecount = 0;
		if (account != null) {

			account.setAccountBalance(account.getAccountBalance() + amount);
			updatecount += updateAccount(account);

			LocalDate date = LocalDate.now();
			String stringdate = String.valueOf(date);

			TransactionDto transactionDto = new TransactionDto(getTransactionCount(), TransactionType.CREDIT, amount,
					"self", accountid, 0, stringdate);
			updatecount += createTransaction(transactionDto);

			return updatecount;
		}
		return 0;
	}

	// withdraw money from account (return 2 --> sucessfull transaction, fail otherwise)
	public int withdrawmoneyFromAccount(int accountid, double amount) {
		AccountDto account = finndAccountByAccountId(accountid);
		int updatecount = 0;
		if (account != null) {
			if (account.getAccountBalance() > amount) {
				account.setAccountBalance(account.getAccountBalance() - amount);
				updatecount += updateAccount(account);

				LocalDate date = LocalDate.now();
				String stringdate = String.valueOf(date);

				TransactionDto transactionDto = new TransactionDto(getTransactionCount(), TransactionType.DEBIT, amount,
						"self", accountid, 0, stringdate);
				updatecount += createTransaction(transactionDto);

				return updatecount;
			}
			return 0;
		}
		return 0;
	}

	// send money to account (return 4 --> sucessfull transaction, fail otherwise)
	public int sendmoneyToAccount(int accountid, double amount, int transactionaccountid) {
		AccountDto from = finndAccountByAccountId(accountid);
		AccountDto to = finndAccountByAccountId(transactionaccountid);

		int updatecount = 0;
		if (from != null && to != null) {
			if (from.getAccountBalance() > amount) {
				from.setAccountBalance(from.getAccountBalance() - amount);
				to.setAccountBalance(to.getAccountBalance() + amount);
				updatecount += updateAccount(from) + updateAccount(to);

				LocalDate date = LocalDate.now();
				String stringdate = String.valueOf(date);

				TransactionDto debittransaction = new TransactionDto(getTransactionCount(), TransactionType.DEBIT,
						amount, "debit", accountid, transactionaccountid, stringdate);

				TransactionDto credittransaction = new TransactionDto(getTransactionCount(), TransactionType.CREDIT,
						amount, "credit", accountid, transactionaccountid, stringdate);

				updatecount += createTransaction(debittransaction);

				updatecount += createTransaction(credittransaction);

				return updatecount;
			}
			return 0;
		}
		return 0;
	}

	// block acccount
	public int updateAccountType(int accountid) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("update account set accountstatus = 1 where accountid = ?");
			pst.setInt(1, accountid);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// delete account by account number
	public int deleteAccount(String email, String pssword, int accountid) {
		try {
			UserDto userDto = loginUser(email, pssword);

			if (userDto != null) {
				Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement("delete account where accountid = ?");
				pst.setInt(1, accountid);
				return pst.executeUpdate();
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	// view account balance by account number
	public double getAccountBalance(int accountid) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select accountbalance from account where accountid = ?");
			pst.setInt(1, accountid);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getDouble(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	// view all transactions
	public List<TransactionDto> getTransactionsByAccountid(int accountid) {
		Connection con = getConnection();
		try {
			PreparedStatement pst = con.prepareStatement("select * from transactions where accountid = ?");
			pst.setInt(1, accountid);
			List<TransactionDto> transactions = new ArrayList<TransactionDto>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				TransactionType transactiontype = rs.getInt(2) == 0 ? TransactionType.CREDIT : TransactionType.DEBIT;
				transactions.add(new TransactionDto(rs.getInt(1), transactiontype, rs.getDouble(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7)));
			}
			return transactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// update password (optional)

}
