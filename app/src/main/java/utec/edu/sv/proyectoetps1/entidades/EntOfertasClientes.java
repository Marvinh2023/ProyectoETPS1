package utec.edu.sv.proyectoetps1.entidades;

public class EntOfertasClientes {

    private String nombre;
    private String fechaCajeo;
    private int cantPuntosRedimidos;

    public EntOfertasClientes() {
    }

    public EntOfertasClientes(String nombre, String fechaCajeo, int cantPuntosRedimidos) {
        this.nombre = nombre;
        this.fechaCajeo = fechaCajeo;
        this.cantPuntosRedimidos = cantPuntosRedimidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCajeo() {
        return fechaCajeo;
    }

    public void setFechaCajeo(String fechaCajeo) {
        this.fechaCajeo = fechaCajeo;
    }

    public int getCantPuntosRedimidos() {
        return cantPuntosRedimidos;
    }

    public void setCantPuntosRedimidos(int cantPuntosRedimidos) {
        this.cantPuntosRedimidos = cantPuntosRedimidos;
    }
}
