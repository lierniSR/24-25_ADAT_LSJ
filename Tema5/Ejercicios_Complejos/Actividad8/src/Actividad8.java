import Dao.DaoCasa;

public class Actividad8 {
    public static void main(String[] args) {
        //DaoCasa.insertarDatos();
        //DaoCasa.visualizarAllCasas();
        DaoCasa.visualizarBarcelona();
        System.out.println("Eliminando huespedes de casas con mas de 6 personas en la casa y en la ciudad Barcelona.");
        DaoCasa.eliminarHuespedes();
        DaoCasa.visualizarBarcelona();
    }
}