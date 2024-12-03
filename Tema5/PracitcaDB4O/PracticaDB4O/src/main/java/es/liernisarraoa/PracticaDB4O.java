package es.liernisarraoa;

import es.liernisarraoa.Dao.*;
import es.liernisarraoa.Datos.InsertarDatosDB4O;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PracticaDB4O {
    public static void main(String[] args) {
        //InsertarDatosDB4O.insertarDatos();
        DaoDeportista.visualizarDeportista();
        //DaoEquipo.visualizarEquipos();
        //DaoEvento.visualizarEventos();
        //DaoDeporte.visualizarDeporte();
        //DaoOlimpiada.visualizarOlimpaidas();
        System.out.println("***********Menu***********\n" +
                "1.- Listar deportistas.");
        String respuesta = Consola.leeString();
        if(respuesta.equalsIgnoreCase("1")){
            System.out.println("Inserta una temporada Winter o Summer");
            String temporada = Consola.leeString();
            System.out.println("Listando Olimpiadas de la temporada " + temporada + "......\n\n");
            DaoOlimpiada.listarOlimpiadasTemporada(temporada);
            System.out.println("Inserte el ID deseado.");
            Integer ID = Consola.leeInt();
            System.out.println("Listando Olimpiadas de la temporada " + temporada + " segun el ID nº " + ID + "......\n\n");
            DaoOlimpiada.listarOlimTemporadaID(temporada, ID);
            System.out.println("Inserta el nombre del deporte deseado.");
            String nombreDeporte = Consola.leeString();
            System.out.println("Listando Olimpiadas de la temporada " + temporada + " segun el ID nº " + ID + ", con el nombre del deporte " + nombreDeporte + " ......\n\n");
            DaoOlimpiada.listarOlimtempoIDNombre(temporada, ID, nombreDeporte);
        }
    }
}