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
import PersonalAcademico.FormularioPrestamo;
import PersonalAcademico.InicioPersonalAcademico;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rafael
 */
public class SancionesRecibidaPersonal extends javax.swing.JFrame {
    private int idusuario;
    /**
     * Creates new form SancionesPersonal
     * @param idusuario
     */
    public SancionesRecibidaPersonal(int idusuario) {
        this.idusuario = idusuario;
        initComponents();
        aplicarColorFilasAlternadas(TblSanciones);
        cargarNombreCompleto();
        cargarTablaSanciones(idusuario);
        cargarNombreApellido(idusuario);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Nombre.setEditable(false);
        Apellido.setEditable(false);
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


    private SancionesRecibidaPersonal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

        panelSidebar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnSolicitudLab = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        LogoSale1 = new javax.swing.JLabel();
        Sanciones1 = new javax.swing.JButton();
        btnCerrarSesion2 = new javax.swing.JButton();
        panelOverlay = new javax.swing.JLayeredPane();
        Nombretxt = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        Superior = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Sanciones2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        Sanciones4 = new javax.swing.JLabel();
        Sanciones5 = new javax.swing.JLabel();
        Sanciones = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblSanciones = new javax.swing.JTable();
        Sanciones3 = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();
        FondoBlanco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSidebar.setBackground(new java.awt.Color(29, 41, 57));
        panelSidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        panelSidebar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 20, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Panel de Control");
        panelSidebar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        panelSidebar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 230, 30));

        btnSolicitudLab.setBackground(new java.awt.Color(29, 41, 57));
        btnSolicitudLab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSolicitudLab.setForeground(new java.awt.Color(241, 241, 241));
        btnSolicitudLab.setText("Solicitud de Laboratorios");
        btnSolicitudLab.setBorder(null);
        btnSolicitudLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSolicitudLabMouseExited(evt);
            }
        });
        btnSolicitudLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudLabActionPerformed(evt);
            }
        });
        panelSidebar.add(btnSolicitudLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 229, 40));

        btnCerrarSesion.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setBorder(null);
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

        Sanciones1.setBackground(new java.awt.Color(29, 41, 57));
        Sanciones1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Sanciones1.setForeground(new java.awt.Color(255, 255, 255));
        Sanciones1.setText("Sanciones");
        Sanciones1.setBorder(null);
        Sanciones1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sanciones1ActionPerformed(evt);
            }
        });
        panelSidebar.add(Sanciones1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 230, 40));

        btnCerrarSesion2.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion2.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion2.setText("INICIO");
        btnCerrarSesion2.setBorder(null);
        btnCerrarSesion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesion2MouseExited(evt);
            }
        });
        btnCerrarSesion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion2ActionPerformed(evt);
            }
        });
        panelSidebar.add(btnCerrarSesion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 229, 40));

        getContentPane().add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 860));

        panelOverlay.setBackground(new java.awt.Color(0, 0, 0));
        panelOverlay.setOpaque(true);
        panelOverlay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelOverlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 860));

        Nombretxt.setBackground(new java.awt.Color(255, 255, 255));
        Nombretxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Nombretxt.setHorizontalAlignment(SwingConstants.RIGHT);
        getContentPane().add(Nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 240, 30));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sanciones2.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Sanciones2.setText("Sanciones del Usuario");
        jPanel4.add(Sanciones2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel10.setText("Nombre:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        jLabel7.setText("Apellido:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));
        jPanel4.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 190, -1));

        Apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoActionPerformed(evt);
            }
        });
        jPanel4.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 190, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 290, 140));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Sanciones4.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Sanciones4.setText("Nota ");
        jPanel5.add(Sanciones4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        Sanciones5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Sanciones5.setText("Si Sobrepasa la cantidad de 5 sanciones, ");
        jPanel5.add(Sanciones5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, -1));

        Sanciones.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Sanciones.setText("Tendra una sancion economica por daños");
        jPanel5.add(Sanciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 400, 140));

        TblSanciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Descripcion", "Fecha", "Tipo", "Sancionado Por"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Byte.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TblSanciones);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 1460, 540));

        Sanciones3.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Sanciones3.setText("Cantidad de Sanciones Recibidas");
        jPanel2.add(Sanciones3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1500, 760));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        FondoGris1.setMaximumSize(new java.awt.Dimension(1540, 863));
        FondoGris1.setMinimumSize(new java.awt.Dimension(1540, 863));
        FondoGris1.setPreferredSize(new java.awt.Dimension(1540, 863));
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, 0, 1680, 870));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1450, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cargarNombreCompleto() {
        try {
            Connection con = Conexion.obtenerConexion();
            String sql = "SELECT nombre, apellido FROM personal_academico WHERE id_usuario = ?";
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
    public void cargarNombreApellido(int idusuario) {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT nombre, apellido FROM personal_academico WHERE id_usuario = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Nombre.setText(rs.getString("nombre"));
                Apellido.setText(rs.getString("apellido"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos del personal.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    private void cargarTablaSanciones(int idusuario) {
        DefaultTableModel modelo = (DefaultTableModel) TblSanciones.getModel();
        modelo.setRowCount(0);

        try {
            Connection con = Conexion.obtenerConexion();
            String sql = "SELECT s.id_sancion, s.descripcion, s.fecha, s.tipo, "
                    + "CONCAT(tp.nombre, ' ', tp.apellido) AS tecnico_nombre_completo "
                    + "FROM sanciones s "
                    + "INNER JOIN tecnico_prestamos tp ON s.sancionado_por = tp.id_tecnico_prestamos "
                    + "INNER JOIN personal_academico pa ON s.id_personal_academico = pa.id_personal_academico "
                    + "WHERE pa.id_usuario = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_sancion"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha"),
                    rs.getString("tipo"),
                    rs.getString("tecnico_nombre_completo") 
                };
                modelo.addRow(fila);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar sanciones: " + ex.getMessage());
        }
    }

    private void btnSolicitudLabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitudLabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitudLabMouseExited

    private void btnSolicitudLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudLabActionPerformed
        FormularioPrestamo formulario1=new FormularioPrestamo(idusuario);
        formulario1.setLocationRelativeTo(null);
        formulario1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSolicitudLabActionPerformed

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

    private void Sanciones1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sanciones1ActionPerformed
        
    }//GEN-LAST:event_Sanciones1ActionPerformed

    private void btnCerrarSesion2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesion2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion2MouseExited

    private void btnCerrarSesion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion2ActionPerformed
        InicioPersonalAcademico inicio = new InicioPersonalAcademico(idusuario);
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSesion2ActionPerformed

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

    private void ApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoActionPerformed

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
            java.util.logging.Logger.getLogger(SancionesRecibidaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SancionesRecibidaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SancionesRecibidaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SancionesRecibidaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SancionesRecibidaPersonal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JLabel LogoSale1;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel Nombretxt;
    private javax.swing.JLabel Sanciones;
    private javax.swing.JButton Sanciones1;
    private javax.swing.JLabel Sanciones2;
    private javax.swing.JLabel Sanciones3;
    private javax.swing.JLabel Sanciones4;
    private javax.swing.JLabel Sanciones5;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TblSanciones;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCerrarSesion2;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSolicitudLab;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLayeredPane panelOverlay;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JLabel perfil;
    // End of variables declaration//GEN-END:variables
}
