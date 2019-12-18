package com.example.agendaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.agendaf.SQL.ConexionSQLite;

public class Horario extends AppCompatActivity {

    private EditText etMateria,etMaestro;
    private int cambio;
    private ImageButton regresa,guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

        etMaestro = (EditText)findViewById(R.id.txtMaestro);
        etMateria = (EditText)findViewById(R.id.txtMateria);

        regresa = (ImageButton) findViewById(R.id.btnR);
        guardar = (ImageButton) findViewById(R.id.btnG);

        if(MainActivity.cambio == 1){//sistemas
            regresa.setImageResource(R.mipmap.regresar);
            guardar.setImageResource(R.mipmap.guardar);
        }else if (MainActivity.cambio ==0){//iconos
            regresa.setImageResource(R.mipmap.ic_undo);
            guardar.setImageResource(R.mipmap.ic_save);
        }
    }

    public void regresar(View view){
        Intent regresar = new Intent(this, Materias.class);
        startActivity(regresar);
    }

    // SQLite
    public void registro(View view){
        ConexionSQLite admin = new ConexionSQLite(this,"Escuela",null,1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        //recivo datos
        String Maestro = etMaestro.getText().toString();
        String Materia = etMateria.getText().toString();

        //verifico que Materia no este vacio
        if(!Materia.isEmpty()){
            //contenedor de variables
            ContentValues registro = new ContentValues();

            //los guardo
            registro.put("materia",Materia);
            registro.put("maestro",Maestro);

            //esocgo la tabla
            BaseDatos.insert("MATERIA",null,registro);
            BaseDatos.close();

            etMaestro.setText("");
            etMateria.setText("");
            Toast.makeText(this,"Registro",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this,"Llena el campo Materia",Toast.LENGTH_SHORT).show();
        }
    }


}
