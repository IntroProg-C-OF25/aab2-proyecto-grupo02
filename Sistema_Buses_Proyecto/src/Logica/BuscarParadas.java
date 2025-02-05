package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscarParadas {

    public void buscarPorParada(String nombreParada) {
        String rutaArchivo = "C:\\Users\\User\\Desktop\\Talleres\\por ahora\\aab2-proyecto-grupo02\\Sistema_Buses_Proyecto\\src\\Datos\\Lineasbu.csv";
        List<List<String>> lineasYParadas = leerArchivoCSV(rutaArchivo);

        if (lineasYParadas.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
            return;
        }

        System.out.println("=== RESULTADOS DE BUSQUEDA PARA: " + nombreParada + " ===");
        boolean encontrado = false;

        for (List<String> linea : lineasYParadas) {
            if (linea.contains(nombreParada)) {
                System.out.println("Ruta: " + linea.get(0));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron lineas que pasen por la parada: " + nombreParada);
        }
    }

    public List<List<String>> leerArchivoCSV(String archivo) {
        List<List<String>> lineasYParadas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                List<String> fila = new ArrayList<>();
                for (String parte : partes) {
                    fila.add(parte.trim());
                }
                lineasYParadas.add(fila);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineasYParadas;
    }
}
