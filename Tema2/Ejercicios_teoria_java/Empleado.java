import java.io.IOException;
import java.io.RandomAccessFile;

public class Empleado {
    private String ID;
    private String nombre;
    private String apellido;
    private String departamento;

    public Empleado(String id, String nombre, String apellido, String departamento){
        this.ID = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
    }


    public static void main(String[] args){
        //Creamos un archivo de acceso aleatorio
        try(RandomAccessFile randomFile = new RandomAccessFile("Empleados.dat", "rw")){
            //Creamo usuarios para insertar en el fichero
            Empleado empleado1 = new Empleado("E001", "Juan", "Pérez", "Ventas");
            Empleado empleado2 = new Empleado("E002", "María", "Gómez", "Marketing");
            Empleado empleado3 = new Empleado("E003", "Pedro", "López", "Desarrollo");
            escribirEmpleado(randomFile, empleado1);
            escribirEmpleado(randomFile, empleado2);
            escribirEmpleado(randomFile, empleado3);

            //Pedimos identificador
            System.out.println("Escribe un identificador.");
            String identificador = Examen.Consola.leeString();
            randomFile.seek(0);
            while(randomFile.getFilePointer() < randomFile.length()){
                try{
                    Empleado empleado = leerEmpleado(randomFile);
                    if(empleado.ID.equals(identificador)){
                        System.out.println("Empleado encontrado:");
                        System.out.println("ID: " + empleado.ID);
                        System.out.println("Nombre: " + empleado.nombre);
                        System.out.println("Apellido: " + empleado.apellido);
                        System.out.println("Departamento: " + empleado.departamento);
                        return;
                    }
                } catch (IOException e){
                    System.out.println("Empleado no encontrado.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Para escribir un empleado en el fichero .dat
    public static void escribirEmpleado(RandomAccessFile randomFile, Empleado empleado){
        try {
            randomFile.writeUTF(empleado.ID);
            randomFile.writeUTF(empleado.nombre);
            randomFile.writeUTF(empleado.apellido);
            randomFile.writeUTF(empleado.departamento);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Leer un empleado desde el fichero .dat
    public static Empleado leerEmpleado(RandomAccessFile randomFile) throws IOException{
        Empleado empleado = null;
        String id = randomFile.readUTF();
        String nombre = randomFile.readUTF();
        String apellido = randomFile.readUTF();
        String departamento = randomFile.readUTF();
        empleado = new Empleado(id, nombre, apellido, departamento);
        return empleado;
    }

    public String getId() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDepartamento() {
        return departamento;
    }
}
