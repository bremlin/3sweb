package com.ibcon.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by s_shmakov on 31.08.2016.
 */
public class MsSQLConnect {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://ssqlphn1d.ibcon.office;database=Factage2";
    private static String user = "sa1";
    private static String password = "11111Q!";

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
