class Criptografo:
    #Funcion encriptar al codigo ASCII
    def encriptar(texto):
        textoASCII = ""
        for caracter in texto:
            textoASCII += str(ord(caracter)) + ","
        return textoASCII
    #Funcion desencriptar del codigo ASCII
    def desencriptar(texto_encriptado):
        texto_desencriptado = ""
        codigoASCII = texto_encriptado.split(",")
        for codigo in codigoASCII:
            if codigo != "":
                texto_desencriptado += chr(int(codigo))
        return texto_desencriptado

    if __name__ == "__main__":
        texto = input("Ingrese el texto a encriptar: ")
        texto_encriptado = encriptar(texto)
        print("El texto encriptado es el siguiente: " + texto_encriptado)
        texto_desencriptado = desencriptar(texto_encriptado)
        print("El texto encriptado se ve de la siguiente manera desencriptado: " + texto_desencriptado)