package com.example.basicbankappfversion.DataBase;

import android.provider.BaseColumns;

public class UserContract {
    public UserContract() {}

    public static final class UserEntry implements BaseColumns {
        //Table name for Users.
        public static final String TABLE_NAME = "user";

        //Table Fields.
        public static final String _ID = BaseColumns._ID;
        public static final String USER_NAME_COL = "name";
        public static final String USER_ACCOUNT_NUMBER_COL = "accountNo";
        public static final String USER_EMAIL_COL = "email";
        public static final String USER_IFSC_CODE_COL = "ifscCode";
        public static final String USER_PHONE_NO_COL = "phoneNo";
        public static final String USER_ACCOUNT_BALANCE_COL = "balance";
    }
}
