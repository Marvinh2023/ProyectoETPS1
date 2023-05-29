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

    public Cliente checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT c.*, tc.NOMTIPO AS TIPO_CLIENTE FROM " + TABLA_CLIENTE + " c " +
                "INNER JOIN " + TABLA_TIPOCLI + " tc ON c.IDTIPOCLIENTE = tc.IDTIPOCLIENTE " +
                "WHERE c.USUARIO = ? AND c.CONTRASENA = ?";

        String[] selectionArgs = {username, password};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        Cliente cliente = null;
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("IDCLIENTE");
            int tipoClienteIndex = cursor.getColumnIndex("TIPO_CLIENTE");
            int nombreIndex = cursor.getColumnIndex("NOMBRE");
            int apellidoIndex = cursor.getColumnIndex("APELLIDO");
            int telefonoIndex = cursor.getColumnIndex("TELEFONO");
            int correoIndex = cursor.getColumnIndex("CORREO");
            int duiIndex = cursor.getColumnIndex("DUI");
            int usuarioIndex = cursor.getColumnIndex("USUARIO");
            int contrasenaIndex = cursor.getColumnIndex("CONTRASENA");
            int puntajeIndex = cursor.getColumnIndex("PUNTAJE");
            // Obtener los valores de las columnas del cursor
            int id = cursor.getInt(idIndex);
            String tipoCliente = cursor.getString(tipoClienteIndex);
            String nombre = cursor.getString(nombreIndex);
            String apellido = cursor.getString(apellidoIndex);
            String telefono = cursor.getString(telefonoIndex);
            String correo = cursor.getString(correoIndex);
            String dui = cursor.getString(duiIndex);
            String usuario = cursor.getString(usuarioIndex);
            String contrasena = cursor.getString(contrasenaIndex);
            int puntaje = cursor.getInt(puntajeIndex);
            // Crear el objeto Cliente con los valores obtenidos
            cliente = new Cliente(id, tipoCliente, nombre, apellido, telefono, correo, dui, usuario, contrasena, puntaje);
        }

        cursor.close();
        return cliente;
    }
}
