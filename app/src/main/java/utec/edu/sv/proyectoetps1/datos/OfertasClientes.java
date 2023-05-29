package utec.edu.sv.proyectoetps1.datos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

import utec.edu.sv.proyectoetps1.entidades.Cliente;
import utec.edu.sv.proyectoetps1.entidades.EntOfertas;
import utec.edu.sv.proyectoetps1.entidades.EntOfertasClientes;

public class OfertasClientes extends BaseHelper{

    Context context;
    public OfertasClientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertOffertCliente(int idOferta, int idCliente, int cantPuntos, String fecha)
    {
        long flag = 0;
        try{

            BaseHelper baseHelp = new BaseHelper(context);
            SQLiteDatabase bd = baseHelp.getWritableDatabase();

            ContentValues valOfert = new ContentValues();
            valOfert.put("IDOEFRTA", idOferta);
            valOfert.put("IDCLIENTE", idCliente);
            valOfert.put("FECHA_CAJEO", fecha);
            valOfert.put("CANT_PUNTOS_REDIMIDOS", cantPuntos);

            flag = bd.insert(TABLA_CLIENTEOFERTA, null, valOfert);
            return flag;

        }catch(Exception e)
        {
            e.toString();
            return flag = 0;
        }
    }

    //ACTUALIZAR EL PUNTAJE DEL CLIENTE
    public int updateCliente(int nuevoPuntaje, int idCliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("PUNTAJE", nuevoPuntaje);

        String whereClause = "IDCLIENTE = ?";
        String[] whereArgs = {String.valueOf(idCliente)};

        return db.update(TABLA_CLIENTE, values, whereClause, whereArgs);
    }

    //METODO PARA LISTAR EL HISTORIAL DE LOS CANJES DE UN CLIENTE
   public ArrayList<EntOfertasClientes> listarOfertasCliente(int idCliente, String telefono) {
        ArrayList<EntOfertasClientes> listaOfertasCliente = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        EntOfertasClientes ofertaCliente = null;
        String query = "SELECT cliofe.IDCLIENTEOEFRTA,ofe.NOMBRE NOMBRE, cliofe.FECHA_CAJEO FECHA_CAJEO, cliofe.CANT_PUNTOS_REDIMIDOS CANT_PUNTOS_REDIMIDOS " +
                "FROM " + TABLA_CLIENTE + " cli " +
                "INNER JOIN " + TABLA_CLIENTEOFERTA + " cliofe ON cliofe.IDCLIENTE = cli.IDCLIENTE " +
                "INNER JOIN " + TABLA_OFERTA + " ofe on ofe.IDOEFRTA = cliofe.IDOEFRTA " +
                "WHERE cli.IDCLIENTE = ? AND cli.TELEFONO = ? "+
                "group by cliofe.IDCLIENTEOEFRTA";

        String[] selectionArgs = {String.valueOf(idCliente), telefono};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if(cursor.moveToFirst())
        {
       do{
                ofertaCliente= new EntOfertasClientes();
                ofertaCliente.setNombre(cursor.getString(0));
                ofertaCliente.setFechaCajeo(cursor.getString(1));
                ofertaCliente.setCantPuntosRedimidos(cursor.getInt(2));
                listaOfertasCliente.add(ofertaCliente);
            }while(cursor.moveToNext());
       }
        cursor.close();
        return listaOfertasCliente;
    }


}
