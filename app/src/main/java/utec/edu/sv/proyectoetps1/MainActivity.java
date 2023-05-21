package utec.edu.sv.proyectoetps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import utec.edu.sv.proyectoetps1.datos.BaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseHelper basehelper = new BaseHelper(MainActivity.this);
        SQLiteDatabase db =basehelper.getWritableDatabase();
        if(db!=null){
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Error en crear la Base de datos", Toast.LENGTH_LONG).show();
        }
    }

    public void Register(View v){
        Intent intento=new Intent(this,Form_Register.class);
        startActivity(intento);
    }
    public void Options(View v){
        Intent intento=new Intent(this,Form_Options.class);
        startActivity(intento);
    }
}