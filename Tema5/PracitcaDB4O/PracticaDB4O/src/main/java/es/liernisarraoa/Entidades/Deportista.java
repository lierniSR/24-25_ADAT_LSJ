package es.liernisarraoa.Entidades;

import java.util.Objects;

public class Deportista {

    private String nombre;
    private String sexo;
    private Integer edad;
    private Double altura;
    private Double peso;
    private String medalla;

    public Deportista() {}

    public Deportista(String nombre, String sexo, Integer edad, Double altura, Double peso, String medalla){
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.medalla = medalla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getMedalla() {
        return medalla;
    }

    public void setMedalla(String medalla) {
        this.medalla = medalla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deportista that = (Deportista) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(sexo, that.sexo) && Objects.equals(edad, that.edad) && Objects.equals(altura, that.altura) && Objects.equals(peso, that.peso) && Objects.equals(medalla, that.medalla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, sexo, edad, altura, peso, medalla);
    }

    @Override
    public String toString() {
        return "***********************\n" +
                "Deportista --> " +
                "\tNombre ->" + nombre + "\n" +
                "\tSexo ->" + sexo + "\n" +
                "\tEdad ->" + edad + "\n" +
                "\tAltura ->" + altura + "\n" +
                "\tPeso ->" + peso + "\n" +
                "\tMedalla ->" + medalla + "\n" +
                "***********************\n";
    }
}
