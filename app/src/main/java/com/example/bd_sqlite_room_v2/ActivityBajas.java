package com.example.bd_sqlite_room_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import bd.EscuelaBD;

public class ActivityBajas {
    EditText cajaNumControl;
    EscuelaBD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        cajaNumControl = findViewById(R.id.caja_num_control_bajas);

        bd = EscuelaBD.getAppDatabase(getApplicationContext());
    }

    public void eliminarAlumno(View v){
        String numControl = cajaNumControl.getText().toString();

        // Verificar que el campo no esté vacío
        if (!numControl.isEmpty()) {
            // Eliminar el alumno de la base de datos por número de control
            bd.alumnoDAO().eliminarAlumnoPorNumControl(numControl);

            Toast.makeText(getApplicationContext(),
                    "Alumno eliminado correctamente",
                    Toast.LENGTH_SHORT).show();

            // Limpiar el campo después de eliminar
            cajaNumControl.setText("");
        } else {
            Toast.makeText(getApplicationContext(),
                    "Ingresa un número de control",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
