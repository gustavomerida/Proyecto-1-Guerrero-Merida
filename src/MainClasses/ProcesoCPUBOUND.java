/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author Angelo
 */
public class ProcesoCPUBOUND extends Proceso {
    private int CicloGenerarExcepcion;
    private int CicloSatisfacerExcepcion;

    public ProcesoCPUBOUND(String nombre_proceso, int cant_instrucciones, int tipo, PCB PCB_proceso) {
        super(nombre_proceso, cant_instrucciones, "CPU BOUND", PCB_proceso);
    }
    
}
