package com.dream.mvpdemo.model;

import com.dream.mvpdemo.model.db.AppDbHelper;
import com.dream.mvpdemo.model.preference.AppPreferenceHelper;

public class DataManager implements AppDbHelper, AppPreferenceHelper {
    private AppDbHelper mAppDbHelper;
    private AppPreferenceHelper mAppPreferenceHelper;

    public DataManager(AppDbHelper mAppDbHelper, AppPreferenceHelper appPreferenceHelper) {
        this.mAppDbHelper = mAppDbHelper;
        this.mAppPreferenceHelper = appPreferenceHelper;
    }

    @Override
    public void testDb() {
        mAppDbHelper.testDb();
    }

    @Override
    public void dbTest() {
        mAppDbHelper.dbTest();
    }

    @Override
    public void testPreference() {
        mAppPreferenceHelper.testPreference();
    }
}
