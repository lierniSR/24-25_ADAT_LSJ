import Dao.DaoCasa;

public class Actividad5 {
    public static void main(String[] args) {
        //DaoCasa.insertarDatos();
        //DaoCasa.visualizarAllCasas();
        DaoCasa.visualizarCasasAna();
        System.out.println("Actualizando codigo postal de las casas con huespedes llamadas Ana.");
        DaoCasa.actualizaCodigoPostal();
        DaoCasa.visualizarCasasAna();
    }
}