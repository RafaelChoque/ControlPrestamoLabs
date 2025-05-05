package Administrador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import ConexionLogin.Login;
import ConexionLogin.Conexion;
import PersonalAcademico.InicioPersonalAcademico;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class AdministradorTecnicoPrestamo extends javax.swing.JFrame {

    /**
     * Creates ntralalero tralala
     */
    public AdministradorTecnicoPrestamo() {
        initComponents();
        ID.setVisible(false);
        this.setLocationRelativeTo(null);
        cargarTabla();
        FondoBlanco.setFocusable(true);
        FondoBlanco.requestFocusInWindow();

        panelOverlay.setVisible(false);
        panelOverlay.setBackground(new Color(0, 0, 0, 0));

        panelSidebar.setVisible(false);
        panelSidebar.setLocation(-250, 0);
        panelOverlay.addMouseListener(new java.awt.event.MouseAdapter() {
        });
        panelOverlay.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        });
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
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnCerrarSesion1 = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        btnInicio2 = new javax.swing.JButton();
        btnPersonalAcademico = new javax.swing.JButton();
        btnTecnicoPrestamo = new javax.swing.JButton();
        btnTecnicoEquipo = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();
        btnMenu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        FondoBlanco1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        txtCI = new javax.swing.JLabel();
        txtRU = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        Telefono = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        CI = new javax.swing.JTextField();
        RU = new javax.swing.JTextField();
        ID = new javax.swing.JTextField();
        HabilitarDeshabilitar = new javax.swing.JButton();
        AgregarTecnico = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTecnicoPrestamo = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ListaTecnicos = new javax.swing.JLabel();
        perfil1 = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Panel de Control");
        panelSidebar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 820, -1, 40));

        btnCerrarSesion1.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion1.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion1.setText("Cerrar Sesión");
        btnCerrarSesion1.setBorder(null);
        btnCerrarSesion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesion1MouseExited(evt);
            }
        });
        btnCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion1ActionPerformed(evt);
            }
        });
        panelSidebar.add(btnCerrarSesion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 820, 229, 40));

        LogoSale1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoUSB.png"))); // NOI18N
        panelSidebar.add(LogoSale1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 160, 60));

        btnInicio2.setBackground(new java.awt.Color(29, 41, 57));
        btnInicio2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInicio2.setForeground(new java.awt.Color(241, 241, 241));
        btnInicio2.setText("INICIO");
        btnInicio2.setBorder(null);
        btnInicio2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicio2MouseExited(evt);
            }
        });
        btnInicio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicio2ActionPerformed(evt);
            }
        });
        panelSidebar.add(btnInicio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 229, 40));

        btnPersonalAcademico.setBackground(new java.awt.Color(29, 41, 57));
        btnPersonalAcademico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPersonalAcademico.setForeground(new java.awt.Color(241, 241, 241));
        btnPersonalAcademico.setText("Personal Academico");
        btnPersonalAcademico.setBorder(null);
        btnPersonalAcademico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPersonalAcademicoMouseExited(evt);
            }
        });
        btnPersonalAcademico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalAcademicoActionPerformed(evt);
            }
        });
        panelSidebar.add(btnPersonalAcademico, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 229, 40));

        btnTecnicoPrestamo.setBackground(new java.awt.Color(29, 41, 57));
        btnTecnicoPrestamo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTecnicoPrestamo.setForeground(new java.awt.Color(241, 241, 241));
        btnTecnicoPrestamo.setText("Tecnico Prestamos");
        btnTecnicoPrestamo.setBorder(null);
        btnTecnicoPrestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTecnicoPrestamoMouseExited(evt);
            }
        });
        btnTecnicoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTecnicoPrestamoActionPerformed(evt);
            }
        });
        panelSidebar.add(btnTecnicoPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 229, 40));

        btnTecnicoEquipo.setBackground(new java.awt.Color(29, 41, 57));
        btnTecnicoEquipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTecnicoEquipo.setForeground(new java.awt.Color(241, 241, 241));
        btnTecnicoEquipo.setText("Tecnico Equipo");
        btnTecnicoEquipo.setBorder(null);
        btnTecnicoEquipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTecnicoEquipoMouseExited(evt);
            }
        });
        btnTecnicoEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTecnicoEquipoActionPerformed(evt);
            }
        });
        panelSidebar.add(btnTecnicoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 229, 40));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FondoBlanco1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 450, 180, 160));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setText("Nombre:");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        txtApellido.setText("Apellido:");
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        txtCI.setText("CI:");
        jPanel1.add(txtCI, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        txtRU.setText("RU:");
        jPanel1.add(txtRU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        txtTelefono.setText("Teléfono:");
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        guardar.setBackground(new java.awt.Color(29, 41, 57));
        guardar.setForeground(new java.awt.Color(255, 255, 255));
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        modificar.setBackground(new java.awt.Color(29, 41, 57));
        modificar.setForeground(new java.awt.Color(255, 255, 255));
        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel1.add(modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        eliminar.setBackground(new java.awt.Color(255, 0, 0));
        eliminar.setForeground(new java.awt.Color(255, 255, 255));
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));

        limpiar.setBackground(new java.awt.Color(29, 41, 57));
        limpiar.setForeground(new java.awt.Color(255, 255, 255));
        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        jPanel1.add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, -1, -1));

        Telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 430, -1));

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 430, -1));

        Apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoActionPerformed(evt);
            }
        });
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 430, -1));

        CI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CIActionPerformed(evt);
            }
        });
        jPanel1.add(CI, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 430, -1));
        jPanel1.add(RU, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 430, -1));

        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });
        jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 10, -1));

        HabilitarDeshabilitar.setBackground(new java.awt.Color(29, 41, 57));
        HabilitarDeshabilitar.setForeground(new java.awt.Color(255, 255, 255));
        HabilitarDeshabilitar.setText("Habilitar/Deshabilitar");
        HabilitarDeshabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HabilitarDeshabilitarActionPerformed(evt);
            }
        });
        jPanel1.add(HabilitarDeshabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        AgregarTecnico.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        AgregarTecnico.setText("Agregar Técnico de Prestamos");
        jPanel1.add(AgregarTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, 550, 250));

        TablaTecnicoPrestamo.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaTecnicoPrestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaTecnicoPrestamoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaTecnicoPrestamo);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 870, 610));

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
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 90, 20));
        String placeholder = "Buscar RU";

        jTextField1.setText(placeholder);
        jTextField1.setForeground(Color.GRAY);

        jTextField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField1.getText().equals(placeholder)) {
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.BLACK);
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

                if (query.equals(placeholder.toLowerCase())) {
                    TablaTecnicoPrestamo.setRowSorter(null);
                    return;
                }

                TableRowSorter<TableModel> sorter = new TableRowSorter<>(TablaTecnicoPrestamo.getModel());
                TablaTecnicoPrestamo.setRowSorter(sorter);

                if (query.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 1, 3));
                }
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 190, 40));

        ListaTecnicos.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaTecnicos.setText("Lista de Tecnicos de Prestamos");
        jPanel2.add(ListaTecnicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1490, 760));

        perfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 560, 210, 280));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void TablaTecnicoPrestamoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaTecnicoPrestamoMouseClicked
        try {
            int fila = TablaTecnicoPrestamo.getSelectedRow();
            int id = Integer.parseInt(TablaTecnicoPrestamo.getValueAt(fila, 0).toString());

            PreparedStatement ps;
            ResultSet rs;

            Connection con = Conexion.obtenerConexion();
            ps = con.prepareStatement("SELECT RU, nombre, apellido, CI, telefono FROM tecnico_prestamos WHERE id_tecnico_prestamos=?");

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
    }//GEN-LAST:event_TablaTecnicoPrestamoMouseClicked
 
    private void cargarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) TablaTecnicoPrestamo.getModel();
        modeloTabla.setRowCount(0);
        
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        int[] anchos = {10, 50, 100, 100, 80, 90, 50};
        for (int i = 0; i < TablaTecnicoPrestamo.getColumnCount(); i++) {
            TablaTecnicoPrestamo.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        try {
            Connection con = Conexion.obtenerConexion();
            ps = con.prepareStatement(
                    "SELECT t.id_tecnico_prestamos, t.RU, t.nombre, t.apellido, t.CI, t.telefono, u.activo "
                    + "FROM tecnico_prestamos t "
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
        String rol = "Tecnico de Prestamos";
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
            
            PreparedStatement psTecnicoPrestamo = con.prepareStatement("INSERT INTO tecnico_prestamos(id_usuario, RU, nombre, apellido, CI, telefono) VALUES(?,?,?,?,?,?)");
            psTecnicoPrestamo.setInt(1, idUsuario);
            psTecnicoPrestamo.setInt(2, ru);
            psTecnicoPrestamo.setString(3, nombre);
            psTecnicoPrestamo.setString(4, apellido);
            psTecnicoPrestamo.setInt(5, ci);
            psTecnicoPrestamo.setInt(6, telefono);
            
            psTecnicoPrestamo.executeUpdate();
            JOptionPane.showMessageDialog(null, "REGISTRO GUARDADO");
            
            AdministradorTecnicoPrestamo interfaz = new AdministradorTecnicoPrestamo();
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
        
        int idTecnicoPrestamo = Integer.parseInt(ID.getText());
        int ru = Integer.parseInt(RU.getText());
        String nombre = Nombre.getText();
        String apellido = Apellido.getText();
        int ci = Integer.parseInt(CI.getText());
        int telefono = Integer.parseInt(Telefono.getText());
        
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");
        String rol ="Tecnico de Prestamos";
        int activo = 1;
        try{
            Connection con = Conexion.obtenerConexion();
            
            PreparedStatement psSelect = con.prepareStatement("SELECT id_usuario FROM tecnico_prestamos WHERE id_tecnico_prestamos = ?");
            psSelect.setInt(1, idTecnicoPrestamo);
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
            
            PreparedStatement psTecnicoPrestamo = con.prepareStatement("UPDATE tecnico_prestamos SET RU=?, nombre=?, apellido=?, CI=?, telefono=? WHERE id_tecnico_prestamos = ?");
            psTecnicoPrestamo.setInt(1, ru);
            psTecnicoPrestamo.setString(2, nombre);
            psTecnicoPrestamo.setString(3, apellido);
            psTecnicoPrestamo.setInt(4, ci);
            psTecnicoPrestamo.setInt(5, telefono);
            psTecnicoPrestamo.setInt(6, idTecnicoPrestamo);

            psTecnicoPrestamo.executeUpdate();
            
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
                
        int tecnicoPrestamoId = Integer.parseInt(ID.getText());
        
        try{
            Connection con = Conexion.obtenerConexion();
            PreparedStatement psObtenerUsuario = con.prepareStatement("SELECT id_usuario FROM tecnico_prestamos WHERE id_tecnico_prestamos=?");
            psObtenerUsuario.setInt(1, tecnicoPrestamoId);
            ResultSet rs = psObtenerUsuario.executeQuery();
            
            if(rs.next()){
                int usuarioId = rs.getInt("id_usuario");
                
                PreparedStatement psTecnico = con.prepareStatement("DELETE FROM tecnico_prestamos WHERE id_tecnico_prestamos=?");
                psTecnico.setInt(1,tecnicoPrestamoId);
                psTecnico.executeUpdate();
                
                PreparedStatement psUsuario = con.prepareStatement("DELETE FROM usuarios WHERE id_usuario=?");
                psUsuario.setInt(1,usuarioId);
                psUsuario.executeUpdate();
            
                JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO");
                Limpiar();
                cargarTabla();
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
        int fila = TablaTecnicoPrestamo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un Tecnico.");
            return;
        }

        int tecnicoId = Integer.parseInt(TablaTecnicoPrestamo.getValueAt(fila, 0).toString());

        try {
            Connection con = Conexion.obtenerConexion();

            // Obtener el id_usuario 
            PreparedStatement psObtenerUsuario = con.prepareStatement("SELECT id_usuario FROM tecnico_prestamos WHERE id_tecnico_prestamos=?");
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnCerrarSesion1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesion1MouseExited

    }//GEN-LAST:event_btnCerrarSesion1MouseExited

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
        Login cerrar = new Login();
        cerrar.setLocationRelativeTo(null);
        cerrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesion1ActionPerformed

    private void btnInicio2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicio2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicio2MouseExited

    private void btnInicio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicio2ActionPerformed
        InicioAdministrador inicio = new InicioAdministrador();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicio2ActionPerformed

    private void btnPersonalAcademicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPersonalAcademicoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPersonalAcademicoMouseExited

    private void btnPersonalAcademicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalAcademicoActionPerformed
        AdministradorPersonalAcademico admin = new AdministradorPersonalAcademico();
        admin.setLocationRelativeTo(null); 
        admin.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnPersonalAcademicoActionPerformed

    private void btnTecnicoPrestamoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTecnicoPrestamoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTecnicoPrestamoMouseExited

    private void btnTecnicoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecnicoPrestamoActionPerformed
        AdministradorTecnicoPrestamo admin = new AdministradorTecnicoPrestamo();
        admin.setLocationRelativeTo(null); 
        admin.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnTecnicoPrestamoActionPerformed

    private void btnTecnicoEquipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTecnicoEquipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTecnicoEquipoMouseExited

    private void btnTecnicoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecnicoEquipoActionPerformed
        AdministradorTecnicoEquipos admin = new AdministradorTecnicoEquipos();
        admin.setLocationRelativeTo(null); 
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTecnicoEquipoActionPerformed

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
                int alpha = (int)(i * (120.0 / pasos)); 

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
                new AdministradorTecnicoPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AgregarTecnico;
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField CI;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoBlanco1;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JButton HabilitarDeshabilitar;
    private javax.swing.JTextField ID;
    private javax.swing.JLabel ListaTecnicos;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField RU;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaTecnicoPrestamo;
    private javax.swing.JTextField Telefono;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnInicio2;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnPersonalAcademico;
    private javax.swing.JButton btnTecnicoEquipo;
    private javax.swing.JButton btnTecnicoPrestamo;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton modificar;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil1;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JLabel txtCI;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtRU;
    private javax.swing.JLabel txtTelefono;
    // End of variables declaration//GEN-END:variables
}
