import Dao.DaoCasa;

public class Actividad4 {
    public static void main(String[] args) {
        //DaoCasa.insertarDatos();
        //DaoCasa.visualizarAllCasas();
        DaoCasa.visualizarCasasMayor50();
        System.out.println("Actualizando numero de personas de cada casa con huespedes mayores de 50 años.");
        DaoCasa.actualizarNumPersonas();
        DaoCasa.visualizarCasasMayor50();
    }
}