package com.example.agendaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agendaf.SQL.ConexionSQLite;

public class ConsultarSis extends AppCompatActivity {

    private ImageButton ingreTare;
    private TextView txtVW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_sis);

        txtVW = (TextView) findViewById(R.id.txtConsulta);
        consultar();

        ingreTare = (ImageButton) findViewById(R.id.btnRTarea);
        if(MainActivity.cambio == 1){//sistemas
            ingreTare.setImageResource(R.mipmap.tarea);
        }else if (MainActivity.cambio ==0){//iconos
            ingreTare.setImageResource(R.mipmap.ic_pen);
        }


    }

    // rMateria
    public void Materia(View view){
        Intent regresar = new Intent(this,Materias.class);
        startActivity(regresar);
    }

    public void IngreTarea(View view){
        Intent regresar = new Intent(this,Tareas.class);
        startActivity(regresar);
    }

    //Consultar
    public void consultar(){
        ConexionSQLite admin = new ConexionSQLite(this,"Escuela",null,1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        String consulta = txtVW.getText().toString();

        txtVW.setText("");

        //el select
        //String query = "SELECT descripcion FROM TAREA";
        String query ="SELECT  t.descripcion,n.fecha from NOTI n inner join tarea t on n.id_tarea = t.id_tarea order by n.fecha ASC";
        Cursor fila = BaseDatos.rawQuery(query,null);

        // si la columa esta vacia
        if(fila.getCount() != 0){
            String aux="";

            while(fila.moveToNext()){
                aux += fila.getString(0)+"  "+fila.getString(1)+"\n";

            }
            txtVW.setText(aux);
            BaseDatos.close();
            //Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No hay tareas",Toast.LENGTH_SHORT).show();
        }

    }
}
