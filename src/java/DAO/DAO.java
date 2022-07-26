package DAO;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public Connection conectar() {
        Connection con = null;

        try {
            Driver drv = new Driver();
            DriverManager.registerDriver(drv);
            String url = "jdbc:mysql://localhost:3306/jada?user=root&password=root&useSSL=false";
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error en conexion a base de datos");
        }
        return con;
    }
}
