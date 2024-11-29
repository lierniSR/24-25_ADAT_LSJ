package Entidades;

import java.util.Objects;

public class Huesped {
    private String nombre;
    private double edad;
    private String email;

    // Constructor vacío
    public Huesped() {}

    // Constructor con parámetros
    public Huesped( String nombre, double edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Huesped --> \n" +
                "\tNombre -> " + nombre + "\n" +
                "\tEdad -> " + edad + "\n" +
                "\tEmail -> " + email + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return Objects.equals(nombre, huesped.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
