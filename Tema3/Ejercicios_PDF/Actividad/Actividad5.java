import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Actividad5 {
    private final static String BDPer = "C:\\DM2\\ADAT\\24-25_ADAT\\Tema3\\Ejercicios_PDF\\BDEjercicio5.yap";
    private static Persona p;
    private static Departamento d;

    public static void main(String[] args) {
        ObjectContainer db= Db4oEmbedded.openFile
                (Db4oEmbedded.newConfiguration(),BDPer) ;
     // Creamos Personas
        Persona p1 = new Persona("Juan", "Guadalajara", 1);
        Persona p2 = new Persona("Ana", "Madrid", 2);
        Persona p3 = new Persona("Luis", "Granada", 3);
        Persona p4 = new Persona("Pedro", "Asturias", 4);

        Departamento d1 = new Departamento(1, "DESARROLLADOR", "LLLLLL");
        Departamento d2 = new Departamento(2, "ADFASDF", "JJJJJJJ");
        Departamento d3 = new Departamento(3, "ASDFGTHTRH", "TTTTTT");
        Departamento d4 = new Departamento(4, "NHGTRTR", "RRRRRRR");
      //Almacenar objetos Persona en la base de datos
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);

        db.store(d1);
        db.store(d2);
        db.store(d3);
        db.store(d4);

        //Para visualizar los datos
        Persona per = new Persona(null,null, null);
        Departamento dep = new Departamento(null, null, null);
        ObjectSet<Departamento> resultadoDep = db.queryByExample(dep);
        ObjectSet<Persona> result = db.queryByExample(per);
        if (resultadoDep.isEmpty()) {
            System.out.println("No existen Registros de Personas..");
        }
        else{
            System.out.println("Número de registros Personas: "+result.size());
            System.out.println("Número de registros Departamentos: "+resultadoDep.size());
            System.out.println("Inserta el departamento: ");
            String departamento = Consola.leeString();
            while(resultadoDep.hasNext()){
                p = result.next();
                d = resultadoDep.next();
                mostrarPorDepartamento(departamento);
            }
        }
        db.close();
    }

    public static void mostrarPorDepartamento(String dep){
        if(d.getDnombre().equals(dep)){
            if(d.getDept_no() == p.getDept_no()){
                System.out.println("Nombre:" + p.getNombre( )+
                        ", Ciudad:" + p.getCiudad( ) +
                        ", Codigo dep:" + p.getDept_no() +
                        ", Nombre departamento:" + d.getDnombre());
            }
        }
    }
}
