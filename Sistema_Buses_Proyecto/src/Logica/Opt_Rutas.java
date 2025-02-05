package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Opt_Rutas {

    public void mostrarResultados() {
        String archivo = "C:\\Users\\User\\Desktop\\Talleres\\por ahora\\aab2-proyecto-grupo02\\Sistema_Buses_Proyecto\\src\\Datos\\Lineasbu - copia.csv";
        List<List<String>> lineasYParadas = leerArchivoCSV(archivo);

        if (lineasYParadas.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo CSV.");
            return;
        }

        List<String> rutaOptimizada = optimizarRuta(lineasYParadas);
        mostrarResultados(rutaOptimizada, lineasYParadas);
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
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return lineasYParadas;
    }

    public List<String> optimizarRuta(List<List<String>> lineasYParadas) {
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

            for (List<String> linea : lineasYParadas) {
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

    public double calcularDistancia(List<List<String>> lineasYParadas, String parada1, String parada2) {
        double radioTierra = 6371;

        List<String> datosParada1 = buscarParada(lineasYParadas, parada1);
        List<String> datosParada2 = buscarParada(lineasYParadas, parada2);

        if (datosParada1 == null || datosParada2 == null || datosParada1.size() < 4 || datosParada2.size() < 4) {
            return Double.MAX_VALUE;
        }

        try {
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
        } catch (NumberFormatException e) {
            System.err.println("Error: Las coordenadas no son números válidos.");
            return Double.MAX_VALUE;
        }
    }

    public List<String> buscarParada(List<List<String>> lineasYParadas, String nombreParada) {
        for (List<String> linea : lineasYParadas) {
            if (linea.size() > 1 && linea.get(1).equals(nombreParada)) {
                return linea;
            }
        }
        return null;
    }

    public void mostrarResultados(List<String> rutaOptimizada, List<List<String>> lineasYParadas) {
        if (rutaOptimizada.isEmpty()) {
            System.out.println("No se encontraron datos para mostrar.");
            return;
        }

        System.out.println("=== RUTA OPTIMIZADA ===");
        System.out.println("+---------------------+----------------------+----------------------+");
        System.out.println("| Nombre de Parada    | Latitud              | Longitud             |");
        System.out.println("+---------------------+----------------------+----------------------+");

        for (String parada : rutaOptimizada) {
            List<String> datosParada = buscarParada(lineasYParadas, parada);
            if (datosParada != null && datosParada.size() > 3) {
                System.out.format("| %-20s | %-20s | %-20s |\n",
                        datosParada.get(1), datosParada.get(2), datosParada.get(3));
            }
        }

        System.out.println("+---------------------+----------------------+----------------------+");
    }
}
/**
 * 
 */
