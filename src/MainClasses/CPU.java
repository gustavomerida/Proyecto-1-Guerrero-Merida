/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author Angelo
 */
public class CPU {
    private int id;
    private Proceso ActualProceso;
    private String Estado; // Ocupado o libre

    public CPU(int id, Proceso ActualProceso, String Estado) {
        this.id = id;
        this.ActualProceso = ActualProceso;
        this.Estado = Estado;
    }        
            
}
