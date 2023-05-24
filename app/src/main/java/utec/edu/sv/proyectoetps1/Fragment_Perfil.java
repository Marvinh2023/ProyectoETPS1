package utec.edu.sv.proyectoetps1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.entidades.Cliente;


public class Fragment_Perfil extends Fragment {
EditText nombre,email,password,repetirPassword;
Context context;
    public Fragment_Perfil() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view =  inflater.inflate(R.layout.fragment__perfil, container, false);



        nombre = view.findViewById(R.id.edtPerfilName);
        email = view.findViewById(R.id.edtPerfilEmail);

        getData();

        return view;
    }

    public void getData(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", requireContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        Clientes client = new Clientes(requireContext().getApplicationContext());
        Cliente cliente = client.checkUserCredentials(username, password);
        nombre.setText(cliente.getNombre());
        email.setText(cliente.getCorreo());
    }
}