package com.pierre44.mareu.events;

import androidx.annotation.NonNull;

public class FilterByDateEvent {

    public String date;

    public FilterByDateEvent(@NonNull String date) {this.date = date;}

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FilterByDateEvent that = (FilterByDateEvent) obj;
        return date.equals(that.date);
    }
}
