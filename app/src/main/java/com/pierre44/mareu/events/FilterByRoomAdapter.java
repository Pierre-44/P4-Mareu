package com.pierre44.mareu.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Room;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pmeignen on 28/06/2021.
 */
public class FilterByRoomAdapter extends RecyclerView.Adapter<FilterByRoomAdapter.MeetingViewHolder> {

    private final List<Room> mRooms;


    public FilterByRoomAdapter(List<Room> rooms) {
        mRooms = rooms;
    }

    @NonNull
    @Override
    public FilterByRoomAdapter.MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting_scalable, parent, false);
        return new FilterByRoomAdapter.MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilterByRoomAdapter.MeetingViewHolder holder, int position) {
        Room room = mRooms.get(position);

        holder.mRoom.setText(room.getRoomName());

        holder.mRoomImage.setImageDrawable(holder.itemView.getContext().getDrawable(room.getRoomImage()));

        //meetingDeleteButton
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FilterByRoomEvent(room));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRooms.size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.meeting_room_text)
        TextView mRoom;
        @BindView(R.id.meeting_room_icon)
        ImageView mRoomImage;


        public MeetingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}