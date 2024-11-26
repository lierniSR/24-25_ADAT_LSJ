package Entidades;

import java.util.ArrayList;
import java.util.Objects;

public class Casa {
    private String direccion;
    private double numHabitaciones;
    private double numPersonas;
    private Ciudad ciudad;
    private ArrayList<Huesped> arrHuesped;

    // Constructor vacío
    public Casa() {}

    // Constructor con parámetros
    public Casa( String direccion, double numHabitaciones, double numPersonas, Ciudad ciudad) {
        this.direccion = direccion;
        this.numHabitaciones = numHabitaciones;
        this.numPersonas = numPersonas;
        this.ciudad = ciudad;
        this.arrHuesped=new ArrayList<Huesped>();
    }



    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getCiudad() {
        return this.ciudad;
    }



    public double getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(double numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public double getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(double numPersonas) {
        this.numPersonas = numPersonas;
    }

    public void setHuesped(Huesped huesped) {
        // TODO Auto-generated method stub
        this.arrHuesped.add(huesped);
    }

    public void setCiudad(Ciudad ciudad) {
        // TODO Auto-generated method stub
        this.ciudad=ciudad;
    }

    public ArrayList<Huesped> getHuespeds(){
        return this.arrHuesped;
    }

    public void setHuespeds(ArrayList<Huesped> arrHuesped){
        this.arrHuesped=arrHuesped;
    }

    public void visualizar(){
        System.out.println("***************Casa***************\n" +
                "\tDireccion -> " + direccion + "\n" +
                "\tNumero de habitaciones -> " + numHabitaciones + "\n" +
                "\tNumero de personas -> " + numPersonas + "\n" +
                "\tCiudad -> " + ciudad.getCiudad() + "\n" +
                "\t\tCodigo Postas de la Ciudad -> " + ciudad.getCodigoPostal() + "\n" +
                "\tHuespedes -> ");
        int cont = 1;
        for(Huesped huesped : arrHuesped){
            System.out.println("\t\tHuesped " + cont + "\n" +
                    "\t\t\tNombre -> " + huesped.getNombre() + "\n" +
                    "\t\t\tEdad -> " + huesped.getEdad() + "\n" +
                    "\t\t\tEmail -> " + huesped.getEmail() + "\n");
            cont++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa casa = (Casa) o;
        return Objects.equals(direccion, casa.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(direccion);
    }
}
