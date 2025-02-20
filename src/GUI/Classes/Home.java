/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.Classes;

import MainPackage.App;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home2
     */
    // Principal y unica instancia app
    private final App app = App.getInstance();

    public Home() {
        initComponents();
        this.setResizable(false);
        this.setSize(1100, 625);
        this.setLocationRelativeTo(null);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        CreateProcess = new javax.swing.JButton();
        simulatorButton = new javax.swing.JButton();
        stadisticsButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        backgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 110, 40));

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

        simulatorButton.setText("Simulador");
        simulatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulatorButtonActionPerformed(evt);
            }
        });
        jPanel2.add(simulatorButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 110, 40));

        stadisticsButton.setText("Estadistica");
        stadisticsButton.setMaximumSize(new java.awt.Dimension(72, 23));
        stadisticsButton.setMinimumSize(new java.awt.Dimension(72, 23));
        stadisticsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stadisticsButtonActionPerformed(evt);
            }
        });
        jPanel2.add(stadisticsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 110, 40));

        saveButton.setText("Guardar");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel2.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 110, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/background2.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 640));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, -1));

        backgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/homepage-shorter.jpg"))); // NOI18N
        jPanel1.add(backgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 940, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void CreateProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateProcessActionPerformed
        try {

            ProcessMaker ProcessMakerWindow = new ProcessMaker();
            this.setVisible(false);
            ProcessMakerWindow.setVisible(true);

        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CreateProcessActionPerformed

    private void simulatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulatorButtonActionPerformed
        String CycleDuration = String.valueOf(app.duracionCicloInstruccion.get());
        String currentAlgorithm = app.getPlanificador().getNombreAlgoritmo();
        int cantidadProcesadores = app.getCPUsActivos();

        Simulator simulatorWindow = new Simulator(CycleDuration, cantidadProcesadores, currentAlgorithm);
        this.setVisible(false);

        simulatorWindow.setVisible(true);
    }//GEN-LAST:event_simulatorButtonActionPerformed

    private void stadisticsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stadisticsButtonActionPerformed
        this.setVisible(false);
        Estadisticas estadistica = new Estadisticas();
        estadistica.setVisible(true);
    }//GEN-LAST:event_stadisticsButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        app.getGuardadoGson().GuardadoGson();
    }//GEN-LAST:event_saveButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateProcess;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel backgroundImage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton simulatorButton;
    private javax.swing.JButton stadisticsButton;
    // End of variables declaration//GEN-END:variables
}
