package com.example.agendaf.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {

    String TABLE_HORARIO = "CREATE TABLE MATERIA(materia text PRIMARY KEY, maestro text)";
    String TABLE_TAREA = "CREATE TABLE TAREA(id_tarea int PRIMARY KEY, descripcion text,materia text, Constraint fk_materia FOREIGN KEY (materia) references MATERIA(materia))";
    String TABLE_NOTI = "CREATE TABLE NOTI(id_noti int PRIMARY KEY not null, fecha DATE,id_tarea int, Constraint fk_id_tarea FOREIGN KEY (id_tarea) references TAREA(id_tarea))";

    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(TABLE_HORARIO);
        bd.execSQL(TABLE_TAREA);
        bd.execSQL(TABLE_NOTI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {

    }
}