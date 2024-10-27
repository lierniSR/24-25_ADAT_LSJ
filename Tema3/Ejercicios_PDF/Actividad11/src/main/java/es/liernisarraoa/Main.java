package es.liernisarraoa;

import java.sql.*;

public class Main {
    private static Connection conexion;

    public Main() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:/C:/DM2/ADAT/24-25_ADAT/Tema3/Ejercicios_PDF/Ejercicio1.db");
    }

    public Connection getConexion(){
        return this.conexion;
    }
    public static void main(String[] args) {
        Connection conexion;
        //Establecemos la conexión con la BD
        try {
            Main driver = new Main();
            conexion = driver.getConexion();
            String sqlEmpleados = "SELECT apellido, salario, oficio, dept_no FROM empleados WHERE dept_no = ?";
            PreparedStatement prst = conexion.prepareStatement(sqlEmpleados);
            System.out.println("Inserte el numero de departamento:");
            int dept_no = Consola.leeInt();
            prst.setInt(1,dept_no);
            ResultSet resultados = prst.executeQuery();
            String sqlDepartamentos = "SELECT loc FROM departamentos WHERE dept_no = ?";
            PreparedStatement prstDept = conexion.prepareStatement(sqlDepartamentos);
            prstDept.setInt(1, resultados.getInt(4));
            ResultSet resultadosDept = prstDept.executeQuery();
            String sqlMedias = "SELECT AVG(salario) AS media, COUNT(*) AS totalEmp FROM empleados";
            Statement stMedias = conexion.createStatement();
            ResultSet resultadoMedias = stMedias.executeQuery(sqlMedias);
            String sqlDeptAll = "SELECT dept_no FROM departamentos";
            Statement stDept = conexion.createStatement();
            ResultSet resulDept = stDept.executeQuery(sqlDeptAll);
            boolean deptExiste = false;
            while(resulDept.next()){
                if(resulDept.getInt(1) == dept_no){
                    deptExiste = true;
                }
            }
            if(deptExiste){
                int contador = 1;
                while(resultados.next()){
                    System.out.println("Empleado numero " + contador + "\n\n" +
                            "Apellido: " + resultados.getString(1) + "\n" +
                            "Salario: " + resultados.getInt(2) + "€\n" +
                            "Oficio: " + resultados.getString(3) + "\n" +
                            "Nombre del departamento: " + resultadosDept.getString(1) + "\n" +
                            "Media de salario de todos los empleados: " + resultadoMedias.getInt(1) + " €\n" +
                            "Total de empleados: " + resultadoMedias.getInt(2) + "\n");
                    contador++;
                }
            } else {
                System.out.println("El departamento no existe.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}