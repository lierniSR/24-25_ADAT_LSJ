import Dao.DaoCasa;

public class Actividad9 {
    public static void main(String[] args) {
        //DaoCasa.insertarDatos();
        //DaoCasa.visualizarAllCasas();
        System.out.println("\n\n\nMostrando ciudades--->\n\n\n");
        DaoCasa.visualizarCiudades();
        System.out.println("\n\n\nMostrando casas--->\n\n\n");
        DaoCasa.visualizarCasas();
        System.out.println("\n\n\nEliminando ciudades--->\n\n\n");
        DaoCasa.eliminarCiudades();
        System.out.println("\n\n\nMostrando ciudades--->\n\n\n");
        DaoCasa.visualizarCiudades();
        System.out.println("\n\n\nMostrando casas--->\n\n\n");
        DaoCasa.visualizarCasas();
    }
}