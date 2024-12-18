package es.liernisarraoa.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import es.liernisarraoa.Entidades.Deportista;

import java.util.List;

public class DaoDeportista {

    public static void insertar(Deportista dep, ObjectContainer db) {
        db.store(dep);
    }

    public static Deportista conseguirPorNombre(String nombre, ObjectContainer db) {
        Deportista dep = new Deportista();
        dep.setNombre(nombre);
        ObjectSet<Deportista> set = db.queryByExample(dep);
        return set.hasNext() ? set.next() : null;
    }

    public static List<Deportista> conseguirPorFragmentoNombre(String fragmentoNombre, ObjectContainer db) {
        List<Deportista> resultados = db.query(new Predicate<Deportista>() {
            @Override
            public boolean match(Deportista dep) {
                return dep.getNombre() != null && dep.getNombre().contains(fragmentoNombre);
            }
        });
        return resultados.isEmpty() ? null : resultados;
    }
}
