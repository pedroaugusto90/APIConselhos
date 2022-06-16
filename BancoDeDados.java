package com.example.webapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    public static final String Tabela = "tbConselhos";
    public static final String Coluna_conselho = "conselhos";

    public static final String DATABASE_Nome = "BDConselho.db";
    public static final int DATABASE_VERSION = 1;

    public BancoDeDados(Context context){
        super(context, DATABASE_Nome, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Criacao_tabela = "create table " + Tabela + "(" + Coluna_conselho + " text primary key);";
        db.execSQL(Criacao_tabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    void addConselho(Conselho conselho ){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Coluna_conselho, conselho.getConselho());

        db.insert(Tabela, null, values);

    }

    public List<Conselho> ListaTodosConselhos(){
        List<Conselho>  ListaConselhos= new ArrayList<Conselho>();


        String query= "SELECT * FROM " + Tabela;

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor c =db.rawQuery(query,null);

        if(c.moveToFirst()){
            do{
                Conselho conselho= new Conselho();
                conselho.setConselho(c.getString(0));

                ListaConselhos.add(conselho);

            }while (c.moveToNext());
        }
        return  ListaConselhos;
    }


}
