package utec.edu.sv.proyectoetps1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utec.edu.sv.proyectoetps1.datos.BaseHelper;
import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.encrypt.EncryptPassword;
import utec.edu.sv.proyectoetps1.entidades.Cliente;

public class MainActivity extends AppCompatActivity {

    EditText userNameOrPass, password;
    Button btnLogin;

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

        userNameOrPass = findViewById(R.id.edtUserOrEmail);
        password = findViewById(R.id.edtPassword);
        btnLogin= findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                EncryptPassword encryp = new EncryptPassword();
                String user = userNameOrPass.getText().toString();
                String pass = encryp.encryptPassword(password.getText().toString());

                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", user);
                editor.putString("password", pass);
                editor.apply();


                if(!validFields()) return ;

                if(!isValidCredentials(user,pass)) {
                    Toast.makeText(getApplicationContext(), "Credenciales inválidas", Toast.LENGTH_SHORT).show(); return;
                }
                Intent intento = new Intent(MainActivity.this, Form_Options.class);
                startActivity(intento);
                cleanFields();

            }
        });

    }

    public void Register(View v){
        Intent intento=new Intent(this,Form_Register.class);
        startActivity(intento);
    }
    private boolean validFields() {
        String username = userNameOrPass.getText().toString().trim();
        String passs = password.getText().toString().trim();

        if (username.isEmpty()) {
            messageRequired("Usuario o Email");
            return false;
        }

        if (passs.isEmpty()) {
            messageRequired("Contrasena");
            return false;
        }

        return true;
    }

    private void messageRequired(String name)
    {
        Toast.makeText(getApplicationContext(), "El campo "+ name +" es requerido", Toast.LENGTH_SHORT).show();
    }

    private boolean isValidCredentials(String username, String password) {
        Clientes client = new Clientes(getApplicationContext());
        Cliente cliente = client.checkUserCredentials(username, password);
        return cliente != null; // Devuelve true si se encontró un cliente válido, false de lo contrario
    }

    private void cleanFields()
    {
        userNameOrPass.setText("");
        password.setText("");
    }
}