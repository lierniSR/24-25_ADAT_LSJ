package es.liernisarraoa;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        try {
        // Establecemos la conexión con la BD
            //Sale error ya que apartir de Java 8 se elimino el puente JDBC-ODBC no puede encontrar el driver
            String url = "jdbc:odbc:mysql-odbc";
            String usuario = "root";
            String contrasenia = "1234";
            Connection conexion = DriverManager.getConnection
                    (url, usuario, contrasenia);
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