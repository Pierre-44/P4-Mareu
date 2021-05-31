package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.pierre44.mareu.R;

public class AddMeetingActivity extends AppCompatActivity {


    /**
     * (R.id.text_view_title);
     * (R.id.text_input_meeting_topic);
     * (R.id.textViewDate);
     * (R.id.date_picker_actions);
     * (R.id.textViewTime);
     * (R.id.time_picker_action);
     * (R.id.text_input_email);
     * (R.id.guests_email_list);
     * (R.id.buttonValidate);
     **/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}