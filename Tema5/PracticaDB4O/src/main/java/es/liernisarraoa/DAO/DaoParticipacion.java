package es.liernisarraoa.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import es.liernisarraoa.Entidades.Deportista;
import es.liernisarraoa.Entidades.Evento;
import es.liernisarraoa.Entidades.Participacion;

import java.util.List;

public class DaoParticipacion {
    public static void insertar(Participacion p, ObjectContainer db) {
        db.store(p);
    }

    public static Participacion conseguirPorDeportistaEvento(Deportista dep, Evento e, ObjectContainer db) {
        Participacion par = new Participacion();
        par.setDeportista(dep);
        par.setEvento(e);
        ObjectSet<Participacion> set = db.queryByExample(par);
        return set.hasNext() ? set.next() : null;
    }

    public static List<Participacion> conseguirPorEvento(Evento e, ObjectContainer db) {
        List<Participacion> participaciones = db.query(new Predicate<Participacion>() {

            @Override
            public boolean match(Participacion par) {
                return par.getEvento().equals(e);
            }
        });
        return participaciones;
    }

    public static List<Participacion> conseguirPorDeportista(Deportista d, ObjectContainer db) {
        List<Participacion> participaciones = db.query(new Predicate<Participacion>() {

            @Override
            public boolean match(Participacion par) {
                return par.getDeportista().equals(d);
            }
        });
        return participaciones;
    }

    public static void actualizarMedallas(String medalla, Deportista dep, Evento e, ObjectContainer db) {
        Participacion p = conseguirPorDeportistaEvento(dep, e, db);
        p.setMedalla(medalla);
        db.store(p);
    }

    public static void eliminar(Deportista dep, Evento e, ObjectContainer db) {
        Participacion p = conseguirPorDeportistaEvento(dep, e, db);
        db.delete(p);
    }
}
