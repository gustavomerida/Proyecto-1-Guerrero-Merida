/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.Classes;

import MainClasses.PCB;
import MainClasses.Proceso;
import MainClasses.ProcesoCPUBOUND;
import MainClasses.ProcesoIOBOUND;
import MainClasses.RegistrosControlEstado;
import MainPackage.App;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * cyc
 *
 * @author Angelo
 */
public class ProcessMaker extends javax.swing.JFrame {

    private Font ebGaramondFont;
    private final App app = App.getInstance();

    public ProcessMaker() throws FontFormatException, IOException {

        initComponents();
        initializationWindow();
    }

    private void initializationWindow() throws FontFormatException, IOException {
        this.setResizable(false);
        this.setSize(1100, 605);
        this.setLocationRelativeTo(null);

        // Label extras - Caso I/O BOUND
        label1IOEXTRA.setVisible(false);
        label2IOEXTRA.setVisible(false);

        // JTextField extras - Caso I/O BOUND
        cycleDurationESTextField.setVisible(false);
        cycleDurationExceptionTextField.setVisible(false);

        ebGaramondFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/GUI/Assets/Font/EBGaramond-Bold.ttf")).deriveFont(Font.PLAIN, 16);

        JLabel[] labelArrayParameters = {jLabel7, jLabel4, jLabel5, label1IOEXTRA, label2IOEXTRA, activeProcessorsLabel, cycleDurationLabel};

        for (JLabel currentLabel : labelArrayParameters) {
            currentLabel.setFont(ebGaramondFont);
        }
    }

    /**
     * Creates new form ProcessCreator
     */
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
        Salir = new javax.swing.JButton();
        CreateProcess = new javax.swing.JButton();
        simulatorButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        label2IOEXTRA = new javax.swing.JLabel();
        cycleDurationExceptionTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        processTypeComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        instructionsQuantityTextField = new javax.swing.JTextField();
        label1IOEXTRA = new javax.swing.JLabel();
        cycleDurationESTextField = new javax.swing.JTextField();
        attributesSimulatorTitle = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        processNameTextField = new javax.swing.JTextField();
        activeProcessorsTextField = new javax.swing.JTextField();
        activeProcessorsLabel = new javax.swing.JLabel();
        cycleDurationLabel = new javax.swing.JLabel();
        cycleDurationPerInstructionTextField = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1110, 660));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 570));

        label2IOEXTRA.setForeground(new java.awt.Color(21, 97, 240));
        label2IOEXTRA.setText("Número de ciclos  para completar la solicitud:");
        jPanel1.add(label2IOEXTRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 330, 40));

        cycleDurationExceptionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cycleDurationExceptionTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(cycleDurationExceptionTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, 240, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cantidad de Instrucciones:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 180, 40));

        processTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU BOUND", "I/O BOUND" }));
        processTypeComboBox.setToolTipText("");
        processTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processTypeComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(processTypeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tipo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 70, 40));

        instructionsQuantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructionsQuantityTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(instructionsQuantityTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 240, -1));

        label1IOEXTRA.setForeground(new java.awt.Color(21, 97, 240));
        label1IOEXTRA.setText("Número de ciclos para solicitud de E/S:");
        jPanel1.add(label1IOEXTRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 320, 40));

        cycleDurationESTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cycleDurationESTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(cycleDurationESTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 240, -1));

        attributesSimulatorTitle.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        attributesSimulatorTitle.setForeground(new java.awt.Color(255, 255, 255));
        attributesSimulatorTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        attributesSimulatorTitle.setText("Atributos del Simulador");
        jPanel1.add(attributesSimulatorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 440, 40));

        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Creación de Procesos");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 440, 40));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre del Proceso:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 170, 40));

        processNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processNameTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(processNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 240, -1));

        activeProcessorsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeProcessorsTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(activeProcessorsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, 240, -1));

        activeProcessorsLabel.setForeground(new java.awt.Color(255, 255, 255));
        activeProcessorsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activeProcessorsLabel.setText("Número de procesadores activos:");
        jPanel1.add(activeProcessorsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 220, 50));

        cycleDurationLabel.setForeground(new java.awt.Color(255, 255, 255));
        cycleDurationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cycleDurationLabel.setText("Duración del ciclo de ejecución de una instrucción:");
        jPanel1.add(cycleDurationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 270, 50));

        cycleDurationPerInstructionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cycleDurationPerInstructionTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(cycleDurationPerInstructionTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, 240, -1));

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Assets/solid-background-color.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 830, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1109, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void CreateProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateProcessActionPerformed

    }//GEN-LAST:event_CreateProcessActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cycleDurationExceptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cycleDurationExceptionTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cycleDurationExceptionTextFieldActionPerformed

    private void activeProcessorsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeProcessorsTextFieldActionPerformed

    }//GEN-LAST:event_activeProcessorsTextFieldActionPerformed

    private void processTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processTypeComboBoxActionPerformed

        if ("I/O BOUND".equals(processTypeComboBox.getSelectedItem().toString())) {

            label1IOEXTRA.setVisible(true);
            label2IOEXTRA.setVisible(true);
            cycleDurationESTextField.setVisible(true);
            cycleDurationExceptionTextField.setVisible(true);

        } else {
            label1IOEXTRA.setVisible(false);
            label2IOEXTRA.setVisible(false);
            cycleDurationESTextField.setVisible(false);
            cycleDurationExceptionTextField.setVisible(false);

        }
    }//GEN-LAST:event_processTypeComboBoxActionPerformed

    private boolean checkPositiveInteger(JTextField jTextField) {
        String valueAsString = jTextField.getText().trim();

        if (valueAsString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int value = Integer.parseInt(valueAsString);
            if (value > 0) {
                return true;

            } else {

                JOptionPane.showMessageDialog(null, "El valor ingresado debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado no es un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private int setSimulatorProcessors() {

        if (checkPositiveInteger(activeProcessorsTextField)) {
            int activeProcessors = Integer.parseInt(activeProcessorsTextField.getText());

            if (activeProcessors == 2 || activeProcessors == 3) {
                return activeProcessors; // Retorna el valor válido
            } else {
                JOptionPane.showMessageDialog(null, "El número de procesadores debe ser 2 o 3", "Error",JOptionPane.ERROR_MESSAGE);
            }
        }

        return 0;
    }


    private void instructionsQuantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructionsQuantityTextFieldActionPerformed

    }//GEN-LAST:event_instructionsQuantityTextFieldActionPerformed

    private void cycleDurationESTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cycleDurationESTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cycleDurationESTextFieldActionPerformed

    private void processNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_processNameTextFieldActionPerformed

    private void cycleDurationPerInstructionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cycleDurationPerInstructionTextFieldActionPerformed

    }//GEN-LAST:event_cycleDurationPerInstructionTextFieldActionPerformed

    private void simulatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulatorButtonActionPerformed
        /*
        NECESITO RECUPERAR EL ESTADO ANTERIOR PARA SETEAR LOS PARAMETROS DEL 
        SIMULADOR. CON LA IMPLEMENTACION DEL ARCHIVO GUARDADO ESTO CAMBIARA.
         */

        Simulator simulator = saveSimulatorParameters();
        this.setVisible(false);
        simulator.setVisible(true);
    }//GEN-LAST:event_simulatorButtonActionPerformed

    private Proceso createNewProcess() {

        // ATRIBUTOS GENERALES
        int instructionsQuantity = 0;
        int cycleDurationInstruction = 0;

        // ATRIBUTOS IO
        int cycleDurationIO = 0;
        int cycleDurationExceptIO = 0;

        String processName = this.processNameTextField.getText();
        String processType = this.processTypeComboBox.getModel().getSelectedItem().toString();

        ///////////////////////////////////////////////////////////////////////////////////////
        RegistrosControlEstado executionEnvironment = new RegistrosControlEstado(0, 1, 0);

        PCB PCBProcess = new PCB(1, processName, "READY", executionEnvironment);

        ///////////////////////////////////////////////////////////////////////////////////////
        if (checkPositiveInteger(this.instructionsQuantityTextField) && checkPositiveInteger(this.cycleDurationPerInstructionTextField)) {

            instructionsQuantity = Integer.parseInt(this.instructionsQuantityTextField.getText());
            cycleDurationInstruction = Integer.parseInt(this.cycleDurationPerInstructionTextField.getText());

        }

        if ("CPU BOUND".equals(processType)) {

            ProcesoCPUBOUND newCPUBoundProcess = new ProcesoCPUBOUND(processName, instructionsQuantity, processType, PCBProcess, cycleDurationInstruction);
            return newCPUBoundProcess;

        } else {

            if (checkPositiveInteger(this.cycleDurationESTextField) && checkPositiveInteger(this.cycleDurationExceptionTextField)) {

                cycleDurationIO = Integer.parseInt(this.cycleDurationESTextField.getText());
                cycleDurationExceptIO = Integer.parseInt(this.cycleDurationExceptionTextField.getText());

                ProcesoIOBOUND newIOBoundProcess = new ProcesoIOBOUND(processName, instructionsQuantity, processType, PCBProcess, cycleDurationInstruction, cycleDurationIO, cycleDurationExceptIO);
                return newIOBoundProcess;
            }

            return null;
        }
    }

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed

        Home home = new Home();
        this.setVisible(false);
        home.setVisible(true);

    }//GEN-LAST:event_homeButtonActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        
        
        Proceso newProcess = createNewProcess();
        setSimulatorProcessors();

        app.getPlanificador().ColaListos.encolar(newProcess);

        cleanTextField();

    }//GEN-LAST:event_guardarActionPerformed

    private Simulator saveSimulatorParameters() {
        // PREGUNTAR A GUSTAVINHO
        String cycleDurationParameter = String.valueOf(cycleDurationPerInstructionTextField.getText());
        int processorsQuantity = setSimulatorProcessors();

        Simulator simulatorWindow = new Simulator(cycleDurationParameter, processorsQuantity);

        return simulatorWindow;
    }

    private void cleanTextField() {

        JTextField[] textFieldArray = {processNameTextField, instructionsQuantityTextField, cycleDurationESTextField, cycleDurationExceptionTextField, activeProcessorsTextField, cycleDurationPerInstructionTextField};

        for (JTextField currentTextField : textFieldArray) {
            currentTextField.setText("");
        }

    }

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
            java.util.logging.Logger.getLogger(ProcessMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcessMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcessMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcessMaker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ProcessMaker().setVisible(true);
                } catch (FontFormatException ex) {
                    Logger.getLogger(ProcessMaker.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ProcessMaker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateProcess;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel activeProcessorsLabel;
    private javax.swing.JTextField activeProcessorsTextField;
    private javax.swing.JLabel attributesSimulatorTitle;
    private javax.swing.JTextField cycleDurationESTextField;
    private javax.swing.JTextField cycleDurationExceptionTextField;
    private javax.swing.JLabel cycleDurationLabel;
    private javax.swing.JTextField cycleDurationPerInstructionTextField;
    private javax.swing.JButton guardar;
    private javax.swing.JButton homeButton;
    private javax.swing.JTextField instructionsQuantityTextField;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label1IOEXTRA;
    private javax.swing.JLabel label2IOEXTRA;
    private javax.swing.JTextField processNameTextField;
    private javax.swing.JComboBox<String> processTypeComboBox;
    private javax.swing.JButton simulatorButton;
    // End of variables declaration//GEN-END:variables
}
