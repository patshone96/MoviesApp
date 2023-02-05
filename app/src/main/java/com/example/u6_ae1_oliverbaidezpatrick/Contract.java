package com.example.u6_ae1_oliverbaidezpatrick;

import android.provider.BaseColumns;

public final class Contract {

    private Contract(){}

    public static class newContract implements BaseColumns {

        public static final String TABLE_NAME = "pelis";
        public static final String COLUMN_NAME_TITLE = "titulo";
        public static final String COLUMN_NAME_DESC = "descripcion";
        public static final String COLUMN_NAME_IMAGE = "imagen";
        public static final String COLUMN_NAME_YEAR = "anio";

    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + newContract.TABLE_NAME + " (" +
                    newContract._ID + " INTEGER PRIMARY KEY," +
                    newContract.COLUMN_NAME_TITLE + " TEXT, " +
                    newContract.COLUMN_NAME_DESC + " TEXT, " +
                    newContract.COLUMN_NAME_IMAGE + " INTEGER, " +
                    newContract.COLUMN_NAME_YEAR + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " +
            newContract.TABLE_NAME;

}
