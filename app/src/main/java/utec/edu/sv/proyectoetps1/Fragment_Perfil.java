package utec.edu.sv.proyectoetps1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utec.edu.sv.proyectoetps1.datos.Clientes;
import utec.edu.sv.proyectoetps1.encrypt.EncryptPassword;
import utec.edu.sv.proyectoetps1.entidades.Cliente;


public class Fragment_Perfil extends Fragment {
EditText nombre,email,password,repetirPassword;
Button btnCerrarSesion,btnActualizar;

Context context;
    public Fragment_Perfil() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view =  inflater.inflate(R.layout.fragment__perfil, container, false);



        nombre = view.findViewById(R.id.edtPerfilName);
        email = view.findViewById(R.id.edtPerfilEmail);
        password = view.findViewById(R.id.edtPerfilPass);
        repetirPassword = view.findViewById(R.id.edtPerfilConfirm);
        email.setEnabled(false);
        getData();

        btnCerrarSesion = view.findViewById(R.id.btnCerrarSession);
        btnActualizar = view.findViewById(R.id.btnActualizar);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para cerrar sesión
                cerrarSesion();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EncryptPassword encryp = new EncryptPassword();
                // Obtener los valores ingresados por el usuario
                String newPassword = password.getText().toString().trim();
                String newUsername = nombre.getText().toString().trim();
                String newNewPassword = repetirPassword.getText().toString().trim();

                // Verificar si los campos no están vacíos
                if (!newPassword.isEmpty() && !newUsername.isEmpty() && !newNewPassword.isEmpty()) {
                    // Actualizar el usuario con los nuevos valores
                    Clientes clientes = new Clientes(getContext());
                    boolean updated = clientes.updatePerfil(encryp.encryptPassword(newPassword), newUsername, encryp.encryptPassword(newNewPassword));

                    if (updated) {
                        // Mostrar un mensaje de éxito
                        Toast.makeText(getActivity(), "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();

                        // Actualizar los datos en la vista
                        email.setText("");
                        password.setText("");
                        repetirPassword.setText("");
                    } else {
                        // Mostrar un mensaje de error
                        Toast.makeText(getActivity(), "La contraseña actual ingresada no coincide", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Mostrar un mensaje de error si los campos están vacíos
                    Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    public void getData(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", requireContext().MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        Clientes client = new Clientes(requireContext().getApplicationContext());
        Cliente cliente = client.checkUserCredentials(username, password);
        nombre.setText(cliente.getNombre());
        email.setText(cliente.getCorreo());
    }
    private void cerrarSesion() {
        // Eliminar las preferencias compartidas
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Navegar a la pantalla de inicio de sesión
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}