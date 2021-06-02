package com.pierre44.mareu.events;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.repository.MeetingRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingActivity extends AppCompatActivity {


    @BindView(R.id.text_input_meeting_topic)
    TextInputLayout meetingTopicInput;
    @BindView(R.id.date_picker_actions)
    DatePicker meetingDatePicker;
    @BindView(R.id.time_picker_action)
    TimePicker meetingTimePicker;
    @BindView(R.id.spinner_duration)
    Spinner meetingDurationpinner;
    @BindView(R.id.spinner_room)
    Spinner meetingRoomSpinner;
    @BindView(R.id.text_input_email)
    TextInputLayout meetingEmailInput;
    @BindView(R.id.guests_email_list)
    ListAdapter meetingGuestList;
    @BindView(R.id.buttonValidate)
    MaterialButton meetingValidate;

    private MeetingRepository mMeetingRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMeetingRepository = DI.getMeetingRepository();
    }

}