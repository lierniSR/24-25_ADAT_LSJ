def en_diccionario(elemento):
    for key in dict.keys():
        if dict[key]==elemento:
            return True        
    return False

if __name__ == '__main__':
    dict={6391595:'Ana',6391596:'Teresa',6391597:'Cristina',6391598:'Nerea'}
    elemento=input('Introduzca un elemento')
    print(en_diccionario(elemento))
