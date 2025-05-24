/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Reportes;

import Administrador.LogManager;
import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
import static ConexionLogin.SesionUsuario.username;
import Materiales.Materiales;
import Materiales.MaterialesHardware;
import Sanciones.SancionesParaDesignar;
import TecnicoDePrestamos.InicioAdmiTecnicoPrestamos;
import TecnicoDePrestamos.ListaLaboratorios;
import TecnicoDePrestamos.ListaPrestamos;
import TecnicoDePrestamos.SolicitudPendiente;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Rafael
 */
public class ReportesSanciones extends javax.swing.JFrame {

    private int idusuario;

    /**
     * Creates new form InicioPersonalAcademico
     * @param idusuario
     */
    public ReportesSanciones(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
        aplicarColorFilasAlternadas(tblSanciones);
        cargarNombreCompleto();
        iconoOriginal = lblFlecha.getIcon();
        panelSubReportes.setLocation(panelSubReportes.getX(), -70);
        panelSubReportes.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cargarTablaTodo();
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
        private boolean flechaAbajo = true; // empieza apuntando hacia abajo
private Icon iconoOriginal;

    private ReportesSanciones() {
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

        panelSidebar = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        LogoSale2 = new javax.swing.JLabel();
        btnListaLaboratorios = new javax.swing.JButton();
        btnListaPrestamos = new javax.swing.JButton();
        btnSolicitudes = new javax.swing.JButton();
        btnSancionesDesignar = new javax.swing.JButton();
        lblFlecha = new javax.swing.JLabel();
        btnReportes = new javax.swing.JButton();
        btnMateriales = new javax.swing.JButton();
        btnComputadoras = new javax.swing.JButton();
        panelSubReportes = new javax.swing.JPanel();
        btnReporteLaboratorios = new javax.swing.JButton();
        btnReporteMantenimiento = new javax.swing.JButton();
        btnReporteSanciones = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();
        Nombretxt = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanciones = new javax.swing.JTable();
        AsignacionSancion1 = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();
        Imprimir = new javax.swing.JButton();
        btnGraficar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        FechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        FechaHasta = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        RUtxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        EstadoSancion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        Limpiar = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        AsignacionSancion2 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        perfil1 = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();

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

        lblFlecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MostrarMasBoton.png"))); // NOI18N
        panelSidebar.add(lblFlecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 382, 20, 20));

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
        panelSubReportes.add(btnReporteLaboratorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 229, 40));

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
        panelSubReportes.add(btnReporteMantenimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 229, 40));

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

        panelSidebar.add(panelSubReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 229, 290));

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 870));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        Nombretxt.setBackground(new java.awt.Color(255, 255, 255));
        Nombretxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nombretxt.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 15, 240, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane1.setViewportView(tblSanciones);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1440, 610));

        AsignacionSancion1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        AsignacionSancion1.setText("Reportes de Sanciones");
        jPanel3.add(AsignacionSancion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, -1));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel3.add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 680, 1450, 320));

        Imprimir.setBackground(new java.awt.Color(51, 153, 0));
        Imprimir.setForeground(new java.awt.Color(255, 255, 255));
        Imprimir.setText("Imprimir");
        Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirActionPerformed(evt);
            }
        });
        jPanel3.add(Imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 10, -1, -1));

        btnGraficar.setText("Graficar");
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGraficar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1382, 10, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1460, 660));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Desde:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, 20));
        jPanel1.add(FechaDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 250, -1));

        jLabel1.setText("Hasta:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 10, -1, 20));
        jPanel1.add(FechaHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, 250, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RUtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RUtxtActionPerformed(evt);
            }
        });
        jPanel4.add(RUtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 180, -1));

        jLabel5.setText("Usuario:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        EstadoSancion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Daño de Material", "Pérdida de material", "Incumplimiento de horario", "Otro"}));
        jPanel4.add(EstadoSancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 180, -1));

        jLabel4.setText("Estado:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 50));

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1460, -1));

        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        jPanel5.add(Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 4, -1, -1));

        Buscar.setBackground(new java.awt.Color(29, 41, 57));
        Buscar.setForeground(new java.awt.Color(255, 255, 255));
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        jPanel5.add(Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 4, -1, -1));

        AsignacionSancion2.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        AsignacionSancion2.setText("Filtros");
        jPanel5.add(AsignacionSancion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 300, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1460, 80));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1480, 770));

        btnMenu.setBackground(new java.awt.Color(178, 191, 207));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonBurger3.png"))); // NOI18N
        btnMenu.setBorder(null);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        getContentPane().add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 15, 30, 30));

        perfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

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
    public void cargarTablaTodo() {
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
    private void ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirActionPerformed
        String ru = RUtxt.getText().trim();
        String estado = EstadoSancion.getSelectedItem().toString();
        Date fechaDesde = FechaDesde.getDate();
        Date fechaHasta = FechaHasta.getDate();

        ImprimirReportesMantenimiento reportesMant = new ImprimirReportesMantenimiento(ru, estado, fechaDesde, fechaHasta);
        reportesMant.setLocationRelativeTo(null); // Centrar la ventana
        reportesMant.setVisible(true);
    }//GEN-LAST:event_ImprimirActionPerformed

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        String[] opciones = {
            "Sanciones por tipo (barras)",
            "Sanciones por técnico (torta)",
            "Sanciones por sancionado (torta)"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el tipo de gráfico a mostrar:",
                "Opciones de gráfico",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == null) {
            return; // Cancelado
        }

        DefaultTableModel model = (DefaultTableModel) tblSanciones.getModel();

        switch (seleccion) {
            case "Sanciones por tipo (barras)":
                Map<String, Integer> sancionesPorTipo = new HashMap<>();
                for (int i = 0; i < model.getRowCount(); i++) {
                    String tipo = model.getValueAt(i, 3).toString();
                    sancionesPorTipo.put(tipo, sancionesPorTipo.getOrDefault(tipo, 0) + 1);
                }
                DefaultCategoryDataset datasetTipo = new DefaultCategoryDataset();
                for (Map.Entry<String, Integer> entry : sancionesPorTipo.entrySet()) {
                    datasetTipo.addValue(entry.getValue(), "Sanciones", entry.getKey());
                }
                JFreeChart barChartTipo = ChartFactory.createBarChart(
                        "Sanciones por Tipo",
                        "Tipo de Sanción",
                        "Cantidad",
                        datasetTipo,
                        PlotOrientation.VERTICAL,
                        true, true, false);
                mostrarGrafico(barChartTipo, "Sanciones por Tipo");
                break;

            case "Sanciones por técnico (torta)":
                Map<String, Integer> sancionesPorTecnico = new HashMap<>();
                for (int i = 0; i < model.getRowCount(); i++) {
                    String tecnico = model.getValueAt(i, 4).toString();
                    sancionesPorTecnico.put(tecnico, sancionesPorTecnico.getOrDefault(tecnico, 0) + 1);
                }
                DefaultPieDataset datasetTecnico = new DefaultPieDataset();
                for (Map.Entry<String, Integer> entry : sancionesPorTecnico.entrySet()) {
                    datasetTecnico.setValue(entry.getKey(), entry.getValue());
                }
                JFreeChart pieChartTecnico = ChartFactory.createPieChart(
                        "Sanciones por Técnico",
                        datasetTecnico,
                        true, true, false);
                mostrarGrafico(pieChartTecnico, "Sanciones por Técnico");
                break;

            case "Sanciones por sancionado (torta)":
                Map<String, Integer> sancionesPorSancionado = new HashMap<>();
                for (int i = 0; i < model.getRowCount(); i++) {
                    String sancionado = model.getValueAt(i, 5).toString();
                    sancionesPorSancionado.put(sancionado, sancionesPorSancionado.getOrDefault(sancionado, 0) + 1);
                }
                DefaultPieDataset datasetSancionado = new DefaultPieDataset();
                for (Map.Entry<String, Integer> entry : sancionesPorSancionado.entrySet()) {
                    datasetSancionado.setValue(entry.getKey(), entry.getValue());
                }
                JFreeChart pieChartSancionado = ChartFactory.createPieChart(
                        "Sanciones por Sancionado",
                        datasetSancionado,
                        true, true, false);
                mostrarGrafico(pieChartSancionado, "Sanciones por Sancionado");
                break;

            default:
                JOptionPane.showMessageDialog(this, "Opción no implementada");
                break;
        }
    }//GEN-LAST:event_btnGraficarActionPerformed
    private void mostrarGrafico(JFreeChart chart, String titulo) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        chartPanel.setMouseWheelEnabled(true);

        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
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
        InicioAdmiTecnicoPrestamos inicioP = new InicioAdmiTecnicoPrestamos(idusuario);
        inicioP.setLocationRelativeTo(null);
        inicioP.setVisible(true);
        this.dispose();
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
            int yFinal = yInicio + 80;

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
        // TODO add your handling code here:
        // Crear la ventana de FormularioPrestamo
        Materiales materiales = new Materiales(idusuario);
        materiales.setLocationRelativeTo(null); // Centrar la ventana
        materiales.setVisible(true);
        // Cerrar o esconder la ventana actual
        this.dispose(); // Cierra completamente la ventana actual
        // o this.setVisible(false); // Solo la oculta, según lo que prefieras
    }//GEN-LAST:event_btnMaterialesActionPerformed

    private void btnComputadorasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComputadorasMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComputadorasMouseExited

    private void btnComputadorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputadorasActionPerformed
        // TODO add your handling code here:
        // Crear la ventana de FormularioPrestamo
        MaterialesHardware hardware = new MaterialesHardware(idusuario);
        hardware.setLocationRelativeTo(null); // Centrar la ventana
        hardware.setVisible(true);
        // Cerrar o esconder la ventana actual
        this.dispose(); // Cierra completamente la ventana actual
        // o this.setVisible(false); // Solo la oculta, según lo que prefieras
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

    private void btnReporteSancionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteSancionesActionPerformed
        ReportesSanciones reportesan = new ReportesSanciones(idusuario);
        reportesan.setLocationRelativeTo(null);
        reportesan.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReporteSancionesActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        String tipo = EstadoSancion.getSelectedItem().toString();
        String ru = RUtxt.getText().trim();

        Date fechaInicio = FechaDesde.getDate();
        Date fechaFin = FechaHasta.getDate();

        String query = "SELECT s.id_sancion, s.descripcion, s.fecha, s.tipo, "
        + "CONCAT(tp.nombre, ' ', tp.apellido) AS tecnico_nombre, "
        + "CONCAT(pa.nombre, ' ', pa.apellido) AS personal_nombre "
        + "FROM sanciones s "
        + "INNER JOIN personal_academico pa ON s.id_personal_academico = pa.id_personal_academico "
        + "INNER JOIN tecnico_prestamos tp ON s.sancionado_por = tp.id_tecnico_prestamos "
        + "WHERE 1=1 ";
        List<Object> parametros = new ArrayList<>();

        if (!"Todos".equals(tipo)) {
            query += " AND s.tipo = ?";
            parametros.add(tipo);
        }

        if (!ru.isEmpty()) {
            query += " AND pa.RU = ?";
            parametros.add(ru);
        }
        if (fechaInicio != null && fechaFin != null) {
            query += " AND s.fecha BETWEEN ? AND ?";
            parametros.add(new java.sql.Date(fechaInicio.getTime()));
            parametros.add(new java.sql.Date(fechaFin.getTime()));
        }
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(query);

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblSanciones.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int idMantenimiento = rs.getInt("id_sancion");
                String descripcion = rs.getString("descripcion");
                Date fecha = rs.getDate("fecha");
                String tiposancion = rs.getString("tipo");
                String tecnico = rs.getString("tecnico_nombre");
                String sancionado = rs.getString("personal_nombre");

                model.addRow(new Object[]{
                    idMantenimiento, descripcion, fecha, tiposancion, tecnico, sancionado
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar las sanciones.");
        }
    }//GEN-LAST:event_BuscarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        EstadoSancion.setSelectedIndex(0);
        RUtxt.setText("");
        FechaDesde.setDate(null);
        FechaHasta.setDate(null);
        cargarTablaTodo();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void RUtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RUtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RUtxtActionPerformed

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
            java.util.logging.Logger.getLogger(ReportesSanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportesSanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportesSanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportesSanciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ReportesSanciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AsignacionSancion1;
    private javax.swing.JLabel AsignacionSancion2;
    private javax.swing.JButton Buscar;
    private javax.swing.JComboBox<String> EstadoSancion;
    private com.toedter.calendar.JDateChooser FechaDesde;
    private com.toedter.calendar.JDateChooser FechaHasta;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JButton Imprimir;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel LogoSale2;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JTextField RUtxt;
    private javax.swing.JLabel Superior;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComputadoras;
    private javax.swing.JButton btnGraficar;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFlecha;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSubReportes;
    private javax.swing.JLabel perfil;
    private javax.swing.JLabel perfil1;
    private javax.swing.JTable tblSanciones;
    // End of variables declaration//GEN-END:variables
}
