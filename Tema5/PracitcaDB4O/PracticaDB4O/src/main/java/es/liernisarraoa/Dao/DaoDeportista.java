package es.liernisarraoa.Dao;

import com.db4o.ObjectContainer;
import es.liernisarraoa.ConexionDB4O.Conexion;
import es.liernisarraoa.Entidades.Deportista;
import es.liernisarraoa.Entidades.Equipo;

import java.util.List;

public class DaoDeportista {

    private static ObjectContainer db;

    static public void visualizarDeportista(){
        db = Conexion.conectar();
        List<Deportista> deportistas = db.queryByExample(new Deportista());
        for(Deportista deportista : deportistas){
            System.out.println(deportista.toString());
        }
        Conexion.desconectar();
    }
}
