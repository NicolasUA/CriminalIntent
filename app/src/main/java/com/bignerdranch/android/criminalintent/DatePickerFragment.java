package com.bignerdranch.android.criminalintent;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    private static final String ARG_DATE = "date";

    private TextView mTitleTextView;
    private DatePicker mDatePicker;
    private Button mOkButton;
    private Calendar mCalendar;

    public static DatePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = initView();

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }

    @NonNull
    private View initView() {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        mTitleTextView = (TextView) view.findViewById(R.id.dialog_date_title_text_view);
        mTitleTextView.setText(R.string.date_picker_title);

        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, month, day, null);

        mOkButton = (Button) view.findViewById(R.id.dialog_date_ok_button);
        mOkButton.setText(android.R.string.ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendar.set(Calendar.YEAR, mDatePicker.getYear());
                mCalendar.set(Calendar.MONTH, mDatePicker.getMonth());
                mCalendar.set(Calendar.DAY_OF_MONTH, mDatePicker.getDayOfMonth());
                sendResult(Activity.RESULT_OK, mCalendar.getTime());
                if (getDialog() != null) {
                    getDialog().dismiss();
                } else {
                    getActivity().finish();
                }
            }
        });

        return view;
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
