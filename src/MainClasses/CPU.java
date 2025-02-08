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
                this.setActualProceso(p);
                System.out.println("Ejecutando proceso: " + p.getNombreProceso());

                // Iniciar ejecución en otro hilo
                Thread procesoThread = new Thread(p);
                procesoThread.start();
                 
                //////////////////////////////////////////////////////
                System.out.println("soy cpu");
                System.out.println(p.getTiempoRestante());
                //////////////////////////////////////////////////////

                // Esperar hasta que el proceso termine
                while (p.getTiempoRestante() > 0) {
                    try {
                        Thread.sleep(p.getCiclosDuracion()); // Simular ciclo de CPU
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Proceso finalizado
                p.getPCB_proceso().setEstado("Exit");
                System.out.println("CPU " + this.id + " terminó proceso: " + p.getNombreProceso());

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
