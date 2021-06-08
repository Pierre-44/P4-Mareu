package com.pierre44.mareu.ui_meeting_list;

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
import com.pierre44.mareu.repository.MeetingRepository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private final MeetingRepository mRepository = DI.getMeetingRepository();


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
        // ViewHolder
        Meeting meeting = mMeetings.get(position);
        holder.meetingTopic.setText(meeting.getMeetingTopic());
        holder.meetingRoomImage.setImageDrawable(Objects.requireNonNull(holder.itemView).getContext().getDrawable(meeting.getMeetingRoom().getRoomImage()));
        holder.meetingRoomText.setText(meeting.getMeetingRoom().getRoomName());
        holder.meetingTime.setText(meeting.getMeetingStartTime());
        holder.meetingGuestsList.setText((CharSequence) meeting.getGuests());

        // Delete meeting event
        holder.mDelete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });

        // go to DetailNeighbourActivity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDetailNeighbourActivity = new Intent(holder.itemView.getContext(), CreateMeetingActivity.class);
                goToDetailNeighbourActivity.putExtra("Neighbour", mMeetings.get(position));
                holder.itemView.getContext().startActivity(goToDetailNeighbourActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.meeting_topic)
        public TextView meetingTopic;
        @BindView(R.id.item_list_avatar)
        public ImageView meetingRoomImage;
        @BindView(R.id.meeting_room)
        public TextView meetingRoomText;
        @BindView(R.id.meeting_time)
        public TextView meetingTime;
        @BindView(R.id.guests_email_list)
        public TextView meetingGuestsList;
        @BindView(R.id.delete_button)
        public ImageButton mDelete_button;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @Override
        public void onClick(View v) {
            // TODO : implement methode
        }
    }
}
