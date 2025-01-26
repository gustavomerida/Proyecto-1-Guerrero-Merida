/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author Angelo
 */
public class Proceso extends Thread{
    private String nombre_proceso;
    private int cant_instrucciones;
    private String tipo;
    private PCB PCB_proceso;
    private int tiempoRestante; // Tiempo restante en ciclos
    
    public Proceso(String nombre_proceso, int cant_instrucciones, String tipo, PCB PCB_proceso){
        this.nombre_proceso = nombre_proceso;
        this.cant_instrucciones = cant_instrucciones;
        this.PCB_proceso = PCB_proceso;
        this.tipo = tipo;
        this.tiempoRestante = cant_instrucciones; // Inicialmente son iguales
    }
    
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void reducirTiempo(int tiempo) {
        this.tiempoRestante -= tiempo;
    }

    public String getNombreProceso() {
        return nombre_proceso;
    }
    
    /**
     *
     */
    @Override
    public void run(){
    }

    /**
     * @return the cant_instrucciones
     */
    public int getCant_instrucciones() {
        return cant_instrucciones;
    }

    /**
     * @param cant_instrucciones the cant_instrucciones to set
     */
    public void setCant_instrucciones(int cant_instrucciones) {
        this.cant_instrucciones = cant_instrucciones;
    }
}
