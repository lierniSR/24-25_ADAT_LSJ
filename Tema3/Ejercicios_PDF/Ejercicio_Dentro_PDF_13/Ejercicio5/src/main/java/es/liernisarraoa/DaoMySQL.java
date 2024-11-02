package es.liernisarraoa;

import java.sql.*;

public class DaoMySQL {
    private Connection conexion;

    public DaoMySQL(){
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/","root","1234");
            rellenarDatos();
            visualizarDatos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void visualizarDatos() {
        try {
            visualizarPoductos();
            visualizarClientes();
            visualizarVentas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void visualizarVentas() throws SQLException {
        String sql = "SELECT * FROM UNIDAD2.VENTAS";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        ResultSet resultados = pstmt.executeQuery();
        System.out.println("Ventas:");
        while(resultados.next()){
            System.out.println("\tID: " + resultados.getInt(1) + "\n"+
                    "\tFecha de venta: " + resultados.getDate(2) + "\n" +
                    "\tID Cliente: " + resultados.getInt(3) + "\n" +
                    "\tID Producto: " + resultados.getInt(4) + "\n" +
                    "\tCantidad: " + resultados.getInt(5) + "\n");
        }
    }

    private void visualizarClientes() throws SQLException {
        String sql = "SELECT * FROM UNIDAD2.CLIENTES";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        ResultSet resultados = pstmt.executeQuery();
        System.out.println("Clientes:");
        while(resultados.next()){
            System.out.println("\tID: " + resultados.getInt(1) + "\n" +
                                "\tNombre: " + resultados.getString(2) + "\n" +
                                "\tDireccion: " + resultados.getString(3) + "\n" +
                                "\tPoblacion: " + resultados.getString(4) + "\n" +
                                "\tTelefono: " + resultados.getString(5) + "\n" +
                                "\tNIF: " + resultados.getString(6));
        }
    }

    private void visualizarPoductos() throws SQLException {
        String sql = "SELECT * FROM UNIDAD2.PRODUCTOS";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        ResultSet resultados = pstmt.executeQuery();
        System.out.println("Productos:");
        while(resultados.next()){
            System.out.println("\tID: " + resultados.getInt(1) + "\n" +
                    "\tDescripcion: " + resultados.getString(2) + "\n" +
                    "\tStock actual: " + resultados.getInt(3) + "\n" +
                    "\tStock minimo: " + resultados.getInt(4) + "\n" +
                    "\tPVP: " + resultados.getInt(5) + "\n");
        }
    }

    private void rellenarDatos() {
        try {
            rellenarProductos();
            rellenarClientes();
            rellenarVentas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void rellenarVentas() throws SQLException {
        String sqlv1 = "INSERT INTO UNIDAD2.VENTAS VALUES (1, '2024-02-11', 2, 2, 3)";
        String sqlv2 = "INSERT INTO UNIDAD2.VENTAS VALUES (2, '2024-02-09', 1, 1, 2)";
        PreparedStatement pstmt = conexion.prepareStatement(sqlv1);
        int lineasInsertadas = pstmt.executeUpdate();
        System.out.println("Venta uno insertado");
        pstmt = conexion.prepareStatement(sqlv2);
        lineasInsertadas = pstmt.executeUpdate();
        System.out.println("Venta dos insertado");
    }

    private void rellenarClientes() throws SQLException {
        String sqlc1 = "INSERT INTO UNIDAD2.CLIENTES VALUES (1, 'Lierni', 'Arrasate - Mondragon', 'Mondragon', '3242524','sdfa')";
        String sqlc2 = "INSERT INTO UNIDAD2.CLIENTES VALUES (2, 'Monica', 'Eibar', 'Eibar', '3242524','sdfa')";
        PreparedStatement pstmt = conexion.prepareStatement(sqlc1);
        int lineasInsertadas = pstmt.executeUpdate();
        System.out.println("Cliente uno insertado");
        pstmt = conexion.prepareStatement(sqlc2);
        lineasInsertadas = pstmt.executeUpdate();
        System.out.println("Cliente dos insertado");
    }

    private void rellenarProductos() throws SQLException {
        String sqlp1 = "INSERT INTO UNIDAD2.PRODUCTOS VALUES (1, 'Lego - Hogwarts', 3, 2, 5)";
        String sqlp2 = "INSERT INTO UNIDAD2.PRODUCTOS VALUES (2, 'Lego - Regreso al Futuro Delorian', 5, 5, 3)";
        PreparedStatement pstmt = conexion.prepareStatement(sqlp1);
        int lineasInsertadas = pstmt.executeUpdate();
        System.out.println("Producto uno insertado");
        pstmt = conexion.prepareStatement(sqlp2);
        lineasInsertadas = pstmt.executeUpdate();
        System.out.println("Producto dos insertado");
    }
}
