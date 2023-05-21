package utec.edu.sv.proyectoetps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utec.edu.sv.proyectoetps1.datos.Clientes;

public class Form_Register extends AppCompatActivity {
    EditText txtNombre,txtApellido,txtTelefono,txtCorreo,txtDui,txtUsuario,txtContrasena,txtRepetircontrasena;
    Button btnRegistrar;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        context = getApplicationContext();

        txtNombre = findViewById(R.id.edtNombre);
        txtApellido = findViewById(R.id.edtApellido);
        txtTelefono = findViewById(R.id.edtTelefono);
        txtCorreo = findViewById(R.id.edtCorreo);
        txtDui = findViewById(R.id.edtUsuario);
        txtUsuario = findViewById(R.id.edtUsuario);
        txtContrasena = findViewById(R.id.edtContrasena);
        txtRepetircontrasena = findViewById(R.id.edtRepetirContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clientes client = new Clientes(context);

                if (!txtContrasena.getText().toString().equals(txtRepetircontrasena.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Las contrasena no coinciden!", Toast.LENGTH_SHORT).show();
                    return;
                }

                long codeSave = client.insertClient(
                        txtNombre.getText().toString(),txtApellido.getText().toString(),txtTelefono.getText().toString(),txtCorreo.getText().toString(),
                        txtDui.getText().toString(),txtUsuario.getText().toString(),txtContrasena.getText().toString()
                );

                if ( codeSave > 0)
                {
                    Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    cleanFields();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Ha ocurrido un error al crear el usuario " + codeSave, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cleanFields()
    {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText(null);
        txtCorreo.setText("");
        txtDui.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtRepetircontrasena.setText("");
    }
}