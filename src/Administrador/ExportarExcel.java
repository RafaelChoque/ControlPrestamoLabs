/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

/**
 *
 * @author Rafael
 */
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * Clase que permite exportar el contenido de la tablaLogs a un archivo Excel .xls
 */
public class ExportarExcel {

    private JTable tablaLogs;

    // Constructor para recibir la tabla desde afuera
    public ExportarExcel(JTable tablaLogs) {
        this.tablaLogs = tablaLogs;
    }

    // Método que realiza la exportación
    public void exportarExcel() throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString();
            if (!ruta.toLowerCase().endsWith(".xls")) {
                ruta += ".xls";
            }

            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }

                Workbook libro = new HSSFWorkbook(); // Formato .xls
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Hoja de Logs");
                hoja.setDisplayGridlines(false);

                // Encabezados
                Row encabezado = hoja.createRow(0);
                for (int c = 0; c < tablaLogs.getColumnCount(); c++) {
                    Cell celda = encabezado.createCell(c);
                    celda.setCellValue(tablaLogs.getColumnName(c));
                }

                // Datos
                for (int f = 0; f < tablaLogs.getRowCount(); f++) {
                    Row fila = hoja.createRow(f + 1);
                    for (int c = 0; c < tablaLogs.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        Object valor = tablaLogs.getValueAt(f, c);
                        if (valor instanceof Number) {
                            celda.setCellValue(Double.parseDouble(valor.toString()));
                        } else {
                            celda.setCellValue(String.valueOf(valor));
                        }
                    }
                }

                libro.write(archivo);
                archivo.close();

                // Abrir el archivo automáticamente
                Desktop.getDesktop().open(archivoXLS);

            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
}
