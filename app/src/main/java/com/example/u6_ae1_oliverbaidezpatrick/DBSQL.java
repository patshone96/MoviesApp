package com.example.u6_ae1_oliverbaidezpatrick;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBSQL extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION  = 1;
    public static final String DATABASE_NAME = "pelis.db";

    public DBSQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Contract.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Contract.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }


}
