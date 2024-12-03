package es.liernisarraoa.BBDD;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DB4O {

    private static ObjectContainer db;

    public static ObjectContainer getConnection() {
        if (db == null) {
            try {
                // Configurar y abrir conexión a la base de datos db4o
                db = Db4oEmbedded.openFile("PracticaDB4O.db4o");
                System.out.println("Conexión a fichero DB4O creada");
            } catch (Exception e) {
                throw new RuntimeException("Error al conectar con db4o: " + e.getMessage());
            }
        }
        return db;
    }

    public static void closeConnection() {
        if (db != null) {
            db.close();
            System.out.println("Conexión a db4o cerrada");
        }
    }
}
