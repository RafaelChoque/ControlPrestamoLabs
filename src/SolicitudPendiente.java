/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Rafael
 */
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
public class SolicitudPendiente extends javax.swing.JFrame {

    /**
     * Creates new form SolicitudesLabs
     */
    public SolicitudPendiente() {
        initComponents();
        cargarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSolicitudes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        AprobarSolicitud = new javax.swing.JButton();
        RechazarSolicitud = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        MotivoRechazo = new javax.swing.JTextArea();
        AgregarPrestamo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Laboratorio", "Docente", "Motivo", "Fecha de Inicio", "Fecha de Fin", "Estado", "Motivo del Rechazo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaSolicitudes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 930, -1));

        jLabel1.setText("SOLICITUDES PENDIENTES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        AprobarSolicitud.setText("Aprobar");
        AprobarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AprobarSolicitudActionPerformed(evt);
            }
        });
        getContentPane().add(AprobarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 90, -1, -1));

        RechazarSolicitud.setText("Rechazar");
        RechazarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechazarSolicitudActionPerformed(evt);
            }
        });
        getContentPane().add(RechazarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 160, -1, -1));

        MotivoRechazo.setColumns(20);
        MotivoRechazo.setRows(5);
        jScrollPane2.setViewportView(MotivoRechazo);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 220, 400, 210));

        AgregarPrestamo.setText("Seleccionar Laboratorio");
        getContentPane().add(AgregarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void cargarTabla() {
    DefaultTableModel modeloTabla = (DefaultTableModel) TablaSolicitudes.getModel();
    modeloTabla.setRowCount(0);  
    
    PreparedStatement ps;
    ResultSet rs;
    ResultSetMetaData rsmd;
    int columnas;

    
    int[] anchos = {10, 100, 100, 100, 80, 80, 80, 100, 100, 80};
    for (int i = 0; i < TablaSolicitudes.getColumnCount(); i++) {
        TablaSolicitudes.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
    }

    try {
        
        Connection con = Conexion.obtenerConexion();  
        ps = con.prepareStatement(
            "SELECT p.id_prestamo, l.Nombre_lab, pa.nombre, pa.apellido, p.motivo, l.bloque, l.seccion, p.fecha_inicio, p.fecha_fin, p.estado "
            + "FROM prestamos p "
            + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
            + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico"
        );
        rs = ps.executeQuery();
        rsmd = rs.getMetaData();
        columnas = rsmd.getColumnCount();

        
        while (rs.next()) {
            Object[] fila = new Object[columnas];
            for (int indice = 0; indice < columnas; indice++) {
                fila[indice] = rs.getObject(indice + 1);
            }
            modeloTabla.addRow(fila);  
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void AprobarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AprobarSolicitudActionPerformed
        try{
        int fila = TablaSolicitudes.getSelectedRow();
        int idSolicitud = Integer.parseInt(TablaSolicitudes.getValueAt(fila,0).toString());
        
        Connection con = Conexion.obtenerConexion();
        PreparedStatement ps = con.prepareStatement("UPDATE prestamos SET estado = 'Aprobado' WHERE id_prestamo = ?");
        ps.setInt(1,idSolicitud);
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Solicitud aprobada correctamente.");
        
        }catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al aprobar la solicitud: " + ex.getMessage());
    }
        
    }//GEN-LAST:event_AprobarSolicitudActionPerformed

    private void RechazarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechazarSolicitudActionPerformed
    int fila = TablaSolicitudes.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud para rechazar.");
        return;
    }

    String motivo = MotivoRechazo.getText().trim();
    if (motivo.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Debe ingresar un motivo para rechazar la solicitud.");
        return;
    }

    try {
        int idSolicitud = Integer.parseInt(TablaSolicitudes.getValueAt(fila, 0).toString());

        Connection con = Conexion.obtenerConexion();
        PreparedStatement ps = con.prepareStatement("UPDATE prestamos SET estado = 'Rechazado', motivo = ? WHERE id_prestamo = ?");
        ps.setString(1, motivo);
        ps.setInt(2, idSolicitud);
        ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Solicitud rechazada correctamente.");
        MotivoRechazo.setText(""); 
        
        
        cargarTabla();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al rechazar la solicitud: " + ex.getMessage());
    }
    }//GEN-LAST:event_RechazarSolicitudActionPerformed

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SolicitudPendiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarPrestamo;
    private javax.swing.JButton AprobarSolicitud;
    private javax.swing.JTextArea MotivoRechazo;
    private javax.swing.JButton RechazarSolicitud;
    private javax.swing.JTable TablaSolicitudes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
