package testtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testMysql {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        String user = "root";        // XAMPP usa 'root' sin contraseña
        String pass = "";            // contraseña vacía

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida ✨");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar:");
            e.printStackTrace();
        }
    }
}
