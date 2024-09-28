import java.io.File;

public class Actividad1 {
    public static void main(String[] args) {
        System.out.println("Escribe la ruta de la carpeta");
        String directorioUsuario = Examen.Consola.leeString();
        File directorio = new File(directorioUsuario);
        String rutaAbsoluta = directorio.getAbsolutePath();
        File carpeta = new File(rutaAbsoluta);
        String[] archivosDirectorio = carpeta.list();
        if(archivosDirectorio != null) {
            for (String ficheros : archivosDirectorio){
                System.out.println(ficheros + "\n");
            }
        }
    }
}