package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_list_activity);
        ButterKnife.bind(this);

        this.configureToolbar();

        mMeetingRepository = DI.getMeetingRepository();

        mRecyclerView = findViewById(R.id.recycler_view_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mMeetings = mMeetingRepository.getMeeting();
        mRecyclerView.setAdapter(new MeetingRecyclerViewAdapter(mMeetings, mMeetingRepository.getRooms()));
    }

    // Create Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the toolbar
        getMenuInflater().inflate(R.menu.menu_meeting_list, menu);
        return true;
    }

    // Configure menu click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterRoom:
                Toast.makeText(this, "R.string.filterByRoomToastMessage", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filterDate:
                Toast.makeText(this, "R.string.filterByDateToastMessage", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Configure Toolbar

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.CreateMeetingButton)
    void createMeeting() {
        CreateMeetingActivity.navigate(this);
    }
}