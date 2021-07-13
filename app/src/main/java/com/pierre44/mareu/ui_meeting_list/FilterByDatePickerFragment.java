package com.pierre44.mareu.ui_meeting_list;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;

import java.util.List;

/**
 * Created by pmeignen on 28/06/2021.
 */
public class FilterByDatePickerFragment extends DialogFragment {

    private MeetingRepository mMeetingRepository;
    private List<Meeting> mMeetings;

    private static final String ARGUMENT_YEAR = "ARGUMENT_YEAR";
    private static final String ARGUMENT_MONTH = "ARGUMENT_MONTH";
    private static final String ARGUMENT_DAY = "ARGUMENT_DAY";
    private DatePickerDialog.OnDateSetListener listener;

    private int year;
    private int dayOfMonth;
    private int monthOfYear;


    public static FilterByDatePickerFragment newInstance(final int year, final int day, final int month) {
        final FilterByDatePickerFragment df = new FilterByDatePickerFragment();
        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_YEAR, year);
        args.putInt(ARGUMENT_MONTH, month);
        args.putInt(ARGUMENT_DAY, day);
        df.setArguments(args);
        return df;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrieveArguments();
    }

    private void retrieveArguments() {
        final Bundle args = getArguments();
        if (args != null) {
            year = args.getInt(ARGUMENT_YEAR);
            dayOfMonth = args.getInt(ARGUMENT_DAY);
            monthOfYear = args.getInt(ARGUMENT_MONTH);
        }
    }

    @Override
    public DatePickerDialog onCreateDialog(final Bundle savedInstanceState) {
        return new DatePickerDialog(getContext(), this.listener, this.year, this.dayOfMonth, this.monthOfYear);
    }

    public void setListener(final ListMeetingActivity listener) {
        this.listener = (DatePickerDialog.OnDateSetListener) listener;
    }
}