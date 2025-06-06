/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PersonalAcademico;

import ConexionLogin.Conexion;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rafael
 */
public class DisponibilidadPrestamos extends javax.swing.JFrame {

    /**
     * Creates new form DisponibilidadPrestamos
     */

    private JTextField campoDestino;
    public DisponibilidadPrestamos(JTextField campoDestino) {
        initComponents();
        aplicarColorFilasAlternadas(Reservas);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cargarTablaCompleta();
        this.campoDestino = campoDestino;
    }
    
    private void aplicarColorFilasAlternadas(JTable tabla) {
    TableCellRenderer renderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                if (row % 2 == 0) {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                } else {
                    setBackground(new Color(240, 240, 240));
                    setForeground(Color.BLACK);
                }
            }

            return this;
        }
    };

    for (int i = 0; i < tabla.getColumnCount(); i++) {
        tabla.getColumnModel().getColumn(i).setCellRenderer(renderer);
    }
}
    private DisponibilidadPrestamos() {
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
        Fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        Reservas = new javax.swing.JTable();
        Bloque = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        FondoBlanco = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Disponibilidad:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 120, 20));
        getContentPane().add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 130, -1));

        Reservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Laboratorio", "Fecha", "Horario Inicio", "Horario Fin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Reservas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReservasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Reservas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 880, 420));
        getContentPane().add(Bloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 130, 20));

        jLabel2.setText("Bloque:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, 20));

        btnBuscar.setBackground(new java.awt.Color(29, 41, 57));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        Guardar.setBackground(new java.awt.Color(51, 153, 0));
        Guardar.setForeground(new java.awt.Color(255, 255, 255));
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 140, 30));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 900, 510));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReservasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservasMouseClicked

    }//GEN-LAST:event_ReservasMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarDisponibilidad();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        int filaSeleccionada = Reservas.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombreLab = Reservas.getValueAt(filaSeleccionada, 1).toString(); // columna Código Laboratorio
            campoDestino.setText(nombreLab);
            dispose(); // Cierra la ventana después de seleccionar
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un laboratorio para guardar.");
        }
    }//GEN-LAST:event_GuardarActionPerformed
private void cargarTablaCompleta() {
    DefaultTableModel modelo = (DefaultTableModel) Reservas.getModel();
    modelo.setRowCount(0);

    // Consulta para obtener todos los laboratorios, independientemente de si están ocupados o no
    String query = "SELECT l.ID_lab, l.Nombre_lab "
            + "FROM laboratorios l "
            + "LEFT JOIN prestamos p ON l.ID_lab = p.ID_lab "
            + "WHERE l.estadolab = 1 "
            + "ORDER BY l.ID_lab ";

    try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(query)) {
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            
            Object[] fila = {
                rs.getInt("ID_lab"),
                rs.getString("Nombre_lab"),
                "Carga disponibilidad" // Mostrar "Carga disponibilidad" siempre
            };
            modelo.addRow(fila);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar la disponibilidad completa: " + ex.getMessage());
    }
}

    private void cargarDisponibilidad() {
        DefaultTableModel modelo = (DefaultTableModel) Reservas.getModel();
        modelo.setRowCount(0);

        java.util.Date fechaSeleccionada = Fecha.getDate();
        String bloqueTexto = Bloque.getText().trim();

        if (fechaSeleccionada == null || bloqueTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha y escribir un bloque.");
            return;
        }

        String fechaSQL = new SimpleDateFormat("yyyy-MM-dd").format(fechaSeleccionada);

        String queryLaboratorios = "SELECT ID_lab, Nombre_lab FROM laboratorios WHERE Bloque = ? AND estadolab = 1 ";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement psLabs = con.prepareStatement(queryLaboratorios)) {

            psLabs.setString(1, bloqueTexto);
            ResultSet rsLabs = psLabs.executeQuery();

            while (rsLabs.next()) {
                int idLab = rsLabs.getInt("ID_lab");
                String nombreLab = rsLabs.getString("Nombre_lab");

                String queryReservas = "SELECT fecha, horario_inicio, horario_fin FROM prestamos WHERE ID_lab = ? AND fecha = ?";
                try (PreparedStatement psRes = con.prepareStatement(queryReservas)) {
                    psRes.setInt(1, idLab);
                    psRes.setString(2, fechaSQL);
                    ResultSet rsRes = psRes.executeQuery();

                    boolean tieneReserva = false;

                    while (rsRes.next()) {
                        tieneReserva = true;
                        modelo.addRow(new Object[]{
                            idLab,
                            nombreLab,
                            rsRes.getString("fecha"),
                            rsRes.getString("horario_inicio"),
                            rsRes.getString("horario_fin")
                        });
                    }

                    if (tieneReserva) {
                        modelo.addRow(new Object[]{
                            idLab,
                            nombreLab,
                            "Disponible",
                            "-",
                            "-"
                        });
                    } else {
                        modelo.addRow(new Object[]{
                            idLab,
                            nombreLab,
                            "Disponible",
                            "-",
                            "-"
                        });
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + ex.getMessage());
        }
    }


    
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
            java.util.logging.Logger.getLogger(DisponibilidadPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DisponibilidadPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisponibilidadPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DisponibilidadPrestamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisponibilidadPrestamos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bloque;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JButton Guardar;
    private javax.swing.JTable Reservas;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
