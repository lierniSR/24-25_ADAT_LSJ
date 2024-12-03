package es.liernisarraoa.Entidades;

import java.util.ArrayList;
import java.util.Objects;

public class Olimpiada {

    private Integer ID;
    private String nombre;
    private Integer anio;
    private String temporada;
    private String ciudad;
    private ArrayList<Deporte> deportes;

    public Olimpiada(){}

    public Olimpiada(Integer ID, String nombre, Integer anio, String temporada, String ciudad){
        this.ID = ID;
        this.nombre = nombre;
        this.anio = anio;
        this.temporada = temporada;
        this.ciudad = ciudad;
        this.deportes = new ArrayList<>();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Deporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(ArrayList<Deporte> deportes) {
        this.deportes = deportes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public void setDeporte(Deporte deporte){
        this.deportes.add(deporte);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Olimpiada olimpiada = (Olimpiada) o;
        return Objects.equals(ID, olimpiada.ID) && Objects.equals(nombre, olimpiada.nombre) && Objects.equals(anio, olimpiada.anio) && Objects.equals(temporada, olimpiada.temporada) && Objects.equals(ciudad, olimpiada.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nombre, anio, temporada, ciudad);
    }

    public void visualizar(){
        System.out.println("***********************\n" +
                "Olimpiada --> \n" +
                "\tID --> " + ID + "\n" +
                "\tNombre --> " + nombre + "\n" +
                "\tAño --> " + anio + "\n" +
                "\tTemporada --> " + temporada + "\n" +
                "\tCiudad --> " + ciudad + "\n");
        for(Deporte deporte : this.deportes){
            deporte.visualizar();
        }
        System.out.println("***********************\n");
    }
}
