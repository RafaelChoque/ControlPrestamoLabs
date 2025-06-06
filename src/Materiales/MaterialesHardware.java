/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Materiales;

import Administrador.LogManager;
import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
import static ConexionLogin.SesionUsuario.username;
import OpenAi.OpenAIClient;
import Reportes.ReportesMantenimiento;
import Reportes.ReportesPrestamos;
import Reportes.ReportesSanciones;
import Sanciones.SancionesParaDesignar;
import TecnicoDePrestamos.InicioAdmiTecnicoPrestamos;
import TecnicoDePrestamos.ListaLaboratorios;
import TecnicoDePrestamos.ListaPrestamos;
import TecnicoDePrestamos.SolicitudPendiente;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Rafael
 */
public class MaterialesHardware extends javax.swing.JFrame {

    private int idusuario;

    /**
     * Creates new form InicioPersonalAcademico
     * @param idusuario
     */



    public MaterialesHardware(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
                agregarMensajeEstilizado("¡Hola! Soy tu asistente. Puedes preguntarme cualquier cosa sobre cómo solicitar un laboratorio. Estoy aquí para ayudarte.", "asistente");
        OpenAIClient.setSystemMessage(
       "Eres un asistente amigable, claro y experto en el sistema de control y préstamos de laboratorios. " +
"Estás asistiendo al técnico de préstamos en la interfaz de 'Computadoras'. " +
"Aquí puedes registrar computadoras y asignarlas a sus respectivos laboratorios. Para agregar una computadora, completa los siguientes campos: " +
"código de la computadora, laboratorio al que pertenece (usa el botón 'Ver laboratorios' para seleccionarlo), tipo de equipo y número de serie. " +
"Después de llenar los datos, presiona el botón 'Agregar' para guardarla. " +
"También puedes modificar los datos de una computadora, habilitarla o deshabilitarla, y eliminarla si es necesario. " +
"Si tienes alguna duda, estoy aquí para ayudarte 😊."
);
        aplicarColorFilasAlternadas(tblMateriales);
        cargarNombreCompleto();
        iconoOriginal = lblFlecha.getIcon();
panelSubReportes.setLocation(panelSubReportes.getX(), -70);
panelSubReportes.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Laboratorio.setEditable(false);
        cargarTabla();
        this.setLocationRelativeTo(null);
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
        
        txtID.setVisible(false);

        this.setLocationRelativeTo(null);
    }
            private void agregarMensajeEstilizado(String mensaje, String rol) {
    StyledDocument doc = txtPaneChat.getStyledDocument();
    Style estiloPrefijo = txtPaneChat.addStyle("estiloPrefijo_" + rol, null);
    StyleConstants.setFontSize(estiloPrefijo, 14);
    StyleConstants.setFontFamily(estiloPrefijo, "Segoe UI");
    StyleConstants.setBold(estiloPrefijo, true);
    if (rol.equals("usuario")) {
        StyleConstants.setForeground(estiloPrefijo, new Color(33, 150, 243));
        StyleConstants.setAlignment(estiloPrefijo, StyleConstants.ALIGN_RIGHT);
    } else {
        StyleConstants.setForeground(estiloPrefijo, new Color(76, 175, 80));
        StyleConstants.setAlignment(estiloPrefijo, StyleConstants.ALIGN_LEFT);
    }
    StyleConstants.setSpaceBelow(estiloPrefijo, 8);
    Style estiloMensaje = txtPaneChat.addStyle("estiloMensaje_" + rol, null);
    StyleConstants.setFontSize(estiloMensaje, 14);
    StyleConstants.setFontFamily(estiloMensaje, "Segoe UI");
    StyleConstants.setForeground(estiloMensaje, Color.BLACK);
    StyleConstants.setAlignment(estiloMensaje, rol.equals("usuario") ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);

    try {
        int longitud = doc.getLength();
        String prefijo = (rol.equals("usuario") ? "Tú: " : "Asistente: ");
        doc.insertString(longitud, prefijo, estiloPrefijo);
        doc.insertString(doc.getLength(), mensaje + "\n", estiloMensaje);
        doc.setParagraphAttributes(longitud, mensaje.length() + prefijo.length() + 1, estiloPrefijo, false);
        SwingUtilities.invokeLater(() -> txtPaneChat.setCaretPosition(txtPaneChat.getDocument().getLength()));
    } catch (BadLocationException e) {
        e.printStackTrace();
    }
}
private void enviarMensaje() {
    String mensaje = txtMensaje.getText().trim();
    if (!mensaje.isEmpty()) {
        agregarMensajeEstilizado(mensaje, "usuario");
        txtMensaje.setText("");

        new Thread(() -> {
            String respuesta = OpenAIClient.enviarMensaje(mensaje);

            SwingUtilities.invokeLater(() -> {
                agregarMensajeEstilizado(respuesta, "asistente"); 
            });
        }).start();
    }
}
private boolean miniChatVisible = false;
private volatile boolean animacionEnCurso = false;

private final int posYVisible = 460;
private final int posYOculto = 835;
private final int paso = 7;
private final int delay = 5; // ms

private void toggleMiniChat() {
    if (animacionEnCurso) return;
    animacionEnCurso = true;

    final int posXActual = MiniChat.getX();
    final int posYActual = MiniChat.getY();

    if (!miniChatVisible) {
        MiniChat.setVisible(true);
        new Thread(() -> {
            int y = posYActual;
            while (y > posYVisible) {
                y -= paso;
                if (y < posYVisible) y = posYVisible;

                final int posY = y;
                SwingUtilities.invokeLater(() -> MiniChat.setLocation(posXActual, posY));

                try { Thread.sleep(delay); } catch (InterruptedException e) { e.printStackTrace(); }
            }
            miniChatVisible = true;
            animacionEnCurso = false;
        }).start();

    } else {
        new Thread(() -> {
            int y = posYActual;
            while (y < posYOculto) {
                y += paso;
                if (y > posYOculto) y = posYOculto;

                final int posY = y;
                SwingUtilities.invokeLater(() -> MiniChat.setLocation(posXActual, posY));

                try { Thread.sleep(delay); } catch (InterruptedException e) { e.printStackTrace(); }
            }
            miniChatVisible = false;
            animacionEnCurso = false;
        }).start();
    }
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

    private MaterialesHardware() {
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
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblFlecha = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
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
        MiniChat = new javax.swing.JPanel();
        txtMensaje = new javax.swing.JTextField();
        ScrollChat = new javax.swing.JScrollPane();
        txtPaneChat = new javax.swing.JTextPane();
        TextoChat = new javax.swing.JLabel();
        BotonChat = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        ChatPersonalizacion = new javax.swing.JPanel();
        Nombretxt = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtID1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnHabilitarDeshabilitar = new javax.swing.JButton();
        Codigo = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnVerLaboratorios = new javax.swing.JButton();
        Laboratorio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        NumeroSerie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TipoEquipo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMateriales = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        FondoBlanco1 = new javax.swing.JLabel();
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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        lblFlecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MostrarMasBoton.png"))); // NOI18N
        panelSidebar.add(lblFlecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 382, 20, 20));

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

        LogoSale1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoUSB.png"))); // NOI18N
        panelSidebar.add(LogoSale1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 160, 60));

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

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 870));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

        MiniChat.setBackground(new java.awt.Color(255, 255, 255));
        MiniChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MiniChat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMensaje.setText("Escribe un mensaje...");
        txtMensaje.setForeground(Color.GRAY);
        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMensajeKeyPressed(evt);
            }
        });
        MiniChat.add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 340, 30));
        txtMensaje.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtMensaje.getText().equals("Escribe un mensaje...")) {
                    txtMensaje.setText("");
                    txtMensaje.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtMensaje.getText().isEmpty()) {
                    txtMensaje.setForeground(Color.GRAY);
                    txtMensaje.setText("Escribe un mensaje...");
                }
            }
        });

        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMensajeKeyPressed(evt);
            }
        });

        MiniChat.add(txtMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 330, 30));

        txtPaneChat.setEditable(false);
        txtPaneChat.setBackground(new java.awt.Color(255, 255, 255));
        ScrollChat.setViewportView(txtPaneChat);

        MiniChat.add(ScrollChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 420, 320));

        TextoChat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TextoChat.setForeground(new java.awt.Color(255, 255, 255));
        TextoChat.setText("Chat AI");
        MiniChat.add(TextoChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 4, 280, -1));

        BotonChat.setBackground(new java.awt.Color(29, 41, 57));
        BotonChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BotonChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonChatActionPerformed(evt);
            }
        });
        MiniChat.add(BotonChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 33));

        btnEnviar.setBackground(new java.awt.Color(29, 41, 57));
        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BotonEnviar.png"))); // NOI18N
        btnEnviar.setBorder(null);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        btnEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEnviarKeyPressed(evt);
            }
        });
        MiniChat.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 80, 30));

        ChatPersonalizacion.setBackground(new java.awt.Color(29, 41, 57));
        MiniChat.add(ChatPersonalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 368, 424, 34));

        getContentPane().add(MiniChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 840, 450, 420));

        Nombretxt.setBackground(new java.awt.Color(255, 255, 255));
        Nombretxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Nombretxt.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 15, 240, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBackground(new java.awt.Color(233, 236, 239));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setText("Buscar ID");
        jTextField1.setToolTipText("");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 65, 90, 20));
        String placeholder = "Buscar ID";
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
                    tblMateriales.setRowSorter(null);
                    return;
                }
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblMateriales.getModel());
                tblMateriales.setRowSorter(sorter);
                if (query.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(query, 0)); // Filtra por columna 0 (ID)
                }
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 65, -1, 20));

        jLabel1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel1.setText("Materiales de Hardware");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, -48, 20, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel8.setText("Datos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 30));

        jLabel4.setText("Codigo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        btnGuardar.setBackground(new java.awt.Color(29, 41, 57));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Agregar");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        btnModificar.setBackground(new java.awt.Color(29, 41, 57));
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        btnLimpiar.setBackground(new java.awt.Color(29, 41, 57));
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        btnHabilitarDeshabilitar.setBackground(new java.awt.Color(29, 41, 57));
        btnHabilitarDeshabilitar.setForeground(new java.awt.Color(255, 255, 255));
        btnHabilitarDeshabilitar.setText("Habilitar/Deshabilitar");
        btnHabilitarDeshabilitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHabilitarDeshabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabilitarDeshabilitarActionPerformed(evt);
            }
        });
        jPanel1.add(btnHabilitarDeshabilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));
        jPanel1.add(Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 380, -1));

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 10, -1));

        jLabel6.setText("Laboratorio");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        btnVerLaboratorios.setText("Ver Laboratorios");
        btnVerLaboratorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLaboratoriosActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerLaboratorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, -1));

        Laboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaboratorioActionPerformed(evt);
            }
        });
        jPanel1.add(Laboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 250, -1));

        jLabel3.setText("Numero de Serie");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));
        jPanel1.add(NumeroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 380, -1));

        jLabel7.setText("Tipo de Equipo");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        jPanel1.add(TipoEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 380, 20));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 520, 710));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 510, 710));

        tblMateriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Codigo", "Tipo de Equipo", "Numero de Serie", "Estado", "Categoria", "Laboratorio"
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
        tblMateriales.setToolTipText("");
        tblMateriales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMaterialesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMateriales);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 930, 660));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_1.png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 190, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 930, 710));

        FondoBlanco1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 0, 740));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1450, 740));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1480, 770));

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
        FondoGris1.setMaximumSize(new java.awt.Dimension(1540, 863));
        FondoGris1.setMinimumSize(new java.awt.Dimension(1540, 863));
        FondoGris1.setPreferredSize(new java.awt.Dimension(1540, 863));
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, -60, 1680, 930));

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
    private void limpiar() {
        Codigo.setText("");
        TipoEquipo.setText("");
        NumeroSerie.setText("");
        Laboratorio.setText("");
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tblMateriales.getModel();
        modelo.setRowCount(0);

        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT mh.id_material_hardware, mh.nombre, mh.tipo_equipo, mh.numero_serie, mh.estado,l.seccion ,l.Codigo_lab "
                    + "FROM materiales_hardware mh "
                    + "JOIN laboratorios l ON mh.ID_lab = l.ID_lab "
                    + "WHERE mh.estado_material_hardware = 1 ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_material_hardware"),
                    rs.getString("nombre"),
                    rs.getString("tipo_equipo"),
                    rs.getString("numero_serie"),
                    rs.getString("estado"),
                    rs.getString("seccion"),
                    rs.getString("Codigo_lab")
                };
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String codigoText = Codigo.getText();
        String tipoEquipoText = TipoEquipo.getText();
        String NumSerieText = NumeroSerie.getText();
        String laboratorioText = Laboratorio.getText(); // Esto será el Codigo_lab

        if (codigoText.isEmpty() || tipoEquipoText.isEmpty() || NumSerieText.isEmpty() || laboratorioText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor rellene todos los campos.");
            return;
        }

        try {
            Connection con = Conexion.obtenerConexion();

            // Primero obtenemos el ID_lab basado en el Codigo_lab
            PreparedStatement psBuscarLab = con.prepareStatement(
                    "SELECT ID_lab FROM laboratorios WHERE Codigo_lab = ?"
            );
            psBuscarLab.setString(1, laboratorioText);
            ResultSet rs = psBuscarLab.executeQuery();

            if (rs.next()) {
                int idLab = rs.getInt("ID_lab"); // Aquí obtenemos el ID

                // Ahora insertamos el material usando el ID_lab
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO materiales_hardware (nombre, tipo_equipo, numero_serie, estado, ID_lab) VALUES (?, ?, ?, ?, ?)"
                );
                ps.setString(1, codigoText);
                ps.setString(2, tipoEquipoText);
                ps.setString(3, NumSerieText);
                ps.setString(4, "Disponible");
                ps.setInt(5, idLab);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Material registrado correctamente.");

                int idTecnico = SesionUsuario.idUsuario;
                String rolTecnico = SesionUsuario.rol;
                String usuarioTecnico = SesionUsuario.username;
                String accion = "Material Registrado";
                String detalleLog = "Usuario: '" + usuarioTecnico + "' Rol: '" + rolTecnico + "' agregó un nuevo material: nombre '" + codigoText
                        + "', tipo '" + tipoEquipoText + "', número de serie '" + NumSerieText
                        + "', laboratorio '" + laboratorioText + "'.";

                LogManager.registrarLog(idTecnico, rolTecnico, accion, detalleLog);

                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Código de laboratorio no encontrado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.toString());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int fila = tblMateriales.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un material de la tabla.");
            return;
        }

        String id = tblMateriales.getValueAt(fila, 0).toString();
        String nombre = Codigo.getText();
        String tipo = TipoEquipo.getText();
        String serie = NumeroSerie.getText();
        String laboratorio = Laboratorio.getText();

        if (nombre.isEmpty() || tipo.isEmpty() || serie.isEmpty() || laboratorio.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
            return;
        }

        try (Connection con = Conexion.obtenerConexion()) {
            // Primero obtenemos el ID_lab basado en el Codigo_lab
            PreparedStatement psBuscarLab = con.prepareStatement(
                    "SELECT ID_lab FROM laboratorios WHERE Codigo_lab = ?"
            );
            psBuscarLab.setString(1, laboratorio);  // Usamos el valor de 'laboratorio' que ya tienes
            ResultSet rs = psBuscarLab.executeQuery();

            if (rs.next()) {
                int idLab = rs.getInt("ID_lab"); // Obtener el ID_lab

                // Ahora actualizamos el material usando el ID_lab
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE materiales_hardware SET nombre=?, tipo_equipo=?, numero_serie=?, ID_lab=? WHERE id_material_hardware=?"
                );
                ps.setString(1, nombre);
                ps.setString(2, tipo);
                ps.setString(3, serie);
                ps.setInt(4, idLab);  // Usamos el ID_lab aquí
                ps.setInt(5, Integer.parseInt(id));  // Usamos el ID del material a modificar

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Material actualizado correctamente.");
                int idUsuario = SesionUsuario.idUsuario;
                String rolUsuario = SesionUsuario.rol;
                String usuario = SesionUsuario.username;
                String accion = "Material Modificado";
                String detalle = "Usuario: '" + usuario + "' Rol: '" + rolUsuario +
                                 "' actualizó el material: nombre '" + nombre +
                                 "', tipo '" + tipo + "', número de serie '" + serie +
                                 "." + "', laboratorio '" + laboratorio;
               
                LogManager.registrarLog(idUsuario, rolUsuario, accion, detalle);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Código de laboratorio no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + e.toString());
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblMateriales.getSelectedRow();
        String codigoText = Codigo.getText();
        String tipoEquipoText = TipoEquipo.getText();
        String NumSerieText = NumeroSerie.getText();
        String laboratorioText = Laboratorio.getText();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un material de la tabla.");
            return;
        }

        String id = tblMateriales.getValueAt(fila, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este material?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection con = Conexion.obtenerConexion()) {

                PreparedStatement ps = con.prepareStatement("UPDATE materiales_hardware SET estado_material_hardware = 0 WHERE id_material_hardware = ?");
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Material eliminado correctamente.");
                
                int idTecnico = SesionUsuario.idUsuario;
                String rolTecnico = SesionUsuario.rol;
                String usuarioTecnico = SesionUsuario.username;
                String accion = "Material Eliminado";
                String detalleLog = "Usuario: '" + usuarioTecnico + "' Rol: '" + rolTecnico + "' eliminó un nuevo material: nombre '" + codigoText
                        + "', tipo '" + tipoEquipoText + "', número de serie '" + NumSerieText
                        + "', laboratorio '" + laboratorioText + "'.";

                LogManager.registrarLog(idTecnico, rolTecnico, accion, detalleLog);
                
                

                limpiar();
                cargarTabla();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.toString());
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnHabilitarDeshabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabilitarDeshabilitarActionPerformed
        int fila = tblMateriales.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un material.");
            return;
        }

        int materialId = Integer.parseInt(tblMateriales.getValueAt(fila, 0).toString());

        try (Connection con = Conexion.obtenerConexion()) {
            // Obtener el estado actual del material
            PreparedStatement psObtenerEstado = con.prepareStatement("SELECT estado, nombre, tipo_equipo, numero_serie FROM materiales_hardware WHERE id_material_hardware = ?");
            psObtenerEstado.setInt(1, materialId);

            try (ResultSet rsEstado = psObtenerEstado.executeQuery()) {
                if (!rsEstado.next()) {
                    JOptionPane.showMessageDialog(null, "Error al encontrar el material.");
                    return;
                }

                String estadoActual = rsEstado.getString("estado");
                String nombreText = rsEstado.getString("nombre");
                String tipoEquipoText = rsEstado.getString("tipo_equipo");
                String NumSerieText = rsEstado.getString("numero_serie");
                System.out.println("Estado actual del material: " + estadoActual);

                // Cambiar el estado según el estado actual
                String nuevoEstado;
                if ("Disponible".equals(estadoActual)) {
                    nuevoEstado = "Mantenimiento";
                } else if ("Mantenimiento".equals(estadoActual)) {
                    nuevoEstado = "Disponible";
                } else {
                    // Si el estado es diferente a "Disponible" o "Mantenimiento", no lo cambiamos
                    JOptionPane.showMessageDialog(null, "Estado inválido. Solo 'Disponible' o 'Mantenimiento' son permitidos.");
                    return;
                }

                System.out.println("Nuevo estado: " + nuevoEstado);

                // Actualizar el estado en la base de datos
                PreparedStatement psActualizarEstado = con.prepareStatement("UPDATE materiales_hardware SET estado = ? WHERE id_material_hardware = ?");
                psActualizarEstado.setString(1, nuevoEstado);
                psActualizarEstado.setInt(2, materialId);
                psActualizarEstado.executeUpdate();

                String mensaje = "Material ahora está en estado de " + nuevoEstado.toLowerCase() + ".";
                JOptionPane.showMessageDialog(null, mensaje);
                
                int idTecnico = SesionUsuario.idUsuario;
                String rolTecnico = SesionUsuario.rol;
                String usuarioTecnico = SesionUsuario.username;
                String accion = "Cambio de Estado de Material";
                String detalleLog = "Usuario: '" + usuarioTecnico + "' Rol: '" + rolTecnico +
                            "' cambió el estado del material: nombre '" + nombreText +
                            "', tipo '" + tipoEquipoText + "', número de serie '" + NumSerieText +
                            ", de estado '" + estadoActual +
                            "' a '" + nuevoEstado + "'.";

        LogManager.registrarLog(idTecnico, rolTecnico, accion, detalleLog);
                
                cargarTabla();

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnHabilitarDeshabilitarActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnVerLaboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLaboratoriosActionPerformed
        VerMaterialesParaLabs ver = new VerMaterialesParaLabs(Laboratorio);
        ver.setLocationRelativeTo(null);
        ver.setVisible(true);
    }//GEN-LAST:event_btnVerLaboratoriosActionPerformed

    private void tblMaterialesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMaterialesMouseClicked
        try {

            int fila = tblMateriales.getSelectedRow();

            if (fila != -1) {

                int id = Integer.parseInt(tblMateriales.getValueAt(fila, 0).toString());

                PreparedStatement ps;
                ResultSet rs;
                Connection con = Conexion.obtenerConexion();

                ps = con.prepareStatement("SELECT m.nombre, m.tipo_equipo, m.numero_serie, m.estado, l.Codigo_lab, l.seccion FROM materiales_hardware m JOIN laboratorios l ON m.ID_lab = l.ID_lab WHERE id_material_hardware=?");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Codigo.setText(rs.getString("nombre"));
                    TipoEquipo.setText(rs.getString("tipo_equipo"));
                    NumeroSerie.setText(rs.getString("numero_serie"));

                    Laboratorio.setText(rs.getString("Codigo_lab"));

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_tblMaterialesMouseClicked

    private void LaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaboratorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LaboratorioActionPerformed

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
        // TODO add your handling code here:
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

    private void txtID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtID1ActionPerformed

    private void txtMensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            enviarMensaje();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtMensajeKeyPressed

    private void BotonChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonChatActionPerformed
        toggleMiniChat();        // TODO add your handling code here:
    }//GEN-LAST:event_BotonChatActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        enviarMensaje();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnEnviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEnviarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviarKeyPressed

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
                new MaterialesHardware().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonChat;
    private javax.swing.JPanel ChatPersonalizacion;
    private javax.swing.JTextField Codigo;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoBlanco1;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JTextField Laboratorio;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JPanel MiniChat;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JTextField NumeroSerie;
    private javax.swing.JScrollPane ScrollChat;
    private javax.swing.JLabel Superior;
    private javax.swing.JLabel TextoChat;
    private javax.swing.JTextField TipoEquipo;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComputadoras;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHabilitarDeshabilitar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListaLaboratorios;
    private javax.swing.JButton btnListaPrestamos;
    private javax.swing.JButton btnMateriales;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnReporteLaboratorios;
    private javax.swing.JButton btnReporteMantenimiento;
    private javax.swing.JButton btnReporteSanciones;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnSancionesDesignar;
    private javax.swing.JButton btnSolicitudes;
    private javax.swing.JButton btnVerLaboratorios;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblFlecha;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSubReportes;
    private javax.swing.JLabel perfil;
    private javax.swing.JTable tblMateriales;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextPane txtPaneChat;
    // End of variables declaration//GEN-END:variables
}
