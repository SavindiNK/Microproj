package com.savindiniranthara.remote.db;

import android.provider.BaseColumns;

/**
 * Created by Charith on 7/17/2017.
 */

public final class RemoteContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private RemoteContract() {
    }

    /* Inner class that defines the table contents */
    public static class SavedRemotes implements BaseColumns {
        public static final String TABLE_NAME = "saved_remotes";
        public static final String COLUMN_NAME_REMOTE_ID = "remote_id";
        public static final String COLUMN_NAME_BUTTON_ID = "button_id";
        public static final String COLUMN_NAME_RAW_CODE = "raw_code";
    }

    public static class RecentlyOpened implements BaseColumns {
        public static final String TABLE_NAME = "recently_opened";
        public static final String COLUMN_NAME_REMOTE_ID = "remote_id";
    }
}