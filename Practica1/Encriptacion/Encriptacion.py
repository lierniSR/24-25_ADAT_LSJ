def vigenere_encriptacion(texto, clave, modo):
    # Convertir texto y clave a mayúsculas
    texto = texto.upper()
    clave = clave.upper()

    # Eliminar espacios o caracteres especiales del texto
    texto = ''.join(e for e in texto if e.isalpha())

    # Repetir clave para igualar la longitud del texto
    clave = clave * (len(texto) // len(clave)) + clave[:len(texto) % len(clave)]

    # Inicializar texto cifrado o descifrado
    resultado = ""

    # Cifrar o descifrar cada letra del texto
    for i in range(len(texto)):
        # Calcular posición de la letra en el alfabeto
        texto_pos = ord(texto[i]) - 65
        clave_pos = ord(clave[i]) - 65

        # Cifrar o descifrar la letra
        if modo == "cifrar":
            resultado_pos = (texto_pos + clave_pos) % 26
        elif modo == "descifrar":
            resultado_pos = (texto_pos - clave_pos) % 26

        # Convertir resultado a letra
        resultado += chr(resultado_pos + 65)

    return resultado

# Ejemplo de uso
texto = "ATAQUE"
clave = "CLAVE"

texto_cifrado = vigenere_encriptacion(texto, clave, "cifrar")
print("Texto cifrado:", texto_cifrado)

texto_descifrado = vigenere_encriptacion(texto_cifrado, clave, "descifrar")
print("Texto descifrado:", texto_descifrado)