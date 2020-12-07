package com.dream.mvpdemo.model.db;

import android.util.Log;

public class DbHelper implements AppDbHelper {
    @Override
    public void testDb() {
        Log.d("print", "数据库操作 testDb");
    }

    @Override
    public void dbTest() {
        Log.d("print", "数据库操作 dbTest");
    }
}
