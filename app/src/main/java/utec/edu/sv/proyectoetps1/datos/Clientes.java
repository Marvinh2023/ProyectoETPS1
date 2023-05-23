package utec.edu.sv.proyectoetps1.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import utec.edu.sv.proyectoetps1.entidades.Cliente;

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

    public Cliente  checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "USUARIO = ? AND CONTRASENA = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLA_CLIENTE, null, selection, selectionArgs, null, null, null);

        Cliente cliente = null;
        if (cursor.moveToFirst()) {
            // Obtener los índices de las columnas en el cursor
            int Id = cursor.getColumnIndex("IDCLIENTE");
            int Nombre = cursor.getColumnIndex("NOMBRE");
            int Apellido = cursor.getColumnIndex("APELLIDO");
            int Telefono = cursor.getColumnIndex("TELEFONO");
            int Correo = cursor.getColumnIndex("CORREO");
            int Dui = cursor.getColumnIndex("DUI");
            int Usuario = cursor.getColumnIndex("USUARIO");
            int Contrasena = cursor.getColumnIndex("CONTRASENA");
            int Puntaje = cursor.getColumnIndex("PUNTAJE");
            // Obtener los valores de las columnas del cursor
            int id = cursor.getInt(Id);
            String nombre = cursor.getString(Nombre);
            String apellido = cursor.getString(Apellido);
            String telefono = cursor.getString(Telefono);
            String correo = cursor.getString(Correo);
            String dui = cursor.getString(Dui);
            String usuario = cursor.getString(Usuario);
            String contrasena = cursor.getString(Contrasena);
            int puntaje = cursor.getInt(Puntaje);
            // Crear el objeto User con los valores obtenidos
            cliente = new Cliente(id, nombre, apellido,telefono,correo,dui,usuario,contrasena,puntaje);
        }

        cursor.close();
        db.close();

        return cliente;
        /*SQLiteDatabase db = this.getReadableDatabase();

        String selection =  "USUARIO = ? AND CONTRASENA = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLA_CLIENTE, null, selection, selectionArgs, null, null, null);

        boolean isValid = cursor.moveToFirst();

        cursor.close();
        db.close();

        return isValid;*/
    }
}
