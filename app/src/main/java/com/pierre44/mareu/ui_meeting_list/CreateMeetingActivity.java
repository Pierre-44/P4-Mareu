package com.pierre44.mareu.ui_meeting_list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.repository.DummyGenerator;
import com.pierre44.mareu.repository.MeetingRepository;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.text_input_meeting_topic)
    TextInputEditText mMeetingTopicInput;
    @BindView(R.id.date_picker_actions)
    Button mMeetingDatePicker;
    @BindView(R.id.time_picker_action)
    Button mMeetingTimePicker;
    @BindView(R.id.spinner_room_action)
    Spinner mMeetingRoomSpinner;
    @BindView(R.id.spinner_duration_action)
    Spinner mMeetingDurationSpinner;
    @BindView(R.id.text_input_email)
    TextInputEditText mMeetingEmailInput;
    @BindView(R.id.guests_email_list)
    ScrollView mMeetingGuestList;
    @BindView(R.id.buttonValidate)
    MaterialButton mMeetingValidate;

    private MeetingRepository mMeetingRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meeting_activity);
        ButterKnife.bind(this);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMeetingRepository = DI.getMeetingRepository();

        // init Pickers And Spinners methode
        initPickersAndSpinners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // init Pickers And Spinners methode
    private void initPickersAndSpinners() {
        //init roomList & durationList
        final List<Room> roomList = DummyGenerator.DUMMY_ROOMS;
        final List<String> durationList = Arrays.asList("00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00");

        // meetingDatePicker
        Button dateButton = (Button) findViewById(R.id.date_picker_actions);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vd) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        // meetingTimePicker
        Button timeButton = (Button) findViewById(R.id.time_picker_action);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vt) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        // meetingRoomSpinner
        final Spinner roomSpinner = findViewById(R.id.spinner_room_action);
        RoomSpinnerAdapter roomSpinnerAdapter = new RoomSpinnerAdapter(getApplicationContext(), R.layout.room_dropdown_item, roomList);
        roomSpinnerAdapter.setDropDownViewResource(R.layout.room_dropdown_item);
        roomSpinner.setAdapter(roomSpinnerAdapter);
        // meetingDurationSpinner
        final Spinner durationSpinner = findViewById(R.id.spinner_duration_action);
        DurationSpinnerAdapter durationSpinnerAdapter = new DurationSpinnerAdapter(getApplicationContext(), R.layout.time_dropdown_item, durationList);
        durationSpinnerAdapter.setDropDownViewResource(R.layout.time_dropdown_item);
        durationSpinner.setAdapter(durationSpinnerAdapter);
    }

    // TODO : methode a completer
    // Recuperé Room sélectioné dans spinner
    // Recuperé Durée sélectioné dans spinner
    // Recuperé string sur title button date
    // Recuperé string sur title button time

/**
    @OnClick(R.id.CreateMeetingButton)
    public void createMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                mMeetingTopicInput.getEditableText().toString(),
                mMeetingDatePicker,
                mMeetingTimePicker,
                mMeetingDurationSpinner.getSelectedItem(),
                mMeetingRoomSpinner.getSelectedItem(),
                mMeetingGuestList
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

        // set text instate of text button
        TextView textViewDate = (TextView) findViewById(R.id.date_picker_actions);
        textViewDate.setText(currentDateString);
    }

    // Set meetingTimePicker
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar t = Calendar.getInstance();
        t.set(Calendar.HOUR_OF_DAY, hourOfDay);
        t.set(Calendar.MINUTE, minute);

        // set Time instate of text button
        TextView textViewTime = (TextView) findViewById(R.id.time_picker_action);
        textViewTime.setText(hourOfDay + ":" + minute);
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, CreateMeetingActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }
}