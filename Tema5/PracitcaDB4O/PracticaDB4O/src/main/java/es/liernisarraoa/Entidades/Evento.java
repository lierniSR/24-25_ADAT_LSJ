package es.liernisarraoa.Entidades;

import java.util.ArrayList;
import java.util.Objects;

public class Evento {

    private String nombre;
    private ArrayList<Deportista> participantes;

    public Evento(){}

    public Evento(String nombre){
        this.nombre = nombre;
        this.participantes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setParticipante(Deportista deportista){
        this.participantes.add(deportista);
    }

    public ArrayList<Deportista> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Deportista> participantes) {
        this.participantes = participantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nombre, evento.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public void visualizar(){
        System.out.println("***********************\n" +
                "Evento --> \n" +
                "\tNombre --> " + nombre + "\n");
        for(Deportista deportista : this.participantes){
            deportista.visualizar();
        }
        System.out.println("***********************\n");
    }
}
