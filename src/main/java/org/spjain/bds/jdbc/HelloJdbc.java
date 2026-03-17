package org.spjain.bds.jdbc;

import java.sql.*;

public class HelloJdbc {
    private static final String URL = "jdbc:mysql://localhost:3306/spjain";
    private static final String USER = "spjain";
    private static final String PASSWORD = "spjain";

    // Load the MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Example of update
    private void modifyEmail(Connection connection, int id, String email) {
        String sql = "UPDATE authors SET Email = ? WHERE AuthorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Example of query
    private int getAuthorId(Connection connection, String email) {
        String sql = "SELECT AuthorId FROM authors where email=?";
        int id = -1;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("AuthorID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Oops. Email not found");
        }
        return id;
    }

    private void addBookForAuthor(Connection connection, int authorId, String title, Date publishedDate) {
        String sql = "INSERT INTO books (AuthorID, Title, PublicationDate) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, authorId);
            statement.setString(2, title);
            statement.setDate(3, publishedDate);
            int rowsInserted = statement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteAuthor(Connection connection, int id) {
        // delete all the books for this author first
        String sql1 = "DELETE FROM books WHERE AuthorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "DELETE FROM authors WHERE AuthorID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAuthor(Connection connection, String fname, String lname, String email) {
        String sql = "INSERT INTO authors (FirstName, LastName, Email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, email);
            int rowsInserted = statement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloJdbc helloJdbc = new HelloJdbc();
        try (Connection connection = helloJdbc.getConnection()) {
            System.out.println("Connection successful: " + connection);
            connection.setAutoCommit(false);

            helloJdbc.addAuthor(connection, "Shiva", "Jayaraman", "shiva@gmail.com");
            int id = helloJdbc.getAuthorId(connection, "shiva@gmail.com");
            if (true) {
                //throw new RuntimeException("Oops. Something went wrong");
            }
            helloJdbc.modifyEmail(connection, id, "hello@gmail.com");

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = helloJdbc.getConnection()) {
            int id = helloJdbc.getAuthorId(connection, "hello@gmail.com");
            helloJdbc.addBookForAuthor(connection, id, "Java for Beginners", Date.valueOf("2024-01-01"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = helloJdbc.getConnection()) {
            connection.setAutoCommit(false);
            int id = helloJdbc.getAuthorId(connection, "hello@gmail.com");
            helloJdbc.deleteAuthor(connection, id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
