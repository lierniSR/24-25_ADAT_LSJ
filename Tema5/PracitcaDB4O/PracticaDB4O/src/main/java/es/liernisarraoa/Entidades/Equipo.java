package es.liernisarraoa.Entidades;

import java.util.Objects;

public class Equipo {

    private String nombre;
    private String iniciales;

    public Equipo(){}

    public Equipo(String nombre, String iniciales){
        this.nombre = nombre;
        this.iniciales = iniciales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre) && Objects.equals(iniciales, equipo.iniciales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, iniciales);
    }

    @Override
    public String toString() {
        return "***********************\n" +
                "Equipo --> \n" +
                "\tNombre --> " + nombre + "\n" +
                "\tIniciales --> " + iniciales + "\n" +
                "***********************\n";
    }
}
