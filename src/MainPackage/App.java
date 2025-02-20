package MainPackage;

import GUI.Classes.ChartClass;
import AuxClass.Cola;
import GUI.Classes.Home;
import MainClasses.CPU;
import MainClasses.PCB;
import MainClasses.Planificador;
import MainClasses.Proceso;
import MainClasses.ProcesoCPUBOUND;
import MainClasses.RegistrosControlEstado;
import FileFunctions.GuardadoGson;
import MainClasses.ProcesoIOBOUND;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private static final App uniqueApp = new App();
    private Planificador planificador;
    private CPU cpu1;
    private CPU cpu2;
    private CPU cpu3;
    private GuardadoGson guardadoGson;
    private int CPUsActivos;
    public AtomicInteger duracionCicloInstruccion = new AtomicInteger(); //Variable global que indica la duración de un ciclo de instrucción

    public int relojGlobal = 0;

    private static ChartClass chartClassSystem;
    private static ChartClass chartClassCPU1;
    private static ChartClass chartClassCPU2;
    private static ChartClass chartClassCPU3;

    private App() {
        this.planificador = inicializarSistemaOperativo();
    }

    public static App getInstance() {
        return uniqueApp;
    }

    private Planificador inicializarSistemaOperativo() {

        Cola<Proceso> colaListos = new Cola<>();
        Cola<Proceso> colaBloqueados = new Cola<>();
        Cola<Proceso> colaTerminados = new Cola<>();

        this.planificador = new Planificador("", colaListos, colaBloqueados, colaTerminados, null);

        return planificador;

    }

    public void start() { //Este me confunde un poco porque pienso que es un hilo
        /*
        TESTEO DE PROCESOS -- CREACION DE PROCESOS
         */
//        RegistrosControlEstado environment = new RegistrosControlEstado(0, 1, 0);
//        PCB pcb = new PCB("p1", "p1", "Ready", environment);
//        
//        RegistrosControlEstado environment2 = new RegistrosControlEstado(0, 1, 0);
//        PCB pcb2 = new PCB("p2", "p2", "Ready", environment2);
//
//        RegistrosControlEstado environment3 = new RegistrosControlEstado(0, 1, 0);
//        PCB pcb3 = new PCB("p3", "p3", "Ready", environment3);
//        
//        RegistrosControlEstado environment4 = new RegistrosControlEstado(0, 1, 0);
//        PCB pcb4 = new PCB("p4", "p4", "Ready", environment4);
//
//        Proceso p1 = new ProcesoCPUBOUND("p1", 18, "CPU BOUND", pcb, duracionCicloInstruccion);
//        Proceso p2 = new ProcesoCPUBOUND("p2", 6, "CPU BOUND", pcb2, duracionCicloInstruccion);
//        Proceso p3 = new ProcesoCPUBOUND("p3", 7, "CPU BOUND", pcb3, duracionCicloInstruccion);
//        Proceso p4 = new ProcesoIOBOUND("p4", 10, "I/O BOUND", pcb4, duracionCicloInstruccion, 3, 3);
////        Proceso p5 = new ProcesoIOBOUND("p5", 5, "I/O BOUND", pcb2, duracionCicloInstruccion, 3, 3);
////        Proceso p6 = new ProcesoIOBOUND("p6", 15, "I/O BOUND", pcb3, duracionCicloInstruccion, 5, 3);
//        planificador.getColaListos().encolar(p1);
//        planificador.getColaListos().encolar(p2);
//        planificador.getColaListos().encolar(p3);
//        planificador.getColaListos().encolar(p4);
////        
////        
//        this.CPUsActivos = 2;
//        this.duracionCicloInstruccion = new AtomicInteger(1000);
//        planificador.getColaListos().encolar(p4);
    }

    public void start2() {

        /*
        CARGA DE LOS PROCESOS EN 1, ESCRITURA DE LOS PROCESOS EN 0
         */
        this.guardadoGson = new GuardadoGson(1);

        Home home = new Home();
        home.setVisible(true);

        /*
        CREACION DE LAS GRAFICAS EN TIEMPO REAL
         */
        chartClassSystem = new ChartClass(0);
        chartClassCPU1 = new ChartClass(1);
        chartClassCPU2 = new ChartClass(2);
        chartClassCPU3 = new ChartClass(3);

//        this.relojGlobal = simulator.getRelojGlobal();
    }

    public void startSimulator(int cpuQuantity) {
        RegistrosControlEstado environmentSO = new RegistrosControlEstado(0, 1, 0);
        PCB pcbSO = new PCB("SO", "SO", "Running", environmentSO);
        ProcesoCPUBOUND pSO = new ProcesoCPUBOUND("SO", 3, "CPU BOUND", pcbSO, duracionCicloInstruccion);

        this.cpu1 = new CPU(0, null, "Activo", pSO);

        this.cpu1.setPlanificador(planificador);

        this.cpu2 = new CPU(1, null, "Activo", pSO);

        this.cpu2.setPlanificador(planificador);

        this.cpu3 = new CPU(2, null, "Activo", pSO);

        this.cpu3.setPlanificador(planificador);

        if (cpuQuantity == 3) {
            this.CPUsActivos = 3;
            this.cpu1.start();
            this.cpu2.start();
            this.cpu3.start();
        } else {
            this.CPUsActivos = 2;
            this.cpu1.start();
            this.cpu2.start();

        }

    }

    public GuardadoGson getGuardadoGson() {
        return guardadoGson;
    }

    public void setGuardadoGson(GuardadoGson guardadoGson) {
        this.guardadoGson = guardadoGson;
    }

    public void setearProcesoACPU() {
        this.planificador.escogerProceso();
    }

    public Planificador getPlanificador() {
        return planificador;
    }

    public CPU getCpu1() {
        return cpu1;
    }

    public CPU getCpu2() { //Falta getcpu3
        return cpu2;
    }

    public CPU getCpu3() {
        return cpu3;
    }

    public int getRelojGlobal() {
        return this.relojGlobal;
    }

    public static ChartClass getChartClassSystem() {
        return chartClassSystem;
    }

    public static ChartClass getChartClassCPU1() {
        return chartClassCPU1;
    }

    public static ChartClass getChartClassCPU2() {
        return chartClassCPU2;
    }

    public static ChartClass getChartClassCPU3() {
        return chartClassCPU3;
    }

    public int getCPUsActivos() {
        return CPUsActivos;
    }

    public void setCPUsActivos(int CPUsActivos) {
        this.CPUsActivos = CPUsActivos;
    }

}
