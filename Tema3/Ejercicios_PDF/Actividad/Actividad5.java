import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Actividad5 {
    final static String BDPer = "C:\\DM2\\ADAT\\24-25_ADAT\\Tema3\\Ejercicios_PDF\\BDActividad5.yap";

    public static void main(String[] args) {
        ObjectContainer db= Db4oEmbedded.openFile
                (Db4oEmbedded.newConfiguration(),BDPer) ;
     // Creamos Personas
        Persona p1 = new Persona("Juan", "Guadalajara");
        Persona p2 = new Persona("Ana", "Madrid");
        Persona p3 = new Persona("Luis", "Granada");
        Persona p4 = new Persona("Pedro", "Asturias");
      //Almacenar objetos Persona en la base de datos
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);

        //Para visualizar los datos
        Persona per = new Persona(null,null);
        ObjectSet<Persona> result = db.queryByExample(per);
        if (result.size() == 0) {
            System.out.println("No existen Registros de Personas..");
        }
        else{
            System.out.println("Número de registros: "+result.size());
            while(result.hasNext()){
                Persona p = result.next();
                System.out.println("Nombre:" + p.getNombre( )+
                        ", Ciudad:" + p.getCiudad( ) );
            }
        }
        db.close();
    }
}
