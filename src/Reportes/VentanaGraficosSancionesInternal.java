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

public class VentanaGraficosSancionesInternal extends JPanel {

    private DefaultTableModel model;

    public VentanaGraficosSancionesInternal(DefaultTableModel model) {
        this.model = model;

        setLayout(new GridLayout(2, 2)); // 2x2 para 4 gráficos
        setPreferredSize(new Dimension(1000, 800)); // Tamaño del panel

        add(crearPanelBarraPorTipo());
        add(crearPanelTortaPorTecnico());
        add(crearPanelTortaPorSancionado());
        add(crearPanelBarraPorFecha());
    }

    private JPanel crearPanelBarraPorTipo() {
        Map<String, Integer> sancionesPorTipo = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tipo = model.getValueAt(i, 3).toString(); // Índice 3 para tipo
            sancionesPorTipo.put(tipo, sancionesPorTipo.getOrDefault(tipo, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : sancionesPorTipo.entrySet()) {
            dataset.addValue(entry.getValue(), "Sanciones", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Sanciones por Tipo", "Tipo", "Cantidad",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelTortaPorTecnico() {
        Map<String, Integer> sancionesPorTecnico = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tecnico = model.getValueAt(i, 4).toString(); // Índice 4 para técnico
            sancionesPorTecnico.put(tecnico, sancionesPorTecnico.getOrDefault(tecnico, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : sancionesPorTecnico.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Sanciones por Técnico", dataset, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelTortaPorSancionado() {
        Map<String, Integer> sancionesPorSancionado = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String sancionado = model.getValueAt(i, 5).toString(); // Índice 5 para sancionado
            sancionesPorSancionado.put(sancionado, sancionesPorSancionado.getOrDefault(sancionado, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : sancionesPorSancionado.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Sanciones por Sancionado", dataset, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelBarraPorFecha() {
        Map<String, Integer> sancionesPorFecha = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String fecha = model.getValueAt(i, 2).toString(); // Índice 2 para fecha
            sancionesPorFecha.put(fecha, sancionesPorFecha.getOrDefault(fecha, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : sancionesPorFecha.entrySet()) {
            dataset.addValue(entry.getValue(), "Sanciones", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Sanciones por Fecha", "Fecha", "Cantidad",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }
}
