/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Rafael
 */
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents(); /*WASAAAAAAAAAAAAA*/
        contrasena.setEchoChar('•');
        btnMostrarContraseña.setIcon(ojoOcultar);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new RoundedPanel();
        btnMostrarContraseña = new javax.swing.JButton();
        Contraseña = new javax.swing.JLabel();
        Linea3 = new javax.swing.JLabel();
        contrasena = new javax.swing.JPasswordField();
        IniciaSesion = new javax.swing.JButton();
        Usuario = new javax.swing.JLabel();
        Linea2 = new javax.swing.JLabel();
        sesion = new javax.swing.JTextField();
        lblErrorUsuario = new javax.swing.JLabel();
        lblErrorContrasena = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        FondoUniversidad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CandadoInicioSesion.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(30, 30));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 100, 80));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMostrarContraseña.setBorder(null);
        btnMostrarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarContraseñaActionPerformed(evt);
            }
        });
        jPanel2.add(btnMostrarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 30, 20));

        Contraseña.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        Contraseña.setForeground(new java.awt.Color(102, 102, 102));
        Contraseña.setText("Contraseña");
        jPanel2.add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 168, 120, -1));

        Linea3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BarraAzulLogin.png"))); // NOI18N
        jPanel2.add(Linea3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 410, 10));

        contrasena.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        contrasena.setBorder(null);
        contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasenaActionPerformed(evt);
            }
        });
        jPanel2.add(contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 370, 20));

        IniciaSesion.setBackground(new java.awt.Color(29, 41, 57));
        IniciaSesion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        IniciaSesion.setForeground(new java.awt.Color(255, 255, 255));
        IniciaSesion.setText("Iniciar Sesión");
        IniciaSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciaSesionActionPerformed(evt);
            }
        });
        jPanel2.add(IniciaSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 170, 40));

        Usuario.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        Usuario.setForeground(new java.awt.Color(102, 102, 102));
        Usuario.setText("Usuario");
        jPanel2.add(Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 68, 80, 30));

        Linea2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BarraAzulLogin.png"))); // NOI18N
        jPanel2.add(Linea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 410, 10));

        sesion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sesion.setBorder(null);
        sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sesionActionPerformed(evt);
            }
        });
        jPanel2.add(sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 360, 20));

        lblErrorUsuario.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblErrorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 260, 20));

        lblErrorContrasena.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(lblErrorContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 270, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 490, 320));

        jLabel1.setText("Sistema de Control y Prestamo de Laboratorios de Electronica, Hardware, Redes y Telecomunicaciones - LosJackson");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 840, 620, -1));

        FondoUniversidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoLogin.png"))); // NOI18N
        getContentPane().add(FondoUniversidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public class RoundedPanel extends JPanel {
    private int arcWidth = 30;  // Radio horizontal
    private int arcHeight = 30; // Radio vertical

    public RoundedPanel() {
        setOpaque(false); // Esto es clave para que no se dibuje el fondo por defecto
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground()); // Usa el color de fondo que le pongas
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2.dispose();
        super.paintComponent(g);
    }
}
    private void IniciaSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciaSesionActionPerformed
        String user = sesion.getText();
        @SuppressWarnings("deprecation")
        String pass = contrasena.getText();
        
        lblErrorUsuario.setText("");
        lblErrorContrasena.setText("");
        lblErrorUsuario.setText("");
        lblErrorUsuario.setIcon(null);
        lblErrorContrasena.setText("");
        lblErrorContrasena.setIcon(null);
        
        
        boolean camposValidos = true;
        if (user.isEmpty()) {
            ImageIcon iconoAdvertencia = new ImageIcon(getClass().getResource("/imagenes/Exclamacion3.png"));
            lblErrorUsuario.setIcon(iconoAdvertencia);
            lblErrorUsuario.setText("Porfavor introduzca un usuario.");

            camposValidos = false;
        }
        if (pass.isEmpty()) {
            ImageIcon iconoAdvertencia = new ImageIcon(getClass().getResource("/imagenes/Exclamacion3.png"));
            lblErrorContrasena.setIcon(iconoAdvertencia);
            lblErrorContrasena.setText("Porfavor introduzca una contraseña.");
            camposValidos = false;
        }

        if (!camposValidos) {
            return;
        }
        
        String query = "SELECT id_usuario, username, contrasena, rol, activo FROM usuarios "
                + "WHERE username = ? AND contrasena = ?";
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            
            //verifica si existe un usuario con los datos proporcionados
            if (rs.next()) {
                String u = rs.getString("username");
                String p = rs.getString("contrasena");
                String priv = rs.getString("rol");
                int idusuario = rs.getInt("id_usuario");
                boolean activo = rs.getBoolean("activo"); // Obtén el estado activo

                if (activo) { // Verifica si el usuario está activo
                    if (pass.equals(p)) {
                        // Redirige a la interfaz correspondiente según el rol
                        if (priv.equals("Tecnico de Prestamos")) {
 
                            this.dispose();
                        } else if (priv.equals("Tecnico de Mantenimientos")) {

                        } else if (priv.equals("Administrador")) {
                            AdministradorTecnicoPrestamo ventanaadmin = new AdministradorTecnicoPrestamo();
                            ventanaadmin.setVisible(true);
                            this.dispose();
                        } else if (priv.equals("Personal Academico")) {
                            // Lógica para redirigir a la interfaz de Personal Académico
                            FormularioPrestamo ventanaAcademico = new FormularioPrestamo(idusuario);
                            ventanaAcademico.setVisible(true);
                            this.dispose();
                        }
                    } else {
                        
                        JOptionPane.showMessageDialog(null, "LA CONTRASEÑA NO ES CORRECTA");
                    }
                } else {
                   
                    JOptionPane.showMessageDialog(null, "SU USUARIO ESTÁ INACTIVO. CONTACTE AL ADMINISTRADOR.");
                }
            } else {
                
                JOptionPane.showMessageDialog(null, "EL USUARIO NO EXISTE");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_IniciaSesionActionPerformed

    private void sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sesionActionPerformed

    private void contrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contrasenaActionPerformed

        ImageIcon ojoMostrar = new ImageIcon(getClass().getResource("/Imagenes/Ojo1.png"));
        ImageIcon ojoOcultar = new ImageIcon(getClass().getResource("/Imagenes/Ojo2.png"));
    private void btnMostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarContraseñaActionPerformed

        if (contrasena.getEchoChar() == '•') {
            contrasena.setEchoChar((char) 0);  // Muestra la contraseña
            btnMostrarContraseña.setIcon(ojoMostrar);
        } else {
            contrasena.setEchoChar('•');  // Oculta la contraseña
             btnMostrarContraseña.setIcon(ojoOcultar);
        }
    }//GEN-LAST:event_btnMostrarContraseñaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e){
            e.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contraseña;
    private javax.swing.JLabel FondoUniversidad;
    private javax.swing.JButton IniciaSesion;
    private javax.swing.JLabel Linea2;
    private javax.swing.JLabel Linea3;
    private javax.swing.JLabel Usuario;
    private javax.swing.JButton btnMostrarContraseña;
    private javax.swing.JPasswordField contrasena;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblErrorContrasena;
    private javax.swing.JLabel lblErrorUsuario;
    private javax.swing.JTextField sesion;
    // End of variables declaration//GEN-END:variables
}


