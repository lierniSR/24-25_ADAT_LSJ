public class Departamento {
    private Integer dept_no;
    private String dnombre;
    private String loc;

    public Departamento(Integer dept_no, String dnombre, String loc){
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }

    public Integer getDept_no(){return this.dept_no;};
    public String getDnombre(){return this.dnombre;};
    public String getLoc(){return this.loc;};

    public void setDept_no(Integer dept_no){this.dept_no = dept_no;};
    public void setDnombre(String dnombre){this.dnombre = dnombre;};
    public void setLoc(String loc){this.loc = loc;};
}
