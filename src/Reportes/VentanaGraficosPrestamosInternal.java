/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;

/**
 *
 * @author Rafael
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class VentanaGraficosPrestamosInternal extends JPanel {

    private DefaultTableModel model;

    public VentanaGraficosPrestamosInternal(DefaultTableModel model) {
        this.model = model;

        setLayout(new GridLayout(2, 2)); // 2x2 para 4 gráficos
        setPreferredSize(new Dimension(1000, 800)); // Tamaño ajustado para el panel

        add(crearPanelBarraPorDocente());
        add(crearPanelTortaPorLaboratorio());
        add(crearPanelTortaPorSeccion());
        add(crearPanelBarraPorEstado());
    }

    private JPanel crearPanelBarraPorDocente() {
        Map<String, Integer> prestamosPorDocente = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String docente = model.getValueAt(i, 1).toString(); // Índice 1 para docente
            prestamosPorDocente.put(docente, prestamosPorDocente.getOrDefault(docente, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : prestamosPorDocente.entrySet()) {
            dataset.addValue(entry.getValue(), "Préstamos", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Préstamos por Docente", "Docente", "Cantidad",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelTortaPorLaboratorio() {
        Map<String, Integer> prestamosPorLaboratorio = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String laboratorio = model.getValueAt(i, 3).toString(); // Índice 3 para laboratorio
            prestamosPorLaboratorio.put(laboratorio, prestamosPorLaboratorio.getOrDefault(laboratorio, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : prestamosPorLaboratorio.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Préstamos por Laboratorio", dataset, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelTortaPorSeccion() {
        Map<String, Integer> prestamosPorSeccion = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String seccion = model.getValueAt(i, 2).toString(); // Índice 2 para sección
            prestamosPorSeccion.put(seccion, prestamosPorSeccion.getOrDefault(seccion, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : prestamosPorSeccion.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Préstamos por Sección", dataset, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelBarraPorEstado() {
        Map<String, Integer> prestamosPorEstado = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String estado = model.getValueAt(i, 7).toString(); 
            prestamosPorEstado.put(estado, prestamosPorEstado.getOrDefault(estado, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : prestamosPorEstado.entrySet()) {
            dataset.addValue(entry.getValue(), "Préstamos", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Préstamos por Estado", "Estado", "Cantidad",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }
}

