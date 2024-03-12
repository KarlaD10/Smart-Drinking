package com.example.smart_drinking.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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
    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // ojo, no es eficiente aún la bd porque no están los tipos de datos que quiero para hacer uniregistro de día
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
    public long addRegistros(String consumo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvR = new ContentValues();

        cvR.put(COLUMN_CONSUMO, consumo);

        long result = db.insert(TABLE_REGISTROS, null, cvR);
        return result;
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

    public int readProgreso(){
        SQLiteDatabase db = this.getReadableDatabase();
        String aguaConsumida;

        Cursor cursor = db.rawQuery("SELECT SUM(registros.agua_consumida) AS ac FROM registros WHERE strftime('%d%m%Y', 'now', 'localtime')=strftime('%d%m%Y', registros.fecha)", null);
        try{
            if (cursor.moveToFirst()) {
               aguaConsumida = cursor.getString(cursor.getColumnIndexOrThrow("ac"));
            } else {
                aguaConsumida = "-1";
            }
            cursor.close();
        }catch (Exception e){
            aguaConsumida = "-1";
        }
        // Intentar convertir la cadena a entero
        try {
            return Integer.parseInt(aguaConsumida);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return -1; // Devolver un valor predeterminado en caso de error
        }
    }

    public int readWeeklyIntake(){
        SQLiteDatabase db = this.getReadableDatabase();
        String aguaConsumida = "-1";
        Calendar calendar = Calendar.getInstance();

        // Formatea la fecha en el formato específico que deseas ("%02d%02d%04d")
        // Nota que aquí usamos "ddMMyyyy" para lograr ese formato
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        String formattedDate = sdf.format(calendar.getTime());
        try {
            Cursor cursor = db.rawQuery("SELECT AVG(registros.agua_consumida) AS ac " +
                    "FROM registros " +
                    "WHERE fecha BETWEEN strftime('now', 'weekday 0', '-6 days') " +
                    "AND strftime('now', 'weekday 0') ", null);
            if (cursor.moveToFirst()) {
                aguaConsumida = cursor.getString(cursor.getColumnIndexOrThrow("ac"));
            }

        }catch (Exception e){
            return -1;
        }
        return Integer.parseInt(aguaConsumida);
    }
    public int readConsumoDia(String fecha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String aguaConsumida = "-1"; // Inicializar con un valor por defecto

        try {
            Cursor cursor = db.rawQuery("SELECT SUM(registros.agua_consumida) AS ac FROM registros WHERE strftime('%d%m%Y', 'now', 'localtime')='" + fecha + "'", null);

            if (cursor.moveToFirst()) {
                aguaConsumida = cursor.getString(cursor.getColumnIndexOrThrow("ac"));
            }

            cursor.close();
        } catch (Exception e) {
            // Manejar la excepción si es necesario
            e.printStackTrace();
        }

        // Intentar convertir la cadena a entero
        try {
            return Integer.parseInt(aguaConsumida);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return -1; // Devolver un valor predeterminado en caso de error
        }
    }


}
