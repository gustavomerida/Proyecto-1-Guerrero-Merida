/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Classes.Simulator;
import javax.swing.SwingUtilities;

/**
 *
 * @author Angelo
 */
public class CPU extends Thread {

    private int id;
    private Proceso actualProceso;
    private String estado; // Ocupado o libre
    private Planificador planificador;

    public CPU(int id, Proceso actualProceso, String estado) {
        this.id = id;
        this.actualProceso = actualProceso;
        this.estado = estado;

    }

    public Proceso getActualProceso() {
        return actualProceso;
    }

    public void setActualProceso(Proceso actualProceso) {

        this.actualProceso = actualProceso;
        actualProceso.getPCB_proceso().setEstado("Running");
    }

    @Override
    public void run() {
        while (true) {

            Proceso p = this.getPlanificador().escogerProceso();

            if (p != null) {
                this.setEstado("Activo");
                // Entre cambios de proceso se debe llamar al so
                // ejecutarProcesoSO();
                switch (this.getPlanificador().getNombreAlgoritmo()) {
                    case "FCFS":
                        this.setActualProceso(p);
                        System.out.println("Ejecutando proceso: " + p.getNombreProceso());

                        p.start();

                        //////////////////////////////////////////////////////
                        System.out.println("soy cpu");
                        System.out.println(p.getTiempoRestante());
                        //////////////////////////////////////////////////////

                        // Esperar hasta que el proceso termine
                        while (p.getTiempoRestante() > 0) {
                            try {
                                Thread.sleep(p.getCiclosDuracion().get()); // Simular ciclo de CPU
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        // Proceso finalizado
                        p.getPCB_proceso().setEstado("Exit");
                        System.out.println("CPU " + this.id + " terminó proceso: " + p.getNombreProceso());
                    
                        break;

                    case "RR":

                        break;
                    case "SPN":

                        break;
                    case "SRT":
                        //srt(this.cpuDefault);
                        break;
                    // Agregar otro caso para el algoritmo que falta
                }
                
                
                

            } else {
                this.setEstado("Inactivo");
                System.out.println("CPU " + this.id + " está inactivo. Ejecutando proceso de SO...");

                try {
                    Thread.sleep(1000); // Esperar un tiempo antes de revisar otra vez
                } catch (InterruptedException ex) {
                    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /*
    private void ejecutarProcesoSO() {
        System.out.println("CPU " + this.id + " ejecutando proceso del Sistema Operativo...");

        // Crear un proceso de SO temporal
        RegistrosControlEstado environment = new RegistrosControlEstado(0, 1, 0);
        PCB pcb = new PCB(0, "Proceso de SO", "Running", environment);
        Proceso procesoSO = new ProcesoCPUBOUND("Proceso de SO", 3, "CPU BOUND", pcb, 500); 

        procesoSO.start();

        // Simular ejecución del proceso de SO
        while (procesoSO.getTiempoRestante() > 0) {
            try {
                Thread.sleep(procesoSO.getCiclosDuracion());
                procesoSO.reducirTiempo(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Proceso de SO finalizado en CPU " + this.id);
    }
    */

    /**
     * @return the id
     */
    public int getIdp() { //Tuve que cambiarle el nombre
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the planificador
     */
    public Planificador getPlanificador() {
        return planificador;
    }

    /**
     * @param planificador the planificador to set
     */
    public void setPlanificador(Planificador planificador) {
        this.planificador = planificador;
    }
}
