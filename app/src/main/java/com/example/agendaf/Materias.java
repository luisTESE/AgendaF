package com.example.agendaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendaf.SQL.ConexionSQLite;

public class Materias extends AppCompatActivity {

    private TextView txtVW;
    private Spinner spi;
    private int tamano =0;
    private int cambio;
    private ImageButton consulta,ingreMa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        txtVW = (TextView) findViewById(R.id.txtView);
        spi = (Spinner) findViewById(R.id.spinner2);
        consulSpinner();

        consulta = (ImageButton) findViewById(R.id.btnConUno);
        ingreMa = (ImageButton) findViewById(R.id.btnRMat);

        if(MainActivity.cambio == 1){//sistemas
            consulta.setImageResource(R.mipmap.consul);
            ingreMa.setImageResource(R.mipmap.horario);
        }else if (MainActivity.cambio ==0){//iconos
            consulta.setImageResource(R.mipmap.ic_file);
            ingreMa.setImageResource(R.mipmap.ic_tea);
        }

    }

    public void Horario(View view){
        Intent regresar = new Intent(this, Horario.class);
        startActivity(regresar);
    }

    // tarea
    public void Tarea(View view){
        Intent regresar = new Intent(this,ConsultarSis.class);
        startActivity(regresar);
    }

    // consultar spinner
    public void consulSpinner(){
        ConexionSQLite admin = new ConexionSQLite(this,"Escuela",null,1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        //el selec
        String query = "SELECT materia FROM MATERIA";
        Cursor fila = BaseDatos.rawQuery(query,null);

        // si la columa esta vacia
        if(fila.getCount() != 0){

            int i=0;
            tamano =0;
            tamano = fila.getCount();
            String[] datos = new String[tamano];
            String aux="";

            while(fila.moveToNext()){
                //aux += fila.getString(0)+"\n";
                datos[i] = fila.getString(0);
                i++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
            spi.setAdapter(adapter);
            BaseDatos.close();
        }else{
            Toast.makeText(this,"ingresa tu Horario",Toast.LENGTH_SHORT).show();
        }
    }


    public void ConsultarUno(View view){
        ConexionSQLite admin = new ConexionSQLite(this,"Escuela",null,1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String consulta = txtVW.getText().toString();

        txtVW.setText("");

        String Materia = spi.getSelectedItem().toString();

        String query = "SELECT  t.descripcion,n.fecha from NOTI n inner join tarea t on n.id_tarea = t.id_tarea WHERE t.materia = '"+Materia+"' order by n.fecha ASC";
        //String query = "SELECT descripcion FROM TAREA where materia='"+Materia+"'";
        Cursor fila = BaseDatos.rawQuery(query,null);

        if(fila.getCount() != 0){
            String aux="";

            while(fila.moveToNext()){
                aux += fila.getString(0)+"  "+fila.getString(1)+"\n";

            }
            txtVW.setText(aux);
            BaseDatos.close();
            Toast.makeText(this,"existe",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No existe",Toast.LENGTH_SHORT).show();
        }

    }


}
