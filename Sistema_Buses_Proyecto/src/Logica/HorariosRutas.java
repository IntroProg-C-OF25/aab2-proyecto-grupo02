package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HorariosRutas {

    public void mostrarHorarios() {
        String rutaArchivo = "C:\\Users\\User\\Desktop\\Talleres\\por ahora\\aab2-proyecto-grupo02\\Sistema_Buses_Proyecto\\src\\Datos\\Horarios.csv";
        List<List<String>> lineas = leerArchivo(rutaArchivo);

        if (lineas.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo.");
            return;
        }

        System.out.println("=== HORARIOS ===");
        for (List<String> fila : lineas) {
            System.out.println(String.join(" | ", fila));
        }
    }

    public List<List<String>> leerArchivo(String rutaArchivo) {
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
        }
        return lineas;
    }
}
/**
 run:
6:20 AM | Linea 22
6:25 AM | Linea 21 | Linea 08
6:30 AM | Linea 01 | Linea 19 | Linea 13 | Linea 02
6:35 AM | Linea 20
6:55 AM | Linea 10 | Linea 15
7:00 AM | Linea 03
7:05 AM | Linea 20
7:25 AM | Linea 15 | Linea 23 | inea 07 | Linea 18;
7:30 AM | Linea 19 | Linea 13
8:25 AM | Linea 10
8:30 AM | Linea 03 | Linea 19
9:30 AM | Linea 33 | Linea 32 | Linea 31
9:35 AM | Linea 14
10:30 AM | Linea 33 | Linea 32 | Linea 31
11:00 AM | Linea 32
11:30 AM | Linea 33 | Linea 32 | Linea 31
12:00 PM | Linea 33 | Linea 32 | Linea 31
12:30 PM | Linea 83 | Linea 70 | Linea 98 | Linea 31 | Linea 76 | Linea 45
1:00 PM | Linea 95 | Linea 70 | Linea 65 | Linea 98 | Linea 38 | Linea 80 | Linea 45
1:30 PM | Linea 03 | Linea 12 | Linea 20
2:25 PM | Linea 10 | Linea 18 | Linea 16
2:30 PM | Linea 03 | Linea17 | Linea 19 | Linea 13 | Linea 25 | Linea 11 | Linea 14
3:30 PM | Linea 33
3:35 PM | Linea 12
4:30 PM | Linea 33 | Linea 32 | Linea 31
5:00 PM | Linea 32
5:05 PM | Linea 14
6:00 PM | Linea 85 | Linea 83 | Linea 33 | Linea 88 | Linea 95 | Linea 51 | Linea 78 | Linea 53 | Linea 70 | Linea 31
6:30 PM | Linea 78
6:35 PM | Linea 14
7:00 PM | Linea 69 | Linea 84 | Linea 66 | Linea 89 | Linea 93
8:00 PM | Linea 79 | Linea 78 | Linea 89 | Linea 93
8:10 PM | Linea 69 | Linea 66
9:00 PM | Linea 54 | Linea 55 | Linea 85 | Linea 81 | Linea 83 | Linea 95 | Linea 78 | Linea 70 | Linea 66 | Linea 93
BUILD SUCCESSFUL (total time: 0 seconds)
 */