package es.liernisarraoa;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try {
        // Establecemos la conexión con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:sqlite:/data/ejercicio1.db");
        // Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT apellido, oficio, salario FROM empleados WHERE dept_no = 10");
        // Recorremos el resultado para visualizar cada fila
        // Se hace un bucle mientras haya registros, se van visualizando
            while (resul.next()) {
                System.out.println(resul.getString(1) + " " + resul.getString(2) + " " + resul.getFloat(3));
            }
            resul.close();// Cerrar ResultSet
            sentencia.close();// Cerrar Statement
            conexion.close();//Cerrar conexión

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//fin de main
}//fin de la clase