package com.pierre44.mareu.ui_meeting_list;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.pierre44.mareu.R;
import com.pierre44.mareu.adapter.MeetingRecyclerViewAdapter;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.events.DeleteMeetingEvent;
import com.pierre44.mareu.events.FilterByDateEvent;
import com.pierre44.mareu.events.FilterByRoomEvent;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
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
@SuppressLint("NonConstantResourceId")
public class ListMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private static final String SELECT_ROOM = "SELECT_ROOM";
    private MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    private static  List<Meeting> mMeetings;
    private RoomDialogFragment filterByRoomFragment;
    private List<Room> mRooms;
    private MeetingRecyclerViewAdapter meetingRecyclerViewAdapter;
    Toolbar toolbar;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view_meeting)
    RecyclerView mRecyclerView;
    @BindView(R.id.create_meeting_button)
    FloatingActionButton mCreateMeetingButton;

    // On create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_list_activity);
        ButterKnife.bind(this);

        this.configureToolbar();
        setSupportActionBar(mToolbar);

        mMeetingRepository = DI.getMeetingRepository();
        mRecyclerView = findViewById(R.id.recycler_view_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMeetings = mMeetingRepository.getMeetings();
        meetingRecyclerViewAdapter = new MeetingRecyclerViewAdapter(mMeetings);
        mRecyclerView.setAdapter(meetingRecyclerViewAdapter);
        meetingRecyclerViewAdapter.notifyDataSetChanged();
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
        //meetingRecyclerViewAdapter.notifyDataSetChanged();
        EventBus.getDefault().register(this);
        super.onResume();
        meetingRecyclerViewAdapter.refreshList();
    }

    // onStop to unregister subscriber
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // Configure filter menu click
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final FragmentManager fm = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.filterDate:
                DialogFragment datePickerDialogFragment = new DatePickerFragment();
                datePickerDialogFragment.show(getSupportFragmentManager(), "date picker");
                break;
            case R.id.filterRoom:
                filterByRoomFragment = RoomDialogFragment.newInstance();
                filterByRoomFragment.show(fm, SELECT_ROOM);
                break;
            case R.id.filterReset:
                meetingRecyclerViewAdapter.refreshList(UtilsTools.FilterType.NONE, null);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
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

    // Subscribe filter by room
    @Subscribe
    public void filterByRoom(FilterByRoomEvent filterByRoomEvent) {
        meetingRecyclerViewAdapter.refreshList(UtilsTools.FilterType.BY_ROOM, filterByRoomEvent.room);
        filterByRoomFragment.dismiss();
    }

    // Subscribe filter by Date
    @Subscribe
    public void filterByDate(FilterByDateEvent filterByDateEvent) {
        meetingRecyclerViewAdapter.refreshList(UtilsTools.FilterType.BY_DATE, filterByDateEvent.date);
    }

    // Custom snackBar
    private void snackBarThis(String toastThis, View v) {
        Snackbar.make(v, toastThis, Snackbar.LENGTH_SHORT)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                .setBackgroundTint(getResources().getColor(R.color.lamzone_500))
                .setTextColor(getResources().getColor(R.color.primaryTextColor))
                .show();
    }

    // Set and format date to send
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDateString = UtilsTools.dateFormat(c, UtilsTools.DATE_FORMAT_DD_MM_YYYY);
        meetingRecyclerViewAdapter.refreshList(UtilsTools.FilterType.BY_DATE, (Object) selectedDateString);
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