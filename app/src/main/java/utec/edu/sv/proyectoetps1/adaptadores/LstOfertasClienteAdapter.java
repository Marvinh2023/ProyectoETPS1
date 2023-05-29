package utec.edu.sv.proyectoetps1.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import utec.edu.sv.proyectoetps1.R;
import utec.edu.sv.proyectoetps1.entidades.EntOfertas;
import utec.edu.sv.proyectoetps1.entidades.EntOfertasClientes;

public class LstOfertasClienteAdapter extends RecyclerView.Adapter<LstOfertasClienteAdapter.OfertasClienteViewHolder>{

    ArrayList<EntOfertasClientes> listaofertasClientes;

    public LstOfertasClienteAdapter(ArrayList<EntOfertasClientes> entOfertasClientes) {
        this.listaofertasClientes=entOfertasClientes;
    }

    @NonNull
    @Override
    public OfertasClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_ofertas_clientes,null,false);
        return new OfertasClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertasClienteViewHolder holder, int position) {
        holder.viewNombre.setText("Oferta Canjeada: "+listaofertasClientes.get(position).getNombre());
        holder.viewPuntos.setText("Cant. Puntos Canjeados: "+String.valueOf(listaofertasClientes.get(position).getCantPuntosRedimidos()));
        holder.viewFecha.setText("Fecha Canjeado: "+listaofertasClientes.get(position).getFechaCajeo());
    }

    @Override
    public int getItemCount() {
        return listaofertasClientes.size();
    }

    public class OfertasClienteViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre,viewPuntos,viewFecha;
        public OfertasClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre=itemView.findViewById(R.id.tvNombreOferta);
            viewPuntos=itemView.findViewById(R.id.tvPuntosOfertas);
            viewFecha=itemView.findViewById(R.id.tvFechaOferta);


        }
    }

    public ArrayList<EntOfertasClientes> getListaofertasClientes() {
        return listaofertasClientes;
    }

    public void setListaofertasClientes(ArrayList<EntOfertasClientes> listaofertasClientes) {
        this.listaofertasClientes = listaofertasClientes;
    }
}
