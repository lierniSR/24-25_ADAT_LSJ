import Dao.DaoCasa;

public class Actividad7 {
    public static void main(String[] args) {
        //DaoCasa.insertarDatos();
        DaoCasa.visualizarCasas();
        DaoCasa.eliminarCasas2();
        System.out.println("Eliminando casas...");
        DaoCasa.visualizarCasas();
    }
}