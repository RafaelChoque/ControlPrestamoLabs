import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


public class ListaLaboratorios extends javax.swing.JFrame {

    public ListaLaboratorios() {
        initComponents();
        FondoBlanco.setFocusable(true);
        FondoBlanco.requestFocusInWindow();



        txtID.setVisible(false);

        this.setLocationRelativeTo(null);
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

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtLaboratorio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JTextField();
        txtBloque = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnHabilitarDeshabilitar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        cbSeccion = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLaboratorios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        LogoSale = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();
        Izquierda = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1540, 863));
        setMinimumSize(new java.awt.Dimension(1540, 863));
        setPreferredSize(new java.awt.Dimension(1540, 863));
        setSize(new java.awt.Dimension(1540, 863));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Barra.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 40, 80));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel8.setText("Datos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));
        jPanel1.add(txtLaboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 340, -1));

        jLabel3.setText("Laboratorio:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setText("Unidades:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        jPanel1.add(txtUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 340, -1));
        jPanel1.add(txtBloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 340, -1));

        jLabel7.setText("Bloque:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(29, 41, 57));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        btnModificar.setBackground(new java.awt.Color(29, 41, 57));
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(29, 41, 57));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, -1, -1));

        btnLimpiar.setBackground(new java.awt.Color(29, 41, 57));
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, -1));

        btnHabilitarDeshabilitar.setBackground(new java.awt.Color(29, 41, 57));
        btnHabilitarDeshabilitar.setForeground(new java.awt.Color(255, 255, 255));
        btnHabilitarDeshabilitar.setText("Habilitar/Deshabilitar");
        btnHabilitarDeshabilitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHabilitarDeshabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarDeshabilitarActionPerformed(evt);
            }
        });
        jPanel1.add(btnHabilitarDeshabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        jLabel9.setText("Codigo:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 340, -1));

        cbSeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Redes", "Telecomunicaciones", "Electronica" }));
        cbSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSeccionActionPerformed(evt);
            }
        });
        jPanel1.add(cbSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 160, -1));

        jLabel10.setText("Sección:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 10, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 210, 490, 250));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, 20));

        jTextField1.setBackground(new java.awt.Color(233, 236, 239));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setText("Buscar");
        jTextField1.setToolTipText("");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 90, 20));
        String placeholder = "Buscar ID";

        // Al inicio, pon el placeholder en el campo de texto
        jTextField1.setText(placeholder);
        jTextField1.setForeground(Color.GRAY); // Estilo de texto guía

        // Listener para manejar el enfoque
        jTextField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField1.getText().equals(placeholder)) {
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.BLACK); // Color normal para escribir
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setText(placeholder);
                    jTextField1.setForeground(Color.GRAY);
                }
            }
        });

        // Listener para filtrar
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String query = jTextField1.getText().toLowerCase();

                // Si el texto es el placeholder, no filtrar nada
                if (query.equals(placeholder.toLowerCase())) {
                    tblLaboratorios.setRowSorter(null); // Mostrar todos
                    return;
                }

                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblLaboratorios.getModel());
                tblLaboratorios.setRowSorter(sorter);

                if (query.trim().isEmpty()) {
                    sorter.setRowFilter(null); // Mostrar todo
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(query, 0)); // Filtra por columna 0 (ID)
                }
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 190, 40));

        tblLaboratorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Laboratorio", "Unidades", "Bloque", "Sección", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLaboratorios.setToolTipText("");
        tblLaboratorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLaboratoriosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblLaboratorios);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 690, 550));

        jLabel1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel1.setText("Lista de Laboratorios");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 240, 50));

        LogoSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logosaleint.png"))); // NOI18N
        getContentPane().add(LogoSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 60));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 80));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 1250, 720));

        Izquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Recuadro azul.png"))); // NOI18N
        getContentPane().add(Izquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 870));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void cargarTabla(){
        
        DefaultTableModel modeloTabla = (DefaultTableModel) tblLaboratorios.getModel();
        modeloTabla.setRowCount(0);
        
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;
        
        
        
        try{
            Connection con = Conexion.obtenerConexion();
            ps = con.prepareStatement("SELECT id_lab, codigo_lab, nombre_lab, unidades, bloque, seccion, estado FROM laboratorios");
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();
            
            while (rs.next()){
                Object[] fila = new Object[columnas];
                for(int indice=0; indice<columnas; indice++){
                    fila[indice]=rs.getObject(indice + 1);
                }modeloTabla.addRow(fila);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }    

    
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String codigo = txtCodigo.getText();
        String laboratorio = txtLaboratorio.getText();
        String computadorasStr = txtUnidades.getText();
        String bloque = txtBloque.getText();
        String seccion = cbSeccion.getSelectedItem() != null ? cbSeccion.getSelectedItem().toString() : "";

        if (codigo.isEmpty() || laboratorio.isEmpty() || computadorasStr.isEmpty() || bloque.isEmpty() || seccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
            return;
        }
        
        int computadoras = Integer.parseInt(computadorasStr);
        
        try{
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO laboratorios (codigo_lab, nombre_lab, unidades, bloque, seccion, estado) VALUES (?,?,?,?,?,?)");
            ps.setString(1, codigo);
            ps.setString(2, laboratorio);
            ps.setInt(3, computadoras);
            ps.setString(4, bloque);
            ps.setString(5, seccion);
            ps.setInt(6, 1);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, ("Registro Guardado"));
            limpiar();
            cargarTabla();
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void limpiar(){
        txtCodigo.setText("");
        txtLaboratorio.setText("");
        txtUnidades.setText("");
        txtBloque.setText("");
        cbSeccion.setSelectedIndex(-1);
    }
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int id  = Integer.parseInt(txtID.getText());
        String codigo = txtCodigo.getText();
        String laboratorio = txtLaboratorio.getText();
        String computadorasStr = txtUnidades.getText();
        String bloque = txtBloque.getText();
        String seccion = cbSeccion.getSelectedItem() != null ? cbSeccion.getSelectedItem().toString() : "";

        if (codigo.isEmpty() || laboratorio.isEmpty() || computadorasStr.isEmpty() || bloque.isEmpty() || seccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
            return;
        }
        
        int computadoras = Integer.parseInt(computadorasStr);
        
        try{
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE laboratorios SET codigo_lab = ?, nombre_lab = ?, unidades = ?, bloque = ?, seccion = ? WHERE id_lab = ?");
            ps.setString(1, codigo);
            ps.setString(2, laboratorio);
            ps.setInt(3, computadoras);
            ps.setString(4, bloque);
            ps.setString(5, seccion);
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, ("Registro Modificado"));
            limpiar();
            cargarTabla();
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int id  = Integer.parseInt(txtID.getText());
        
        try{
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM laboratorios WHERE id_lab = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, ("Registro Eliminado"));
            limpiar();
            cargarTabla();
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnHabilitarDeshabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarDeshabilitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHabilitarDeshabilitarActionPerformed

    private void cbSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSeccionActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void tblLaboratoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLaboratoriosMouseClicked
        try{
            int fila = tblLaboratorios.getSelectedRow();
            int id = Integer.parseInt(tblLaboratorios.getValueAt(fila, 0).toString());
            PreparedStatement ps;
            ResultSet rs;
            Connection con = Conexion.obtenerConexion();
            ps = con.prepareStatement("SELECT codigo_lab, nombre_lab, unidades, bloque, seccion, estado FROM laboratorios WHERE id_lab=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                txtID.setText(String.valueOf(id));
                txtCodigo.setText(rs.getString("codigo_lab"));
                txtLaboratorio.setText(rs.getString("nombre_lab"));
                txtUnidades.setText(rs.getString("unidades"));
                txtBloque.setText(rs.getString("bloque"));
                cbSeccion.setSelectedItem(rs.getString("seccion"));
                //txtCodigo.setText(rs.getString("codigo_lab"));
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            
        }
    }//GEN-LAST:event_tblLaboratoriosMouseClicked


    
    
    public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e){
            e.printStackTrace();
        }
        /*    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaLaboratorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaLaboratorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaLaboratorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaLaboratorios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaLaboratorios().setVisible(true);
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JLabel Izquierda;
    private javax.swing.JLabel LogoSale;
    private javax.swing.JLabel Superior;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHabilitarDeshabilitar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbSeccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblLaboratorios;
    private javax.swing.JTextField txtBloque;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLaboratorio;
    private javax.swing.JTextField txtUnidades;
    // End of variables declaration//GEN-END:variables
}
