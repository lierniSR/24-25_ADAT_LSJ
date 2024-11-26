package Dao;

import Conexion.ConexionDB4O;
import Entidades.Casa;
import Entidades.Ciudad;
import Entidades.Huesped;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoCasa {
    static public void insertarDatos(){
        // Leer archivo CSV de batallas y ataques
        List<Casa> casas = new ArrayList<Casa>();
        List<Ciudad> ciudades = new ArrayList<Ciudad>();
        List<Huesped> huespedes = new ArrayList<Huesped>();
        Casa c;
        Ciudad ciudad;
        Huesped huesped;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\DM2\\ADAT\\24-25_ADAT\\Tema5\\Ejercicios_Simples\\Actividad1\\Ficheros\\Casas.csv"))) {
            System.out.println("Leyendo archivo CSV...");
            String linea;
            br.readLine(); // Saltar header
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                ciudad = new Ciudad(campos[3], campos[4]);
                if(campos[6].isEmpty()){
                    huesped = new Huesped(campos[5], 0.0, campos[7]);
                } else {
                    huesped = new Huesped(campos[5], Double.parseDouble(campos[6]), campos[7]);
                }
                if(campos[1].isEmpty()){
                    if(campos[2].isEmpty()){
                        c = new Casa(
                                campos[0], //Direccion
                                0.0, //numero habitaciones
                                0.0, //numero personas
                                ciudad//Ciudad
                        );
                    } else {
                        c = new Casa(
                                campos[0], //Direccion
                                0.0, //numero habitaciones
                                Double.parseDouble(campos[2]), //numero personas
                                ciudad//Ciudad
                        );
                    }
                } else {
                    if(campos[2].isEmpty()){
                        c = new Casa(campos[0], //Direccion
                                Double.parseDouble(campos[1]), //numero habitaciones
                                0.0, //numero personas
                                ciudad//Ciudad
                                );
                    } else {
                        c = new Casa(
                                campos[0], //Direccion
                                Double.parseDouble(campos[1]), //numero habitaciones
                                Double.parseDouble(campos[2]), //numero personas
                                ciudad//Ciudad
                        );
                    }
                }
                //Esta parte es para que añada un huesped si es igual a la casa
                boolean update = false;
                for (Casa casa : casas) {
                    if (casa.equals(c)) {
                        casa.setHuesped(huesped);
                        update = true;
                    }
                }
                if(!update){
                    casas.add(c);
                }
                ciudades.add(ciudad);
                huespedes.add(huesped);
            }
            System.out.println("Archivo CSV leído correctamente.");
        } catch (IOException e) {
            System.out.println("Error leyendo archivo CSV: " + e.getMessage());
        }

        //Insertar los objetos en la base de datos
        ObjectContainer db = ConexionDB4O.conectar();
        for(Ciudad ciudad1 : ciudades){
            db.store(ciudad1);
        }

        for(Huesped huesped1 : huespedes){
            db.store(huesped1);
        }

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
                c.visualizar();
            }
        }
    }

    static public void visualizarHuesped(){
        ObjectContainer db = ConexionDB4O.conectar();
        List<Casa> casas = db.queryByExample(new Casa());
        if(casas.isEmpty()){
            System.out.println("No se han encontrado casas.");
        } else {
            for(Casa c : casas){
                c.visualizar();
            }
        }
        ConexionDB4O.desconectar();
    }

    static public void listarCasas(){
        ObjectContainer db = ConexionDB4O.conectar();
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
                return casa.getNumHabitaciones() >= 5;
            }
        });

        visualizarCasas(casas);

        ConexionDB4O.desconectar();

    }

    static public void actualizarHabitaciones(){
        ObjectContainer db = ConexionDB4O.conectar();
        List<Casa> casas = db.query(new Predicate<Casa>() {
            @Override
            public boolean match(Casa casa) {
                return casa.getNumHabitaciones() >= 5;
            }
        });
        for(Casa casa : casas){
            casa.setNumHabitaciones(10.0);
            db.store(casa);
        }
        ConexionDB4O.desconectar();
    }
}
