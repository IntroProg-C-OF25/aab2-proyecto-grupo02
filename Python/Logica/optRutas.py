import math

class Opt_Rutas:
    def mostrar_resultados(self):
        archivo = "C:\Users\User\Desktop\Talleres\por ahora\aab2-proyecto-grupo02\Python\Datos\Lineasbu - copia.csv"
        lineas_y_paradas = self.leer_archivo_csv(archivo)

        if not lineas_y_paradas:
            print("No se encontraron datos en el archivo CSV.")
            return

        ruta_optimizada = self.optimizar_ruta(lineas_y_paradas)
        self.mostrar_resultados_finales(ruta_optimizada, lineas_y_paradas)

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

    def optimizar_ruta(self, lineas_y_paradas):
        ruta_optimizada = []
        paradas_visitadas = set()

        if not lineas_y_paradas or len(lineas_y_paradas[0]) < 2:
            print("Error: No hay suficientes datos para optimizar la ruta.")
            return ruta_optimizada

        parada_actual = lineas_y_paradas[0][1]  # Primera parada de la primera línea
        ruta_optimizada.append(parada_actual)
        paradas_visitadas.add(parada_actual)

        while len(paradas_visitadas) < len(lineas_y_paradas):
            siguiente_parada = None
            distancia_minima = float("inf")

            for linea in lineas_y_paradas:
                if len(linea) < 2:
                    continue
                parada = linea[1]

                if parada not in paradas_visitadas:
                    distancia = self.calcular_distancia(lineas_y_paradas, parada_actual, parada)
                    if distancia < distancia_minima:
                        distancia_minima = distancia
                        siguiente_parada = parada

            if siguiente_parada:
                ruta_optimizada.append(siguiente_parada)
                paradas_visitadas.add(siguiente_parada)
                parada_actual = siguiente_parada
            else:
                break

        return ruta_optimizada

    def calcular_distancia(self, lineas_y_paradas, parada1, parada2):
        radio_tierra = 6371

        datos_parada1 = self.buscar_parada(lineas_y_paradas, parada1)
        datos_parada2 = self.buscar_parada(lineas_y_paradas, parada2)

        if not datos_parada1 or not datos_parada2 or len(datos_parada1) < 4 or len(datos_parada2) < 4:
            return float("inf")

        try:
            lat1 = float(datos_parada1[2])
            lon1 = float(datos_parada1[3])
            lat2 = float(datos_parada2[2])
            lon2 = float(datos_parada2[3])

            d_lat = math.radians(lat2 - lat1)
            d_lon = math.radians(lon2 - lon1)
            a = math.sin(d_lat / 2) ** 2 + math.cos(math.radians(lat1)) * math.cos(math.radians(lat2)) * math.sin(d_lon / 2) ** 2
            c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
            return radio_tierra * c
        except ValueError:
            print("Error: Las coordenadas no son números válidos.")
            return float("inf")

    def buscar_parada(self, lineas_y_paradas, nombre_parada):
        for linea in lineas_y_paradas:
            if len(linea) > 1 and linea[1] == nombre_parada:
                return linea
        return None

    def mostrar_resultados_finales(self, ruta_optimizada, lineas_y_paradas):
        if not ruta_optimizada:
            print("No se encontraron datos para mostrar.")
            return

        print("=== RUTA OPTIMIZADA ===")
        print("+---------------------+----------------------+----------------------+")
        print("| Nombre de Parada    | Latitud              | Longitud             |")
        print("+---------------------+----------------------+----------------------+")

        for parada in ruta_optimizada:
            datos_parada = self.buscar_parada(lineas_y_paradas, parada)
            if datos_parada and len(datos_parada) > 3:
                print(f"| {datos_parada[1]:<20} | {datos_parada[2]:<20} | {datos_parada[3]:<20} |")

        print("+---------------------+----------------------+----------------------+")