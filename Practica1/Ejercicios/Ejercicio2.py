import csv
import os
import pandas as pd

class Olimpiadas:
    def __init__(self):
        self.menu()

    def menu(self):
        while True:
            print("Menú de opciones:")
            print("1. Generar fichero csv de olimpiadas")
            print("2. Buscar deportista")
            print("3. Buscar deportistas por deporte y olimpiada")
            print("4. Añadir deportista")
            print("5. Salir del programa")
            opcion = input("Introduzca la opción deseada: ")
            if opcion == "1":
                self.generar_fichero_csv()
            elif opcion == "2":
                self.buscar_deportista()
            elif opcion == "3":
                self.buscar_deportistas_por_deporte_y_olimpiada()
            elif opcion == "4":
                self.anadir_deportista()
            elif opcion == "5":
                print("Saliendo...")
                break
            else:
                print("Opción inválida. Por favor, ingrese una opción válida.")

    def generar_fichero_csv(self):
        try:
            ruta_archivo = os.path.join(os.getcwd(), 'C:/DM2/ADAT/UD2/Practica_encriptacion/Ejercicios/athlete_events.csv')
            with open(ruta_archivo, 'r') as archivo_entrada:
                contenido = archivo_entrada.readlines()

            with open('olimpiadas.csv', 'w') as archivo_salida:
                for linea in contenido:
                    archivo_salida.write(linea)

            print("Fichero csv de olimpiadas generado con éxito.")
        except FileNotFoundError:
            print("El archivo 'athlete_events.csv' no existe.")
        except Exception as e:
            print("Ha ocurrido un error: ", str(e))

    def buscar_deportista(self):
        # Pedir al usuario una cadena de búsqueda
        nombre = input("Ingrese el nombre del deportista: ")

        # Leer el fichero csv de eventos
        eventos = pd.read_csv('C:/DM2/ADAT/UD2/Practica_encriptacion/Ejercicios/athlete_events.csv')

        # Buscar los eventos del deportista
        eventos_deportista = eventos[eventos['Name'].str.contains(nombre, case=False)]

        # Mostrar los resultados
        if eventos_deportista.empty:
            print("No se encontraron eventos para el deportista.")
        else:
            print(eventos_deportista)

    def buscar_deportistas_por_deporte_y_olimpiada(self):
        # Pedir al usuario un deporte, un año y una temporada
        deporte = input("Introduzca el deporte: ")
        año = int(input("Introduzca el año: "))
        temporada = input("Introduzca la temporada (Summer o Winter): ")

        # Leer el fichero csv de eventos
        eventos = pd.read_csv('C:/DM2/ADAT/UD2/Practica_encriptacion/Ejercicios/athlete_events.csv')

        # Buscar los eventos de los deportistas que participaron en el deporte y la olimpiada seleccionada
        eventos_deportistas = eventos[(eventos['Sport'] == deporte) & (eventos['Year'] == año) & (eventos['Season'] == temporada)]

        # Mostrar los resultados
        if eventos_deportistas.empty:
            print("No se encontraron eventos para el deporte y la olimpiada seleccionada.")
        else:
            print("Detalles de la edición Olímpica:")
            print(eventos_deportistas[['Games', 'City']].drop_duplicates())
            print("\nDeportistas participantes:")
            print(eventos_deportistas[['Name', 'Event', 'Medal']])

    def anadir_deportista(self):
        # Pedir al usuario la información del deportista
        nombre = input("Introduzca el nombre del deportista: ")
        sexo = input("Introduzca el sexo del deportista (M o F): ")
        edad = int(input("Introduzca la edad del deportista: "))
        altura = float(input("Introduzca la altura del deportista: "))
        peso = float(input("Introduzca el peso del deportista: "))
        equipo = input("Introduzca el equipo del deportista: ")
        deporte = input("Introduzca el deporte del deportista: ")
        evento = input("Introduzca el evento del deportista: ")
        medalla = input("Introduzca la medalla del deportista (Gold, Silver, Bronze o NaN): ")

        # Leer el fichero csv de eventos
        eventos = pd.read_csv('C:/DM2/ADAT/UD2/Practica_encriptacion/Ejercicios/athlete_events.csv')

        # Añadir el nuevo deportista
        nuevo_deportista = pd.DataFrame({
            'Name': [nombre],
            'Sex': [sexo],
            'Age': [edad],
            'Height': [altura],
            'Weight': [peso],
            'Team': [equipo],
            'Sport': [deporte],
            'Even': [evento],
            'Medal': [medalla]
        })

        # Guardar el resultado en el fichero csv
        eventos = pd.concat([eventos, nuevo_deportista])
        eventos = pd.concat([eventos, nuevo_deportista])
        eventos.to_csv('C:/DM2/ADAT/UD2/Practica_encriptacion/Ejercicios/athlete_events.csv', index=False)

        print("Deportista añadido.")

if __name__ == "__main__":
    olimpiadas = Olimpiadas()