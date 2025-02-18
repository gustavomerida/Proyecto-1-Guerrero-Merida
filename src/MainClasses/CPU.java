/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import AuxClass.Conjunto;
import GUI.Classes.Simulator;
import javax.swing.SwingUtilities;
import MainPackage.App;
import javax.swing.JOptionPane;

/**
 *
 * @author Angelo
 */
public class CPU extends Thread {

    private int id;
    private Proceso actualProceso;
    private String estado; // Ocupado o libre
    private Planificador planificador;
    private final ProcesoCPUBOUND procesoSO;
    private final App app = App.getInstance();

    private int CPUBoundCounter;
    private int IOBoundCounter;
    
    private Conjunto conjuntoProcesos;

    public CPU(int id, Proceso actualProceso, String estado, ProcesoCPUBOUND procesoSO) {
        this.id = id;
        this.actualProceso = actualProceso;
        this.estado = estado;
        this.procesoSO = procesoSO;
        this.CPUBoundCounter = 0;
        this.IOBoundCounter = 0;
        this.conjuntoProcesos = new Conjunto();
    }

    public Conjunto getConjuntoProcesos() {
        return conjuntoProcesos;
    }

    public void setConjuntoProcesos(Conjunto conjuntoProcesos) {
        this.conjuntoProcesos = conjuntoProcesos;
    }
    
    

    public int getCPUBoundCounter() {
        return CPUBoundCounter;
    }

    public int getIOBoundCounter() {
        return IOBoundCounter;
    }

    public Proceso getActualProceso() {
        return actualProceso;
    }

    public void setActualProceso(Proceso actualProceso) {

        this.actualProceso = actualProceso;
        actualProceso.getPCB_proceso().setEstado("Running");
    }

    @Override
    public void run() { //Aquí también hay que llevar el contadodor de ciclos global
        int contadorCiclos = 0; // Contador de ciclos
        final int quantum = planificador.getQuantum(); //Tiempo máximo (cantidad de ciclos) de ejecución por proceso
        //Excepcion e = new Excepcion(null, this, this.getPlanificador());
        Proceso p = null;
        while (true) {
            if (p != null) {
                p.setTiempoEnCola(p.getTiempoEnCola() + (app.getRelojGlobal()-p.getCicloEntradaListo()));
                if (p.getPCB_proceso().getEstado() == "Blocked") {
                    int[] ciclos;
                    ciclos = new int[]{p.getCicloGenerarExcepcion(), p.getCicloSatisfacerExcepcion()};
                    Excepcion e = new Excepcion(p, this, this.getPlanificador(), ciclos);
                    e.start();
                }
            }

            ProcesoCPUBOUND pr = procesoSO.copiar();
            this.setActualProceso(pr);
            try {
                pr.start();
//                System.out.println("Esperando por proceso de SO a terminar...");
                this.sleep(pr.getCant_instrucciones() * pr.getCiclosDuracion().get()); //HAY que cambiar este tiempo. 
            } catch (InterruptedException ex) {
                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
            }

            p = this.getPlanificador().escogerProceso(app.getRelojGlobal());
            System.out.println(p);

            if (p != null) {
//                if (p.getPCB_proceso().getEstado()=="Blocked"){
//                   e.proceso = p;
//                   e.start();
//                }
                if (p.getTipo().equals("CPU BOUND")) {
                    this.CPUBoundCounter++;
                } else {
                    this.IOBoundCounter++;
                }

                this.setEstado("Activo"); //estado del CPU
                switch (this.getPlanificador().getNombreAlgoritmo()) {
                    case "FCFS":
                        this.setActualProceso(p);
                        this.conjuntoProcesos.insertar(p);
                        System.out.println("SE HA INSERTADO CON EXITO " + p.getNombreProceso() + "EN CPU " + this.id);

                         {
                            try {
                                if (!("Exit".equals(p.getPCB_proceso().getEstado())) && !("Blocked".equals(p.getPCB_proceso().getEstado()))) {
                                    p.start();
                                    this.sleep(p.getSleepTime() * p.getCiclosDuracion().get());
                                }
                                
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case "RR":

                        this.setActualProceso(p);
                        this.conjuntoProcesos.insertar(p);
                        contadorCiclos = 0; // Resetea el contador al iniciar el proceso
                        
                        try {
                            p.start();
                            while (contadorCiclos < quantum  && !("Exit".equals(p.getPCB_proceso().getEstado())) && !("Blocked".equals(p.getPCB_proceso().getEstado()))) {
                                this.sleep(p.getCiclosDuracion().get()); // Duerme el tiempo de un ciclo
                                contadorCiclos++; // Incrementa el contador de ciclos
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        

                        // Verifica si el proceso terminó
                        if (p.getTiempoRestante() == 0) {
                            p.getPCB_proceso().setEstado("Exit");
                        } else {
                            System.out.println("El proceso " + p.getNombreProceso() + "está por ser expulsado");
                            // Si no terminó, reinsertar en la cola de listos
                            this.planificador.expulsarProceso(p);
                        }
                        break;

                    case "SPN":
                        this.setActualProceso(p);
                        this.conjuntoProcesos.insertar(p);

                         {
                            try {
                                if (!(p.getPCB_proceso().getEstado().equals("Exit")) && !((p.getPCB_proceso().getEstado().equals("Blocked")))) {
                                    p.start();
                                }
                                this.sleep(p.getSleepTime() * p.getCiclosDuracion().get());
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case "SRT":
                        //srt(this.cpuDefault);
                        System.out.println("Aqui taaa");
                        this.setActualProceso(p);
                        this.conjuntoProcesos.insertar(p);
                        Proceso nuevoP = this.getPlanificador().getShorterProcess();
                        int shortestRT = (nuevoP == null) ? (p.getTiempoRestante()+10) : nuevoP.getTiempoRestante(); //el valor del tiempo restante más corto depende de si hay procesos en la cola de listos
                        try {
                            p.start();
                            
                            while (shortestRT > p.getTiempoRestante()  && !("Exit".equals(p.getPCB_proceso().getEstado())) && !("Blocked".equals(p.getPCB_proceso().getEstado()))) {
                                this.sleep(p.getCiclosDuracion().get()); // Duerme el tiempo de un ciclo
                                nuevoP = this.getPlanificador().getShorterProcess();
                                shortestRT = (nuevoP == null) ? (p.getTiempoRestante()+10) : nuevoP.getTiempoRestante();
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        

                        // Verifica si el proceso terminó
                        if (p.getTiempoRestante() == 0) {
                            p.getPCB_proceso().setEstado("Exit");
                        } else {
                            System.out.println("El proceso " + p.getNombreProceso() + "está por ser expulsado");
                            if (!("Blocked".equals(p.getPCB_proceso().getEstado()))){
                                // Si no terminó, reinsertar en la cola de listos
                                this.planificador.expulsarProceso(p);
                            }
                        }
                        break;
                    case "HRRN": //Creo que falta completar
                        this.setActualProceso(p);
                        this.conjuntoProcesos.insertar(p);
                        
                        {
                            try {
                                p.start();
                                for (int i = 0; i < p.getCant_instrucciones(); i++) {
                                    
                                    // Verifica si el proceso terminó
                                    if (p.getTiempoRestante() == 0) {
                                        // Si el proceso terminó sale del bucle
                                        p.getPCB_proceso().setEstado("Exit");
                                        //contadorCiclos = 0;
                                        break;
                                    }

                                    // Después de dormir, verifica si hay un proceso listo con menor tiempo restante
                                    Proceso nuevoProceso = this.getPlanificador().getColaListos().getHead().gettInfo();
                                    
                                    if (nuevoProceso != null) {
                                        this.planificador.calculoRadioRespuesta(nuevoProceso, app.getRelojGlobal());
                                        this.planificador.calculoRadioRespuesta(p, app.getRelojGlobal());
                                        if (nuevoProceso.getTasaRespuesta() < p.getTasaRespuesta()){
                                            // Si hay un nuevo proceso con menos tiempo restante, interrumpimos el actual
                                            this.planificador.expulsarProceso(p);
                                            break;// Mueve el proceso actual a la cola de listos
                                            //p = nuevoProceso; // Cambia al nuevo proceso
                                        }
                                    }
                                    this.sleep(p.getCiclosDuracion().get());
                                }
                                
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        
                        
//                        while (true){
//                            if (this.getPlanificador().getColaListos().getSize()>0){
//                                this.getPlanificador().ordenarColaPorRadioRespuesta(this.getPlanificador().getColaListos());
//                                Proceso n = this.getPlanificador().getColaListos().getHead().gettInfo();
//                                this.planificador.calculoRadioRespuesta(p, app.getRelojGlobal());
//                                this.planificador.calculoRadioRespuesta(n, app.getRelojGlobal());
//                                if (n.getTasaRespuesta()>p.getTasaRespuesta()){
//                                    this.planificador.expulsarProceso(p); 
//                                    break;
//                                }
//                            }else if (!(p.getPCB_proceso().getEstado().equals("Running")) || p.getTiempoRestante()==0){
//                                break;
//                                
//                            }
//                        }

                        
//                         {
//                            try {
//                                p.start();
//                                p.getPCB_proceso().setEstado("Ready");
//                                this.planificador.getColaListos().encolar(p);
//                                this.sleep(p.getCant_instrucciones() * p.getCiclosDuracion().get());
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }

                       // break;


                }
            } else {
                this.setEstado("Inactivo");
                pr = procesoSO.copiar();

                this.setActualProceso(pr);
                try {
                    pr.start();
                    System.out.println("Esperando por proceso de SO a terminar...");
                    this.sleep(pr.getCant_instrucciones() * pr.getCiclosDuracion().get()); //HAY que cambiar este tiempo. 
                } catch (InterruptedException ex) {
                    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /*
    private void ejecutarProcesoSO() {
        System.out.println("CPU " + this.id + " ejecutando proceso del Sistema Operativo...");

        // Crear un proceso de SO temporal
        RegistrosControlEstado environment = new RegistrosControlEstado(0, 1, 0);
        PCB pcb = new PCB(0, "Proceso de SO", "Running", environment);
        Proceso procesoSO = new ProcesoCPUBOUND("Proceso de SO", 3, "CPU BOUND", pcb, 500); 

        procesoSO.start();

        // Simular ejecución del proceso de SO
        while (procesoSO.getTiempoRestante() > 0) {
            try {
                Thread.sleep(procesoSO.getCiclosDuracion());
                procesoSO.reducirTiempo(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Proceso de SO finalizado en CPU " + this.id);
    }
     */
    /**
     * @return the id
     */
    public int getIdp() { //Tuve que cambiarle el nombre
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the planificador
     */
    public Planificador getPlanificador() {
        return planificador;
    }

    /**
     * @param planificador the planificador to set
     */
    public void setPlanificador(Planificador planificador) {
        this.planificador = planificador;
    }
}
