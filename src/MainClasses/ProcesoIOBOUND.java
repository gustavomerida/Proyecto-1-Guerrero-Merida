/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import MainPackage.App;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class ProcesoIOBOUND extends Proceso {
    
    private AtomicInteger ciclosDuracion;
    private int cicloGenerarExcepcion;
    private int cicloSatisfacerExcepcion;
    private int contadorCiclos;
    
    
    private int tiempoEnColaDeListos;
    
    private final App app = App.getInstance();

    public ProcesoIOBOUND(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso, AtomicInteger ciclosDuracion, int cicloGenerarExcepcion, int cicloSatisfacerExcepcion) {
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
        System.out.println("Proceso " + this.getNombreProceso() + " listo para ejecutarse nuevamente");
        this.getPCB_proceso().setEstado("Running"); //En realidad se va a ready y hay que tener un semáforo para la cola de listos
    }

    
    @Override
    public void run() {
        this.getPCB_proceso().setEstado("Running");
        while (true) {
            if (this.getTiempoRestante() == 0) {
                this.getPCB_proceso().setEstado("Exit");
            }
            if ("Running".equals(this.getPCB_proceso().getEstado())){
                System.out.println("Proceso " + this.getNombreProceso() + " ejecutándose");
                System.out.println("Cant_instrucciones: " + this.getCant_instrucciones());
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
                    //AQUÏ HAY QUE USAR UN SEMÁFORO PARA AGG A LA COLA DE BLOQUEADOS
                    this.generarExcepcion();
                } else {
                    try {
                        this.sleep(this.getCiclosDuracion().get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcesoIOBOUND.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else if ("Blocked".equals(this.getPCB_proceso().getEstado())){
                for (int i = 0; i < this.cicloSatisfacerExcepcion; i++) {
                        try {
                            this.sleep(this.getCiclosDuracion().get());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ProcesoIOBOUND.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                this.satisfacerExcepcion();
            } else if ("Ready".equals(this.getPCB_proceso().getEstado())){
                
                
                // aqui iria la suma del contador de la cola de listos.
                System.out.println("Proceso listo");
            }else {
                System.out.println("Proceso terminado");
            } 
            if (this.getTiempoRestante() == 0) {
                this.getPCB_proceso().setEstado("Exit");
                System.out.println("Proceso terminado");
                break;
            } 
        } 
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
    public void setCiclosDuracion(AtomicInteger ciclosDuracion) {
        this.ciclosDuracion = ciclosDuracion;
    }
}


