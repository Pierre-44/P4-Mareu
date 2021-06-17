package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.events.DeleteMeetingEvent;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.MeetingRepository;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingRepository mMeetingRepository;
    private List<Meeting> mMeetings;
    private MeetingRecyclerViewAdapter meetingRecyclerViewAdapter;

    Toolbar toolbar;

    @BindView(R.id.recycler_view_meeting)
    RecyclerView mRecyclerView;

    // On create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_list_activity);
        ButterKnife.bind(this);

        this.configureToolbar();

        mMeetingRepository = DI.getMeetingRepository();

        mRecyclerView = findViewById(R.id.recycler_view_meeting);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMeetings = mMeetingRepository.getMeeting();

        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(mMeetings, mMeetingRepository.getRooms());

        mRecyclerView.setAdapter(meetingRecyclerViewAdapter);

    }

    // Create Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the toolbar
        getMenuInflater().inflate(R.menu.menu_meeting_list, menu);
        return true;
    }

    // onResume to register subscriber
    @Override
    protected void onResume() {
        EventBus.getDefault().register(this);
        super.onResume();
    }

    // onStop to unregister subscriber
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // Configure menu click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterRoom:
                Toast.makeText(this, R.string.filterByRoomToastMessage, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filterDate:
                Toast.makeText(this, R.string.filterByDateToastMessage, Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, R.string.filterDisable, Toast.LENGTH_SHORT).show();
                return true;
        }
    }

    // Configure Toolbar
    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
    }

    @OnClick(R.id.CreateMeetingButton)
    void createMeeting() {
        CreateMeetingActivity.navigate(this);
    }

    // Subscribe delete meeting event and notifyDataSetChanged
    @Subscribe
    public void deleteMeeting(DeleteMeetingEvent deleteMeetingEvent) {
        mMeetings.remove(deleteMeetingEvent.meeting);
        meetingRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void snackBarThis(String toastThis, View v){
        Snackbar.make(v, toastThis, Snackbar.LENGTH_SHORT)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(getResources().getColor(R.color.lamzone_500))
                .setTextColor(getResources().getColor(R.color.primaryTextColor))
                .show();
    }
}