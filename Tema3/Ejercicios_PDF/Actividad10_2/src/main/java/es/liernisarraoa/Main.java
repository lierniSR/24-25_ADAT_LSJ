package es.liernisarraoa;

import java.sql.*;
import java.time.LocalDate;

public class Main {
    private static Connection conexion;
    private static String errores = "";

    public Main() throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:/C:/DM2/ADAT/24-25_ADAT/Tema3/Ejercicios_PDF/Ejercicio1.db");
    }

    public Connection getConexion(){
        return this.conexion;
    }
    public static void main(String[] args) throws SQLException {
        Connection conexion;
        try {
            //Establecemos la conexión con la BD
            Main driver = new Main();
            conexion = driver.getConexion();
            System.out.println("Inserte un numero de codigo:");
            int numEmpleado = Consola.leeInt();
            System.out.println("Inserte el apellido:");
            String apellido = Consola.leeString();
            System.out.println("Inserte el oficio:");
            String oficio = Consola.leeString();
            System.out.println("Inserte el numero del director:");
            int numDirector = Consola.leeInt();
            System.out.println("Inserte el salario:");
            int salario = Consola.leeInt();
            System.out.println("Inserte la comision:");
            int comision = Consola.leeInt();
            System.out.println("Inserte el numero de departamento:");
            int numDepartamento = Consola.leeInt();
            comprobacionDatos(numEmpleado,apellido,oficio,numDirector,salario,comision,numDepartamento);

            if(errores.isEmpty()){
                LocalDate fechaActual = LocalDate.now();
                String sql = "INSERT INTO empleados VALUES ("+numEmpleado+",'"+apellido+"','"+oficio+"',"+numDirector+",'"+fechaActual+"',"+salario+","+comision+","+numDepartamento+")";
                Statement sentencia = conexion.createStatement();
                int filas = sentencia.executeUpdate(sql);
                System.out.println("Se ha insertado el empleado");
            } else {
                System.out.println(errores);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        conexion.close(); //Cerrar conexión
    }

    private static void comprobacionDatos(int numEmpleado, String apellido, String oficio, int numDirector, int salario, int comision, int numDepartamento) {
        errores = "";
        try {
            Statement sentenciaDatos = conexion.createStatement();
            ResultSet rs = sentenciaDatos.executeQuery("SELECT emp_no, dept_no FROM empleados;");
            boolean compDep = false;
            boolean compEmp = true;
            boolean compSal = false;
            boolean compDir = false;
            while(rs.next()){
                int EMP_NO = rs.getInt(1);
                int DEPT_NO = rs.getInt(2);
                if(comprobacionDep(DEPT_NO, numDepartamento)){
                    compDep = true;
                }
                if(compromacionNumEmp(EMP_NO, numEmpleado)){
                    compEmp = false;
                }
                if(comprobacionSal(salario)){
                    compSal = true;
                }
                if(comprobacionNumDir(EMP_NO, numDirector)){
                    compDir = true;
                }
            }
            if(!compDep){
                errores += "Departamento no existe en al tabla DEPARTAMENTOS.\n";
            }
            if(!compEmp){
                errores += "Codigo de empleado ya existe.\n";
            }
            if(!compSal){
                errores += "Salario es menor a 0.\n";
            }
            if(!compDir){
                errores += "Numero de director no existe.\n";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean comprobacionNumDir(int EMP_NO, int numDirector) {
        if(EMP_NO != numDirector){
            return false;
        }
        return true;
    }

    private static boolean comprobacionSal(int salario) {
        if(salario <= 0){
            return false;
        }
        return true;
    }

    private static boolean compromacionNumEmp(int EMP_NO, int numEmpleado) {
        if(EMP_NO != numEmpleado){
            return true;
        }
        return false;
    }

    private static boolean comprobacionDep(int DEPT_NO, int numDepartamento) {
        if(DEPT_NO != numDepartamento){
            return false;
        }
        return true;
    }
}