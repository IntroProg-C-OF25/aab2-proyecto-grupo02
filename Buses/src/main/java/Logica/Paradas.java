package Logica;

// Importaciones

    import com.opencsv.*;
    import com.opencsv.exceptions.*;
    import java.io.*;
    import java.util.*;


public class Paradas {

    public static void main(String[] args) {
        // Declaración de variables
        String archivo = "C:\\Users\\User\\Desktop\\Talleres\\por ahora\\aab2-proyecto-grupo02\\aab2-proyecto-grupo02\\Buses\\src\\main\\Datos\\Lineasbu.csv";
        Map<String, List<String>> mapaLineas;

        // Proceso
        mapaLineas = leerArchivoCSV(archivo);
        mostrarResultados(mapaLineas);
    }

    // Método para leer el archivo CSV
    public static Map<String, List<String>> leerArchivoCSV(String archivo) {
        // Declaración de variables
        Map<String, List<String>> mapaLineas = new LinkedHashMap<>();
        CSVParser parser = null;
        CSVReader reader = null;

        try {
            // Configurar el parser con punto y coma como delimitador
            parser = new CSVParserBuilder()
                    .withSeparator(';') // Delimitador: punto y coma
                    .build();

            // Inicializar el lector CSV
            reader = new CSVReaderBuilder(new FileReader(archivo))
                    .withCSVParser(parser) // Asignar el parser configurado
                    .build();

            // Leer todas las líneas del archivo
            List<String[]> lineas = reader.readAll();

            // Procesar cada línea
            for (String[] partes : lineas) {
                if (partes.length == 0) continue;

                // Declaración de variables para el procesamiento de la línea
                String nombreLinea;
                List<String> paradas;

                // Extraer el nombre de la línea y las paradas
                nombreLinea = partes[0].trim();
                paradas = new ArrayList<>(Arrays.asList(partes).subList(1, partes.length));
                paradas.removeIf(String::isEmpty); // Eliminar vacíos

                // Agregar al mapa
                mapaLineas.put(nombreLinea, paradas);
            }

        } catch (IOException | CsvException e) {
            // Mensaje de error personalizado
            System.err.println("Error al procesar el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos en el bloque finally
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el lector CSV: " + e.getMessage());
            }
        }

        return mapaLineas;
    }

    // Método para mostrar los resultados
    public static void mostrarResultados(Map<String, List<String>> mapaLineas) {
        // Declaración de variables
        String linea;
        List<String> paradas;

        // Proceso
        System.out.println("=== LINEAS Y PARADAS (OpenCSV) ===");
        for (Map.Entry<String, List<String>> entry : mapaLineas.entrySet()) {
            linea = entry.getKey();
            paradas = entry.getValue();

            System.out.println("\nRuta: " + linea);
            System.out.println("Paradas: " + String.join(" -> ", paradas));
        }
    }
}
