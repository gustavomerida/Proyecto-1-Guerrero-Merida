/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author Angelo
 */
public class SO {
    private PCB currentProcess;
    private PCB newProcess;
    public Planificador planificador;

    public SO(PCB CurrentProcess, PCB NewProcess, Planificador planificador) {
        this.currentProcess = CurrentProcess;
        this.newProcess = NewProcess;
        this.planificador = planificador;
    }
   
}
