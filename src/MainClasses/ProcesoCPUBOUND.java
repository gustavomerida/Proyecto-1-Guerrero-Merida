/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class ProcesoCPUBOUND extends Proceso {

    private AtomicInteger ciclosDuracion;

//    public ProcesoCPUBOUND(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso, int ciclosDuracion) {
//        super(nombre_proceso, cant_instrucciones, "CPU BOUND", PCB_proceso);
//        this.ciclosDuracion = ciclosDuracion;
//    }

    public ProcesoCPUBOUND(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso, AtomicInteger ciclosDuracion) {
        super(nombre_proceso, cant_instrucciones, "CPU BOUND", PCB_proceso);
        this.ciclosDuracion = ciclosDuracion;
    }

    @Override
    public void run() {

        while (this.getTiempoRestante() > 0) {
            try {
                // Simular la duración del ciclo de instrucción
                Thread.sleep(ciclosDuracion.get());

                System.out.println("soy proceso");

                // Reducir el tiempo restante del proceso
                this.reducirTiempo(1);

                // Actualizar MAR y PC
                int MAR_num = this.getCant_instrucciones() - this.getTiempoRestante();
                this.getPCB_proceso().getAmbienteEjecucion().setMAR(MAR_num);
                this.getPCB_proceso().getAmbienteEjecucion().setPc(MAR_num + 1);

                // Mostrar información del proceso
                System.out.println("Proceso " + this.getNombreProceso() + " ejecutándose");
                System.out.println("Tiempo Restante: " + this.getTiempoRestante());
                System.out.println("MAR: " + this.getPCB_proceso().getAmbienteEjecucion().getMAR());
                System.out.println("PC: " + this.getPCB_proceso().getAmbienteEjecucion().getPc());

            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoCPUBOUND.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Marcar el proceso como finalizado
        this.getPCB_proceso().setEstado("Exit");
        System.out.println("Proceso " + this.getNombreProceso() + " ha terminado.");
    }

    /**
     * @return the ciclosDuracion
     */
    public AtomicInteger getCiclosDuracion() {
        return ciclosDuracion;
    }

    /**
     * @param ciclosDuracion the ciclosDuracion to set
     */
    @Override
    public void setCiclosDuracion(AtomicInteger ciclosDuracion) {
        this.ciclosDuracion = ciclosDuracion;
    }
}
