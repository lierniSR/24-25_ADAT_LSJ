package Entidades;

import java.util.Objects;

public class Ciudad {
    private String ciudad;
    private String codigoPostal;

    // Constructor vacío
    public Ciudad() {}

    // Constructor con parámetros
    public Ciudad( String ciudad, String codigoPostal) {
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    // Getters y Setters




    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Comparación de referencia
        if (!(obj instanceof Ciudad)) return false; // Verifica si es del mismo tipo
        Ciudad otraCiudad = (Ciudad) obj; // Hace el casting
        return Objects.equals(ciudad, otraCiudad.ciudad) &&
                Objects.equals(codigoPostal, otraCiudad.codigoPostal); // Compara atributos
    }

    @Override
    public int hashCode() {
        return Objects.hash(ciudad, codigoPostal); // Genera un hash basado en los atributos
    }

    @Override
    public String toString() {
        return "Ciudad -> \n" +
                "\tCiudad -> " + ciudad + "\n" +
                "\tCodigoPostal -> " + codigoPostal + "\n";
    }
}
