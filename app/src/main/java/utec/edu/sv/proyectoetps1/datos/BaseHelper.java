package utec.edu.sv.proyectoetps1.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseHelper extends SQLiteOpenHelper {

    private static  final int VERSION_BASEDATOS=1;
    private static  final String NOMBRE_BASE="BDTANQUELLENO.db";

    public static   final String TABLA_TIPOGAS="t_tipo_gas";
    public static   final String TABLA_TIPOCLI="t_tipo_cliente";
    public static  final String TABLA_GASOLINERA="t_gasolineras";
    public static   final String TABLA_OFERTA="t_ofertas";
    public static  final String TABLA_CLIENTE="t_clientes";
    public static   final String TABLA_CLIENTEOFERTA="t_cliente_oferta";
    public static   final String TABLA_HISTORIALPUNTAJE="t_historial_puntaje";

    public static   final String FOREIGN_TIPOGAS ="IDTIPOGAS";
    public static   final String FOREIGN_TIPOCLI ="IDTIPOCLIENTE";
    public static   final String FOREIGN_CLIENTE ="IDCLIENTE";
    public static   final String FOREIGN_OFERTA ="IDOEFRTA";
    public static   final String FOREIGN_GASOLINERA ="IDGAS";

    public BaseHelper(@Nullable Context context) {
        super(context, NOMBRE_BASE, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLA_TIPOGAS+" ("+
                "IDTIPOGAS INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "NOMGAS TEXT NOT NULL"
                +")");
        db.execSQL("CREATE TABLE "+TABLA_TIPOCLI+" ("+
                "IDTIPOCLIENTE INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "NOMTIPO TEXT NOT NULL"
                +")");

        db.execSQL("CREATE TABLE "+TABLA_GASOLINERA+" ("+
                "IDGAS INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "IDTIPOGAS INTEGER NOT NULL ,"+
                "NOMBRE TEXT NOT NULL ,"+
                "UBICACION TEXT NOT NULL, "+
                "FOREIGN KEY(" +  FOREIGN_TIPOGAS + " ) REFERENCES " + TABLA_TIPOGAS + "IDTIPOGAS"
                +")");

        db.execSQL("CREATE TABLE "+TABLA_OFERTA+" ("+
                "IDOEFRTA INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "IDTIPOGAS INTEGER NOT NULL ,"+
                "NOMBRE TEXT NOT NULL ,"+
                "FECHAINICIO TEXT NOT NULL, "+
                "FECHAFIN TEXT NOT NULL, "+
                "CANTIDAD_PUNTOS TEXT NOT NULL, "+
                "FOREIGN KEY(" +  FOREIGN_TIPOGAS + " ) REFERENCES " + TABLA_TIPOGAS + "IDTIPOGAS"
                +")");

        db.execSQL("CREATE TABLE "+TABLA_CLIENTE+" ("+
                "IDCLIENTE INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "IDTIPOCLIENTE INTEGER NOT NULL ,"+
                "NOMBRE TEXT NOT NULL ,"+
                "APELLIDO TEXT NOT NULL, "+
                "TELEFONO TEXT NOT NULL, "+
                "CORREO TEXT NOT NULL, "+
                "DUI TEXT NOT NULL, "+
                "USUARIO TEXT NOT NULL, "+
                "CONTRASENA TEXT NOT NULL, "+
                "ESTADO TEXT NOT NULL, "+
                "PUNTAJE TEXT NOT NULL, "+
                "FOREIGN KEY(" +  FOREIGN_TIPOCLI + " ) REFERENCES " + TABLA_CLIENTE + "IDTIPOGAS"
                +")");

        db.execSQL("CREATE TABLE "+TABLA_CLIENTEOFERTA+" ("+
                "IDCLIENTEOEFRTA INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "IDOEFRTA INTEGER NOT NULL ,"+
                "IDCLIENTE INTEGER NOT NULL ,"+
                "CANT_PUNTOS_REDIMIDOS TEXT NOT NULL ,"+
                "FECHA_CAJEO TEXT NOT NULL, "+
                "FOREIGN KEY(" +  FOREIGN_CLIENTE + " ) REFERENCES " + TABLA_CLIENTE + "IDCLIENTE ,"+
                "FOREIGN KEY(" +  FOREIGN_OFERTA + " ) REFERENCES " + TABLA_OFERTA + "IDOEFRTA"
                +")");

        db.execSQL("CREATE TABLE "+TABLA_HISTORIALPUNTAJE+" ("+
                "IDPUNTAJE INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                "IDGAS INTEGER NOT NULL ,"+
                "IDTIPOGAS INTEGER NOT NULL ,"+
                "PUNTAJE_INICIAL INTEGER NOT NULL ,"+
                "PUNTAJE_ANTERIOR TEXT NOT NULL ,"+
                "PUNTAJE_ACTUAL TEXT NOT NULL, "+
                "CANT_GAS_RECIBIDO TEXT NOT NULL, "+
                "FOREIGN KEY(" +  FOREIGN_GASOLINERA + " ) REFERENCES " + TABLA_GASOLINERA + "IDGAS ,"+
                "FOREIGN KEY(" +  FOREIGN_TIPOGAS + " ) REFERENCES " + TABLA_TIPOGAS + "IDTIPOGAS"
                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_TIPOGAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_TIPOCLI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_GASOLINERA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_OFERTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CLIENTEOFERTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_HISTORIALPUNTAJE);

        onCreate(db);
    }
}
