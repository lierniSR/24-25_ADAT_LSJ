package es.liernisarraoa.Entidades;

import java.util.ArrayList;
import java.util.Objects;

public class Deporte {

    private String nombre;
    private ArrayList<Evento> eventos;

    private Integer ID = 1;

    public Deporte(){}

    public Deporte(String nombre){
        this.nombre = nombre;
        this.eventos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public void setEvento(Evento evento){
        this.eventos.add(evento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deporte deporte = (Deporte) o;
        return Objects.equals(nombre, deporte.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public void visualizar(){
        System.out.println("***********************\n" +
                "Deporte Nº " + this.ID + " --> \n" +
                "\tNombre --> " + nombre + "\n");
        for(Evento evento : this.eventos){
            evento.visualizar();
        }
        System.out.println("***********************\n");
        this.ID++;
    }
}
