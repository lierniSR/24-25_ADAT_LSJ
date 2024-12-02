package es.liernisarraoa.Dao;

import com.db4o.ObjectContainer;
import es.liernisarraoa.ConexionDB4O.Conexion;
import es.liernisarraoa.Entidades.Equipo;

import java.util.List;

public class DaoEquipo {

    private static ObjectContainer db;

    static public void visualizarEquipos(){
        db = Conexion.conectar();
        List<Equipo> equipos = db.queryByExample(new Equipo());
        for(Equipo equipo : equipos){
            System.out.println(equipo.toString());
        }
        Conexion.desconectar();
    }
}
