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
    
    public ProcesoIOBOUND(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso) {
        super(nombre_proceso, cant_instrucciones, "I/O BOUND", PCB_proceso);
    }
    
    
    
}
