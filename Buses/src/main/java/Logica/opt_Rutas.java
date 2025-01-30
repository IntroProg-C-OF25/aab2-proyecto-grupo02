package Logica;

// Importaciones
import com.opencsv.*;
import com.opencsv.exceptions.*;
import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class opt_Rutas {

    // Método para leer las paradas desde un archivo CSV
    public static Map<String, List<String>> leerParadas(String archivo) {
        Map<String, List<String>> paradasMap = new HashMap<>();
        CSVParser parser = null;
        CSVReader reader = null;

        try {
            parser = new CSVParserBuilder().withSeparator(',').build();
            reader = new CSVReaderBuilder(new FileReader(archivo)).withCSVParser(parser).build();
            List<String[]> lineas = reader.readAll();

            for (int i = 1; i < lineas.size(); i++) {
                String[] datos = lineas.get(i);
                String nombre = datos[0].trim();
                double latitud = Double.parseDouble(datos[1].trim());
                double longitud = Double.parseDouble(datos[2].trim());
                paradasMap.put(nombre, Arrays.asList(String.valueOf(latitud), String.valueOf(longitud)));
            }

        } catch (IOException | CsvException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el lector CSV: " + e.getMessage());
            }
        }

        return paradasMap;
    }

    // Método para calcular la distancia entre dos paradas (fórmula de Haversine)
    public static double calcularDistancia(Map<String, List<String>> paradasMap, String parada1, String parada2) {
        double radioTierra = 6371; // Radio de la Tierra en kilómetros
        double lat1 = Double.parseDouble(paradasMap.get(parada1).get(0));
        double lon1 = Double.parseDouble(paradasMap.get(parada1).get(1));
        double lat2 = Double.parseDouble(paradasMap.get(parada2).get(0));
        double lon2 = Double.parseDouble(paradasMap.get(parada2).get(1));
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c;
    }

    // Método para calcular el tiempo de viaje entre dos paradas (en minutos)
    public static int calcularTiempoViaje(Map<String, List<String>> paradasMap, String parada1, String parada2) {
        double velocidadPromedio = 30; // km/h
        double distancia = calcularDistancia(paradasMap, parada1, parada2); // en km
        return (int) Math.round((distancia / velocidadPromedio) * 60); // en minutos
    }

    // Algoritmo para optimizar la ruta basada en horarios de salida
    public static List<String> optimizarRutaConHorarioSalida(Map<String, List<String>> paradasMap, LocalTime horarioSalida) {
        List<String> rutaOptimizada = new ArrayList<>();
        Set<String> visitadas = new HashSet<>();
        String actual = paradasMap.keySet().iterator().next(); // Primera parada
        LocalTime tiempoActual = horarioSalida;

        rutaOptimizada.add(actual);
        visitadas.add(actual);

        while (visitadas.size() < paradasMap.size()) {
            String siguienteParada = null;
            int tiempoMinimo = Integer.MAX_VALUE;

            for (String parada : paradasMap.keySet()) {
                if (!visitadas.contains(parada)) {
                    int tiempoViaje = calcularTiempoViaje(paradasMap, actual, parada);
                    if (tiempoViaje < tiempoMinimo) {
                        tiempoMinimo = tiempoViaje;
                        siguienteParada = parada;
                    }
                }
            }

            if (siguienteParada != null) {
                rutaOptimizada.add(siguienteParada);
                visitadas.add(siguienteParada);
                actual = siguienteParada;
                tiempoActual = tiempoActual.plusMinutes(tiempoMinimo);
            } else {
                break;
            }
        }

        return rutaOptimizada;
    }

    // Método para guardar las rutas en un archivo CSV (solo nombres)
    public static void guardarRutas(Map<String, List<String>> rutas, String archivoSalida) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(archivoSalida);

            for (Map.Entry<String, List<String>> entry : rutas.entrySet()) {
                writer.write(entry.getKey() + ":\n");
                for (String parada : entry.getValue()) {
                    writer.write(parada + "\n");
                }
                writer.write("\n");
            }

        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el escritor CSV: " + e.getMessage());
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        String archivoParadas = "ruta/al/archivo/paradas.csv";
        String archivoSalida = "ruta/al/archivo/rutas.csv";
        LocalTime horarioSalida = LocalTime.of(8, 0); // Horario de salida (8:00 AM)

        // Leer las paradas desde el archivo CSV
        Map<String, List<String>> paradasMap = leerParadas(archivoParadas);

        // Optimizar la ruta basada en el horario de salida
        List<String> rutaOptimizada = optimizarRutaConHorarioSalida(paradasMap, horarioSalida);

        // Almacenar las rutas en un mapa
        Map<String, List<String>> rutas = new LinkedHashMap<>();
        rutas.put("Ruta Optimizada", rutaOptimizada);

        // Guardar las rutas en un archivo CSV
        guardarRutas(rutas, archivoSalida);

        // Mostrar las rutas en la consola
        System.out.println("=== RUTA OPTIMIZADA ===");
        for (Map.Entry<String, List<String>> entry : rutas.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (String parada : entry.getValue()) {
                System.out.println(parada);
            }
        }
    }
}
//codigo no terminado 
