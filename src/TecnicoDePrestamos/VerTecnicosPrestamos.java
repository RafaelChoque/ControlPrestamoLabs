/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TecnicoDePrestamos;

import ConexionLogin.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class VerTecnicosPrestamos extends javax.swing.JFrame {

    /**
     * Creates new form VerTecnicosPrestamos
     */
    private JTextField campoDestino;
    public VerTecnicosPrestamos(JTextField campoDestino) {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.campoDestino = campoDestino;
    }

    private VerTecnicosPrestamos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        RUtxt = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        VerTecnicosPrestamos = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel1.setText("Usuarios (TecnicoPrestamos)");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 320, 30));

        jLabel2.setText("RU:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 20));

        RUtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RUtxtActionPerformed(evt);
            }
        });
        getContentPane().add(RUtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 210, -1));

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        getContentPane().add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        VerTecnicosPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RU", "Nombre", "Apellido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        VerTecnicosPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerTecnicosPrestamosmouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VerTecnicosPrestamos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 350, 90));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 640, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("RU");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        

        String RUBuscado = RUtxt.getText().trim(); 

        try {
            Connection con = Conexion.obtenerConexion();
            String sql;

            if (RUBuscado.isEmpty()) {
                // Si no escribió nada, mostrar todos
                sql = "SELECT id_tecnico_prestamos, RU, nombre, apellido FROM tecnico_prestamos";
            } else {
                // Si escribió un bloque, buscar solo ese bloque
                sql = "SELECT id_tecnico_prestamos, RU, nombre, apellido FROM tecnico_prestamos WHERE RU = ?";
            }

            PreparedStatement pst = con.prepareStatement(sql);

            if (!RUBuscado.isEmpty()) {
                pst.setString(1, RUBuscado);
            }

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("id_tecnico_prestamos");
                fila[1] = rs.getString("RU");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                modelo.addRow(fila);
            }

            VerTecnicosPrestamos.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage());
        }
    }
    private void RUtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RUtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RUtxtActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        cargarTabla();
    }//GEN-LAST:event_BuscarActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        int filaSeleccionada = VerTecnicosPrestamos.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombre = VerTecnicosPrestamos.getValueAt(filaSeleccionada, 2).toString(); // Supongamos columna 1: nombre
            String apellido = VerTecnicosPrestamos.getValueAt(filaSeleccionada, 3).toString(); // Supongamos columna 2: apellido
            String nombreCompleto = nombre + " " + apellido; // Concatenar nombre + espacio + apellido
            campoDestino.setText(nombreCompleto);
            dispose(); // Cierra la ventana después de seleccionar
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para guardar.");
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void VerTecnicosPrestamosmouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerTecnicosPrestamosmouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_VerTecnicosPrestamosmouseClicked

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
            java.util.logging.Logger.getLogger(VerTecnicosPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerTecnicosPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerTecnicosPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerTecnicosPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerTecnicosPrestamos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField RUtxt;
    private javax.swing.JTable VerTecnicosPrestamos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
