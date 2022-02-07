package com.example.basicbankappfversion.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TransferHelper extends SQLiteOpenHelper {

    //Name of database file.
    private static final String DATABASE_NAME = "Transfer.db";
    //Version of database.
    private static final int DATABASE_VERSION = 1;

    public TransferHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TRANSFER_TABLE = "CREATE TABLE " + TransferContract.TransferEntry.TABLE_NAME + " ("
                + TransferContract.TransferEntry.FROM_NAME_COL + " VARCHAR, "
                + TransferContract.TransferEntry.TO_NAME_COL + " VARCHAR, "
                + TransferContract.TransferEntry.AMOUNT_COL + " INTEGER, "
                + TransferContract.TransferEntry.STATUS_COL + " INTEGER);";

        db.execSQL(SQL_CREATE_TRANSFER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TransferContract.TransferEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TransferContract.TransferEntry.TABLE_NAME, null);
        return cursor;
    }

    public boolean insetTransfer(String fromName, String toName, int amount, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TransferContract.TransferEntry.FROM_NAME_COL, fromName);
        contentValues.put(TransferContract.TransferEntry.TO_NAME_COL, toName);
        contentValues.put(TransferContract.TransferEntry.AMOUNT_COL, amount);
        contentValues.put(TransferContract.TransferEntry.STATUS_COL, status);

        long result = db.insert(TransferContract.TransferEntry.TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
