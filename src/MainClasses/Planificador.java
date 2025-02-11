/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import AuxClass.Cola;
import AuxClass.Nodo;
import MainClasses.CPU;
import MainClasses.Proceso;
import java.util.concurrent.Semaphore;

public class Planificador {

    private String nombreAlgoritmo;
    public Cola<Proceso> ColaListos;
    private Cola<Proceso> ColaBloqueados;
    private Cola<Proceso> ColaTerminados;

    //private CPU cpuDefault;
    private Proceso procesoEntrante;

    private final int quantum = 5; //Quantum de tiempo de RR en ciclos
    private Semaphore semaphore; // Semáforo para garantizar exclusión mutua

    public Planificador(String nombreAlgoritmo, Cola<Proceso> ColaListos, Cola<Proceso> ColaBloqueados, Cola<Proceso> ColaTerminados, CPU cpuDefault) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.ColaListos = ColaListos;
        this.ColaBloqueados = ColaBloqueados;
        this.ColaTerminados = ColaTerminados;

        //this.cpuDefault = cpuDefault;
        this.semaphore = new Semaphore(1); // Inicializar el semáforo con un permiso disponible
    }

    public Proceso escogerProceso() {
        System.out.println(ColaListos.travel());
        Proceso proceso = null;
        System.out.println("EScogiendo");
        try {
            semaphore.acquire(); // Adquirir el permiso del semáforo (wait)
            switch (nombreAlgoritmo) {
                case "FCFS":
                    System.out.println("jejejeje");
                    proceso = fcfs();
                    break;
                case "RR":
                    proceso = fcfs();
                    break;
                case "SPN":
                    proceso = spn();
                    break;
                case "SRT":
                    //srt(this.cpuDefault);
                    break;
                case "HRRN":
                    System.out.println("Ejecutando HRRN");
                    proceso = hrrn();
                // Agregar otro caso para el algoritmo que falta
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Liberar el permiso del semáforo (signal)
        }
        return proceso;
    }

    public void despacharProceso(Proceso proceso) {
        // Lógica para ejecutar el proceso
        ejecutarProcesos(proceso);
    }

    private Proceso hrrn() {
        System.out.println("Ejecutando política HRRN");

        if (ColaListos.isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }

        // Calcular la tasa de respuesta para cada proceso en la cola
        Nodo<Proceso> actual = ColaListos.getHead();

        while (actual != null) {
            calculoRadioRespuesta(actual.gettInfo());
            actual = actual.getpNext();
        }

        ordenarColaPorRadioRespuesta(ColaListos);
        
        Proceso proceso = ColaListos.getHead().gettInfo();
        ColaListos.desencolar(); 
        
        return proceso; 
    }

    private void calculoRadioRespuesta(Proceso proceso) {
        int tasaRespuesta = (proceso.getTiempoEnCola() + proceso.getTiempoRestante()) / proceso.getTiempoRestante();
        proceso.setTasaRespuesta(tasaRespuesta);
    }

    public void ordenarColaPorRadioRespuesta(Cola<Proceso> cola) {
        if (cola.isEmpty() || cola.getHead().getpNext() == null) {
            return; // La cola está vacía o tiene un solo elemento
        }

            boolean intercambiado;
        do {
            Nodo<Proceso> actual = cola.getHead();
            Nodo<Proceso> siguiente = actual.getpNext();
            intercambiado = false;

            while (siguiente != null) {
                // Comparar la tasa de respuesta entre los nodos
                if (actual.gettInfo().getTasaRespuesta() < siguiente.gettInfo().getTasaRespuesta()) {
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

    private Proceso fcfs() {
        System.out.println("Escogiendo en fcfs");

        // Verifica si la cola de procesos está vacía
        if (ColaListos.isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }

        Proceso proceso = ColaListos.getHead().gettInfo(); // Obtener el primer proceso
        ColaListos.desencolar(); // Eliminar de la cola
        System.out.println(proceso + " escogidooo");
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
        ordenarColaPorNumeroInstrucciones(ColaListos); //Quizá haya que cambiar por el método que ordena por tiempo restante
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

    // Nuevo método para ordenar por tiempo restante
    public void ordenarColaPorTiempoRestante(Cola<Proceso> cola) {
        if (cola.isEmpty() || cola.getHead().getpNext() == null) {
            return; // La cola está vacía o tiene un solo elemento
        }

        boolean intercambiado;
        do {
            Nodo<Proceso> actual = cola.getHead();
            Nodo<Proceso> siguiente = actual.getpNext();
            intercambiado = false;

            while (siguiente != null) {
                // Comparar el tiempo restante entre los nodos
                if (actual.gettInfo().getTiempoRestante() > siguiente.gettInfo().getTiempoRestante()) {
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

    public void expulsarProceso(Proceso proceso) {
        try {
            semaphore.acquire(); // Adquirir el permiso del semáforo (wait)
            proceso.getPCB_proceso().setEstado("Ready"); // Cambiar el estado a Ready
            int tiempoRestante = proceso.getTiempoRestante();
            //Quería usar el metodo copiar pero no me deja
            ProcesoCPUBOUND proceso2 = new ProcesoCPUBOUND(proceso.getNombreProceso(), proceso.getCant_instrucciones(), "CPU BOUND", proceso.getPCB_proceso(), proceso.getCiclosDuracion());
            System.out.println("Al hilo le fantan " + tiempoRestante + " instrucciones");
            System.out.println(proceso2.getTiempoRestante());
            proceso2.setTiempoRestante(tiempoRestante);
            proceso2.getPCB_proceso().setEstado("Ready");

            ColaListos.encolar(proceso2); // Reencolar el proceso

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Liberar el permiso del semáforo (signal)
        }
    }

    public void ejecutarProcesos(Proceso proceso) {
        if (proceso.getTipo() == "CPU BOUND") {
            if (nombreAlgoritmo == "FCFS") {
                this.escogerProceso();
            }
            //this.cpuDefault.setActualProceso(proceso);
            proceso.start();
        } else if (proceso.getTipo() == "I/O BOUND") {

            proceso.start();
            // Agregar el proceso a la cola de bloqueados
            ColaBloqueados.encolar(proceso);
            // Eliminar el proceso de la cola de listos
            //ColaListos.eliminar(proceso);
        }
        // Actualizar las colas según el estado del proceso
        actualizarColas(proceso);
    }

    private void actualizarColas(Proceso proceso) {
//        if (proceso.estaTerminado()) {
//            // Eliminar el proceso de la cola de listos o bloqueados
//            if (ColaListos.contiene(proceso)) {
//                ColaListos.eliminar(proceso);
//            } else if (ColaBloqueados.contiene(proceso)) {
//                ColaBloqueados.eliminar(proceso);
//            }
//            // Agregar el proceso a la cola de terminados
//            ColaTerminados.encolar(proceso);
//        } else if (proceso.estaBloquedo()) {
//            // Eliminar el proceso de la cola de listos
//            ColaListos.eliminar(proceso);
//        } else {
//            // Agregar el proceso a la cola de listos
//            ColaListos.encolar(proceso);
//        }
    }

    public void satisfacerExcepcion(Proceso proceso) {
        // Reactivar el proceso bloqueado
        //ColaBloqueados.eliminar(proceso);
        ColaListos.encolar(proceso);
    }

    public String getNombreAlgoritmo() {
        return nombreAlgoritmo;
    }

    public void setNombreAlgoritmo(String nombreAlgoritmo) {
        this.nombreAlgoritmo = nombreAlgoritmo;
    }

    private void ejecutarFCFS(Proceso proceso) {
        // Lógica para ejecutar el proceso en FCFS
        //cpuDefault.setActualProceso(proceso);
        proceso.start();
        // Actualizar las colas según el estado del proceso
        actualizarColas(proceso);
    }

    /**
     * @return the quantum
     */
    public int getQuantum() {
        return quantum;
    }
}
