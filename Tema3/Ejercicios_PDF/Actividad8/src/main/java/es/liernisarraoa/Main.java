package es.liernisarraoa;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try
        {
            //Establecemos la conexión con la BD
            Connection conexion = DriverManager. getConnection("jdbc:sqlite:/C:/DM2/ADAT/24-25_ADAT/Tema3/Ejercicios_PDF/Actividad8.db");
            DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto
            ResultSet resul = null;
            String nombre = dbmd.getDatabaseProductName (); String driver = dbmd.getDriverName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName() ;
            System.out.println ("INFORMACIÓN SOBRE LA BASE DE DATOS:");
            System.out.println("*===================================");
            System.out.println ("Nombre : " + nombre );
            System.out.println ("Driver : " + driver );
            System.out.println ("URL : " + url );
            System.out.println("Usuario: " + usuario );
            //Obtener información de las tablas y vistas que hay
            resul = dbmd.getTables(null, null, null, new String[]{"TABLE"});
            while (resul.next ()) {
                String catalogo = resul.getString(1); //columna 1 que devuelve ResulSet
                String esquema = resul.getString(2); //columna 2
                String tabla = resul.getString(3); //columna 3
                String tipo = resul.getString(4); //columna 4
                System.out.println (tipo + " - Catalogo: " + catalogo +
                        ", Esquema : " + esquema + ", Nombre : " + tabla);
            }
            conexion.close(); //Cerrar conexión
        } catch (SQLException e) {e.printStackTrace();}
    } //fin de main
}//fin de la clase