package es.liernisarraoa.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import es.liernisarraoa.Entidades.Deporte;

public class DaoDeporte {

    public static void insertar(Deporte dep, ObjectContainer db) {
        db.store(dep);
    }

    public static Deporte conseguirPorNombre(String nombre, ObjectContainer db) {
        Deporte dep = new Deporte();
        dep.setNombre(nombre);
        ObjectSet<Deporte> set = db.queryByExample(dep);
        return set.hasNext() ? set.next() : null;
    }
}
