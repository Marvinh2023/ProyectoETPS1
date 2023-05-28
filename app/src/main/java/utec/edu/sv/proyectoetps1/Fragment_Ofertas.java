package utec.edu.sv.proyectoetps1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import utec.edu.sv.proyectoetps1.adaptadores.LstOfertasAdapter;
import utec.edu.sv.proyectoetps1.datos.Ofertas;
import utec.edu.sv.proyectoetps1.entidades.EntOfertas;

public class Fragment_Ofertas extends Fragment {

    RecyclerView listaOfertas;
    ArrayList<EntOfertas> AlistOferta;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__ofertas, container, false);

        listaOfertas = view.findViewById(R.id.rcvLstOfertas);
        listaOfertas.setLayoutManager(new LinearLayoutManager(context));

        Ofertas dbOfertas = new Ofertas(context);

        AlistOferta=new ArrayList<>();
        LstOfertasAdapter adapter=new LstOfertasAdapter(dbOfertas.mostrarOfertas());
        listaOfertas.setAdapter(adapter);

        return view;
    }
}