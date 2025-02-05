import csv

def main():
    archivo = r"C:\Users\Usuario iTC\Desktop\Projectos PV\Proyectos y Talleres\aab2-proyecto-grupo02\Sistema_Buses_Proyecto\src\Datos\Lineasbu.csv"
    
    lineas_y_paradas = leer_archivo_csv(archivo)
    mostrar_resultados(lineas_y_paradas)


def leer_archivo_csv(archivo):
    lineas_y_paradas = []
    
    try:
        with open(archivo, 'r', encoding='utf-8') as archivo_csv:
            lector = csv.reader(archivo_csv, delimiter=';')
            
            for fila in lector:
                # Filtrar elementos vacíos
                fila_filtrada = [elemento.strip() for elemento in fila if elemento.strip()]
                
                if fila_filtrada:
                    lineas_y_paradas.append(fila_filtrada)
    
    except IOError as e:
        print(f"Error al leer el archivo CSV: {e}")
    
    return lineas_y_paradas

def mostrar_resultados(lineas_y_paradas):
    if not lineas_y_paradas:
        print("No se encontraron datos en el archivo CSV.")
        return
    
    print("=== LINEAS Y PARADAS ===")
    for linea_y_paradas in lineas_y_paradas:
        if not linea_y_paradas:
            print("\nAdvertencia: Se encontró una línea sin datos.")
            continue
        
        nombre_linea = linea_y_paradas[0]
        paradas = linea_y_paradas[1:]
        
        print(f"\nRuta: {nombre_linea}")
        print(f"Paradas: {' -> '.join(paradas)}")

