package com.example.solano.tcc_v3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Solano on 14/05/2016.
 */
public class ControlaBanco  {

    protected SQLiteDatabase db;
    protected CriaBanco banco;

    public ControlaBanco(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(String name, String host, String user, String password, String sgbd){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NAME, name);
        valores.put(CriaBanco.HOST, host);
        valores.put(CriaBanco.USER, user);
        valores.put(CriaBanco.PASSWORD, password);
        valores.put(CriaBanco.SGBD, sgbd);

        resultado = db.insert(CriaBanco.TABLE, null, valores);
        db.close();

        if (resultado ==-1)
            return "Error while Registering";
        else
            return "Successfully Registered";


    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NAME,banco.SGBD};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABLE, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
