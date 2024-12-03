package es.liernisarraoa.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import es.liernisarraoa.Entidades.Deporte;
import es.liernisarraoa.Entidades.Evento;
import es.liernisarraoa.Entidades.Olimpiada;

import java.util.List;

public class DaoEvento {

    public static void insertar(Evento e, ObjectContainer db) {
        db.store(e);
    }

    public static Evento conseguirPorNombre(String nombre, ObjectContainer db) {
        Evento e = new Evento();
        e.setNombre(nombre);
        ObjectSet<Evento> set = db.queryByExample(e);
        return set.hasNext() ? set.next() : null;
    }

    public static List<Evento> conseguirPorOlimpiada(Olimpiada o, ObjectContainer db) {
        List<Evento> eventos = db.query(new Predicate<Evento>() {

            @Override
            public boolean match(Evento e) {
                return e.getOlimpiada().equals(o);
            }
        });
        return eventos;

    }

    public static List<Evento> conseguirPorOlimpiadaDeporte(Olimpiada o, Deporte d, ObjectContainer db) {
        List<Evento> eventos = db.query(new Predicate<Evento>() {

            @Override
            public boolean match(Evento e) {
                return e.getOlimpiada().equals(o) && e.getDeporte().equals(d);
            }
        });
        return eventos;

    }
}
