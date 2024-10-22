public class Persona {
    private String nombre;
    private String ciudad;
    private Integer dept_no;

    public Persona(String nombre,String ciudad, Integer dept_no) {
        this.nombre=nombre;
        this.ciudad=ciudad;
        this.dept_no = dept_no;
    }

    public String getNombre(){return nombre;}
    public void setNombre(String nom){nombre=nom; }
    public String getCiudad(){return ciudad; }
    public void setCiudad(String dir){ciudad=dir;}
    public Integer getDept_no(){return dept_no;};
    public void setDept_no(Integer dept_no){this.dept_no = dept_no;};
}
