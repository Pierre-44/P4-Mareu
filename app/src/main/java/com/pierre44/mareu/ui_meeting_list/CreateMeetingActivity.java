package com.pierre44.mareu.ui_meeting_list;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.pierre44.mareu.R;
import com.pierre44.mareu.adapter.DurationSpinnerAdapter;
import com.pierre44.mareu.adapter.RoomSpinnerAdapter;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.model.User;
import com.pierre44.mareu.repository.DummyGenerator;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.utils.UtilsTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

    @BindView(R.id.text_input_meeting_topic)
    TextInputEditText mMeetingTopicInput;
    @BindView(R.id.date_picker_actions)
    Button mMeetingDatePicker;
    @BindView(R.id.time_picker_action)
    Button mMeetingTimePicker;
    @BindView(R.id.spinner_room_action)
    Spinner mMeetingRoomSpinner;
    @BindView(R.id.detail_meeting_duration)
    Spinner mMeetingDurationSpinner;
    @BindView(R.id.text_input_email)
    TextInputEditText mMeetingEmailInput;
    @BindView(R.id.detail_guests_email_recyclerview)
    ChipGroup mMeetingGuestChipGroup;
    @BindView(R.id.create_meeting_validate_button)
    MaterialButton mMeetingValidateButton;

    private MeetingRepository mMeetingRepository;
    private Calendar mCalendar;
    private int idChip;
    private Room selectedRoom;
    private Meeting meeting;
    private List<User> mMeetingGuestList;
    final List<Room> roomList = DummyGenerator.DUMMY_ROOMS_LIST;
    final List<String> durationList = Arrays.asList("", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45", "02:00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meeting_activity);
        ButterKnife.bind(this);
        init();
    }

    /**
     * Initialize vars and set listeners , TextChanger etc...
     */
    private void init() {

        mMeetingRepository = DI.getMeetingRepository();
        mCalendar = Calendar.getInstance();
        mMeetingGuestList = new ArrayList<>();

        initPickersAndSpinners();

        onMeetingGuestSet();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMeetingRoomSpinner.setOnItemSelectedListener(this);
        mMeetingDurationSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.date_picker_actions:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;
            case R.id.time_picker_action:
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                break;
            case R.id.create_meeting_validate_button:
                createMeeting();
        }
    }

    /**
     * Create meeting.
     */
// Create meeting methode
    @OnClick(R.id.create_meeting_validate_button)
    public void createMeeting() {
        Meeting meeting = new Meeting(
                System.currentTimeMillis(),
                mMeetingTopicInput.getText().toString(),
                (String) mMeetingDatePicker.getText(),
                (String) mMeetingTimePicker.getText(),
                (String) mMeetingDurationSpinner.getSelectedItem(),
                (Room) mMeetingRoomSpinner.getSelectedItem(),
                mMeetingGuestList
        );
        mMeetingRepository.createMeeting(meeting);
        //Toast.makeText(this, mMeetingDatePicker.getText().toString(), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        // set Date instate of text button
        String currentDateString = UtilsTools.dateFormat(c, UtilsTools.DATE_FORMAT_DD_MM_YYYY);
        mMeetingDatePicker.setText(currentDateString);
        checkIfValidateButtonCanActivated();
    }

    // Set meetingTimePicker
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        // set Time instate of text button
        String currentTimeString = UtilsTools.dateFormat(c, UtilsTools.TIME_FORMAT_HH_MM);
        mMeetingTimePicker.setText(currentTimeString);
        checkIfValidateButtonCanActivated();
    }

    // init Pickers And Spinners methode
    private void initPickersAndSpinners() {

        // meetingDatePicker
        Button dateButton = findViewById(R.id.date_picker_actions);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vd) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        // meetingTimePicker
        Button timeButton = findViewById(R.id.time_picker_action);
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
        checkIfValidateButtonCanActivated();
        // meetingDurationSpinner
        final Spinner durationSpinner = findViewById(R.id.detail_meeting_duration);
        DurationSpinnerAdapter durationSpinnerAdapter = new DurationSpinnerAdapter(getApplicationContext(), R.layout.time_dropdown_item, durationList);
        durationSpinnerAdapter.setDropDownViewResource(R.layout.time_dropdown_item);
        durationSpinner.setAdapter(durationSpinnerAdapter);
        checkIfValidateButtonCanActivated();
    }

    private void onMeetingGuestSet() {
        mMeetingEmailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // nothing before
            }

            // perform IME_ACTION_DONE action when use " " , ";" or "," character on mMeetingEmailInput
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ") || s.toString().contains(";") || s.toString().contains(",")) {
                    mMeetingEmailInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // nothing after
            }
        });

        // input text email chip builder & test
        mMeetingEmailInput.setOnEditorActionListener((v, actionId, event) -> {
            String EmailInputText = v.getText().toString();
            EmailInputText = EmailInputText.replace(" ", "").replace(";", "").replace(",", "");
            if (EmailInputText.contains("@") && !EmailInputText.endsWith("@") && EmailInputText.contains(".") && !EmailInputText.endsWith(".")) {
                mMeetingGuestList.add(new User(1, "toto", EmailInputText));
                Chip chip = new Chip(CreateMeetingActivity.this);
                chip.setText(EmailInputText);
                chip.setCloseIconVisible(true);
                chip.setCloseIconTintResource(R.color.lamzone_900);
                chip.setChipBackgroundColorResource(R.color.lamzone_500);
                chip.setTextColor(getResources().getColor(R.color.primaryTextColor));
                chip.setId(idChip++);
                chip.setOnCloseIconClickListener(v1 -> {
                    mMeetingGuestChipGroup.removeView(v1);
                    mMeetingGuestList.remove(chip.getText().toString());
                    CreateMeetingActivity.this.checkIfValidateButtonCanActivated();
                });
                mMeetingGuestChipGroup.addView(chip);
            } else {
                Snackbar.make(v, R.string.Erreur_email, Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.lamzone_500))
                        .setTextColor(getResources().getColor(R.color.primaryTextColor))
                        .show();
            }
            checkIfValidateButtonCanActivated();
            mMeetingEmailInput.setText("");
            return false;
        });
    }

    private void checkIfValidateButtonCanActivated() {
        mMeetingValidateButton.setEnabled(
                !mMeetingTopicInput.getText().toString().isEmpty()
                        && !mMeetingDatePicker.getText().toString().equals(getResources().getString(R.string.add_meeting_date_picker))
                        && !mMeetingTimePicker.getText().toString().equals(getResources().getString(R.string.add_meeting_date_picker))
                        && !mMeetingRoomSpinner.getSelectedItem().toString().isEmpty()
                        && !mMeetingDurationSpinner.getSelectedItem().toString().isEmpty()
                        && !mMeetingGuestList.isEmpty()
        );
    }

    /**
     * Navigate.
     *
     * @param context the context
     */
    public static void navigate(Context context) {
        Intent intent = new Intent(context, CreateMeetingActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }
}