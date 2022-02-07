package com.example.basicbankappfversion.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserHelper extends SQLiteOpenHelper {

    String TABLE_NAME = UserContract.UserEntry.TABLE_NAME;
    //Name of database file.
    private static final String DATABASE_NAME = "User.db";
    //Version of database .
    private static final int DATABASE_VERSION = 1;

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + "("
                + UserContract.UserEntry.USER_ACCOUNT_NUMBER_COL + " INTEGER, "
                + UserContract.UserEntry.USER_NAME_COL + " VARCHAR, "
                + UserContract.UserEntry.USER_EMAIL_COL + " VARCHAR, "
                + UserContract.UserEntry.USER_IFSC_CODE_COL + " VARCHAR, "
                + UserContract.UserEntry.USER_PHONE_NO_COL + " VARCHAR, "
                + UserContract.UserEntry.USER_ACCOUNT_BALANCE_COL + " INTEGER NOT NULL);";
        //execute the SQL Create table statement.
        db.execSQL(SQL_CREATE_USER_TABLE);

        //insert into table
        db.execSQL("insert into " + TABLE_NAME + " values(7800,'Mahmoud Elsayed','mahmoudelsayed@gmail.com','7500','01093729626',15000)");
        db.execSQL("insert into " + TABLE_NAME + " values(5862,'Mohamed Ahmed','mohamedahmed@gmail.com','1258','01015729882',5000)");
        db.execSQL("insert into " + TABLE_NAME + " values(7895,'Adel abdo','adel_abdo@gmail.com','8890','01075729671',3000)");
        db.execSQL("insert into " + TABLE_NAME + " values(1752,'Eslam Ahmed','e_ahmed123@gmail.com','7752','01093742726',800)");
        db.execSQL("insert into " + TABLE_NAME + " values(3304,'Ahmed Elsayed','Ahmedelsayed@gmail.com','3669','01093712326',7500)");
        db.execSQL("insert into " + TABLE_NAME + " values(4487,'Ketty Perry','kettyperryy@gmail.com','1207','01091527527',6500)");
        db.execSQL("insert into " + TABLE_NAME + " values(8870,'Esraa Medhatt','esraa456@gmail.com','4522','01093789505',4500)");
        db.execSQL("insert into " + TABLE_NAME + " values(7803,'Will Smith','willsmith12@gmail.com','6582','01093729756',2500)");
        db.execSQL("insert into " + TABLE_NAME + " values(7821,'Manar Khaled','manar125@gmail.com','1203','01075699754',10500)");
        db.execSQL("insert into " + TABLE_NAME + " values(1233,'Patrik Jhon','patrikk@gmail.com','2236','01045699757',8500)");
        db.execSQL("insert into " + TABLE_NAME + " values(1455,'Madi Saad','ms2019@gmail.com','6692','01056789754',12500)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserContract.UserEntry.TABLE_NAME, null);
        return cursor;
    }

    public void updateAmount(int accountNo, int amount) {
        Log.d("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + UserContract.UserEntry.TABLE_NAME + " set " + UserContract.UserEntry.USER_ACCOUNT_BALANCE_COL + " = " + amount + " where " +
                UserContract.UserEntry.USER_ACCOUNT_NUMBER_COL + " = " + accountNo);
    }
}

