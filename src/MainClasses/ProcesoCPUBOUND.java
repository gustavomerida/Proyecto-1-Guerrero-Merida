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
public class ProcesoCPUBOUND extends Proceso {
    private int ciclosDuracion;

    public ProcesoCPUBOUND(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso, int ciclosDuracion) {
        super(nombre_proceso, cant_instrucciones, "CPU BOUND", PCB_proceso);
        this.ciclosDuracion = ciclosDuracion;
    }

    @Override
    public void run() {
        this.getPCB_proceso().setEstado("Running");
        while ("Running".equals(this.getPCB_proceso().getEstado())) {
            try {
                // Simular duración del ciclo
                this.sleep(this.ciclosDuracion);
                if (this.getTiempoRestante() == 0) {
                    this.getPCB_proceso().setEstado("Exit");
                    //llamar al planificador o importar App
                }
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
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcesoCPUBOUND.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Proceso terminado");
    }
}

