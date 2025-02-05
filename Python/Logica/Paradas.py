
class Rutas:
    def mostrar_resultados(self):
        archivo = r"C:\Users\User\Desktop\Talleres\por ahora\aab2-proyecto-grupo02\Python\Datos\Lineasbu.csv"
        lineas_y_paradas = self.leer_archivo_csv(archivo)

        if not lineas_y_paradas:
            print("No se encontraron datos en el archivo CSV.")
            return

        print("=== LINEAS Y PARADAS ===")
        for linea_y_paradas in lineas_y_paradas:
            if not linea_y_paradas:
                print("\nAdvertencia: Se encontro una linea sin datos.")
                continue

            nombre_linea = linea_y_paradas[0]
            paradas = linea_y_paradas[1:]

            print(f"\nRuta: {nombre_linea}")
            print(f"Paradas: {' -> '.join(paradas)}")

    def leer_archivo_csv(self, archivo):
        lineas_y_paradas = []
        try:
            with open(archivo, "r", encoding="utf-8") as archivo_csv:
                for linea in archivo_csv:
                    partes = linea.strip().split(";")
                    fila = [parte.strip() for parte in partes]
                    lineas_y_paradas.append(fila)
        except IOError as e:
            print(f"Error al leer el archivo CSV: {e}")
        return lineas_y_paradas

