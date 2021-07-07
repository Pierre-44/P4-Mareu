package com.pierre44.mareu.events;

/**
 * Created by pmeignen on 10/06/2021.
 */
public class FilterByDateEvent {

    public String date;

    public FilterByDateEvent(String date) {this.date = date;}

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
