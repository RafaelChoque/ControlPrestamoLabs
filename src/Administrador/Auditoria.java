/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
import OpenAi.OpenAIClient;
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
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Erlan
 */
public class Auditoria extends javax.swing.JFrame {

    /**
     * Creates new form Auditoria
     */
    public Auditoria() {
        initComponents();
        agregarMensajeEstilizado("¡Hola! Soy tu asistente. Puedes preguntarme cualquier cosa sobre cómo solicitar un laboratorio. Estoy aquí para ayudarte.", "asistente");
        OpenAIClient.setSystemMessage(
       "Eres un asistente amigable, claro y experto en el sistema de control y préstamos de laboratorios. " +
"Estás asistiendo al administrador dentro de la interfaz de 'Auditoría'. " +
"En esta sección, puede visualizar los logs del sistema: un historial detallado de todas las acciones realizadas por los usuarios. " +
"Puede filtrar los registros utilizando el RU (Registro Único) del usuario que realizó la acción, o bien seleccionar un tipo de acción específica (como creación de usuario, modificación de datos, solicitud de préstamo, eliminación de registro, entre otras). " +
"El administrador puede elegir el filtro que desee según lo que necesite revisar. " +
"Además, tiene la opción de exportar los datos filtrados a un archivo en formato .csv para guardarlos o analizarlos con más comodidad. " +
"Responde de forma clara y precisa, sin rodeos. Si el usuario hace una pregunta específica, enfócate solo en eso sin repetir todo el contexto. " +
"Si pregunta por otras interfaces, respóndele solo si es necesario y con amabilidad. " +
"Tu tono debe ser humano, cordial y útil. Si el usuario agradece o se despide, responde de forma natural, como por ejemplo: '¡Con gusto! Si necesitas algo más, estaré aquí 😊'. " +
"Si el usuario se confunde, ayúdalo con ejemplos breves y explicaciones simples. No hagas respuestas muy largas a menos que él lo pida."
        );
tablaLogs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
});
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
    public class TextAreaRenderer extends JTextArea implements TableCellRenderer {

    public TextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

@Override
public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {

    setText(value != null ? value.toString() : "");

    setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
    int preferredHeight = getPreferredSize().height;

    if (table.getRowHeight(row) < preferredHeight) {
        table.setRowHeight(row, preferredHeight);
    }

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
}   
    public class LogViewer {

    public static void mostrarLogs(JTable tabla) {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Usuario");
    modelo.addColumn("Rol");
    modelo.addColumn("Tipo");
    modelo.addColumn("Acción");
    modelo.addColumn("Fecha");

String sql = """
    SELECT l.id_log, u.username, u.rol, l.tipo, l.accion,
           DATE_FORMAT(l.fecha, '%Y-%m-%d %H:%i:%s') AS fecha
    FROM logs l
    JOIN usuarios u ON l.id_usuario = u.id_usuario
    ORDER BY l.fecha DESC
""";


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    try (Connection con = Conexion.obtenerConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String fechaFormateada = rs.getString("fecha");

            Object[] fila = {
                rs.getInt("id_log"),
                rs.getString("username"),
                rs.getString("rol"),
                rs.getString("tipo"),
                rs.getString("accion"),
                fechaFormateada
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);

    } catch (SQLException e) {
        System.out.println("Error al mostrar logs: " + e.getMessage());
    }
}
}
private void aplicarFiltros() {
    String texto = txtBuscarUsuario.getText().toLowerCase().trim();
    String accionSeleccionada = cbAccion.getSelectedItem().toString();
    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablaLogs.getModel());
    tablaLogs.setRowSorter(sorter);

    List<RowFilter<Object, Object>> filtros = new ArrayList<>();

    // Filtro de texto por RU o Usuario (columnas 1 y 3)
    if (!texto.isEmpty() && !texto.equalsIgnoreCase("buscar ru")) {
        filtros.add(RowFilter.regexFilter("(?i)" + Pattern.quote(texto), 1, 3));
    }

    // Filtro por Tipo exacto (ahora en columna 3)
    if (!accionSeleccionada.equals("Todos")) {
        filtros.add(RowFilter.regexFilter("^" + Pattern.quote(accionSeleccionada) + "$", 3));
    }

    if (filtros.isEmpty()) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.andFilter(filtros));
    }
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
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnPersonalAcademico = new javax.swing.JButton();
        btnTecnicoPrestamo = new javax.swing.JButton();
        btnTecnicoEquipo = new javax.swing.JButton();
        btnVicerrector = new javax.swing.JButton();
        btnAuditoria = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();
        MiniChat = new javax.swing.JPanel();
        txtMensaje = new javax.swing.JTextField();
        ScrollChat = new javax.swing.JScrollPane();
        txtPaneChat = new javax.swing.JTextPane();
        TextoChat = new javax.swing.JLabel();
        BotonChat = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        ChatPersonalizacion = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ListaPersonal = new javax.swing.JLabel();
        txtBuscarUsuario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLogs = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();
        cargardatos = new javax.swing.JButton();
        cbAccion = new javax.swing.JComboBox<>();
        ExportarExcel = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        perfil = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

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
        btnVicerrector.setHorizontalAlignment(SwingConstants.LEFT);
        btnVicerrector.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
        btnVicerrector.setIconTextGap(10);
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ListaPersonal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaPersonal.setText("Logs");
        jPanel2.add(ListaPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, -1));

        txtBuscarUsuario.setBackground(new java.awt.Color(233, 236, 239));
        txtBuscarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscarUsuario.setText("Buscar");
        txtBuscarUsuario.setToolTipText("");
        txtBuscarUsuario.setBorder(null);
        txtBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(txtBuscarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 90, 20));
        String placeholder = "Buscar RU";

        txtBuscarUsuario.setText(placeholder);
        txtBuscarUsuario.setForeground(Color.GRAY);

        txtBuscarUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscarUsuario.getText().equals(placeholder)) {
                    txtBuscarUsuario.setText("");
                    txtBuscarUsuario.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscarUsuario.getText().isEmpty()) {
                    txtBuscarUsuario.setText(placeholder);
                    txtBuscarUsuario.setForeground(Color.GRAY);
                }
            }
        });

        txtBuscarUsuario.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltros();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarFiltros();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarFiltros();
            }

            private void filterTable() {
                String query = txtBuscarUsuario.getText().toLowerCase();

                if (query.equals(placeholder.toLowerCase())) {
                    tablaLogs.setRowSorter(null);
                    return;
                }

                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tablaLogs.getModel());
                tablaLogs.setRowSorter(sorter);

                if (query.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 1, 3));
                }
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar.png"))); // NOI18N
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_1.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 190, 40));

        tablaLogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Rol", "Tipo", "Acción", "Fecha"
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
        tablaLogs.getTableHeader().setReorderingAllowed(false);
        tablaLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaLogsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaLogs);
        if (tablaLogs.getColumnModel().getColumnCount() > 0) {
            tablaLogs.getColumnModel().getColumn(0).setResizable(false);
            tablaLogs.getColumnModel().getColumn(1).setResizable(false);
            tablaLogs.getColumnModel().getColumn(2).setResizable(false);
            tablaLogs.getColumnModel().getColumn(3).setResizable(false);
            tablaLogs.getColumnModel().getColumn(4).setResizable(false);
            tablaLogs.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1420, 610));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        jPanel2.add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 680, 410));

        cargardatos.setBackground(new java.awt.Color(29, 41, 57));
        cargardatos.setForeground(new java.awt.Color(255, 255, 255));
        cargardatos.setText("Actualizar");
        cargardatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargardatosActionPerformed(evt);
            }
        });
        jPanel2.add(cargardatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 70, -1, -1));

        cbAccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Iniciar Sesión", "Cerrar Sesión", "Material Registrado", "Cambio de Estado de Material", "Material Modificado", "Material Eliminado", "Laboratorio Registrado", "Laboratorio Modificado", "Cambio de estado de laboratorio", "Laboratorio Eliminado", "Solicitud Aprobada", "Solicitud Rechazada", "Registro Nuevo", "Habilitar", "Deshabilitar", "Registro Modificado", "Registro Eliminado" }));
        cbAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAccionActionPerformed(evt);
            }
        });
        jPanel2.add(cbAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 190, 40));

        ExportarExcel.setBackground(new java.awt.Color(29, 41, 57));
        ExportarExcel.setForeground(new java.awt.Color(255, 255, 255));
        ExportarExcel.setText("Exportar a Excel");
        ExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportarExcelActionPerformed(evt);
            }
        });
        jPanel2.add(ExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1205, 70, 130, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1490, 760));

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarUsuarioActionPerformed

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

    private void btnTecnicoEquipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTecnicoEquipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTecnicoEquipoMouseExited

    private void btnTecnicoEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTecnicoEquipoActionPerformed
        AdministradorTecnicoEquipos admin = new AdministradorTecnicoEquipos();
        admin.setLocationRelativeTo(null);
        admin.setVisible(true);
        this.dispose();
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        LogViewer.mostrarLogs(tablaLogs); 
        tablaLogs.getColumnModel().getColumn(4).setCellRenderer(new TextAreaRenderer());
tablaLogs.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());
tablaLogs.getColumnModel().getColumn(0).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(0).setPreferredWidth(50);
tablaLogs.getColumnModel().getColumn(0).setMaxWidth(100);
tablaLogs.getColumnModel().getColumn(1).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(1).setPreferredWidth(60);
tablaLogs.getColumnModel().getColumn(1).setMaxWidth(100);
tablaLogs.getColumnModel().getColumn(2).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(2).setPreferredWidth(170);
tablaLogs.getColumnModel().getColumn(2).setMaxWidth(200);
tablaLogs.getColumnModel().getColumn(3).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(3).setPreferredWidth(140);
tablaLogs.getColumnModel().getColumn(3).setMaxWidth(200);
tablaLogs.getColumnModel().getColumn(4).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(4).setPreferredWidth(800);
tablaLogs.getColumnModel().getColumn(4).setMaxWidth(2000);
tablaLogs.getColumnModel().getColumn(5).setPreferredWidth(150); 

    }//GEN-LAST:event_formWindowOpened

    private void cargardatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargardatosActionPerformed
        LogViewer.mostrarLogs(tablaLogs);
        tablaLogs.getColumnModel().getColumn(4).setCellRenderer(new TextAreaRenderer());
        tablaLogs.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());
        tablaLogs.getColumnModel().getColumn(0).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(0).setPreferredWidth(50);
tablaLogs.getColumnModel().getColumn(0).setMaxWidth(100);
tablaLogs.getColumnModel().getColumn(1).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(1).setPreferredWidth(60);
tablaLogs.getColumnModel().getColumn(1).setMaxWidth(100);
tablaLogs.getColumnModel().getColumn(2).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(2).setPreferredWidth(170);
tablaLogs.getColumnModel().getColumn(2).setMaxWidth(200);
tablaLogs.getColumnModel().getColumn(3).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(3).setPreferredWidth(140);
tablaLogs.getColumnModel().getColumn(3).setMaxWidth(200);
tablaLogs.getColumnModel().getColumn(4).setMinWidth(30);
tablaLogs.getColumnModel().getColumn(4).setPreferredWidth(800);
tablaLogs.getColumnModel().getColumn(4).setMaxWidth(2000);
tablaLogs.getColumnModel().getColumn(5).setPreferredWidth(150); 
    }//GEN-LAST:event_cargardatosActionPerformed

    private void btnAuditoriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAuditoriaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAuditoriaMouseExited

    private void btnAuditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaActionPerformed

    }//GEN-LAST:event_btnAuditoriaActionPerformed

    private void tablaLogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaLogsMouseClicked

    }//GEN-LAST:event_tablaLogsMouseClicked

    private void cbAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAccionActionPerformed
aplicarFiltros();     // TODO add your handling code here:
    }//GEN-LAST:event_cbAccionActionPerformed

    private void ExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportarExcelActionPerformed
        try {
            ExportarExcel exportador = new ExportarExcel(tablaLogs); // Usa tu tabla JTable aquí
            exportador.exportarExcel();
        } catch (IOException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(this, "Error al exportar: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ExportarExcelActionPerformed

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
                new Auditoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonChat;
    private javax.swing.JPanel ChatPersonalizacion;
    private javax.swing.JButton ExportarExcel;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JLabel ListaPersonal;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JPanel MiniChat;
    private javax.swing.JScrollPane ScrollChat;
    private javax.swing.JLabel Superior;
    private javax.swing.JLabel TextoChat;
    private javax.swing.JButton btnAuditoria;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnPersonalAcademico;
    private javax.swing.JButton btnTecnicoEquipo;
    private javax.swing.JButton btnTecnicoPrestamo;
    private javax.swing.JButton btnVicerrector;
    private javax.swing.JButton cargardatos;
    private javax.swing.JComboBox<String> cbAccion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    private javax.swing.JTable tablaLogs;
    private javax.swing.JTextField txtBuscarUsuario;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextPane txtPaneChat;
    // End of variables declaration//GEN-END:variables
}
