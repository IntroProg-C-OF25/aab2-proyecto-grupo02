import sys
import os

# Agregar el directorio "Logica" al path para evitar errores de importación
sys.path.append(os.path.abspath(os.path.dirname(__file__) + "/Logica"))

# Importación de módulos corregidos
from Horarios import Horarios
from Paradas import Rutas
from optRutas import optRutas
from BuscarParada import BuscarParadas

class Principal:
    def main(self):
        llamada_horarios = Horarios()
        llamada_pa_ru = Rutas()
        llama_op_rutas = optRutas()
        buscador = BuscarParadas()

        while True:
            print("\n=== BUSES UTPL ===")
            print("1. Horarios")
            print("2. Rutas y Paradas")
            print("3. Rutas Optimizadas")
            print("4. Buscar por Parada")
            print("5. Salir")
            opcion = input("Escoja una opcion: ")

            try:
                opcion = int(opcion)
                if opcion == 1:
                    print("\n=== HORARIOS ===")
                    llamada_horarios.mostrar_horarios()
                elif opcion == 2:
                    print("\n=== RUTAS Y PARADAS ===")
                    llamada_pa_ru.mostrar_resultados()
                elif opcion == 3:
                    print("\n=== RUTAS OPTIMIZADAS ===")
                    llama_op_rutas.mostrar_resultados()
                elif opcion == 4:
                    nombre_parada = input("\nIngrese el nombre de la parada: ").strip()
                    print("\n=== BUSQUEDA DE PARADA ===")
                    buscador.buscar_por_parada(nombre_parada)
                elif opcion == 5:
                    print("\nSaliendo del programa...")
                    break
                else:
                    print("\n Opción no válida. Intente de nuevo.")
            except ValueError:
                print("\n Error: Entrada no válida. Intente de nuevo.")

if __name__ == "__main__":
    principal = Principal()
    principal.main()
