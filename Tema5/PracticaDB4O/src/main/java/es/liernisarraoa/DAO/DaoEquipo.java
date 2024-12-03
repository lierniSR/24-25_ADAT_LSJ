package es.liernisarraoa.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import es.liernisarraoa.Entidades.Equipo;

public class DaoEquipo {

    public static void insertar(Equipo e, ObjectContainer db) {
        db.store(e);
    }

    public static Equipo conseguirPorNombre(String nombre, ObjectContainer db) {
        Equipo dep = new Equipo();
        dep.setNombre(nombre);
        ObjectSet<Equipo> set = db.queryByExample(dep);
        return set.hasNext() ? set.next() : null;
    }

}
