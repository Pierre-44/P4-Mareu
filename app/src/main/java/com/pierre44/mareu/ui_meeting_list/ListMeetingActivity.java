package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.pierre44.mareu.R;
import com.pierre44.mareu.adapter.MeetingRecyclerViewAdapter;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.events.DeleteMeetingEvent;
import com.pierre44.mareu.events.FilterByDateEvent;
import com.pierre44.mareu.events.FilterByRoomEvent;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.repository.DummyGenerator;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.utils.UtilsTools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingRepository mMeetingRepository = DI.getMeetingRepository();
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

        mMeetings = mMeetingRepository.getMeetings();

        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(mMeetings, mMeetingRepository.getRooms());

        mRecyclerView.setAdapter(meetingRecyclerViewAdapter);

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


    // Create Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meeting_list, menu);
        return true;
    }

    // onResume to register subscriber
    @Override
    public void onResume() {
        meetingRecyclerViewAdapter.notifyDataSetChanged();
        EventBus.getDefault().register(this);
        super.onResume();
    }

    // onStop to unregister subscriber
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // Configure menu click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterRoom:
                //create the dialog fragment of the Room list for the Room filter
                final FragmentManager fm = getSupportFragmentManager();
                RoomDialogFragment filterByRoomFragment = RoomDialogFragment.newInstance();
                filterByRoomFragment.show(fm, "SELECTED_ROOM");
            case R.id.filterDate:
                //create the datePicker dialog fragment for the date filter
                final Calendar c = Calendar.getInstance();
                final boolean is24Hours = DateFormat.is24HourFormat(this);
                final FilterByDatePickerFragment filterByDateDialogFragment = FilterByDatePickerFragment.newInstance(
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        is24Hours);
                filterByDateDialogFragment.setListener(this);
                filterByDateDialogFragment.showNow(filterByDateDialogFragment.getParentFragmentManager(), "filterByDatePickerFragment");
            case R.id.filterReset:
                meetingRecyclerViewAdapter.refresh(UtilsTools.FilterType.NONE,3);
        }
                return true;
    }

    @OnClick(R.id.create_meeting_button)
    public void createMeeting() {
        CreateMeetingActivity.navigate(this);
    }

    // Subscribe delete meeting event and notifyDataSetChanged
    @Subscribe
    public void deleteMeeting(DeleteMeetingEvent deleteMeetingEvent) {
        mMeetings.remove(deleteMeetingEvent.meeting);
        meetingRecyclerViewAdapter.notifyDataSetChanged();
    }

    // Subscribe filter by room and notifyDataSetChanged
    @Subscribe
    public void filterByRoom(FilterByRoomEvent filterByRoomEvent) {
        mMeetings.clear();
        mMeetings.addAll(mMeetingRepository.filterByRoom(filterByRoomEvent.room));
        meetingRecyclerViewAdapter.notifyDataSetChanged();
    }

    // Subscribe filter by Date and notifyDataSetChanged
    @Subscribe
    public void filterByDate(FilterByDateEvent filterByDateEvent) {
        mMeetings.clear();
        mMeetings.addAll(mMeetingRepository.filterByDate(filterByDateEvent.date));
        meetingRecyclerViewAdapter.notifyDataSetChanged();
    }

    // Custom snackBar
    private void snackBarThis(String toastThis, View v) {
        Snackbar.make(v, toastThis, Snackbar.LENGTH_SHORT)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(getResources().getColor(R.color.lamzone_500))
                .setTextColor(getResources().getColor(R.color.primaryTextColor))
                .show();
    }

    @VisibleForTesting
    public void doMeetingListEmpty() {
        mMeetingRepository.getMeetings().clear();
        meetingRecyclerViewAdapter.notifyDataSetChanged();
    }

    @VisibleForTesting
    public void addAllTestMeetings() {
        mMeetingRepository.getMeetings().addAll(DummyGenerator.DUMMY_MEETINGS_LIST);
        meetingRecyclerViewAdapter.notifyDataSetChanged();
    }
}