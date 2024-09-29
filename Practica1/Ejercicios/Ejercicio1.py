import os
import shutil

class SistemaArchivos:
    def __init__(self):
        self.menu()

    def menu(self):
        while True:
            print("Menú:")
            print("1. Crear un directorio")
            print("2. Listar un directorio")
            print("3. Copiar un archivo")
            print("4. Mover un archivo")
            print("5. Eliminar un archivo/directorio")
            print("6. Salir del programa")
            opcion = input("Introduzca una opcion: ")
            if opcion == "1":
                self.crear_directorio()
            elif opcion == "2":
                self.listar_directorio()
            elif opcion == "3":
                self.copiar_archivo()
            elif opcion == "4":
                self.mover_archivo()
            elif opcion == "5":
                self.eliminar_archivo_directorio()
            elif opcion == "6":
                print("Saliendo...")
                break
            else:
                print("Opción inválida. Por favor, introduzca una opción valida.")

    def crear_directorio(self):
        ruta = input("Introduzca la ruta del directorio padre: ")
        nombre = input("Introduzca el nombre del nuevo directorio: ")
        try:
            os.mkdir(os.path.join(ruta, nombre))
            print("Directorio creado.")
        except FileExistsError:
            print("El directorio ya existe.")
        except OSError as e:
            print(f"Error al crear el directorio: {e}")

    def listar_directorio(self):
        ruta = input("Introduzca la ruta del directorio a listar: ")
        try:
            contenido = os.listdir(ruta)
            print("Contenido del directorio:")
            for elemento in contenido:
                if os.path.isfile(os.path.join(ruta, elemento)):
                    print(f"Archivo: {elemento}")
                elif os.path.isdir(os.path.join(ruta, elemento)):
                    print(f"Directorio: {elemento}")
        except FileNotFoundError:
            print("El directorio no existe.")
        except OSError as e:
            print(f"Error al listar el directorio: {e}")

    def copiar_archivo(self):
        ruta_origen = input("Introduzca la ruta del archivo original: ")
        ruta_destino = input("Introduzca la ruta del directorio de destino: ")
        try:
            shutil.copy(ruta_origen, ruta_destino)
            print("Archivo copiado.")
        except FileNotFoundError:
            print("El archivo no existe.")
        except OSError as e:
            print(f"Error al copiar el archivo: {e}")

    def mover_archivo(self):
        ruta_origen = input("Introduzca la ruta del archivo a mover: ")
        ruta_destino = input("Introduzca la ruta del directorio de destino: ")
        try:
            shutil.move(ruta_origen, ruta_destino)
            print("Archivo movido.")
        except FileNotFoundError:
            print("El archivo no existe.")
        except OSError as e:
            print(f"Error al mover el archivo: {e}")

    def eliminar_archivo_directorio(self):
        ruta = input("Inserta la ruta del archivo o directorio a eliminar: ")
        try:
            if os.path.isfile(ruta):
                os.remove(ruta)
                print("Archivo eliminado.")
            elif os.path.isdir(ruta):
                if os.listdir(ruta):
                    print("El directorio no está vacío. No se puede eliminar.")
                else:
                    os.rmdir(ruta)
                    print("Directorio eliminado.")
        except FileNotFoundError:
            print("El archivo o directorio no existe.")
        except OSError as e:
            print(f"Error al eliminar el archivo o directorio: {e}")

if __name__ == "__main__":
    sistema_archivos = SistemaArchivos()