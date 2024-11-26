package Dao;

import Conexion.ConexionDB4O;
import Entidades.Casa;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoCasa {
    static public void insertarCasas(){
        // Leer archivo CSV de batallas y ataques
        List<Casa> casas = new ArrayList<Casa>();
        Casa c;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\DM2\\ADAT\\24-25_ADAT\\Tema5\\Ejercicios_Simples\\Actividad1\\Ficheros\\Casas.csv"))) {
            System.out.println("Leyendo archivo CSV...");
            String linea;
            br.readLine(); // Saltar header
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                c = new Casa(
                        campos[0], //Nombre
                        campos[1], //Direccion
                        campos[2], //Poblacion
                        Integer.parseInt(campos[3]), //Codigo postal
                        Integer.parseInt(campos[4]) //Numero de habitaciones
                );
                casas.add(c);
            }
            System.out.println("Archivo CSV leído correctamente.");
        } catch (IOException e) {
            System.out.println("Error leyendo archivo CSV: " + e.getMessage());
        }

        //Insertar los objetos en la base de datos
        ObjectContainer db = ConexionDB4O.conectar();
        for(Casa casa : casas){
            db.store(casa);
        }

        //Cerrar conexion
        ConexionDB4O.desconectar();
    }

    static public void visualizarCasas(List<Casa> casas){
        if(casas.isEmpty()){
            System.out.println("No se han encontrado casas.");
        } else {
            for(Casa c : casas){
                System.out.println(c.toString());
            }
        }
    }

    static public void listarCasaHabita3(){
        ObjectContainer db = ConexionDB4O.conectar();
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
                return casa.getNumHabitaciones() > 3;
            }
        });

        visualizarCasas(casas);

        ConexionDB4O.desconectar();

    }
}
