package utec.edu.sv.proyectoetps1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.datos.Ofertas;
import utec.edu.sv.proyectoetps1.entidades.EntOfertas;
import utec.edu.sv.proyectoetps1.entidades.EntTipoGas;

public class Form_Ofertas extends AppCompatActivity {
    EditText edtNombre,edtFechaI,edtFechaF,edtCantPuntos;
    Spinner edtTipo,spnOfertas;
    Button btnGuardar,btnActualizar,btnEliminar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ofertas);
        context = getApplicationContext();

        edtNombre = findViewById(R.id.edtNombreOferta);
        edtTipo = findViewById(R.id.edtTipoGasOferta);
        spnOfertas = findViewById(R.id.spinnerOfertas);
        edtFechaI = findViewById(R.id.edtFechaIOferta);
        edtFechaF = findViewById(R.id.edtFechaFinOferta);
        edtCantPuntos = findViewById(R.id.edtPuntosOferta);
        btnGuardar = findViewById(R.id.btnGuardarOferta);
        btnActualizar = findViewById(R.id.btnModificarOferta);
        btnEliminar = findViewById(R.id.btnBorrarOferta);
        //Integer valorEntero = Integer.valueOf(null);

        // Configurar el adaptador del Spinner
        ArrayAdapter<EntTipoGas> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, new ArrayList<>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtTipo.setAdapter(adapter);

        obtenerDatos(adapter);


        // Crear una lista de ofertas
        ArrayList<EntOfertas> listaOfertas = obtenerOfertas();

        // Agregar el elemento "Selecciona un item" al inicio de la lista
                EntOfertas seleccionaItem = new EntOfertas(0,null, "Selecciona un item", null, null, null, null);
                listaOfertas.add(0, seleccionaItem);

        // Crear el adaptador
                ArrayAdapter<EntOfertas> adapters = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listaOfertas);
                adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spnOfertas.setAdapter(adapters);







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

        spnOfertas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la oferta seleccionada
                EntOfertas ofertaSeleccionada = (EntOfertas) parent.getSelectedItem();
                int idOfertaSeleccionada = ofertaSeleccionada.getIdOferta();

                // Obtener los detalles de la oferta por su ID
                Ofertas ofertas = new Ofertas(context);
                EntOfertas detallesOferta = ofertas.obtenerOfertaPorId(idOfertaSeleccionada);
                if (detallesOferta != null) {
                    // Llenar los campos del formulario con los datos de la oferta
                    llenarCamposFormulario(detallesOferta);
                }

                // Realizar la búsqueda en la base de datos utilizando el ID de la oferta
                // y llenar los campos del formulario con los datos obtenidos

                // Aquí debes implementar el código para buscar los detalles de la oferta
                // en la base de datos utilizando el ID de la oferta seleccionada.
                // Luego, puedes llenar los campos del formulario con los datos obtenidos.

                // Ejemplo:
                // DetallesOferta detallesOferta = obtenerDetallesOfertaPorId(idOfertaSeleccionada);
                // if (detallesOferta != null) {
                //     llenarCamposFormulario(detallesOferta);
                // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se seleccionó ningún elemento
                // Puedes agregar aquí la lógica que desees para este caso
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ofertas offert = new Ofertas(context);
                if (!validFields()) return;

                // Obtener la oferta seleccionada del Spinner
                EntOfertas ofertaSeleccionada = (EntOfertas) spnOfertas.getSelectedItem();

                // Verificar si se seleccionó una oferta válida
                if (ofertaSeleccionada == null || ofertaSeleccionada.getIdOferta() == 0) {
                    Toast.makeText(getApplicationContext(), "Selecciona una oferta para actualizar", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Obtener el dato seleccionado del Spinner de tipo de gas
                EntTipoGas tipoGasSeleccionado = (EntTipoGas) edtTipo.getSelectedItem();

                // Obtener el ID del tipo de gas seleccionado
                int idTipoGasSeleccionado = tipoGasSeleccionado.getIdTipoGas();
                String idTipoGasSeleccionadoString = String.valueOf(idTipoGasSeleccionado);

                // Realizar la actualización de la oferta en la base de datos
                int codeUpdate = offert.updateOferta(
                        ofertaSeleccionada.getIdOferta(),
                        edtNombre.getText().toString(),
                        idTipoGasSeleccionadoString,
                        edtFechaI.getText().toString(),
                        edtFechaF.getText().toString(),
                        edtCantPuntos.getText().toString()
                );

                if (codeUpdate > 0) {
                    Toast.makeText(getApplicationContext(), "Oferta actualizada exitosamente", Toast.LENGTH_SHORT).show();
                    cleanFields();
                } else {
                    Toast.makeText(getApplicationContext(), "Ha ocurrido un error al actualizar la oferta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ofertas offert = new Ofertas(context);

                // Obtener la oferta seleccionada del Spinner
                EntOfertas ofertaSeleccionada = (EntOfertas) spnOfertas.getSelectedItem();

                // Verificar si se seleccionó una oferta válida
                if (ofertaSeleccionada == null || ofertaSeleccionada.getIdOferta() == 0) {
                    Toast.makeText(getApplicationContext(), "Selecciona una oferta para eliminar", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Realizar la eliminación de la oferta en la base de datos
                int codeDelete = offert.deleteOferta(ofertaSeleccionada.getIdOferta());

                if (codeDelete > 0) {
                    Toast.makeText(getApplicationContext(), "Oferta eliminada exitosamente", Toast.LENGTH_SHORT).show();
                    cleanFields();
                } else {
                    Toast.makeText(getApplicationContext(), "Ha ocurrido un error al eliminar la oferta", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void llenarCamposFormulario(EntOfertas oferta) {
        // Llenar los campos del formulario con los datos de la oferta
        edtNombre.setText(oferta.getNombre());
        edtFechaI.setText(oferta.getFechaInicio());
        edtFechaF.setText(oferta.getFechaFin());
        edtCantPuntos.setText(oferta.getCantPuntos());

        // Seleccionar el elemento correspondiente en el spinner de tipo de gas
        ArrayAdapter<EntTipoGas> adapter = (ArrayAdapter<EntTipoGas>) edtTipo.getAdapter();
        int tipoGasCount = adapter.getCount();
        String idTipoGas = oferta.getIdTipoGas();
        if (idTipoGas != null) {
            int idTipoGasInt = Integer.parseInt(idTipoGas);
            for (int i = 0; i < tipoGasCount; i++) {
                EntTipoGas tipoGas = adapter.getItem(i);
                if (tipoGas.getIdTipoGas() == idTipoGasInt) {
                    edtTipo.setSelection(i);
                    break;
                }
            }
        }
    }



    private ArrayList<EntOfertas> obtenerOfertas() {
        Ofertas ofertas = new Ofertas(context);
        return ofertas.obtenerOfertas();
    }

    private void obtenerDatos(ArrayAdapter<EntTipoGas> adapter) {
        Ofertas ofertas = new Ofertas(context);
        ArrayList<EntTipoGas> listaDatos = ofertas.obtenerDatos();

        // Actualizar los datos del adaptador del Spinner
        adapter.clear();
        adapter.addAll(listaDatos);
        adapter.notifyDataSetChanged();
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