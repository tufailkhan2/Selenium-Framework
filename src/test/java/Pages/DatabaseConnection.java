package Pages;

import java.sql.*;

public class DatabaseConnection {
    public static Statement stmt;
    public static String query = "select * from movies";
    public static ResultSet res;

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Host123!");

        if(connection.isClosed()) {

            System.out.println("Connection is not established with the database");
        }

        else {
            System.out.println("Connections are successfully established");
            try {
                // Get the contents of movies table from DB
                res = stmt.executeQuery(query);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println(res);
        }
    }
}
