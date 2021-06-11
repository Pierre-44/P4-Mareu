package com.pierre44.mareu.ui_meeting_list;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.pierre44.mareu.R;
import com.pierre44.mareu.model.Room;

import java.util.List;

/**
 * Created by pmeignen on 04/06/2021.
 */
public class RoomSpinnerAdapter extends ArrayAdapter<Room> implements SpinnerAdapter {

    Context context;
    List<Room> roomsList;

    // Constructor accepts Context and a list of rooms
    public RoomSpinnerAdapter(Context context, int room_dropdown_item, List<Room> rooms) {
        super(context, R.layout.create_meeting_activity);
        this.context = context;
        this.roomsList = rooms;
    }

    // Override these methods and instead return our custom view (with image and text)
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getSpinnerRoomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getSpinnerRoomView(position, convertView, parent);
    }

    // Function to return custom View (View with an image and text)
    public View getSpinnerRoomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.room_dropdown_item, parent, true);

        // Image and TextViews
        TextView roomText = row.findViewById(R.id.meeting_room_text);
        ImageView roomIcon = row.findViewById(R.id.meeting_room_icon);

        // Get room icon from drawables folder
        Resources res = context.getResources();
        Room mRoom = roomsList.get(position);
        Drawable drawable = context.getDrawable(mRoom.getRoomImage());

        //Set room text and room icon
        roomText.setText(roomsList.get(position).getRoomName());
        roomIcon.setImageDrawable(drawable);

        return row;
    }
}
