package PersonalAcademico;

import ConexionLogin.Conexion;
import ConexionLogin.Login;
import Sanciones.SancionesRecibidaPersonal;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Rafael
 */
public class FormularioPrestamo extends javax.swing.JFrame {

    private int idusuario;

    /**
     * Creates new form FormularioPrestamo
     *
     * @param idusuario
     */
    public FormularioPrestamo(int idusuario) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.idusuario = idusuario;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Nombre.setEditable(false);
        Apellido.setEditable(false);
        Laboratorio.setEditable(false);
        HorarioFijo.setVisible(false);
        HorarioPersonalizadoInicio.setVisible(false);
        HorarioPersonalizadoFin.setVisible(false);

        cargarTabla(idusuario);
        cargarTabla2();
        cargarNombreApellido(idusuario);

        HorarioPersonalizadoInicio.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(HorarioPersonalizadoInicio, "HH:mm");
        HorarioPersonalizadoInicio.setEditor(editorInicio);

        HorarioPersonalizadoFin.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(HorarioPersonalizadoFin, "HH:mm");
        HorarioPersonalizadoFin.setEditor(editorFin);
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
        this.setLocationRelativeTo(null);
    }

    private boolean sidebarMostrado = false;
    private Timer animacion;
    private boolean sidebarListo = false;

    FormularioPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
                int alpha = (int) (i * (120.0 / pasos));

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
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnSolicitudLab = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        Sanciones = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Motivo = new javax.swing.JTextField();
        Fecha = new com.toedter.calendar.JDateChooser();
        Bloque = new javax.swing.JComboBox<>();
        guardar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        Formulario = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Seccion = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Laboratorio = new javax.swing.JTextField();
        DisponibilidadPrestamo = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TipoHorario = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        HorarioFijo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        HorarioPersonalizadoInicio = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        HorarioPersonalizadoFin = new javax.swing.JSpinner();
        btnMaterialExtra = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        ListaPersonal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();
        Actualizar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        MotivoRechazo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPrestamos2 = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        Superior = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Panel de Control");
        panelSidebar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

        btnSolicitudLab.setBackground(new java.awt.Color(29, 41, 57));
        btnSolicitudLab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSolicitudLab.setForeground(new java.awt.Color(241, 241, 241));
        btnSolicitudLab.setText("Solicitud de Laboratorios");
        btnSolicitudLab.setBorder(null);
        btnSolicitudLab.setHorizontalAlignment(SwingConstants.LEFT);
        btnSolicitudLab.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnSolicitudLab.setIconTextGap(10);
        btnSolicitudLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSolicitudLabMouseExited(evt);
            }
        });
        btnSolicitudLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudLabActionPerformed(evt);
            }
        });
        panelSidebar.add(btnSolicitudLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 229, 40));

        btnCerrarSesion.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
        btnCerrarSesion.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnCerrarSesion.setIconTextGap(10);
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

        Sanciones.setBackground(new java.awt.Color(29, 41, 57));
        Sanciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Sanciones.setForeground(new java.awt.Color(255, 255, 255));
        Sanciones.setText("Sanciones");
        Sanciones.setBorder(null);
        Sanciones.setHorizontalAlignment(SwingConstants.LEFT);
        Sanciones.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        Sanciones.setIconTextGap(10);
        Sanciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SancionesActionPerformed(evt);
            }
        });
        panelSidebar.add(Sanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 230, 40));

        btnInicio.setBackground(new java.awt.Color(29, 41, 57));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(241, 241, 241));
        btnInicio.setText("INICIO");
        btnInicio.setBorder(null);
        btnInicio.setHorizontalAlignment(SwingConstants.LEFT);
        btnInicio.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnInicio.setIconTextGap(10);
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

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 870));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Motivo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel5.setText("Fecha:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jLabel7.setText("Apellido:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, 20));

        jLabel1.setText("Cantidad:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 190, -1));
        jPanel1.add(Motivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 480, -1));
        jPanel1.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 480, -1));

        Bloque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));
        Bloque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BloqueActionPerformed(evt);
            }
        });
        jPanel1.add(Bloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 480, -1));

        guardar.setBackground(new java.awt.Color(51, 153, 0));
        guardar.setForeground(new java.awt.Color(255, 255, 255));
        guardar.setText("Solicitar Laboratorio");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 150, -1));

        Limpiar.setBackground(new java.awt.Color(29, 41, 57));
        Limpiar.setForeground(new java.awt.Color(255, 255, 255));
        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 110, -1));

        Formulario.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Formulario.setText("Formulario de Solicitud");
        jPanel1.add(Formulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel9.setText("Bloque:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        Seccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Redes", "Telecomunicaciones", "Electronica" }));
        jPanel1.add(Seccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 480, -1));

        jLabel10.setText("Nombre:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        Apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoActionPerformed(evt);
            }
        });
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 190, -1));

        jLabel11.setText("Laboratorio:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        jLabel15.setText("Sección:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        Laboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaboratorioActionPerformed(evt);
            }
        });
        jPanel1.add(Laboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 300, -1));

        DisponibilidadPrestamo.setBackground(new java.awt.Color(29, 41, 57));
        DisponibilidadPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        DisponibilidadPrestamo.setText("Seleccionar Laboratorio");
        DisponibilidadPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisponibilidadPrestamoActionPerformed(evt);
            }
        });
        jPanel1.add(DisponibilidadPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 170, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 300, -1));

        jLabel8.setText("Horario:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));

        TipoHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fijo", "Personalizado"}));
        TipoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoHorarioActionPerformed(evt);
            }
        });
        jPanel1.add(TipoHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 300, -1));

        jLabel3.setText("Fijo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 20));

        HorarioFijo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:30 - 9:00", "9:15 - 10:45", "11:00 - 12:30" }));
        HorarioFijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HorarioFijoActionPerformed(evt);
            }
        });
        jPanel1.add(HorarioFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 110, -1));

        jLabel13.setText("Personalizado:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, -1, 20));
        jPanel1.add(HorarioPersonalizadoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 110, -1));

        jLabel2.setText("Hasta:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, 20));
        jPanel1.add(HorarioPersonalizadoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 110, -1));

        btnMaterialExtra.setBackground(new java.awt.Color(29, 41, 57));
        btnMaterialExtra.setForeground(new java.awt.Color(255, 255, 255));
        btnMaterialExtra.setText("Material Extra");
        btnMaterialExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaterialExtraActionPerformed(evt);
            }
        });
        jPanel1.add(btnMaterialExtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 170, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 610, 350));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ListaPersonal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaPersonal.setText("Historial de Prestamos");
        jPanel4.add(ListaPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, -1));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Laboratorio", "Nombre", "Apellido", "Fecha", "Horario Inicio", "Horario Fin", "Estado de Solicitud", "Motivo del Rechazo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHistorialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaHistorial);
        int[] anchos = {50, 100, 120, 200, 120, 120, 100, 130, 250};
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < anchos.length; i++) {
            tablaHistorial.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            tablaHistorial.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1420, 330));

        Actualizar.setBackground(new java.awt.Color(29, 41, 57));
        Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 10, 110, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 1440, 380));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MotivoRechazo.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        MotivoRechazo.setText("Descripción");
        jPanel2.add(MotivoRechazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TablaPrestamos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Motivo", "Bloque", "Seccion", "Tipo de  Horario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        int[]anchos2 = {50, 215, 70, 130, 130};
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < anchos2.length; i++) {
            TablaPrestamos2.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
            TablaPrestamos2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
        }
        jScrollPane2.setViewportView(TablaPrestamos2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 790, 290));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 810, 350));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel3.add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 10, 630));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1480, 770));

        perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

        btnMenu.setBackground(new java.awt.Color(178, 191, 207));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonBurger3.png"))); // NOI18N
        btnMenu.setBorder(null);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        getContentPane().add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 15, 30, 30));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void cargarNombreApellido(int idusuario) {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT nombre, apellido FROM personal_academico WHERE id_usuario = ?";

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

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        String motivo = Motivo.getText();
        Date fechaGeneral = Fecha.getDate();
        String laboratorioText = Laboratorio.getText();
        String tipoHorario = TipoHorario.getSelectedItem().toString();
        String horaInicio = "";
        String horaFin = "";

        if (motivo.isEmpty() || laboratorioText.isEmpty() || fechaGeneral == null) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.");
            return;
        }

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        if (tipoHorario.equals("Fijo")) {
            String horaSeleccionada = (String) HorarioFijo.getSelectedItem();
            if (horaSeleccionada == null || horaSeleccionada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un horario fijo válido.");
                return;
            }

            String[] horas = horaSeleccionada.split("-");
            if (horas.length != 2) {
                JOptionPane.showMessageDialog(null, "Formato de horario fijo inválido. Use el formato 'HH:mm - HH:mm'.");
                return;
            }

            horaInicio = horas[0].trim();
            horaFin = horas[1].trim();

        } else if (tipoHorario.equals("Personalizado")) {
            try {
                Date inicio = (Date) HorarioPersonalizadoInicio.getValue();
                Date fin = (Date) HorarioPersonalizadoFin.getValue();

                if (inicio.after(fin)) {
                    JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser mayor que la de fin.");
                    return;
                }

                horaInicio = formatoHora.format(inicio);
                horaFin = formatoHora.format(fin);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener las horas personalizadas.");
                return;
            }
        }

        String regexHora = "^(\\d{1,2}):(\\d{2})$";
        if (!horaInicio.matches(regexHora) || !horaFin.matches(regexHora)) {
            JOptionPane.showMessageDialog(null, "El formato de la hora debe ser HH:mm.");
            return;
        }

        java.sql.Date sqlFecha = new java.sql.Date(fechaGeneral.getTime());
        java.sql.Time sqlHoraInicio = java.sql.Time.valueOf(horaInicio + ":00");
        java.sql.Time sqlHoraFin = java.sql.Time.valueOf(horaFin + ":00");

        int idPersonalAcademico = obtenerIdPersonalAcademico();

        try {
            Connection con = Conexion.obtenerConexion();

            // Buscar el ID_lab a partir del Nombre del laboratorio
            PreparedStatement psBuscarLab = con.prepareStatement(
                    "SELECT ID_lab FROM laboratorios WHERE Nombre_lab = ?"
            );
            psBuscarLab.setString(1, laboratorioText);
            ResultSet rs = psBuscarLab.executeQuery();

            if (rs.next()) {
                int idLaboratorio = rs.getInt("ID_lab");

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO prestamos (ID_lab, id_personal_academico, motivo, fecha, horario_inicio, horario_fin, estado, tipo_horario) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                );
                ps.setInt(1, idLaboratorio);
                ps.setInt(2, idPersonalAcademico);
                ps.setString(3, motivo);
                ps.setDate(4, sqlFecha);
                ps.setTime(5, sqlHoraInicio);
                ps.setTime(6, sqlHoraFin);
                ps.setString(7, "Pendiente");
                ps.setString(8, tipoHorario);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Préstamo guardado exitosamente");
                    limpiarFormulario();
                    cargarTabla(idusuario);
                    cargarTabla2();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el préstamo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nombre de laboratorio no encontrado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el préstamo: " + e.getMessage());
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_LimpiarActionPerformed
    private void limpiarFormulario() {
        Motivo.setText("");
        Fecha.setDate(null);
        TipoHorario.setSelectedIndex(0);
        HorarioFijo.setSelectedIndex(0);
        HorarioPersonalizadoFin.setValue(new Date());
        Bloque.setSelectedIndex(0);
        Seccion.setSelectedIndex(0);
    }

    private int obtenerIdLaboratorio(String bloque, String seccion) {
        int idLaboratorio = -1;

        try {
            Connection con = Conexion.obtenerConexion();
            String query = "SELECT id_lab FROM laboratorios WHERE bloque = ? AND seccion = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bloque);
            ps.setString(2, seccion);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idLaboratorio = rs.getInt("id_lab");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del laboratorio: " + e.getMessage());
        }

        return idLaboratorio;
    }

    private int obtenerIdPersonalAcademico() {
        int id = -1;
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT id_personal_academico FROM personal_academico WHERE id_usuario = ?");
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_personal_academico");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del personal académico: " + e.getMessage());
        }
        return id;
    }

    private void BloqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BloqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BloqueActionPerformed

    private void tablaHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHistorialMouseClicked

    }//GEN-LAST:event_tablaHistorialMouseClicked

    private void HorarioFijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorarioFijoActionPerformed
        String selectedTime = (String) HorarioFijo.getSelectedItem();

        if (selectedTime == null || selectedTime.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún horario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] times = selectedTime.split(" - ");

        if (times.length < 2) {
            JOptionPane.showMessageDialog(this, "Formato de horario inválido. Asegúrate de seleccionar un horario como '7:30 - 9:00'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String startTime = times[0].trim();
        String endTime = times[1].trim();

        System.out.println("Hora de inicio: " + startTime);
        System.out.println("Hora de fin: " + endTime);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date startDate = format.parse(startTime);
            Date endDate = format.parse(endTime);

            ((JSpinner.DefaultEditor) HorarioPersonalizadoInicio.getEditor()).getTextField().setText(format.format(startDate));
            ((JSpinner.DefaultEditor) HorarioPersonalizadoFin.getEditor()).getTextField().setText(format.format(endDate));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al convertir la hora. Asegúrate de que el horario tenga el formato correcto (HH:mm).", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_HorarioFijoActionPerformed

    private void TipoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoHorarioActionPerformed
        String tipo = TipoHorario.getSelectedItem().toString();

        if (tipo.equals("Fijo")) {
            HorarioFijo.setVisible(true);
            HorarioPersonalizadoInicio.setVisible(false);
            HorarioPersonalizadoFin.setVisible(false);
        } else if (tipo.equals("Personalizado")) {
            HorarioFijo.setVisible(false);
            HorarioPersonalizadoInicio.setVisible(true);
            HorarioPersonalizadoFin.setVisible(true);
        } else {
            // En caso sea una opción vacía o "Seleccionar...", ocultar todo
            HorarioFijo.setVisible(false);
            HorarioPersonalizadoInicio.setVisible(false);
            HorarioPersonalizadoFin.setVisible(false);
        }
    }//GEN-LAST:event_TipoHorarioActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        cargarTabla(idusuario);
        cargarTabla2();
    }//GEN-LAST:event_ActualizarActionPerformed

    private void DisponibilidadPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisponibilidadPrestamoActionPerformed
        DisponibilidadPrestamos disponibilidadpres = new DisponibilidadPrestamos(Laboratorio);
        disponibilidadpres.setLocationRelativeTo(null);
        disponibilidadpres.setVisible(true);
    }//GEN-LAST:event_DisponibilidadPrestamoActionPerformed

    private void LaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaboratorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaboratorioActionPerformed

    private void SancionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SancionesActionPerformed
        SancionesRecibidaPersonal sanciones = new SancionesRecibidaPersonal(idusuario);
        sanciones.setLocationRelativeTo(null);
        sanciones.setVisible(true);
    }//GEN-LAST:event_SancionesActionPerformed

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

    private void ApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoActionPerformed

    private void btnSolicitudLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitudLabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitudLabMouseExited

    private void btnSolicitudLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudLabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitudLabActionPerformed

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited

    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login cerrar = new Login();
        cerrar.setLocationRelativeTo(null);
        cerrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        InicioPersonalAcademico inicio = new InicioPersonalAcademico(idusuario);
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnMaterialExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialExtraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaterialExtraActionPerformed
    public void cargarTabla(int idusuario) {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT p.id_prestamo, l.Nombre_lab, pa.nombre, pa.apellido, p.motivo, l.bloque, l.seccion, p.fecha, p.horario_inicio, p.horario_fin, p.estado, p.motivo_rechazo "
                    + "FROM prestamos p "
                    + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico "
                    + "WHERE pa.id_usuario = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tablaHistorial.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                String nombreLab = rs.getString("Nombre_lab");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fecha = rs.getDate("fecha");
                Time horaInicio = rs.getTime("horario_inicio");
                Time horaFin = rs.getTime("horario_fin");
                String estado = rs.getString("estado");
                String motivoRechazo = rs.getString("motivo_rechazo"); // Obtener el motivo de rechazo

                model.addRow(new Object[]{
                    idPrestamo, nombreLab, nombre, apellido,
                    fecha, horaInicio, horaFin, estado, motivoRechazo // Añadir el motivo de rechazo aquí
                });
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void cargarTabla2() {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT p.id_prestamo, p.motivo, l.bloque, l.seccion,p.tipo_horario "
                    + "FROM prestamos p "
                    + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico "
                    + "WHERE pa.id_usuario = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model2 = (DefaultTableModel) TablaPrestamos2.getModel();
            model2.setRowCount(0);

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                String motivo = rs.getString("motivo");
                String bloque = rs.getString("bloque");
                String seccion = rs.getString("seccion");
                String tipoHorario = rs.getString("tipo_horario");

                model2.addRow(new Object[]{
                    idPrestamo, motivo, bloque, seccion, tipoHorario
                });
            }

            

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioPrestamo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JTextField Apellido;
    private javax.swing.JComboBox<String> Bloque;
    private javax.swing.JButton DisponibilidadPrestamo;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JLabel Formulario;
    private javax.swing.JComboBox<String> HorarioFijo;
    private javax.swing.JSpinner HorarioPersonalizadoFin;
    private javax.swing.JSpinner HorarioPersonalizadoInicio;
    private javax.swing.JTextField Laboratorio;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel ListaPersonal;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextField Motivo;
    private javax.swing.JLabel MotivoRechazo;
    private javax.swing.JTextField Nombre;
    private javax.swing.JButton Sanciones;
    private javax.swing.JComboBox<String> Seccion;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaPrestamos2;
    private javax.swing.JComboBox<String> TipoHorario;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMaterialExtra;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSolicitudLab;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    private javax.swing.JTable tablaHistorial;
    // End of variables declaration//GEN-END:variables
}
