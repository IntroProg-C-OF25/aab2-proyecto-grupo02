package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParadasRutas {

    public static void main(String[] args) {
        // Declaración de variables
        String archivo = "C:\\\\Users\\\\Usuario iTC\\\\Desktop\\\\Projectos PV\\\\Proyectos y Talleres\\\\aab2-proyecto-grupo02\\\\Sistema_Buses_Proyecto\\\\src\\\\Datos\\\\Lineasbu.csv";
        List<ArrayList<String>> lineasYParadas;

        // Proceso
        lineasYParadas = leerArchivoCSV(archivo);
        mostrarResultados(lineasYParadas);
    }

    // Método para leer el archivo CSV
    public static List<ArrayList<String>> leerArchivoCSV(String archivo) {
        // Declaración de variables
        List<ArrayList<String>> lineasYParadas = new ArrayList<>();
        BufferedReader br = null;

        try {
            // Inicializar el BufferedReader
            br = new BufferedReader(new FileReader(archivo));
            String linea;

            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Dividir la línea usando el delimitador ";"
                String[] partes = linea.split(";");

                // Verificar si la línea no está vacía
                if (partes.length == 0) continue;

                // Crear una lista para almacenar el nombre de la línea y sus paradas
                ArrayList<String> lineaYParadas = new ArrayList<>();

                // Agregar el nombre de la línea y las paradas a la lista
                for (String parte : partes) {
                    String valor = parte.trim();
                    if (!valor.isEmpty()) {
                        lineaYParadas.add(valor);
                    }
                }

                // Agregar la lista a la lista principal
                lineasYParadas.add(lineaYParadas);
            }
        } catch (IOException e) {
            // Mensaje de error personalizado
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar el BufferedReader en el bloque finally
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el BufferedReader: " + e.getMessage());
                }
            }
        }

        return lineasYParadas;
    }

    // Método para mostrar los resultados
    public static void mostrarResultados(List<ArrayList<String>> lineasYParadas) {
        // Verificar si la lista está vacía
        if (lineasYParadas.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo CSV.");
            return;
        }

        // Proceso
        System.out.println("=== LINEAS Y PARADAS ===");
        for (ArrayList<String> lineaYParadas : lineasYParadas) {
            // Verificar si la lista de paradas no está vacía
            if (lineaYParadas.isEmpty()) {
                System.out.println("\nAdvertencia: Se encontró una línea sin datos.");
                continue;
            }

            // El primer elemento es el nombre de la línea
            String nombreLinea = lineaYParadas.get(0);

            // Los elementos restantes son las paradas
            List<String> paradas = lineaYParadas.subList(1, lineaYParadas.size());

            // Mostrar resultados
            System.out.println("\nRuta: " + nombreLinea);
            System.out.println("Paradas: " + String.join(" -> ", paradas));
        }
    }
}
