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

import java.util.List;

/**
 * Created by pmeignen on 04/06/2021.
 */
public class RoomSpinnerAdapter extends ArrayAdapter<String> implements SpinnerAdapter {

    Context context;
    List<String> roomsList;

    // Constructor accepts Context and a list of rooms
    public RoomSpinnerAdapter(Context context, int room_dropdown_item, List<String> rooms) {
        super(context, R.layout.create_meeting_activity, rooms);
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
    public View getSpinnerRoomView(int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.room_dropdown_item, parent, false);

        // Image and TextViews
        TextView room = row.findViewById(R.id.meeting_duration_text);
        ImageView icon = row.findViewById(R.id.meeting_room_icon);

        // Get room icon from drawables folder
        Resources res = context.getResources();
        String drawableName = roomsList.get(position);
        int resId = res.getIdentifier(drawableName, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resId);

        //Set room text and room icon
        room.setText(roomsList.get(position));
        icon.setImageDrawable(drawable);

        return row;
    }
}
