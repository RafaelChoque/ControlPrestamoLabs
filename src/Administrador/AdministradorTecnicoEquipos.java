package Administrador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author tralalero tralala --que tremendo bobo el que escribio eso
 */
import TecnicoDePrestamos.ListaPrestamos;
import TecnicoDePrestamos.ListaLaboratorios;
import ConexionLogin.Login;
import ConexionLogin.Conexion;
import ConexionLogin.SesionUsuario;
import PersonalAcademico.InicioPersonalAcademico;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.mindrot.jbcrypt.BCrypt;
public class AdministradorTecnicoEquipos extends javax.swing.JFrame {


    /**
     * Creates new form InicioPersonalAcademico
     * 
     */
    public AdministradorTecnicoEquipos() {

        initComponents();
        aplicarColorFilasAlternadas(TablaTecnicoEquipo);
        ID.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
        cargarTabla();
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

        panelSidebar = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnPersonalAcademico = new javax.swing.JButton();
        btnTecnicoPrestamo = new javax.swing.JButton();
        btnTecnicoEquipo = new javax.swing.JButton();
        btnVicerrector = new javax.swing.JButton();
        btnAuditoria = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();
        btnMenu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        FondoBlanco1 = new javax.swing.JLabel();
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
        AgregarTecnico = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTecnicoEquipo = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ListaTecnicos = new javax.swing.JLabel();
        perfil1 = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
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

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

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

        btnPersonalAcademico.setBackground(new java.awt.Color(29, 41, 57));
        btnPersonalAcademico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPersonalAcademico.setForeground(new java.awt.Color(241, 241, 241));
        btnPersonalAcademico.setText("Personal Academico");
        btnPersonalAcademico.setBorder(null);
        btnPersonalAcademico.setHorizontalAlignment(SwingConstants.LEFT);
        btnPersonalAcademico.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnPersonalAcademico.setIconTextGap(10);
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
        btnTecnicoPrestamo.setHorizontalAlignment(SwingConstants.LEFT);
        btnTecnicoPrestamo.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnTecnicoPrestamo.setIconTextGap(10);
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
        btnTecnicoEquipo.setHorizontalAlignment(SwingConstants.LEFT);
        btnTecnicoEquipo.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnTecnicoEquipo.setIconTextGap(10);
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

        btnVicerrector.setBackground(new java.awt.Color(29, 41, 57));
        btnVicerrector.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVicerrector.setForeground(new java.awt.Color(241, 241, 241));
        btnVicerrector.setText("VicerrectoradoAcademico");
        btnVicerrector.setBorder(null);
        btnTecnicoPrestamo.setHorizontalAlignment(SwingConstants.LEFT);
        btnTecnicoPrestamo.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnTecnicoPrestamo.setIconTextGap(10);
        btnVicerrector.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVicerrectorMouseExited(evt);
            }
        });
        btnVicerrector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVicerrectorActionPerformed(evt);
            }
        });
        panelSidebar.add(btnVicerrector, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 229, 40));

        btnAuditoria.setBackground(new java.awt.Color(29, 41, 57));
        btnAuditoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAuditoria.setForeground(new java.awt.Color(241, 241, 241));
        btnAuditoria.setText("Auditoria");
        btnAuditoria.setBorder(null);
        btnAuditoria.setHorizontalAlignment(SwingConstants.LEFT);
        btnAuditoria.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnAuditoria.setIconTextGap(10);
        btnAuditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAuditoriaMouseExited(evt);
            }
        });
        btnAuditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuditoriaActionPerformed(evt);
            }
        });
        panelSidebar.add(btnAuditoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 229, 40));

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 870));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

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
        jPanel2.add(FondoBlanco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 500, 680, 410));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel4.setText("Apellido:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jLabel5.setText("CI:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        jLabel6.setText("RU:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

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

        jLabel7.setText("Teléfono:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));
        jPanel1.add(RU, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 430, -1));

        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });
        jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 10, -1));

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
        AgregarTecnico.setText("Agregar Personal de Mantenimiento de Equipos");
        jPanel1.add(AgregarTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, 550, 240));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 870, 600));

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
                    TablaTecnicoEquipo.setRowSorter(null);
                    return;
                }

                TableRowSorter<TableModel> sorter = new TableRowSorter<>(TablaTecnicoEquipo.getModel());
                TablaTecnicoEquipo.setRowSorter(sorter);

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
        ListaTecnicos.setText("Lista de Personal de Mantenimiento de Equipos");
        jPanel2.add(ListaTecnicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 500, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1490, 760));

        perfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouseer.png"))); // NOI18N
        getContentPane().add(perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 10, 60, 60));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 450, 500, 350));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 860));

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
                    + "INNER JOIN usuarios u ON t.id_usuario = u.id_usuario "
                    + "WHERE t.estado = 1 "
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
    private void Limpiar(){
        RU.setText("");
        Nombre.setText("");
        Apellido.setText("");
        CI.setText("");
        Telefono.setText("");
    }
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

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
            
            int idAdmin = SesionUsuario.idUsuario; 
            String rolAdmin = SesionUsuario.rol;
            String usuarioAdmin = SesionUsuario.username;

            String accion = (nuevoEstado == 1) ? "Habilitar" : "Deshabilitar";
            String detalleLog = "Usuario: '" + usuarioAdmin + "' Rol: '" + rolAdmin + 
                "' realizó la acción '" + accion + "' sobre el Tecnico de Equipos con ID de usuario: " + idUsuario;
            LogManager.registrarLog(idAdmin, rolAdmin, accion, detalleLog);

            String mensaje = (nuevoEstado == 1) ? "Técnico habilitado." : "Técnico deshabilitado.";
            JOptionPane.showMessageDialog(null, mensaje);

            cargarTabla();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_HabilitarDeshabilitarActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        ID.setVisible(false);
    }//GEN-LAST:event_IDActionPerformed

    private void CIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CIActionPerformed

    private void ApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoActionPerformed

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void TelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefonoActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_limpiarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (ID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un técnico a eliminar");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de que desea eliminar este registro?\nEsta acción deshabilitará al usuario del sistema.",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return; 
        }

        int tecnicoEquiposId = Integer.parseInt(ID.getText());

        try {
            Connection con = Conexion.obtenerConexion();

            PreparedStatement psVerificar = con.prepareStatement(
                    "SELECT estado, id_usuario FROM tecnico_equipos WHERE id_tecnico_equipos = ?"
            );
            psVerificar.setInt(1, tecnicoEquiposId);
            ResultSet rs = psVerificar.executeQuery();

            if (rs.next()) {
                int estado = rs.getInt("estado");
                int idUsuario = rs.getInt("id_usuario");

                if (estado == 0) {
                    JOptionPane.showMessageDialog(null, "El usuario ya fue eliminado anteriormente");
                    return;
                }

                PreparedStatement psActualizarEstado = con.prepareStatement(
                        "UPDATE tecnico_equipos SET estado = 0 WHERE id_tecnico_equipos = ?"
                );
                psActualizarEstado.setInt(1, tecnicoEquiposId);
                psActualizarEstado.executeUpdate();

                PreparedStatement psActualizarUsuario = con.prepareStatement(
                        "UPDATE usuarios SET activo = 0 WHERE id_usuario = ?"
                );
                psActualizarUsuario.setInt(1, idUsuario);
                psActualizarUsuario.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro marcado como eliminado e inhabilitado para el sistema");
                int idAdmin = SesionUsuario.idUsuario;
                String rolAdmin = SesionUsuario.rol;
                String usuarioAdmin = SesionUsuario.username;
                String accion = "Registro Eliminado";
                String detalleLog = "Usuario: '" + usuarioAdmin + "' Rol: '" + rolAdmin +
                    "' eliminó al Tecnico de Equipos con ID de usuario: " + idUsuario;

                LogManager.registrarLog(idAdmin, rolAdmin, accion, detalleLog);
                Limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró al técnico de equipos");
            }
        } catch (SQLException ex) {
            System.out.println("Error en eliminación lógica: " + ex.toString());
        }
    }//GEN-LAST:event_eliminarActionPerformed

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
        String contrasenaHasheada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
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
            psUsuario.setString(2, contrasenaHasheada);
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
            int idAdmin = SesionUsuario.idUsuario; // Usuario que modifica (admin)
            String rolAdmin = SesionUsuario.rol;
            String usuarioAdmin = SesionUsuario.username;
            String accion = "Registro Modificado";
            String detalleLog = "Usuario: '" + usuarioAdmin + "' Rol: '" + rolAdmin +
                "' modificó los datos del Tecnico de Equipos con ID de usuario: " + idUsuario;

            LogManager.registrarLog(idAdmin, rolAdmin, accion, detalleLog);
            Limpiar();
            cargarTabla();
        }catch(SQLException ex) {
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_modificarActionPerformed

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
            String contrasenaEncriptada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
            PreparedStatement psUsuario = con.prepareStatement("INSERT INTO usuarios(username, contrasena, rol, activo) VALUES (?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, username);
            psUsuario.setString(2, contrasenaEncriptada);
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
            int idAdmin = SesionUsuario.idUsuario;
                String rolAdmin = SesionUsuario.rol;
                String usuarioAdmin = SesionUsuario.username;

                String detalleLog = "Usuario: '" + usuarioAdmin + "' Rol '" + rolAdmin +
                    "' registró nuevo Tecnico de Equipos: " + nombre + " " + apellido +
                    ", CI: " + ci + ", RU: " + ru;

                LogManager.registrarLog(idAdmin, rolAdmin, "Registro Nuevo", detalleLog);
            AdministradorTecnicoEquipos interfaz = new AdministradorTecnicoEquipos();
            interfaz.setVisible(true);

            Limpiar();
            cargarTabla();

        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited

    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        int idUsuario = SesionUsuario.idUsuario;
        String rol = SesionUsuario.rol;
        String usuario = SesionUsuario.username;
        LogManager.registrarLog(idUsuario, rol, "Cerrar Sesión", "Usuario '" + usuario + "' Rol: '" + rol + "' cerró sesión correctamente.");
        Login cerrar = new Login();
        cerrar.setLocationRelativeTo(null);
        cerrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        InicioAdministrador inicio = new InicioAdministrador();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

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

    private void btnTecnicoEquipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTecnicoEquipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTecnicoEquipoMouseExited

    private void btnTecnicoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecnicoEquipoActionPerformed
        
    }//GEN-LAST:event_btnTecnicoEquipoActionPerformed

    private void btnVicerrectorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVicerrectorMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVicerrectorMouseExited

    private void btnVicerrectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVicerrectorActionPerformed
        AdministradorVicerrectoradoAcademico admin = new AdministradorVicerrectoradoAcademico();
        admin.setLocationRelativeTo(null);
        admin.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_btnVicerrectorActionPerformed

    private void btnAuditoriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAuditoriaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAuditoriaMouseExited

    private void btnAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaActionPerformed
        Auditoria admin = new Auditoria();
        admin.setLocationRelativeTo(null);
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAuditoriaActionPerformed

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
    private javax.swing.JLabel FondoBlanco1;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JButton HabilitarDeshabilitar;
    private javax.swing.JTextField ID;
    private javax.swing.JLabel ListaTecnicos;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField RU;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaTecnicoEquipo;
    private javax.swing.JTextField Telefono;
    private javax.swing.JButton btnAuditoria;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnPersonalAcademico;
    private javax.swing.JButton btnTecnicoEquipo;
    private javax.swing.JButton btnTecnicoPrestamo;
    private javax.swing.JButton btnVicerrector;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton modificar;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    private javax.swing.JLabel perfil1;
    // End of variables declaration//GEN-END:variables
}
