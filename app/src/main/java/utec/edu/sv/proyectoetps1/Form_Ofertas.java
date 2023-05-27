package utec.edu.sv.proyectoetps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.datos.Ofertas;

public class Form_Ofertas extends AppCompatActivity {
    EditText edtNombre,edtTipo,edtFechaI,edtFechaF,edtCantPuntos;
    Button btnGuardar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ofertas);
        context = getApplicationContext();

        edtNombre = findViewById(R.id.edtNombreOferta);
        edtTipo = findViewById(R.id.edtTipoGasOferta);
        edtFechaI = findViewById(R.id.edtFechaIOferta);
        edtFechaF = findViewById(R.id.edtFechaFinOferta);
        edtCantPuntos = findViewById(R.id.edtPuntosOferta);
        btnGuardar = findViewById(R.id.btnGuardarOferta);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ofertas offert = new Ofertas(context);
                if(!validFields()) return;
                long codeSave = offert.insertOffert(
                        edtNombre.getText().toString(),edtTipo.getText().toString(),edtFechaI.getText().toString(),edtFechaF.getText().toString(),
                        edtCantPuntos.getText().toString()
                );

                if ( codeSave > 0)
                {
                    Toast.makeText(getApplicationContext(), "Oferta registrado exitosamente", Toast.LENGTH_SHORT).show();
                    cleanFields();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Ha ocurrido un error al crear la oferta " + codeSave, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validFields() {
        String nombre = edtNombre.getText().toString().trim();
        String tipo = edtTipo.getText().toString().trim();
        String fechai = edtFechaI.getText().toString().trim();
        String fechaf = edtFechaF.getText().toString().trim();
        String cant = edtCantPuntos.getText().toString().trim();

        if (nombre.isEmpty()) {
            messageRequired("Nombre");
            return false;
        }

        if (tipo.isEmpty()) {
            messageRequired("Tipo Gas");
            return false;
        }

        if (fechai.isEmpty()) {
            messageRequired("Fecha inicio");
            return false;
        }

        if (fechaf.isEmpty()) {
            messageRequired("Fecha Fin");
            return false;
        }

        if (cant.isEmpty()) {
            messageRequired("Cantidad puntos");
            return false;
        }

        return true;
    }
    private void messageRequired(String name)
    {
        Toast.makeText(getApplicationContext(), "El campo "+ name +" es requerido", Toast.LENGTH_SHORT).show();
    }
    private void cleanFields()
    {
        edtNombre.setText("");
        edtTipo.setText("");
        edtFechaI.setText("");
        edtFechaF.setText("");
        edtCantPuntos.setText("");
    }
}