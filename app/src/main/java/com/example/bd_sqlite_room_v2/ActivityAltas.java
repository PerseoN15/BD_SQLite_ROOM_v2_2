package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.room.Room;

import bd.EscuelaBD;
import entidades.Alumno;

public class ActivityAltas extends Activity {

    EditText cajaNumControl, cajaNombre;
    EscuelaBD bd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        cajaNumControl = findViewById(R.id.caja_num_control);
        cajaNombre =findViewById(R.id.caja_nombre);
        bd = EscuelaBD.getAppDatabase(getApplicationContext());
    }

    public void agregarAlumno(View v){
        String numControl = cajaNumControl.getText().toString();
        String nombre = cajaNombre.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Verificar que los campos no estén vacíos
                if (!numControl.isEmpty() && !nombre.isEmpty()) {
                    // Agregar el alumno a la base de datos
                    Alumno alumno = new Alumno(numControl, nombre,primerAp,segundoAp,edad,semestre,carrera);
                    bd.alumnoDAO().agregarAlumno(alumno);

                    Toast.makeText(getApplicationContext(),
                            "Alumno agregado correctamente",
                            Toast.LENGTH_SHORT).show();

                    // Limpiar los campos después de agregar
                    cajaNumControl.setText("");
                    cajaNombre.setText("");
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Completa todos los campos",
                            Toast.LENGTH_SHORT).show();

            }
        }).start();
    }
}
