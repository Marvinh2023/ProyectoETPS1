package utec.edu.sv.proyectoetps1.entidades;

public class EntTipoGas {
    private int IdTipoGas;
    private String Nomgas;

    public EntTipoGas(int idTipoGas, String nomgas) {
        IdTipoGas = idTipoGas;
        Nomgas = nomgas;
    }

    public int getIdTipoGas() {
        return IdTipoGas;
    }

    public void setIdTipoGas(int idTipoGas) {
        IdTipoGas = idTipoGas;
    }

    public String getNomgas() {
        return Nomgas;
    }

    public void setNomgas(String nomgas) {
        Nomgas = nomgas;
    }
}
