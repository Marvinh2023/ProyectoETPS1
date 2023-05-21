package utec.edu.sv.proyectoetps1.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class Clientes extends BaseHelper{
    Context context;
    public Clientes(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertClient(String NOMBRE, String APELLIDO, String TELEFONO, String CORREO, String DUI, String USUARIO, String CONTRASENA)
    {
        long flag = 0;
         try{

             BaseHelper baseHelp = new BaseHelper(context);
             SQLiteDatabase bd = baseHelp.getWritableDatabase();

             ContentValues valClient = new ContentValues();
             valClient.put("IDTIPOCLIENTE", 1);
             valClient.put("NOMBRE", NOMBRE);
             valClient.put("APELLIDO", APELLIDO);
             valClient.put("TELEFONO", TELEFONO);
             valClient.put("CORREO", CORREO);
             valClient.put("DUI", DUI);
             valClient.put("USUARIO", USUARIO);
             valClient.put("CONTRASENA", CONTRASENA);

             flag = bd.insert(TABLA_CLIENTE, null, valClient);
             return flag;

         }catch(Exception e)
         {
             e.toString();
             return flag = 0;
         }
    }
}
