/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TecnicoDeEquipos;

import ConexionLogin.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class ReparacionesCompus extends javax.swing.JFrame {

    private int idusuario;

    /**
     * Creates new form ReparacionesCompus
     * @param idusuario
     */
    public ReparacionesCompus(int idusuario) {
        initComponents();
        this.idusuario = idusuario;

        Nombre.setEditable(false);
        Apellido.setEditable(false);
        cargarTablaLista();
        cargarNombreApellido(idusuario);
    }

    private ReparacionesCompus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Superior = new javax.swing.JLabel();
        CompusArregladas = new javax.swing.JLabel();
        ListaCompus1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Fecha = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Descripcion = new javax.swing.JTextArea();
        Guardar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCompusArregladas = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaComputadoras = new javax.swing.JTable();
        FondoGris1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        CompusArregladas.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        CompusArregladas.setText("Computadoras Arregladas");
        getContentPane().add(CompusArregladas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 280, 30));

        ListaCompus1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaCompus1.setText("Lista de Computadoras");
        getContentPane().add(ListaCompus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel8.setText("Datos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));
        jPanel1.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 300, -1));

        jLabel10.setText("Descripcion:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 70, 20));

        jLabel11.setText("Fecha:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 40, 20));

        jLabel12.setText("Nombre:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        jLabel7.setText("Apellido");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 300, -1));
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 300, -1));

        Descripcion.setColumns(20);
        Descripcion.setRows(5);
        jScrollPane2.setViewportView(Descripcion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 400, 110));

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, 440, 350));

        tblCompusArregladas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Código", "Laboratorio", "Estado", "Fecha", "Descripcion", "Realizado Por"
            }
        ));
        jScrollPane3.setViewportView(tblCompusArregladas);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 487, 1490, 360));

        tblListaComputadoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Laboratorio", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListaComputadoras);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1030, 340));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void cargarTablaLista() {
    DefaultTableModel modelo = (DefaultTableModel) tblListaComputadoras.getModel();
    modelo.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos

    try {
        Connection con = Conexion.obtenerConexion();
        PreparedStatement ps = con.prepareStatement(
                "SELECT mh.id_material_hardware, mh.nombre, l.Codigo_lab, mh.estado "
                + "FROM materiales_hardware mh "
                + "JOIN laboratorios l ON mh.ID_lab = l.ID_lab "
                + "WHERE mh.estado = 'Mantenimiento'"); // Filtramos por el estado "Mantenimiento"

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Object[] fila = {
                rs.getInt("id_material_hardware"),
                rs.getString("nombre"),
                rs.getString("Codigo_lab"),
                rs.getString("estado"),
            };
            modelo.addRow(fila);
        }
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
}

private void cargarTablaReparado(){
    
}

    private int obtenerIdMaterialSeleccionado() {
        int filaSeleccionada = tblListaComputadoras.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un material de la lista.");
            return -1; 
        }
        return (int) tblListaComputadoras.getValueAt(filaSeleccionada, 0); 
    }


    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        String nombretxt = Nombre.getText();
        String apellidotxt = Apellido.getText();
        Date fechatxt = Fecha.getDate();
        String descripciontxt = Descripcion.getText();

        if (fechatxt == null || descripciontxt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.");
            return;
        }

        java.sql.Timestamp fechaSQL = new java.sql.Timestamp(fechatxt.getTime());

        try {
            Connection con = ConexionLogin.Conexion.obtenerConexion();

            int idMaterial = obtenerIdMaterialSeleccionado();

            // Insertar la reparación
            String sql = "INSERT INTO mantenimiento (id_material_hardware, fecha, descripcion, id_tecnico_equipos) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMaterial);
            ps.setTimestamp(2, fechaSQL);
            ps.setString(3, descripciontxt);
            ps.setInt(4, idusuario);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                // Actualizar el estado del material a "Disponible"
                String actualizarEstadoSql = "UPDATE materiales_hardware SET estado = 'Disponible' WHERE id_material_hardware = ?";
                PreparedStatement psUpdate = con.prepareStatement(actualizarEstadoSql);
                psUpdate.setInt(1, idMaterial);
                psUpdate.executeUpdate();

                JOptionPane.showMessageDialog(null, "Reparación guardada correctamente y estado actualizado a Disponible.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar la reparación.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_GuardarActionPerformed

        public void cargarNombreApellido(int idusuario) {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT nombre, apellido FROM tecnico_equipos WHERE id_usuario = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Nombre.setText(rs.getString("nombre"));
                Apellido.setText(rs.getString("apellido"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos del personal.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
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
            java.util.logging.Logger.getLogger(ReparacionesCompus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReparacionesCompus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReparacionesCompus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReparacionesCompus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReparacionesCompus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JLabel CompusArregladas;
    private javax.swing.JTextArea Descripcion;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel ListaCompus1;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel Superior;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCompusArregladas;
    private javax.swing.JTable tblListaComputadoras;
    // End of variables declaration//GEN-END:variables
}
