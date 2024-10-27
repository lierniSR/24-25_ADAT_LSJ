package es.liernisarraoa;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexion;
        try {
            //Establecemos la conexión con la BD
            conexion = DriverManager.getConnection("jdbc:sqlite:/C:/DM2/ADAT/24-25_ADAT/Tema3/Ejercicios_PDF/Ejercicio1.db");
            Statement sentencia = conexion.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM empleados");
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();
            String nulo;
            System.out.println("Numero de columnas recuperadas: " + numColumnas);
            for (int i = 1; i <= numColumnas; i++) {
                System.out.println("Columna: " + i + ":");
                System.out.println(" Nombre : " + rsmd.getColumnName(i));
                System.out.println(" Tipo : " + rsmd.getColumnTypeName(i));
                if (rsmd.isNullable(i) == 0) nulo = "N0";
                else nulo = "SI";
                System.out.println(" Puede ser nula? : " + nulo);
                System.out.println(" Máximo ancho de la columna: " + rsmd.getColumnDisplaySize(i));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        conexion.close(); //Cerrar conexión
    }
}