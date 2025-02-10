package MainPackage;

import AuxClass.Cola;
import GUI.Classes.Home;
import GUI.Classes.Simulator;
import MainClasses.CPU;
import MainClasses.PCB;
import MainClasses.Planificador;
import MainClasses.Proceso;
import MainClasses.ProcesoCPUBOUND;
import MainClasses.ProcesoIOBOUND;
import MainClasses.RegistrosControlEstado;
import MainClasses.SO;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
   
    private static final App uniqueApp = new App();
    
    // 2. Componentes del sistema encapsulados
    private final SO sistemaOperativo;
    private Planificador planificador;
    private CPU cpu1;
    private CPU cpu2;
    public AtomicInteger duracionCicloInstruccion = new AtomicInteger(2000); //Variable global que indica la duración de un ciclo de instrucción
    
    // 3. Constructor privado
    private App() {
        this.sistemaOperativo = inicializarSistemaOperativo();
    }
    
    // 4. Método Singleton
    public static App getInstance() {
        return uniqueApp;
    }
    
    // 5. Inicialización de componentes
    private SO inicializarSistemaOperativo() {
        Cola<Proceso> colaListos = new Cola<>();
        Cola<Proceso> colaBloqueados = new Cola<>();
        Cola<Proceso> colaTerminados = new Cola<>();
        RegistrosControlEstado environmentSO = new RegistrosControlEstado(0, 1, 0);
        PCB pcbSO = new PCB(0, "SO", "Running", environmentSO);
        ProcesoCPUBOUND pSO = new ProcesoCPUBOUND("SO", 3, "CPU BOUND", pcbSO, duracionCicloInstruccion);
        //////////////////////////////////////////////////////////////////////////////
        /*
        TESTEO DE PROCESOS -- CREACION DE PROCESOS
        */
        RegistrosControlEstado environment = new RegistrosControlEstado(0, 1, 0);
        PCB pcb = new PCB(0, "p1", "Ready", environment);
        
        RegistrosControlEstado environment2 = new RegistrosControlEstado(0, 1, 0);
        PCB pcb2 = new PCB(0, "p2", "Ready", environment2);
        
        RegistrosControlEstado environment3 = new RegistrosControlEstado(0, 1, 0);
        PCB pcb3 = new PCB(0, "p3", "Ready", environment3);
        
        
        Proceso p1 = new ProcesoCPUBOUND("p1", 10, "CPU BOUND", pcb, duracionCicloInstruccion);
        Proceso p2 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, duracionCicloInstruccion);
        Proceso p3 = new ProcesoCPUBOUND("p3", 6, "CPU BOUND", pcb3, duracionCicloInstruccion);
        
        
//        Proceso p4 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p5 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p6 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p7 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p8 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p9 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p10 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p11 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p12 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);
//        Proceso p13 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 1000);

        colaListos.encolar(p1);
        colaListos.encolar(p2);
        colaListos.encolar(p3);
        //colaListos.encolar(p4);
        
        
//        colaListos.encolar(p3);
//        colaListos.encolar(p4);
//        colaListos.encolar(p5);
//        colaListos.encolar(p6);
//        colaListos.encolar(p7);
//        colaListos.encolar(p8);
//        colaListos.encolar(p9);
//        colaListos.encolar(p10);
//        colaListos.encolar(p11);
//        colaListos.encolar(p12);
//        colaListos.encolar(p13);
        
        
        
        ////////////////////////////////////////////////////////////////////////////
        this.cpu1 = new CPU(0, null, "Activo", pSO);
        this.planificador = new Planificador("RR", colaListos, colaBloqueados, colaTerminados, cpu1);
        this.cpu1.setPlanificador(planificador);
        //////////////////////////////////////////////////////////////////////////////
        this.cpu2 = new CPU(0, null, "Activo", pSO);
        this.planificador = new Planificador("RR", colaListos, colaBloqueados, colaTerminados, cpu2);
        this.cpu2.setPlanificador(planificador);
        
        this.cpu1.start();
        this.cpu2.start();
        
        return new SO(null, null, planificador);
    }
    
    public void start() { //Este me confunde un poco porque pienso que es un hilo
//        Home home = new Home();
//        home.setVisible(true);
        //SO so = this.inicializarSistemaOperativo();
        
        Simulator simulator =  new Simulator("1000", 2, "RR");
        simulator.setVisible(true);
        

    }
    
    public void setearProcesoACPU()
    {
       this.planificador.escogerProceso();
       //this.cpu.setActualProceso(actualProceso);
    }
    
    
    public SO getSistemaOperativo() {
        return sistemaOperativo;
    }
    
    public Planificador getPlanificador() {
        return planificador;
    }
    
    public CPU getCpu1() {
        return cpu1;
    }
    
    public CPU getCpu2() {
        return cpu2;
    }
    
}