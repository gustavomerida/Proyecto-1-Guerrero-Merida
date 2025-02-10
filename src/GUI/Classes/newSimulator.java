/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Classes;

import MainClasses.PCB;
import MainClasses.ProcesoCPUBOUND;
import MainPackage.App;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Angelo
 */
public class newSimulator extends JFrame {

    private final App app = App.getInstance();

    private JLabel estadoLabel, marLabel, pcLabel;
    private JButton startButton, stopButton;
    private ProcesoCPUBOUND proceso;
    private Thread procesoThread;
    private JPanel panel;

    // SOLO PARA PRUEBAS
    private Timer uiUpdater;

    public newSimulator() {

        setTitle("Pruebas de corrida de instrucciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        estadoLabel = new JLabel("Estado: Inactivo");
        marLabel = new JLabel("MAR: -");
        pcLabel = new JLabel("PC: -");

        startButton = new JButton("Iniciar Proceso");
        stopButton = new JButton("Detener Proceso");

        panel.add(new JLabel("Estado del Proceso:"));
        panel.add(estadoLabel);
        panel.add(new JLabel("MAR:"));
        panel.add(marLabel);
        panel.add(new JLabel("PC:"));
        panel.add(pcLabel);
        panel.add(startButton);
        panel.add(stopButton);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (procesoThread == null || !procesoThread.isAlive()) {
                    iniciarProceso();
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proceso != null) {
                    proceso.getPCB_proceso().setEstado("Exit");
                }
            }
        });

    }

    private void iniciarProceso() {
        PCB pcb = new PCB(1, "nombre", "Running", app.getCpu1().getActualProceso().getPCB_proceso().getAmbienteEjecucion()); // Suponiendo que tienes la clase PCB implementada
        proceso = new ProcesoCPUBOUND("Proceso1", 10, "CPU BOUND", pcb, 1000);

        procesoThread = new Thread(proceso);
        procesoThread.start();

        // Hilo que actualiza la UI periódicamente
        uiUpdater = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    estadoLabel.setText("Estado: " + proceso.getPCB_proceso().getEstado());
                    marLabel.setText("MAR: " + proceso.getPCB_proceso().getAmbienteEjecucion().getMAR());
                    pcLabel.setText("PC: " + proceso.getPCB_proceso().getAmbienteEjecucion().getPc());
                });
            }
        });
        uiUpdater.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new newSimulator().setVisible(true);
        });
    }

}
