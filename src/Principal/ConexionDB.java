package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    public Connection ConexionDB;

    public static Connection conectar() {
        Connection conexion;
        String host = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "2052003";
        String bd = "inmobiliaria";
        System.out.println("Conectando....");

        try {
            conexion = DriverManager.getConnection(host + bd, user, pass);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }

        return conexion;
    }

    public static void main(String[] args) {
        Connection bd = conectar();


    }
}