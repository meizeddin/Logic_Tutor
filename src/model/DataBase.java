package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String DATABASE_NAME = "DB/LogicTutorDB.db";
    private Connection connection = null;

    public DataBase() {
        try {
            // Register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Open a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);

            // Execute a method to create tables
            createTables();

            // Print a success message
            System.out.println("Database created successfully");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if(connection != null)
                    connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    private void createTables() throws SQLException {
        // Execute a statement to create tables
        Statement statement = connection.createStatement();
        String createLessonTable = "CREATE TABLE Lesson ( " +
                "lesson_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lesson_title TEXT NOT NULL UNIQUE" +
                ")";
        statement.executeUpdate(createLessonTable);

        String createTestTable = "CREATE TABLE Test ( " +
                "test_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lesson_id INTEGER, " +
                "test_title TEXT NOT NULL UNIQUE, " +
                "FOREIGN KEY (lesson_id) REFERENCES Lesson(lesson_id) " +
                ")";
        statement.executeUpdate(createTestTable);

        String createQuestionTable = "CREATE TABLE Question ( " +
                "question_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "test_id INTEGER, " +
                "question_text TEXT, " +
                "FOREIGN KEY (test_id) REFERENCES Test(test_id) " +
                ")";
        statement.executeUpdate(createQuestionTable);

        String createAnswerTable = "CREATE TABLE Answer ( " +
                "answer_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question_id INTEGER, " +
                "answer_text TEXT NOT NULL, " +
                "FOREIGN KEY (question_id) REFERENCES Question(question_id) " +
                ")";
        statement.executeUpdate(createAnswerTable);

        // Close statement
        statement.close();
    }
}
