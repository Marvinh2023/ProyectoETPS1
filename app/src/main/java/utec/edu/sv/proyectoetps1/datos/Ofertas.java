package utec.edu.sv.proyectoetps1.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public EntOfertas obtenerOfertaPorId(int idOferta) {
        BaseHelper baseHelp = new BaseHelper(context);
        SQLiteDatabase bd = baseHelp.getWritableDatabase();
        EntOfertas oferta = null;
        Cursor cursor;

        String query = "SELECT O.IDOEFRTA, T.NOMGAS, O.NOMBRE, O.FECHAINICIO, O.FECHAFIN, O.CANTIDAD_PUNTOS " +
                "FROM " + TABLA_OFERTA + " O " +
                "INNER JOIN " + TABLA_TIPOGAS + " T ON O.IDTIPOGAS = T.IDTIPOGAS " +
                "WHERE O.IDOEFRTA = ?";

        String[] selectionArgs = {String.valueOf(idOferta)};
        cursor = bd.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            oferta = new EntOfertas();
            oferta.setIdOferta(cursor.getInt(0));
            oferta.setNombreGas(cursor.getString(1));
            oferta.setNombre(cursor.getString(2));
            oferta.setFechaInicio(cursor.getString(3));
            oferta.setFechaFin(cursor.getString(4));
            oferta.setCantPuntos(cursor.getString(5));
        }

        cursor.close();
        return oferta;
    }

    public ArrayList<EntTipoGas> obtenerDatos() {
        BaseHelper baseHelp = new BaseHelper(context);
        SQLiteDatabase bd = baseHelp.getReadableDatabase();
        ArrayList<EntTipoGas> listaDatos = new ArrayList<>();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + TABLA_TIPOGAS, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("IDTIPOGAS");
                int nombreIndex = cursor.getColumnIndexOrThrow("NOMGAS");
                int id = cursor.getInt(idIndex);
                String nombre = cursor.getString(nombreIndex);
                EntTipoGas gas = new EntTipoGas(id, nombre);
                listaDatos.add(gas);
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return listaDatos;
    }

    public ArrayList<EntOfertas> obtenerOfertas() {
        BaseHelper baseHelp = new BaseHelper(context);
        SQLiteDatabase bd = baseHelp.getReadableDatabase();
        ArrayList<EntOfertas> listaOfertas = new ArrayList<>();
        //

        Cursor cursor = bd.rawQuery("SELECT * FROM " + TABLA_OFERTA, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndexOrThrow("IDOEFRTA");
                int nombreIndex = cursor.getColumnIndexOrThrow("NOMBRE");
                int id = cursor.getInt(idIndex);
                String nombre = cursor.getString(nombreIndex);
                EntOfertas gas = new EntOfertas(id,null, nombre,null,null,null,null);
                listaOfertas.add(gas);
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return listaOfertas;
    }

    public int updateOferta(int idOferta, String nombre, String idTipoGas, String fechaInicio, String fechaFin, String cantidadPuntos) {
        BaseHelper baseHelp = new BaseHelper(context);
        SQLiteDatabase bd = baseHelp.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NOMBRE", nombre);
        values.put("IDTIPOGAS", idTipoGas);
        values.put("FECHAINICIO", fechaInicio);
        values.put("FECHAFIN", fechaFin);
        values.put("CANTIDAD_PUNTOS", cantidadPuntos);

        String whereClause = "IDOEFRTA = ?";
        String[] whereArgs = {String.valueOf(idOferta)};

        return bd.update(TABLA_OFERTA, values, whereClause, whereArgs);
    }
    public int deleteOferta(int idOferta) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLA_OFERTA, "IDOEFRTA=?", new String[]{String.valueOf(idOferta)});
        db.close();
        return rowsAffected;
    }






}
