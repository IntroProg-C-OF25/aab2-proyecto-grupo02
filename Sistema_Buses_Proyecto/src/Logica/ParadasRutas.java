package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParadasRutas {

<<<<<<< HEAD
    public void mostrarResultados() {
        String archivo = "C:\\Users\\User\\Desktop\\Talleres\\por ahora\\aab2-proyecto-grupo02\\Sistema_Buses_Proyecto\\src\\Datos\\Lineasbu.csv";
        List<List<String>> lineasYParadas = leerArchivoCSV(archivo);
=======
    public static void main(String[] args) {
        // Declaración de variables
        String archivo = "C:\\\\Users\\\\Usuario iTC\\\\Desktop\\\\Projectos PV\\\\Proyectos y Talleres\\\\aab2-proyecto-grupo02\\\\Sistema_Buses_Proyecto\\\\src\\\\Datos\\\\Lineasbu.csv";
        List<ArrayList<String>> lineasYParadas;
>>>>>>> 7d8f7b34baef71d79a0e6dfc2669f2fac5633e3d

        if (lineasYParadas.isEmpty()) {
            System.out.println("No se encontraron datos en el archivo CSV.");
            return;
        }

        System.out.println("=== LINEAS Y PARADAS ===");
        for (List<String> lineaYParadas : lineasYParadas) {
            if (lineaYParadas.isEmpty()) {
                System.out.println("\nAdvertencia: Se encontro una linea sin datos.");
                continue;
            }

            String nombreLinea = lineaYParadas.get(0);
            List<String> paradas = lineaYParadas.subList(1, lineaYParadas.size());

            System.out.println("\nRuta: " + nombreLinea);
            System.out.println("Paradas: " + String.join(" -> ", paradas));
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
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return lineasYParadas;
    }
}
/**
 run:
=== LINEAS Y PARADAS ===

Ruta: Linea 01
Paradas: Parque Infantil Estadio -> La Salle -> Colegio Beatriz Cueva -> La Urna -> Campus UTPL

Ruta: Linea 02
Paradas: Pradera -> Av. Kigman -> Parque Infantil -> La Urna -> Campus UTPL

Ruta: Linea 03
Paradas: Pradera -> Av. Kigman -> Parque Infantil -> Estadio -> La Colegio Beatriz Cueva -> La Urna -> Campus UTPL

Ruta: Linea 07
Paradas: Ciudad Alegria -> Esteban Godoy -> Canchas Calva & Calva -> Av. Pio Jaramillo -> Tebaida Baja -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 08
Paradas: Ciudad Alegria -> Esteban Godoy -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 09
Paradas: Canchas Calva & Calva -> AV. Pio Jaramillo -> Tebaida Baja -> Coliseo Ciudad de Loja -> Lauro Guerrero -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 10
Paradas: Ciudad Alegria -> Esteban Godoy -> Cachas Calva & Calva -> Av. Pio Jaramillo -> Tebaida Baja -> Coliseo Ciudad de Loja -> Pio y Venezuela -> Lauro Guerrero -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 11
Paradas: Daniel Alvarez -> Benjamin Carrion -> Tebaida Baja -> Coliseo Ciudad de Loja -> Pio Venezuela -> Lauro Guerrero -> Puerta de Ciudad -> La Urna -> Campus

Ruta: Linea 12
Paradas: Tebaida Baja -> Coliseo Ciudad de Loja -> Pio y Venezuela -> Lauro Guerrero -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 13
Paradas: Coliseo Ciudad de Loja -> Pio y Venezuela -> Lauro Guerrero -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 14
Paradas: Lauro Guerrero -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 15
Paradas: Operadores -> Epoca -> Av. de Los Paltas -> Bomba La Llave -> Occidental -> EI Bosque -> Zona Militar -> La Urna -> Campus UTPL

Ruta: Linea 16
Paradas: Operadores -> Epoca -> Av. de Los Paltas -> Zona Militar -> ta Urna -> Campus UTPL

Ruta: Linea 17
Paradas: Bomba La Llave -> Occidental -> EI Bosque -> Zona Militar -> La Urna -> Campus UTPL

Ruta: Linea 18
Paradas: Sauces Norte -> Av. 8 de Diciembre -> Clinica Nataly -> Las Pitas -> Terminal Terrestre -> Zona Militar -> La Urna -> Campus UTPL

Ruta: Linea 19
Paradas: Hipervalle -> Calasanz -> Solca -> La Paz -> Las Pitas -> Terminal Terrestre -> Zona Militar -> La Urna -> Campus UTPL

Ruta: Linea 20
Paradas: Las Pitas -> Terminal Terrestre -> Zona Militar -> La Urna -> Campus UTPL

Ruta: Linea 21
Paradas: Operadores -> Daniel alvarez Burmeo -> Benjamin Carrion -> Tebaida Baja -> Coliseo Ciudad de Loja -> Lauro Guerrero -> Puerta de la Ciudad -> La Urna -> Campus UTPL

Ruta: Linea 22
Paradas: Epoca -> Av. de Los Paltas -> Bomba La Llave -> Occidental EI Bosque -> Zona Militar -> La Urna -> Campus UTPL

Ruta: Linea 23
Paradas: Rosales -> Pradera -> Av. Kigman -> Parque Infantil -> Estadio -> La Salle -> Colegio Beatriz Cuevas La Urna -> Campus UTPL

Ruta: Linea 25
Paradas: Rosales -> Av. Kigman -> Parque Infantil -> Estadio -> La Salle -> Colegio Beatriz Cueva -> La Urna -> Campus UTPL

Ruta: Linea 31
Paradas: Campus UTPL -> Tame -> La Salle -> Estadio -> La Urna

Ruta: Linea 32
Paradas: Campus UTPL -> Puerta de la Ciudad -> Jose A. Eguiguren -> Mercadillo -> Lauro Guerrero -> La Urna

Ruta: Linea 33
Paradas: Campus UTPL -> Predesur -> Hipervalle -> Calasanz -> Terminal Terrestre -> Zona Militar -> La Urna

Ruta: Linea 38
Paradas: Campus UTPL -> JoseA. Eguiguren -> Mercadillo -> Pio y Venezuela

Ruta: Linea 45
Paradas: Campus UTPL -> Zona Militar -> Terminal Terrestre -> Occidental -> Bomba La Llave -> Av. dg Los Paltas -> Epoca

Ruta: Linea 51
Paradas: Campus UTPL -> Solca -> La Paz -> Las Pitas -> Clinica Nataly -> Av. 8 de Diciembre -> Sauces Norte

Ruta: Linea 53
Paradas: Campus UTPL -> Tame -> La Salle -> Estadio -> Parque Infantil

Ruta: Linea 54
Paradas: Campus UTPL -> Predesur -> Hipervalle -> Clinica Nataly -> Av. 8 de Diciembre -> Sauces Norte -> Entrada Chinqui lanchi -> Colegio Eugenio Espejo -> Ilelsa

Ruta: Linea 55
Paradas: Campus UTPL -> Calasanz -> Solca -> La Paz -> Las Pitas -> Consacola (Pitas 2)

Ruta: Linea 65
Paradas: Campus UTPL -> Tame -> La Salle -> Estadio

Ruta: Linea 66
Paradas: Campus UTPL -> Tame -> La Salle -> Estadio -> Zamora Huayco

Ruta: Linea 69
Paradas: Campus UTPL -> Parque Infantil -> Pradera

Ruta: Linea 70
Paradas: Campus UTPL -> Parque Infantil -> Pradera -> Rosales

Ruta: Linea 74
Paradas: Campus UTPL -> Puerta de la Ciudad -> JoseA. Eguiguren -> Coliseo Ciudad de Loja -> Av. Pio Jaramillo -> Canchas Calva & Calva

Ruta: Linea 75
Paradas: Campus UTPL -> Esteban Godoy -> Ciudad Alegria

Ruta: Linea 76
Paradas: Campus UTPL -> Puerta de la Ciudad -> JoseA. Eguiguren -> Coliseo Ciudad de Loja -> Av. Pio Jaramillo -> Canchas Calva & Calva -> Esteban Godoy -> Ciudad Alegria

Ruta: Linea 78
Paradas: Campus UTPL: Puerta de la Ciudad -> JoseA. Eguiguren -> Mercadillo

Ruta: Linea 79
Paradas: Campus UTPL -> Pio y Venezuela -> San Pedro -> Bellavista -> Tebaida Baja

Ruta: Linea 80
Paradas: Campus UTPL -> Tebaida Baja -> Benjamin Carrion -> Daniel Alvarez -> Operadores -> Epoca

Ruta: Linea 81
Paradas: Campus UTPL -> Mercadillo -> Pio y Venezuela -> San Pedro

Ruta: Linea 82
Paradas: Campus UTPL -> Bellavista -> Tebaida Baja -> Benjamin Carrion -> Daniel Alvarez

Ruta: Linea 83
Paradas: Campus UTPL -> Pio y Venezuela -> San Pedro -> Bellavista -> Tebaida Baja -> Benjamin Carrion -> Daniel Alvarez

Ruta: Linea 84
Paradas: Campus UTPL -> Puerta de la Ciudad -> JoseA. Egui uren -> Mercadillo -> Pio y Venezuela -> San pedro -> Bellavista -> Tebaida Baja

Ruta: Linea 85
Paradas: Campus UTPL -> EI Bosque -> Occidental -> Bomba La Llave -> Av. de los Paltas -> Epoca -> Operadores

Ruta: Linea 86
Paradas: Campus UTPL -> EI Bosque -> Occidental -> Bomba La Llave -> Av. de los Paltas

Ruta: Linea 87
Paradas: Campus UTPL -> Av. de Los Paltas -> Epoca -> Operadores

Ruta: Linea 88
Paradas: Campus UTPL -> Zona Militar -> Terminal Terrestre -> EI Bosque -> Occidental -> Bomba La Llave -> Av. de Los Paltas -> Epoca -> Operadores

Ruta: Linea 89
Paradas: Campus UTPL -> Predesur -> Hipervalle -> Calasanz -> Solca -> La Paz -> Las Pitas

Ruta: Linea 93
Paradas: Campus UTPL -> Av. Cuxibamba -> Zona Militar -> Terminal Terrestre -> Clodoveo -> Entrada a Shushuhuayco

Ruta: Linea 95
Paradas: Campus UTPL -> Coliseo Ciudad de Lo ia -> AV. Pio Jaramillo -> Canchas Calva & Calva -> Esteban Godoy -> Ciudad Alegria

Ruta: Linea 98
Paradas: Campus UTPI -> Predesur -> Hipervalle -> Calasanz -> La paz -> Las Pitas -> Clinica Nataly -> Av. 8 de Diciembre -> Sauces Norte
BUILD SUCCESSFUL (total time: 0 seconds)
 */