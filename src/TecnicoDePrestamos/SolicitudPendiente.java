package TecnicoDePrestamos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Rafael
 */
import Administrador.LogManager;
import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
import static ConexionLogin.SesionUsuario.username;
import Materiales.Materiales;
import Materiales.MaterialesHardware;
import OpenAi.OpenAIClient;
import PersonalAcademico.DisponibilidadPrestamos;
import PersonalAcademico.EmailSender;
import Reportes.ReportesMantenimiento;
import Reportes.ReportesPrestamos;
import Reportes.ReportesSanciones;
import Sanciones.SancionesParaDesignar;
import TecnicoDeEquipos.VerTecnicosEquipos;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.BorderFactory;
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
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
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
public class SolicitudPendiente extends javax.swing.JFrame {

    private int idusuario;

    /**
     * Creates new form InicioPersonalAcademico
     * @param idusuario
     */
    public SolicitudPendiente(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
        agregarMensajeEstilizado("¡Hola! Soy tu asistente. Puedes preguntarme cualquier cosa sobre cómo solicitar un laboratorio. Estoy aquí para ayudarte.", "asistente");
        OpenAIClient.setSystemMessage(
       "Eres un asistente amigable, claro y experto en el sistema de control y préstamos de laboratorios. " +
"Estás asistiendo al Técnico de Préstamos dentro de la interfaz de 'Solicitudes'. " +
"Aquí puedes aprobar o rechazar las solicitudes pendientes enviadas por los usuarios del rol Personal Académico. " +
"Para gestionar una solicitud, selecciona una fila de la tabla y luego elige si deseas aprobarla o rechazarla. " +
"Al aprobarla, se enviará automáticamente un correo electrónico al usuario informando que fue aceptada. " +
"Si decides rechazarla, deberás ingresar un motivo; sin motivo no se podrá enviar el rechazo. También se notificará por correo al usuario. " +
"Responde con claridad y sin rodeos. Si el usuario se despide o agradece, responde de manera natural: '¡Con gusto! Si necesitas algo más, aquí estaré 😊'. " +
"Si hay confusión, ayúdalo con ejemplos simples."
 );
        aplicarColorFilasAlternadas(TablaSolicitudes);
        cargarNombreCompleto();
        iconoOriginal = lblFlecha.getIcon();
panelSubReportes.setLocation(panelSubReportes.getX(), -70);
panelSubReportes.setVisible(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.dispose();
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
        cargarTabla();
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

    private SolicitudPendiente() {
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
    private String obtenerCorreoUsuarioPorSolicitud(int idSolicitud) {
    String correo = "";
    try {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT pa.email FROM prestamos p JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico WHERE p.id_prestamo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idSolicitud);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            correo = rs.getString("email");
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return correo;
}
private String obtenerNombreUsuarioPorSolicitud(int idSolicitud) {
    String nombre = "";
    try {
        Connection con = Conexion.obtenerConexion();
        String sql = "SELECT pa.nombre FROM prestamos p JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico WHERE p.id_prestamo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idSolicitud);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nombre = rs.getString("nombre");
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return nombre;
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSidebar = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFlecha = new javax.swing.JLabel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSolicitudes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblErrorMotivo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MotivoRechazo = new javax.swing.JTextArea();
        motivoRechazo = new javax.swing.JLabel();
        AprobarSolicitud = new javax.swing.JButton();
        RechazarSolicitud = new javax.swing.JButton();
        VerHorarios = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        perfil1 = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();

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
        Nombretxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nombretxt.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 15, 240, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Laboratorio", "Docente", "Motivo", "Fecha", "Horario Inicio", "Horario Fin", "Estado"
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
        jScrollPane1.setViewportView(TablaSolicitudes);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1110, 660));

        jLabel1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        jLabel1.setText("Solicitudes Pendientes");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

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
                    TablaSolicitudes.setRowSorter(null);
                    return;
                }
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(TablaSolicitudes.getModel());
                TablaSolicitudes.setRowSorter(sorter);
                if (query.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(query, 0)); // Filtra por columna 0 (ID)
                }
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 65, -1, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 55, 190, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1110, 710));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblErrorMotivo.setForeground(new java.awt.Color(255, 0, 0));
        jPanel5.add(lblErrorMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 155, 300, 20));

        MotivoRechazo.setColumns(20);
        MotivoRechazo.setRows(5);
        jScrollPane2.setViewportView(MotivoRechazo);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, 300, 110));

        motivoRechazo.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        motivoRechazo.setText("Motivo del Rechazo");
        jPanel5.add(motivoRechazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, -1, -1));

        AprobarSolicitud.setBackground(new java.awt.Color(51, 153, 0));
        AprobarSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        AprobarSolicitud.setText("Aprobar");
        AprobarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AprobarSolicitudActionPerformed(evt);
            }
        });
        jPanel5.add(AprobarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 180, 150, 30));

        RechazarSolicitud.setBackground(new java.awt.Color(255, 0, 0));
        RechazarSolicitud.setForeground(new java.awt.Color(255, 255, 255));
        RechazarSolicitud.setText("Rechazar");
        RechazarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechazarSolicitudActionPerformed(evt);
            }
        });
        jPanel5.add(RechazarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 180, 150, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1440, 710));

        VerHorarios.setBackground(new java.awt.Color(29, 41, 57));
        VerHorarios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        VerHorarios.setForeground(new java.awt.Color(255, 255, 255));
        VerHorarios.setText("Ver Horarios");
        VerHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerHorariosActionPerformed(evt);
            }
        });
        jPanel2.add(VerHorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, 210, 30));

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

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, -80, 1570, 950));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 610, 180));

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
    private void cargarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) TablaSolicitudes.getModel();
        modeloTabla.setRowCount(0);

        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT p.id_prestamo, l.Nombre_lab, CONCAT(pa.nombre, ' ', pa.apellido) AS nombre_completo, "
                    + "p.motivo, p.fecha, p.horario_inicio, p.horario_fin, p.estado, p.motivo_rechazo "
                    + "FROM prestamos p "
                    + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico"
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_prestamo"),
                    rs.getString("Nombre_lab"),
                    rs.getString("nombre_completo"),
                    rs.getString("motivo"),
                    rs.getDate("fecha"),
                    rs.getTime("horario_inicio"),
                    rs.getTime("horario_fin"),
                    rs.getString("estado"),
                    rs.getString("motivo_rechazo")
                };
                modeloTabla.addRow(fila);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar solicitudes: " + ex.getMessage());
        }
    }

    private void AprobarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AprobarSolicitudActionPerformed
int fila = TablaSolicitudes.getSelectedRow();

if (fila == -1) {
    JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud para aprobar.");
    return;
}

try {
    int idSolicitud = Integer.parseInt(TablaSolicitudes.getValueAt(fila, 0).toString());
    int idTecnico = SesionUsuario.idtecnico;
    String rolTecnico = SesionUsuario.rol;

    if (idTecnico <= 0) {
        JOptionPane.showMessageDialog(null, "No se ha identificado correctamente al técnico logueado.");
        return;
    }

    Connection con = Conexion.obtenerConexion();

    PreparedStatement ps = con.prepareStatement(
        "UPDATE prestamos SET estado = 'Aprobado', id_tecnico_prestamos = ? WHERE id_prestamo = ?"
    );
    ps.setInt(1, idTecnico);
    ps.setInt(2, idSolicitud);
    ps.executeUpdate();

    // Obtener detalles para el log
    PreparedStatement psDetallesLog = con.prepareStatement(
        "SELECT l.Nombre_lab, p.motivo, p.fecha, p.horario_inicio, p.horario_fin " +
        "FROM prestamos p " +
        "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab " +
        "WHERE p.id_prestamo = ?"
    );
    psDetallesLog.setInt(1, idSolicitud);
    ResultSet rsLog = psDetallesLog.executeQuery();

    if (rsLog.next()) {
        String laboratorio = rsLog.getString("Nombre_lab");
        String motivo = rsLog.getString("motivo");
        Date fecha = rsLog.getDate("fecha");
        Time inicio = rsLog.getTime("horario_inicio");
        Time fin = rsLog.getTime("horario_fin");

        String detalleLog = "Solicitud " + idSolicitud + " aprobada: " + laboratorio + 
                            ", " + motivo + ", " + fecha + ", " + inicio + "-" + fin;
        LogManager.registrarLog(idTecnico, rolTecnico, "Solicitud Aprobada", detalleLog);
    }

    JOptionPane.showMessageDialog(null, "Solicitud aprobada correctamente.");
    cargarTabla();
    lblErrorMotivo.setText("");
    lblErrorMotivo.setIcon(null);

    // Enviar correo
    new Thread(() -> {
        try {
            Connection conCorreo = Conexion.obtenerConexion();
            PreparedStatement psDetalles = conCorreo.prepareStatement(
                "SELECT pa.email, pa.nombre, pa.apellido, l.Nombre_lab, p.motivo, p.fecha, " +
                "p.horario_inicio, p.horario_fin " +
                "FROM prestamos p " +
                "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab " +
                "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico " +
                "WHERE p.id_prestamo = ?"
            );
            psDetalles.setInt(1, idSolicitud);
            ResultSet rs = psDetalles.executeQuery();

            if (rs.next()) {
                String correo = rs.getString("email");
                String nombre = rs.getString("nombre") + " " + rs.getString("apellido");
                String laboratorio = rs.getString("Nombre_lab");
                String motivo = rs.getString("motivo");
                Date fecha = rs.getDate("fecha");
                Time inicio = rs.getTime("horario_inicio");
                Time fin = rs.getTime("horario_fin");

                String asunto = "Solicitud de préstamo aprobada";
                String mensaje = "Hola " + nombre + ",\n\n" +
                                 "Tu solicitud de préstamo ha sido APROBADA con los siguientes detalles:\n" +
                                 "Laboratorio: " + laboratorio + "\n" +
                                 "Motivo: " + motivo + "\n" +
                                 "Fecha: " + fecha + "\n" +
                                 "Horario: " + inicio + " - " + fin + "\n\n" +
                                 "Saludos,\nSistema de Préstamos de Laboratorios.";

                EmailSender.enviarCorreo(correo, asunto, mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error al aprobar la solicitud: " + ex.getMessage());
}

    }//GEN-LAST:event_AprobarSolicitudActionPerformed
    private int obtenerIdTecnico(String nombreCompleto) {
        int idTecnico = -1;
        try {
            Connection con = Conexion.obtenerConexion();
            String query = "SELECT id_tecnico_prestamos FROM tecnico_prestamos WHERE CONCAT(nombre, ' ', apellido) = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombreCompleto);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idTecnico = rs.getInt("id_tecnico_prestamos");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del técnico.");
        }
        return idTecnico;
    }
    private void RechazarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechazarSolicitudActionPerformed
int fila = TablaSolicitudes.getSelectedRow();

if (fila == -1) {
    JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud para rechazar.");
    return;
}

String motivo = MotivoRechazo.getText().trim();

if (motivo.isEmpty()) {
    lblErrorMotivo.setText("Debe ingresar un motivo para rechazar la solicitud.");
    lblErrorMotivo.setIcon(new ImageIcon(getClass().getResource("/imagenes/Exclamacion3.png")));
    return;
}

try {
    int idSolicitud = Integer.parseInt(TablaSolicitudes.getValueAt(fila, 0).toString());
    int idTecnico = SesionUsuario.idtecnico;
    String rolTecnico = SesionUsuario.rol;

    if (idTecnico <= 0) {
        JOptionPane.showMessageDialog(null, "No se ha identificado correctamente al técnico logueado.");
        return;
    }

    Connection con = Conexion.obtenerConexion();
    PreparedStatement ps = con.prepareStatement(
        "UPDATE prestamos SET estado = 'Rechazado', motivo_rechazo = ?, id_tecnico_prestamos = ? WHERE id_prestamo = ?"
    );
    ps.setString(1, motivo);
    ps.setInt(2, idTecnico);
    ps.setInt(3, idSolicitud);
    ps.executeUpdate();

    // Obtener detalles para el log
    PreparedStatement psDetallesLog = con.prepareStatement(
        "SELECT l.Nombre_lab, p.motivo, p.fecha, p.horario_inicio, p.horario_fin " +
        "FROM prestamos p " +
        "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab " +
        "WHERE p.id_prestamo = ?"
    );
    psDetallesLog.setInt(1, idSolicitud);
    ResultSet rsLog = psDetallesLog.executeQuery();

    if (rsLog.next()) {
        String laboratorio = rsLog.getString("Nombre_lab");
        String motivoOriginal = rsLog.getString("motivo");
        Date fecha = rsLog.getDate("fecha");
        Time inicio = rsLog.getTime("horario_inicio");
        Time fin = rsLog.getTime("horario_fin");

        String detalleLog = "Solicitud " + idSolicitud + " rechazada: " + laboratorio +
                            ", " + motivoOriginal + ", " + fecha + ", " + inicio + "-" + fin +
                            ", Motivo: " + motivo;
        LogManager.registrarLog(idTecnico, rolTecnico, "Solicitud Rechazada", detalleLog);
    }

    JOptionPane.showMessageDialog(null, "Solicitud rechazada correctamente.");
    MotivoRechazo.setText("");
    cargarTabla();
    lblErrorMotivo.setText("");
    lblErrorMotivo.setIcon(null);

    // Enviar correo
    new Thread(() -> {
        try (Connection conCorreo = Conexion.obtenerConexion()) {
            PreparedStatement psDetalles = conCorreo.prepareStatement(
                "SELECT pa.email, pa.nombre, pa.apellido, l.Nombre_lab, p.motivo, p.fecha, " +
                "p.horario_inicio, p.horario_fin " +
                "FROM prestamos p " +
                "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab " +
                "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico " +
                "WHERE p.id_prestamo = ?"
            );
            psDetalles.setInt(1, idSolicitud);
            ResultSet rs = psDetalles.executeQuery();

            if (rs.next()) {
                String correo = rs.getString("email");
                String nombre = rs.getString("nombre") + " " + rs.getString("apellido");
                String laboratorio = rs.getString("Nombre_lab");
                String motivoOriginal = rs.getString("motivo");
                Date fecha = rs.getDate("fecha");
                Time inicio = rs.getTime("horario_inicio");
                Time fin = rs.getTime("horario_fin");

                String asunto = "Solicitud de préstamo rechazada";
                String mensaje = "Hola " + nombre + ",\n\n" +
                                 "Tu solicitud de préstamo ha sido RECHAZADA.\n\n" +
                                 "Detalles de la solicitud:\n" +
                                 "Laboratorio: " + laboratorio + "\n" +
                                 "Motivo de solicitud: " + motivoOriginal + "\n" +
                                 "Fecha: " + fecha + "\n" +
                                 "Horario: " + inicio + " - " + fin + "\n\n" +
                                 "Motivo del rechazo: " + motivo + "\n\n" +
                                 "Saludos,\nSistema de Préstamos de Laboratorios.";

                EmailSender.enviarCorreo(correo, asunto, mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error al rechazar la solicitud: " + ex.getMessage());
}

    }//GEN-LAST:event_RechazarSolicitudActionPerformed

    
    
    private void VerHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerHorariosActionPerformed
        VerHorarios ver = new VerHorarios();
        ver.setLocationRelativeTo(null);
        ver.setVisible(true);
    }//GEN-LAST:event_VerHorariosActionPerformed

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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

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
        // TODO add your handling code here:
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SolicitudPendiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AprobarSolicitud;
    private javax.swing.JButton BotonChat;
    private javax.swing.JPanel ChatPersonalizacion;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JPanel MiniChat;
    private javax.swing.JTextArea MotivoRechazo;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JButton RechazarSolicitud;
    private javax.swing.JScrollPane ScrollChat;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaSolicitudes;
    private javax.swing.JLabel TextoChat;
    private javax.swing.JButton VerHorarios;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnComputadoras;
    private javax.swing.JButton btnEnviar;
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblErrorMotivo;
    private javax.swing.JLabel lblFlecha;
    private javax.swing.JLabel motivoRechazo;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JPanel panelSubReportes;
    private javax.swing.JLabel perfil1;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextPane txtPaneChat;
    // End of variables declaration//GEN-END:variables
}
