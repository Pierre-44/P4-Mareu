package com.pierre44.mareu.ui_meeting_list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.adapter.MeetingDetailsRecyclerViewAdapter;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.User;
import com.pierre44.mareu.repository.MeetingRepository;

import java.util.List;

import butterknife.BindView;

import static com.pierre44.mareu.adapter.MeetingRecyclerViewAdapter.CLICKED_MEETING;

public class MeetingDetailsActivity extends AppCompatActivity {

    private final MeetingRepository mMeetingRepository = DI.getMeetingRepository();

    Meeting meeting ;
    private List<User> mGuests ;
    private MeetingDetailsRecyclerViewAdapter mMeetingDetailsRecyclerViewAdapter;

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
    @BindView(R.id.guests_email_chip_group)
    RecyclerView mDetailRecyclerview;
    @BindView(R.id.detail_action_close)
    Button mActionClose;

    // On create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_details_activity);

        meeting = (Meeting) getIntent().getSerializableExtra(CLICKED_MEETING);
        mGuests = mMeetingRepository.getGuests(CLICKED_MEETING);

        //widgets connection
        mDetailMeetingTopic = findViewById(R.id.detail_meeting_topic);
        mDetailMeetingDate = findViewById(R.id.detail_meeting_date);
        mDetailMeetingTime = findViewById(R.id.detail_meeting_time);
        mDetailMeetingRoom = findViewById(R.id.detail_meeting_room);
        mDetailMeetingRoomImage = findViewById(R.id.detail_meeting_room_image);
        mDetailMeetingDuration = findViewById(R.id.detail_meeting_duration);
        mDetailRecyclerview = findViewById(R.id.detail_guests_recycler_view_email_list);
        mActionClose = findViewById(R.id.detail_action_close);
        mActionClose.setOnClickListener(v -> backToListMeetingActivity());
        initView();
    }

    private void backToListMeetingActivity() {
        Intent intent = new Intent(this, ListMeetingActivity.class);
        startActivity(intent);
    }

    // Initialisation of view
    private void initView() {
        mDetailMeetingTopic.setText(meeting.getMeetingTopic());
        mDetailMeetingDate.setText(meeting.getMeetingStartDate());
        mDetailMeetingTime.setText(meeting.getMeetingStartTime());
        mDetailMeetingRoom.setText(meeting.getMeetingRoom().getRoomName());
        mDetailMeetingRoomImage.setImageDrawable(getDrawable(meeting.getMeetingRoom().getRoomImage()));
        mDetailMeetingDuration.setText(meeting.getMeetingDuration());

        mMeetingDetailsRecyclerViewAdapter = new MeetingDetailsRecyclerViewAdapter(mGuests);
        mDetailRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mDetailRecyclerview.setAdapter(mMeetingDetailsRecyclerViewAdapter);
    }
}