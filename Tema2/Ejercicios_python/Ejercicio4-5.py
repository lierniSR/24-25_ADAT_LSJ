class Libreria:
    def __init__(self,NombreLibro, Autor, PrecioLibro, PrecioConDescuento, NumPaginas):
        self.NombreLibro = NombreLibro
        self.Autor = Autor
        self.PrecioLibro = PrecioLibro
        self.PrecioConDescuento = PrecioConDescuento
        self.NumPaginas = NumPaginas

    def __str__(self):
        return f"Nombre del libro: {self.NombreLibro}\nAutor: {self.Autor}\nPrecio: {self.PrecioLibro}\nPrecio despues de descuento: {self.PrecioConDescuento}\nNumero de páginas: {self.NumPaginas}"
    
    def __repr__(self):
        return f"Libreria('{self.NombreLibro}', '{self.Autor}', {self.PrecioLibro}, {self.PrecioConDescuento}, {self.NumPaginas})"

if __name__ == "__main__":
    NombreLibroInput = input("Inserte el nombre del Libro")
    AutorInput = input("Inserte el autor del Libro")
    PrecioLibroInput = float(input("Inserte el precio del Libro"))
    PrecioConDescuentoInput = float(input("Inserte el precio con descuento del Libro"))
    NumPaginasInput = int(input("Inserte el número de paginas del Libro"))
    listaLibros = []
    listaLibros.append(Libreria(NombreLibroInput, AutorInput, PrecioLibroInput, PrecioConDescuentoInput, NumPaginasInput))
    respuesta = input("Desea insertar otro Libro?")
    while respuesta == "SI" or respuesta == "si":
        NombreLibroInput = input("Inserte el nombre del Libro")
        AutorInput = input("Inserte el autor del Libro")
        PrecioLibroInput = float(input("Inserte el precio del Libro"))
        PrecioConDescuentoInput = float(input("Inserte el precio con descuento del Libro"))
        NumPaginasInput = int(input("Inserte el número de paginas del Libro"))
        listaLibros.append(Libreria(NombreLibroInput, AutorInput, PrecioLibroInput, PrecioConDescuentoInput, NumPaginasInput))
        respuesta = input("Desea insertar otro Libro?")
    
    fichero = open("ListaLibros.txt", "w")
    for objeto in listaLibros:
        fichero.write(repr(objeto) + "\n")
    fichero.close()

    #Parte dos del ejercicio
    fichero_lectura = open("ListaLibros.txt", "r")
    listaLibrosFichero = []
    for objeto in fichero_lectura:
        listaLibrosFichero.append(eval(objeto))
    fichero_lectura.close()
    listaDescuentos = []
    max = 0
    nombreLibro = ""
    for libro in listaLibrosFichero:
        cantidad = libro.PrecioLibro - libro.PrecioConDescuento
        porcentaje = (cantidad / libro.PrecioLibro) * 100
        listaDescuentos.append(porcentaje)
        for i in range(len(listaDescuentos)):
            if max < listaDescuentos[i]:
                max = listaDescuentos[i]
                nombreLibro = libro.NombreLibro
    

    print("El libro con más descuento es" + nombreLibro + " y su porcentaje es de " + str(max) + " %")