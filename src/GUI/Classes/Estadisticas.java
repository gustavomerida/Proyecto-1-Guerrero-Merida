/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.Classes;

import MainPackage.App;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Angelo
 */
public class Estadisticas extends javax.swing.JFrame {

    /**
     * Creates new form Estadisticas
     */
    private Point initialClick;
    private final App app = App.getInstance();
    private XYSeries seriesCpuBound;
    private XYSeries seriesIoBound;
    private Timer updateTimer;
    private static Estadisticas instance;

    public static synchronized Estadisticas getInstance() {
        if (instance == null) {
            instance = new Estadisticas();
        }
        return instance;
    }

    public Estadisticas() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(1100, 605);
        this.setLocationRelativeTo(null);

        jPanelChartSystem.setLayout(new java.awt.BorderLayout());
        jPanelChartSystem.add(app.getChartClassSystem().getChartClass(), java.awt.BorderLayout.CENTER);
        jPanelChartSystem.validate();
        
        jPanelChartCPU1.setLayout(new java.awt.BorderLayout());
        jPanelChartCPU1.add(app.getChartClassCPU1().getChartClass(), java.awt.BorderLayout.CENTER);
        jPanelChartCPU1.validate();
        
        jPanelChartCPU2.setLayout(new java.awt.BorderLayout());
        jPanelChartCPU2.add(app.getChartClassCPU2().getChartClass(), java.awt.BorderLayout.CENTER);
        jPanelChartCPU2.validate();
        
        jPanelChartCPU3.setLayout(new java.awt.BorderLayout());
        jPanelChartCPU3.add(app.getChartClassCPU3().getChartClass(), java.awt.BorderLayout.CENTER);
        jPanelChartCPU3.validate();
    }

    private void start() {
        // Crear un nuevo hilo para el bucle infinito
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });

                        // Pausar el hilo separado, no el EDT
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });

        updateThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stadisticsButtons = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jPanelChartSystem = new javax.swing.JPanel();
        jPanelChartCPU1 = new javax.swing.JPanel();
        jPanelChartCPU3 = new javax.swing.JPanel();
        jPanelChartCPU2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cycleDurationLabel = new javax.swing.JLabel();
        currentAlgorithmLabel = new javax.swing.JLabel();
        Salir = new javax.swing.JButton();
        CreateProcess = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 51));
        jLabel4.setForeground(new java.awt.Color(255, 215, 0));
        jLabel4.setText("SISTEMA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 60, -1));
        getContentPane().add(jPanelChartSystem, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 370, 190));
        getContentPane().add(jPanelChartCPU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 370, 190));
        getContentPane().add(jPanelChartCPU3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 370, 190));
        getContentPane().add(jPanelChartCPU2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 370, 190));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 215, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("GRAFICO ESTADISTICO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 420, 60));

        jLabel1.setForeground(new java.awt.Color(255, 215, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/solid-background-color.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 890, 580));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cycleDurationLabel.setForeground(new java.awt.Color(255, 215, 0));
        cycleDurationLabel.setText("Ciclos de reloj:");
        jPanel2.add(cycleDurationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 140, 30));

        currentAlgorithmLabel.setForeground(new java.awt.Color(255, 215, 0));
        currentAlgorithmLabel.setText("Algoritmo actual:");
        jPanel2.add(currentAlgorithmLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 130, 20));

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jPanel2.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 110, 40));

        CreateProcess.setText("Interfaz de Creación");
        CreateProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateProcessActionPerformed(evt);
            }
        });
        jPanel2.add(CreateProcess, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 140, 40));

        jButton4.setText("Simulador");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 110, 40));

        jButton5.setText("Estadistica");
        jButton5.setMaximumSize(new java.awt.Dimension(72, 23));
        jButton5.setMinimumSize(new java.awt.Dimension(72, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 110, 40));

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 110, 40));

        homeButton.setText("Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        jPanel2.add(homeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 110, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/background2.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 640));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SalirActionPerformed

    private void CreateProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateProcessActionPerformed

        ProcessMaker processMakerWindow = null;
        try {
            processMakerWindow = new ProcessMaker();
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Estadisticas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        processMakerWindow.setVisible(true);

    }//GEN-LAST:event_CreateProcessActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed

        Home home = new Home();
        this.setVisible(false);
        home.setVisible(true);
    }//GEN-LAST:event_homeButtonActionPerformed
   /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateProcess;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel currentAlgorithmLabel;
    private javax.swing.JLabel cycleDurationLabel;
    private javax.swing.JButton homeButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelChartCPU1;
    private javax.swing.JPanel jPanelChartCPU2;
    private javax.swing.JPanel jPanelChartCPU3;
    private javax.swing.JPanel jPanelChartSystem;
    private javax.swing.ButtonGroup stadisticsButtons;
    // End of variables declaration//GEN-END:variables
}
