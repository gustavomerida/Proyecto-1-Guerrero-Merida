/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import AuxClass.Cola;
import AuxClass.Nodo;

/**
 *
 * @author Angelo
 */
public class Planificador {
    private String nombreAlgoritmo;
    private Cola <Proceso> ColaListos;
    private Cola <Proceso> ColaBloqueados;
    private Cola <Proceso> ColaTerminados;
    private final int quantum = 5; //Quantum de tiempo de RR en ciclos

    public Planificador(String nombreAlgoritmo, Cola<Proceso> ColaListos, Cola<Proceso> ColaBloqueados, Cola<Proceso> ColaTerminados) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.ColaListos = ColaListos;
        this.ColaBloqueados = ColaBloqueados;
        this.ColaTerminados = ColaTerminados;
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
                proceso = srt();
            // Agregar otro caso para el algoritmo que falta
        }
        if (proceso != null) {
            despacharProceso(proceso);
        }
    }
    
    public void despacharProceso(Proceso proceso){
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

        // Ordenar la cola por número de instrucciones antes de buscar el proceso más corto
        ordenarColaPorNumeroInstrucciones(ColaListos);

        // Obtener el proceso con el menor número de instrucciones
        Proceso procesoMasCorto = ColaListos.getHead().gettInfo();
        ColaListos.desencolar(); // Eliminar de la cola

        return procesoMasCorto; // Retornar el proceso que se ha ejecutado
    }
    
    private Proceso srt(){
         // Implementar lógica de SRT
        return null;
    }

    // Agregar más métodos para el algoritmo que falta
    
    //...
    
    
    //Ordenamiento de colas (Ordenamiento burbuja)
    public void ordenarColaPorNumeroInstrucciones(Cola<Proceso> cola) {
        if (cola.isEmpty() || cola.getHead().getpNext() == null) {
            return; // La cola está vacía o tiene un solo elemento
        }

        boolean intercambiado;
        do {
            Nodo<Proceso> actual = cola.getHead();
            Nodo<Proceso> anterior = null;
            intercambiado = false;

            while (actual.getpNext() != null) {
                Proceso procesoActual = actual.gettInfo();
                Proceso procesoSiguiente = actual.getpNext().gettInfo();

                // Comparar número de instrucciones
                if (procesoActual.getCant_instrucciones() > procesoSiguiente.getCant_instrucciones()) {
                    // Intercambiar nodos
                    if (anterior == null) {
                        // Intercambiando el head
                        Nodo<Proceso> temp = actual.getpNext();
                        actual.setpNext(temp.getpNext());
                        temp.setpNext(actual);
                        cola.setHead(temp); // Actualizar el head de la cola
                    } else {
                        anterior.setpNext(actual.getpNext());
                        actual.setpNext(actual.getpNext().getpNext());
                        anterior.getpNext().setpNext(actual);
                    }
                    intercambiado = true;
                } else {
                    anterior = actual;
                    actual = actual.getpNext();
                }
            }
        } while (intercambiado);
    }
}
