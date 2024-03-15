package org.structure.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public Connection conn;

    private final String databaseUser = "avnadmin";
    private final String databasePassword = "AVNS_E9194BN-hYsEl5IDjDg";
    private final String url = "jdbc:mysql://mysql-23b70503-hamdounechaymae-7b98.a.aivencloud.com:10809/SEBO";


    public ConnectDB() {
        super();
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            e.getCause();
        }
        return this.conn;
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

