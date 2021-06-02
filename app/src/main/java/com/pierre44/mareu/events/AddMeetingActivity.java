package com.pierre44.mareu.events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.text_input_meeting_topic)
    TextInputLayout meetingTopicInput;
    @BindView(R.id.date_picker_actions)
    DatePicker meetingDatePicker;
    @BindView(R.id.time_picker_action)
    TimePicker meetingTimePicker;
    @BindView(R.id.spinner_room)
    Spinner meetingRoomSpinner;
    @BindView(R.id.spinner_duration)
    Spinner meetingDurationSpinner;
    @BindView(R.id.text_input_email)
    TextInputLayout meetingEmailInput;
    @BindView(R.id.guests_email_list)
    ChipGroup meetingGuestList;
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

    //@OnClick(R.id.buttonValidate)
    //void addMeeting() {
    //    Meeting meeting = new Meeting(System.currentTimeMillis(),
    //            meetingTopicInput.getEditText().toString(),
    //            meetingDatePicker.getDayOfMonth(),
    //            meetingTimePicker.get(),
    //            meetingRoomSpinner.getPrompt(),
    //            meetingDurationSpinner.getPrompt(),
    //            meetingGuestList.getChipSpacingHorizontal(),
    //            );
    //}

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}