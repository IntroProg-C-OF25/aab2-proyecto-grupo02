package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HorariosRutas {

    public static void main(String[] args) {

        String rutaArchivo = "C:\\Users\\Usuario iTC\\Desktop\\Projectos PV\\Proyectos y Talleres\\aab2-proyecto-grupo02\\SistemasBuses\\src\\Datos\\Horarios.csv";
        List<List<String>> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(",");
                List<String> fila = new ArrayList<>();
                for (String columna : columnas) {
                    fila.add(columna.trim());
                }
                lineas.add(fila);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        for (List<String> fila : lineas) {
            System.out.println(String.join(" | ", fila));
        }
    }
}
