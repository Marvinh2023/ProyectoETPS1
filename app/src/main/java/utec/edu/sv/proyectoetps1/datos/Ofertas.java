package utec.edu.sv.proyectoetps1.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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
}
