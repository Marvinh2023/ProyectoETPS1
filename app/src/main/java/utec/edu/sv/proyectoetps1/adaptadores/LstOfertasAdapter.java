package utec.edu.sv.proyectoetps1.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import utec.edu.sv.proyectoetps1.R;
import utec.edu.sv.proyectoetps1.entidades.EntOfertas;

public class LstOfertasAdapter extends RecyclerView.Adapter<LstOfertasAdapter.OfertasViewHolder> {

    ArrayList<EntOfertas> listaofertas;
    public LstOfertasAdapter(ArrayList<EntOfertas> entOfertas) {
        this.listaofertas=entOfertas;
    }

    @NonNull
    @Override
    public LstOfertasAdapter.OfertasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_ofertas,null,false);
        return new OfertasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LstOfertasAdapter.OfertasViewHolder holder, int position) {
        holder.viewNombre.setText(listaofertas.get(position).getNombre());
        holder.viewNomGas.setText(listaofertas.get(position).getNombreGas() + " - Pts +" + listaofertas.get(position).getCantPuntos());
        holder.viewFechaall.setText(listaofertas.get(position).getFechaInicio() + " - " + listaofertas.get(position).getFechaFin());
    }

    @Override
    public int getItemCount() {
        return listaofertas.size();
    }

    public class OfertasViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre,viewNomGas,viewFechaall;
        public OfertasViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre=itemView.findViewById(R.id.tvNombre);
            viewNomGas=itemView.findViewById(R.id.tvNomGas);
            viewFechaall=itemView.findViewById(R.id.tvFechaIniFin);
            
            
        }
    }

    public ArrayList<EntOfertas> getListaofertas() {
        return listaofertas;
    }
}
