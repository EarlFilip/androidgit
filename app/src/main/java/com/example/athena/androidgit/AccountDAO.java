package com.example.athena.androidgit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class AccountDAO {
    private DBHelper dbHelper;

    public AccountDAO(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public long insert(Account account)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.EntradaBanda.COLUMN_EMAIL, account.getEmail());
        values.put(DBHelper.EntradaBanda.COLUMN_PASSWORD, account.getPassword());

        return db.insert(DBHelper.EntradaBanda.TABLE_NAME, null, values);
    }

    public List<Account> select()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] colunas =
                {DBHelper.EntradaBanda._ID,
                        DBHelper.EntradaBanda.COLUMN_EMAIL,
                        DBHelper.EntradaBanda.COLUMN_PASSWORD};
        Cursor cursor = db.query(DBHelper.EntradaBanda.TABLE_NAME, colunas, null, null, null, null, null);
        List<Account> accounts = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do
            {
                Account account = new Account();
                account.setId(cursor.getLong(cursor.getColumnIndex(DBHelper.EntradaBanda._ID)));
                account.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.EntradaBanda.COLUMN_EMAIL)));
                account.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.EntradaBanda.COLUMN_PASSWORD)));

                accounts.add(account);
            } while (cursor.moveToNext());
        }

        return accounts;
    }

    public Boolean searchByEmail(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String condicao = DBHelper.EntradaBanda.COLUMN_EMAIL + " = ?";
        String[] condicaoArg = {String.valueOf(email)};

        int rows = db.query(DBHelper.EntradaBanda.TABLE_NAME, condicao, condicaoArg);

        return rows > 0 ? true : false;
    }

    public void returnAccount(Boolean verification){
        Account account = null;
    }

    public Boolean update(Account account)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.EntradaBanda.COLUMN_EMAIL, account.getEmail());
        values.put(DBHelper.EntradaBanda.COLUMN_PASSWORD, account.getPassword());

        String condicao = DBHelper.EntradaBanda._ID + " = ?";
        String[] condicaoArg = {String.valueOf(account.getId())};
        int rows = db.update(DBHelper.EntradaBanda.TABLE_NAME, values, condicao, condicaoArg);

        return rows > 0 ? true : false;
    }

    public Boolean delete(long id)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String condicao = DBHelper.EntradaBanda._ID + " = ?";
        String[] condicaoArg = {String.valueOf(id)};

        int rows = db.delete(DBHelper.EntradaBanda.TABLE_NAME, condicao, condicaoArg);

        return rows > 0 ? true : false;
    }
}
