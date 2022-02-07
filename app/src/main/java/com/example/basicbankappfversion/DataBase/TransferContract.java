package com.example.basicbankappfversion.DataBase;

import android.provider.BaseColumns;

public class TransferContract {
    public TransferContract() {}

    public static final class TransferEntry implements BaseColumns {
        //Table name for Transfers.
        public static final String TABLE_NAME = "Transfer_table";

        //Table Fields.
        public static final String _ID = BaseColumns._ID;
        public static final String FROM_NAME_COL = "from_name";
        public static final String TO_NAME_COL = "to_name";
        public static final String AMOUNT_COL = "amount";
        public static final String STATUS_COL = "status";

    }
}

