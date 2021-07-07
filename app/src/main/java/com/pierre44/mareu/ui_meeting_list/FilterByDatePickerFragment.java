package com.pierre44.mareu.ui_meeting_list;

import android.app.Dialog;
import android.app.TimePickerDialog;
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

    private static final String ARGUMENT_HOUR = "ARGUMENT_HOUR";
    private static final String ARGUMENT_MINUTE = "ARGUMENT_MINUTE";
    private static final String ARGUMENT_IS_24_HOURS = "ARGUMENT_IS_24_HOURS";
    private TimePickerDialog.OnTimeSetListener listener;

    private int hourOfDay;
    private int minute;
    private boolean is24Hours;


    public static FilterByDatePickerFragment newInstance(final int hour, final int minute, final boolean is24Hours) {
        final FilterByDatePickerFragment df = new FilterByDatePickerFragment();
        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_HOUR, hour);
        args.putInt(ARGUMENT_MINUTE, minute);
        args.putBoolean(ARGUMENT_IS_24_HOURS, is24Hours);
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
            hourOfDay = args.getInt(ARGUMENT_HOUR);
            minute = args.getInt(ARGUMENT_MINUTE);
            is24Hours = args.getBoolean(ARGUMENT_IS_24_HOURS);
        }
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        return new TimePickerDialog(getContext(), this.listener, this.hourOfDay, this.minute, this.is24Hours);
    }

    public void setListener(final ListMeetingActivity listener) {
        this.listener = (TimePickerDialog.OnTimeSetListener) listener;
    }
}