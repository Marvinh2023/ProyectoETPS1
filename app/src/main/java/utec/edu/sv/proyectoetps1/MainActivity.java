package utec.edu.sv.proyectoetps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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