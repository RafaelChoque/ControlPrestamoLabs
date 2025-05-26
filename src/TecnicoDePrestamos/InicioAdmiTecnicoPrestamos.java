package TecnicoDePrestamos;

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
import Reportes.VentanaGraficosMantenimientoInternal;
import Reportes.VentanaGraficosMultiplesMantenimiento;
import Reportes.VentanaGraficosMultiplesPrestamos;
import Reportes.VentanaGraficosMultiplesSanciones;
import Reportes.VentanaGraficosPrestamosInternal;
import Reportes.VentanaGraficosSancionesInternal;
import Sanciones.SancionesParaDesignar;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Jose Sebastian
 */
public class InicioAdmiTecnicoPrestamos extends javax.swing.JFrame {
    private int idusuario;
    public static String mensajeCompartido = "";
    /**
     * * Creates new form InicioPersonalAcademico
     * @param idusuario
     */
    public InicioAdmiTecnicoPrestamos(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
        cargarNombreCompleto();
        cargarTablaTodoSanciones();
        cargarTablaTodoMantenimiento();
        cargarTablaTodoPrestamos();
        cargarGraficoMantenimiento();
        cargarGraficoPrestamos();
        cargarGraficoSanciones();

        iconoOriginal = lblFlecha.getIcon();
        panelSubReportes.setLocation(panelSubReportes.getX(), -70);
        panelSubReportes.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        FondoBlanco.setFocusable(true);
        
        //mantenimiento
        tblMantenimiento.setVisible(false);
        tblMantenimiento.setEnabled(false);
        tblMantenimiento.setFocusable(false);
        tblMantenimiento.setRequestFocusEnabled(false);
        jScrollPane2.setVisible(false);
        jScrollPane2.setEnabled(false);
        jScrollPane2.setFocusable(false);
        //prestamos
        tblPrestamos.setVisible(false);
        tblPrestamos.setEnabled(false);
        tblPrestamos.setFocusable(false);
        tblPrestamos.setRequestFocusEnabled(false);
        jScrollPane3.setVisible(false);
        jScrollPane3.setEnabled(false);
        jScrollPane3.setFocusable(false);
        //sanciones
        tblSanciones.setVisible(false);
        tblSanciones.setEnabled(false);
        tblSanciones.setFocusable(false);
        tblSanciones.setRequestFocusEnabled(false);
        jScrollPane4.setVisible(false);
        jScrollPane4.setEnabled(false);
        jScrollPane4.setFocusable(false);
        
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
        private boolean flechaAbajo = true; // empieza apuntando hacia abajo
private Icon iconoOriginal;

    private InicioAdmiTecnicoPrestamos() {
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
        btnMenu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        InicioPersonal = new javax.swing.JLabel();
        FondoBlanco1 = new javax.swing.JLabel();
        dato = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        InicioPersonal1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Mensajestxt = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        AsignarMensajes = new javax.swing.JButton();
        AsignarMensajes1 = new javax.swing.JButton();
        InternalMantenimiento = new javax.swing.JDesktopPane();
        InternalSanciones = new javax.swing.JDesktopPane();
        InternalPrestamos = new javax.swing.JDesktopPane();
        dato1 = new javax.swing.JLabel();
        dato2 = new javax.swing.JLabel();
        dato3 = new javax.swing.JLabel();
        btnAccesoGraficosGenerales = new javax.swing.JButton();
        perfil = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanciones = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMantenimiento = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();
        panelSidebar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblFlecha = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnListaLaboratorios = new javax.swing.JButton();
        btnListaPrestamos = new javax.swing.JButton();
        btnSancionesDesignar = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        btnSolicitudes = new javax.swing.JButton();
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
        Nombretxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nombretxt.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 15, 240, 30));

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

        InicioPersonal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        InicioPersonal.setText("Inicio");
        jPanel2.add(InicioPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, 30));

        FondoBlanco1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 10, 10));

        dato.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        dato.setText("Gráfico de Sanciones");
        jPanel2.add(dato, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 160, 170, -1));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Zona Achachicala, Av. Chacaltaya Nro. 1258");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ubicacion (1).png"))); // NOI18N
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 30, 50));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 500, 70));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ProyectoLosJackson@gmail.com");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/email.png"))); // NOI18N
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 50, 50));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 700, 480, 70));

        InicioPersonal1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        InicioPersonal1.setText("Anuncios");
        jPanel2.add(InicioPersonal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, -1));

        Mensajestxt.setColumns(20);
        Mensajestxt.setRows(5);
        jScrollPane1.setViewportView(Mensajestxt);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1440, 70));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jose     67106924");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Rafael 75298318");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Erlan   69938519");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 140, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Contactos");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telefono.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 700, 500, 70));

        AsignarMensajes.setBackground(new java.awt.Color(29, 41, 57));
        AsignarMensajes.setForeground(new java.awt.Color(255, 255, 255));
        AsignarMensajes.setText("Publicar");
        AsignarMensajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AsignarMensajesActionPerformed(evt);
            }
        });
        jPanel2.add(AsignarMensajes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 80, -1));

        AsignarMensajes1.setBackground(new java.awt.Color(29, 41, 57));
        AsignarMensajes1.setForeground(new java.awt.Color(255, 255, 255));
        AsignarMensajes1.setText("Eliminar");
        AsignarMensajes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AsignarMensajes1ActionPerformed(evt);
            }
        });
        jPanel2.add(AsignarMensajes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 80, -1));
        jPanel2.add(InternalMantenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 480, 510));
        jPanel2.add(InternalSanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, 490, 510));
        jPanel2.add(InternalPrestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 480, 510));

        dato1.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        dato1.setText("Sistema de Control y Prestamo de Laboratorios de Hardware, Redes, Telecomunicaciones y Electronica");
        jPanel2.add(dato1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        dato2.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        dato2.setText("Gráfico de Mantenimientos");
        jPanel2.add(dato2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 210, -1));

        dato3.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        dato3.setText("Gráfico de Prestamos");
        jPanel2.add(dato3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 170, -1));

        btnAccesoGraficosGenerales.setBackground(new java.awt.Color(0, 0, 0));
        btnAccesoGraficosGenerales.setForeground(new java.awt.Color(255, 255, 255));
        btnAccesoGraficosGenerales.setText("Acceso a Gráficos Generales");
        btnAccesoGraficosGenerales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccesoGraficosGeneralesActionPerformed(evt);
            }
        });
        jPanel2.add(btnAccesoGraficosGenerales, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 180, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1490, 770));

        perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 860));

        tblSanciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Fecha", "Tipo", "Tecnico", "Sancionado"
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
        jScrollPane4.setViewportView(tblSanciones);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 10, 30));

        tblMantenimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Laboratorio", "Estado", "Fecha", "Descripcion", "Realizado Por"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane2.setViewportView(tblMantenimiento);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 10, 30));

        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Docente", "Seccion", "Laboratorio", "Fecha", "Horario Inicio", "Horario Fin", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblPrestamos);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 10, 30));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1450, 740));

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Panel de Control");
        panelSidebar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        lblFlecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MostrarMasBoton.png"))); // NOI18N
        panelSidebar.add(lblFlecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 382, 20, 20));

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
    
        public void cargarTablaTodoSanciones() {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT s.id_sancion, s.descripcion, s.fecha, s.tipo, "
                + "CONCAT(tp.nombre, ' ', tp.apellido) AS tecnico_nombre, "
                + "CONCAT(pa.nombre, ' ', pa.apellido) AS personal_nombre "
                + "FROM sanciones s "
                + "INNER JOIN personal_academico pa ON s.id_personal_academico = pa.id_personal_academico "
                + "INNER JOIN tecnico_prestamos tp ON s.sancionado_por = tp.id_tecnico_prestamos ";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblSanciones.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int idSancion = rs.getInt("id_sancion");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                String tipo = rs.getString("tipo");
                String tecnico = rs.getString("tecnico_nombre");
                String personal = rs.getString("personal_nombre");
                

                model.addRow(new Object[]{
                    idSancion, descripcion, fecha, tipo, tecnico, personal
                });
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
            public void cargarTablaTodoMantenimiento() {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT m.id_mantenimiento, mh.nombre AS material_nombre, l.Codigo_lab, "
                    + "mh.estado, m.fecha, m.descripcion, CONCAT(te.nombre, ' ', te.apellido) AS tecnico_nombre "
                    + "FROM mantenimiento m "
                    + "INNER JOIN materiales_hardware mh ON m.id_material_hardware = mh.id_material_hardware "
                    + "INNER JOIN laboratorios l ON mh.ID_lab = l.ID_lab "
                    + "INNER JOIN tecnico_equipos te ON m.id_tecnico_equipos = te.id_tecnico_equipos ";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblMantenimiento.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int idMantenimiento = rs.getInt("id_mantenimiento");
                String material = rs.getString("material_nombre");
                String codigolab = rs.getString("Codigo_lab");
                String estado = rs.getString("estado");
                Date fecha = rs.getDate("fecha");
                String descripcion = rs.getString("descripcion");
                String tecnico = rs.getString("tecnico_nombre");
                

                model.addRow(new Object[]{
                    idMantenimiento, material, codigolab, estado, fecha, descripcion, tecnico
                });
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
                public void cargarTablaTodoPrestamos() {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT p.id_prestamo, l.Nombre_lab, CONCAT(pa.nombre, ' ', pa.apellido) AS docente, "
                    + "l.Seccion, p.fecha, p.horario_inicio, p.horario_fin, p.estado "
                    + "FROM prestamos p "
                    + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblPrestamos.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                String docente = rs.getString("docente");
                String seccion = rs.getString("Seccion");
                String nombreLab = rs.getString("Nombre_lab");
                Date fecha = rs.getDate("fecha");
                Time horaInicio = rs.getTime("horario_inicio");
                Time horaFin = rs.getTime("horario_fin");
                String estado = rs.getString("estado");
                

                model.addRow(new Object[]{
                    idPrestamo, docente, seccion, nombreLab, fecha, horaInicio, horaFin, estado
                });
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
    }
    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed

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

    private void btnSancionesDesignarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSancionesDesignarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSancionesDesignarMouseExited

    private void btnSancionesDesignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSancionesDesignarActionPerformed

        SancionesParaDesignar sancionesDesig = new SancionesParaDesignar(idusuario);
        sancionesDesig.setLocationRelativeTo(null);
        sancionesDesig.setVisible(true);
        this.dispose();
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

    private void btnSolicitudesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitudesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitudesMouseExited

    private void btnSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudesActionPerformed

        SolicitudPendiente solicitud = new SolicitudPendiente(idusuario);
        solicitud.setLocationRelativeTo(null);
        solicitud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSolicitudesActionPerformed

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

    private void AsignarMensajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AsignarMensajesActionPerformed
        mensajeCompartido = Mensajestxt.getText();  
        Mensajestxt.setText(mensajeCompartido);  
        JOptionPane.showMessageDialog(this, "Mensaje asignado correctamente.");
    }//GEN-LAST:event_AsignarMensajesActionPerformed

    private void AsignarMensajes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AsignarMensajes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsignarMensajes1ActionPerformed

    private void btnAccesoGraficosGeneralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccesoGraficosGeneralesActionPerformed
        String[] opciones = {
            "Gráficos de Préstamos",
            "Gráficos de Mantenimiento",
            "Gráficos de Sanciones", // solo si tienes esta ventana
            "Mostrar todos los gráficos"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el tipo de gráfico a mostrar:",
                "Opciones de gráficos",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) {
            return; // Usuario canceló
        }

        DefaultTableModel model1 = (DefaultTableModel) tblPrestamos.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tblMantenimiento.getModel();
        DefaultTableModel model3 = (DefaultTableModel) tblSanciones.getModel();

        switch (seleccion) {
            case "Gráficos de Préstamos":
                new VentanaGraficosMultiplesPrestamos(model1).setVisible(true);
                break;

            case "Gráficos de Mantenimiento":
                new VentanaGraficosMultiplesMantenimiento(model2).setVisible(true);
                break;

            case "Gráficos de Sanciones":
                new VentanaGraficosMultiplesSanciones(model3).setVisible(true);
                break;

            case "Mostrar todos los gráficos":
                new VentanaGraficosMultiplesPrestamos(model1).setVisible(true);
                new VentanaGraficosMultiplesMantenimiento(model2).setVisible(true);
                new VentanaGraficosMultiplesSanciones(model3).setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Opción no implementada");
                break;
        }
    }//GEN-LAST:event_btnAccesoGraficosGeneralesActionPerformed
    private void cargarGraficoMantenimiento() {
        DefaultTableModel model = (DefaultTableModel) tblMantenimiento.getModel();

        VentanaGraficosMantenimientoInternal internalMantenimiento = new VentanaGraficosMantenimientoInternal(model);

        InternalMantenimiento.removeAll();
        InternalMantenimiento.add(internalMantenimiento); // SIN constraints
        internalMantenimiento.setBounds(0, 0, 480, 510);
        internalMantenimiento.setVisible(true);

        InternalMantenimiento.revalidate();
        InternalMantenimiento.repaint();
    }

    private void cargarGraficoPrestamos() {
        DefaultTableModel model = (DefaultTableModel) tblPrestamos.getModel();

        VentanaGraficosPrestamosInternal internalPrestamos = new VentanaGraficosPrestamosInternal(model);

        InternalPrestamos.removeAll();
        InternalPrestamos.add(internalPrestamos); // SIN constraints
        internalPrestamos.setBounds(0, 0, 480, 510);
        internalPrestamos.setVisible(true);

        InternalPrestamos.revalidate();
        InternalPrestamos.repaint();
    }

    private void cargarGraficoSanciones() {
        DefaultTableModel model = (DefaultTableModel) tblSanciones.getModel();

        VentanaGraficosSancionesInternal internalSanciones = new VentanaGraficosSancionesInternal(model);

        InternalSanciones.removeAll();
        InternalSanciones.add(internalSanciones); // SIN constraints
        internalSanciones.setBounds(0, 0, 480, 510);
        internalSanciones.setVisible(true);

        InternalSanciones.revalidate();
        InternalSanciones.repaint();
    }


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
                new InicioAdmiTecnicoPrestamos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AsignarMensajes;
    private javax.swing.JButton AsignarMensajes1;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoBlanco1;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JLabel InicioPersonal;
    private javax.swing.JLabel InicioPersonal1;
    private javax.swing.JDesktopPane InternalMantenimiento;
    private javax.swing.JDesktopPane InternalPrestamos;
    private javax.swing.JDesktopPane InternalSanciones;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextArea Mensajestxt;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JLabel Superior;
    private javax.swing.JButton btnAccesoGraficosGenerales;
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
    private javax.swing.JLabel dato;
    private javax.swing.JLabel dato1;
    private javax.swing.JLabel dato2;
    private javax.swing.JLabel dato3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblFlecha;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSubReportes;
    private javax.swing.JLabel perfil;
    private javax.swing.JTable tblMantenimiento;
    private javax.swing.JTable tblPrestamos;
    private javax.swing.JTable tblSanciones;
    // End of variables declaration//GEN-END:variables
}
