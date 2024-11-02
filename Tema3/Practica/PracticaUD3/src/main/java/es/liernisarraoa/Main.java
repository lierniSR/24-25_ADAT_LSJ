package es.liernisarraoa;

import es.liernisarraoa.Dao.DaoCrearBBDD;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DaoCrearBBDD creacionBD = new DaoCrearBBDD();
        String respuesta = "-1";

        while(!respuesta.equals("0")){
            System.out.println("*************MENU*************");
            System.out.println("1.- Crear BBDD en MySQL.");
            System.out.println("2.- Crear BBDD en SQLite");
            System.out.println("3.- Listado de deportistas en dirferentes deportes.");
            System.out.println("4.- Listado de deportistas participantes.");
            System.out.println("5.- Añadir deportista/participacion.");
            System.out.println("6.- Eliminar participacion.");
            System.out.println("0.- Salir del programa.");
            respuesta = Consola.leeString();

            switch (respuesta) {
                case "1":
                    creacionBD.crearBDMySQL();
                    break;
                case "2":
                    creacionBD.crearBDSQLite();
                    break;
                case "3":
                    System.out.println("**********Saliendo del programa**********");
                    creacionBD.listadoDeportistasDiferenteDeporte();
                    break;
                case "4":
                    System.out.println("**********Saliendo del programa**********");
                    creacionBD.listadoDeportistasParticipantes();
                    break;
                case "5":
                    System.out.println("**********Saliendo del programa**********");
                    creacionBD.aniadirDeportistaAParticipacion();
                    break;
                case "6":
                    System.out.println("**********Saliendo del programa**********");
                    creacionBD.eliminarParticipacion();
                    break;
                default:
                    System.out.println("**********Saliendo del programa**********");
                    break;
            }
        }
    }
}