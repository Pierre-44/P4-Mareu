package com.pierre44.mareu.events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.TimeFormat;
import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.ui_meeting_list.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

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
        setContentView(R.layout.create_meeting_activity);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMeetingRepository = DI.getMeetingRepository();

        // Set date picker
        Button dateButton = (Button) findViewById(R.id.date_picker_actions);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }

        });

        // Set time picker
        Button timeButton = (Button) findViewById(R.id.time_picker_action);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new DatePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

    }




    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textViewDate = (TextView) findViewById(R.id.date_picker_actions); // Todo : est ce que cella envoie le text à la place du text du boutton ?
        textViewDate.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textViewTime = (TextView) findViewById(R.id.time_picker_action);
        textViewTime.setText(hourOfDay + ":" + minute);
    }

    // TODO : methode a completer
    /**
    @OnClick(R.id.buttonValidate)
    void createMeeting() {
        Meeting meeting = new Meeting(System.currentTimeMillis(),
                meetingTopicInput.getEditText().toString(),
                meetingDatePicker.getDayOfMonth(),
                meetingTimePicker.textViewTime,
                meetingRoomSpinner.getPrompt(),
                meetingDurationSpinner.getPrompt(),
                meetingGuestList.getChipSpacingHorizontal(),
                );
    }
    **/

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, CreateMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}