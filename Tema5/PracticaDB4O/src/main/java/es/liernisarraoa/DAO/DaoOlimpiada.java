package es.liernisarraoa.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import es.liernisarraoa.Entidades.Olimpiada;

import java.util.List;

public class DaoOlimpiada {

    public static void insertar(Olimpiada o, ObjectContainer db) {
        db.store(o);
    }

    public static List<Olimpiada> conseguirPorTemporada(String temporada, ObjectContainer db) {
        List<Olimpiada> olimpiadas = db.query(new Predicate<Olimpiada>() {

            @Override
            public boolean match(Olimpiada o) {
                return o.getTemporada().equals(temporada);
            }
        });
        return olimpiadas;
    }

    public static Olimpiada conseguirPorNombre(String nombre, ObjectContainer db) {
        Olimpiada dep = new Olimpiada();
        dep.setNombre(nombre);
        ObjectSet<Olimpiada> set = db.queryByExample(dep);
        return set.hasNext() ? set.next() : null;
    }

}
