package com.example.agendaf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendaf.SQL.ConexionSQLite;

import java.util.Calendar;

public class Tareas extends AppCompatActivity {

    private EditText etDescrip;
    private Spinner spi;
    private int tamano =0;
    private int dia,mes,ano;
    private TextView calendario;
    private ImageButton guardar,regre,calen;

    public static final int TIPO_DIALOGO =0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        calendario = (TextView) findViewById(R.id.txtcale);
        etDescrip = (EditText) findViewById(R.id.txtDes);
        spi = (Spinner) findViewById(R.id.spinner);
        consulSpinner();

        guardar = (ImageButton) findViewById(R.id.btnG2);
        regre = (ImageButton) findViewById(R.id.btnR2);
        calen = (ImageButton) findViewById(R.id.btnCalendario);
        if(MainActivity.cambio == 1){//sistemas
            guardar.setImageResource(R.mipmap.guardar);
            regre.setImageResource(R.mipmap.regresar);
            calen.setImageResource(R.mipmap.ic_calendar);
        }else if (MainActivity.cambio ==0){//iconos
            guardar.setImageResource(R.mipmap.ic_save);
            regre.setImageResource(R.mipmap.ic_undo);
            calen.setImageResource(R.mipmap.ic_calendar);
        }

        Calendar ca = Calendar.getInstance();
        dia = ca.get(Calendar.DAY_OF_MONTH);
        mes = ca.get(Calendar.MONTH);
        ano = ca.get(Calendar.YEAR);

        mostrarFecha();
    }

    public void mostrarFecha(){
        calendario.setText(dia+"/"+mes+"/"+ano);
    }
    public void cons(View view){
        Intent regresar = new Intent(this, ConsultarSis.class);
        startActivity(regresar);
    }

    public void Calenadario(View view){
        //calendario.setText("");
        DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int anio, int mese, int diaDelMes) {
                calendario.setText(diaDelMes+"/"+mese+"/"+anio);
            }
        };

        DatePickerDialog data = new DatePickerDialog(this,listenerDeDatePicker,ano,mes,dia);
        data.show();
       
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
            Toast.makeText(this,"No hay datos en la materia",Toast.LENGTH_SHORT).show();
        }
    }


    //Guardar
    public void Guardar(View view){
        if(tamano != 0){
            ConexionSQLite admin = new ConexionSQLite(this,"Escuela",null,1);
            SQLiteDatabase BaseDatos = admin.getWritableDatabase();

            // cuenta de datos para id
            String query = "SELECT * FROM TAREA;";
            Cursor fila = BaseDatos.rawQuery(query,null);
            int id=0;
            id = fila.getCount();
            id++;


            //recivo datos
            // id
            String Materia = spi.getSelectedItem().toString();
            String descripcion = etDescrip.getText().toString();

            String fecha = calendario.getText().toString();

            //verifico que Materia no este vacio
            if(!descripcion.isEmpty()){
                //contenedor de variables
                ContentValues registro = new ContentValues();
                ContentValues Noti = new ContentValues();

                //los guardo
                registro.put("id_tarea",id);
                registro.put("descripcion",descripcion);
                registro.put("materia",Materia);

                Noti.put("id_noti",id);
                Noti.put("fecha",fecha);
                Noti.put("id_tarea",id);

                //esocgo la tabla
                BaseDatos.insert("TAREA",null,registro);
                BaseDatos.insert("NOTI",null,Noti);
                BaseDatos.close();

                etDescrip.setText("");
                calendario.setText("");
                Toast.makeText(this,"Registro",Toast.LENGTH_SHORT).show();

                // tabla de noti
            }else
            {
                Toast.makeText(this,"Llena el campo Materia",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Agrega Materias Primero",Toast.LENGTH_SHORT).show();
        }

    }
}
