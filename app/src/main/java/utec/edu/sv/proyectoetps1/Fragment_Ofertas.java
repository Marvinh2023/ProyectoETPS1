package utec.edu.sv.proyectoetps1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import utec.edu.sv.proyectoetps1.adaptadores.LstOfertasAdapter;
import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.datos.Ofertas;
import utec.edu.sv.proyectoetps1.datos.OfertasClientes;
import utec.edu.sv.proyectoetps1.entidades.Cliente;
import utec.edu.sv.proyectoetps1.entidades.EntOfertas;

public class Fragment_Ofertas extends Fragment {

    RecyclerView listaOfertas;
    ArrayList<EntOfertas> AlistOferta;
    Context context;

    private int idCliente;
    private int cantPuntosCliente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__ofertas, container, false);

        listaOfertas = view.findViewById(R.id.rcvLstOfertas);
        listaOfertas.setLayoutManager(new LinearLayoutManager(getActivity()));

        Ofertas dbOfertas = new Ofertas(getActivity());

        AlistOferta = new ArrayList<>();
        LstOfertasAdapter adapter = new LstOfertasAdapter(dbOfertas.mostrarOfertas());
        listaOfertas.setAdapter(adapter);

        // Configurar el escuchador de clics para los elementos del RecyclerView
        listaOfertas.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View childView = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(childView);

                if (position != RecyclerView.NO_POSITION) {
                    // Acción a realizar cuando se hace clic en un elemento
                    onItemClick(position);
                }

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        return view;
    }

    private void onItemClick(int position) {
        int idOferta = 0;
        long result = 0;
        String cantPuntos = "", message ;

        try {
            LstOfertasAdapter adapter = (LstOfertasAdapter) listaOfertas.getAdapter();
            ArrayList<EntOfertas> listaOfertas = adapter.getListaofertas();

            if (position >= 0 && position < listaOfertas.size()) {
                EntOfertas oferta = listaOfertas.get(position);
                idOferta = oferta.getIdOferta();
                cantPuntos = oferta.getCantPuntos();
            }
            //ANTES DE GUARDAR VALIDAR SI EL PUNTAJE ES CORRECTO
            int cantPuntosInt = Integer.parseInt(cantPuntos);
            if (cantPuntosCliente >= cantPuntosInt){
                // Guardar las ofertas canjeadas por el cliente
                OfertasClientes dbOfertasClientes = new OfertasClientes(getActivity());
                result = dbOfertasClientes.insertOffertCliente(idOferta,idCliente,cantPuntosInt,"27/05/2023");
                if (result > 0){
                    message ="Oferta canjeada con éxito ";
                    //showToast(message);
                    result = dbOfertasClientes.updateCliente((cantPuntosCliente-cantPuntosInt),idCliente);
                    if (result >0){
                        System.out.println("puntaje del cliente actualizado correctamente");
                    }
                }
            }else{
                message = "El cliente no tiene suficientes puentos para canjear la oferta ";
               // showToast(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getData(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", requireContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        Clientes client = new Clientes(requireContext().getApplicationContext());
        Cliente cliente = client.checkUserCredentials(username, password);
        idCliente = cliente.getId();
        cantPuntosCliente = cliente.getPuntaje();
    }

    private void showToast(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}