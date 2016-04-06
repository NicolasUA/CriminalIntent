package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;

public class TimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.time";

    public static Intent newIntent(Context packageContext, Date date) {
        Intent intent = new Intent(packageContext, TimeActivity.class);
        intent.putExtra(EXTRA_DATE, date);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Date date = (Date) getIntent().getSerializableExtra(EXTRA_DATE);
        return TimePickerFragment.newInstance(date);
    }
}
