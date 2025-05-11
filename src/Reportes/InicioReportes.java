/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Reportes;

import ConexionLogin.Login;
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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author Rafael
 */
public class InicioReportes extends javax.swing.JFrame {

    /**
     * Creates new form InicioReportes
     */
    public InicioReportes() {
        initComponents();
        iconoOriginal = lblFlecha.getIcon();
panelSubReportes.setLocation(panelSubReportes.getX(), -70); // fuera de vista por arriba
panelSubReportes.setVisible(false);

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
    private boolean flechaAbajo = true; // empieza apuntando hacia abajo
private Icon iconoOriginal;
private void mostrarSubReportes() {
    panelSubReportes.setVisible(true);
    int yFinal = 120; // posición Y donde debe quedar el panel
    int yInicio = -70; // posición inicial fuera de vista
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
        btnReportes.setBackground(colorPresionado); // Color cuando se presiona
    } else {
        btnReportes.setBackground(colorDefault); // Restaurar el color por defecto
    }
}

private Icon rotarIcono(Icon icono, double angulo) {
    int w = icono.getIconWidth();
    int h = icono.getIconHeight();

    // Calcular la diagonal máxima
    int size = (int) Math.ceil(Math.sqrt(w * w + h * h));

    BufferedImage imagenOriginal = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2dOriginal = imagenOriginal.createGraphics();
    icono.paintIcon(null, g2dOriginal, 0, 0);
    g2dOriginal.dispose();

    BufferedImage imagenRotada = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = imagenRotada.createGraphics();

    // Suavizado y rotación centrada
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    AffineTransform at = new AffineTransform();
    at.translate(size / 2.0, size / 2.0); // mover al centro
    at.rotate(-angulo);  // Usamos el signo negativo para rotar hacia la derecha
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
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        btnListaLaboratorios = new javax.swing.JButton();
        btnListaPrestamos = new javax.swing.JButton();
        btnSolicitudes = new javax.swing.JButton();
        btnSancionesDesignar = new javax.swing.JButton();
        btnMateriales = new javax.swing.JButton();
        btnComputadoras = new javax.swing.JButton();
        lblFlecha = new javax.swing.JLabel();
        btnReportes = new javax.swing.JButton();
        panelSubReportes = new javax.swing.JPanel();
        btnReporteLaboratorios = new javax.swing.JButton();
        btnReporteMantenimiento = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();
        AsignacionSancion = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
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

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

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

        LogoSale1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoUSB.png"))); // NOI18N
        panelSidebar.add(LogoSale1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 160, 60));

        btnListaLaboratorios.setBackground(new java.awt.Color(29, 41, 57));
        btnListaLaboratorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnListaLaboratorios.setForeground(new java.awt.Color(241, 241, 241));
        btnListaLaboratorios.setText("Lista de Laboratorios");
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
        btnListaPrestamos.setText("Lista de Prestamos");
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
        btnSancionesDesignar.setText("Sanciones para Designar");
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

        btnMateriales.setBackground(new java.awt.Color(29, 41, 57));
        btnMateriales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMateriales.setForeground(new java.awt.Color(241, 241, 241));
        btnMateriales.setText("Materiales de Laboratorio");
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

        panelSubReportes.setBackground(new java.awt.Color(16, 23, 32));
        panelSubReportes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReporteLaboratorios.setBackground(new java.awt.Color(16, 23, 32));
        btnReporteLaboratorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteLaboratorios.setForeground(new java.awt.Color(241, 241, 241));
        btnReporteLaboratorios.setText("Laboratorios");
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

        panelSidebar.add(panelSubReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 229, 290));

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 860));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 860));

        AsignacionSancion.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        AsignacionSancion.setText("Selección De reportes");
        getContentPane().add(AsignacionSancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 230, -1));

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

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 0, 740));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        InicioAdmiTecnicoPrestamos inicioP = new InicioAdmiTecnicoPrestamos();
        inicioP.setLocationRelativeTo(null);
        inicioP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnListaLaboratoriosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaLaboratoriosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListaLaboratoriosMouseExited

    private void btnListaLaboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaLaboratoriosActionPerformed
        
        ListaLaboratorios listLab = new ListaLaboratorios();
        listLab.setLocationRelativeTo(null);
        listLab.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnListaLaboratoriosActionPerformed

    private void btnListaPrestamosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaPrestamosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListaPrestamosMouseExited

    private void btnListaPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaPrestamosActionPerformed
        
        ListaPrestamos listPrest = new ListaPrestamos();
        listPrest.setLocationRelativeTo(null);
        listPrest.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnListaPrestamosActionPerformed

    private void btnSolicitudesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitudesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitudesMouseExited

    private void btnSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudesActionPerformed
        SolicitudPendiente solicitud = new SolicitudPendiente();
        solicitud.setLocationRelativeTo(null);
        solicitud.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnSolicitudesActionPerformed

    private void btnSancionesDesignarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSancionesDesignarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSancionesDesignarMouseExited

    private void btnSancionesDesignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSancionesDesignarActionPerformed
        SancionesParaDesignar sancionesDesig = new SancionesParaDesignar();
        sancionesDesig.setLocationRelativeTo(null); // Centrar la ventana
        sancionesDesig.setVisible(true);
        // Cerrar o esconder la ventana actual
        this.dispose(); // Cierra completamente la ventana actual
        // o this.setVisible(false); // Solo la oculta, según lo que prefieras
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
        
        Materiales materiales = new Materiales();
        materiales.setLocationRelativeTo(null); 
        materiales.setVisible(true);
        
        this.dispose();
        
    }//GEN-LAST:event_btnMaterialesActionPerformed

    private void btnComputadorasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComputadorasMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComputadorasMouseExited

    private void btnComputadorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComputadorasActionPerformed
        
        MaterialesHardware hardware = new MaterialesHardware();
        hardware.setLocationRelativeTo(null);
        hardware.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnComputadorasActionPerformed

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
        ReportesPrestamos reportepres = new ReportesPrestamos();
        reportepres.setLocationRelativeTo(null);
        reportepres.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReporteLaboratoriosActionPerformed

    private void btnReporteMantenimientoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteMantenimientoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReporteMantenimientoMouseExited

    private void btnReporteMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteMantenimientoActionPerformed
        ReportesMantenimiento reporteman = new ReportesMantenimiento();
        reporteman.setLocationRelativeTo(null);
        reporteman.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReporteMantenimientoActionPerformed

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
                new InicioReportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AsignacionSancion;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JLabel Superior;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComputadoras;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnListaLaboratorios;
    private javax.swing.JButton btnListaPrestamos;
    private javax.swing.JButton btnMateriales;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnReporteLaboratorios;
    private javax.swing.JButton btnReporteMantenimiento;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSancionesDesignar;
    private javax.swing.JButton btnSolicitudes;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblFlecha;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSubReportes;
    private javax.swing.JLabel perfil;
    // End of variables declaration//GEN-END:variables
}
