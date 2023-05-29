package utec.edu.sv.proyectoetps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.encrypt.EncryptPassword;

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
        txtCorreo = findViewById(R.id.edtEmail);
        txtDui = findViewById(R.id.edtDui);
        txtUsuario = findViewById(R.id.edtUsuario);
        txtContrasena = findViewById(R.id.edtContrasena);
        txtRepetircontrasena = findViewById(R.id.edtRepetirContrasena);
        btnRegistrar = findViewById(R.id.btnGuardarOferta);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clientes client = new Clientes(context);
                EncryptPassword encryp = new EncryptPassword();

                if(!validFields()) return;

                if (!txtContrasena.getText().toString().equals(txtRepetircontrasena.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Las contrasena no coinciden!", Toast.LENGTH_SHORT).show();
                    return;
                }

                long codeSave = client.insertClient(
                        txtNombre.getText().toString(),txtApellido.getText().toString(),txtTelefono.getText().toString(),txtCorreo.getText().toString(),
                        txtDui.getText().toString(),txtUsuario.getText().toString(),encryp.encryptPassword(txtContrasena.getText().toString())
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
    private boolean validFields() {
        String nombre = txtNombre.getText().toString().trim();
        String apellido = txtApellido.getText().toString().trim();
        String telefono = txtTelefono.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();
        String dui = txtDui.getText().toString().trim();
        String usuario = txtUsuario.getText().toString().trim();
        String contrasena = txtContrasena.getText().toString().trim();
        String repetirContrasena = txtRepetircontrasena.getText().toString().trim();

        if (nombre.isEmpty()) {
            messageRequired("Nombre");
            return false;
        }

        if (apellido.isEmpty()) {
            messageRequired("Apellido");
            return false;
        }

        if (telefono.isEmpty()) {
            messageRequired("Telefono");
            return false;
        }

        if (correo.isEmpty()) {
            messageRequired("Correo");
            return false;
        }

        if (dui.isEmpty()) {
            messageRequired("DUI");
            return false;
        }

        if (usuario.isEmpty()) {
            messageRequired("Usuario");
            return false;
        }

        if (contrasena.isEmpty()) {
            messageRequired("Contrasena");
            return false;
        }

        if (repetirContrasena.isEmpty()) {
            messageRequired("Repita contrasena");
            return false;
        }

        return true;
    }

    private void messageRequired(String name)
    {
        Toast.makeText(getApplicationContext(), "El campo "+ name +" es requerido", Toast.LENGTH_SHORT).show();
    }

}