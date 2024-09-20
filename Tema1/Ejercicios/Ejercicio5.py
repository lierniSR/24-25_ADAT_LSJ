#Funcion sumatorio de la lista
def sumatorio(lista):
    return sum(lista)
#Funcion media de la lista
def media(lista):
    return sumatorio(lista) / len(lista)
#Funcion numero maximo de la lista
def maximo(lista):
    return max(lista)
#Funcion numero minimo de la lista
def minimo(lista):
    return min(lista)
if __name__ == '__main__':
    lista = []
    while len(lista) < 10:
        numero = input('Introduce un numero')
        try:
            if int(numero)%2 != 0:
                lista.append(int(numero))
        except ValueError:
            print('No es un número')
    
    seleccion = input("¿Que desea hacer con la lista?\n\t1. Sumatorio\n\t2. Media\n\t3. Máximo\n\t4. Mínimo\n\n\t0. Salir\n")
    match seleccion:
        case "1":
            print("El sumatorio de la lista es " + str(sumatorio(lista)))
        case "2":
            if len(lista) != 0:
                media = sumatorio / len(lista)
                print("La media de la lista es " + str(media(lista)))
        case "3":
            print("El número maximo es " + str(maximo(lista)))
        case "4":
            print("El número minimo es " + str(minimo(lista)))
        case "0":
            exit()