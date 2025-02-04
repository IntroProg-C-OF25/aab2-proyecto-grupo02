package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Opt_Rutas {

    public static void main(String[] args) {
        // Declaración de variables
        String archivo = "C:\\Users\\User\\Desktop\\Talleres\\por ahora\\aab2-proyecto-grupo02\\aab2-proyecto-grupo02\\Buses\\src\\main\\Datos\\Lineasbu - Copia.csv";
        List<ArrayList<String>> lineasYParadas;

        // Proceso
        lineasYParadas = leerArchivoCSV(archivo);

        // Verificar si se leyeron datos correctamente
        if (lineasYParadas.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo CSV.");
            return;
        }

        // Imprimir datos leídos para depuración
        System.out.println("Datos leídos del archivo CSV:");
        for (ArrayList<String> linea : lineasYParadas) {
            System.out.println(linea);
        }

        // Optimizar la ruta
        List<String> rutaOptimizada = optimizarRuta(lineasYParadas);

        // Mostrar los resultados
        mostrarResultados(rutaOptimizada, lineasYParadas);
    }

    public static List<ArrayList<String>> leerArchivoCSV(String archivo) {
        List<ArrayList<String>> lineasYParadas = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length < 2) continue; // Evitar líneas con menos de dos elementos

                ArrayList<String> lineaYParadas = new ArrayList<>();
                lineaYParadas.add(partes[0].trim());

                for (int i = 1; i < partes.length; i++) {
                    String paradaConCoordenadas = partes[i].trim();
                    int inicioCoordenadas = paradaConCoordenadas.indexOf("(");
                    int finCoordenadas = paradaConCoordenadas.indexOf(")");

                    if (inicioCoordenadas != -1 && finCoordenadas != -1) {
                        String nombreParada = paradaConCoordenadas.substring(0, inicioCoordenadas).trim();
                        String coordenadas = paradaConCoordenadas.substring(inicioCoordenadas + 1, finCoordenadas).trim();

                        String[] latLon = coordenadas.split(",");
                        if (latLon.length == 2) {
                            lineaYParadas.add(nombreParada);
                            lineaYParadas.add(latLon[0].trim());
                            lineaYParadas.add(latLon[1].trim());
                        }
                    }
                }

                if (lineaYParadas.size() > 1) { // Verificar que hay al menos una parada
                    lineasYParadas.add(lineaYParadas);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo CSV: " + e.getMessage());
            }
        }

        return lineasYParadas;
    }

    public static List<String> optimizarRuta(List<ArrayList<String>> lineasYParadas) {
        List<String> rutaOptimizada = new ArrayList<>();
        Set<String> paradasVisitadas = new HashSet<>();

        if (lineasYParadas.isEmpty() || lineasYParadas.get(0).size() < 2) {
            System.out.println("Error: No hay suficientes datos para optimizar la ruta.");
            return rutaOptimizada;
        }

        String paradaActual = lineasYParadas.get(0).get(1); // Primera parada de la primera línea
        rutaOptimizada.add(paradaActual);
        paradasVisitadas.add(paradaActual);

        while (paradasVisitadas.size() < lineasYParadas.size()) {
            String siguienteParada = null;
            double distanciaMinima = Double.MAX_VALUE;

            for (ArrayList<String> linea : lineasYParadas) {
                if (linea.size() < 2) continue;
                String parada = linea.get(1);

                if (!paradasVisitadas.contains(parada)) {
                    double distancia = calcularDistancia(lineasYParadas, paradaActual, parada);
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        siguienteParada = parada;
                    }
                }
            }

            if (siguienteParada != null) {
                rutaOptimizada.add(siguienteParada);
                paradasVisitadas.add(siguienteParada);
                paradaActual = siguienteParada;
            } else {
                break;
            }
        }

        return rutaOptimizada;
    }

    public static double calcularDistancia(List<ArrayList<String>> lineasYParadas, String parada1, String parada2) {
        double radioTierra = 6371;

        ArrayList<String> datosParada1 = buscarParada(lineasYParadas, parada1);
        ArrayList<String> datosParada2 = buscarParada(lineasYParadas, parada2);

        if (datosParada1 == null || datosParada2 == null || datosParada1.size() < 4 || datosParada2.size() < 4) {
            return Double.MAX_VALUE;
        }

        double lat1 = Double.parseDouble(datosParada1.get(2));
        double lon1 = Double.parseDouble(datosParada1.get(3));
        double lat2 = Double.parseDouble(datosParada2.get(2));
        double lon2 = Double.parseDouble(datosParada2.get(3));

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c;
    }

    public static ArrayList<String> buscarParada(List<ArrayList<String>> lineasYParadas, String nombreParada) {
        for (ArrayList<String> linea : lineasYParadas) {
            if (linea.size() > 1 && linea.get(1).equals(nombreParada)) {
                return linea;
            }
        }
        return null;
    }

    public static void mostrarResultados(List<String> rutaOptimizada, List<ArrayList<String>> lineasYParadas) {
        if (rutaOptimizada.isEmpty()) {
            System.out.println("No se encontraron datos para mostrar.");
            return;
        }

        System.out.println("=== RUTA OPTIMIZADA ===");
        System.out.println("+---------------------+----------------------+----------------------+");
        System.out.println("| Nombre de Parada    | Latitud              | Longitud             |");
        System.out.println("+---------------------+----------------------+----------------------+");

        for (String parada : rutaOptimizada) {
            ArrayList<String> datosParada = buscarParada(lineasYParadas, parada);
            if (datosParada != null && datosParada.size() > 3) {
                System.out.format("| %-20s | %-20s | %-20s |\n",
                        datosParada.get(1), datosParada.get(2), datosParada.get(3));
            }
        }

        System.out.println("+---------------------+----------------------+----------------------+");
    }
}

