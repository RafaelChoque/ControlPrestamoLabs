package ConexionLogin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rafael
 */
import java.sql.*;

public class Conexion {
    public static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3306/ControlPrestamo?"
                   + "useSSL=false&"
                   + "serverTimezone=UTC";
        String user = "root";
        String password = "13644332"; 

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            return con; //Comentario aFMNSAIOFNSAIOGNA
        } catch (SQLException ex) {
            System.out.println("Error en la conexión: " + ex.toString());
            return null;//.
        }
    }
}


