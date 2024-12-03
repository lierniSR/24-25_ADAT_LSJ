package es.liernisarraoa.Dao;

import com.db4o.ObjectContainer;
import es.liernisarraoa.ConexionDB4O.Conexion;
import es.liernisarraoa.Entidades.Deportista;
import es.liernisarraoa.Entidades.Evento;

import java.util.List;

public class DaoEvento {

    private static ObjectContainer db;

    static public void visualizarEventos(){
        db = Conexion.conectar();
        List<Evento> eventos = db.queryByExample(new Evento());
        for(Evento evento : eventos){
            evento.visualizar();
        }
        Conexion.desconectar();
    }
}
