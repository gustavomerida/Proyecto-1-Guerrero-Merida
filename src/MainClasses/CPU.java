/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class CPU extends Thread{
    private int id;
    private Proceso actualProceso;
    private String estado; // Ocupado o libre
    private Planificador planificador;

    public CPU(int id, Proceso actualProceso, String estado) {
        this.id = id;
        this.actualProceso = actualProceso;
        this.estado = estado;
        //this.actualProceso.start();
    }

    public Proceso getActualProceso() {
        return actualProceso;
    }

    public void setActualProceso(Proceso actualProceso) {
        
        this.actualProceso = actualProceso;
        actualProceso.getPCB_proceso().setEstado("Running");
    }
    
    
    
    @Override
    public void run(){
        while (true){
            Proceso p = this.getPlanificador().escogerProceso();
            System.out.println(p);
            if (p!=null){
                switch (this.getPlanificador().getNombreAlgoritmo()) {
                    case "FCFS":
                        this.setActualProceso(p);
                        
                    {
                        try {
                            p.start();
                            this.sleep(p.getCant_instrucciones() * p.getCiclosDuracion());
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
                try { 
                    RegistrosControlEstado environment = new RegistrosControlEstado(0, 1, 0);
                    PCB pcb = new PCB(0, "Proceso de SO", "Running", environment);
                    p = new ProcesoCPUBOUND("Proceso de SO", 3, "CPU BOUND", pcb, 3000);
                    p.start();
                    System.out.println("Esperando por proceso de SO a terminar...");
                    this.sleep(p.getCant_instrucciones() * p.getCiclosDuracion()); //HAY que cambiar este tiempo. 
                } catch (InterruptedException ex) {
                    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        }  
    }             

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
