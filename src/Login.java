/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Rafael
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        contrasena = new javax.swing.JPasswordField();
        IniciaSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Usuario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sesion = new javax.swing.JTextField();
        Contraseña = new javax.swing.JLabel();
        FondoUniversidad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contrasena.setBorder(null);
        contrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasenaActionPerformed(evt);
            }
        });
        jPanel2.add(contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 220, 30));

        IniciaSesion.setBackground(new java.awt.Color(53, 140, 198));
        IniciaSesion.setForeground(new java.awt.Color(255, 255, 255));
        IniciaSesion.setText("Iniciar Sesion");
        IniciaSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciaSesionActionPerformed(evt);
            }
        });
        jPanel2.add(IniciaSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        jLabel1.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel1.setText("Bienvenido al Sistema ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 10, 180, 20));

        jLabel4.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel4.setText("de Historial Clínico");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 160, -1));

        Usuario.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        Usuario.setText("Usuario");
        jPanel2.add(Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, -1));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 220, 3));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 220, 3));

        sesion.setBorder(null);
        sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sesionActionPerformed(evt);
            }
        });
        jPanel2.add(sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 220, 30));

        Contraseña.setFont(new java.awt.Font("Candara", 1, 12)); // NOI18N
        Contraseña.setText("Contraseña");
        jPanel2.add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 70, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 260, 260));

        FondoUniversidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoUniversidad.png"))); // NOI18N
        getContentPane().add(FondoUniversidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IniciaSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciaSesionActionPerformed
        String user = sesion.getText();
        @SuppressWarnings("deprecation")
        String pass = contrasena.getText();
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
                        if (priv.equals("paciente")) {

                        } else if (priv.equals("medico")) {

                        } else if (priv.equals("administrador")) {
                            Administrador ventanaadmin = new Administrador();
                            ventanaadmin.setVisible(true);
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel Usuario;
    private javax.swing.JPasswordField contrasena;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField sesion;
    // End of variables declaration//GEN-END:variables
}
