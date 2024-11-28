import Dao.DaoCasa;

public class Actividad9 {
    public static void main(String[] args) {
        //DaoCasa.insertarDatos();
        System.out.println("Datos insertados....");
        DaoCasa.visualizarCiudades();
        DaoCasa.eliminarCiudadesSinCasa();
        System.out.println("Eliminando ciudades...\n\n");
        DaoCasa.visualizarCiudades();
    }
}