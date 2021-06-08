package com.pierre44.mareu.ui_meeting_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.pierre44.mareu.R;

import java.util.List;

/**
 * Created by pmeignen on 04/06/2021.
 */
public class TimeSpinnerAdapter extends ArrayAdapter<String> implements SpinnerAdapter {

    Context context;
    List<String> durationsList;


    // Constructor accepts Context and a list of rooms
    public TimeSpinnerAdapter(Context context, int time_dropdown_item, List<String> durations) {
        super(context, R.layout.create_meeting_activity, durations);
        this.context = context;
        this.durationsList = durations;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getSpinnerTimeView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getSpinnerTimeView(position, convertView, parent);
    }

    // Function to return custom View (View with an image and text)
    public View getSpinnerTimeView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.time_dropdown_item, parent, false);

        TextView durations = row.findViewById(R.id.meeting_duration_text);

        durations.setText(durationsList.get(position));

        return row;
    }
}
