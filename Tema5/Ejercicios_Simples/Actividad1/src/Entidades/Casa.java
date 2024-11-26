package Entidades;

public class Casa {
    public String nombre;
    public String direccion;
    public String poblacion;
    public Integer CP;
    public Integer numHabitaciones;

    public Casa(){}

    public Casa(String nombre, String direccion, String poblacion, Integer CP, Integer numHabitaciones){
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.CP = CP;
        this.numHabitaciones = numHabitaciones;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Integer getCP() {
        return CP;
    }

    public void setCP(Integer CP) {
        this.CP = CP;
    }

    @Override
    public String toString() {
        return "Casa --> \n" +
                "\tNombre -> " + nombre + "\n" +
                "\tDireccion -> " + direccion + "\n" +
                "\tPoblacion -> " + poblacion + "\n" +
                "\tCP -> " + CP + "\n" +
                "\tNumero de habitaciones -> " + numHabitaciones + "\n";
    }
}
