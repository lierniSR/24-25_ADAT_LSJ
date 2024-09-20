def indice_palabra(palabra, letra):
    indice = 0
    while indice < len(palabra):
        if palabra[indice] == letra:
            return indice
        indice = indice + 1
    return -1
if __name__ == '__main__':
    palabra = input('Escribe una palabra')
    letra = input('Escribe una letra a buscar')
    if indice_palabra(palabra, letra) == -1:
        indice = indice_palabra(palabra, letra)
    else:
        indice = indice_palabra(palabra, letra) + 1
    print('El indice de la letra ' + letra + ' es el ' + str(indice))

    #ahora con el metodo find
    if palabra.find(letra) == -1:
        print('El indice de la letra ' + letra + ' es el ' + str(palabra.find(letra)) + ' esta parte está hecha con el metodo find')
    else:
        print('El indice de la letra ' + letra + ' es el ' + str(palabra.find(letra) + 1) + ' esta parte está hecha con el metodo find')
    
