package com.pierre44.mareu.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.di.DI;
import com.pierre44.mareu.events.DeleteMeetingEvent;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.ui_meeting_list.MeetingDetailsDialogFragment;
import com.pierre44.mareu.utils.UtilsTools;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    private List<Meeting> mMeetings;
    private final List<Room> mRooms;
    static final String CLICKED_MEETING = "CLICKED_MEETING";

    private UtilsTools.FilterType mFilterType = UtilsTools.FilterType.NONE;
    private long mFilterValue;

    public List<Room> getRooms() {
        return mRooms;
    }

    public MeetingRecyclerViewAdapter(List<Meeting> meetings, List<Room> rooms) {
        mMeetings = meetings;
        mRooms = rooms;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting_scalable, parent, false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        //meetingTopic
        holder.meetingTopic.setText(meeting.getMeetingTopic());
        holder.meetingTopic.setNestedScrollingEnabled(true);
        holder.meetingTopic.setSelected(true);
        //meetingRoomImage
        holder.meetingRoomImage.setImageDrawable(holder.itemView.getContext().getDrawable(meeting.getMeetingRoom().getRoomImage()));
        //meetingRoomText
        holder.meetingRoomText.setText(meeting.getMeetingRoom().getRoomName());
        //meetingDate
        holder.meetingDate.setText(meeting.getMeetingStartDate());
        //meetingTime
        holder.meetingTime.setText(meeting.getMeetingStartTime());
        //meetingGuestsList
        String meetingGuestsList = "";
        for (int i = 0; i < meeting.getMeetingGuests().size(); i++) {
            meetingGuestsList += meeting.getMeetingGuests().get(i).getEmail() + " - ";
        }
        holder.meetingGuestsList.setText(meetingGuestsList);
        holder.meetingGuestsList.setNestedScrollingEnabled(true);
        holder.meetingGuestsList.setSelected(true);

        //meeting Delete & detail event
        holder.meetingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(mMeetings.get(position)));
            }
        });
        // go to Detail meeting dialog fragment
        // TODO : resolve error : android.content.ActivityNotFoundException: Unable to find explicit activity class {com.pierre44.mareu/com.pierre44.mareu.ui_meeting_list.MeetingDetailsDialogFragment}; have you declared this activity in your AndroidManifest.xml?

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDetailMeetingDialogFragment = new Intent(holder.itemView.getContext(), MeetingDetailsDialogFragment.class);
                goToDetailMeetingDialogFragment.putExtra(CLICKED_MEETING, mMeetings.get(position));
                holder.itemView.getContext().startActivity(goToDetailMeetingDialogFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public void refreshList(UtilsTools.FilterType filterType, long filterValue) {
        mFilterType = filterType;
        mFilterValue = filterValue;
        switch (filterType) {
            case NONE:
                mMeetings = mMeetingRepository.getMeetings();
                break;
            case BY_DATE:
                mMeetings = mMeetingRepository.getMeetingsForFilterMeetingDate(filterValue);
                break;
            case BY_ROOM:
                mMeetings = mMeetingRepository.getMeetingsForFilterMeetingRoom(filterValue);
                break;
        }
    }


    public void refresh(UtilsTools.FilterType none, long filterValue) {
        this.refresh(mFilterType, mFilterValue);
    }

    @SuppressLint("NonConstantResourceId")
    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.meeting_topic)
        TextView meetingTopic;
        @BindView(R.id.item_list_avatar)
        ImageView meetingRoomImage;
        @BindView(R.id.meeting_room)
        TextView meetingRoomText;
        @BindView(R.id.meeting_time)
        TextView meetingTime;
        @BindView(R.id.meeting_date)
        TextView meetingDate;
        @BindView(R.id.guests_email_group)
        TextView meetingGuestsList;
        @BindView(R.id.delete_button)
        ImageButton meetingDeleteButton;

        public MeetingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}