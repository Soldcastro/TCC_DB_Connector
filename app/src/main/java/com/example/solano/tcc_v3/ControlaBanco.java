package com.example.solano.tcc_v3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Solano on 14/05/2016.
 */
public class ControlaBanco  {

    private SQLiteDatabase db;
    private CriaBanco banco;

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

}
