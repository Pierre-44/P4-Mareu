package com.pierre44.mareu.ui_meeting_list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.repository.DummyMeetingGenerator;
import com.pierre44.mareu.repository.MeetingRepository;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.text_input_meeting_topic)
    TextInputLayout mMeetingTopicInput;
    @BindView(R.id.date_picker_actions)
    DatePicker mMeetingDatePicker;
    @BindView(R.id.time_picker_action)
    TimePicker mMeetingTimePicker;
    @BindView(R.id.spinner_room_action)
    Spinner mMeetingRoomSpinner;
    @BindView(R.id.spinner_duration_action)
    Spinner mMeetingDurationSpinner;
    @BindView(R.id.text_input_email)
    TextInputLayout mMeetingEmailInput;
    @BindView(R.id.guests_email_list)
    ChipGroup mMeetingGuestList;
    @BindView(R.id.buttonValidate)
    MaterialButton mMeetingValidate;

    private MeetingRepository mMeetingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meeting_activity);
        ButterKnife.bind(this);
        mMeetingRepository = DI.getMeetingRepository();

        // TODO : implement init();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMeetingRepository = DI.getMeetingRepository();
        final List<String> roomList = Arrays.asList(DummyMeetingGenerator.DUMMY_USERS_LIST.toArray(new String[0]));
        final List<String> durationList = Arrays.asList("00:15","00:30","00:45","00:00","01:00","01:15","01:30","01:45","02:00");

        // meetingDatePicker
        Button dateButton = (Button) findViewById(R.id.date_picker_actions);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker" );
            }
        });

        // meetingTimePicker
        Button timeButton = (Button) findViewById(R.id.time_picker_action);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new DatePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker" );
            }
        });

        // TODO : implement Spinners to be finished

        // meetingRoomSpinner
        final Spinner roomSpinner = findViewById(R.id.spinner_room_action);

        RoomSpinnerAdapter roomSpinnerAdapter = new RoomSpinnerAdapter(getApplicationContext(),R.layout.room_dropdown_item, roomList);
        roomSpinnerAdapter.setDropDownViewResource(R.layout.room_dropdown_item);
        roomSpinner.setAdapter(roomSpinnerAdapter);

        // meetingDurationSpinner
        final Spinner durationSpinner = findViewById(R.id.spinner_duration_action);

        TimeSpinnerAdapter timeSpinnerAdapter = new TimeSpinnerAdapter(getApplicationContext(),R.layout.time_dropdown_item, durationList);
        timeSpinnerAdapter.setDropDownViewResource(R.layout.time_dropdown_item);
        durationSpinner.setAdapter(timeSpinnerAdapter);
    }

    // TODO : methode a completer
/**
    @OnClick(R.id.CreateMeetingButton)
    void addNeighbour() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                mMeetingTopicInput.getEditText().getText().toString(),
                mMeetingDatePicker.getDate(),
                mMeetingTimePicker.getTime(),
                mMeetingDurationSpinner.getSelectedItem(),
                mMeetingRoomSpinner.getSelectedItem(),
                mMeetingGuestList.getChipSpacingHorizontal()
        );
        mMeetingRepository.createMeeting(meeting);
        finish();
    }
**/
    // Set meetingDatePicker
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textViewDate = (TextView) findViewById(R.id.date_picker_actions); // Todo : set text instate of text button ?
        textViewDate.setText(currentDateString);
    }

    // Set meetingTimePicker
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textViewTime = (TextView) findViewById(R.id.time_picker_action);
        textViewTime.setText(hourOfDay + ":" + minute);
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, CreateMeetingActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }

}