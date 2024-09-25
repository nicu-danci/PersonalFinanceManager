package org.example;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:finance_manager.db";

    public Connection connect(){
        Connection conn=null;

        try{
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to SQLite has been established");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createNewTables(){
        String usersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "username TEXT NOT NULL,\n"
                + "email TEXT NOT NULL,\n"
                + "password TEXT NOT NULL\n"
                + ");";

        String transactionsTable = "CREATE TABLE IF NOT EXISTS transactions (\n"
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "user_id INTEGER,\n"
                + "date TEXT,\n"
                + "description TEXT,\n"
                + "amount REAL,\n"
                + "category TEXT,\n"
                + "transaction_type TEXT,\n"
                + "FOREIGN KEY(user_id) REFERENCES users(id)\n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(usersTable);
            stmt.execute(transactionsTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // method to register new users
    public boolean registerUser (String username, String email, String hashedPassword) {
        String sql = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";
        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, hashedPassword);
            pstmt.executeUpdate();
            System.out.println("User registered successfully.");
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //method to authenticate user by username and password

    public boolean authenticateUser(String username, String passwordHash){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try(Connection conn=this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                System.out.println("Login successfully.");
                return true;
            } else {
                System.out.println("Login failed. Incorrect username or password.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
