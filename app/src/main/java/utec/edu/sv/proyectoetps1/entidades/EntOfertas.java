package utec.edu.sv.proyectoetps1.entidades;

public class EntOfertas {
    private int idOferta;

    private String idTipoGas;
    private String nombre;
    private String nombreGas;
    private String fechaInicio;
    private String fechaFin;
    private String cantPuntos;

    public EntOfertas() {
    }

    public EntOfertas(int idOferta,String idTipoGas, String nombre, String nombreGas, String fechaInicio, String fechaFin, String cantPuntos) {
        this.idOferta = idOferta;
        this.idTipoGas = idTipoGas;
        this.nombre = nombre;
        this.nombreGas = nombreGas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantPuntos = cantPuntos;
    }

    public String getIdTipoGas() {
        return idTipoGas;
    }

    public void setIdTipoGas(String idTipoGas) {
        this.idTipoGas = idTipoGas;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreGas() {
        return nombreGas;
    }

    public void setNombreGas(String nombreGas) {
        this.nombreGas = nombreGas;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCantPuntos() {
        return cantPuntos;
    }

    public void setCantPuntos(String cantPuntos) {
        this.cantPuntos = cantPuntos;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
