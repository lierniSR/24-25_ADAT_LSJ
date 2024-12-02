package es.liernisarraoa.Datos;

import com.db4o.ObjectContainer;
import es.liernisarraoa.ConexionDB4O.Conexion;
import es.liernisarraoa.Entidades.Deportista;
import es.liernisarraoa.Entidades.Equipo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InsertarDatosDB4O {
    static public void insertarDatos(){
        List<Equipo> equipos = new ArrayList<Equipo>();
        List<Deportista> deportistas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\DM2\\ADAT\\24-25_ADAT\\Tema5\\PracitcaDB4O\\PracticaDB4O\\Ficheros\\athlete_events-sort.csv"))) {
            System.out.println("Leyendo archivo CSV...");
            String linea;
            br.readLine(); // Saltar header
            while((linea = br.readLine()) != null){
                String[] campos = linea.split(",");
                //Conseguir los datos para el objeto Equipo
                Equipo equipo = new Equipo(campos[6], campos[7]);
                if(equipos.isEmpty()){
                    equipos.add(equipo);
                } else{
                    if(!equipos.contains(equipo)){
                        equipos.add(equipo);
                    }
                }
                //Conseguir los datos para el objeto Deportista
                Deportista deportista = new Deportista();
                if(campos[4].equalsIgnoreCase("NA")){
                    if(campos[5].equalsIgnoreCase("NA")){
                        deportista = new Deportista(campos[1], campos[2], Integer.parseInt(campos[3]), 0.0, 0.0, campos[14]);
                    } else {
                        deportista = new Deportista(campos[1], campos[2], Integer.parseInt(campos[3]), 0.0, Double.parseDouble(campos[5]), campos[14]);
                    }
                } else {
                    if(campos[5].equalsIgnoreCase("NA")){
                        deportista = new Deportista(campos[1], campos[2], Integer.parseInt(campos[3]), Double.parseDouble(campos[4]), 0.0, campos[14]);
                    } else {
                        deportista = new Deportista(campos[1], campos[2], Integer.parseInt(campos[3]), Double.parseDouble(campos[4]), Double.parseDouble(campos[5]), campos[14]);
                    }
                }
                if(deportistas.isEmpty()){
                    deportistas.add(deportista);
                } else{
                    if(!deportistas.contains(deportista)){
                        deportistas.add(deportista);
                    }
                }
            }
            System.out.println("Equipos insertados.");
        } catch (IOException e) {
            System.out.println("Error leyendo archivo CSV: " + e.getMessage());
        }

        ObjectContainer db = Conexion.conectar();
        //Insertar Equipos a DB4O
        for(Equipo e : equipos){
            db.store(e);
        }

        //Insertar Deportistas a DB4O
        for(Deportista deportis : deportistas){
            db.store(deportis);
        }
        Conexion.desconectar();
    }
}
