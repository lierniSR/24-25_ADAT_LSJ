import Persona


if __name__ == "__main__":
    dni1 = Persona.Persona.generaDNI()
    persona1 = Persona.Persona("Lierni", 21, 53, 1.74, dni1, "M")

    dni2 = Persona.Persona.generaDNI()
    persona2 = Persona.Persona("Jon", 18, 53, 1.74, dni2, "H")

    dni3 = Persona.Persona.generaDNI()
    persona3 = Persona.Persona("Leire", 15, 53, 1.74, dni3, "M")

    #Persona 1
    if persona1.calcularIMC() == -1:
        print("Peso muy bajo")
    elif persona1.calcularIMC() == 0:
        print("Peso bajo")
    else:
        print("Sobrepeso")

    if persona1.es_mayor_de_edad() == True:
        print("Es mayor de edad.")
    else:
        print("No es mayor de edad.")

    print(persona1.__str__())
    #Persona 2
    if persona2.calcularIMC() == -1:
        print("Peso muy bajo")
    elif persona2.calcularIMC() == 0:
        print("Peso bajo")
    else:
        print("Sobrepeso")

    if persona2.es_mayor_de_edad() == True:
        print("Es mayor de edad.")
    else:
        print("No es mayor de edad.")

    print(persona2.__str__())
    #Persona 3
    if persona3.calcularIMC() == -1:
        print("Peso muy bajo")
    elif persona3.calcularIMC() == 0:
        print("Peso bajo")
    else:
        print("Sobrepeso")
    
    if persona3.es_mayor_de_edad() == True:
        print("Es mayor de edad.")
    else:
        print("No es mayor de edad.")

    print(persona3.__str__())