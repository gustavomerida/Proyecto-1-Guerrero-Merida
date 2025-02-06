package MainPackage;

import AuxClass.Cola;
import GUI.Classes.Home;
import MainClasses.CPU;
import MainClasses.PCB;
import MainClasses.Planificador;
import MainClasses.Proceso;
import MainClasses.ProcesoCPUBOUND;
import MainClasses.ProcesoIOBOUND;
import MainClasses.RegistrosControlEstado;
import MainClasses.SO;

public class App {
    // 1. Instancia única Singleton 
    private static final App uniqueApp = new App();
    
    // 2. Componentes del sistema encapsulados
    private final SO sistemaOperativo;
    private Planificador planificador;
    private CPU cpu;
    
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
        
        //////////////////////////////////////////////////////////////////////////////
        /*
        TESTEO DE PROCESOS -- CREACION DE PROCESOS
        */
        RegistrosControlEstado environment = new RegistrosControlEstado(0, 1, 0);
        PCB pcb = new PCB(0, "p4", "Ready", environment);
        Proceso p1 = new ProcesoCPUBOUND("p1", 10, "CPU BOUND", pcb, 3000);
        
//        RegistrosControlEstado environment2 = new RegistrosControlEstado(0, 1, 0);
//        PCB pcb2 = new PCB(0, "p2", "Ready", environment2);
//        Proceso p2 = new ProcesoCPUBOUND("p2", 4, "CPU BOUND", pcb2, 3000);
        
        
        Proceso p4 = new ProcesoIOBOUND("p4", 8, "I/O BOUND", pcb, 3000, 2, 3);
        
        
        //colaListos.encolar(p1);
//        colaListos.encolar(p2);
        colaListos.encolar(p4);
        
        
        ////////////////////////////////////////////////////////////////////////////
        this.cpu = new CPU(0, p4, "Activo");
        this.planificador = new Planificador("FCFS", colaListos, colaBloqueados, colaTerminados, cpu);
        
        return new SO(null, null, planificador);
    }
    
    public void start() { //Este me confunde un poco porque pienso que es un hilo
        Home home = new Home();
        home.setVisible(true);

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
    
    public CPU getCpu() {
        return cpu;
    }
}