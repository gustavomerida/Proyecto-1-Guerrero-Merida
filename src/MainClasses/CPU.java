/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Classes.Simulator;
import javax.swing.SwingUtilities;
import MainPackage.App;

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

    private int CPUBoundCounter;
    private int IOBoundCounter;

    public CPU(int id, Proceso actualProceso, String estado, ProcesoCPUBOUND procesoSO) {
        this.id = id;
        this.actualProceso = actualProceso;
        this.estado = estado;
        this.procesoSO = procesoSO;
        this.CPUBoundCounter = 0;
        this.IOBoundCounter = 0;
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
                if (p.getPCB_proceso().getEstado() == "Blocked") {
                    int[] ciclos;
                    ciclos = new int[]{p.getCicloGenerarExcepcion(), p.getCicloSatisfacerExcepcion()};
                    System.out.println("MIRAAAAAAAAAAAAAAAAA" + ciclos[0]);
                    Excepcion e = new Excepcion(p, this, this.getPlanificador(), ciclos);
                    //e.proceso = p;
                    e.start();
//                    if (p.getPCB_proceso().getEstado().equals("Blocked")) {
//                        // Ejecutar el SO durante los ciclos necesarios
//                        for (int i = 0; i < this.procesoSO.getCant_instrucciones(); i++) {
//                            try {
//                                // Lógica para ejecutar el proceso del SO
//                                this.sleep(this.procesoSO.getCiclosDuracion().get());
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                        // Mover el proceso de la cola de bloqueados a la cola de listos
//                        this.planificador.desbloquearProceso(p);
//                    }
                }
            }

            ProcesoCPUBOUND pr = procesoSO.copiar();
            this.setActualProceso(pr);
            try {
                pr.start();
                System.out.println("Esperando por proceso de SO a terminar...");
                this.sleep(pr.getCant_instrucciones() * pr.getCiclosDuracion().get()); //HAY que cambiar este tiempo. 
            } catch (InterruptedException ex) {
                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
            }
            p = this.getPlanificador().escogerProceso();
            System.out.println(p);
            if (p != null) {
//                if (p.getPCB_proceso().getEstado()=="Blocked"){
//                   e.proceso = p;
//                   e.start();
//                }
                if (p.getTipo().equals("CPU BOUND")) {
                    this.CPUBoundCounter++;
                } else {
                    this.IOBoundCounter ++;
                }

                this.setEstado("Activo"); //estado del CPU
                switch (this.getPlanificador().getNombreAlgoritmo()) {
                    case "FCFS":
                        this.setActualProceso(p);

                         {
                            try {
                                if (p.getPCB_proceso().getEstado() != "Exit" && p.getPCB_proceso().getEstado() != "Blocked") {
                                    p.start();
                                }
                                this.sleep(p.getCant_instrucciones() * p.getCiclosDuracion().get());
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case "RR":

                        this.setActualProceso(p);
                        if (contadorCiclos < quantum) {
                            try {
                                p.start();
                                this.sleep(p.getCiclosDuracion().get() * (quantum + 1)); // Duerme el tiempo de un ciclo
                                contadorCiclos = quantum + 1; // Incrementa el contador de ciclos
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            // Verifica si el proceso terminó
                            if (p.getTiempoRestante() == 0) {
                                // Si el proceso terminó, resetea el contador y sale del bucle
                                p.getPCB_proceso().setEstado("Exit");
                                contadorCiclos = 0;
                                break;
                            }
                        }
                        // Si se alcanzó el quantum, cambia el estado del proceso actual a "Ready"
                        if (contadorCiclos == quantum + 1) {
                            //p.getPCB_proceso().setEstado("Ready"); //ya esto lo hace la función de abajo
                            // Aquí va la lógica para reinsertar el proceso en la cola de listos
                            this.planificador.expulsarProceso(p);
                        }

                        contadorCiclos = 0; // Resetea el contador para el próximo proceso
                        break;

                    case "SPN":
                        this.setActualProceso(p);

                         {
                            try {
                                if (p.getPCB_proceso().getEstado() != "Exit" && p.getPCB_proceso().getEstado() != "Blocked") {
                                    p.start();
                                }
                                this.sleep(p.getCant_instrucciones() * p.getCiclosDuracion().get());
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                    case "SRT":
                        //srt(this.cpuDefault);
                        System.out.println("Aqui taaa");
                        this.setActualProceso(p);
                        try {
                            // Verificar si el proceso actual puede continuar
                            //if (p.getPCB_proceso().getEstado() != "Exit" && p.getPCB_proceso().getEstado() != "Blocked") {
                                p.start();
                            //}

                            // Duerme por el tiempo del proceso actual
                            this.sleep(p.getCiclosDuracion().get() * p.getCant_instrucciones());

                            // Después de dormir, verifica si hay un proceso listo con menor tiempo restante
                            Proceso nuevoProceso = this.getPlanificador().escogerProceso();
                            if (nuevoProceso != null && nuevoProceso.getTiempoRestante() < p.getTiempoRestante()) {
                                // Si hay un nuevo proceso con menos tiempo restante, interrumpimos el actual
                                this.planificador.expulsarProceso(p); // Mueve el proceso actual a la cola de listos
                                //p = nuevoProceso; // Cambia al nuevo proceso
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "HRRN": //Creo que falta completar
                        this.setActualProceso(p);

                         {
                            try {
                                p.start();
                                p.getPCB_proceso().setEstado("Ready");
                                this.planificador.getColaListos().encolar(p);
                                this.sleep(p.getCant_instrucciones() * p.getCiclosDuracion().get());
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        break;

                }
            } else {
                this.setEstado("Inactivo");
//                pr = procesoSO.copiar();
//                this.setActualProceso(pr);
//                try {
//                    pr.start();
//                    System.out.println("Esperando por proceso de SO a terminar...");
//                    this.sleep(pr.getCant_instrucciones() * pr.getCiclosDuracion().get()); //HAY que cambiar este tiempo. 
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
//                }
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
