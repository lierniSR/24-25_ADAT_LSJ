import Dao.DaoCasa;

public class Actividad8 {
    public static void main(String[] args) {
        DaoCasa.insertarDatos();
        DaoCasa.visualizarHuesped();
        DaoCasa.eliminarHuespedes18();
        System.out.println("Eliminando huespedes...\n\n");
        DaoCasa.visualizarHuesped();
    }
}