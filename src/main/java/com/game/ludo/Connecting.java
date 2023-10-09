package com.game.ludo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connecting {
    static Connection connection;
    public Connecting(String DataBaseName) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + DataBaseName,
                "root",
                ""
        );
    }
}