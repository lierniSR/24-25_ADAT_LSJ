import Dao.DaoCasa;

public class Actividad7 {
    public static void main(String[] args) {
        //DaoCasa.insertarDatos();
        //DaoCasa.visualizarAllCasas();
        DaoCasa.visualizarValencia3();
        System.out.println("Eliminando casas con menos de 3 habitacion y con la ciudad Valencia.");
        DaoCasa.eliminarHabitaciones3Valencia();
        DaoCasa.visualizarValencia3();
    }
}