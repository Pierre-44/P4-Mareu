package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.events.AddMeetingActivity;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;

import java.util.List;

import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingRepository mMeetingRepository;
    private List<Meeting> mMeetings;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_list_activity);

        mMeetingRepository = DI.getMeetingRepository();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mMeetings = mMeetingRepository.getMeeting();
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings));
    }

    @OnClick(R.id.addMeetingButton)
    void addMeeting() {
        AddMeetingActivity.navigate(this);
    }
}