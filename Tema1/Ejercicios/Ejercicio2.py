if __name__ == '__main__':
    cont = 0
    lista = []
    sumatorio = 0
    media = 0
    while cont < 10:
        numero = input('Introduce un numero')
        lista.append(numero)
        cont = cont + 1
        sumatorio = sumatorio + int(numero)

    media = sumatorio / len(lista)
    print(lista)
    print(sumatorio)
    print(media)