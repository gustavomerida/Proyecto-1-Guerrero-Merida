/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Angelo
 */
public class CargarDatosGson(){

        public CargarDatosGson() {
            CargarDatosGson();
    }

    public void CargarDatosGson() {

        Gson gson = new Gson();

        try (Reader reader = new FileReader("simulacion.json")) {
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            // --- Sección 1: Parámetros Generales ---
            JsonObject parametrosGenerales = root.getAsJsonObject("parametrosGenerales");
            int cicloDuracion = parametrosGenerales.get("cicloDuracion").getAsInt();
            String nombreAlgoritmo = parametrosGenerales.get("nombreAlgoritmo").getAsString();

            // Actualiza los parámetros generales en tu simulación o planificador
            // Ejemplo:
            // simulacion.setCicloDuracion(cicloDuracion);
            // simulacion.setNombreAlgoritmo(nombreAlgoritmo);
            System.out.println("Ciclo de duración: " + cicloDuracion);
            System.out.println("Nombre del algoritmo: " + nombreAlgoritmo);

            // --- Sección 2: Cola de Listos ---
            JsonArray colaListosArray = root.getAsJsonArray("colaListos");

            // Vaciar o inicializar la cola de listos actual según tu implementación
            // Por ejemplo: app.getPlanificador().getColaListos().vaciar();  // Método ficticio
            for (JsonElement elemento : colaListosArray) {
                JsonObject procesoJson = elemento.getAsJsonObject();

                String nombre = procesoJson.get("nombre").getAsString();
                int cantidadInstrucciones = procesoJson.get("cantidadInstrucciones").getAsInt();
                String estado = procesoJson.get("estado").getAsString();
                int PC = procesoJson.get("PC").getAsInt();
                int MAR = procesoJson.get("MAR").getAsInt();

                // Aquí puedes determinar el tipo de proceso (por ejemplo, CPUBOUND o IOBOUND)
                // y recrear el objeto proceso correspondiente.
                // Para este ejemplo se creará un proceso genérico; adapta esta parte a tu lógica.
                // Ejemplo:
                // Proceso proceso = new Proceso(nombre, cantidadInstrucciones, estado);
                // o, si deseas recrear un ProcesoCPUBOUND:
                // PCB pcb = new PCB();
                // pcb.setAmbiente(new int[]{PC, MAR});
                // pcb.setEstado(estado);
                // AtomicInteger ciclosDuracion = new AtomicInteger(valorPorDefecto);
                // Proceso proceso = new ProcesoCPUBOUND(nombre, cantidadInstrucciones, "CPU BOUND", pcb, ciclosDuracion);
                // Por el momento, mostramos los datos leídos:
                System.out.println("Proceso leído: " + nombre + ", Instrucciones: " + cantidadInstrucciones
                        + ", Estado: " + estado + ", PC: " + PC + ", MAR: " + MAR);

                // Finalmente, agrega el proceso a la cola de listos de tu planificador.
                // Ejemplo: app.getPlanificador().getColaListos().encolar(proceso);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
}
