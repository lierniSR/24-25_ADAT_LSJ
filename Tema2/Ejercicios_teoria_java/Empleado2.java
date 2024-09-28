import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Empleado2 {
    private String ID;
    private String nombre;
    private String apellido;
    private String departamento;
    private String salario;

    public Empleado2(String id, String nombre, String apellido, String departamento, String salario){
        this.ID = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }

    public static void main(String[] args){
        //Creamos un archivo de acceso aleatorio
        try(RandomAccessFile randomFile = new RandomAccessFile("Empleados2.dat", "rw")){
            //Creamo usuarios para insertar en el fichero
            Empleado2 empleado1 = new Empleado2("E001", "Juan", "Pérez", "Ventas", "500");
            Empleado2 empleado2 = new Empleado2("E002", "María", "Gómez", "Marketing", "600");
            Empleado2 empleado3 = new Empleado2("E003", "Pedro", "López", "Desarrollo", "700");
            escribirEmpleado(randomFile, empleado1);
            escribirEmpleado(randomFile, empleado2);
            escribirEmpleado(randomFile, empleado3);

            //Pedimos identificador
            System.out.println("Escribe un identificador.");
            String identificador = Examen.Consola.leeString();
            System.out.println("Escribe el salario a implementar");
            String salarioIncrementar = Examen.Consola.leeString();
            randomFile.seek(0);
            while(randomFile.getFilePointer() < randomFile.length()) {
                try {
                    Empleado2 empleado = leerEmpleado(randomFile);
                    if (empleado.ID.equals(identificador)) {
                        String salarioAntiguo = empleado.salario;
                        implementarSalario(empleado, salarioIncrementar);
                        System.out.println("Empleado encontrado:");
                        System.out.println("ID: " + empleado.ID);
                        System.out.println("Nombre: " + empleado.nombre);
                        System.out.println("Apellido: " + empleado.apellido);
                        System.out.println("Departamento: " + empleado.departamento);
                        System.out.println("Salario antiguo: " + salarioAntiguo);
                        System.out.println("Salario actual: " + empleado.salario);
                        escribirEmpleado(randomFile, empleado1);
                        escribirEmpleado(randomFile, empleado2);
                        escribirEmpleado(randomFile, empleado3);
                        return;
                    }
                } catch (IOException e) {
                    System.out.println("Empleado no encontrado.");
                }
            }
        } catch (IOException e) {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void implementarSalario(Empleado2 empleado, String salario) {
        empleado.salario = String.valueOf(Double.parseDouble(empleado.salario) + Double.parseDouble(salario));
    }

    //Para escribir un empleado en el fichero .dat
    public static void escribirEmpleado(RandomAccessFile randomFile, Empleado2 empleado){
        try {
            randomFile.writeUTF(empleado.ID);
            randomFile.writeUTF(empleado.nombre);
            randomFile.writeUTF(empleado.apellido);
            randomFile.writeUTF(empleado.departamento);
            randomFile.writeUTF(empleado.salario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Leer un empleado desde el fichero .dat
    public static Empleado2 leerEmpleado(RandomAccessFile randomFile) throws IOException{
        Empleado2 empleado = null;
        String id = randomFile.readUTF();
        String nombre = randomFile.readUTF();
        String apellido = randomFile.readUTF();
        String departamento = randomFile.readUTF();
        String salario = randomFile.readUTF();
        empleado = new Empleado2(id, nombre, apellido, departamento, salario);
        return empleado;
    }

}
