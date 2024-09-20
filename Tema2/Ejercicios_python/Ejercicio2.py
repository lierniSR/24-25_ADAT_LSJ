import random

def palabra_aleatoria(palabra1, palabra2):
    cont1 = 0
    cont2 = 0
    palabra_nueva = ""
    while len(palabra_nueva) < (len(palabra1) + len(palabra2)):
        if cont1 == len(palabra1):
            caracter = palabra2[cont2:] #Si cont1 es igual a la longitud se añade las letras que quedan de la otra palabra
        elif cont2 == len(palabra2):
            caracter = palabra1[cont1:] #Si cont2 es igual a la longitud se añade las letras que quedan de la otra palabra
        else: #Si no se escoge uno aleatorio
            caracter = random.choice([palabra1[cont1], palabra2[cont2]])
            if caracter == palabra1[cont1]: #Si es de la palabra 1 cont 1 se suma
                cont1 += 1
            else: #Si no se suma cont2
                cont2 += 1
        palabra_nueva += caracter #Despues de todo añadir caracter
    
    return palabra_nueva

if __name__ == "__main__":
    print(palabra_aleatoria("Hola", "Suprimo"))