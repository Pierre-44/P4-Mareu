package com.pierre44.mareu.adapter;

import android.annotation.SuppressLint;
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
import com.pierre44.mareu.events.GetMeetingDetail;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;
import com.pierre44.mareu.repository.MeetingRepository;
import com.pierre44.mareu.utils.UtilsTools;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    private List<Meeting> mMeetings;
    private List<Room> mRooms;
    public static final String CLICKED_MEETING = "CLICKED_MEETING";
    private UtilsTools.FilterType mFilterType = UtilsTools.FilterType.NONE;
    private Object mFilterValue;


    public MeetingRecyclerViewAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting_scalable, parent, false);
        final MeetingViewHolder viewHolder = new MeetingViewHolder(v);
        return new MeetingViewHolder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        //meetingTopic
        holder.meetingTopic.setText(meeting.getMeetingTopic());
        //meetingRoomImage
        holder.meetingRoomImage.setImageDrawable(holder.itemView.getContext().getDrawable(meeting.getMeetingRoom().getRoomImage()));
        //meetingRoomText
        holder.meetingRoomText.setText(meeting.getMeetingRoom().getRoomName());
        //meetingDate
        holder.meetingDate.setText(meeting.getMeetingStartDate());
        //meetingTime
        holder.meetingDuration.setText(meeting.getMeetingStartTime());
        //meetingGuestsList
        StringBuilder meetingGuestsList = new StringBuilder();
        for (int i = 0; i < meeting.getMeetingGuests().size(); i++) {
            meetingGuestsList.append(meeting.getMeetingGuests().get(i).getEmail()).append(" - ");
        }
        holder.meetingGuestsList.setText(meetingGuestsList.toString());
        //meeting Delete event
        holder.meetingDeleteButton.setOnClickListener(v -> {
            EventBus.getDefault().post(new DeleteMeetingEvent(mMeetings.get(position)));
            notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            if (mFilterType != UtilsTools.FilterType.NONE) {
                mMeetings.remove(position);
            }
        });

        //meeting detail event
        holder.itemView.setOnClickListener(v -> EventBus.getDefault().post(new GetMeetingDetail(mMeetings.get(position))));
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public void refreshList(UtilsTools.FilterType filterType, Object filterValue) {
        mFilterType = filterType;
        mFilterValue = filterValue;
        switch (filterType) {
            case NONE:
                mMeetings = mMeetingRepository.getMeetings();
                break;
            case BY_DATE:
                mMeetings = mMeetingRepository.filterByDate((String) filterValue);
                break;
            case BY_ROOM:
                mMeetings = mMeetingRepository.filterByRoom((Room) filterValue);
                break;
        }
        notifyDataSetChanged();
    }

    public void refreshList(List<Meeting> meetings) {
        mMeetings = meetings;
        this.refreshList();
    }

    public void refreshList() {
        this.refreshList(mFilterType, mFilterValue);
    }

    public Room getRoom(int position) {
        return mRooms.get(position);
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
        TextView meetingDuration;
        @BindView(R.id.meeting_date)
        TextView meetingDate;
        @BindView(R.id.guests_email_group)
        TextView meetingGuestsList;
        @BindView(R.id.delete_button)
        ImageButton meetingDeleteButton;

        public MeetingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}