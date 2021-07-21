package com.pierre44.mareu.ui_meeting_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;

import butterknife.BindView;

import static com.pierre44.mareu.adapter.MeetingRecyclerViewAdapter.CLICKED_MEETING;

/**
 * Created by pmeignen on 05/07/2021.
 */
@SuppressLint("NonConstantResourceId")
public class MeetingDetailsActivity extends AppCompatActivity {

    private MeetingRepository mMeetingRepository;
    Meeting meeting;
    private Toolbar mToolbar;
    private static final String TAG = "MeetingDetailsActivity";

    // widget binding
    @BindView(R.id.detail_meeting_topic)
    TextView mDetailMeetingTopic;
    @BindView(R.id.detail_meeting_date)
    TextView mDetailMeetingDate;
    @BindView(R.id.detail_meeting_time)
    TextView mDetailMeetingTime;
    @BindView(R.id.detail_meeting_room)
    TextView mDetailMeetingRoom;
    @BindView(R.id.detail_meeting_room_image)
    ImageView mDetailMeetingRoomImage;
    @BindView(R.id.spinner_meeting_duration)
    TextView mDetailMeetingDuration;
    @BindView(R.id.detail_guests_email_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.detail_action_close)
    Button mActionClose;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.meeting_details_activity);
        meeting = (Meeting) getIntent().getSerializableExtra(CLICKED_MEETING);
        //widgets connection
        mDetailMeetingTopic = findViewById(R.id.detail_meeting_topic);
        mDetailMeetingDate = findViewById(R.id.detail_meeting_date);
        mDetailMeetingTime = findViewById(R.id.detail_meeting_time);
        mDetailMeetingRoom = findViewById(R.id.detail_meeting_room);
        mDetailMeetingRoomImage = findViewById(R.id.detail_meeting_room_image);
        mDetailMeetingDuration = findViewById(R.id.detail_meeting_duration);
        mRecyclerView = findViewById(R.id.detail_guests_email_recyclerview);
        mActionClose = findViewById(R.id.detail_action_close);
        initView();
    }

    // Initialisation of view
    private void initView() {
        mDetailMeetingTopic.setText(meeting.getMeetingTopic());
        mDetailMeetingDate.setText(meeting.getMeetingStartDate());
        mDetailMeetingTime.setText(meeting.getMeetingStartTime());
        mDetailMeetingRoom.setText(meeting.getMeetingRoom().getRoomName());
        //TODO : setImageDrawable correct ?
        mDetailMeetingRoomImage.setImageDrawable(getDrawable(meeting.getMeetingRoom().getRoomImage()));
        mDetailMeetingDuration.setText(meeting.getMeetingDuration());
        mRecyclerView.setAdapter(mRecyclerView.getAdapter());
    }
}