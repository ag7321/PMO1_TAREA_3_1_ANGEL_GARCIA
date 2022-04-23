package com.example.pmo1_tarea_3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmo1_tarea_3_1.Configuracion.SQLiteConexion;
import com.example.pmo1_tarea_3_1.Configuracion.Transacciones;

public class ActivityNuevo extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtEdad, txtDireccion, txtTrabajo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);


        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtTrabajo = (EditText) findViewById(R.id.txtTrabajo);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarEmpleado();
            }
        });
    }

    private String validarCampos() {
        String response  = "OK";

        if (txtNombre.getText().toString().isEmpty()) {
            response = "Ingrese el nombre del empleado";
        } else if (txtApellidos.getText().toString().isEmpty()) {
            response = "Ingrese el apellido del empleado";
        }  else if (txtEdad.getText().toString().isEmpty()) {
            response = "Ingrese la edad del empleado";
        } else if (txtDireccion.getText().toString().isEmpty()) {
            response = "Ingrese la direccion del empleado";
        } else if (txtTrabajo.getText().toString().isEmpty()) {
            response = "Ingrese el trabajo del empleado";
        }

        return response;
    }

    private void guardarEmpleado() {
        String response = validarCampos();

        if (!response.equals("OK")) {
            Toast.makeText(this, response, Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteConexion connection = new SQLiteConexion(this, Transacciones.NAME_DATABASE, null, 1);
        SQLiteDatabase database = connection.getWritableDatabase();

        String nombres = txtNombre.getText().toString().trim();
        String apellidos = txtApellidos.getText().toString().trim();
        String direccion = txtDireccion.getText().toString().trim();
        String edad = txtEdad.getText().toString().trim();
        String trabajo = txtTrabajo.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(Transacciones.EMPLOYEE_NAMES, nombres);
        values.put(Transacciones.EMPLOYEE_SURNAMES, apellidos);
        values.put(Transacciones.EMPLOYEE_AGE, edad);
        values.put(Transacciones.EMPLOYEE_DIRECTION, direccion);
        values.put(Transacciones.EMPLOYEE_JOB, trabajo);

        Long resp = database.insert(Transacciones.NAME_TABLE, Transacciones.EMPLOYEE_ID, values);

        if (resp > 0) {
            Toast.makeText(this, "Empleado guardado correctamente", Toast.LENGTH_SHORT).show();
            limpiarInputs();
        } else {
            Toast.makeText(this, "Se produjo un error al guardar el empleado", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarInputs(){
        txtNombre.setText(null);
        txtApellidos.setText(null);
        txtEdad.setText(null);
        txtTrabajo.setText(null);
        txtDireccion.setText(null);
    }
}