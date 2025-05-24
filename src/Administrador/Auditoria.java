/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Administrador;

import ConexionLogin.Conexion;
import ConexionLogin.Login;
import ConexionLogin.SesionUsuario;
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
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
        jPanel2 = new javax.swing.JPanel();
        ListaPersonal = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaLogs = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();
        cargardatos = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        perfil = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        FondoGris = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ListaPersonal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaPersonal.setText("Logs");
        jPanel2.add(ListaPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 340, -1));

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
        getContentPane().add(FondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 860));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris;
    private javax.swing.JLabel ListaPersonal;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JLabel Superior;
    private javax.swing.JButton btnAuditoria;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnPersonalAcademico;
    private javax.swing.JButton btnTecnicoEquipo;
    private javax.swing.JButton btnTecnicoPrestamo;
    private javax.swing.JButton btnVicerrector;
    private javax.swing.JButton cargardatos;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    private javax.swing.JTable tablaLogs;
    // End of variables declaration//GEN-END:variables
}
