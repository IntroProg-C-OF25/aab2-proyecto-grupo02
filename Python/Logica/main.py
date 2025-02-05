from Logica import Horarios
from Logica import Paradas
from Logica import optRutas
from Logica import BuscarParada


class Principal:
    def main(self):
        llamada_horarios = Horarios()
        llamada_pa_ru = Paradas()
        llama_op_rutas = optRutas()
        buscador = BuscarParada()

        while True:
            print("=== BUSES UTPL ===")
            print("1. Horarios")
            print("2. Rutas y Paradas")
            print("3. Rutas Optimizadas")
            print("4. Buscar por parada")
            print("5. Salir")
            opcion = input("Escoja una opción: ")

            try:
                opcion = int(opcion)
                if opcion == 1:
                    llamada_horarios.mostrar_horarios()
                elif opcion == 2:
                    llamada_pa_ru.mostrar_resultados()
                elif opcion == 3:
                    llama_op_rutas.mostrar_resultados()
                elif opcion == 4:
                    nombre_parada = input("Ingrese el nombre de la parada: ")
                    buscador.buscar_por_parada(nombre_parada)
                elif opcion == 5:
                    print("Saliendo del programa...")
                    break
                else:
                    print("Opción no válida. Intente de nuevo.")
            except ValueError:
                print("Error: Entrada no válida. Intente de nuevo.")


if __name__ == "__main__":
    principal = Principal()
    principal.main()