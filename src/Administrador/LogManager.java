/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;
import ConexionLogin.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Erlan
 */
public class LogManager {
    
    public static void registrarLog(int idUsuario, String rol, String tipo, String accion) {
        Connection con = Conexion.obtenerConexion();

        if (con == null) {
            System.out.println("No se pudo registrar log porque la conexión falló.");
            return;
        }

        String sql = "INSERT INTO logs (id_usuario, rol, tipo, accion, fecha) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, rol);
            ps.setString(3, tipo);
            ps.setString(4, accion);

            LocalDateTime now = LocalDateTime.now();
            String fechaHora = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ps.setString(5, fechaHora);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar el log: " + e.getMessage());
        }
    }
}