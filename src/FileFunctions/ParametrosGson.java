/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileFunctions;

import AuxClass.Cola;
import MainClasses.PCB;
import MainClasses.Proceso;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Angelo
 */
public class ParametrosGson {

    private Cola<Proceso> colaListos;
    private int cicloDuracion;
    private String estado;
    private int cantProcesadores;


    public ParametrosGson(Cola<Proceso> colaListos, int cicloDuracion, String estado, int cantProcesadores) {
        this.colaListos = colaListos;
        this.cicloDuracion = cicloDuracion;
        this.estado = estado;
        this.cantProcesadores = cantProcesadores;
    }

    public Cola<Proceso> getColaListos() {
        return colaListos;
    }

    public void setColaListos(Cola<Proceso> colaListos) {
        this.colaListos = colaListos;
    }

    public int getCicloDuracion() {
        return cicloDuracion;
    }

    public void setCicloDuracion(int cicloDuracion) {
        this.cicloDuracion = cicloDuracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    


    public int getCantProcesadores() {
        return cantProcesadores;
    }

    public void setCantProcesadores(int cantProcesadores) {
        this.cantProcesadores = cantProcesadores;
    }

  
}
