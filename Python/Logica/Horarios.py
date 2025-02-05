
class HorariosRutas:
    def mostrar_horarios(self):
        ruta_archivo = "C:\Users\User\Desktop\Talleres\por ahora\aab2-proyecto-grupo02\Python\Datos\Horarios.csv"
        lineas = self.leer_archivo(ruta_archivo)

        if not lineas:
            print("No se encontraron datos en el archivo.")
            return

        print("=== HORARIOS ===")
        for fila in lineas:
            print(" | ".join(fila))

    def leer_archivo(self, ruta_archivo):
        lineas = []
        try:
            with open(ruta_archivo, "r", encoding="utf-8") as archivo:
                for linea in archivo:
                    columnas = linea.strip().split(",")
                    fila = [columna.strip() for columna in columnas]
                    lineas.append(fila)
        except IOError as e:
            print(f"Error al leer el archivo: {e}")
        return lineas
