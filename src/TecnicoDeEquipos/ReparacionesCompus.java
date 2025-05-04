/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TecnicoDeEquipos;

import ConexionLogin.Conexion;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class ReparacionesCompus extends javax.swing.JFrame {


    /**
     * Creates new form ReparacionesCompus
     
     */
    public ReparacionesCompus() {
        initComponents();
        Nombre.setEditable(false);
        this.setLocationRelativeTo(null);
        cargarTablaLista();
        FondoBlanco.setFocusable(true);
        FondoBlanco.requestFocusInWindow();

        panelOverlay.setVisible(false);
        panelOverlay.setBackground(new Color(0, 0, 0, 0));

        panelSidebar.setVisible(false);
        panelSidebar.setLocation(-250, 0);

        panelOverlay.addMouseListener(new java.awt.event.MouseAdapter() {});
        panelOverlay.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {});
        
        panelOverlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();

            int sidebarX = panelSidebar.getX();
            int sidebarY = panelSidebar.getY();
            int sidebarWidth = panelSidebar.getWidth();
            int sidebarHeight = panelSidebar.getHeight();

            boolean clicFueraSidebar = !(x >= sidebarX && x <= (sidebarX + sidebarWidth)
                                      && y >= sidebarY && y <= (sidebarY + sidebarHeight));

                if (clicFueraSidebar) {
                    cerrarSidebar(); // ejecuta la animación
                }
            }
            
        });
        
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("ESCAPE"), "cerrarSidebar");

        getRootPane().getActionMap().put("cerrarSidebar", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (panelSidebar.isVisible()) {
                    cerrarSidebar();
                }
            }
        });

        this.setLocationRelativeTo(null);

    }
private boolean sidebarMostrado = false;
    private Timer animacion;
    private boolean sidebarListo = false;
    
    private void mostrarSidebar() {
    panelOverlay.setVisible(true);
    sidebarMostrado = true;
    panelSidebar.setLocation(-250, 0);

    animacion = new Timer(5, new ActionListener() {
        int x = panelSidebar.getX();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (x < 0) {
                x += 10;
                panelSidebar.setLocation(x, 0);
            } else {
                panelSidebar.setLocation(0, 0);
                animacion.stop();
            }
        }
    });
    animacion.start();
}

    
    private void cerrarSidebar() {
    new Thread(() -> {
        int duracion = 150;
        int pasos = 25;
        int delay = duracion / pasos;

        for (int i = pasos; i >= 0; i--) {
            int x = -250 + (i * 10);
            int alpha = (int)(i * (120.0 / pasos));

            panelSidebar.setLocation(x, 0);
            panelOverlay.setBackground(new Color(0, 0, 0, alpha));

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        panelSidebar.setVisible(false);
        panelOverlay.setVisible(false);
    }).start();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSidebar = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnMantenimientoEquipo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        panelOverlay = new javax.swing.JLayeredPane();
        btnMenu = new javax.swing.JButton();
        perfil = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        CompusArregladas = new javax.swing.JLabel();
        ListaCompus1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Fecha = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Descripcion = new javax.swing.JTextArea();
        Guardar = new javax.swing.JButton();
        Nombre = new javax.swing.JTextField();
        BuscarRu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCompusArregladas = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaComputadoras = new javax.swing.JTable();
        FondoGris1 = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Panel de Control");
        panelSidebar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        btnInicio.setBackground(new java.awt.Color(29, 41, 57));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(241, 241, 241));
        btnInicio.setText("INICIO");
        btnInicio.setBorder(null);
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
            }
        });
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        panelSidebar.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 229, 40));

        btnMantenimientoEquipo.setBackground(new java.awt.Color(29, 41, 57));
        btnMantenimientoEquipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMantenimientoEquipo.setForeground(new java.awt.Color(241, 241, 241));
        btnMantenimientoEquipo.setText("Mantenimiento de Equipos");
        btnMantenimientoEquipo.setBorder(null);
        btnMantenimientoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMantenimientoEquipoMouseExited(evt);
            }
        });
        btnMantenimientoEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoEquipoActionPerformed(evt);
            }
        });
        panelSidebar.add(btnMantenimientoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 229, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 820, -1, 40));

        btnCerrarSesion.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseExited(evt);
            }
        });
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        panelSidebar.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 820, 229, 40));

        LogoSale1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoUSB.png"))); // NOI18N
        panelSidebar.add(LogoSale1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 160, 60));

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 860));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 860));

        btnMenu.setBackground(new java.awt.Color(178, 191, 207));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonBurger3.png"))); // NOI18N
        btnMenu.setBorder(null);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        getContentPane().add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 15, 30, 30));

        perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

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
        jPanel1.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 300, -1));

        jLabel10.setText("Descripcion:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 70, 20));

        jLabel11.setText("Fecha:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 40, 20));

        jLabel12.setText("Nombre:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        Descripcion.setColumns(20);
        Descripcion.setRows(5);
        jScrollPane2.setViewportView(Descripcion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 400, 120));

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 300, -1));

        BuscarRu.setText("Buscar");
        BuscarRu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarRuActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarRu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

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
        tblListaComputadoras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaComputadorasmouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaComputadoras);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1030, 340));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 920));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 10, 630));

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
                    rs.getString("estado"),};
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    private void cargarTablaReparado() {
        DefaultTableModel modelo = (DefaultTableModel) tblCompusArregladas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT m.id_mantenimiento, mh.nombre AS material_nombre, l.Codigo_lab, "
                    + "mh.estado, m.fecha, m.descripcion, CONCAT(te.nombre, ' ', te.apellido) AS tecnico_nombre "
                    + "FROM mantenimiento m "
                    + "JOIN materiales_hardware mh ON m.id_material_hardware = mh.id_material_hardware "
                    + "JOIN laboratorios l ON mh.ID_lab = l.ID_lab "
                    + "JOIN tecnico_equipos te ON m.id_tecnico_equipos = te.id_tecnico_equipos "
                    + "ORDER BY m.fecha DESC"
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_mantenimiento"),
                    rs.getString("material_nombre"), // nombre del material
                    rs.getString("Codigo_lab"),
                    rs.getString("estado"),
                    rs.getDate("fecha"),
                    rs.getString("descripcion"),
                    rs.getString("tecnico_nombre") // nombre + apellido del técnico
                };
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar datos reparados: " + ex.toString());
        }
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
        String nombreCompleto = Nombre.getText().trim(); // Ejemplo: "Juan Pérez"
        Date fecha = Fecha.getDate();
        String descripcion = Descripcion.getText();

        // Validación de campos vacíos
        if (nombreCompleto.isEmpty() || fecha == null || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.");
            return;
        }

        int idMaterial = obtenerIdMaterialSeleccionado();
        if (idMaterial == -1) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un material de la tabla.");
            return;
        }

        java.sql.Timestamp fechaSQL = new java.sql.Timestamp(fecha.getTime());

        try {
            Connection con = Conexion.obtenerConexion();

            // Buscar el ID del técnico usando CONCAT(nombre, ' ', apellido)
            PreparedStatement psBuscarTecnico = con.prepareStatement(
                    "SELECT id_tecnico_equipos FROM tecnico_equipos WHERE CONCAT(nombre, ' ', apellido) = ?"
            );
            psBuscarTecnico.setString(1, nombreCompleto);
            ResultSet rs = psBuscarTecnico.executeQuery();

            if (rs.next()) {
                int idTecnico = rs.getInt("id_tecnico_equipos");

                // Insertar mantenimiento
                PreparedStatement psInsert = con.prepareStatement(
                        "INSERT INTO mantenimiento (id_material_hardware, fecha, descripcion, id_tecnico_equipos) VALUES (?, ?, ?, ?)"
                );
                psInsert.setInt(1, idMaterial);
                psInsert.setTimestamp(2, fechaSQL);
                psInsert.setString(3, descripcion);
                psInsert.setInt(4, idTecnico);

                int insertado = psInsert.executeUpdate();

                if (insertado > 0) {
                    // Actualizar estado del material
                    PreparedStatement psUpdate = con.prepareStatement(
                            "UPDATE materiales_hardware SET estado = 'Disponible' WHERE id_material_hardware = ?"
                    );
                    psUpdate.setInt(1, idMaterial);
                    psUpdate.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Reparación registrada correctamente y estado actualizado a 'Disponible'.");
                    cargarTablaLista();
                    cargarTablaReparado();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar la reparación.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Técnico no encontrado. Verifique el nombre y apellido.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar reparación: " + e.getMessage());
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void tblListaComputadorasmouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaComputadorasmouseClicked
        int filaSeleccionada = tblListaComputadoras.rowAtPoint(evt.getPoint());
        if (filaSeleccionada != -1) {
            int idMaterial = (int) tblListaComputadoras.getValueAt(filaSeleccionada, 0);
            
        }
    }//GEN-LAST:event_tblListaComputadorasmouseClicked

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void BuscarRuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarRuActionPerformed
        VerTecnicosEquipos ver = new VerTecnicosEquipos(Nombre);
        ver.setLocationRelativeTo(null);
        ver.setVisible(true);
    }//GEN-LAST:event_BuscarRuActionPerformed

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnMantenimientoEquipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoEquipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMantenimientoEquipoMouseExited

    private void btnMantenimientoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMantenimientoEquipoActionPerformed

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited

    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        panelOverlay.setVisible(true);

        panelSidebar.setVisible(true);
        panelSidebar.setLocation(-250, 0);

        getContentPane().revalidate();
        getContentPane().repaint();

        new Thread(() -> {
            int duracion = 150;
            int pasos = 25;
            int delay = duracion / pasos;

            for (int i = 0; i <= pasos; i++) {
                int x = -250 + (i * 10);
                int alpha = (int) (i * (120.0 / pasos));

                panelSidebar.setLocation(x, 0);

                Color overlayColor = new Color(0, 0, 0, alpha);
                panelOverlay.setBackground(overlayColor);

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_btnMenuActionPerformed


    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
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
    private javax.swing.JButton BuscarRu;
    private javax.swing.JLabel CompusArregladas;
    private javax.swing.JTextArea Descripcion;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel ListaCompus1;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel Superior;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMantenimientoEquipo;
    private javax.swing.JButton btnMenu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    private javax.swing.JTable tblCompusArregladas;
    private javax.swing.JTable tblListaComputadoras;
    // End of variables declaration//GEN-END:variables
}
