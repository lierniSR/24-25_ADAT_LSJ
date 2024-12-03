package es.liernisarraoa.Dao;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import es.liernisarraoa.ConexionDB4O.Conexion;
import es.liernisarraoa.Entidades.Deporte;
import es.liernisarraoa.Entidades.Evento;
import es.liernisarraoa.Entidades.Olimpiada;

import java.util.List;
import java.util.Objects;

public class DaoOlimpiada {

    private static ObjectContainer db;

    static public void visualizarOlimpaidas(){
        db = Conexion.conectar();
        List<Olimpiada> olimpiadas = db.queryByExample(new Olimpiada());
        for(Olimpiada olimpiada : olimpiadas){
            olimpiada.visualizar();
        }
        Conexion.desconectar();
    }

    static public void listarOlimpiadasTemporada(String temporada){
        db = Conexion.conectar();
        List<Olimpiada> olimpiadas = db.query(new Predicate<Olimpiada>() {
            @Override
            public boolean match(Olimpiada olimpiada) {
                return olimpiada.getTemporada().equalsIgnoreCase(temporada);
            }
        });
        for(Olimpiada olimpiada : olimpiadas){
            olimpiada.visualizar();
        }
        Conexion.desconectar();
    }

    static public void listarOlimTemporadaID(String temporada, Integer ID){
        db = Conexion.conectar();
        List<Olimpiada> olimpiadas = db.query(new Predicate<Olimpiada>() {
            @Override
            public boolean match(Olimpiada olimpiada) {
                return olimpiada.getTemporada().equalsIgnoreCase(temporada) && Objects.equals(olimpiada.getID(), ID);
            }
        });
        for(Olimpiada olimpiada : olimpiadas){
            for(Deporte deporte : olimpiada.getDeportes()){
                deporte.visualizar();
            }
        }
        Conexion.desconectar();
    }

    static public void listarOlimtempoIDNombre(String temporada, Integer ID, String nombreDeporte){
        db = Conexion.conectar();
        List<Olimpiada> olimpiadas = db.query(new Predicate<Olimpiada>() {
            @Override
            public boolean match(Olimpiada olimpiada) {
                boolean listar = false;
                if(olimpiada.getTemporada().equalsIgnoreCase(temporada) && Objects.equals(olimpiada.getID(), ID)){
                    for(Deporte deporte : olimpiada.getDeportes()){
                        if (deporte.getNombre().equalsIgnoreCase(nombreDeporte)) {
                            listar = true;
                            break;
                        }
                    }
                }
                return listar;
            }
        });
        for(Olimpiada olimpiada : olimpiadas){
            for(Deporte deporte : olimpiada.getDeportes()){
                for(Evento evento : deporte.getEventos()){
                    evento.visualizar();
                }
            }
        }
        Conexion.desconectar();
    }
}
