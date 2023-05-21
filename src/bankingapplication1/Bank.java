package bankingapplication1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {

    private String name;

    public Bank(String name) {
        this.name = name;
    }

    public void listAccounts() { //Query database
        Connection con = BankConnection.connect();
        Statement statement;
        try {
            statement = con.createStatement();
            String sql = "select * from account";
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                System.out.println(results.getString(1) + " " + results.getString(2) + " " + results.getString(3));
            }
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void openAccount(Account account) { // Insert database
        Connection con = BankConnection.connect();
        String sql = "insert into account(accID,accName,accBalance) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeAccount(int number) { // Delete database
        Connection con = BankConnection.connect();
        String sql = "delete from account where accID = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void depositMoney(Account account, double amount) { // Update database
        account.deposit(amount);
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void withdrawMoney(Account account, double amount) { // Update database
        account.withdraw(amount);
        Connection con = BankConnection.connect();
        String sql = "update account set accBalance = ? where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccount(int number) { //Query database
        Connection con = BankConnection.connect();
        Account account = null;
        String accountName = "";
        double balance = 0;
        String sql = "select * from account where accID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, number);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                accountName = results.getString(2);
                balance = results.getDouble(3);
            }
            account = new Account(number, accountName, balance);
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }

        return account;

    }

}
