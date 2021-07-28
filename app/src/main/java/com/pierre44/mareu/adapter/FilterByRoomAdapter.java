package com.pierre44.mareu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierre44.mareu.R;
import com.pierre44.mareu.events.FilterByRoomEvent;
import com.pierre44.mareu.model.Room;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterByRoomAdapter extends RecyclerView.Adapter<FilterByRoomAdapter.FilterRoomViewHolder> {

    private final List<Room> mRooms;
    private final Context mContext;

    public FilterByRoomAdapter(List<Room> rooms, Context context) {
        mRooms = rooms;
        mContext = context;
    }

    @NonNull
    @Override
    public FilterRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_dropdown_item, parent, false);
        return new FilterRoomViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(FilterRoomViewHolder holder, int position) {
        Room room = mRooms.get(position);

        holder.mRoom.setText(room.getRoomName());
        holder.mRoomImage.setImageDrawable(holder.itemView.getContext().getDrawable(room.getRoomImage()));
        holder.itemView.setOnClickListener(v -> EventBus.getDefault().post(new FilterByRoomEvent(room)));
    }

    @Override
    public long getItemId(int position) {
        return mRooms.get(position).getRoomId();
    }

    @Override
    public int getItemCount() {
        return mRooms.size();
    }

    // Inner ViewHolder class
    @SuppressLint("NonConstantResourceId")
    public static class FilterRoomViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.meeting_room_text)
        TextView mRoom;
        @BindView(R.id.meeting_room_icon)
        ImageView mRoomImage;

        public FilterRoomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}