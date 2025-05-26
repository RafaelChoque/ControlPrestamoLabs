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

public class VentanaGraficosMultiplesMantenimiento extends JFrame {
    private DefaultTableModel model;

    public VentanaGraficosMultiplesMantenimiento(DefaultTableModel model) {
        this.model = model;
        setTitle("Gráficos de Mantenimientos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2)); // Mostrar 4 gráficos en 2x2

        add(crearPanelBarraPorTecnico());
        add(crearPanelTortaPorComputadora());
        add(crearPanelTortaPorLaboratorio());
        add(crearPanelTortaPorEstado());
    }

    private JPanel crearPanelBarraPorTecnico() {
        Map<String, Integer> mantenimientoPorTecnico = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String tecnico = model.getValueAt(i, 6).toString();
            mantenimientoPorTecnico.put(tecnico, mantenimientoPorTecnico.getOrDefault(tecnico, 0) + 1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : mantenimientoPorTecnico.entrySet()) {
            dataset.addValue(entry.getValue(), "Mantenimientos", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Mantenimientos por Técnico", "Técnico", "Cantidad",
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelTortaPorComputadora() {
        Map<String, Integer> mantenimientoPorComputadora = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String computadora = model.getValueAt(i, 1).toString();
            mantenimientoPorComputadora.put(computadora, mantenimientoPorComputadora.getOrDefault(computadora, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : mantenimientoPorComputadora.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Distribución por Computadora", dataset, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelTortaPorLaboratorio() {
        Map<String, Integer> mantenimientoPorLaboratorio = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String lab = model.getValueAt(i, 2).toString();
            mantenimientoPorLaboratorio.put(lab, mantenimientoPorLaboratorio.getOrDefault(lab, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : mantenimientoPorLaboratorio.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Distribución por Laboratorio", dataset, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel crearPanelTortaPorEstado() {
        Map<String, Integer> mantenimientoPorEstado = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String estado = model.getValueAt(i, 3).toString();
            mantenimientoPorEstado.put(estado, mantenimientoPorEstado.getOrDefault(estado, 0) + 1);
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : mantenimientoPorEstado.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Distribución por Estado", dataset, true, true, false);

        return new ChartPanel(chart);
    }
}


