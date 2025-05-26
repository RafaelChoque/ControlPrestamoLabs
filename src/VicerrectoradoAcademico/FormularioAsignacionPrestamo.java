/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VicerrectoradoAcademico;

import Administrador.LogManager;
import VicerrectoradoAcademico.VerRUdePersonalAcademico;
import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
import static ConexionLogin.SesionUsuario.username;
import PersonalAcademico.DisponibilidadPrestamos;
import PersonalAcademico.FormularioPrestamo;
import Sanciones.SancionesRecibidaPersonal;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rafael
 */
public class FormularioAsignacionPrestamo extends javax.swing.JFrame {
    private int idusuario;
    /**
     * Creates new form FormularioAsignacionPrestamo
     * @param idusuario
     */
    public FormularioAsignacionPrestamo(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
        cargarTabla(idusuario);
        cargarTabla2();
        aplicarColorFilasAlternadas(TablaAsignacion2);
        aplicarColorFilasAlternadas(TablaAsignacion);
        cargarNombreCompleto();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        NombreVicerrector.setEditable(false);
        cargarNombreApellido(idusuario);
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
    private boolean sidebarMostrado = false;
    private Timer animacion;
    private boolean sidebarListo = false;
    private FormularioAsignacionPrestamo() {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombretxt = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Formulario = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        FechaFin = new com.toedter.calendar.JDateChooser();
        FechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        HorarioFijo = new javax.swing.JComboBox<>();
        Laboratorio = new javax.swing.JTextField();
        Seccion = new javax.swing.JComboBox<>();
        Bloque = new javax.swing.JComboBox<>();
        ElegirDocente = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        Semestre = new javax.swing.JComboBox<>();
        guardar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        DisponibilidadPrestamo1 = new javax.swing.JButton();
        NombreVicerrector = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Carrera = new javax.swing.JComboBox<>();
        Materia = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        ListaPersonal = new javax.swing.JLabel();
        Actualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAsignacion = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        MotivoRechazo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaAsignacion2 = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        perfil = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();
        panelSidebar = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnAsignacionSemestre = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nombretxt.setBackground(new java.awt.Color(255, 255, 255));
        Nombretxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Nombretxt.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 240, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Fecha Fin:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, 20));

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 250, -1));

        Formulario.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Formulario.setText("Formulario de Asignacion");
        jPanel1.add(Formulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel10.setText("Nombre del Docente:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel5.setText("Materia:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, 20));

        jLabel6.setText("Fecha Inicio:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));
        jPanel1.add(FechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 170, -1));
        jPanel1.add(FechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 170, -1));

        jLabel9.setText("Bloque:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        jLabel15.setText("Sección:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        jLabel11.setText("Laboratorio:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));

        jLabel3.setText("Horario:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));

        HorarioFijo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:30 - 9:00", "9:15 - 10:45", "11:00 - 12:30" }));
        HorarioFijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HorarioFijoActionPerformed(evt);
            }
        });
        jPanel1.add(HorarioFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 430, -1));

        Laboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaboratorioActionPerformed(evt);
            }
        });
        jPanel1.add(Laboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 250, -1));

        Seccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Redes", "Telecomunicaciones", "Electronica" }));
        jPanel1.add(Seccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 430, -1));

        Bloque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));
        Bloque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BloqueActionPerformed(evt);
            }
        });
        jPanel1.add(Bloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 430, -1));

        ElegirDocente.setBackground(new java.awt.Color(29, 41, 57));
        ElegirDocente.setForeground(new java.awt.Color(255, 255, 255));
        ElegirDocente.setText("Buscar Docente");
        ElegirDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ElegirDocenteActionPerformed(evt);
            }
        });
        jPanel1.add(ElegirDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 170, -1));

        jLabel8.setText("Semestre");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 20));

        Semestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1-2025", "2-2026", "1-2027", "2-2027" }));
        jPanel1.add(Semestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 430, -1));

        guardar.setBackground(new java.awt.Color(51, 153, 0));
        guardar.setForeground(new java.awt.Color(255, 255, 255));
        guardar.setText("Guardar Asignacion");
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

        DisponibilidadPrestamo1.setBackground(new java.awt.Color(29, 41, 57));
        DisponibilidadPrestamo1.setForeground(new java.awt.Color(255, 255, 255));
        DisponibilidadPrestamo1.setText("Seleccionar Laboratorio");
        DisponibilidadPrestamo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisponibilidadPrestamo1ActionPerformed(evt);
            }
        });
        jPanel1.add(DisponibilidadPrestamo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 170, -1));
        jPanel1.add(NombreVicerrector, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 430, -1));

        jLabel1.setText("Vicerrector:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 70, 20));

        jLabel7.setText("Carrera");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        Carrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingeniería de Sistemas", "Ingeniería Comercial", "Contaduria Publica", "Ciencias de la Educacion" }));
        jPanel1.add(Carrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 170, -1));
        jPanel1.add(Materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 170, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 610, 350));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ListaPersonal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaPersonal.setText("Historial de Asignaciones");
        jPanel4.add(ListaPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, -1));

        Actualizar.setBackground(new java.awt.Color(29, 41, 57));
        Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPanel4.add(Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 10, 110, -1));

        TablaAsignacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Laboratorio", "Docente", "Fecha Inicio", "Fecha Fin", "Horario Inicio", "Horario Fin", "Materia", "Carrera"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaAsignacion);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1420, 340));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 1440, 390));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MotivoRechazo.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        MotivoRechazo.setText("Descripción");
        jPanel2.add(MotivoRechazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TablaAsignacion2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Bloque", "Seccion", "Semestre"
            }
        ));
        int[]anchos2 = {50, 215, 70, 130};
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < anchos2.length; i++) {
            TablaAsignacion2.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
            TablaAsignacion2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
        }
        jScrollPane2.setViewportView(TablaAsignacion2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 790, 300));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 810, 350));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel3.add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 10, 630));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1480, 780));

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

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Panel de Control");
        panelSidebar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

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

        btnAsignacionSemestre.setBackground(new java.awt.Color(29, 41, 57));
        btnAsignacionSemestre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAsignacionSemestre.setForeground(new java.awt.Color(241, 241, 241));
        btnAsignacionSemestre.setText("Asignación Semestral");
        btnAsignacionSemestre.setBorder(null);
        btnAsignacionSemestre.setHorizontalAlignment(SwingConstants.LEFT);
        btnAsignacionSemestre.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnAsignacionSemestre.setIconTextGap(10);
        btnAsignacionSemestre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAsignacionSemestreMouseExited(evt);
            }
        });
        btnAsignacionSemestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignacionSemestreActionPerformed(evt);
            }
        });
        panelSidebar.add(btnAsignacionSemestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 229, 40));

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 870));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarNombreCompleto() {
    try {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT nombre, apellido FROM vicerrectorado_academico WHERE id_usuario = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, this.idusuario);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
            Nombretxt.setText(nombreCompleto);
        } else {
            Nombretxt.setText("Nombre no encontrado");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        Nombretxt.setText("Error al cargar nombre");
    }
}
    public void cargarNombreApellido(int idusuario) {
    try {
        Connection con = Conexion.obtenerConexion();

        String query = "SELECT CONCAT(nombre, ' ', apellido) AS nombre_completo FROM vicerrectorado_academico WHERE id_usuario = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idusuario);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String nombreCompleto = rs.getString("nombre_completo");
            NombreVicerrector.setText(nombreCompleto);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron datos del personal.");
        }
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
}

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        cargarTabla(idusuario);
        cargarTabla2();
    }//GEN-LAST:event_ActualizarActionPerformed

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
    }//GEN-LAST:event_HorarioFijoActionPerformed

    private void LaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaboratorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaboratorioActionPerformed

    private void BloqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BloqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BloqueActionPerformed

    private void ElegirDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ElegirDocenteActionPerformed
        VerRUdePersonalAcademico RUpersonal = new VerRUdePersonalAcademico(Nombre);
        RUpersonal.setLocationRelativeTo(null);
        RUpersonal.setVisible(true);
    }//GEN-LAST:event_ElegirDocenteActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
    String nombredocente = Nombre.getText();
    String carreratxt = Carrera.getSelectedItem().toString();
    String laboratorioText = Laboratorio.getText();
    String semestre = Semestre.getSelectedItem().toString();
    String materiatxt = Materia.getText();
    Date fechaInicioDate = FechaInicio.getDate();
    Date fechaFinDate = FechaFin.getDate();
    String horaSeleccionada = (String) HorarioFijo.getSelectedItem();

    if (carreratxt.isEmpty() || laboratorioText.isEmpty() || materiatxt.isEmpty() || semestre.isEmpty()
            || fechaInicioDate == null || fechaFinDate == null || horaSeleccionada == null || horaSeleccionada.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.");
        return;
    }

    if (fechaInicioDate.after(fechaFinDate)) {
        JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser posterior a la fecha de fin.");
        return;
    }

    String[] horas = horaSeleccionada.split("-");
    if (horas.length != 2) {
        JOptionPane.showMessageDialog(null, "Formato de horario fijo inválido. Use el formato 'HH:mm - HH:mm'.");
        return;
    }

    String horaInicio = horas[0].trim();
    String horaFin = horas[1].trim();

    String regexHora = "^(\\d{1,2}):(\\d{2})$";
    if (!horaInicio.matches(regexHora) || !horaFin.matches(regexHora)) {
        JOptionPane.showMessageDialog(null, "El formato de la hora debe ser HH:mm.");
        return;
    }

    java.sql.Date sqlFechaInicio = new java.sql.Date(fechaInicioDate.getTime());
    java.sql.Date sqlFechaFin = new java.sql.Date(fechaFinDate.getTime());
    java.sql.Time sqlHoraInicio = java.sql.Time.valueOf(horaInicio + ":00");
    java.sql.Time sqlHoraFin = java.sql.Time.valueOf(horaFin + ":00");

    try {
        Connection con = Conexion.obtenerConexion();

        // Obtener ID del laboratorio
// Obtener ID del laboratorio directamente con nombre
        int idLaboratorio = -1;
        PreparedStatement psBuscarLab = con.prepareStatement(
                "SELECT ID_lab FROM laboratorios WHERE Nombre_lab = ?"
        );

        psBuscarLab.setString(1, laboratorioText);
        ResultSet rsLab = psBuscarLab.executeQuery();
        if (rsLab.next()) {
            idLaboratorio = rsLab.getInt("ID_lab");
        } else {
            JOptionPane.showMessageDialog(null, "Laboratorio no encontrado.");
            return;
        }

        // Obtener ID del personal académico (docente)
        int idPersonalAcademico = -1;
        PreparedStatement psBuscarDocente = con.prepareStatement(
                "SELECT id_personal_academico FROM personal_academico WHERE CONCAT(nombre, ' ', apellido) = ?"
        );
        psBuscarDocente.setString(1, nombredocente);
        ResultSet rs = psBuscarDocente.executeQuery();
        if (rs.next()) {
            idPersonalAcademico = rs.getInt("id_personal_academico");
        } else {
            JOptionPane.showMessageDialog(null, "Personal académico no encontrado con ese nombre.");
            return;
        }

        // Insertar en la tabla asignaciones
        PreparedStatement psInsert = con.prepareStatement(
                "INSERT INTO asignaciones (ID_lab, id_personal_academico, carrera, materia, hora_inicio, hora_fin, fecha_inicio, fecha_fin, semestre, id_vicerrectorado_academico) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );
        psInsert.setInt(1, idLaboratorio);
        psInsert.setInt(2, idPersonalAcademico);
        psInsert.setString(3, carreratxt);
        psInsert.setString(4, materiatxt);
        psInsert.setTime(5, sqlHoraInicio);
        psInsert.setTime(6, sqlHoraFin);
        psInsert.setDate(7, sqlFechaInicio);
        psInsert.setDate(8, sqlFechaFin);
        psInsert.setString(9, semestre);
        psInsert.setInt(10, obtenerIdVicerrectorAcademico());

        int rowsAffected = psInsert.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Asignación guardada exitosamente.");
            cargarTabla(idusuario);
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar la asignación.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar la asignación: " + e.getMessage());
    }
    }//GEN-LAST:event_guardarActionPerformed
    private void limpiarFormulario(){
        Nombre.setText("");
        Carrera.setSelectedIndex(0);
        Materia.setText("");
        FechaInicio.setDate(null);
        FechaFin.setDate(null);
        Bloque.setSelectedIndex(0);
        Seccion.setSelectedIndex(0);
        Laboratorio.setText("");
        HorarioFijo.setSelectedIndex(0);
        Semestre.setSelectedIndex(0);
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

    private int obtenerIdVicerrectorAcademico() {
        int id = -1;
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT id_vicerrectorado_academico FROM vicerrectorado_academico WHERE id_usuario = ?");
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_vicerrectorado_academico");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del vicerrector academico: " + e.getMessage());
        }
        return id;
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
    public void cargarTabla(int idusuario) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "ID", "Laboratorio", "Docente", "Materia", "Carrera",
            "Fecha Inicio", "Fecha Fin", "Hora Inicio", "Hora Fin"
        });

        try {
            Connection con = Conexion.obtenerConexion();
            String query = "SELECT a.id_asignacion, l.Nombre_lab, "
                    + "CONCAT(pa.nombre, ' ', pa.apellido) AS nombre_completo, "
                    + "a.materia, a.carrera, a.fecha_inicio, a.fecha_fin, "
                    + "a.hora_inicio, a.hora_fin "
                    + "FROM asignaciones a "
                    + "INNER JOIN laboratorios l ON a.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON a.id_personal_academico = pa.id_personal_academico "
                    + "INNER JOIN vicerrectorado_academico va ON a.id_vicerrectorado_academico = va.id_vicerrectorado_academico "
                    + "INNER JOIN usuarios u ON va.id_usuario = u.id_usuario "
                    + "WHERE u.id_usuario = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idusuario); // Asegúrate de que 'idusuario' esté disponible en la clase
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_asignacion"),
                    rs.getString("Nombre_lab"),
                    rs.getString("nombre_completo"),
                    rs.getString("materia"),
                    rs.getString("carrera"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getTime("hora_inicio"),
                    rs.getTime("hora_fin")
                });
            }

            TablaAsignacion.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar asignaciones: " + e.getMessage());
        }
    }




public void cargarTabla2() {
    try {
        Connection con = Conexion.obtenerConexion();

        String query = "SELECT a.id_asignacion, l.Bloque, l.Seccion, a.semestre " +
                       "FROM asignaciones a " +
                       "INNER JOIN laboratorios l ON a.ID_lab = l.ID_lab " +
                       "INNER JOIN vicerrectorado_academico va ON a.id_vicerrectorado_academico = va.id_vicerrectorado_academico " +
                       "INNER JOIN usuarios u ON va.id_usuario = u.id_usuario " +
                       "WHERE u.id_usuario = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idusuario);
        ResultSet rs = ps.executeQuery();

        DefaultTableModel model2 = (DefaultTableModel) TablaAsignacion2.getModel();
        model2.setRowCount(0);

        while (rs.next()) {
            model2.addRow(new Object[]{
                rs.getInt("id_asignacion"),
                rs.getString("Bloque"),
                rs.getString("Seccion"),
                rs.getString("semestre")
            });
        }
    } catch (SQLException ex) {
        System.out.println("Error cargarTabla2: " + ex.getMessage());
    }
}


    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void DisponibilidadPrestamo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisponibilidadPrestamo1ActionPerformed
        DisponibilidadPrestamos disponibilidadpres = new DisponibilidadPrestamos(Laboratorio);
        disponibilidadpres.setLocationRelativeTo(null);
        disponibilidadpres.setVisible(true);
    }//GEN-LAST:event_DisponibilidadPrestamo1ActionPerformed

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited

    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        int idUsuario = SesionUsuario.idUsuario;
        String rol = SesionUsuario.rol;
        String usuario = SesionUsuario.username;
        LogManager.registrarLog(idusuario, rol, "Cerrar Sesión", "Usuario '" + username + "' Rol: '" + rol + "' cerró sesión correctamente.");
        Login cerrar = new Login();
        cerrar.setLocationRelativeTo(null);
        cerrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        InicioVicerrectorAcademico iniciovice = new InicioVicerrectorAcademico(idusuario);
        iniciovice.setLocationRelativeTo(null);
        iniciovice.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAsignacionSemestreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAsignacionSemestreMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsignacionSemestreMouseExited

    private void btnAsignacionSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignacionSemestreActionPerformed

    }//GEN-LAST:event_btnAsignacionSemestreActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioAsignacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioAsignacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioAsignacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioAsignacionPrestamo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioAsignacionPrestamo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JComboBox<String> Bloque;
    private javax.swing.JComboBox<String> Carrera;
    private javax.swing.JButton DisponibilidadPrestamo1;
    private javax.swing.JButton ElegirDocente;
    private com.toedter.calendar.JDateChooser FechaFin;
    private com.toedter.calendar.JDateChooser FechaInicio;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JLabel Formulario;
    private javax.swing.JComboBox<String> HorarioFijo;
    private javax.swing.JTextField Laboratorio;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel ListaPersonal;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextField Materia;
    private javax.swing.JLabel MotivoRechazo;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField NombreVicerrector;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JComboBox<String> Seccion;
    private javax.swing.JComboBox<String> Semestre;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaAsignacion;
    private javax.swing.JTable TablaAsignacion2;
    private javax.swing.JButton btnAsignacionSemestre;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    // End of variables declaration//GEN-END:variables
}
