if __name__ == '__main__':
    palabra = input('Escribe una palabra')
    indice = 0
    contiene_a = True
    while indice < len(palabra):
        if palabra[indice] == 'a':
            contiene_a = False
        else:
            contiene_a = True
        indice = indice + 1
    
    print(contiene_a)
    