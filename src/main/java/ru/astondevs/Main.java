package ru.astondevs;

import java.sql.*;

/**
 * Main class demonstrating basic JDBC operations with PostgreSQL,
 * including table creation, data insertion, and reading with a simple cache.
 */
public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    /**
     * Entry point of the application.
     * Establishes a database connection and performs basic operations.
     */
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to PostgreSQL successfully!");

            createTable(conn);
            insertUser(conn, "Alice", "alice@example.com");
            insertUser(conn, "Bob", "bob@example.com");
            readUsers(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the 'users' table if it does not already exist.
     *
     * @param conn the database connection
     * @throws SQLException if a database error occurs
     */
    private static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100)
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created (if not exists).");
        }
    }

    /**
     * Inserts a new user into the 'users' table.
     *
     * @param conn  the database connection
     * @param name  the name of the user
     * @param email the email address of the user
     * @throws SQLException if a database error occurs
     */
    private static void insertUser(Connection conn, String name, String email) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Inserted: " + name);
        }
    }

    /**
     * Reads all users from the database, using a simple in-memory cache
     * to avoid redundant queries.
     *
     * @param conn the database connection
     * @throws SQLException if a database error occurs
     */
    private static void readUsers(Connection conn) throws SQLException {
        String sql = "SELECT id, name, email FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nUsers in DB:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Email: %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            }
        }
    }
}
