package com.example.solano.tcc_v3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Solano on 14/05/2016.
 */


public class CriaBanco extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "connections.db";
    protected static final String TABLE = "connections";
    protected static final String ID = "id";
    protected static final String NAME = "name";
    protected static final String HOST = "host";
    protected static final String USER = "user";
    protected static final String PASSWORD = "password";
    protected static final String SGBD = "sgbd";
    protected static final int VERSAO = 1;

    public CriaBanco(Context context) {
        super(context, DATABASE_NAME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE + "("
                + ID + "integer primary key autoincrement,"
                + NAME + "text,"
                + HOST + "text,"
                + USER + "text,"
                + PASSWORD + "text,"
                + SGBD + "text"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE);
        onCreate(db);
    }
}