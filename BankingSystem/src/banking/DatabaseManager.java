package BankingSystem.src.banking;

import java.sql.*;

public class DatabaseManager {
    // Database connection URL for the SQLite file.
    private static final String URL = "jdbc:sqlite:BankingSystem/data/banking.db";

    // Create a connection to the SQLite database.
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Create the accounts table if it does not already exist.
    public static void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS accounts (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    account_name TEXT NOT NULL,
                    balance REAL NOT NULL
                )
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("\n[DB] Table 'accounts' verified/created.\n");

        } catch (SQLException e) {
            System.out.println("\n[DB Error] Could not create table: " + e.getMessage() + "\n");
        }
    }

    // Save a new account row into the database.
    public static void saveAccount(BankAccount account) {
        String sql = "INSERT INTO accounts (account_name, balance) VALUES (?, ?);";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getAccountHolder());
            pstmt.setDouble(2, account.getBalance());

            pstmt.executeUpdate();
            System.out.println("\n[DB] Account successfully saved to database!\n");

        } catch (SQLException e) {
            System.out.println("\n[DB Error] Could not save account: " + e.getMessage() + "\n");
        }
    }

    // Update the stored balance for the current account holder.
    public static void updateAccount(BankAccount account) {
        String sql = """
                UPDATE accounts
                SET balance = ?
                WHERE account_name = ?
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, account.getBalance());
            pstmt.setString(2, account.getAccountHolder());
            pstmt.executeUpdate();

            System.out.println("\n[DB] Account successfully updated to database!");

        } catch (Exception e) {
            System.out.println("\n[DB Error] Couldn't update account: " + e.getMessage());
        }
    }

    // Check whether an account already exists in the database.
    public static boolean getAccount(BankAccount account) {
        String sql = """
                SELECT balance
                FROM accounts
                WHERE account_name = ?
                """;

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getAccountHolder());

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    account.setBalance(balance);
                    return true;
                    }
                }
        } catch (Exception e) {
            System.out.println("\n[DB Error] Can't access database: " + e.getMessage());
        }
        return false;
    }
}


