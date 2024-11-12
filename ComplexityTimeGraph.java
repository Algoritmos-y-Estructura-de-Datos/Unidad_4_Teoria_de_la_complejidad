package java_awt6;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ComplexityTimeGraph extends JFrame {

    private static final int NUM_POINTS = 1000;

    public ComplexityTimeGraph(String title) {
        super(title);

        XYSeries linearSeries = new XYSeries("Linear");
        XYSeries quadraticSeries = new XYSeries("Quadratic");
        XYSeries logarithmicSeries = new XYSeries("Logarithmic");
        XYSeries exponentialSeries = new XYSeries("Exponential");
        XYSeries nLogNSeries = new XYSeries("n log n");

        // Capturar el tiempo de inicio
        long startTime = System.nanoTime();

        for (int i = 1; i <= NUM_POINTS; i++) {
            linearSeries.add(i, i);
            quadraticSeries.add(i, i * i);
            logarithmicSeries.add(i, Math.log(i));
            exponentialSeries.add(i, Math.exp(i));
            nLogNSeries.add(i, i * Math.log(i));
        }

        // Capturar el tiempo de fin
        long endTime = System.nanoTime();

        // Calcular el tiempo total de ejecución
        long duration = (endTime - startTime) / 1000000; // Convertir nanosegundos a milisegundos

        System.out.println("Tiempo total de ejecución: " + duration + " milisegundos");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(logarithmicSeries);
        dataset.addSeries(linearSeries);
        //dataset.addSeries(nLogNSeries);
        //dataset.addSeries(quadraticSeries);

        //dataset.addSeries(exponentialSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Complexity Comparison",
                "Input Size",
                "Time",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	ComplexityTimeGraph graph = new ComplexityTimeGraph("Complexity Graph");
                graph.setSize(800, 400);
                graph.setLocationRelativeTo(null);
                graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                graph.setVisible(true);
            }
        });
    }
}
