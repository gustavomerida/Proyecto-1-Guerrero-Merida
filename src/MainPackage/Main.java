/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package MainPackage;

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
        // TODO code application logic here
        Cola<Proceso> colaListos = new Cola<>();
        Cola<Proceso> colaBloqueados = new Cola<>();
        Cola<Proceso> colaTerminados = new Cola<>();

        // Crear procesos de ejemplo
        Proceso p1 = new Proceso("Proceso 1", 10, "CPU BOUND", new PCB());
        Proceso p2 = new Proceso("Proceso 2", 5, "I/O BOUND", new PCB());
        Proceso p3 = new Proceso("Proceso 3", 8, "I/O BOUND", new PCB());
        Proceso p4 = new Proceso("Proceso 4", 2, "I/O BOUND", new PCB());
        System.out.println(p1);
        // Encolar procesos
        colaListos.encolar(p1);
        colaListos.encolar(p2);
        colaListos.encolar(p3);
        colaListos.encolar(p4);

        System.out.println(colaListos.travel());

        // Crear planificador y despachar procesos
        Planificador planificador = new Planificador("SPN", colaListos, colaBloqueados, colaTerminados);
        planificador.escogerProceso();
        System.out.println(colaListos.travel());
    }
    
}
