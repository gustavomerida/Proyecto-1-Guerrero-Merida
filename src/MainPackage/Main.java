/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainPackage;

import MainClasses.Planificador;
import AuxClass.*;
import MainClasses.*;

/**
 *
 * @author Angelo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // CREACION DE COLAS
        Cola<Proceso> colaListos = new Cola<>();
        Cola<Proceso> colaBloqueados = new Cola<>();
        Cola<Proceso> colaTerminados = new Cola<>();

        // Crear procesos de ejemplo
        Proceso p1 = new Proceso("Proceso 1", 10, "CPU BOUND", new PCB());
        Proceso p2 = new Proceso("Proceso 2", 5, "I/O BOUND", new PCB());
        Proceso p3 = new Proceso("Proceso 3", 8, "I/O BOUND", new PCB());
        Proceso p4 = new Proceso("Proceso 4", 2, "I/O BOUND", new PCB());
        
        // CREACION DE UN CPU (EJEMPLO)
        CPU cpu1 = new CPU(0, p1, "ACTIVO");
        
        // MUESTRA DEL PROCESO EN CPU
        System.out.println(cpu1.getActualProceso().getNombreProceso());
        
        // P1 SE ENCUENTRA EN EL CPU. NO SE CONSIDERA EN LA COLA DE LISTOS
        colaListos.encolar(p2);
        colaListos.encolar(p3);
        colaListos.encolar(p4);

        System.out.println(colaListos.travel());
        
        
        
        // Crear planificador y despachar procesos
        Planificador planificador = new Planificador("SRT", colaListos, colaBloqueados, colaTerminados, cpu1);
        planificador.escogerProceso();
        
        System.out.println(cpu1.getActualProceso().getNombreProceso());
    }
    
}
