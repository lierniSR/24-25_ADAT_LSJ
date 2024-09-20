import random
import pickle

if __name__ == "__main__":
    #Parte uno del ejercicio
    lista_random = [random.uniform(-100, 100) for _ in range(1000)] #Numero aleatorios entre -100, 100 hasta 1000 numeros
    fichero_objeto = open("ListaNum.txt","wb") #Abrimos el fichero en forma de escritura
    pickle.dump(lista_random, fichero_objeto) #Con la clase pickle insertamos la lista como objeto al fichero
    fichero_objeto.close() #Cerramos fichero

    #Parte dos del ejercicio
    lista_random_comparar = [random.uniform(-100, 100) for _ in range(1000)]#Numero aleatorios entre -100, 100 hasta 1000 numeros
    fichero_leer = open("ListaNum.txt","rb") #Abrimos el archivo en forma de lectura
    lista = pickle.load(fichero_leer) #Ponemos la lista en otra lista
    fichero_leer.close() #Cerramos fichero
    lista_comparacion = []
    for i in range(len(lista)):
        lista_comparacion.append(lista[i] - lista_random_comparar[i]) #Insertamos la comparacion de cada numero
    print(lista)