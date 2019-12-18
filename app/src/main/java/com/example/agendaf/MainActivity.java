package com.example.agendaf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static int cambio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Activity sistemas
    public void sistema(View view){
        cambio = 1;
        Intent sistemas = new Intent(this,Materias.class);
        startActivity(sistemas);
    }
    // Activity Iconos
    public void iconos(View view){
        cambio = 0;
        Intent iconos = new Intent(this,Materias.class);
        startActivity(iconos);
    }
}
