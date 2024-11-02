package es.liernisarraoa;

import es.liernisarraoa.Modelo.Clientes;
import es.liernisarraoa.Modelo.Productos;
import es.liernisarraoa.Modelo.Ventas;

import java.sql.*;

public class DaoVentasMySQL {
    private Connection conexion;
    private Ventas venta;
    private Productos producto;
    private Clientes cliente;
    private String errores = "";

    public DaoVentasMySQL(Ventas venta, Productos producto, Clientes cliente){
        this.cliente = cliente;
        this.venta = venta;
        this.producto = producto;
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/","root","1234");
            comprobacionDeDatos();
            if(errores.isEmpty()){
                insertarDatos();
            } else {
                System.out.println(errores);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void insertarDatos() {
        try{
            String sql = "INSERT INTO UNIDAD2.VENTAS VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1,venta.getIDVenta());
            pstmt.setString(2,venta.getFechaVenta());
            pstmt.setInt(3, venta.getIDCliente());
            pstmt.setInt(4, venta.getIDProducto());
            pstmt.setInt(5, venta.getCantidad());
            Integer lineas = pstmt.executeUpdate();
            System.out.println(lineas + " fila insertada.");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void comprobacionDeDatos() throws SQLException {
        String sqlVentas = "SELECT IDVenta FROM UNIDAD2.VENTAS";
        PreparedStatement pstmt = conexion.prepareStatement(sqlVentas);
        ResultSet resultadosVentas = pstmt.executeQuery();
        boolean IDCliente = false;
        boolean IDProducto = false;
        while(resultadosVentas.next()){
            if(resultadosVentas.getInt(1) == venta.getIDVenta()){
                errores += "El numero de venta ya existe.\n";
            }
        }
        String sqlProducto = "SELECT ID FROM UNIDAD2.PRODUCTOS";
        pstmt = conexion.prepareStatement(sqlProducto);
        ResultSet resultadosProducto = pstmt.executeQuery();
        while(resultadosProducto.next()){
            if(resultadosProducto.getInt(1) == producto.getID()){
                IDProducto = true;
            }
        }
        String sqlCliente = "SELECT ID FROM UNIDAD2.CLIENTES";
        pstmt = conexion.prepareStatement(sqlCliente);
        ResultSet resultadoClientes = pstmt.executeQuery();
        while(resultadoClientes.next()){
            if(resultadoClientes.getInt(1) == cliente.getID()){
                IDCliente = true;
            }
        }
        if(!IDCliente){
            errores += "El ID del cliente no existe.\n";
        }
        if(!IDProducto){
            errores += "El ID del producto no existe.\n";
        }
    }
}
