package es.liernisarraoa.Dao;

import com.db4o.ObjectContainer;
import es.liernisarraoa.ConexionDB4O.Conexion;
import es.liernisarraoa.Entidades.Deporte;
import es.liernisarraoa.Entidades.Evento;

import java.util.List;

public class DaoDeporte {

    private static ObjectContainer db;

    static public void visualizarDeporte(){
        db = Conexion.conectar();
        List<Deporte> deportes = db.queryByExample(new Deporte());
        for(Deporte deporte : deportes){
            deporte.visualizar();
        }
        Conexion.desconectar();
    }
}
