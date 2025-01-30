/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import AuxClass.Cola;
import AuxClass.Nodo;
import MainClasses.CPU;
import MainClasses.Proceso;

/**
 *
 * @author Angelo
 */
public class Planificador {
    private String nombreAlgoritmo;
    private Cola<Proceso> ColaListos;
    private Cola<Proceso> ColaBloqueados;
    private Cola<Proceso> ColaTerminados;

    private CPU cpuDefault;
    private Proceso procesoEntrante;

    private final int quantum = 5; //Quantum de tiempo de RR en ciclos

    public Planificador(String nombreAlgoritmo, Cola<Proceso> ColaListos, Cola<Proceso> ColaBloqueados, Cola<Proceso> ColaTerminados, CPU cpuDefault) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.ColaListos = ColaListos;
        this.ColaBloqueados = ColaBloqueados;
        this.ColaTerminados = ColaTerminados;

        this.cpuDefault = cpuDefault;
    }

    public void escogerProceso() {
        Proceso proceso = null;
        switch (nombreAlgoritmo) {
            case "FCFS":
                proceso = fcfs();
                break;
            case "RR":
                proceso = roundRobin();
                break;
            case "SPN":
                proceso = spn();
                break;
            case "SRT":
                srt(this.cpuDefault);
                break;
            // Agregar otro caso para el algoritmo que falta
        }
        if (proceso != null) {
            despacharProceso(proceso);
        }
    }

    public void despacharProceso(Proceso proceso) {
        // Lógica para ejecutar el proceso
    }

    private Proceso fcfs() {
        if (ColaListos.isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }

        Proceso proceso = ColaListos.getHead().gettInfo(); // Obtener el primer proceso
        ColaListos.desencolar(); // Eliminar de la cola

        return proceso; // Retornar el proceso
    }

    /*La diferencia es que este se ejecuta 
    cada vez que termina el quantum de tiempo*/
    private Proceso roundRobin() {
        if (ColaListos.isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }

        Proceso proceso = ColaListos.getHead().gettInfo(); // Obtener el primer proceso 
        ColaListos.desencolar(); // Eliminar de la cola

        return proceso; // Retornar el proceso
    }

    private Proceso spn() {
        if (ColaListos.isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }
        System.out.println("Antes de ordenar:");
        System.out.println(ColaListos.travel());

        // Ordenar la cola por número de instrucciones antes de buscar el proceso más corto
        ordenarColaPorNumeroInstrucciones(ColaListos);
        System.out.println("Después de ordenar:");
        System.out.println(ColaListos.travel());
        // Obtener el proceso con el menor número de instrucciones
        Proceso procesoMasCorto = ColaListos.getHead().gettInfo();
        ColaListos.desencolar(); // Eliminar de la cola

        return procesoMasCorto; // Retornar el proceso que se ha ejecutado
    }

    private void srt(CPU cpuDefault) {
        if (ColaListos.isEmpty()) {
            return;
        }
        Proceso shorterProcess = spn();
        Proceso cpuCurrentProcess = cpuDefault.getActualProceso();
        
        if (shorterProcess.getCant_instrucciones() < cpuCurrentProcess.getCant_instrucciones()) {
            cpuDefault.setActualProceso(shorterProcess);
        }
    }

    public void ordenarColaPorNumeroInstrucciones(Cola<Proceso> cola) {
        if (cola.isEmpty() || cola.getHead().getpNext() == null) {
            return; // La cola está vacía o tiene un solo elemento
        }

        boolean intercambiado;
        do {
            Nodo<Proceso> actual = cola.getHead();
            Nodo<Proceso> siguiente = actual.getpNext();
            intercambiado = false;

            while (siguiente != null) {
                // Comparar el número de instrucciones entre los nodos
                if (actual.gettInfo().getCant_instrucciones() > siguiente.gettInfo().getCant_instrucciones()) {
                    // Intercambiar los datos de los nodos
                    Proceso temp = actual.gettInfo();
                    actual.settInfo(siguiente.gettInfo());
                    siguiente.settInfo(temp);
                    intercambiado = true;
                }
                // Avanzar a los siguientes nodos
                actual = siguiente;
                siguiente = siguiente.getpNext();
            }
        } while (intercambiado);
    }
}
