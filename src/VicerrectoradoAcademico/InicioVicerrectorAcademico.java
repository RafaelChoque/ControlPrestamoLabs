/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VicerrectoradoAcademico;

import PersonalAcademico.FormularioPrestamo;
import Administrador.AdministradorPersonalAcademico;
import Administrador.AdministradorTecnicoEquipos;
import Administrador.AdministradorTecnicoPrestamo;
import Administrador.LogManager;
import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
import static ConexionLogin.SesionUsuario.username;
import OpenAi.OpenAIClient;
import Sanciones.SancionesRecibidaPersonal;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
public class InicioVicerrectorAcademico extends javax.swing.JFrame {

    private int idusuario;

    /**
     * Creates new form InicioPersonalAcademico
     * @param idusuario
     */
    public InicioVicerrectorAcademico(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
        agregarMensajeEstilizado("¡Hola! Soy tu asistente. Puedes preguntarme cualquier cosa sobre cómo solicitar un laboratorio. Estoy aquí para ayudarte.", "asistente");
        OpenAIClient.setSystemMessage(
       "Eres un asistente amigable, claro y experto en el sistema de control y préstamos de laboratorios. " +
"Estás asistiendo al usuario con el rol de Vicerrectorado Académico dentro de la interfaz principal. " +
"En esta sección puedes visualizar los anuncios realizados por el Técnico de Préstamos. " +
"No hay muchas funciones adicionales aquí, pero si deseas realizar acciones específicas como la asignación semestral de uso de laboratorios, puedes hacerlo desde la interfaz 'Asignación Semestral'. " +
"Si quieres saber más sobre esa sección, ve directamente a esa interfaz y con gusto te ayudo. " +
"Responde siempre con claridad y sin rodeos innecesarios. Si el usuario se despide o agradece, responde de forma natural: '¡Con gusto! Si necesitas algo más, aquí estaré 😊'."
);
        cargarNombreCompleto();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
    private boolean sidebarMostrado = false;
    private Timer animacion;
    private boolean sidebarListo = false;
    private InicioVicerrectorAcademico() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnAsignacionSemestre = new javax.swing.JButton();
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
        btnMenu = new javax.swing.JButton();
        perfil = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        FondoBlanco = new javax.swing.JLabel();
        dato = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        InicioPersonal1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        InicioPersonal2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Mensajestxt = new javax.swing.JTextArea();
        Superior = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Panel de Control");
        panelSidebar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

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
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 240, 30));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 10, 10));

        dato.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        dato.setText("Sistema de Control y Prestamo de Laboratorios de Hardware, Redes, Telecomunicaciones y Electronica");
        jPanel2.add(dato, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Zona Achachicala, Av. Chacaltaya Nro. 1258");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, 30));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ubicacion (1).png"))); // NOI18N
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 30, 50));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 480, 70));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ProyectoLosJackson@gmail.com");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/email.png"))); // NOI18N
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 50, 50));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 680, 480, 70));

        InicioPersonal1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        InicioPersonal1.setText("Inicio");
        jPanel2.add(InicioPersonal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, -1));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Jose     67106924");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 150, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Rafael 75298318");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Erlan   69938519");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 140, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Contactanos");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/telefono.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 680, 480, 70));

        InicioPersonal2.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        InicioPersonal2.setText("Anuncios");
        jPanel2.add(InicioPersonal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 160, -1));

        Mensajestxt.setColumns(20);
        Mensajestxt.setRows(5);
        jScrollPane1.setViewportView(Mensajestxt);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1440, 120));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1480, 770));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        FondoGris.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 870));

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
    }//GEN-LAST:event_btnInicioActionPerformed

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

    private void btnAsignacionSemestreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAsignacionSemestreMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsignacionSemestreMouseExited

    private void btnAsignacionSemestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignacionSemestreActionPerformed
        FormularioAsignacionPrestamo formasig = new FormularioAsignacionPrestamo(idusuario);
        formasig.setLocationRelativeTo(null);
        formasig.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAsignacionSemestreActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioVicerrectorAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioVicerrectorAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioVicerrectorAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioVicerrectorAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioVicerrectorAcademico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonChat;
    private javax.swing.JPanel ChatPersonalizacion;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JLabel InicioPersonal1;
    private javax.swing.JLabel InicioPersonal2;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextArea Mensajestxt;
    private javax.swing.JPanel MiniChat;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JScrollPane ScrollChat;
    private javax.swing.JLabel Superior;
    private javax.swing.JLabel TextoChat;
    private javax.swing.JButton btnAsignacionSemestre;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMenu;
    private javax.swing.JLabel dato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextPane txtPaneChat;
    // End of variables declaration//GEN-END:variables
}
