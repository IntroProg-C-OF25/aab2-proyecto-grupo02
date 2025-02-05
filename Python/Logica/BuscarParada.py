class BuscadorParadas:
    def buscar_por_parada(self, nombre_parada):
        ruta_archivo = "C:\Users\User\Desktop\Talleres\por ahora\aab2-proyecto-grupo02\Python\Datos\Lineasbu.csv"
        lineas_y_paradas = self.leer_archivo_csv(ruta_archivo)

        if not lineas_y_paradas:
            print("No se encontraron datos en el archivo.")
            return

        print(f"=== RESULTADOS DE BÚSQUEDA PARA: {nombre_parada} ===")
        encontrado = False

        for linea in lineas_y_paradas:
            if nombre_parada in linea:
                print(f"Línea: {linea[0]}")
                encontrado = True

        if not encontrado:
            print(f"No se encontraron líneas que pasen por la parada: {nombre_parada}")

    def leer_archivo_csv(self, archivo):
        lineas_y_paradas = []
        try:
            with open(archivo, "r", encoding="utf-8") as archivo_csv:
                for linea in archivo_csv:
                    partes = linea.strip().split(";")
                    fila = [parte.strip() for parte in partes]
                    lineas_y_paradas.append(fila)
        except IOError as e:
            print(f"Error al leer el archivo: {e}")
        return lineas_y_paradas