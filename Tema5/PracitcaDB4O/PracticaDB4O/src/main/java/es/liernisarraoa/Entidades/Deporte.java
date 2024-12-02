package es.liernisarraoa.Entidades;

import java.util.ArrayList;
import java.util.Objects;

public class Deporte {

    private String nombre;
    private ArrayList<Evento> eventos;

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
}
