import xml.etree.ElementTree as ET
import pickle

class Binario:
    def __init__(self):
        self.menu()

    def menu(self):
        while True:
            print("Menu:")
            print("1. Crear fichero serializable de olimpiadas")
            print("2. Añadir edición olímpica")
            print("3. Buscar olimpiadas por sede")
            print("4. Eliminar edición olímpica")
            print("5. Salir")
            opcion = input("Seleccione una opción: ")
            if opcion == "1":
                self.generar_fichero_serializable()
            elif opcion == "2":
                self.aniadir_edicion_olimpica()
            elif opcion == "3":
                self.buscar_olimpiadas_por_sede()
            elif opcion == "4":
                self.eliminar_edicion_olimpica()
            elif opcion == "5":
                print("Saliendo...")
                break
            else:
                print("Opción inválida. Por favor, ingrese una opción válida.")

    def generar_fichero_serializable(self):
        # Leer archivo XML de olimpiadas
        arbol = ET.parse('C:/DM2/ADAT/UD2/Practica_encriptacion/olimpiadas.xml')
        root = arbol.getroot()

        # Crear lista de objetos Olimpiada
        olimpiadas = []
        for olimpiada in root.findall('olimpiada'):
            olimpiada_obj = {
                'year': olimpiada.get('anio'),
                'games': olimpiada.find('juegos').text,
                'season': olimpiada.find('temporada').text,
                'city': olimpiada.find('ciudad').text
            }
            olimpiadas.append(olimpiada_obj)

        # Serializar lista de objetos en archivo binario
        with open('olimpiadas.bin', 'wb') as file:
            pickle.dump(olimpiadas, file)

        print("Fichero serializable de olimpiadas creado correctamente")

    def aniadir_edicion_olimpica(self):
        # Pedir datos de la nueva edición olímpica
        anio = input("Ingrese el año de la edición olímpica: ")
        juego = input("Ingrese el nombre de los juegos: ")
        temporada = input("Ingrese la temporada (Summer/Winter): ")
        ciudad = input("Ingrese la ciudad sede: ")

        # Deserializar lista de objetos desde archivo binario
        try:
            with open('C:/DM2/ADAT/UD2/Practica_encriptacion/olimpiadas.bin', 'rb') as file:
                olimpiadas = pickle.load(file)
        except FileNotFoundError:
            olimpiadas = []

        # Añadir nueva edición olímpica a la lista
        olimpiada_obj = {
            'year': anio,
            'games': juego,
            'season': temporada,
            'city': ciudad
        }
        olimpiadas.append(olimpiada_obj)

        # Serializar lista de objetos en archivo binario
        with open('C:/DM2/ADAT/UD2/Practica_encriptacion/olimpiadas.bin', 'wb') as file:
            pickle.dump(olimpiadas, file)

        print("Edición olímpica añadida correctamente")

    def buscar_olimpiadas_por_sede(self):
         # Pedir palabra de búsqueda
        palabra_encontrar = input("Ingrese la palabra de búsqueda: ")

        # Deserializar lista de objetos desde archivo binario
        try:
            with open('C:/DM2/ADAT/UD2/Practica_encriptacion/olimpiadas.bin', 'rb') as file:
                olimpiadas = pickle.load(file)
        except FileNotFoundError:
            olimpiadas = []

        # Buscar olimpiadas que coincidan con la palabra de búsqueda
        encontrar_olimpiadas = [olimpiada for olimpiada in olimpiadas if palabra_encontrar.lower() in olimpiada['ciudad'].lower()]

        # Mostrar resultados
        if encontrar_olimpiadas:
            print("Olimpiadas encontradas:")
            for olimpiada in encontrar_olimpiadas:
                print(f"Año: {olimpiada['anio']}, Juegos: {olimpiada['juego']}, Temporada: {olimpiada['temporada']}, Ciudad: {olimpiada['ciudad']}")
        else:
            print("No se encontraron olimpiadas que coincidan con la palabra de búsqueda")

    def eliminar_edicion_olimpica(self):
        # Pedir año y temporada de la edición olímpica a eliminar
        anio = input("Ingrese el año de la edición olímpica a eliminar: ")
        temporada = input("Ingrese la temporada (Summer/Winter) de la edición olímpica a eliminar: ")

        # Deserializar lista de objetos desde archivo binario
        try:
            with open('olimpiadas.bin', 'rb') as file:
                olimpiadas = pickle.load(file)
        except FileNotFoundError:
            olimpiadas = []

        # Buscar y eliminar edición olímpica
        for olimpiada in olimpiadas:
            if olimpiada['anio'] == anio and olimpiada['temporada'] == temporada:
                olimpiadas.remove(olimpiada)
                print("Edición olímpica eliminada correctamente")
                break
        else:
            print("No se encontró la edición olímpica a eliminar")

        # Serializar lista de objetos en archivo binario
        with open('C:/DM2/ADAT/UD2/Practica_encriptacion/olimpiadas.bin', 'wb') as file:
            pickle.dump(olimpiadas, file)

if __name__ == "__main__":
    FicherosBinarios = Binario()
        