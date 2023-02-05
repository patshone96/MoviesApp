package com.example.u6_ae1_oliverbaidezpatrick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Film> films;
    RecyclerView recyclerFilms;
    DBSQL dbsql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dbsql = new DBSQL(getApplicationContext());



        //Intent para cambiar la activity cuando pulsamos en un elemento
        //Nos lleva a una activity con toda la información de la película
        //Esta nueva activity se genera a partir de los datos de la peli que le pasamos
        Intent intent = new Intent(this, DetailScreen.class);

        //Otras variables que vamos a necesitar en la ejecución del programa
        films = new ArrayList<>(); //Array de pelis para rellenar el recycler view

        // Insetamos datos en la BBDD
        // insertar();


        //Recuperamos los datos de la BBDD
        //recuperar();

        //Variables para capturar el recycler del layout
        recyclerFilms = findViewById(R.id.rRecycler);
        //Le asignamos un layout linear
        recyclerFilms.setLayoutManager(new LinearLayoutManager(this));

        //Función para insertar las películas en el array de películas



        fillFilms();

        //fillFilms2(films);




        //Creamos un adaptador propio (clase AdapterFilms) al que le pasamos el
        //Array de películas que hemos creado
        AdapterFilms adapter = new AdapterFilms(this, films);

        //Seteamos el adaptador en el recycler view para que introduzca los objetos en el recycler
        recyclerFilms.setAdapter(adapter);



        // Añadimos un onclicklistener a cada elemento del adaptador
        // Este evento OnClick se ha creado dentro de la clase propia AdapterFilms
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //metodo on click del adaptador
                //Lanzamos un toast de corta duración que indica el título de la peli seleccionada
                Toast.makeText(getApplicationContext(), films.get(recyclerFilms.getChildAdapterPosition(view)).getTitle(), Toast.LENGTH_SHORT).show();

                //Le pasamos un elemento serializable (film) como variable al intent que vamos a lanzar
                // Este elemento es el mismo en el que hemos pulsado
                // Para ello, debemos haber implementado la interfaz Serializable a la clase Film
                intent.putExtra("film", (Serializable) films.get(recyclerFilms.getChildAdapterPosition(view)));

                //iniciamos la nueva actividad
                startActivity(intent);
            }
        });


    }


    //A Futuro, para cerrar la base de datos sql lite
    @Override
    protected void onDestroy() {

        super.onDestroy();
        dbsql.close();
    }



    // Recuperar registros de la bd sqLite
    public void recuperar(){


        //Recuperamos la DB legible
        SQLiteDatabase db = dbsql.getWritableDatabase();

        //Creamos una proyección con la columnas que queremos recuperar
        String[] projection = {
                Contract.newContract.COLUMN_NAME_TITLE,
                Contract.newContract.COLUMN_NAME_DESC,
                Contract.newContract.COLUMN_NAME_IMAGE,
                Contract.newContract.COLUMN_NAME_YEAR,

        };

        //Lanzamos la query que nos devuelve un cursor (similar a un iterable)
        Cursor cursor = db.query(
                Contract.newContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );




        //Iterando el cursor, vamos generando objetos película que intertaremos en el array films
        while (cursor.moveToNext()){

                Film film = new Film(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        false);

            films.add(film);

        }

        //Cerramos el cursor
        cursor.close();


    }

    //Insertar pelis en la bbdd FUNCIONAL
    public void insertar(){

        //CONECTAR A LA BBDD
        SQLiteDatabase db = dbsql.getWritableDatabase();

        ContentValues values = new ContentValues();

        int bucle = getResources().getInteger(R.integer.numberFilms);

        for(int i = 0; i < bucle; i++){
            values.put(Contract.newContract.COLUMN_NAME_TITLE, getResources().getStringArray(R.array.pelis)[i]);


            values.put(Contract.newContract.COLUMN_NAME_DESC, getResources().getStringArray(R.array.descript)[i]);


            values.put(Contract.newContract.COLUMN_NAME_IMAGE, i);


            values.put(Contract.newContract.COLUMN_NAME_YEAR, getResources().getIntArray(R.array.years)[i]);

            db.insert(Contract.newContract.TABLE_NAME, null, values);
        }
    }



    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    //Para salir de la app con un backpress creamos una activity HOME y un dialogo
    // El usuario deberá aceptar para salir, o cancelar para continuar en la app
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        //Seteamos el intent de salida
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Contructor del dialog
        AlertDialog.Builder constructor = new AlertDialog.Builder(this);

        //Seteamos un titulo y un mensaje
        constructor.setTitle("Vas a cerrar la app").setMessage("¿Quieres salir de la app?");

        //Permitimos que el dialogo sea cancelable, que seria equivalente a presionar
        //El negativeButton
        constructor.setCancelable(true);

        //Establecemos el botón negativo con una lambda
        constructor.setNegativeButton("No", (DialogInterface.OnClickListener)
                (dialog, which) -> {
                    dialog.cancel();
                });

        //Establecemos el botón positivo con una lambda
        constructor.setPositiveButton("Sí", (DialogInterface.OnClickListener)
                (dialog, which) -> {
                    //Lanzamos el intent de salida
                    startActivity(intent);
                });

        // Hemos de instanciar un Alertdialog, que coma al constructor como parámetro
        //Y luego lanzarlo.
        AlertDialog alert = constructor.create();
        alert.show();






    }

    @Override
   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);

        menu.setHeaderTitle("Opciones");

   }


    //añadimos el menú creado mediante xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbar, menu);
        return true;
    }

    //Definimos las acciones de cada opción del options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.config: //Vamos a la pantalla de configuracióm
                Intent intent = new Intent(this, Configuration.class);

                startActivity(intent);

                return true;
            case R.id.about: //Vamos a la pantalla de informacion

                Intent intentA = new Intent(this, About.class);

                startActivity(intentA);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    //Función que genera las pelis y las inserta en el arraylist de pelis
    private void fillFilms() {

        //Usamos el constructor de películas, le pasamos los parámetros:
        // Nombre de la peli, guardado en un xml
        // Desciopción de la peli, guardada en otro xml
        // imagen, contenida en la carpeta drawable
        // Año (esto está hardcodeado, pero se podría hacer por array de xml)


        //int drawableId = getResources().getIdentifier(getResources().getStringArray(R.array.pelis)[0], "drawable", getPackageName());

        films.add(new Film(getResources().getStringArray(R.array.pelis)[0],
                getResources().getStringArray(R.array.descript)[0],
                  R.drawable.bros,

                 getResources().getIntArray(R.array.years)[0], false
        ));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[1],
                getResources().getStringArray(R.array.descript)[1],
                R.drawable.dwd,
                getResources().getIntArray(R.array.years)[1] , false));


        films.add(new Film(getResources().getStringArray(R.array.pelis)[2],
                getResources().getStringArray(R.array.descript)[2],
                R.drawable.ba,
                getResources().getIntArray(R.array.years)[2]
                , false
        ));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[3],
                getResources().getStringArray(R.array.descript)[3],
                R.drawable.dotn,
                getResources().getIntArray(R.array.years)[3]
                , false));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[4],
                getResources().getStringArray(R.array.descript)[4],
                R.drawable.bp,
                getResources().getIntArray(R.array.years)[4]
                , false));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[5],
                getResources().getStringArray(R.array.descript)[5],
                R.drawable.pitch,
                getResources().getIntArray(R.array.years)[5]
                , false));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[6],
                getResources().getStringArray(R.array.descript)[6],
                R.drawable.nope,
                getResources().getIntArray(R.array.years)[6]
                , false));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[7],
                getResources().getStringArray(R.array.descript)[7],
                R.drawable.horror,
                getResources().getIntArray(R.array.years)[7]
                , false));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[8],
                getResources().getStringArray(R.array.descript)[8],
                R.drawable.every,
                getResources().getIntArray(R.array.years)[8], false));

        films.add(new Film(getResources().getStringArray(R.array.pelis)[9],
                getResources().getStringArray(R.array.descript)[9],
                R.drawable.parasite,
                getResources().getIntArray(R.array.years)[9] , false));

    }
}