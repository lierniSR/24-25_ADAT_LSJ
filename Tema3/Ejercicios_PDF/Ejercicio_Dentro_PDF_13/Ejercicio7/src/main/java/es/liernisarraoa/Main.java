package es.liernisarraoa;

public class Main {
    public static void main(String[] args) {
        System.out.println("Inserte 1 para MySQL y 2 para SQLite");
        Integer bbdd = Consola.leeInt();
        System.out.println("Inserte el numero del cliente");
        Integer clienteID = Consola.leeInt();
        if(bbdd == 1){
            DaoVentasMySQL sql = new DaoVentasMySQL(clienteID);
        } else {
            DaoVentasSQLite sqlite = new DaoVentasSQLite(clienteID);
        }
    }
}