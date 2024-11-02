package es.liernisarraoa;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("********Menu********");
        System.out.println("1.- Insertar datos en MySQL y visualizarlos");
        System.out.println("2.- Insertar datos en SQLite y visualizarlos");
        Integer respuesta = Consola.leeInt();
        if(respuesta == 1){
            DaoMySQL sql = new DaoMySQL();
        } else if (respuesta == 2) {
            DaoSQLite sqlite = new DaoSQLite();
        } else {
            System.out.println("No se ha seleccionado ninguna opcion del menu");
        }
    }
}