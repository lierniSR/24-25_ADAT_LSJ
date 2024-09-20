if __name__ == '__main__':
    numero = int(input('Escribe un numero.'))
    binario_al_reves = ''
    num_binario = ''
    while numero > 1:
        binario_al_reves = str(numero % 2) + binario_al_reves
        numero = int(numero/2)
    
    binario_al_reves = binario_al_reves + str(numero)
    indice = len(binario_al_reves) -1
    while indice > -1:
        num_binario = num_binario + binario_al_reves[indice]
        indice = indice - 1
    
    print(num_binario)