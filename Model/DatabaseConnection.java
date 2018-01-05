package Model;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DatabaseConnection {

    private Connection connection = null;

    public DatabaseConnection(String filename)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
            System.out.println("Database connection successfully established.");
        }
        catch (ClassNotFoundException exception)
        {
            System.out.println("Class not found exception: " + exception.getMessage());
        }
        catch (SQLException exception)
        {
            System.out.println("Database connection error: " + exception.getMessage());
        }

    }


    public PreparedStatement newStatement(String query)
    {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        }
        catch (SQLException exception)
        {
            System.out.println("Database statement error: " + exception.getMessage());
        }
        return statement;
    }


    public ResultSet executeQuery(PreparedStatement statement)
    {
        try {
            return statement.executeQuery();
        }
        catch (SQLException exception)
        {
            System.out.println("Database query error: " + exception.getMessage());
            return null;
        }
    }

    public ResultSet executeQuery(String query)
    {
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) { return null; }

    }


    public void executeUpdate(PreparedStatement statement)
    {
        try {
            statement.executeUpdate();
        }
        catch (SQLException exception)
        {
            System.out.println("Database update error: " + exception.getMessage());
        }
    }

    public void disconnect()
    {
        System.out.println("Disconnecting from database.");
        try {
            if (connection != null) connection.close();
        }
        catch (SQLException exception)
        {
            System.out.println("Database disconnection error: " + exception.getMessage());
        }
    }

}

