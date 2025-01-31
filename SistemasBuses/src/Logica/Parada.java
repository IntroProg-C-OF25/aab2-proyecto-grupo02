package Logica;

/**
 *
 * @author User
 */

import java.util.*;
import com.opencsv.*;
import com.opencsv.exceptions.*;
import java.io.*;



public class Paradas {

    public static void main(String[] args) throws CsvException {
        Map<String, List<String>> mapaLineas = new LinkedHashMap<>();
        String archivo = "C:\\Users\\User\\Desktop\\Talleres\\por ahora\\aab2-proyecto-grupo02\\aab2-proyecto-grupo02\\Buses\\src\\main\\Datos\\Lineasbu.csv";

        // Configurar el parser con tabulación como delimitador
        CSVParser parser = new CSVParserBuilder()
                .withSeparator('\t') // Delimitador: tabulación
                .build();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(archivo))
                .withCSVParser(parser) // Asignar el parser configurado
                .build()) {

            List<String[]> lineas = reader.readAll();

            for (String[] partes : lineas) {
                if (partes.length == 0) continue;

                String nombreLinea = partes[0].trim();
                List<String> paradas = new ArrayList<>(Arrays.asList(partes).subList(1, partes.length));
                paradas.removeIf(String::isEmpty); // Eliminar vacíos

                mapaLineas.put(nombreLinea, paradas);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        // Mostrar resultados
        System.out.println("=== LINEAS Y PARADAS (OpenCSV) ===");
        mapaLineas.forEach((linea, paradas) -> {
            System.out.println("\nLínea: " + linea);
            System.out.println("Paradas: " + String.join(" → ", paradas));
        });
    }
}

