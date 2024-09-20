import random

class Persona:
    SEXO_H = "H" #definimos constante

    def __init__(self, nombre, edad, peso, altura, DNI, sexo=SEXO_H):#init es el constructor
        self.nombre = nombre
        self.edad = edad
        self.DNI = DNI
        self.sexo = sexo
        self.peso = peso
        self.altura = altura
    #getters
    def nombre(self):
        return self.nombre

    def edad(self):
        return self.edad

    def peso(self):
        return self.peso

    def altura(self):
        return self.altura

    def DNI(self):
        return self.DNI
   
    def sexo(self):
        return self.sexo

    #setters
    def nombre(self, nombre):
        self.nombre = nombre

    def edad(self, edad):
        self.edad = edad

    def peso(self, peso):
        self.peso = peso
 
    def altura(self, altura):
        self.altura = altura

    def sexo(self, sexo):
        self.sexo = sexo

    #Metodos
    #Metodo generar DNI
    @staticmethod
    def generaDNI():
        listaLetrasDNI = ["T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K"]
        DNI = ""
        while len(DNI) != 8:
            DNI += str(random.randint(0, 9))
        resto = int(int(DNI) % 23)
        DNI += listaLetrasDNI[resto]
        return DNI
    #Metodo que identifica si es mayor de edad o no
    def es_mayor_de_edad(self):
        esMayor = False
        if self.edad >= 18:
            esMayor = True
        return esMayor
    #Metodo toString
    def __str__(self):
        return f"Nombre: {self.nombre}, {self.edad} años.\n" \
               f"Peso y altura {self.peso} Kg y {self.altura} m.\n" \
               f"DNI: {self.DNI}. Sexo: {self.sexo}."
   #Metodo calcular el peso ideal
    def calcularIMC(self):
        calculoPeso = self.peso / (self.altura * self.altura)
        if calculoPeso < 20:
            return -1
        if calculoPeso >= 20 and calculoPeso <= 25:
            return 0
        if calculoPeso > 25:
            return 1

if __name__ == "__main__":
    dni = Persona.generaDNI()
    persona = Persona("Lierni", 21, 53, 1.74, dni, "M")
    print(persona.__str__())
    if persona.es_mayor_de_edad() == True:
        print("Es mayor de edad.")
    else:
        print("No es mayor de edad.")
    if persona.calcularIMC() == -1:
        print("Peso muy bajo")
    elif persona.calcularIMC() == 0:
        print("Peso bajo")
    else:
        print("Sobrepeso")