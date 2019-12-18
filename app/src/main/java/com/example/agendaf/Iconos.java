package com.example.agendaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Iconos extends AppCompatActivity {

    private int cambio;
    private ImageButton horario,consulta,tareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iconos);

        cambio = MainActivity.cambio;
        Toast.makeText(this,String.valueOf(cambio),Toast.LENGTH_LONG).show();

        horario = (ImageButton) findViewById(R.id.btHorario);
        consulta = (ImageButton) findViewById(R.id.btnConsu);
        tareas = (ImageButton) findViewById(R.id.btnTarea);
        if(cambio == 1){//sistemas
            //tareas.setImageResource(R.mipmap);
            //horario.setImageResource(R.mipmap);
            //consulta.setImageResource(R.mipmap);
        }else if (cambio ==0){//iconos
            //tareas.setImageResource(R.mipmap);
            //horario.setImageResource(R.mipmap);
            //consulta.setImageResource(R.mipmap);
        }
    }

    // Activity Horario
    public void horario(View view){
        Intent horario = new Intent(this,Horario.class);
        startActivity(horario);
    }
    // Activity Consultar
    public void consultar(View view){
        Intent tareas = new Intent(this,ConsultarSis.class);
        startActivity(tareas);
    }
    // Activity Tareas
    public void tareas(View view){
        Intent tareas = new Intent(this,Tareas.class);
        startActivity(tareas);
    }


}
