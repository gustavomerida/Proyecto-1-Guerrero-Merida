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
public class ProcesoIOBOUND extends Proceso {
    
    private int ciclosDuracion;
    private int cicloGenerarExcepcion;
    private int cicloSatisfacerExcepcion;
    private int contadorCiclos;

    public ProcesoIOBOUND(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso, int ciclosDuracion, int cicloGenerarExcepcion, int cicloSatisfacerExcepcion) {
        super(nombre_proceso, cant_instrucciones, "I/O BOUND", PCB_proceso);
        this.ciclosDuracion = ciclosDuracion;
        this.cicloGenerarExcepcion = cicloGenerarExcepcion;
        this.cicloSatisfacerExcepcion = cicloSatisfacerExcepcion;
        this.contadorCiclos = 0;
    }
    
    private void generarExcepcion() {
        this.getPCB_proceso().setEstado("Blocked");
        System.out.println("Proceso " + this.getNombreProceso() + " bloqueado por excepción I/O");
    }

    private void satisfacerExcepcion() {
        this.getPCB_proceso().setEstado("Ready");
        System.out.println("Proceso " + this.getNombreProceso() + " listo para ejecutarse nuevamente");
    }

    @Override
    public void run() {
        this.getPCB_proceso().setEstado("Running");
        while ("Running".equals(this.getPCB_proceso().getEstado())) {
            if (this.getTiempoRestante() == 0) {
                this.getPCB_proceso().setEstado("Exit");
            } else {
                System.out.println("Proceso " + this.getNombreProceso() + " ejecutándose");
                System.out.println("Cant_instrucciones: " + this.getCant_instrucciones());
                this.reducirTiempo(1);
                int MAR_num = this.getCant_instrucciones() - this.getTiempoRestante();
                this.getPCB_proceso().getAmbienteEjecucion().setMAR(MAR_num);
                this.getPCB_proceso().getAmbienteEjecucion().setPc(MAR_num + 1);
                System.out.println("MAR: " + this.getPCB_proceso().getAmbienteEjecucion().getMAR());
                System.out.println("PC: " + this.getPCB_proceso().getAmbienteEjecucion().getPc());
                System.out.println("Estado: " + this.getPCB_proceso().getEstado());
                System.out.println("");
                this.reducirTiempo(1);
                this.contadorCiclos++;
                if (this.contadorCiclos % this.cicloGenerarExcepcion == 0) {
                    this.generarExcepcion();
                    for (int i = 0; i < this.cicloSatisfacerExcepcion; i++) {
                        try {
                            this.sleep(this.ciclosDuracion);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ProcesoIOBOUND.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    this.satisfacerExcepcion();
                } else {
                    try {
                        this.sleep(this.ciclosDuracion);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcesoIOBOUND.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        System.out.println("Proceso terminado");
    }
}


