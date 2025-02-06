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
    private Proceso actualProceso;
    private String estado; // Ocupado o libre

    public CPU(int id, Proceso actualProceso, String estado) {
        this.id = id;
        this.actualProceso = actualProceso;
        this.estado = estado;
        this.actualProceso.start();
    }

    public Proceso getActualProceso() {
        return actualProceso;
    }

    public void setActualProceso(Proceso actualProceso) {
        this.actualProceso = actualProceso;
    }
    
              
}
