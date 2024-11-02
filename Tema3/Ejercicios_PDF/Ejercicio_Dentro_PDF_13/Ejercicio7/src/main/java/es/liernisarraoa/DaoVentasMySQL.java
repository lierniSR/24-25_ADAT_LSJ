package es.liernisarraoa;

import java.sql.*;

public class DaoVentasMySQL {
    private Connection conexion;
    private Integer clienteID;
    public DaoVentasMySQL(Integer clienteID){
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/","root","1234");
            this.clienteID = clienteID;
            visualizarDatos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void visualizarDatos() {
        String sql = "SELECT UNIDAD2.VENTAS.IDVenta, UNIDAD2.VENTAS.cantidad, UNIDAD2.PRODUCTOS.descripcion, UNIDAD2.PRODUCTOS.PVP, UNIDAD2.CLIENTES.nombre FROM UNIDAD2.VENTAS INNER JOIN UNIDAD2.PRODUCTOS ON UNIDAD2.VENTAS.IDProducto = UNIDAD2.PRODUCTOS.ID INNER JOIN UNIDAD2.CLIENTES ON UNIDAD2.VENTAS.IDCliente = UNIDAD2.CLIENTES.ID WHERE UNIDAD2.VENTAS.IDCliente = ?";
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
