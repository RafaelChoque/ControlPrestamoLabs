
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Rafael
 */
public class FormularioPrestamo extends javax.swing.JFrame {

    /**
     * Creates new form FormularioPrestamo
     */
    public FormularioPrestamo(int idusuario) {
        initComponents();
        cargarTabla();
        cargarNombreApellido(idusuario);
HorarioPersonalizadoInicio.setModel(new SpinnerDateModel());
    JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(HorarioPersonalizadoInicio, "HH:mm");
    HorarioPersonalizadoInicio.setEditor(editorInicio);

    // Configurar el JSpinner para la hora de fin
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

        LogoSale = new javax.swing.JLabel();
        MotivoRechazo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MostrarMotivoRechazo = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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
        Superior = new javax.swing.JLabel();
        Izquierda = new javax.swing.JLabel();
        ListaPersonal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPrestamos = new javax.swing.JTable();
        FondoBlanco = new javax.swing.JLabel();
        FondoGris1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logosaleint.png"))); // NOI18N
        getContentPane().add(LogoSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 170, 60));

        MotivoRechazo.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        MotivoRechazo.setText("Motivo del Rechazo");
        getContentPane().add(MotivoRechazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, 210, -1));

        MostrarMotivoRechazo.setColumns(20);
        MostrarMotivoRechazo.setRows(5);
        jScrollPane2.setViewportView(MostrarMotivoRechazo);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, 420, 210));

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
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 20));

        jLabel7.setText("Apellido");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel1.setText("Horario Fijo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));
        jPanel1.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 330, -1));
        jPanel1.add(Motivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 330, -1));
        jPanel1.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 330, -1));

        Bloque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));
        Bloque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BloqueActionPerformed(evt);
            }
        });
        jPanel1.add(Bloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 330, -1));

        guardar.setBackground(new java.awt.Color(29, 41, 57));
        guardar.setForeground(new java.awt.Color(255, 255, 255));
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        Limpiar.setBackground(new java.awt.Color(29, 41, 57));
        Limpiar.setForeground(new java.awt.Color(255, 255, 255));
        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, -1));

        Formulario.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        Formulario.setText("Formulario de Prestamos");
        jPanel1.add(Formulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, -1));

        jLabel8.setText("Tipo de Horario");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));
        jPanel1.add(HorarioPersonalizadoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 150, -1));

        HorarioFijo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7:30-9:00", "9:15-10:45", "11:00-12:30" }));
        HorarioFijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HorarioFijoActionPerformed(evt);
            }
        });
        jPanel1.add(HorarioFijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 150, -1));

        jLabel9.setText("Bloque:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        Seccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hardware", "Redes", "Telecomunicaciones", "Electronica" }));
        jPanel1.add(Seccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 330, -1));

        jLabel10.setText("Nombre:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));
        jPanel1.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 330, -1));

        TipoHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fijo", "Personalizado"}));
        TipoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoHorarioActionPerformed(evt);
            }
        });
        jPanel1.add(TipoHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, -1, -1));

        jLabel11.setText("Seccion:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));
        jPanel1.add(HorarioPersonalizadoInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 150, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 550, 350));

        Superior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SuperiorInterfaz.png"))); // NOI18N
        getContentPane().add(Superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 80));

        Izquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Recuadro azul.png"))); // NOI18N
        getContentPane().add(Izquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 870));

        ListaPersonal.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        ListaPersonal.setText("Historial de Prestamos");
        getContentPane().add(ListaPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 480, 240, -1));

        TablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Laboratorio", "Nombre", "Apellido", "Motivo", "Bloque", "Seccion", "Fecha", "Horario Inicio", "Horario Fin", "Estado", "Tipo Horario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, 1180, 310));

        FondoBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo_2.png"))); // NOI18N
        getContentPane().add(FondoBlanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 1250, 730));

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
        // Obtener los valores del formulario
        String motivo = Motivo.getText();
        Date fechaGeneral = Fecha.getDate();  // Fecha seleccionada en JDateChooser
        String tipoHorario = TipoHorario.getSelectedItem().toString();

        // Inicializar las variables para hora de inicio y fin
        String horaInicio = "";
        String horaFin = "";

        if (tipoHorario.equals("Fijo")) {
            // Obtener el horario seleccionado del ComboBox
            String horaSeleccionada = HorarioFijo.getSelectedItem().toString();  // ComboBox para horario fijo
            String[] horas = horaSeleccionada.split("-");

            // Extraer hora de inicio y fin
            horaInicio = horas[0].trim();
            horaFin = horas[1].trim();
        } else if (tipoHorario.equals("Personalizado")) {
            horaInicio = HorarioPersonalizadoInicio.getValue().toString();  // JSpinner para hora de inicio personalizada
            horaFin = HorarioPersonalizadoFin.getValue().toString();  // JSpinner para hora de fin personalizada
        }

        // Convertir la fecha y hora a un formato compatible
        java.sql.Date sqlFecha = new java.sql.Date(fechaGeneral.getTime());

        // Validar el formato de hora
        String formatoHora = "^(\\d{1,2}):(\\d{2})$";  // Validación simple para horas y minutos
        if (!horaInicio.matches(formatoHora) || !horaFin.matches(formatoHora)) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un formato de hora válido.");
            return;
        }

        // Parsear las horas
        java.sql.Time sqlHoraInicio = java.sql.Time.valueOf(horaInicio + ":00");
        java.sql.Time sqlHoraFin = java.sql.Time.valueOf(horaFin + ":00");

        // Obtener el laboratorio seleccionado y el personal académico
        int idLaboratorio = obtenerLaboratorioSeleccionado();  // Método ficticio
        int idPersonalAcademico = obtenerIdPersonalAcademico();  // Método ficticio

        // Guardar en la base de datos
        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO prestamos (ID_lab, id_personal_academico, motivo, fecha, horario_inicio, horario_fin, estado, tipo_horario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, idLaboratorio);
            ps.setInt(2, idPersonalAcademico);
            ps.setString(3, motivo);
            ps.setDate(4, sqlFecha);  // Fecha del préstamo
            ps.setTime(5, sqlHoraInicio);  // Hora de inicio
            ps.setTime(6, sqlHoraFin);  // Hora de fin
            ps.setString(7, "Pendiente");  // Estado del préstamo
            ps.setString(8, tipoHorario);  // Tipo de horario (Fijo o Personalizado)

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Préstamo guardado exitosamente");
                limpiarFormulario();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el préstamo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el préstamo: " + e.getMessage());
        }
    }//GEN-LAST:event_guardarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_LimpiarActionPerformed
    private void limpiarFormulario() {
        // Limpiar todos los campos del formulario
        Nombre.setText("");
        Apellido.setText("");
        Motivo.setText("");
        Fecha.setDate(null);
        TipoHorario.setSelectedIndex(0);  // Seleccionar el primer item (Fijo)
        HorarioFijo.setSelectedIndex(0);  // Seleccionar el primer horario fijo
        HorarioPersonalizadoFin.setValue(new Date());  // Limpiar el valor del JSpinner
        Bloque.setSelectedIndex(0);  // Seleccionar el primer bloque
        Seccion.setSelectedIndex(0);  // Seleccionar la primera sección
    }

    private int obtenerLaboratorioSeleccionado() {
        // Aquí debes obtener el ID del laboratorio seleccionado
        return 1;  // Retornar un valor por defecto por ahora
    }

    private int obtenerIdPersonalAcademico() {
        // Aquí debes obtener el ID del personal académico que está logueado
        return 1;  // Retornar un valor por defecto por ahora
    }
    private void BloqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BloqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BloqueActionPerformed

    private void TablaPrestamosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPrestamosMouseClicked

    }//GEN-LAST:event_TablaPrestamosMouseClicked

    private void HorarioFijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorarioFijoActionPerformed
        // Obtener el horario seleccionado del JComboBox
        String selectedTime = (String) HorarioFijo.getSelectedItem();

        // Dividir la cadena por el guion (–) para obtener la hora de inicio y fin
        String[] times = selectedTime.split("–");

        // Extraer la hora de inicio y fin
        String startTime = times[0].trim(); // Hora de inicio
        String endTime = times[1].trim();   // Hora de fin

        // Mostrar las horas obtenidas para verificar
        System.out.println("Hora de inicio: " + startTime);
        System.out.println("Hora de fin: " + endTime);

        // Crear un objeto SimpleDateFormat para parsear las horas
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date startDate = format.parse(startTime);  // Hora de inicio
            Date endDate = format.parse(endTime);      // Hora de fin

            // Asignar las horas al JSpinner de inicio y fin
            ((JSpinner.DefaultEditor) HorarioPersonalizadoInicio.getEditor()).getTextField().setText(format.format(startDate));
            ((JSpinner.DefaultEditor) HorarioPersonalizadoFin.getEditor()).getTextField().setText(format.format(endDate));

        } catch (Exception ex) {
            ex.printStackTrace(); // Manejar el error si la hora no tiene el formato esperado
        }
    }//GEN-LAST:event_HorarioFijoActionPerformed

    private void TipoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoHorarioActionPerformed
        String tipo = TipoHorario.getSelectedItem().toString();
        boolean esPersonalizado = tipo.equals("Personalizado");

        HorarioFijo.setVisible(!esPersonalizado);
        HorarioPersonalizadoInicio.setVisible(esPersonalizado);
        HorarioPersonalizadoFin.setVisible(esPersonalizado);
    }//GEN-LAST:event_TipoHorarioActionPerformed
    private void cargarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) TablaPrestamos.getModel();
        modeloTabla.setRowCount(0);  // Limpiar la tabla antes de llenarla

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        // Definir los anchos de las columnas de la tabla
        int[] anchos = {10, 100, 100, 100, 80, 80, 80, 100, 100, 80, 80,80};
        for (int i = 0; i < TablaPrestamos.getColumnCount(); i++) {
            TablaPrestamos.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        try {
            // Establecer la conexión con la base de datos
            Connection con = Conexion.obtenerConexion();  // Usar tu método de conexión
            ps = con.prepareStatement(
                    "SELECT p.id_prestamo, l.Nombre_lab, pa.nombre, pa.apellido, p.motivo, l.bloque, l.seccion, p.fecha, p.horario_inicio, p.horario_fin, p.estado, p.tipo_horario "
                    + "FROM prestamos p "
                    + "INNER JOIN laboratorios l ON p.ID_lab = l.ID_lab "
                    + "INNER JOIN personal_academico pa ON p.id_personal_academico = pa.id_personal_academico"
            );
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            // Llenar la tabla con los datos obtenidos
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }
                modeloTabla.addRow(fila);  // Añadir la fila a la tabla
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JTextField Apellido;
    private javax.swing.JComboBox<String> Bloque;
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
    private javax.swing.JTextArea MostrarMotivoRechazo;
    private javax.swing.JTextField Motivo;
    private javax.swing.JLabel MotivoRechazo;
    private javax.swing.JTextField Nombre;
    private javax.swing.JComboBox<String> Seccion;
    private javax.swing.JLabel Superior;
    private javax.swing.JTable TablaPrestamos;
    private javax.swing.JComboBox<String> TipoHorario;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
