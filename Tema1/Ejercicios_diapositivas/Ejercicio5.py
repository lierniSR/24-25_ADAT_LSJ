if __name__ == '__main__':
    texto = input('Escribe un texto ')
    cont = 0
    indice = 0
    while indice < len(texto):
        if texto[indice] == 'a':
            cont = cont + 1
        indice = indice + 1
    
    porcentaje = (cont * 100) / len(texto)
    print('Contiene ' + str(porcentaje) + ' de porcentaje sobre la letra a')
    #hay que cambiarlo es porcentaje de las palaras que no contengan a