package com.pierre44.mareu.ui_meeting_list;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.adapter.MeetingDetailsRecyclerViewAdapter;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.events.GetMeetingDetail;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.User;
import com.pierre44.mareu.repository.MeetingRepository;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by pmeignen on 05/07/2021.
 */
public class MeetingDetailsDialogFragment extends Fragment
        //implements View.OnClickListener
{

    MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    List<Meeting> mMeetings = new ArrayList<>();
    Meeting meeting;
    private MeetingDetailsRecyclerViewAdapter mMeetingDetailsRecyclerViewAdapter;
    private View mView;
    private Bundle mSavedInstanceState;


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


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.meeting_details_fragment, container, false);

    }

    @Subscribe
    public void onMeetingDetail(GetMeetingDetail event) {
        meeting = event.meeting;
        DetailPopup popup = new DetailPopup(getActivity());
        popup.build();
    }

    //Class for the Meeting detail popup
    public class DetailPopup extends Dialog {
        public DetailPopup(FragmentActivity activity) {
            super(activity, R.style.Theme_AppCompat_DayNight_Dialog);

        }

        public void build() {
            show();

            detailMeetingTopic.setText(meeting.getMeetingTopic());
            detailMeetingDate.setText(meeting.getMeetingStartDate());
            detailMeetingTime.setText(meeting.getMeetingStartDate());
            detailMeetingRoom.setText(meeting.getMeetingRoom().getRoomName());
            detailMeetingDuration.setText(meeting.getMeetingDuration());

            List<User> mGuestList = meeting.getMeetingGuests();
            detailGuestListRecyclerview.setAdapter(new MeetingDetailsRecyclerViewAdapter((ArrayList<User>) mGuestList));
        }
    }
}
