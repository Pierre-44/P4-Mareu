package com.pierre44.mareu.ui_meeting_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.adapter.MeetingDetailsRecyclerViewAdapter;
import com.pierre44.mareu.events.GetMeetingDetail;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.User;
import com.pierre44.mareu.repository.MeetingRepository;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * Created by pmeignen on 05/07/2021.
 */
public class MeetingDetailsDialogFragment extends DialogFragment {

    private MeetingRepository mMeetingRepository;
    Meeting meeting;

    private static final String TAG = "MeetingDetailsDialogFra";

    // widget binding
    @BindView(R.id.detail_meeting_topic)
    TextView detailMeetingTopic;
    @BindView(R.id.detail_meeting_date)
    TextView detailMeetingDate;
    @BindView(R.id.detail_meeting_time)
    TextView detailMeetingTime;
    @BindView(R.id.detail_meeting_room)
    TextView detailMeetingRoom;
    @BindView(R.id.detail_meeting_duration)
    TextView detailMeetingDuration;
    @BindView(R.id.detail_guests_email_recyclerview)
    RecyclerView detailGuestListRecyclerview;

    @BindView(R.id.detail_action_ok)
    EditText mActionOk;


    // TODO cf  ROOM DIALOG FRAGMENT CLASS pour faire fonctioner le popoup

   //@Override
   //public DialogFragment onCreateDialog(@Nullable Bundle savedInstanceState) {
   //
   //}


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meeting_details_fragment, container);

        // click OK for dismiss dialog
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialogFragment");
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Subscribe
    public void onMeetingDetail(GetMeetingDetail event) {
        meeting = event.meeting;
        DetailPopup popup = new DetailPopup();
        popup.build();
    }

    public void show() {
    }

    private class DetailPopup {
        public void build() {
            detailMeetingTopic.setText(meeting.getMeetingTopic());
            detailMeetingDate.setText(meeting.getMeetingStartDate());
            detailMeetingTime.setText(meeting.getMeetingStartDate());
            detailMeetingRoom.setText(meeting.getMeetingRoom().getRoomName());
            detailMeetingDuration.setText(meeting.getMeetingDuration());
            List<User> mGuestList = meeting.getMeetingGuests();
            detailGuestListRecyclerview.setAdapter(new MeetingDetailsRecyclerViewAdapter(mGuestList));
        }
    }
}