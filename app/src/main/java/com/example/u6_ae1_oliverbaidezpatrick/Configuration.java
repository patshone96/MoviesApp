package com.example.u6_ae1_oliverbaidezpatrick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Configuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        Switch swDescarga = (Switch) findViewById(R.id.swDescarga);
        Spinner spnLang = (Spinner) findViewById(R.id.spnLanguage);
        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        Button btnDefecto = (Button) findViewById(R.id.btnDefecto);



        swDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swDescarga.isChecked()){
                    Toast.makeText(getApplicationContext(), "Descarga Automática Activada", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "Descarga Automática Desactivada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Configuración guardada", Toast.LENGTH_SHORT).show();


            }
        });

        btnDefecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                swDescarga.setChecked(false);

                Toast.makeText(getApplicationContext(), "Configuración Revertida", Toast.LENGTH_SHORT).show();


            }
        });







    }


}