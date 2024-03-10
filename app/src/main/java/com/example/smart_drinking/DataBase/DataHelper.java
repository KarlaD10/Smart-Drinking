package com.example.smart_drinking.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "Smart_Drinking.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_REGISTROS = "registros";

    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_CONSUMO = "agua_consumida";
    private static final String TABLE_RECORDATORIOS = "recordatorios";
    private static final String COLUMN_ID_RECORDATORIO = "id_recordatorio";
    private static final String COLUMN_DIA = "dia";
    private static final String COLUMN_HORA = "hora";
    private static final String COLUMN_MINUTO = "minuto";
    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_REGISTROS =
                "CREATE TABLE IF NOT EXISTS " + TABLE_REGISTROS +
                        "(" + COLUMN_FECHA + " NUMERIC NOT NULL DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')) PRIMARY KEY, " +
                        COLUMN_CONSUMO + " TEXT);";

        String CREATE_TABLE_RECORDATORIOS =
                "CREATE TABLE IF NOT EXISTS " + TABLE_RECORDATORIOS +
                        "(" + COLUMN_ID_RECORDATORIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DIA + " TEXT," +
                        COLUMN_HORA + " TEXT,"+
                        COLUMN_MINUTO + " TEXT);";

        db.execSQL(CREATE_TABLE_REGISTROS);
        db.execSQL(CREATE_TABLE_RECORDATORIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    --------------- REGISTRO DE DATOS ---------------------------
    public void addRegistros(String consumo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvR = new ContentValues();

        cvR.put(COLUMN_CONSUMO, consumo);

        try{
            db.insert(TABLE_REGISTROS, null, cvR);
        }catch (Exception e){
            System.out.println("Excepcion al añadir el registro: " + e);
        }

    }


    public void addRecordatorio(String dia, String hora, String minuto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvR = new ContentValues();

        cvR.put(COLUMN_DIA, dia);
        cvR.put(COLUMN_HORA, hora);
        cvR.put(COLUMN_MINUTO, minuto);

        try{
            db.insert(TABLE_REGISTROS, null, cvR);
        }catch (Exception e){
            System.out.println("Excepcion al añadir el registro: " + e);
        }

    }

}
