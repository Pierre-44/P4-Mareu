package com.pierre44.mareu.ui_meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.events.DeleteMeetingEvent;
import com.pierre44.mareu.model.Meeting;
import com.pierre44.mareu.model.Room;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final List<Meeting> mMeetings;
    private final List<Room> mRooms;

    public MeetingRecyclerViewAdapter(List<Meeting> meetings, List<Room> rooms) {
        mMeetings = meetings;
        mRooms = rooms;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.meetingTopic.setText(meeting.getMeetingTopic());
        holder.meetingRoomImage.setImageDrawable(holder.itemView.getContext().getDrawable(meeting.getMeetingRoom().getRoomImage()));
        holder.meetingRoomText.setText(meeting.getMeetingRoom().getRoomName());
        holder.meetingTime.setText(meeting.getMeetingStartTime());
        String meetingGuestsList = "";
        for (int i = 0; i < meeting.getGuests().size(); i++) {
            meetingGuestsList += "" + meeting.getGuests().get(i);
        }
        holder.meetingGuestsList.setText(meetingGuestsList);

        holder.meetingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(mMeetings.get(position)));

            }
        });
    }


    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.meeting_topic)
        TextView meetingTopic;
        @BindView(R.id.item_list_avatar)
        ImageView meetingRoomImage;
        @BindView(R.id.meeting_room)
        TextView meetingRoomText;
        @BindView(R.id.meeting_time)
        TextView meetingTime;
        @BindView(R.id.guests_email_list)
        TextView meetingGuestsList;
        @BindView(R.id.delete_button)
        ImageButton meetingDeleteButton;

        public MeetingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

