package utec.edu.sv.proyectoetps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.datos.Ofertas;
import utec.edu.sv.proyectoetps1.entidades.EntTipoGas;

public class Form_Ofertas extends AppCompatActivity {
    EditText edtNombre,edtFechaI,edtFechaF,edtCantPuntos;
    Spinner edtTipo;
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

        ArrayList<EntTipoGas> listaDatos = obtenerDatos();
        ArrayAdapter<EntTipoGas> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listaDatos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtTipo.setAdapter(adapter);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ofertas offert = new Ofertas(context);
                if(!validFields()) return;
                // Obtener el dato seleccionado del dropdown
                EntTipoGas datoSeleccionado = (EntTipoGas) edtTipo.getSelectedItem();

                // Obtener el ID seleccionado
                int idSeleccionado = datoSeleccionado.getIdTipoGas();
                String idSeleccionadoString = String.valueOf(idSeleccionado);
                long codeSave = offert.insertOffert(
                        edtNombre.getText().toString(),idSeleccionadoString,edtFechaI.getText().toString(),edtFechaF.getText().toString(),
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

    private ArrayList<EntTipoGas> obtenerDatos() {
        ArrayList<EntTipoGas> listaDatos = new ArrayList<>();

        // Aquí se realiza la obtención de datos de la base de datos y se agregan a la lista
        // Asegúrate de utilizar los métodos adecuados para obtener los datos de la base de datos

        // Ejemplo de agregar datos ficticios a la lista para probar
        listaDatos.add(new EntTipoGas(1, "Gas 1"));
        listaDatos.add(new EntTipoGas(2, "Gas 2"));
        listaDatos.add(new EntTipoGas(3, "Gas 3"));

        // Imprimir los datos en el log
        for (EntTipoGas dato : listaDatos) {
            Log.d("MiApp", "ID: " + dato.getIdTipoGas());
            Log.d("MiApp", "Nombre: " + dato.getNomgas());
            Log.d("MiApp", "-----------------------");
        }

        return listaDatos;
    }


    private boolean validFields() {
        String nombre = edtNombre.getText().toString().trim();
      //  String tipo = edtTipo.getText().toString().trim();
        String fechai = edtFechaI.getText().toString().trim();
        String fechaf = edtFechaF.getText().toString().trim();
        String cant = edtCantPuntos.getText().toString().trim();

        if (nombre.isEmpty()) {
            messageRequired("Nombre");
            return false;
        }

       /* if (tipo.isEmpty()) {
            messageRequired("Tipo Gas");
            return false;
        }*/

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
       // edtTipo.setText("");
        edtFechaI.setText("");
        edtFechaF.setText("");
        edtCantPuntos.setText("");
    }
}