if __name__ == '__main__':
    palabra = input('Escribe una palabra, si tiene mas de 10 palabras se mostrara.')
    cantidad_letras = len(palabra)
    if cantidad_letras > 10:
        print(palabra)
