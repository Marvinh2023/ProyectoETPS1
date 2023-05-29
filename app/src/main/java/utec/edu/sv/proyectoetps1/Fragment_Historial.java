package utec.edu.sv.proyectoetps1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import utec.edu.sv.proyectoetps1.adaptadores.LstOfertasAdapter;
import utec.edu.sv.proyectoetps1.adaptadores.LstOfertasClienteAdapter;
import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.datos.Ofertas;
import utec.edu.sv.proyectoetps1.datos.OfertasClientes;
import utec.edu.sv.proyectoetps1.entidades.Cliente;
import utec.edu.sv.proyectoetps1.entidades.EntOfertas;
import utec.edu.sv.proyectoetps1.entidades.EntOfertasClientes;

public class Fragment_Historial extends Fragment {

    RecyclerView listaOfertas;
    ArrayList<EntOfertasClientes> AlistOferta;
    Context context;
    private int idCliente;
    private String telefono;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getData();
        View view = inflater.inflate(R.layout.fragment__historial, container, false);

        listaOfertas = view.findViewById(R.id.rcvLstOfertasClientes);
        listaOfertas.setLayoutManager(new LinearLayoutManager(getActivity()));

        OfertasClientes dbOfertasClientes = new OfertasClientes(getActivity());

        AlistOferta = new ArrayList<>();
        LstOfertasClienteAdapter adapter = new LstOfertasClienteAdapter(dbOfertasClientes.listarOfertasCliente(idCliente,telefono));
        System.out.println(adapter.getItemCount());
        listaOfertas.setAdapter(adapter);

        return view;
    }

    public void getData(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", requireContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        Clientes client = new Clientes(requireContext().getApplicationContext());
        Cliente cliente = client.checkUserCredentials(username, password);
        idCliente = cliente.getId();
        telefono = cliente.getTelefono();
    }


}