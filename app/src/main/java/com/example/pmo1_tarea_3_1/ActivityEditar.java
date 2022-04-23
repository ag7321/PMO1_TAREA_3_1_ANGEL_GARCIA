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
import com.example.pmo1_tarea_3_1.Tablas.Empleado;

public class ActivityEditar extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtEdad, txtDireccion, txtTrabajo;
    Button btnEditar, btnEliminar;

    Empleado empleado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        txtNombre = (EditText) findViewById(R.id.txtNombreEditar);
        txtApellidos = (EditText) findViewById(R.id.txtApellidosEditar);
        txtEdad = (EditText) findViewById(R.id.txtEdadEditar);
        txtDireccion = (EditText) findViewById(R.id.txtDireccionEditar);
        txtTrabajo = (EditText) findViewById(R.id.txtTrabajoEditar);

        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarEmpleado();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarEmpleado();
                finish();
            }
        });

        Bundle objEnviado = getIntent().getExtras();
        if(objEnviado != null){
            empleado = (Empleado) objEnviado.getSerializable(Transacciones.NAME_TABLE);

            txtNombre.setText(empleado.nombres);
            txtApellidos.setText(empleado.apellidos);
            txtEdad.setText(empleado.edad);
            txtDireccion.setText(empleado.direccion);
            txtTrabajo.setText(empleado.trabajo);
        }
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

    private void eliminarEmpleado(){
        SQLiteConexion connection = new SQLiteConexion(this, Transacciones.NAME_DATABASE, null, 1);
        SQLiteDatabase database = connection.getWritableDatabase();

        int result = database.delete(Transacciones.NAME_TABLE, Transacciones.EMPLOYEE_ID+"=?", new String[]{empleado.id});

        if(result > 0){
            Toast.makeText(this, "Empleado eliminado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Se produjo un error al eliminar el empleado", Toast.LENGTH_SHORT).show();
        }
    }

    private void editarEmpleado() {
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

        values.put(Transacciones.EMPLOYEE_ID, empleado.id);
        values.put(Transacciones.EMPLOYEE_NAMES, nombres);
        values.put(Transacciones.EMPLOYEE_SURNAMES, apellidos);
        values.put(Transacciones.EMPLOYEE_AGE, edad);
        values.put(Transacciones.EMPLOYEE_DIRECTION, direccion);
        values.put(Transacciones.EMPLOYEE_JOB, trabajo);

        Long resp = database.replace(Transacciones.NAME_TABLE, Transacciones.EMPLOYEE_ID, values);

        if (resp > 0) {
            Toast.makeText(this, "Empleado editado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Se produjo un error al editar el empleado", Toast.LENGTH_SHORT).show();
        }
    }
}