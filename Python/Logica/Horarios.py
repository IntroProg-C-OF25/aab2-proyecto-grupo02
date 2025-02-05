import csv

def leer_horarios(ruta_archivo):
    lineas = []
    
    try:
        with open(ruta_archivo, 'r', encoding='utf-8') as archivo:
            lector_csv = csv.reader(archivo)
            for fila in lector_csv:
                #  Eliminar espacios en blanco de cada columna
                lineas.append([columna.strip() for columna in fila])
    
    except IOError as e:
        print(f"Error al leer el archivo: {e}")
        return []
    
    return lineas

def main():
    ruta_archivo = r"C:\Users\Usuario iTC\Desktop\Projectos PV\Proyectos y Talleres\aab2-proyecto-grupo02\Sistema_Buses_Proyecto\src\Datos\Horarios.csv"
    
    # Leer horarios
    horarios = leer_horarios(ruta_archivo)
    
    # Imprimir horarios
    for fila in horarios:
        print(" | ".join(fila))

if __name__ == "__main__":
    main()