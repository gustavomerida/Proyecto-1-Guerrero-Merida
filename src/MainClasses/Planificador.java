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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Planificador {

    private String nombreAlgoritmo;
    private Cola<Proceso> ColaListos;
    private Cola<Proceso> ColaBloqueados;
    private Cola<Proceso> ColaTerminados;

    //private CPU cpuDefault;
    private Proceso procesoEntrante;

    private final int quantum = 5; //Quantum de tiempo de RR en ciclos
    private Semaphore semaphore; // Semáforo para garantizar exclusión mutua
    private Semaphore semaphore2; //Semáforo para exclusión mutua de la cola de bloqueados 
    private Semaphore semaphore3; //este es para la cola de terminados

    public Planificador(String nombreAlgoritmo, Cola<Proceso> ColaListos, Cola<Proceso> ColaBloqueados, Cola<Proceso> ColaTerminados, CPU cpuDefault) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.ColaListos = ColaListos;
        this.ColaBloqueados = ColaBloqueados;
        this.ColaTerminados = ColaTerminados;

        //this.cpuDefault = cpuDefault;
        this.semaphore = new Semaphore(1); // Inicializar el semáforo con un permiso disponible
        this.semaphore2 = new Semaphore(1); 
        this.semaphore3 = new Semaphore(1);
    }

    public Cola<Proceso> getColaBloqueados() {
        return ColaBloqueados;
    }

    
    
    public Proceso escogerProceso() {
        System.out.println(getColaListos().travel());
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
                    proceso = srt();
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

        if (getColaListos().isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }

        // Calcular la tasa de respuesta para cada proceso en la cola
        Nodo<Proceso> actual = getColaListos().getHead();

        while (actual != null) {
            calculoRadioRespuesta(actual.gettInfo());
            actual = actual.getpNext();
        }

        ordenarColaPorRadioRespuesta(getColaListos());
        
        Proceso proceso = getColaListos().getHead().gettInfo();
        getColaListos().desencolar(); 
        
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
        if (getColaListos().isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }

        Proceso proceso = getColaListos().getHead().gettInfo(); // Obtener el primer proceso
        getColaListos().desencolar(); // Eliminar de la cola
        System.out.println(proceso + " escogidooo");
        return proceso; // Retornar el proceso
    }

    /*La diferencia es que este se ejecuta 
    cada vez que termina el quantum de tiempo*/
    private Proceso roundRobin() {
        if (getColaListos().isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }

        Proceso proceso = getColaListos().getHead().gettInfo(); // Obtener el primer proceso 
        getColaListos().desencolar(); // Eliminar de la cola
        return proceso; // Retornar el proceso
    }

    private Proceso spn() {
        if (getColaListos().isEmpty()) {
            return null; // Si no hay procesos listos, retorna null
        }
        System.out.println("Antes de ordenar:");
        System.out.println(getColaListos().travel());

        // Ordenar la cola por número de instrucciones antes de buscar el proceso más corto
        ordenarColaPorNumeroInstrucciones(getColaListos()); //Quizá haya que cambiar por el método que ordena por tiempo restante
        System.out.println("Después de ordenar:");
        System.out.println(getColaListos().travel());
        // Obtener el proceso con el menor número de instrucciones
        Proceso procesoMasCorto = getColaListos().getHead().gettInfo();
        getColaListos().desencolar(); // Eliminar de la cola

        return procesoMasCorto; // Retornar el proceso que se ha ejecutado
    }

    private Proceso srt() {
        if (getColaListos().isEmpty()) {
            return null;
        }
        ordenarColaPorTiempoRestante(getColaListos()); 
        Proceso shorterProcess = getColaListos().getHead().gettInfo();
        getColaListos().desencolar(); // Eliminar de la cola
        //Proceso cpuCurrentProcess = cpuDefault.getActualProceso();
        return shorterProcess;
//        if (shorterProcess.getCant_instrucciones() < cpuCurrentProcess.getCant_instrucciones()) {
//            cpuDefault.setActualProceso(shorterProcess);
//        }
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

    public void expulsarProceso(Proceso proceso) { //hay que arreglarlo para que devuelva el tipo que es
        try {
            semaphore.acquire(); // Adquirir el permiso del semáforo (wait)
            proceso.getPCB_proceso().setEstado("Ready"); // Cambiar el estado a Ready
            int tiempoRestante = proceso.getTiempoRestante();
            //Quería usar el metodo copiar pero no me deja
            
            
            
            if (proceso.getTipo()=="CPU BOUND"){
                ProcesoCPUBOUND proceso2 = new ProcesoCPUBOUND(proceso.getNombreProceso(), proceso.getCant_instrucciones(), "CPU BOUND", proceso.getPCB_proceso(), proceso.getCiclosDuracion());
                System.out.println("Al hilo le fantan " + tiempoRestante + " instrucciones");
                System.out.println(proceso2.getTiempoRestante());
                proceso2.setTiempoRestante(tiempoRestante);
                proceso2.getPCB_proceso().setEstado("Ready");
                if (proceso.getNombreProceso() != "SO"){
                    this.getColaListos().encolar(proceso2); // Encolar el proceso en Listos
                }
            }else{
                ProcesoIOBOUND proceso2 = new ProcesoIOBOUND(proceso.getNombreProceso(), proceso.getCant_instrucciones(), "I/O BOUND", proceso.getPCB_proceso(), proceso.getCiclosDuracion(), proceso.getCicloGenerarExcepcion(), proceso.getCicloSatisfacerExcepcion());
                System.out.println("Al hilo le fantan " + tiempoRestante + " instrucciones");
                System.out.println(proceso2.getTiempoRestante());
                proceso2.setTiempoRestante(tiempoRestante);
                proceso2.getPCB_proceso().setEstado("Ready");
                if (proceso.getNombreProceso() != "SO"){
                    this.getColaListos().encolar(proceso2); // Encolar el proceso en Listos
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Liberar el permiso del semáforo (signal)
        }
    }

    public void bloquearProceso(Proceso proceso, int CiclosGenerar, int CicloSatisfacer) { //realmente lo que hace es meterlo a la cola de bloqueados
        try {
            semaphore2.acquire(); // Adquirir el permiso del semáforo (wait)
            proceso.getPCB_proceso().setEstado("Blocked"); // Cambiar el estado a Blocked
            int tiempoRestante = proceso.getTiempoRestante();
            //Quería usar el metodo copiar pero no me deja
            ProcesoIOBOUND proceso2 = new ProcesoIOBOUND(proceso.getNombreProceso(), proceso.getCant_instrucciones(), "I/O BOUND", proceso.getPCB_proceso(), proceso.getCiclosDuracion(), CiclosGenerar, CicloSatisfacer);
            System.out.println("Al hilo le fantan " + tiempoRestante + " instrucciones");
            System.out.println(proceso2.getTiempoRestante());
            
            this.getColaBloqueados().encolar(proceso);
            
            /*
            
            TE COMENTE LO DE ENCOLAR EL PROCESO COPIA
            
            */
                 
                
            
//            proceso2.setTiempoRestante(tiempoRestante);
//            proceso2.getPCB_proceso().setEstado("Blocked");
//
//            this.getColaBloqueados().encolar(proceso2); // Encolar el proceso en bloqueados
            

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore2.release(); // Liberar el permiso del semáforo (signal)
        }
    }
    
    public void desbloquearProceso(Proceso proceso, Proceso procesoEnEjecucion,  int CiclosGenerar, int CicloSatisfacer) { //necesitas es proceso que ya esperó por la E/S y el proceso actual en ejecución para sacarlo del cpu
        //Sacar al proceso de la cola de bloqueados
        try {
            semaphore2.acquire(); // Adquirir el permiso del semáforo (wait)
            proceso.getPCB_proceso().setEstado("Blocked"); // Cambiar el estado a Ready
            System.out.println("sacando al proceso de la cola de bloqueados");
            this.getColaBloqueados().desencolarEspecifico(proceso); // Desencolar el proceso

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore2.release(); // Liberar el permiso del semáforo (signal)
        }
        
        //Expulsar proceso en ejecución y colocar ambos en la cola de listos
        try {
            semaphore.acquire(); // Adquirir el permiso del semáforo (wait)
            procesoEnEjecucion.getPCB_proceso().setEstado("Ready"); // Cambiar el estado a Ready
            int tiempoRestante = procesoEnEjecucion.getTiempoRestante();
            //Quería usar el metodo copiar pero no me deja
            
            if (procesoEnEjecucion.getTipo()=="CPU BOUND"){
                ProcesoCPUBOUND proceso2 = new ProcesoCPUBOUND(proceso.getNombreProceso(), proceso.getCant_instrucciones(), "CPU BOUND", proceso.getPCB_proceso(), proceso.getCiclosDuracion());
                System.out.println("Al hilo le fantan " + tiempoRestante + " instrucciones");
                System.out.println(proceso2.getTiempoRestante());
                proceso2.setTiempoRestante(tiempoRestante);
                proceso2.getPCB_proceso().setEstado("Ready");
                if (procesoEnEjecucion.getNombreProceso() != "SO"){
                    this.getColaListos().encolar(proceso2); // Encolar el proceso en Listos
                }
            }else{
                ProcesoIOBOUND proceso2 = new ProcesoIOBOUND(procesoEnEjecucion.getNombreProceso(), procesoEnEjecucion.getCant_instrucciones(), "I/O BOUND", procesoEnEjecucion.getPCB_proceso(), procesoEnEjecucion.getCiclosDuracion(), procesoEnEjecucion.getCicloGenerarExcepcion(), procesoEnEjecucion.getCicloSatisfacerExcepcion());
                System.out.println("Al hilo le fantan " + tiempoRestante + " instrucciones");
                System.out.println(proceso2.getTiempoRestante());
                proceso2.setTiempoRestante(tiempoRestante);
                proceso2.getPCB_proceso().setEstado("Ready");
//                if (proceso.getNombreProceso() != "SO"){
//                    this.getColaListos().encolar(proceso2); // Encolar el proceso en Listos
//                }
            }
            
            int tiempoRestante2 = proceso.getTiempoRestante();
            ProcesoIOBOUND procesocopia = new ProcesoIOBOUND(proceso.getNombreProceso(), proceso.getCant_instrucciones(), "I/O BOUND", proceso.getPCB_proceso(), proceso.getCiclosDuracion(), CiclosGenerar, CicloSatisfacer);
            procesocopia.setTiempoRestante(tiempoRestante2);
            this.getColaListos().encolar(procesocopia);
            System.out.println("Encolados ya");
            System.out.println("El proceso en la cola de litos es.." + ColaListos.getTail().gettInfo().getNombreProceso());
            

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Liberar el permiso del semáforo (signal)
        }
        
    }
    
    
    
    public void terminarProceso(Proceso procesoTerminado){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Tamaño de cola de terminados..." + ColaTerminados.getSize());
        try {
            semaphore3.acquire(); //wait
            procesoTerminado.getPCB_proceso().setEstado("Exit");
            if (procesoTerminado.getNombreProceso() != "SO"){
                this.ColaTerminados.encolar(procesoTerminado);// Encolar el proceso en Terminados
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Planificador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            semaphore3.release(); // Liberar el permiso del semáforo (signal)
        }
        System.out.println(ColaTerminados.travel());
        System.out.println("Tamaño de cola de terminados..." + ColaTerminados.getSize());
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
            /*
            
            TE COMENTE VOLVER A ENCOLAR EL PROCESO
            
            */
            
            //getColaBloqueados().encolar(proceso);
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
        getColaListos().encolar(proceso);
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

    /**
     * @return the ColaListos
     */
    public Cola<Proceso> getColaListos() {
        return ColaListos;
    }

    /**
     * @return the ColaTerminados
     */
    public Cola<Proceso> getColaTerminados() {
        return ColaTerminados;
    }
    
    
}
