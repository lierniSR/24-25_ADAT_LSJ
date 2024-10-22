package es.liernisarraoa;
import java.sql.*;

public class Parte2 {
    private static String apellidoEmp;
    private static float salarioMax = 0;
    private static ResultSet resultados;

    public static void main(String[] args) {
        try {
            // Establecemos la conexión con la BD
            Connection conexion = DriverManager.getConnection
                    ("jdbc:mysql://localhost/EJEMPLO", "root", "1234");
            // Preparamos la consulta
            Statement sentencia = conexion.createStatement();
            ResultSet resul = sentencia.executeQuery("SELECT apellido, salario FROM empleados WHERE dept_no = 10");
            resultados = resul;
            // Recorremos el resultado para visualizar cada fila
            // Se hace un bucle mientras haya registros, se van visualizando
            while (resul.next()) {
                System.out.println("Empleado con maximo salario:");
                System.out.println(resul.getString(1) + " " + resul.getString(2));
            }
            resul.close();// Cerrar ResultSet
            sentencia.close();// Cerrar Statement
            conexion.close();//Cerrar conexión

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void salarioMaximo() throws SQLException {
        while (resultados.next()) {
            //if(resultados.get)

        }
        System.out.println("Empleado con maximo salario:");
        System.out.println(apellidoEmp + " " + salarioMax);
    }
}
