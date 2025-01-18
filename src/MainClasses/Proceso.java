/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author Angelo
 */
public class Proceso {
    private String nombre_proceso;
    private int cant_instrucciones;
    private String tipo;
    private PCB PCB_proceso;
    
    public Proceso(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso){
        this.nombre_proceso = nombre_proceso;
        this.cant_instrucciones = cant_instrucciones;
        this.PCB_proceso = PCB_proceso;
        this.tipo = tipo;
    }
    
}
