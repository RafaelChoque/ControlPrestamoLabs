/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Rafael
 */
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class AdministradorTecnicoEquipos extends javax.swing.JFrame {

    /**
     * Creates new form AdministradorTecnicoEquipos
     */
    public AdministradorTecnicoEquipos() {
        initComponents();
        ID.setVisible(false);
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

        LogoSale = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        Telefono = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        CI = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        RU = new javax.swing.JTextField();
        ID = new javax.swing.JTextField();
        HabilitarDeshabilitar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTecnicoEquipo = new javax.swing.JTable();
        Superior = new javax.swing.JLabel();
        ListaTecnicos = new javax.swing.JLabel();
        AgregarTecnico = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();
        Izquierda = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logosaleint.png"))); // NOI18N
        getContentPane().add(LogoSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 170, 60));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel3.setText("Nombre");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 30));

        jLabel4.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel4.setText("Apellido");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, 30));

        jLabel5.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel5.setText("CI");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, 30));

        jLabel6.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel6.setText("RU");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, 30));

        guardar.setBackground(new java.awt.Color(53, 140, 198));
        guardar.setForeground(new java.awt.Color(255, 255, 255));
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, -1));

        modificar.setBackground(new java.awt.Color(53, 140, 198));
        modificar.setForeground(new java.awt.Color(255, 255, 255));
        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel1.add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, -1, -1));

        eliminar.setBackground(new java.awt.Color(255, 0, 0));
        eliminar.setForeground(new java.awt.Color(255, 255, 255));
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, -1, -1));

        limpiar.setBackground(new java.awt.Color(53, 140, 198));
        limpiar.setForeground(new java.awt.Color(255, 255, 255));
        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        jPanel1.add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 200, -1, -1));

        Telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 300, -1));

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 300, -1));

        Apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoActionPerformed(evt);
            }
        });
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 300, -1));

        CI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CIActionPerformed(evt);
            }
        });
        jPanel1.add(CI, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 300, -1));

        jLabel7.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel7.setText("Teléfono");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, -1, 30));
        jPanel1.add(RU, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 300, -1));

        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });
        jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, 10, -1));

        HabilitarDeshabilitar.setBackground(new java.awt.Color(53, 140, 198));
        HabilitarDeshabilitar.setForeground(new java.awt.Color(255, 255, 255));
        HabilitarDeshabilitar.setText("Habilitar/Deshabilitar");
        HabilitarDeshabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HabilitarDeshabilitarActionPerformed(evt);
            }
        });
        jPanel1.add(HabilitarDeshabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 200, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 1120, 270));

        TablaTecnicoEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RU", "Nombre", "Apellido", "CI", "Teléfono", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        TablaTecnicoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaTecnicoEquipoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaTecnicoEquipo);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 1120, 360));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 80));

        ListaTecnicos.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaTecnicos.setText("Lista de Personal de Mantenimiento de Equipos");
        getContentPane().add(ListaTecnicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 500, -1));

        AgregarTecnico.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        AgregarTecnico.setText("Agregar Personal de Mantenimiento de Equipos");
        getContentPane().add(AgregarTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 1250, 730));

        Izquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Recuadro azul.png"))); // NOI18N
        getContentPane().add(Izquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 870));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) TablaTecnicoEquipo.getModel();
        modeloTabla.setRowCount(0);
        
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        int[] anchos = {10, 50, 100, 100, 80, 90, 50};
        for (int i = 0; i < TablaTecnicoEquipo.getColumnCount(); i++) {
            TablaTecnicoEquipo.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        try {
            Connection con = Conexion.obtenerConexion();
            ps = con.prepareStatement(
                    "SELECT t.id_tecnico_equipos, t.RU, t.nombre, t.apellido, t.CI, t.telefono, u.activo "
                    + "FROM tecnico_equipos t "
                    + "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario"
            );
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas -1; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }
                // activo o inactivo
                fila[columnas - 1] = rs.getInt("activo") == 1 ? "Activo" : "Inactivo";
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        String ruText = RU.getText();
        String nombre = Nombre.getText();
        String apellido = Apellido.getText();
        String ciText = CI.getText();
        String telefonoText = Telefono.getText();

        if (ruText.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || ciText.isEmpty() || telefonoText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
            return;
        }
        
        int ru = Integer.parseInt(ruText);
        int ci = Integer.parseInt(ciText);
        int telefono = Integer.parseInt(telefonoText);

        //Usuario y contra

        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña");
        String rol = "Tecnico de Mantenimientos";
        int idUsuario = 0;
        int activo = 1;

        try{
            Connection con = Conexion.obtenerConexion();

            PreparedStatement psUsuario = con.prepareStatement("INSERT INTO usuarios(username, contrasena, rol, activo) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, username);
            psUsuario.setString(2, contrasena);
            psUsuario.setString(3, rol);
            psUsuario.setInt(4, activo);

            psUsuario.executeUpdate();

            ResultSet rs = psUsuario.getGeneratedKeys();
            if(rs.next()){
                idUsuario = rs.getInt(1);
            }else{
                JOptionPane.showMessageDialog(null,"Error al obtener el ID del usuario.");
                return;
            }

            PreparedStatement pTecnicoEquipos = con.prepareStatement("INSERT INTO tecnico_equipos(id_usuario, RU, nombre, apellido, CI, telefono) VALUES(?,?,?,?,?,?)");
            pTecnicoEquipos.setInt(1, idUsuario);
            pTecnicoEquipos.setInt(2, ru);
            pTecnicoEquipos.setString(3, nombre);
            pTecnicoEquipos.setString(4, apellido);
            pTecnicoEquipos.setInt(5, ci);
            pTecnicoEquipos.setInt(6, telefono);

            pTecnicoEquipos.executeUpdate();
            JOptionPane.showMessageDialog(null, "REGISTRO GUARDADO");

            AdministradorTecnicoEquipos interfaz = new AdministradorTecnicoEquipos();
            interfaz.setVisible(true);

            Limpiar();
            cargarTabla();
            
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }

    }//GEN-LAST:event_guardarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        if (ID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un técnico antes de modificar.");
            return; 
        }
        
        int idTecnicoEquipos = Integer.parseInt(ID.getText());
        int ru = Integer.parseInt(RU.getText());
        String nombre = Nombre.getText();
        String apellido = Apellido.getText();
        int ci = Integer.parseInt(CI.getText());
        int telefono = Integer.parseInt(Telefono.getText());

        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");
        String rol ="Tecnico de Mantenimientos";
        int activo = 1;
        try{
            Connection con = Conexion.obtenerConexion();

            PreparedStatement psSelect = con.prepareStatement("SELECT id_usuario FROM tecnico_equipos WHERE id_tecnico_equipos = ?");
            psSelect.setInt(1, idTecnicoEquipos);
            ResultSet rs= psSelect.executeQuery();

            int idUsuario = 0;
            if(rs.next()){
                idUsuario = rs.getInt("id_usuario");
            }else{
                JOptionPane.showMessageDialog(null, "Técnico no encontrado.");
                return;
            }

            PreparedStatement psUsuario = con.prepareStatement("UPDATE usuarios SET username=?, contrasena=?, rol=?, activo=? WHERE id_usuario=?");
            psUsuario.setString(1, username);
            psUsuario.setString(2, contrasena);
            psUsuario.setString(3, rol);
            psUsuario.setInt(4, activo);
            psUsuario.setInt(5, idUsuario);
            
            psUsuario.executeUpdate();

            PreparedStatement psTecnicoEquipos = con.prepareStatement("UPDATE tecnico_equipos SET RU=?, nombre=?, apellido=?, CI=?, telefono=? WHERE id_tecnico_equipos = ?");
            psTecnicoEquipos.setInt(1, ru);
            psTecnicoEquipos.setString(2, nombre);
            psTecnicoEquipos.setString(3, apellido);
            psTecnicoEquipos.setInt(4, ci);
            psTecnicoEquipos.setInt(5, telefono);
            psTecnicoEquipos.setInt(6, idTecnicoEquipos);

            psTecnicoEquipos.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "REGISTRO MODIFICADO");
            Limpiar();
            cargarTabla();
        }catch(SQLException ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_modificarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (ID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un técnico a eliminar");
            return;
        }
        
        int tecnicoEquiposId = Integer.parseInt(ID.getText());

        try{
            Connection con = Conexion.obtenerConexion();
            PreparedStatement psObtenerUsuario = con.prepareStatement("SELECT id_usuario FROM tecnico_equipos WHERE id_tecnico_equipos=?");
            psObtenerUsuario.setInt(1, tecnicoEquiposId);
            ResultSet rs = psObtenerUsuario.executeQuery();

            if(rs.next()){
                int usuarioId = rs.getInt("id_usuario");

                PreparedStatement psTecnico = con.prepareStatement("DELETE FROM tecnico_equipos WHERE id_tecnico_equipos=?");
                psTecnico.setInt(1,tecnicoEquiposId);
                psTecnico.executeUpdate();

                PreparedStatement psUsuario = con.prepareStatement("DELETE FROM usuarios WHERE id_usuario=?");
                psUsuario.setInt(1,usuarioId);
                psUsuario.executeUpdate();

                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                Limpiar();
            }else{
                JOptionPane.showMessageDialog(null,"No se encontro al técnico");
            }
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_limpiarActionPerformed
    private void Limpiar(){
        RU.setText("");
        Nombre.setText("");
        Apellido.setText("");
        CI.setText("");
        Telefono.setText("");
    }
    private void TelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefonoActionPerformed

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void ApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoActionPerformed

    private void CIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CIActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        ID.setVisible(false);
    }//GEN-LAST:event_IDActionPerformed

    private void HabilitarDeshabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HabilitarDeshabilitarActionPerformed
        int fila = TablaTecnicoEquipo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un Tecnico.");
            return;
        }

        int tecnicoId = Integer.parseInt(TablaTecnicoEquipo.getValueAt(fila, 0).toString());

        try {
            Connection con = Conexion.obtenerConexion();

            // Obtener el id_usuario 
            PreparedStatement psObtenerUsuario = con.prepareStatement("SELECT id_usuario FROM tecnico_equipos WHERE id_tecnico_equipos=?");
            psObtenerUsuario.setInt(1, tecnicoId);
            ResultSet rsUsuario = psObtenerUsuario.executeQuery();

            if (!rsUsuario.next()) {
                JOptionPane.showMessageDialog(null, "Error al encontrar el usuario asociado al técnico.");
                return;
            }

            int idUsuario = rsUsuario.getInt("id_usuario");

       
            PreparedStatement psEstado = con.prepareStatement("SELECT activo FROM usuarios WHERE id_usuario=?");
            psEstado.setInt(1, idUsuario);
            ResultSet rsEstado = psEstado.executeQuery();

            if (!rsEstado.next()) {
                JOptionPane.showMessageDialog(null, "Error al encontrar el estado del usuario.");
                return;
            }

            int estadoActual = rsEstado.getInt("activo");

            
            int nuevoEstado = (estadoActual == 1) ? 0 : 1;

            
            PreparedStatement psActualizar = con.prepareStatement("UPDATE usuarios SET activo=? WHERE id_usuario=?");
            psActualizar.setInt(1, nuevoEstado);
            psActualizar.setInt(2, idUsuario);
            psActualizar.executeUpdate();

           
            String mensaje = (nuevoEstado == 1) ? "Técnico habilitado." : "Técnico deshabilitado.";
            JOptionPane.showMessageDialog(null, mensaje);

       
            cargarTabla();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_HabilitarDeshabilitarActionPerformed

    private void TablaTecnicoEquipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaTecnicoEquipoMouseClicked
        try {
            int fila = TablaTecnicoEquipo.getSelectedRow();
            int id = Integer.parseInt(TablaTecnicoEquipo.getValueAt(fila, 0).toString());

            PreparedStatement ps;
            ResultSet rs;

            Connection con = Conexion.obtenerConexion();
            ps = con.prepareStatement("SELECT RU, nombre, apellido, CI, telefono FROM tecnico_equipos WHERE id_tecnico_equipos=?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                ID.setText(String.valueOf(id));
                RU.setText(rs.getString("RU"));
                Nombre.setText(rs.getString("nombre"));
                Apellido.setText(rs.getString("apellido"));
                CI.setText(rs.getString("CI"));
                Telefono.setText(rs.getString("telefono"));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_TablaTecnicoEquipoMouseClicked

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
            java.util.logging.Logger.getLogger(AdministradorTecnicoEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministradorTecnicoEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministradorTecnicoEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministradorTecnicoEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministradorTecnicoEquipos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AgregarTecnico;
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField CI;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JButton HabilitarDeshabilitar;
    private javax.swing.JTextField ID;
    private javax.swing.JLabel Izquierda;
    private javax.swing.JLabel ListaTecnicos;
    private javax.swing.JLabel LogoSale;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField RU;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaTecnicoEquipo;
    private javax.swing.JTextField Telefono;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton modificar;
    // End of variables declaration//GEN-END:variables
}
