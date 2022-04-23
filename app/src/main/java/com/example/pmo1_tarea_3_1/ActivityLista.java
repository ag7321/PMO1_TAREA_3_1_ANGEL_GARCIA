package com.example.pmo1_tarea_3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pmo1_tarea_3_1.Configuracion.SQLiteConexion;
import com.example.pmo1_tarea_3_1.Configuracion.Transacciones;
import com.example.pmo1_tarea_3_1.Tablas.Empleado;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    ListView listView;
    private ArrayList<Empleado> listaEmpleado;
    private ArrayList<String> listaEmpleadoString;
    private SQLiteConexion connection;

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        connection = new SQLiteConexion(this, Transacciones.NAME_DATABASE, null, 1);

        listView = (ListView) findViewById(R.id.listViewLista);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                irEditar(listaEmpleado.get(i));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getAllEmpleados();
    }

    private void getAllEmpleados() {
        SQLiteDatabase database = connection.getReadableDatabase();
        Empleado empTemp = null;

        Cursor cursor = database.rawQuery(Transacciones.SELECT_ALL_TABLE_EMPLOYEE, null);
        listaEmpleado = new ArrayList<>();
        listaEmpleadoString = new ArrayList<>();

        while (cursor.moveToNext()){
            empTemp = new Empleado();

            empTemp.id = cursor.getString(0);
            empTemp.nombres = cursor.getString(1);
            empTemp.apellidos = cursor.getString(2);
            empTemp.direccion = cursor.getString(3);
            empTemp.trabajo = cursor.getString(4);
            empTemp.edad = cursor.getString(5);

            listaEmpleado.add(empTemp);

            listaEmpleadoString.add(empTemp.nombres + " " + empTemp.apellidos);
        }
        cursor.close();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaEmpleadoString);
        listView.setAdapter(arrayAdapter);
    }

    private void irEditar(Empleado empleado){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Transacciones.NAME_TABLE, empleado);

        Intent intent = new Intent(getApplicationContext(), ActivityEditar.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}