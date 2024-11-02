package es.liernisarraoa.Modelo;

public class Ventas {
    private Integer IDVenta;
    private String fechaVenta;
    private Integer IDCliente;
    private Integer IDProducto;
    private Integer cantidad;

    public Ventas(Integer IDVenta, String formattedDate, Integer IDCliente, Integer IDProducto, Integer cantidad){
        this.IDVenta = IDVenta;
        this.fechaVenta = formattedDate;
        this.IDCliente = IDCliente;
        this.IDProducto = IDProducto;
        this.cantidad = cantidad;
    }

    public Integer getIDVenta() {
        return IDVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getIDProducto() {
        return IDProducto;
    }

    public Integer getIDCliente() {
        return IDCliente;
    }
}
