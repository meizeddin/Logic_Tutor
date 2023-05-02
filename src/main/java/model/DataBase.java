package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            File databaseFile = new File(DATABASE_NAME);
            if(!databaseFile.exists()) {
                createTables();
                // Print a success message
                System.out.println("Database created successfully");
            } else{
                System.out.println("Database already exists");
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
                "isCorrect BOOLEAN, " +
                "FOREIGN KEY (question_id) REFERENCES Question(question_id) " +
                ")";
        statement.executeUpdate(createAnswerTable);

        // Close statement
        statement.close();
    }

    public ObservableList<String> retrieveLessonsFromDatabase() {
        ObservableList<String> lessons = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT lesson_title FROM Lesson");
            while (resultSet.next()) {
                lessons.add(resultSet.getString("lesson_title"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public ObservableList<String> retrieveTestsFromDatabaseForLesson(String lesson) {
        ObservableList<String> tests = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT test_title FROM Test WHERE lesson_id = ?");
            preparedStatement.setInt(1, getLessonId(lesson));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tests.add(resultSet.getString("test_title"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    private int getLessonId(String lessonTitle) throws SQLException {
        int lessonId = -1;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT lesson_id FROM Lesson WHERE lesson_title = ?");
        preparedStatement.setString(1, lessonTitle);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            lessonId = resultSet.getInt("lesson_id");
        }
        preparedStatement.close();
        return lessonId;
    }

    private int getTestId(String testTitle) throws SQLException {
        int testId = -1;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT test_id FROM Test WHERE test_title = ?");
        preparedStatement.setString(1, testTitle);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            testId = resultSet.getInt("test_id");
        }
        preparedStatement.close();
        return testId;
    }

    private int getQuestionId(String questionText) throws SQLException {
        int questionId = -1;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT question_id FROM Question WHERE question_text = ?");
        preparedStatement.setString(1, questionText);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            questionId = resultSet.getInt("question_id");
        }
        preparedStatement.close();
        return questionId;
    }

    public ObservableList<String> retrieveQuestionsForTest(String test) {
        ObservableList<String> questions = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT question_text FROM Question WHERE test_id = ?");
            preparedStatement.setInt(1, getTestId(test));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                questions.add(resultSet.getString("question_text"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
    public ObservableList<String> retrieveAnswersForQuestion(String question) {
        ObservableList<String> answers = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT answer_text FROM Answer WHERE question_id = ?");
            preparedStatement.setInt(1, getQuestionId(question));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answers.add(resultSet.getString("answer_text"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
    public List<String> retrieveCorrectAnswersForQuestion(String question) {
        List<String> answers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT answer_text FROM Answer WHERE question_id = ? AND isCorrect = ?");
            preparedStatement.setInt(1, getQuestionId(question));
            preparedStatement.setInt(2, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answers.add(resultSet.getString("answer_text"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
