package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parada {
    public static void main(String[] args) {
        // Ruta del archivo CSV
        String archivoParadas = "C:\\Users\\User\\Desktop\\Talleres\\aab2-proyecto-grupo02\\SistemasBuses\\src\\Datos\\Lineasbu.csv";
        
        // Lista para almacenar las líneas y sus paradas
        ArrayList<ArrayList<String>> lineasBus = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoParadas))) {
            String linea;

            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Dividir la línea por el delimitador ";" y almacenarla en una lista
                String[] paradas = linea.split(";");
                ArrayList<String> lineaParadas = new ArrayList<>();
                
                for (String parada : paradas) {
                    lineaParadas.add(parada.trim()); // Quitar espacios innecesarios
                }
                
                lineasBus.add(lineaParadas); // Agregar la lista de paradas a la lista principal
            }

            // Imprimir las paradas de cada línea
            for (int i = 0; i < lineasBus.size(); i++) {
                System.out.print("-" + " ");
                
                for (String parada : lineasBus.get(i)) {
                    System.out.print(parada + " | ");
                }
                System.out.println(); 
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}

