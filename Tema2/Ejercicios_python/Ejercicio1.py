import random

def rnd_word(fichero1, fichero2):
    fichero_leer = open(fichero1, "r") #Abrimos fichero 1 en modo lectura
    linea = fichero_leer.readline() #Leemos la primera linea
    linea_strip = linea.strip() #Utilizamos la funcion strip() para eliminar caracteres innecesarios
    palabras_a_escribir = []
    while linea_strip:
        partes_linea = linea_strip.split(" ") #Partimos la linea en cachos para ponerlo en una lista
        palabras_a_escribir.append(random.choice(partes_linea)) #Se añade a la lista una palabra aleatoria con el metodo choice de la clase random
        linea = fichero_leer.readline() #Cambiamos linea
        linea_strip = linea.strip() #Volvemos a hacer el sprit()
    fichero_leer.close() #Cerramos fichero
    fichero_escribir = open(fichero2, "w") #Abrimos fichero en modo escritura
    for palabras in palabras_a_escribir: #Hacemos un for para recorrer las palabras a escribir
        fichero_escribir.write(palabras + "\n") #Escribimos en el fichero las palabras
    fichero_escribir.close #Cerramos fichero

if __name__ == "__main__":
    rnd_word("c:\\DM2\\ADAT\\UD2\\Ejercicios_ficheros\\Ejercicio1_leer.txt","c:\\DM2\\ADAT\\UD2\\Ejercicios_ficheros\\Ejercicio1_escribir.txt")
        