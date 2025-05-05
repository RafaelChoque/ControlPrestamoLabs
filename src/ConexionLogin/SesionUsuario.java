package ConexionLogin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Erlan
 */
public class SesionUsuario {
    public static int idUsuario;
    public static String username;
    public static String rol;
    public static String nombre;  // Para almacenar el nombre
    public static String apellido;  // Para almacenar el apellido
    public static int ci;
    public static String telefono;
    // Método para establecer los datos
    public static void setDatos(int id, String username, String rol, String nombre, String apellido) {
        SesionUsuario.idUsuario = id;
        SesionUsuario.username = username;
        SesionUsuario.rol = rol;
        SesionUsuario.nombre = nombre;
        SesionUsuario.apellido = apellido;  // Asignar apellido

    }
}

