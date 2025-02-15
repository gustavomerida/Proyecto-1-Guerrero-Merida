package MainPackage;

import GUI.Classes.ChartClass;
import AuxClass.Cola;
import GUI.Classes.Estadisticas;
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
    private CPU cpu3;
    public AtomicInteger duracionCicloInstruccion = new AtomicInteger(3000); //Variable global que indica la duración de un ciclo de instrucción

    private static ChartClass chartClass;

    // 3. Constructor privado
    private App() {
        this.sistemaOperativo = inicializarSistemaOperativo();
    }

    // 4. Método Singleton
    public static App getInstance() {
        return uniqueApp;
    }

    public CPU getCpu3() {
        return cpu3;
    }

    // 5. Inicialización de componentes
    private SO inicializarSistemaOperativo() {

        Cola<Proceso> colaListos = new Cola<>();
        Cola<Proceso> colaBloqueados = new Cola<>();
        Cola<Proceso> colaTerminados = new Cola<>();


        this.planificador = new Planificador("FCFS", colaListos, colaBloqueados, colaTerminados, cpu1);

        return new SO(null, null, planificador);
    }

    public void start() { //Este me confunde un poco porque pienso que es un hilo

        RegistrosControlEstado environmentSO = new RegistrosControlEstado(0, 1, 0);
        PCB pcbSO = new PCB(0, "SO", "Running", environmentSO);
        ProcesoCPUBOUND pSO = new ProcesoCPUBOUND("SO", 3, "CPU BOUND", pcbSO, duracionCicloInstruccion);
        //////////////////////////////////////////////////////////////////////////////
        /*
        TESTEO DE PROCESOS -- CREACION DE PROCESOS
         */
        RegistrosControlEstado environment = new RegistrosControlEstado(0, 1, 0);
        PCB pcb = new PCB(0, "p4", "Ready", environment);

        RegistrosControlEstado environment2 = new RegistrosControlEstado(0, 1, 0);
        PCB pcb2 = new PCB(0, "p5", "Ready", environment2);

        RegistrosControlEstado environment3 = new RegistrosControlEstado(0, 1, 0);
        PCB pcb3 = new PCB(0, "p6", "Ready", environment3);

        //Proceso p1 = new ProcesoCPUBOUND("p1", 18, "CPU BOUND", pcb, duracionCicloInstruccion);
        Proceso p2 = new ProcesoCPUBOUND("p2", 10, "CPU BOUND", pcb2, duracionCicloInstruccion);
        //Proceso p3 = new ProcesoCPUBOUND("p3", 7, "CPU BOUND", pcb3, duracionCicloInstruccion);
        Proceso p4 = new ProcesoIOBOUND("p4", 6, "I/O BOUND", pcb, duracionCicloInstruccion, 3, 3);
//        Proceso p5 = new ProcesoIOBOUND("p5", 5, "I/O BOUND", pcb2, duracionCicloInstruccion, 3, 3);
//        Proceso p6 = new ProcesoIOBOUND("p6", 15, "I/O BOUND", pcb3, duracionCicloInstruccion, 5, 3);

        //planificador.getColaListos().encolar(p1);
        planificador.getColaListos().encolar(p2);
        //planificador.getColaListos().encolar(p3);
        planificador.getColaListos().encolar(p4);

        ////////////////////////////////////////////////////////////////////////////
        this.cpu1 = new CPU(0, null, "Activo", pSO);

        this.cpu1.setPlanificador(planificador);
        //////////////////////////////////////////////////////////////////////////////
        this.cpu2 = new CPU(1, null, "Activo", pSO);

        this.cpu2.setPlanificador(planificador);

        this.cpu1.start();
        this.cpu2.start();

    }

    public static ChartClass getChartClass() {
        return chartClass;
    }

    public void start2() {

        Simulator simulator = new Simulator("3000", 2, "FCFS");
        simulator.setVisible(true);

        chartClass = new ChartClass(0);

        Estadisticas estadistica = new Estadisticas();
        estadistica.setVisible(true);
    }

    public void setearProcesoACPU() {
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
