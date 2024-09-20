if __name__ == '__main__':
    lista = []
    sumatorio = 0
    media = 0
    while len(lista) < 10:
        numero = input('Introduce un numero')
        try:
            if int(numero)%2 != 0:
                lista.append(numero)
                sumatorio = sumatorio + int(numero)
        except ValueError:
            print('No es un número')

    
    if sumatorio != 0:
        media = sumatorio / len(lista)
    
    print(lista)
    print(sumatorio)
    print(media)
    