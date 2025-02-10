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
        while (true) {
            if ("Running".equals(this.getPCB_proceso().getEstado())){
                try {
                    // Simular duración del ciclo
                    this.sleep(this.ciclosDuracion.get());
                    
                    System.out.println("Proceso " + this.getNombreProceso() + " ejecutándose");
                    System.out.println("Cant_instrucciones: " + this.getCant_instrucciones());
                    // Actualizar MAR y PC
                    int MAR_num = this.getCant_instrucciones() - this.getTiempoRestante();
                    this.getPCB_proceso().getAmbienteEjecucion().setMAR(MAR_num);
                    this.getPCB_proceso().getAmbienteEjecucion().setPc(MAR_num + 1);
                    // Mostrar MAR y PC
                    System.out.println("MAR: " + this.getPCB_proceso().getAmbienteEjecucion().getMAR());
                    System.out.println("PC: " + this.getPCB_proceso().getAmbienteEjecucion().getPc());
                    System.out.println("Estado: " + this.getPCB_proceso().getEstado());
                    System.out.println("");
                    this.reducirTiempo(1);
                    
                    if (this.getTiempoRestante() == 0) {
                        this.getPCB_proceso().setEstado("Exit");
                        //llamar al planificador o importar App
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcesoCPUBOUND.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if ("Blocked".equals(this.getPCB_proceso().getEstado()) || "Ready".equals(this.getPCB_proceso().getEstado())){
                //Nada
                System.out.println("Proceso bloqueado o listo");
            }else {
                System.out.println("Proceso terminado");
                break;
            }   
        }    
    }

    public ProcesoCPUBOUND copiar() {
        return new ProcesoCPUBOUND(
            this.getNombreProceso(),
            this.getCant_instrucciones(),
            "CPU BOUND", // Tipo fijo
            this.getPCB_proceso(), // Asumiendo que PCB también tiene un método de copia
            this.ciclosDuracion // Copia profunda de ciclosDuracion
        );
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
