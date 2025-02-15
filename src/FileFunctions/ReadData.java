/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileFunctions;

import AuxClass.Cola;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import AuxClass.List;
import AuxClass.Nodo;
import MainClasses.PCB;
import MainClasses.Proceso;
import MainPackage.App;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Angelo
 */
public class ReadData {

    private final App app = App.getInstance();

    public static String read(File file) {
        String line;
        String data = "";

        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                // LEYENDO CARACTER A CARACTER TODO EL DOCUMENTO

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null) {
                    if (!(line.isEmpty())) {
                        data += line + "\n";
                    }
                }
                br.close();
            }
            return data;

        } catch (Exception e) {
        }
        return data;
    }

    public static List getGeneralParams(String fileData) {
        int initialIndex = fileData.indexOf("[General Params]");
        if (initialIndex == -1) {
            return null;
        }

        int lastIndex = fileData.indexOf("[", initialIndex + 1);
        if (lastIndex == -1) {
            lastIndex = fileData.length();
        }

        String generalSection = fileData.substring(initialIndex, lastIndex);

        // DIVISORIA DE SECCION EN LINEAS
        String[] lines = generalSection.split("\n");

        // CREAMOS UNA LISTA PARA ALMACENAR LOS VALORES DE LA CONFIGURACION
        List generalParams = new List<>("General Params");

        for (String line : lines) {
            if (line.contains("=")) {
                // DIVIDE A CLAVE VALOR
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    try {
                        generalParams.append(parts[1].trim());
                    } catch (Exception e) {
                        System.out.println("ha ocurrido un error");
                    }
                }
            }
        }

        return generalParams;
    }

//    public static List<Proceso> getColaListos(String fileData, int TipoProceso) {
//
//        //
//    }

    // RECORDAR LO DE STATIC
    public String getActualParams() {

        String data = "[General Params]\n";
        String nombreAlgoritmo = app.getPlanificador().getNombreAlgoritmo();
        // DEFINIR EL CILODURACION EN PLANIFICADOR
//        AtomicInteger cicloDuracion = app.getPlanificador()

        data += "nombreAlgoritmo=" + nombreAlgoritmo + "\n" + "";

        String nombre_proceso;
        int cant_instrucciones;
        String tipo;
        PCB PCB_proceso;
        AtomicInteger ciclosDuracion;
        int cicloGenerarExcepcion; 
        int cicloSatisfacerExcepcion;

        data += "[Cola de listos]";
        Cola ColaLISTOS = app.getPlanificador().getColaListos();
        Nodo<Proceso> pListos = ColaLISTOS.getHead();
        for (int i = 0; i < ColaLISTOS.getSize(); i++) {
            // CONSIDERAR SI LA COLA ESTA VACIA
            
            if (pListos.gettInfo().getTipo().equals("CPU BOUND")) {
                nombre_proceso = pListos.gettInfo().getNombreProceso();
                cant_instrucciones = pListos.gettInfo().getCant_instrucciones();
                tipo = "CPU BOUND";
                PCB_proceso = pListos.gettInfo().getPCB_proceso();
                ciclosDuracion = pListos.gettInfo().getCiclosDuracion();
            } else {
                nombre_proceso = pListos.gettInfo().getNombreProceso();
                cant_instrucciones = pListos.gettInfo().getCant_instrucciones();
                tipo = "CPU BOUND";
                PCB_proceso = pListos.gettInfo().getPCB_proceso();
                ciclosDuracion = pListos.gettInfo().getCiclosDuracion();
                cicloGenerarExcepcion = pListos.gettInfo().getCicloGenerarExcepcion();
                cicloSatisfacerExcepcion = pListos.gettInfo().getCicloSatisfacerExcepcion();
            }
        }

//        data += "nombreProceso=" + nombre_proceso;
        Cola ColaTERMINADOS = app.getPlanificador().getColaTerminados();
        data += "[Cola de Terminados]";
        return null;
    }

}
