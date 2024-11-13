USE UNIDAD2;
CREATE TABLE VENTAS (
	IDVenta INT NOT NULL PRIMARY KEY,
	FechaVenta DATE NOT NULL,
	IDCliente INT,
	IDProducto INT,
	Cantidad INT,
	FOREIGN KEY (IDCliente) REFERENCES CLIENTES(ID),
	FOREIGN KEY (IDProducto) REFERENCES PRODUCTOS(ID));
	
CREATE TABLE PRODUCTOS (
	ID INT NOT NULL PRIMARY KEY,
	Descripcion VARCHAR(50) NOT NULL,
	StockActual INT,
	StockMinimo INT,
	PVP INT);
	
CREATE TABLE CLIENTES (
	ID INT NOT NULL PRIMARY KEY,
	Nombre VARCHAR(50) NOT NULL,
	Direccion VARCHAR(50),
	Poblacion VARCHAR(50),
	Telefono VARCHAR(20),
	NIF VARCHAR(10));