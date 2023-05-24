package utec.edu.sv.proyectoetps1.entidades;

public class Cliente {
    Integer Id;
    String NombreTipoClient;
    String Nombre;
    String Apellido;
    String Telefono;
    String Correo;
    String Dui;
    String Usuario;
    String Contrasena;
    Integer Puntaje;

    public Cliente() {
    }

    public Cliente(Integer id,String nombreTipoClient, String nombre, String apellido, String telefono, String correo, String dui, String usuario, String contrasena, Integer puntaje) {
        Id = id;
        NombreTipoClient = nombreTipoClient;
        Nombre = nombre;
        Apellido = apellido;
        Telefono = telefono;
        Correo = correo;
        Dui = dui;
        Usuario = usuario;
        Contrasena = contrasena;
        Puntaje = puntaje;
    }

    public String getNombreTipoClient() {
        return NombreTipoClient;
    }

    public void setNombreTipoClient(String nombreTipoClient) {
        NombreTipoClient = nombreTipoClient;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDui() {
        return Dui;
    }

    public void setDui(String dui) {
        Dui = dui;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public Integer getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        Puntaje = puntaje;
    }
}
