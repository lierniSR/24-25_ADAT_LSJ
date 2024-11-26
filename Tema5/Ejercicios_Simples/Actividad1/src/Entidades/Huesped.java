package Entidades;

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
}
