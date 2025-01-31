/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author Angelo
 */
public class ProcesoIOBOUND extends Proceso {
    
    private int cicloGenerarExcepcion;
    private int cicloSatisfacerExcepcion;

    public ProcesoIOBOUND(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso, int cicloGenerarExcepcion, int cicloSatisfacerExcepcion) {
        super(nombre_proceso, cant_instrucciones, "CPU BOUND", PCB_proceso);
        
        this.cicloGenerarExcepcion = cicloGenerarExcepcion;
        this.cicloSatisfacerExcepcion = cicloSatisfacerExcepcion;
    }
    
    
}
