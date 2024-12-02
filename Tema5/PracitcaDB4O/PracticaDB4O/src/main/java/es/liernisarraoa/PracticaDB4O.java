package es.liernisarraoa;

import es.liernisarraoa.Dao.DaoDeportista;
import es.liernisarraoa.Dao.DaoEquipo;
import es.liernisarraoa.Datos.InsertarDatosDB4O;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class PracticaDB4O {
    public static void main(String[] args) {
        InsertarDatosDB4O.insertarDatos();
        DaoDeportista.visualizarDeportista();
        DaoEquipo.visualizarEquipos();
    }
}