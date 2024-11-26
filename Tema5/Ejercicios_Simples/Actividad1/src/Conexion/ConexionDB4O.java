package Conexion;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ConexionDB4O {

    private static ObjectContainer db = null;

    public static ObjectContainer conectar() {
        if (db == null) {
            db = Db4oEmbedded.openFile("Actividad1.db4o");
        }
        return db;
    }

    public static void desconectar() {
        if (db != null) {
            db.close();
            db = null;
        }
    }
}
