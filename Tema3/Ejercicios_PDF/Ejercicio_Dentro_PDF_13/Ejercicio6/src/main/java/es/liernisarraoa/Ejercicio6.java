package es.liernisarraoa;

import es.liernisarraoa.Modelo.Clientes;
import es.liernisarraoa.Modelo.Productos;
import es.liernisarraoa.Modelo.Ventas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ejercicio6 {
    public static void main(String[] args) {
        args = new String[]{"1", "3", "3", "3", "10"};
        // Obtener la fecha actual
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        Ventas venta = new Ventas(Integer.parseInt(args[1]), formattedDate, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        Productos producto = new Productos(Integer.parseInt(args[3]));
        Clientes cliente = new Clientes(Integer.parseInt(args[2]));
        DaoVentasMySQL insertarMySQL = new DaoVentasMySQL(venta, producto, cliente);
    }
}
