package utec.edu.sv.proyectoetps1.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import utec.edu.sv.proyectoetps1.entidades.EntOfertas;
import utec.edu.sv.proyectoetps1.entidades.EntTipoGas;

public class Ofertas extends BaseHelper{
    Context context;
    public Ofertas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertOffert(String NOMBRE,String IDTIPOGAS, String FECHAINICIO, String FECHAFIN, String CANTIDAD_PUNTOS)
    {
        long flag = 0;
        try{

            BaseHelper baseHelp = new BaseHelper(context);
            SQLiteDatabase bd = baseHelp.getWritableDatabase();

            ContentValues valOfert = new ContentValues();
            valOfert.put("IDTIPOGAS", IDTIPOGAS);
            valOfert.put("NOMBRE", NOMBRE);
            valOfert.put("FECHAINICIO", FECHAINICIO);
            valOfert.put("FECHAFIN", FECHAFIN);
            valOfert.put("CANTIDAD_PUNTOS", CANTIDAD_PUNTOS);

            flag = bd.insert(TABLA_OFERTA, null, valOfert);
            return flag;

        }catch(Exception e)
        {
            e.toString();
            return flag = 0;
        }
    }

    public ArrayList<EntOfertas> mostrarOfertas(){
        BaseHelper baseHelp = new BaseHelper(context);
        SQLiteDatabase bd = baseHelp.getWritableDatabase();
        ArrayList<EntOfertas>listaOfertas=new ArrayList<>();
        EntOfertas entOfertas=null;
        Cursor cursorPrverdor;

        String query = "SELECT O.IDOEFRTA, T.NOMGAS , O.NOMBRE, O.FECHAINICIO, O.FECHAFIN, O.CANTIDAD_PUNTOS " +
                "FROM "+TABLA_OFERTA+" O " +
                "INNER JOIN "+TABLA_TIPOGAS+" T ON O.IDTIPOGAS = T.IDTIPOGAS";

        cursorPrverdor=bd.rawQuery(query,null);
        if(cursorPrverdor.moveToFirst())
        {
            do{
                entOfertas= new EntOfertas();
                entOfertas.setIdOferta(cursorPrverdor.getInt(0));
                entOfertas.setNombreGas(cursorPrverdor.getString(1));
                entOfertas.setNombre(cursorPrverdor.getString(2));
                entOfertas.setFechaInicio(cursorPrverdor.getString(3));
                entOfertas.setFechaFin(cursorPrverdor.getString(4));
                entOfertas.setCantPuntos(cursorPrverdor.getString(5));
                listaOfertas.add(entOfertas);
            }
            while(cursorPrverdor.moveToNext());
        }
        cursorPrverdor.close();
        return listaOfertas;
    }
    public ArrayList<EntTipoGas> obtenerDatos() {
        BaseHelper baseHelp = new BaseHelper(context);
        SQLiteDatabase bd = baseHelp.getReadableDatabase();
        ArrayList<EntTipoGas> listaDatos = new ArrayList<>();

        Cursor cursor = bd.rawQuery("SELECT * FROM "+TABLA_TIPOGAS, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                EntTipoGas gas = new EntTipoGas(id, nombre);
                listaDatos.add(gas);
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();

        return listaDatos;
    }

}
