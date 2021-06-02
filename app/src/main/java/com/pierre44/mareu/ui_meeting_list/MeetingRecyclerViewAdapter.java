package com.pierre44.mareu.ui_meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    // widget connect

    @Override
    public void onBindViewHolder(MeetingRecyclerViewAdapter.ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.meetingTopic.setText(meeting.getMeetingTopic());
        holder.meetingRoomImage.setImageDrawable(holder.itemView.getContext().getDrawable(meeting.getMeetingRoom().getRoomImage()));
        holder.meetingRoomText.setText(meeting.getMeetingRoom().getRoomName());
        holder.meetingTime.setText(meeting.getMeetingStartTime());
        holder.meetingGuestsList.setText((CharSequence) meeting.getGuests());
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
