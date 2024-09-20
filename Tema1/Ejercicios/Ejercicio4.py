if __name__ == '__main__':
    lista = []
    sumatorio = 0
    media = 0
    while len(lista) < 10:
        numero = input('Introduce un numero')
        try:
            if int(numero)%2 != 0:
                lista.append(int(numero))
                sumatorio = sumatorio + int(numero)
        except ValueError:
            print('No es un número')
    
    seleccion = input("¿Que desea hacer con la lista?\n\t1. Sumatorio\n\t2. Media\n\t3. Máximo\n\t4. Mínimo\n\n\t0. Salir\n")
    match seleccion:
        case "1":
            print("El sumatorio de la lista es " + str(sumatorio))
        case "2":
            if len(lista) != 0:
                media = sumatorio / len(lista)
                print("La media de la lista es " + str(media))
        case "3":
            print(max(lista))
        case "4":
            print("El número minimo es " + str(min(lista)))
        case "0":
            exit()