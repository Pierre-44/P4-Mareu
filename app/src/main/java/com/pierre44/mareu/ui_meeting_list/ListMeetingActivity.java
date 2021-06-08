package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingRepository mMeetingRepository;
    private List<Meeting> mMeetings;

    @BindView(R.id.recycler_view_meeting)
    RecyclerView mRecyclerView;
    @BindView(R.id.collapsingToolbarMenu)
    CollapsingToolbarLayout mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_list_activity);
        ButterKnife.bind(this);

        mMeetingRepository = DI.getMeetingRepository();

        mRecyclerView = findViewById(R.id.recycler_view_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mMeetings = mMeetingRepository.getMeeting();
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings));
    }

    @OnClick(R.id.CreateMeetingButton)
    void createMeeting() {
        CreateMeetingActivity.navigate(this);
    }
}