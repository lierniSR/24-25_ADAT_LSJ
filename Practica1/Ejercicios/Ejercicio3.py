import csv
import xml.etree.ElementTree as ET
import xml.sax

class XML:

    def __init__(self):
        self.menu()
    
    def menu(self):
        while True:
            print("Menu:")
            print("1. Crear fichero XML de olimpiadas")
            print("2. Crear un fichero XML de deportistas")
            print("3. Listado de olimpiadas")
            print("4. Salir")
            opcion = input("Seleccione una opción: ")
            if opcion == "1":
                self.generar_fichero_XML_olimpiadas()
            elif opcion == "2":
                self.generar_fichero_XML_deportistas()
            elif opcion == "3":
                self.listar_olimpiadas()
            elif opcion == "4":
                print("Saliendo...")
                break
            else:
                print("Opción inválida. Por favor, ingrese una opción válida.")

    def generar_fichero_XML_olimpiadas(self):
        # Leer archivo CSV de olimpiadas
        olimpiadas = []
        with open('C:/DM2/ADAT/UD2/Practica_encriptacion/olimpiadas.csv', 'r') as file:
            reader = csv.reader(file)
            next(reader)  # Skip header
            for row in reader:
                olimpiadas.append({
                    'Year': row[0],
                    'Games': row[1],
                    'Season': row[2],
                    'City': row[3]
                })

        # Ordenar olimpiadas por año y temporada
        olimpiadas.sort(key=lambda x: (x['Year'], x['Season']))

        # Crear archivo XML de olimpiadas
        root = ET.Element('olimpiadas')
        for olimpiada in olimpiadas:
            elem = ET.SubElement(root, 'olimpiada', year=olimpiada['Year'])
            ET.SubElement(elem, 'juegos').text = olimpiada['Games']
            ET.SubElement(elem, 'temporada').text = olimpiada['Season']
            ET.SubElement(elem, 'ciudad').text = olimpiada['City']

        tree = ET.ElementTree(root)
        tree.write('olimpiadas.xml')
        print("Fichero XML de olimpiadas creado correctamente")

    def generar_fichero_XML_deportistas(self):
        # Leer archivo CSV de deportistas
        deportistas = []
        with open('C:/DM2/ADAT/UD2/Practica_encriptacion/Ejercicios/athlete_events.csv', 'r') as file:
            reader = csv.reader(file)
            next(reader)  # Saltar header
            for row in reader:
                deportistas.append({
                    'Id': row[0],
                    'Name': row[1],
                    'Sex': row[2],
                    'Height': row[4],
                    'Weight': row[5],
                    'Participaciones': []
                })
            #Parte de las aprticipaciones
            participaciones = []
            for row in reader:
                participaciones.append({
                    'DeportistaId': row[0],
                    'Sport': row[12],
                    'Age': row[3],
                    'NOC': row[7],
                    'Games': row[8],
                    'City': row[11],
                    'Event': row[13],
                    'Medal': row[14]
                })

        # Asignar participaciones a deportistas
        for participacion in participaciones:
            for deportista in deportistas:
                if deportista['Id'] == participacion['DeportistaId']:
                    deportista['Participaciones'].append(participacion)

        # Crear archivo XML de deportistas
        root = ET.Element('deportistas')
        for deportista in deportistas:
            elem = ET.SubElement(root, 'deportista', id=deportista['Id'])
            ET.SubElement(elem, 'nombre').text = deportista['Name']
            ET.SubElement(elem, 'sexo').text = deportista['Sex']
            ET.SubElement(elem, 'altura').text = deportista['Height']
            ET.SubElement(elem, 'peso').text = deportista['Weight']
            participaciones_elem = ET.SubElement(elem, 'participaciones')
            for participacion in deportista['Participaciones']:
                deporte_elem = ET.SubElement(participaciones_elem, 'deporte', nombre=participacion['Sport'])
                ET.SubElement(deporte_elem, 'participacion', edad=participacion['Age']).text = ''
                ET.SubElement(deporte_elem, 'equipo', abbr=participacion['NOC']).text = ''
                ET.SubElement(deporte_elem, 'juegos').text = participacion['Games'] + ' - ' + participacion['City']
                ET.SubElement(deporte_elem, 'evento').text = participacion['Event']
                ET.SubElement(deporte_elem, 'medalla').text = participacion['Medal']

        tree = ET.ElementTree(root)
        tree.write('deportistas.xml')
        print("Fichero XML de deportistas creado correctamente")

    def listar_olimpiadas(self):
        class OlimpiadasHandler(xml.sax.ContentHandler):
            def __init__(self):
                self.CurrentData = ""
                self.Games = ""
                self.Year = ""

            def startElement(self, tag, attributes):
                self.CurrentData = tag
                if tag == "olimpiada":
                    self.Year = attributes["year"]

            def endElement(self, tag):
                if self.CurrentData == "juegos":
                    print(f"Games: {self.Games}, Year: {self.Year}")
                self.CurrentData = ""

            def characters(self, content):
                if self.CurrentData == "juegos":
                    self.Games = content

        parser = xml.sax.make_parser()
        parser.setFeature(xml.sax.handler.feature_namespaces, 0)

        Handler = OlimpiadasHandler()
        parser.setContentHandler(Handler)

        parser.parse("C:/DM2/ADAT/UD2/Practica_encriptacion/olimpiadas.xml")
        print("Listado de olimpiadas mostrado correctamente")

if __name__ == "__main__":
    FicherosXML = XML()