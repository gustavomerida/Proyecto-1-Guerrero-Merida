package MainPackage;

import AuxClass.Cola;
import GUI.Classes.Home;
import MainClasses.CPU;
import MainClasses.Planificador;
import MainClasses.Proceso;
import MainClasses.SO;

public class App {
    // 1. Instancia única Singleton (inicialización eager)
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
        
        this.cpu = new CPU(0, null, "Activo");
        this.planificador = new Planificador("FCFS", colaListos, colaBloqueados, colaTerminados, cpu);
        
        return new SO(null, null, planificador);
    }
    
    public void start() {
        Home home = new Home();
        home.setVisible(true);
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