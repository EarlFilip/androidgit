package com.example.athena.androidgit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DBHelper extends SQLiteOpenHelper {
    public static final String BASE_NAME = "dbaccount.db";
    public static final Integer DB_VERSION = 1;

    public static class EntradaBanda implements BaseColumns {
        public static final String COLUMN_EMAIL = "email";

        public static final String COLUMN_PASSWORD = "password";

        public static final String TABLE_NAME = "account";

        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME +
                        "(" +
                        _ID + " integer primary key, " +
                        COLUMN_EMAIL + " text, " +
                        COLUMN_PASSWORD + " text)";

        private static final String REMOVER_TABELA =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public DBHelper(Context context) {
        super(context, BASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EntradaBanda.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EntradaBanda.REMOVER_TABELA);
        onCreate(db);
    }
}
