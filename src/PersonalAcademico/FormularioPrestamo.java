package PersonalAcademico;

import ConexionLogin.Conexion;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableCellRenderer;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Rafael
 */
public class FormularioPrestamo extends javax.swing.JFrame {

    private int idusuario;

    /**
     * Creates new form FormularioPrestamo
     *
     * @param idusuario
     */
    public FormularioPrestamo(int idusuario) {
        initComponents();
        this.idusuario = idusuario;

        Nombre.setEditable(false);
        Apellido.setEditable(false);

        cargarTabla(idusuario);
        cargarTabla2();
        cargarNombreApellido(idusuario);

        HorarioPersonalizadoInicio.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(HorarioPersonalizadoInicio, "HH:mm");
        HorarioPersonalizadoInicio.setEditor(editorInicio);

        HorarioPersonalizadoFin.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(HorarioPersonalizadoFin, "HH:mm");
        HorarioPersonalizadoFin.setEditor(editorFin);
    }

    private FormularioPrestamo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LogoSale = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnCerrarSesion1 = new javax.swing.JButton();
        btnCerrarSesion2 = new javax.swing.JButton();
        btnCerrarSesion3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        perfil = new javax.swing.JLabel();
        Superior = new javax.swing.JLabel();
        Izquierda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        MotivoRechazo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPrestamos2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Actualizar = new javax.swing.JButton();
        Nombre = new javax.swing.JTextField();
        Motivo = new javax.swing.JTextField();
        Fecha = new com.toedter.calendar.JDateChooser();
        Bloque = new javax.swing.JComboBox<>();
        guardar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        Formulario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        HorarioPersonalizadoFin = new javax.swing.JSpinner();
        HorarioFijo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Seccion = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        Apellido = new javax.swing.JTextField();
        TipoHorario = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        HorarioPersonalizadoInicio = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        SeleccionLab = new javax.swing.JTextField();
        DisponibilidadPrestamo = new javax.swing.JButton();
        ListaPersonal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPrestamos = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Menú Principal");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarsesion.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 820, 20, 30));

        LogoSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoUSB.png"))); // NOI18N
        getContentPane().add(LogoSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 170, 60));

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
        getContentPane().add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 813, 229, 40));

        btnCerrarSesion1.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion1.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion1.setText("Cerrar Sesión");
        btnCerrarSesion1.setBorder(null);
        btnCerrarSesion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesion1MouseExited(evt);
            }
        });
        btnCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrarSesion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 229, 40));

        btnCerrarSesion2.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion2.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion2.setText("Cerrar Sesión");
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
        getContentPane().add(btnCerrarSesion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 229, 40));

        btnCerrarSesion3.setBackground(new java.awt.Color(29, 41, 57));
        btnCerrarSesion3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrarSesion3.setForeground(new java.awt.Color(241, 241, 241));
        btnCerrarSesion3.setText("Cerrar Sesión");
        btnCerrarSesion3.setBorder(null);
        btnCerrarSesion3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesion3MouseExited(evt);
            }
        });
        btnCerrarSesion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion3ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrarSesion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 229, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoBar.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 230, 40));

        perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconouser.png"))); // NOI18N
        getContentPane().add(perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 40, -1));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 60));

        Izquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Recuadro azul.png"))); // NOI18N
        getContentPane().add(Izquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 860));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MotivoRechazo.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        MotivoRechazo.setText("Descripción");
        jPanel2.add(MotivoRechazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TablaPrestamos2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Motivo", "Bloque", "Seccion", "Tipo de  Horario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        int[]anchos2 = {50, 215, 70, 130, 130};
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < anchos2.length; i++) {
            TablaPrestamos2.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
            TablaPrestamos2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
        }
        jScrollPane2.setViewportView(TablaPrestamos2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 590, 320));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 630, 380));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(194, 194, 194)));
        jPanel1.setToolTipText("");
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Motivo del Prestamo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 20));

        jLabel5.setText("Fecha:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        jLabel6.setText("Horario Personalizado");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 20));

        jLabel7.setText("Apellido");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel1.setText("Horario Fijo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 20));

        Actualizar.setBackground(new java.awt.Color(29, 41, 57));
        Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, -1, -1));
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 370, -1));
        jPanel1.add(Motivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 370, -1));
        jPanel1.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 370, -1));

        Bloque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));
        Bloque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BloqueActionPerformed(evt);
            }
        });
        jPanel1.add(Bloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 370, -1));

        guardar.setBackground(new java.awt.Color(29, 41, 57));
        guardar.setForeground(new java.awt.Color(255, 255, 255));
        guardar.setText("Solicitar Laboratorio");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        Limpiar.setBackground(new java.awt.Color(29, 41, 57));
        Limpiar.setForeground(new java.awt.Color(255, 255, 255));
        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, -1, -1));

        Formulario.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Formulario.setText("Formulario de Prestamos");
        jPanel1.add(Formulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, -1));

        jLabel8.setText("Tipo de Horario");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));
        jPanel1.add(HorarioPersonalizadoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 150, -1));

        HorarioFijo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:30 - 9:00", "9:15 - 10:45", "11:00 - 12:30" }));
        HorarioFijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HorarioFijoActionPerformed(evt);
            }
        });
        jPanel1.add(HorarioFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 150, -1));

        jLabel9.setText("Bloque:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        Seccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Redes", "Telecomunicaciones", "Electronica" }));
        jPanel1.add(Seccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 370, -1));

        jLabel10.setText("Nombre:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 370, -1));

        TipoHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fijo", "Personalizado"}));
        TipoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoHorarioActionPerformed(evt);
            }
        });
        jPanel1.add(TipoHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 150, -1));

        jLabel11.setText("Laboratorio");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));
        jPanel1.add(HorarioPersonalizadoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 150, -1));

        jLabel2.setText("Hasta:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, -1, 20));

        jLabel15.setText("Seccion:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        SeleccionLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionLabActionPerformed(evt);
            }
        });
        jPanel1.add(SeleccionLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 150, -1));

        DisponibilidadPrestamo.setText("Seleccion de Laboratorio");
        DisponibilidadPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisponibilidadPrestamoActionPerformed(evt);
            }
        });
        jPanel1.add(DisponibilidadPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 550, 380));

        ListaPersonal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaPersonal.setText("Historial de Prestamos");
        getContentPane().add(ListaPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 240, -1));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Laboratorio", "Nombre", "Apellido", "Fecha", "Horario Inicio", "Horario Fin", "Estado de Solicitud", "Motivo del Rechazo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPrestamosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaPrestamos);
        int[] anchos = {50, 100, 120, 200, 120, 120, 100, 130, 250};
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < anchos.length; i++) {
            TablaPrestamos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            TablaPrestamos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, 1200, 310));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 1250, 760));

        FondoGris1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_3.png"))); // NOI18N
        getContentPane().add(FondoGris1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1680, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents
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

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        String motivo = Motivo.getText();
        Date fechaGeneral = Fecha.getDate();
        String laboratorio = SeleccionLab.getText();
        String tipoHorario = TipoHorario.getSelectedItem().toString();
        String bloque = (String) Bloque.getSelectedItem();
        String seccion = (String) Seccion.getSelectedItem();
        String horaInicio = "";
        String horaFin = "";

        if (motivo.isEmpty() || laboratorio.isEmpty() || fechaGeneral == null) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos requeridos.");
            return;
        }

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        if (tipoHorario.equals("Fijo")) {
            String horaSeleccionada = (String) HorarioFijo.getSelectedItem();
            if (horaSeleccionada == null || horaSeleccionada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un horario fijo válido.");
                return;
            }

            String[] horas = horaSeleccionada.split("-");
            if (horas.length != 2) {
                JOptionPane.showMessageDialog(null, "Formato de horario fijo inválido. Use el formato 'HH:mm - HH:mm'.");
                return;
            }

            horaInicio = horas[0].trim();
            horaFin = horas[1].trim();

        } else if (tipoHorario.equals("Personalizado")) {
            try {
                Date inicio = (Date) HorarioPersonalizadoInicio.getValue();
                Date fin = (Date) HorarioPersonalizadoFin.getValue();

                if (inicio.after(fin)) {
                    JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser mayor que la de fin.");
                    return;
                }

                horaInicio = formatoHora.format(inicio);
                horaFin = formatoHora.format(fin);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al obtener las horas personalizadas.");
                return;
            }
        }

        String regexHora = "^(\\d{1,2}):(\\d{2})$";
        if (!horaInicio.matches(regexHora) || !horaFin.matches(regexHora)) {
            JOptionPane.showMessageDialog(null, "El formato de la hora debe ser HH:mm.");
            return;
        }

        java.sql.Date sqlFecha = new java.sql.Date(fechaGeneral.getTime());
        java.sql.Time sqlHoraInicio = java.sql.Time.valueOf(horaInicio + ":00");
        java.sql.Time sqlHoraFin = java.sql.Time.valueOf(horaFin + ":00");

        int idLaboratorio = obtenerIdLaboratorio(bloque, seccion);
        int idPersonalAcademico = obtenerIdPersonalAcademico();

        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO prestamos (ID_lab, id_personal_academico, motivo, fecha, horario_inicio, horario_fin, estado, tipo_horario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, idLaboratorio);
            ps.setInt(2, idPersonalAcademico);
            ps.setString(3, motivo);
            ps.setDate(4, sqlFecha);
            ps.setTime(5, sqlHoraInicio);
            ps.setTime(6, sqlHoraFin);
            ps.setString(7, "Pendiente");
            ps.setString(8, tipoHorario);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Préstamo guardado exitosamente");
                limpiarFormulario();
                cargarTabla(idusuario);
                cargarTabla2();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el préstamo");
            }
            cargarTabla(idusuario);
            cargarTabla2();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el préstamo: " + e.getMessage());
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_LimpiarActionPerformed
    private void limpiarFormulario() {
        Motivo.setText("");
        Fecha.setDate(null);
        TipoHorario.setSelectedIndex(0);
        HorarioFijo.setSelectedIndex(0);
        HorarioPersonalizadoFin.setValue(new Date());
        Bloque.setSelectedIndex(0);
        Seccion.setSelectedIndex(0);
    }

    private int obtenerIdLaboratorio(String bloque, String seccion) {
        int idLaboratorio = -1;

        try {
            Connection con = Conexion.obtenerConexion();
            String query = "SELECT id_lab FROM laboratorios WHERE bloque = ? AND seccion = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, bloque);
            ps.setString(2, seccion);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idLaboratorio = rs.getInt("id_lab");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del laboratorio: " + e.getMessage());
        }

        return idLaboratorio;
    }

    private int obtenerIdPersonalAcademico() {
        int id = -1;
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("SELECT id_personal_academico FROM personal_academico WHERE id_usuario = ?");
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_personal_academico");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del personal académico: " + e.getMessage());
        }
        return id;
    }

    private void BloqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BloqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BloqueActionPerformed

    private void TablaPrestamosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPrestamosMouseClicked

    }//GEN-LAST:event_TablaPrestamosMouseClicked

    private void HorarioFijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorarioFijoActionPerformed
        String selectedTime = (String) HorarioFijo.getSelectedItem();

        if (selectedTime == null || selectedTime.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún horario.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] times = selectedTime.split(" - ");

        if (times.length < 2) {
            JOptionPane.showMessageDialog(this, "Formato de horario inválido. Asegúrate de seleccionar un horario como '7:30 - 9:00'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String startTime = times[0].trim();
        String endTime = times[1].trim();

        System.out.println("Hora de inicio: " + startTime);
        System.out.println("Hora de fin: " + endTime);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date startDate = format.parse(startTime);
            Date endDate = format.parse(endTime);

            ((JSpinner.DefaultEditor) HorarioPersonalizadoInicio.getEditor()).getTextField().setText(format.format(startDate));
            ((JSpinner.DefaultEditor) HorarioPersonalizadoFin.getEditor()).getTextField().setText(format.format(endDate));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al convertir la hora. Asegúrate de que el horario tenga el formato correcto (HH:mm).", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_HorarioFijoActionPerformed

    private void TipoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoHorarioActionPerformed
        String tipo = TipoHorario.getSelectedItem().toString();
        boolean esPersonalizado = tipo.equals("Personalizado");

        HorarioFijo.setVisible(!esPersonalizado);
        HorarioPersonalizadoInicio.setVisible(esPersonalizado);
        HorarioPersonalizadoFin.setVisible(esPersonalizado);
    }//GEN-LAST:event_TipoHorarioActionPerformed

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited

    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnCerrarSesion1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesion1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion1MouseExited

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion1ActionPerformed

    private void btnCerrarSesion2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesion2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion2MouseExited

    private void btnCerrarSesion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion2ActionPerformed

    private void btnCerrarSesion3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesion3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion3MouseExited

    private void btnCerrarSesion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesion3ActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        cargarTabla(idusuario);
        cargarTabla2();
    }//GEN-LAST:event_ActualizarActionPerformed

    private void DisponibilidadPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisponibilidadPrestamoActionPerformed
        DisponibilidadPrestamos disponibilidadpres = new DisponibilidadPrestamos();
        disponibilidadpres.setLocationRelativeTo(null);
        disponibilidadpres.setVisible(true);
    }//GEN-LAST:event_DisponibilidadPrestamoActionPerformed

    private void SeleccionLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionLabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeleccionLabActionPerformed
public void cargarTabla(int idusuario) {
    try {
        Connection con = Conexion.obtenerConexion();

        String query = "SELECT p.id_prestamo, l.Nombre_lab, pa.nombre, pa.apellido, p.motivo, l.bloque, l.seccion, p.fecha, p.horario_inicio, p.horario_fin, p.estado, p.motivo_rechazo "
                + "FROM prestamos p "
                + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico "
                + "WHERE pa.id_usuario = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idusuario);
        ResultSet rs = ps.executeQuery();
        DefaultTableModel model = (DefaultTableModel) TablaPrestamos.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            int idPrestamo = rs.getInt("id_prestamo");
            String nombreLab = rs.getString("Nombre_lab");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            Date fecha = rs.getDate("fecha");
            Time horaInicio = rs.getTime("horario_inicio");
            Time horaFin = rs.getTime("horario_fin");
            String estado = rs.getString("estado");
            String motivoRechazo = rs.getString("motivo_rechazo"); // Obtener el motivo de rechazo

            model.addRow(new Object[]{
                idPrestamo, nombreLab, nombre, apellido, 
                fecha, horaInicio, horaFin, estado, motivoRechazo // Añadir el motivo de rechazo aquí
            });
        }

    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
}
    public void cargarTabla2() {
        try {
            Connection con = Conexion.obtenerConexion();

            String query = "SELECT p.id_prestamo, p.motivo, l.bloque, l.seccion,p.tipo_horario "
                    + "FROM prestamos p "
                    + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico "
                    + "WHERE pa.id_usuario = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model2 = (DefaultTableModel) TablaPrestamos2.getModel();
            model2.setRowCount(0);

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                String motivo = rs.getString("motivo");
                String bloque = rs.getString("bloque");
                String seccion = rs.getString("seccion");
                String tipoHorario = rs.getString("tipo_horario");

                model2.addRow(new Object[]{
                    idPrestamo, motivo, bloque, seccion, tipoHorario
                });
            }

            if (model2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Aún no tienes préstamos registrados.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioPrestamo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JTextField Apellido;
    private javax.swing.JComboBox<String> Bloque;
    private javax.swing.JButton DisponibilidadPrestamo;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JLabel FondoBlanco;
    private javax.swing.JLabel FondoGris1;
    private javax.swing.JLabel Formulario;
    private javax.swing.JComboBox<String> HorarioFijo;
    private javax.swing.JSpinner HorarioPersonalizadoFin;
    private javax.swing.JSpinner HorarioPersonalizadoInicio;
    private javax.swing.JLabel Izquierda;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel ListaPersonal;
    private javax.swing.JLabel LogoSale;
    private javax.swing.JTextField Motivo;
    private javax.swing.JLabel MotivoRechazo;
    private javax.swing.JTextField Nombre;
    private javax.swing.JComboBox<String> Seccion;
    private javax.swing.JTextField SeleccionLab;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaPrestamos;
    private javax.swing.JTable TablaPrestamos2;
    private javax.swing.JComboBox<String> TipoHorario;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnCerrarSesion2;
    private javax.swing.JButton btnCerrarSesion3;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel perfil;
    // End of variables declaration//GEN-END:variables
}
