/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Sanciones;

import Administrador.LogManager;
import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
import static ConexionLogin.SesionUsuario.username;
import Materiales.Materiales;
import Materiales.MaterialesHardware;
import Reportes.ReportesMantenimiento;
import Reportes.ReportesPrestamos;
import Reportes.ReportesSanciones;
import TecnicoDePrestamos.InicioAdmiTecnicoPrestamos;
import TecnicoDePrestamos.ListaLaboratorios;
import TecnicoDePrestamos.ListaPrestamos;
import TecnicoDePrestamos.SolicitudPendiente;
import TecnicoDePrestamos.VerTecnicosPrestamos;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rafael
 */
public class SancionesParaDesignar extends javax.swing.JFrame {

private int idPrestamoSeleccionado = -1;
private int idTecnicoActual = -1;

    private int idusuario;

    /**
     * Creates new form InicioPersonalAcademico
     * @param idusuario
     */
    public SancionesParaDesignar(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
        aplicarColorFilasAlternadas(TblPrestamosAntiguos);
        aplicarColorFilasAlternadas(TblSanciones);
        cargarNombreCompleto();
        iconoOriginal = lblFlecha.getIcon();
panelSubReportes.setLocation(panelSubReportes.getX(), -70);
panelSubReportes.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cargarTablaPrestamos();
        cargarTablaSanciones(); 
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
            private boolean flechaAbajo = true; // empieza apuntando hacia abajo
private Icon iconoOriginal;

    private SancionesParaDesignar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
private void mostrarSubReportes() {
    panelSubReportes.setVisible(true);
    int yFinal = 120;
    int yInicio = -70; 
    panelSubReportes.setLocation(panelSubReportes.getX(), yInicio);

    new Thread(() -> {
        for (int y = yInicio; y <= yFinal; y += 5) {
            final int posY = y;
            SwingUtilities.invokeLater(() -> {
                panelSubReportes.setLocation(panelSubReportes.getX(), posY);
            });
            try { Thread.sleep(5); } catch (InterruptedException e) {}
        }
    }).start();
}
private void ocultarSubReportes() {
    int yInicio = panelSubReportes.getY();
    int yFinal = -70;

    new Thread(() -> {
        for (int y = yInicio; y >= yFinal; y -= 5) {
            final int posY = y;
            SwingUtilities.invokeLater(() -> {
                panelSubReportes.setLocation(panelSubReportes.getX(), posY);
            });
            try { Thread.sleep(5); } catch (InterruptedException e) {}
        }
        SwingUtilities.invokeLater(() -> panelSubReportes.setVisible(false));
    }).start();
}
private final Color colorDefault = new Color(29, 41, 57);
private final Color colorPresionado = new Color(41, 59, 83);
private void cambiarColorBotonAlPresionar(boolean estaPresionado) {
    if (estaPresionado) {
        btnReportes.setBackground(colorPresionado); // color cuando se presiona
    } else {
        btnReportes.setBackground(colorDefault); // restaurar el color por defecto
    }
}

private Icon rotarIcono(Icon icono, double angulo) {
    int w = icono.getIconWidth();
    int h = icono.getIconHeight();

    int size = (int) Math.ceil(Math.sqrt(w * w + h * h));

    BufferedImage imagenOriginal = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2dOriginal = imagenOriginal.createGraphics();
    icono.paintIcon(null, g2dOriginal, 0, 0);
    g2dOriginal.dispose();

    BufferedImage imagenRotada = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = imagenRotada.createGraphics();

    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    AffineTransform at = new AffineTransform();
    at.translate(size / 2.0, size / 2.0); // mover al centro
    at.rotate(-angulo);  
    at.translate(-w / 2.0, -h / 2.0);     // mover imagen al centro

    g2d.drawImage(imagenOriginal, at, null);
    g2d.dispose();

    return new ImageIcon(imagenRotada);
}

private void animarRotacionFlecha(boolean haciaArriba) {
    final int pasos = 12;
    final int delay = 10; // milisegundos entre pasos
    final double anguloInicial = haciaArriba ? 0 : Math.PI;
    final double anguloFinal = haciaArriba ? Math.PI : 0;
    final double incremento = (anguloFinal - anguloInicial) / pasos;

    Timer timer = new Timer(delay, null);
    final int[] pasoActual = {0};

    timer.addActionListener(e -> {
        double angulo = anguloInicial + pasoActual[0] * incremento;
        lblFlecha.setIcon(rotarIcono(iconoOriginal, angulo));
        pasoActual[0]++;
        if (pasoActual[0] > pasos) {
            ((Timer) e.getSource()).stop();
        }
    });

    timer.start();
}
private boolean subReportesMostrado = false;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombretxt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblPrestamosAntiguos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblSanciones = new javax.swing.JTable();
        perfil = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        AsignacionSancion = new javax.swing.JLabel();
        NombreSancionadotxt = new javax.swing.JTextField();
        NombreSancionado = new javax.swing.JLabel();
        Descripcion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Descripciontxt = new javax.swing.JTextArea();
        Fecha = new javax.swing.JLabel();
        Fechadate = new com.toedter.calendar.JDateChooser();
        TipoBox = new javax.swing.JComboBox<>();
        Tipo = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        Nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BuscarRu = new javax.swing.JButton();
        FondoBlanco1 = new javax.swing.JLabel();
        FondoBlanco2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BuscarPrestamo = new javax.swing.JButton();
        FechaPrestamo = new com.toedter.calendar.JDateChooser();
        FechaPrestam = new javax.swing.JLabel();
        Formulario1 = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();
        panelSidebar = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFlecha = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        LogoSale2 = new javax.swing.JLabel();
        btnListaLaboratorios = new javax.swing.JButton();
        btnListaPrestamos = new javax.swing.JButton();
        btnSolicitudes = new javax.swing.JButton();
        btnSancionesDesignar = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnMateriales = new javax.swing.JButton();
        btnComputadoras = new javax.swing.JButton();
        panelSubReportes = new javax.swing.JPanel();
        btnReporteLaboratorios = new javax.swing.JButton();
        btnReporteMantenimiento = new javax.swing.JButton();
        btnReporteSanciones = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nombretxt.setBackground(new java.awt.Color(255, 255, 255));
        Nombretxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Nombretxt.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 15, 240, 30));

        TblPrestamosAntiguos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Prestamo", "Laboratorio", "Fecha", "Horario Inicio", "Horario Fin", "Docente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblPrestamosAntiguos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPrestamosAntiguosmouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblPrestamosAntiguos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 910, 290));

        TblSanciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Descripcion", "Fecha", "Tipo", "Tecnico", "Sancionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Byte.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TblSanciones);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 1440, 370));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AsignacionSancion.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        AsignacionSancion.setText("Asignar Sancion");
        jPanel1.add(AsignacionSancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, -1));
        jPanel1.add(NombreSancionadotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 380, -1));

        NombreSancionado.setText("Sancionado:");
        jPanel1.add(NombreSancionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        Descripcion.setText("Descripcion:");
        jPanel1.add(Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        Descripciontxt.setColumns(20);
        Descripciontxt.setRows(5);
        jScrollPane2.setViewportView(Descripciontxt);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 380, 100));

        Fecha.setText("Fecha:");
        jPanel1.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, 20));
        jPanel1.add(Fechadate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 380, -1));

        TipoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daño de Material", "Perdida de Material", "Incumplimiento de Horario", "Otro"}));
        TipoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoBoxActionPerformed(evt);
            }
        });
        jPanel1.add(TipoBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 380, -1));

        Tipo.setText("Tipo:");
        jPanel1.add(Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 20));

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 290, 90, -1));

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 300, -1));

        jLabel1.setText("Tecnico:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        BuscarRu.setText("Buscar");
        BuscarRu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarRuActionPerformed(evt);
            }
        });
        jPanel1.add(BuscarRu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 510, 330));

        FondoBlanco1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 0, 740));

        FondoBlanco2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 1450, 380));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BuscarPrestamo.setText("Buscar Prestamo");
        BuscarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarPrestamoActionPerformed(evt);
            }
        });
        jPanel3.add(BuscarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));
        jPanel3.add(FechaPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 160, -1));

        FechaPrestam.setText("Fecha del Prestamo");
        jPanel3.add(FechaPrestam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 910, 130));

        Formulario1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Formulario1.setText("Docentes Sancionados");
        jPanel2.add(Formulario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 270, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1480, 770));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 920));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1450, 740));

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Panel de Control");
        panelSidebar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

        lblFlecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MostrarMasBoton.png"))); // NOI18N
        panelSidebar.add(lblFlecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 382, 20, 20));

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

        btnInicio.setBackground(new java.awt.Color(29, 41, 57));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(241, 241, 241));
        btnInicio.setText("Inicio");
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

        LogoSale2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoUSB.png"))); // NOI18N
        panelSidebar.add(LogoSale2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 160, 60));

        btnListaLaboratorios.setBackground(new java.awt.Color(29, 41, 57));
        btnListaLaboratorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnListaLaboratorios.setForeground(new java.awt.Color(241, 241, 241));
        btnListaLaboratorios.setText("Laboratorios");
        btnListaLaboratorios.setBorder(null);
        btnListaLaboratorios.setHorizontalAlignment(SwingConstants.LEFT);
        btnListaLaboratorios.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnListaLaboratorios.setIconTextGap(10);
        btnListaLaboratorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnListaLaboratoriosMouseExited(evt);
            }
        });
        btnListaLaboratorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaLaboratoriosActionPerformed(evt);
            }
        });
        panelSidebar.add(btnListaLaboratorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 229, 40));

        btnListaPrestamos.setBackground(new java.awt.Color(29, 41, 57));
        btnListaPrestamos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnListaPrestamos.setForeground(new java.awt.Color(241, 241, 241));
        btnListaPrestamos.setText("Prestamos");
        btnListaPrestamos.setBorder(null);
        btnListaPrestamos.setHorizontalAlignment(SwingConstants.LEFT);
        btnListaPrestamos.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnListaPrestamos.setIconTextGap(10);
        btnListaPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnListaPrestamosMouseExited(evt);
            }
        });
        btnListaPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaPrestamosActionPerformed(evt);
            }
        });
        panelSidebar.add(btnListaPrestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 229, 40));

        btnSolicitudes.setBackground(new java.awt.Color(29, 41, 57));
        btnSolicitudes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSolicitudes.setForeground(new java.awt.Color(241, 241, 241));
        btnSolicitudes.setText("Solicitudes");
        btnSolicitudes.setBorder(null);
        btnSolicitudes.setHorizontalAlignment(SwingConstants.LEFT);
        btnSolicitudes.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnSolicitudes.setIconTextGap(10);
        btnSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSolicitudesMouseExited(evt);
            }
        });
        btnSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudesActionPerformed(evt);
            }
        });
        panelSidebar.add(btnSolicitudes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 229, 40));

        btnSancionesDesignar.setBackground(new java.awt.Color(29, 41, 57));
        btnSancionesDesignar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSancionesDesignar.setForeground(new java.awt.Color(241, 241, 241));
        btnSancionesDesignar.setText("Sanciones");
        btnSancionesDesignar.setBorder(null);
        btnSancionesDesignar.setHorizontalAlignment(SwingConstants.LEFT);
        btnSancionesDesignar.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnSancionesDesignar.setIconTextGap(10);
        btnSancionesDesignar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSancionesDesignarMouseExited(evt);
            }
        });
        btnSancionesDesignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSancionesDesignarActionPerformed(evt);
            }
        });
        panelSidebar.add(btnSancionesDesignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 229, 40));

        btnReportes.setBackground(new java.awt.Color(29, 41, 57));
        btnReportes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(241, 241, 241));
        btnReportes.setText("Reportes");
        btnReportes.setBorder(null);
        btnReportes.setHorizontalAlignment(SwingConstants.LEFT);
        btnReportes.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnReportes.setIconTextGap(10);
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportesMouseExited(evt);
            }
        });
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        panelSidebar.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 229, 40));

        btnMateriales.setBackground(new java.awt.Color(29, 41, 57));
        btnMateriales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMateriales.setForeground(new java.awt.Color(241, 241, 241));
        btnMateriales.setText("Materiales");
        btnMateriales.setBorder(null);
        btnMateriales.setHorizontalAlignment(SwingConstants.LEFT);
        btnMateriales.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnMateriales.setIconTextGap(10);
        btnMateriales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMaterialesMouseExited(evt);
            }
        });
        btnMateriales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaterialesActionPerformed(evt);
            }
        });
        panelSidebar.add(btnMateriales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 229, 40));

        btnComputadoras.setBackground(new java.awt.Color(29, 41, 57));
        btnComputadoras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnComputadoras.setForeground(new java.awt.Color(241, 241, 241));
        btnComputadoras.setText("Computadoras");
        btnComputadoras.setBorder(null);
        btnComputadoras.setHorizontalAlignment(SwingConstants.LEFT);
        btnComputadoras.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnComputadoras.setIconTextGap(10);
        btnComputadoras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComputadorasMouseExited(evt);
            }
        });
        btnComputadoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComputadorasActionPerformed(evt);
            }
        });
        panelSidebar.add(btnComputadoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 229, 40));

        panelSubReportes.setBackground(new java.awt.Color(16, 23, 32));
        panelSubReportes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReporteLaboratorios.setBackground(new java.awt.Color(16, 23, 32));
        btnReporteLaboratorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteLaboratorios.setForeground(new java.awt.Color(241, 241, 241));
        btnReporteLaboratorios.setText("Prestamos");
        btnReporteLaboratorios.setBorder(null);
        btnReporteLaboratorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporteLaboratoriosMouseExited(evt);
            }
        });
        btnReporteLaboratorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteLaboratoriosActionPerformed(evt);
            }
        });
        panelSubReportes.add(btnReporteLaboratorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 230, 40));

        btnReporteMantenimiento.setBackground(new java.awt.Color(16, 23, 32));
        btnReporteMantenimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteMantenimiento.setForeground(new java.awt.Color(241, 241, 241));
        btnReporteMantenimiento.setText("Mantenimiento");
        btnReporteMantenimiento.setBorder(null);
        btnReporteMantenimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporteMantenimientoMouseExited(evt);
            }
        });
        btnReporteMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteMantenimientoActionPerformed(evt);
            }
        });
        panelSubReportes.add(btnReporteMantenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 230, 40));

        btnReporteSanciones.setBackground(new java.awt.Color(16, 23, 32));
        btnReporteSanciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteSanciones.setForeground(new java.awt.Color(255, 255, 255));
        btnReporteSanciones.setText("Sanciones");
        btnReporteSanciones.setBorder(null);
        btnReporteSanciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteSancionesActionPerformed(evt);
            }
        });
        panelSubReportes.add(btnReporteSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 230, 40));

        panelSidebar.add(panelSubReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 230, 330));

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 860));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents
        private void cargarNombreCompleto() {
    try {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT nombre, apellido FROM tecnico_prestamos WHERE id_usuario = ?";
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
    private void cargarTablaSanciones() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Sanción");
        modelo.addColumn("Descripción");
        modelo.addColumn("Fecha");
        modelo.addColumn("Tipo");
        modelo.addColumn("Técnico");
        modelo.addColumn("Sancionado");

        try {
            Connection con = Conexion.obtenerConexion();
            String sql = "SELECT "
                    + "s.id_sancion, "
                    + "s.descripcion, "
                    + "s.fecha, "
                    + "s.tipo, "
                    + "CONCAT(tp.nombre, ' ', tp.apellido) AS tecnico, "
                    + "CONCAT(pa.nombre, ' ', pa.apellido) AS sancionado "
                    + "FROM sanciones s "
                    + "JOIN tecnico_prestamos tp ON s.sancionado_por = tp.id_tecnico_prestamos "
                    + "JOIN personal_academico pa ON s.id_personal_academico = pa.id_personal_academico";


            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt("id_sancion");
                fila[1] = rs.getString("descripcion");
                fila[2] = rs.getDate("fecha");
                fila[3] = rs.getString("tipo");
                fila[4] = rs.getString("tecnico");
                fila[5] = rs.getString("sancionado");

                modelo.addRow(fila);
            }

            TblSanciones.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sanciones: " + e.getMessage());
        }
    }
    public void cargarTablaPrestamos() {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT p.id_prestamo, l.Nombre_lab, CONCAT(pa.nombre, ' ', pa.apellido) AS docente, "
                    + "p.fecha, p.horario_inicio, p.horario_fin "
                    + "FROM prestamos p "
                    + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) TblPrestamosAntiguos.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                String nombreLab = rs.getString("Nombre_lab");
                Date fecha = rs.getDate("fecha");
                Time horaInicio = rs.getTime("horario_inicio");
                Time horaFin = rs.getTime("horario_fin");
                String docente = rs.getString("docente");

                model.addRow(new Object[]{
                    idPrestamo, nombreLab, fecha, horaInicio, horaFin, docente
                });
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    private void cargarDisponibilidad() {
        DefaultTableModel modelo = (DefaultTableModel) TblPrestamosAntiguos.getModel();
        modelo.setRowCount(0);

        java.util.Date fechaSeleccionada = FechaPrestamo.getDate();

        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha.");
            return;
        }

        String fechaSQL = new SimpleDateFormat("yyyy-MM-dd").format(fechaSeleccionada);

        String query = "SELECT p.id_prestamo, l.Nombre_lab, CONCAT(pa.nombre, ' ', pa.apellido) AS docente, "
                + "p.fecha, p.horario_inicio, p.horario_fin "
                + "FROM prestamos p "
                + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico "
                + "WHERE p.fecha = ?";

        try (Connection con = Conexion.obtenerConexion(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, fechaSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                String nombreLab = rs.getString("Nombre_lab");
                Date fecha = rs.getDate("fecha");
                Time horaInicio = rs.getTime("horario_inicio");
                Time horaFin = rs.getTime("horario_fin");
                String docente = rs.getString("docente");

                modelo.addRow(new Object[]{
                    idPrestamo, nombreLab, fecha, horaInicio, horaFin, docente
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + ex.getMessage());
        }
    }


    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        String descripcion = Descripciontxt.getText().trim();
        Date fecha = Fechadate.getDate();
        String tipo = (String) TipoBox.getSelectedItem();
        String nombreTecnico = Nombre.getText().trim();

        if (descripcion.isEmpty() || fecha == null || tipo == null || idPrestamoSeleccionado == -1 || nombreTecnico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos, seleccionar préstamo y técnico.");
            return;
        }

        try (Connection con = Conexion.obtenerConexion()) {

            // Obtener ID del técnico por nombre completo
            PreparedStatement psBuscar = con.prepareStatement(
                    "SELECT id_tecnico_prestamos FROM tecnico_prestamos WHERE CONCAT(nombre, ' ', apellido) = ?"
            );
            psBuscar.setString(1, nombreTecnico);
            ResultSet rs = psBuscar.executeQuery();

            if (rs.next()) {
                int idTecnico = rs.getInt("id_tecnico_prestamos");

                // Obtener ID del docente (personal académico) asociado al préstamo
                int idPersonalAcademico = -1;
                PreparedStatement psDocente = con.prepareStatement(
                        "SELECT id_personal_academico FROM prestamos WHERE id_prestamo = ?"
                );
                psDocente.setInt(1, idPrestamoSeleccionado);
                ResultSet rsDocente = psDocente.executeQuery();

                if (rsDocente.next()) {
                    idPersonalAcademico = rsDocente.getInt("id_personal_academico");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el docente asociado al préstamo.");
                    return;
                }

                // Insertar sanción
                PreparedStatement psInsert = con.prepareStatement(
                        "INSERT INTO sanciones (id_prestamo, id_personal_academico, descripcion, fecha, tipo, sancionado_por) "
                        + "VALUES (?, ?, ?, ?, ?, ?)"
                );
                psInsert.setInt(1, idPrestamoSeleccionado);
                psInsert.setInt(2, idPersonalAcademico);
                psInsert.setString(3, descripcion);
                psInsert.setDate(4, new java.sql.Date(fecha.getTime()));
                psInsert.setString(5, tipo);
                psInsert.setInt(6, idTecnico);

                psInsert.executeUpdate();
                JOptionPane.showMessageDialog(this, "Sanción registrada correctamente.");
                cargarTablaSanciones();

            } else {
                JOptionPane.showMessageDialog(this, "Técnico no encontrado.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void TipoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoBoxActionPerformed

    private void BuscarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarPrestamoActionPerformed
        cargarDisponibilidad();
    }//GEN-LAST:event_BuscarPrestamoActionPerformed

    private void TblPrestamosAntiguosmouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPrestamosAntiguosmouseClicked
        int filaSeleccionada = TblPrestamosAntiguos.getSelectedRow();

        if (filaSeleccionada != -1) {
            idPrestamoSeleccionado = (int) TblPrestamosAntiguos.getValueAt(filaSeleccionada, 0); // Columna 0 = id_prestamo
            String nombreDocente = TblPrestamosAntiguos.getValueAt(filaSeleccionada, 5).toString();
            NombreSancionadotxt.setText(nombreDocente); // Mostrar nombre del sancionado (solo visual)
        }
    }//GEN-LAST:event_TblPrestamosAntiguosmouseClicked

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
        // TODO add your handling code here:
        // Crear la ventana de FormularioPrestamo
        InicioAdmiTecnicoPrestamos inicioP = new InicioAdmiTecnicoPrestamos(idusuario);
        inicioP.setLocationRelativeTo(null); // Centrar la ventana
        inicioP.setVisible(true);
        // Cerrar o esconder la ventana actual
        this.dispose(); // Cierra completamente la ventana actual
        // o this.setVisible(false); // Solo la oculta, según lo que prefieras
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnListaLaboratoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaLaboratoriosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListaLaboratoriosMouseExited

    private void btnListaLaboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaLaboratoriosActionPerformed
        ListaLaboratorios listLab = new ListaLaboratorios(idusuario);
        listLab.setLocationRelativeTo(null); 
        listLab.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnListaLaboratoriosActionPerformed

    private void btnListaPrestamosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaPrestamosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListaPrestamosMouseExited

    private void btnListaPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaPrestamosActionPerformed
        ListaPrestamos listPrest = new ListaPrestamos(idusuario);
        listPrest.setLocationRelativeTo(null);
        listPrest.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnListaPrestamosActionPerformed

    private void btnSolicitudesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitudesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitudesMouseExited

    private void btnSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudesActionPerformed
        SolicitudPendiente solicitud = new SolicitudPendiente(idusuario);
        solicitud.setLocationRelativeTo(null);
        solicitud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSolicitudesActionPerformed

    private void btnSancionesDesignarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSancionesDesignarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSancionesDesignarMouseExited

    private void btnSancionesDesignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSancionesDesignarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSancionesDesignarActionPerformed

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        boolean estaVisible = panelSubReportes.isVisible();

        if (estaVisible) {
            cambiarColorBotonAlPresionar(false);
            int yInicio = panelSubReportes.getY();
            int yFinal = yInicio - 80;

            
            animarRotacionFlecha(false);
            flechaAbajo = true;

            new Thread(() -> {
                try {
                    Thread.sleep(50); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int y = yInicio; y >= yFinal; y -= 5) {
                    final int posY = y;
                    SwingUtilities.invokeLater(() -> {
                        panelSubReportes.setLocation(panelSubReportes.getX(), posY);
                    });
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

               
                SwingUtilities.invokeLater(() -> panelSubReportes.setVisible(false));
            }).start();

        } else {
            cambiarColorBotonAlPresionar(true);
            int yInicio = panelSubReportes.getY();
            int yFinal = yInicio + 120;

            
            animarRotacionFlecha(true);
            flechaAbajo = false;

            panelSubReportes.setVisible(true);

            new Thread(() -> {
                try {
                    Thread.sleep(50); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int y = yInicio; y <= yFinal; y += 5) {
                    final int posY = y;
                    SwingUtilities.invokeLater(() -> {
                        panelSubReportes.setLocation(panelSubReportes.getX(), posY);
                    });
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnMaterialesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaterialesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaterialesMouseExited

    private void btnMaterialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaterialesActionPerformed
        Materiales materiales = new Materiales(idusuario);
        materiales.setLocationRelativeTo(null);
        materiales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMaterialesActionPerformed

    private void btnComputadorasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComputadorasMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComputadorasMouseExited

    private void btnComputadorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputadorasActionPerformed
        MaterialesHardware hardware = new MaterialesHardware(idusuario);
        hardware.setLocationRelativeTo(null);
        hardware.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnComputadorasActionPerformed

    private void BuscarRuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarRuActionPerformed
        VerTecnicosPrestamos ver = new VerTecnicosPrestamos(Nombre);
        ver.setLocationRelativeTo(null);
        ver.setVisible(true);
    }//GEN-LAST:event_BuscarRuActionPerformed

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

    private void btnReporteLaboratoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteLaboratoriosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteLaboratoriosMouseExited

    private void btnReporteLaboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteLaboratoriosActionPerformed
        ReportesPrestamos reportepres = new ReportesPrestamos(idusuario);
        reportepres.setLocationRelativeTo(null);
        reportepres.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReporteLaboratoriosActionPerformed

    private void btnReporteMantenimientoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteMantenimientoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteMantenimientoMouseExited

    private void btnReporteMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteMantenimientoActionPerformed
        ReportesMantenimiento reporteman = new ReportesMantenimiento(idusuario);
        reporteman.setLocationRelativeTo(null);
        reporteman.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReporteMantenimientoActionPerformed

    private void btnReporteSancionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteSancionesActionPerformed
        ReportesSanciones reportesan = new ReportesSanciones(idusuario);
        reportesan.setLocationRelativeTo(null);
        reportesan.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReporteSancionesActionPerformed

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
            java.util.logging.Logger.getLogger(SancionesParaDesignar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SancionesParaDesignar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SancionesParaDesignar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SancionesParaDesignar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SancionesParaDesignar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AsignacionSancion;
    private javax.swing.JButton BuscarPrestamo;
    private javax.swing.JButton BuscarRu;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JTextArea Descripciontxt;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel FechaPrestam;
    private com.toedter.calendar.JDateChooser FechaPrestamo;
    private com.toedter.calendar.JDateChooser Fechadate;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoBlanco1;
    private javax.swing.JLabel FondoBlanco2;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JLabel Formulario1;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel LogoSale2;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel NombreSancionado;
    private javax.swing.JTextField NombreSancionadotxt;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TblPrestamosAntiguos;
    private javax.swing.JTable TblSanciones;
    private javax.swing.JLabel Tipo;
    private javax.swing.JComboBox<String> TipoBox;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComputadoras;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnListaLaboratorios;
    private javax.swing.JButton btnListaPrestamos;
    private javax.swing.JButton btnMateriales;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnReporteLaboratorios;
    private javax.swing.JButton btnReporteMantenimiento;
    private javax.swing.JButton btnReporteSanciones;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSancionesDesignar;
    private javax.swing.JButton btnSolicitudes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFlecha;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSubReportes;
    private javax.swing.JLabel perfil;
    // End of variables declaration//GEN-END:variables
}
