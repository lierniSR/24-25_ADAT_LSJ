import Dao.DaoHuesped;

public class Actividad6 {
    public static void main(String[] args) {
        //DaoHuesped.insertarDatos();
        //DaoCasa.visualizarAllCasas();
        DaoHuesped.visualizarMenoresEdad();
        System.out.println("Actualizando nombre de los huespedes menores de edad.");
        DaoHuesped.actualizarNombre();
        DaoHuesped.visualizarMenoresEdad();
    }
}