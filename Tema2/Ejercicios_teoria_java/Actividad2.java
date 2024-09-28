import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Actividad2 {
    public static void main(String[] args){
        System.out.println("Escribe el nombre de un archivo de texto.");
        String nombreFichero = Examen.Consola.leeString();
        try(BufferedReader br = new BufferedReader(new FileReader(nombreFichero + ".txt"))){
            String linea = br.readLine();
            while(linea != null){
                System.out.println(linea + "\n");
                linea = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}