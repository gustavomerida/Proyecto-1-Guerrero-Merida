/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Classes.Simulator;
import javax.swing.SwingUtilities;
import MainPackage.App;

/**
 *
 * @author Angelo
 */
public class CPU extends Thread {

    private int id;
    private Proceso actualProceso;
    private String estado; // Ocupado o libre
    private Planificador planificador;
    private final ProcesoCPUBOUND procesoSO;

    public CPU(int id, Proceso actualProceso, String estado, ProcesoCPUBOUND procesoSO) {
        this.id = id;
        this.actualProceso = actualProceso;
        this.estado = estado;
        this.procesoSO = procesoSO;

    }

    public Proceso getActualProceso() {
        return actualProceso;
    }

    public void setActualProceso(Proceso actualProceso) {

        this.actualProceso = actualProceso;
        actualProceso.getPCB_proceso().setEstado("Running");
    }

    @Override
    public void run(){ //Aquí también hay que llevar el contadodor de ciclos global
        while (true){
            ProcesoCPUBOUND pr = procesoSO.copiar();
            this.setActualProceso(pr);
            try { 
                pr.start();
                System.out.println("Esperando por proceso de SO a terminar...");
                this.sleep(pr.getCant_instrucciones() * pr.getCiclosDuracion().get()); //HAY que cambiar este tiempo. 
            } catch (InterruptedException ex) {
                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
            }
            Proceso p = this.getPlanificador().escogerProceso();
            System.out.println(p);
            if (p!=null){
                this.setEstado("Activo");
                switch (this.getPlanificador().getNombreAlgoritmo()) {
                    case "FCFS":
                        this.setActualProceso(p);
                        
                    {
                        try {
                            p.start();
                            this.sleep(p.getCant_instrucciones() * p.getCiclosDuracion().get());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
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
                pr = procesoSO.copiar();
                this.setActualProceso(pr);
                try { 
                    pr.start();
                    System.out.println("Esperando por proceso de SO a terminar...");
                    this.sleep(pr.getCant_instrucciones() * pr.getCiclosDuracion().get()); //HAY que cambiar este tiempo. 
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
