package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parada {
    public static void main(String[] args) {
        Map<String, List<String>> mapaLineas = new LinkedHashMap<>(); // Mantiene orden
        String archivo = "rutas.txt";
        
        // Configura el delimitador como tabulación ('\t')
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(archivo), '\t');
            List<String[]> lineas = reader.readAll();
            
            for (String[] partes : lineas) {
                if (partes.length == 0) continue;
                
                String nombreLinea = partes[0].trim();
                List<String> paradas = new ArrayList<>(Arrays.asList(partes).subList(1, partes.length));
                paradas.removeIf(String::isEmpty); // Elimina campos vacíos
                
                mapaLineas.put(nombreLinea, paradas);
            }
            
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Mostrar resultados
        System.out.println("=== LÍNEAS Y PARADAS (OpenCSV) ===");
        mapaLineas.forEach((linea, paradas) -> {
            System.out.println("\nLínea: " + linea);
            System.out.println("Paradas: " + String.join(" → ", paradas));
        });
    }
}

