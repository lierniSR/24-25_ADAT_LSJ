if __name__ == '__main__':
    palabra = input('Escribe una palabra')
    cont = 0
    palabra_nueva = ''
    while cont < len(palabra) * 2:
        palabra_nueva = palabra_nueva + ' '
        cont = cont + 1

    palabra_nueva = palabra_nueva + palabra
    print(palabra_nueva)
    