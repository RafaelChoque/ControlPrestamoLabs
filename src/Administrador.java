/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Rafael
 */
public class Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Administrador
     */
    public Administrador() {
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

        AgregarTecnico = new javax.swing.JLabel();
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
        medicos = new javax.swing.JTable();
        FondoGris = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AgregarTecnico.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        AgregarTecnico.setText("Agregar Técnico");
        getContentPane().add(AgregarTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

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

        medicos.setModel(new javax.swing.table.DefaultTableModel(
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
        medicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(medicos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 1120, 360));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_1.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void medicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicosMouseClicked

    }//GEN-LAST:event_medicosMouseClicked

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed

    }//GEN-LAST:event_guardarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed

    }//GEN-LAST:event_modificarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed

    }//GEN-LAST:event_eliminarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed

    }//GEN-LAST:event_limpiarActionPerformed

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

    }//GEN-LAST:event_HabilitarDeshabilitarActionPerformed

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
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AgregarTecnico;
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField CI;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JButton HabilitarDeshabilitar;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField RU;
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
    private javax.swing.JTable medicos;
    private javax.swing.JButton modificar;
    // End of variables declaration//GEN-END:variables
}
