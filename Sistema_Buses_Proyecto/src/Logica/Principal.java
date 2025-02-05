package Logica;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opc;

        HorariosRutas llamadaHorarios = new HorariosRutas();
        ParadasRutas llamadaPaRu = new ParadasRutas();
        Opt_Rutas llamaOpRutas = new Opt_Rutas();
        BuscarParadas buscador = new BuscarParadas();

        while (true) {
            System.out.println("=== BUSES UTPL ===");
            System.out.println("1. Horarios");
            System.out.println("2. Rutas y Paradas");
            System.out.println("3. Rutas Optimizadas");
            System.out.println("4. Buscar por parada");
            System.out.println("5. Salir");
            System.out.print("Escoja una opcion: ");

            try {
                opc = input.nextInt();
                input.nextLine(); // Limpiar el buffer del scanner

                switch (opc) {
                    case 1:
                        llamadaHorarios.mostrarHorarios();
                        break;
                    case 2:
                        llamadaPaRu.mostrarResultados();
                        break;
                    case 3:
                        llamaOpRutas.mostrarResultados();
                        break;
                    case 4:
                        System.out.print("Ingrese el nombre de la parada: ");
                        String nombreParada = input.nextLine();
                        buscador.buscarPorParada(nombreParada);
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        input.close();
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida. Intente de nuevo.");
                input.nextLine(); // Limpiar el buffer del scanner en caso de error
            }
        }
    }
}