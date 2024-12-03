package es.liernisarraoa.Datos;

import com.db4o.ObjectContainer;
import es.liernisarraoa.ConexionDB4O.Conexion;
import es.liernisarraoa.Entidades.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InsertarDatosDB4O {
    static private ObjectContainer db;
    static public void insertarDatos(){
        List<Equipo> equipos = new ArrayList<Equipo>();
        List<Deportista> deportistas = new ArrayList<Deportista>();
        List<Evento> eventos = new ArrayList<Evento>();
        List<Deporte> deportes = new ArrayList<Deporte>();
        List<Olimpiada> olimpiadas = new ArrayList<Olimpiada>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\DM2\\ADAT\\24-25_ADAT\\Tema5\\PracitcaDB4O\\PracticaDB4O\\Ficheros\\athlete_events-cleaned.csv"))) {
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

                //Cargar datos de eventos
                Evento evento = new Evento(campos[13]);
                if(eventos.isEmpty()){
                    evento.setParticipante(deportista);
                    eventos.add(evento);
                } else {
                    //Para añadir participantes al evento
                    boolean aniadirParticipantes = false;
                    for(Evento event : eventos){
                        if(event.getNombre().equalsIgnoreCase(evento.getNombre())){
                            event.setParticipante(deportista);
                            aniadirParticipantes = true;
                            break;
                        }
                    }
                    if(!aniadirParticipantes){
                        evento.setParticipante(deportista);
                        eventos.add(evento);
                    }
                }

                //Cargar datos de Deporte a DB4O
                Deporte deporte = new Deporte(campos[12]);
                if(deportes.isEmpty()){
                    deporte.setEvento(evento);
                    deportes.add(deporte);
                } else {
                    //Para añadir eventos al deporte
                    boolean aniadirEvento = false;
                    for(Deporte depor : deportes){
                        if(depor.getNombre().equalsIgnoreCase(deporte.getNombre())){
                            depor.setEvento(evento);
                            aniadirEvento = true;
                            break;
                        }
                    }
                    if(!aniadirEvento){
                        deporte.setEvento(evento);
                        deportes.add(deporte);
                    }
                }

                //Cargar datos de Olimpiadas para DB4O
                Olimpiada olimpiada = new Olimpiada(Integer.parseInt(campos[0]), campos[8], Integer.parseInt(campos[9]), campos[10], campos[11]);
                if(olimpiadas.isEmpty()){
                    olimpiada.setDeporte(deporte);
                    olimpiadas.add(olimpiada);
                } else {
                    //Para añadir deportes a la olimpiada
                    boolean aniadirOlimpiada = false;
                    for(Olimpiada olim : olimpiadas){
                        if(olim.getNombre().equalsIgnoreCase(olimpiada.getNombre())){
                            olim.setDeporte(deporte);
                            aniadirOlimpiada = true;
                            break;
                        }
                    }
                    if(!aniadirOlimpiada){
                        olimpiada.setDeporte(deporte);
                        olimpiadas.add(olimpiada);
                    }
                }
            }
            System.out.println("Equipos insertados.");
            System.out.println("Deportistas insertados.");
            System.out.println("Eventos insertados.");
            System.out.println("Deportes insertados.");
            System.out.println("Olimpiadas insertados.");
        } catch (IOException e) {
            System.out.println("Error leyendo archivo CSV: " + e.getMessage());
        }

        db = Conexion.conectar();
        //Insertar Equipos a DB4O
        for(Equipo e : equipos){
            db.store(e);
        }

        //Insertar Deportistas a DB4O
        for(Deportista deportis : deportistas){
            db.store(deportis);
        }

        //Insertar Eventos a DB4O
        for(Evento ev : eventos){
            db.store(ev);
        }

        //Insertar Deporte a DB4O
        for(Deporte dep : deportes){
            db.store(dep);
        }

        //Insertar Olimpiadas a DB4O
        for(Olimpiada o : olimpiadas){
            db.store(o);
        }
        Conexion.desconectar();
    }
}
