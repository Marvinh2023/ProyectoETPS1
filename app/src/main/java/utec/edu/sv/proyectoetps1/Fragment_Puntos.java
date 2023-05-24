package utec.edu.sv.proyectoetps1;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.entidades.Cliente;

public class Fragment_Puntos extends Fragment {
    TextView tvNivel, tvTarjeta,tvPuntos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment__puntos, container, false);

        tvNivel = view.findViewById(R.id.tvNivel);
        tvTarjeta = view.findViewById(R.id.tvNumTarjeta);
        tvPuntos = view.findViewById(R.id.tvPuntos);

        getData();


        return view;
    }

    public void getData(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", requireContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        Clientes client = new Clientes(requireContext().getApplicationContext());
        Cliente cliente = client.checkUserCredentials(username, password);
       /* String nivel = null;
        if(cliente.getIdTipoCliente() == 1){
            nivel = "Bronce";
        }*/
        tvNivel.setText(cliente.getNombreTipoClient());
        tvTarjeta.setText(cliente.getTelefono());
        tvPuntos.setText(cliente.getPuntaje().toString());
    }
}