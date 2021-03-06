package com.example.solano.tcc_v3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Solano on 14/05/2016.
 */


public class CriaBanco extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_connector.db";
    protected static final String TABLE = "connections";
    protected static final String ID = "_id";
    protected static final String NAME = "name";
    protected static final String HOST = "host";
    protected static final String USER = "user";
    protected static final String PASSWORD = "password";
    protected static final String SGBD = "sgbd";
    private static final int VERSION = 1;


    public CriaBanco(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


            public void onCreate (SQLiteDatabase db){
                String sql = "CREATE TABLE " + TABLE + "("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + NAME + " TEXT,"
                        + HOST + " TEXT,"
                        + USER + " TEXT,"
                        + PASSWORD + " TEXT,"
                        + SGBD + " TEXT"
                        + ")";
                db.execSQL(sql);
            }


        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + TABLE);
            onCreate(db);
        }
}



