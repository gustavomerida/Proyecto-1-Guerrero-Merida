/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import AuxClass.List;

/**
 *
 * @author Angelo
 */
public class PCB {
    // registros_ambiente posible lista anidada
    
    /* 
        Es posible usar un id tipo hash?
    */
    private int id; 
    private String nombre_proceso;
    /* 
        Posibles estados: Running, Blocked, Ready
    */
    private String estado;
    private List <RegistrosControlEstado> ambienteEjecucion;
}
