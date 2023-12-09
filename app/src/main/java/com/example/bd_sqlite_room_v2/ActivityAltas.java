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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        cajaNumControl = findViewById(R.id.caja_num_control);
        cajaNombre =findViewById(R.id.caja_nombre);
    }

    public void agregarAlumno(View v){
        Log.i("MSJ->", "metodo agregar");

        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("MSJ->", "CORRECTO");
                bd.alumnoDAO().agregarAlumno(
                        new Alumno(cajaNumControl.getText().toString(), "1", "1", "1", (byte)1, (byte)1, "1"));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "REGISTRO AGREGADO CORRECTAMENTE",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();



    }
}
