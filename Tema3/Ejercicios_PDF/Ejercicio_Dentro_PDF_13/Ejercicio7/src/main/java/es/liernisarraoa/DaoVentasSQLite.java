package es.liernisarraoa;

import java.sql.*;

public class DaoVentasSQLite {
    private Connection conexion;
    private Integer clienteID;
    public DaoVentasSQLite(Integer clienteID){
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:/C:/DM2/ADAT/24-25_ADAT/Tema3/Ejercicios_PDF/UNIDAD2.db");
            this.clienteID = clienteID;
            visualizarDatos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void visualizarDatos() {
        String sql = "SELECT VENTAS.IDVenta, VENTAS.cantidad, PRODUCTOS.descripcion, PRODUCTOS.PVP, CLIENTES.nombre FROM VENTAS INNER JOIN PRODUCTOS ON VENTAS.IDProducto = PRODUCTOS.ID INNER JOIN CLIENTES ON VENTAS.IDCliente = CLIENTES.ID WHERE VENTAS.IDCliente = ?";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, this.clienteID);
            ResultSet resultados = pstmt.executeQuery();
            Integer contador = 0;
            Integer importeTotal = 0;
            while(resultados.next()){
                if(contador == 0){
                    System.out.println("Ventas del cliente " + resultados.getString(5));
                }
                importeTotal += resultados.getInt(2) * resultados.getInt(4);
                System.out.println("Venta: " + resultados.getInt(1) + "\n" +
                        "\t Producto: " + resultados.getString(3) + " -- PVP: " + resultados.getInt(4) + "\n" +
                        "\tCantidad: " + resultados.getInt(2) + "\n" +
                        "\t Importe: " + (resultados.getInt(2) * resultados.getInt(4)) + "\n");
                contador ++;
            }
            System.out.println("Número de total de ventas: " + contador + "\n" +
                    "Importe total: " + importeTotal + "€");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
